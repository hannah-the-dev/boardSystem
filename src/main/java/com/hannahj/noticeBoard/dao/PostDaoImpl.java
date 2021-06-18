package com.hannahj.noticeBoard.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hannahj.noticeBoard.domain.Post;

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
		query = String.format(
				"insert into notice_board values(%s, null, '%s', '%s', '%s', now(), '%s')",
				notice.getBoardIdx(), notice.getUserName(), notice.getPw(), notice.getTitle(), notice.getContent());
		stmt.execute(query);
		stmt.close();
		conn.close();
	}

//	read all
	@Override
	public List<Post> list() throws SQLException {
		query = String.format(
				"select * "
				+ "from %s;", BoardInfo.NOTICE.columnName
				);
		rset = stmt.executeQuery(query);
		
		List<Post> board = new ArrayList<Post>();
		while(rset.next()) {
			Post boardList = new Post(
					rset.getInt(PostColumns.BOARD_IDX.order),
					rset.getInt(PostColumns.IDX.order), 
					rset.getString(PostColumns.USER_NAME.order), 
					rset.getString(PostColumns.PW.order), 
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
	public List<Post> list(int start, int sizer) throws SQLException {
		query = String.format(
				"select * "
						+ "from %s "
						+ "limit, %s, %s;", 
						BoardInfo.NOTICE.columnName, start, sizer
				);
		rset = stmt.executeQuery(query);
		
		List<Post> boards = new ArrayList<Post>();
		while(rset.next()) {
			Post boardList = new Post(
					rset.getInt(PostColumns.BOARD_IDX.order),
					rset.getInt(PostColumns.IDX.order), 
					rset.getString(PostColumns.USER_NAME.order), 
					rset.getString(PostColumns.PW.order), 
					rset.getString(PostColumns.TITLE.order),
					rset.getDate(PostColumns.DATETIME.order),
					rset.getString(PostColumns.CONTENT.order),
					rset.getInt(PostColumns.PARENT_IDX.order)
			); 
			boards.add(boardList);
		}
		return boards;
	}

//	read single data
	@Override
	public Post read(Post notice, String pw) throws SQLException {
		if (!isCorrect(notice.getIdx(), pw)) {
			return null;
		}
//		query = String.format(
//				"select "
//				+ "idx, user_name, pw, title, `date`, content "
//				+ "from notice_board "
//				+ "where idx=%d;", notice.getIdx());
//		rset = stmt.executeQuery(query);
//		
//		List<Post> boards = new ArrayList<Post>();
//		while(rset.next()) {
//			Post boardList = new Post(
//					rset.getInt(PostColumns.getOrder(PostColumns.IDX)), 
//					rset.getString(PostColumns.getOrder(PostColumns.USER_NAME)), 
//					rset.getString(PostColumns.getOrder(PostColumns.PW)), 
//					rset.getString(PostColumns.getOrder(PostColumns.TITLE)), 
//					rset.getDate(PostColumns.getOrder(PostColumns.DATETIME)), 
//					rset.getString(PostColumns.getOrder(PostColumns.CONTENT)),
//					rset.getInt(PostColumns.getOrder(PostColumns.PARENT_IDX)),
////					rset.getString(PostColumns.getOrder(PostColumns.COMMENT)),
//					rset.getInt(PostColumns.getOrder(PostColumns.BOARD_IDX))
//			);
//			boards.add(boardList);
//		}
//		return boards.get(0);
		
		return notice;
	}

	@Override
	public boolean update(Post notice, String pw) throws SQLException {
		if (!isCorrect(notice.getIdx(), pw)) {
			return false;
		}
		query = String.format(
				"update	notice_board "
				+ "set "
					  + "user_name = '%s', "
					  + "title = '%s', "
					  + "`date` = now(), "
					  + "content = %s"
				+ "where idx=%d and board_idx=%d;",
				notice.getUserName(), 
				notice.getTitle(), 
				notice.getContent(), 
				notice.getIdx(), notice.getBoardIdx());
		stmt.execute(query);
		stmt.close();
		conn.close();
		return true;
	}

	@Override
	public boolean delete(Post notice, String pw) throws SQLException {
		if (!isCorrect(notice.getIdx(), pw)) {
			return false;
		}
		query = String.format(
				"delete from notice_board "
				+ "where idx=%d and board_idx=%d;",
				notice.getIdx(), notice.getBoardIdx());
		stmt.execute(query);
		stmt.close();
		conn.close();
		return true;
	}

	@Override
	public boolean isCorrect(int idx, String pw) throws SQLException {
		query = String.format(
				"select pw from notice_board "
				+ "where idx=%d and board_idx=%d;"
				, idx, BoardInfo.NOTICE.idx);
		rset = stmt.executeQuery(query);
		rset.last();
		boolean validPw = (rset.getString(1) == pw ? true : false);
		return validPw;
	}
}
