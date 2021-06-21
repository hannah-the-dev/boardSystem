package com.hannahj.bbs.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hannahj.bbs.domain.Post;

public class PostDaoImpl implements PostDao {
//		idx, user_name, pw, title, dateTime, content
	Connection conn = ConnectionPool.getConnection();
	Statement stmt = conn.createStatement();
	String query;
	ResultSet rset;
	public PostDaoImpl() throws ClassNotFoundException, SQLException {
	}
	
//	create
	@Override
	public void write(Post notice) throws SQLException {
		System.out.println(notice.toString());
		query = String.format(
				"insert into posts values(%s, null, '%s', '%s', now(), '%s', %s)",
				notice.getBoardIdx(), 
				notice.getUserName(), 
				notice.getTitle(), 
				notice.getContent(), 
				notice.getParentIdx()
				);
		try {
			stmt.execute(query);			
		} catch (SQLException e) {
			conn.rollback();
		}
		stmt.close();
		conn.close();
	}

//	read all
	@Override
	public List<Post> getList(int boardIdx) throws SQLException {
		query = String.format(
				"select * "
				+ "from posts "
				+ "where board_idx=%d "
				+ "and parent_idx is null "
				+ "order by idx desc;", boardIdx
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
		return board;
	}
	
//	read all
	@Override
	public List<Post> getList(int idx, int start, int sizer) throws SQLException {
		query = String.format(
				"select * "
						+ "from posts "
						+ "where board_idx=%d "
						+ "order by idx desc "
						+ "limit %s, %s;", 
						idx, start, sizer
				);
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
		return posts;
	}

//	read single data
	@Override
	public Post read(int boardIdx, int idx) throws SQLException {
//			return null;
//		}
		query = String.format(
				"select * "
//				+ "idx, user_name, pw, title, `date`, content "
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
		
		return posts.get(0);
	}

	public List<Post> getComments(int boardIdx, int parentIdx) throws SQLException {
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
		return comments;
	}
	
	
	@Override
	public boolean update(Post notice) throws SQLException {
//		if (!isCorrect(notice.getIdx(), pw)) {
//			return false;
//		}
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
//		if (!isCorrect(notice.getIdx(), pw)) {
//			return false;
//		}
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
		query = String.format(
				"select pw from posts "
				+ "where idx=%d and board_idx=%d;"
				, idx, BoardInfo.NOTICE.idx);
		rset = stmt.executeQuery(query);
		rset.last();
		boolean validPw = (rset.getString(1) == pw ? true : false);
		return validPw;
	}

	public int getMaxID() throws SQLException {
		query = "select count(*) from posts ";
		rset = stmt.executeQuery(query);
		rset.next();
		return rset.getInt(1);
	}
	
	public List<Post> search(String string)  throws SQLException {
		query = String.format(
				"select * from posts "
				+ "where content like '%s' "
				+ "or title like '%s';"
				, string, string);
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
			return posts;
	}
}
