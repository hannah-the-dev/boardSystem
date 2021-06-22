package com.hannahj.bbs.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hannahj.bbs.domain.Post;

public class PostDaoImpl implements PostDao {
//	Connection conn = ConnectionPool.getConnection();
//	Statement stmt = conn.createStatement();
	String query;
	ResultSet rset;	
	
	public static int BOARDSIZE = 10;
	public static int LISTSIZE = 10;
	
	public PostDaoImpl() throws ClassNotFoundException, SQLException {
	}
	
//	create
	@Override
	public void write(Post notice) throws SQLException {
		Connection conn = ConnectionPool.getConnection();
		Statement stmt = conn.createStatement();
		System.out.println(notice.toString());
		query = String.format(
				"insert into posts values(%s, null, '%s', '%s', now(), '%s', %s)",
				notice.getBoardIdx(), 
				notice.getUserName(), 
				notice.getTitle(), 
				notice.getContent(), 
				notice.getParentIdx()
				);
//		try {
			stmt.execute(query);			
//		} catch (SQLException e) {
//			conn.rollback();
//		}
		stmt.close();
		conn.close();
	}

//	read all
	@Override
	public List<Post> getList(int boardIdx, int start) throws SQLException {
		Connection conn = ConnectionPool.getConnection();
		Statement stmt = conn.createStatement();
		System.out.println(start);
		query = String.format(
				"select * "
				+ "from posts "
				+ "where board_idx=%d "
				+ "and parent_idx is null "
				+ "order by idx desc "
				+ "limit %d, %d;",
				boardIdx, start, BOARDSIZE
				);
		rset = stmt.executeQuery(query);
		
		List<Post> board = new ArrayList<Post>();
		while(rset.next()) {
			Post boardList = new Post(
					rset.getInt(PostColumns.BOARD_IDX.order),
					rset.getInt(PostColumns.IDX.order), 
					rset.getString(PostColumns.USER_NAME.order), 
					rset.getString(PostColumns.TITLE.order),
					rset.getDate(PostColumns.DATETIME.order),
					rset.getString(PostColumns.CONTENT.order),
					rset.getInt(PostColumns.PARENT_IDX.order)
			); 
			board.add(boardList);
			System.out.println(boardList.getIdx());
			System.out.println(boardList.getUserName());
			
		}
		rset.close();
		stmt.close();
		conn.close();
		return board;
	}
	
//	read single data
	@Override
	public Post read(int boardIdx, int idx) throws SQLException {
		Connection conn = ConnectionPool.getConnection();
		Statement stmt = conn.createStatement();
		query = String.format(
				"select * "
				+ "from posts "
				+ "where idx=%d and board_idx=%d and parent_idx is null;", 
				idx, boardIdx);
		rset = stmt.executeQuery(query);
		
		List<Post> posts = new ArrayList<Post>();
		while(rset.next()) {
			Post post = new Post(
					rset.getInt(PostColumns.BOARD_IDX.order),
					rset.getInt(PostColumns.IDX.order), 
					rset.getString(PostColumns.USER_NAME.order), 	
					rset.getString(PostColumns.TITLE.order),
					rset.getDate(PostColumns.DATETIME.order),
					rset.getString(PostColumns.CONTENT.order),
					rset.getInt(PostColumns.PARENT_IDX.order)
			); 
			posts.add(post);
		}		
		rset.close();
		stmt.close();
		conn.close();
		return posts.get(0);
	}
	
	@Override
	public List<Post> getComments(int boardIdx, int parentIdx) throws SQLException {
		Connection conn = ConnectionPool.getConnection();
		Statement stmt = conn.createStatement();
		query = String.format(
				"select * "
				+ "from posts "
				+ "where parent_idx=%d and board_idx=%d;",
				parentIdx, boardIdx
				);
		rset = stmt.executeQuery(query);
		
		List<Post> comments = new ArrayList<Post>();
		while(rset.next()) {
			Post comment = new Post(
					rset.getInt(PostColumns.BOARD_IDX.order),
					rset.getInt(PostColumns.IDX.order), 
					rset.getString(PostColumns.USER_NAME.order), 
					rset.getString(PostColumns.TITLE.order),
					rset.getDate(PostColumns.DATETIME.order),
					rset.getString(PostColumns.CONTENT.order),
					rset.getInt(PostColumns.PARENT_IDX.order)
			); 
			comments.add(comment);
		}
		rset.close();
		stmt.close();
		conn.close();
		return comments;
	}
	
	@Override
	public boolean update(Post notice) throws SQLException {
		Connection conn = ConnectionPool.getConnection();
		Statement stmt = conn.createStatement();
		query = String.format(
				"update	posts "
				+ "set "
					  + "user_name = '%s', "
					  + "title = '%s', "
					  + "`date` = now(), "
					  + "content = '%s' "
				+ "where idx=%d and board_idx=%d;",
				notice.getUserName(), 
				notice.getTitle(), 
				notice.getContent(), 
				notice.getIdx(), notice.getBoardIdx());
		stmt.executeUpdate(query);
		stmt.close();
		conn.close();
		return true;
	}

	@Override
	public boolean delete(int boardIdx, int idx) throws SQLException {
		Connection conn = ConnectionPool.getConnection();
		Statement stmt = conn.createStatement();
		query = String.format(
				"delete from posts "
				+ "where idx=%d and board_idx=%d;",
				idx, boardIdx);
		stmt.executeUpdate(query);
		stmt.close();
		conn.close();
		return true;
	}

	@Override
	public boolean isCorrect(int idx, String pw) throws SQLException {
		Connection conn = ConnectionPool.getConnection();
		Statement stmt = conn.createStatement();
		query = String.format(
				"select pw from posts "
				+ "where idx=%d and board_idx=%d;"
				, idx, BoardInfo.NOTICE.idx);
		rset = stmt.executeQuery(query);
		rset.last();
		boolean validPw = (rset.getString(1) == pw ? true : false);
		stmt.close();
		conn.close();
		return validPw;
	}
	
	@Override
	public int getMaxID() throws SQLException {
		Connection conn = ConnectionPool.getConnection();
		Statement stmt = conn.createStatement();
		query = "select max(idx) from posts; ";
		rset = stmt.executeQuery(query);
		rset.next();
		int maxId = rset.getInt(1);
		stmt.close();
		conn.close();
		return maxId;
	}
	
	@Override
	public int getPostCount(int boardIdx) throws SQLException {
		Connection conn = ConnectionPool.getConnection();
		Statement stmt = conn.createStatement();
		query = String.format(
				"select count(*) from posts "
				+ "where board_idx=%d and parent_idx is null;",
				boardIdx
				);
		rset = stmt.executeQuery(query);
		rset.next();
		int postCount = rset.getInt(1);
		stmt.close();
		conn.close();
		return postCount;
	}

	@Override
	public int getSearchCount(String regexp) throws SQLException {
		Connection conn = ConnectionPool.getConnection();
		Statement stmt = conn.createStatement();
		query = String.format(
				"select count(*) from posts "
						+ "where (content regexp '%s' "
						+ "or title regexp '%s') "
						+ "and parent_idx is null; "
						, regexp, regexp
				);
		rset = stmt.executeQuery(query);
		rset.next();
		int searchCount = rset.getInt(1);
		stmt.close();
		conn.close();
		return searchCount;
	}
	
	@Override
	public List<Post> search(String regexp, int start)  throws SQLException {
		Connection conn = ConnectionPool.getConnection();
		Statement stmt = conn.createStatement();
		query = String.format(
				"select * from posts "
				+ "where (regexp_like (content, '%s') "
				+ "or regexp_like (title, '%s')) "
				+ "and parent_idx is null "
				+ "limit %d, %d;"
				, regexp, regexp, start, BOARDSIZE);
		rset = stmt.executeQuery(query);
		List<Post> posts = new ArrayList<Post>();
		while(rset.next()) {
			Post post = new Post(
					rset.getInt(PostColumns.BOARD_IDX.order),
					rset.getInt(PostColumns.IDX.order), 
					rset.getString(PostColumns.USER_NAME.order), 	
					rset.getString(PostColumns.TITLE.order),
					rset.getDate(PostColumns.DATETIME.order),
					rset.getString(PostColumns.CONTENT.order),
					rset.getInt(PostColumns.PARENT_IDX.order)
			); 
			posts.add(post);
		}
		stmt.close();
		conn.close();
		return posts;
	}
}