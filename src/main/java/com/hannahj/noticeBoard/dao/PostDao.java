package com.hannahj.noticeBoard.dao;

import java.sql.SQLException;
import java.util.List;

import com.hannahj.noticeBoard.domain.Post;

// CRUD
public interface PostDao {
	void write(Post notice) throws SQLException;
	List<Post> list() throws SQLException;
	List<Post> list(int page, int sizer) throws SQLException;
//	NoticeBoard read(NoticeBoard notice) throws SQLException;
	Post read(Post notice, String pw) throws SQLException;
	boolean update(Post notice, String pw) throws SQLException;
	boolean delete(Post notice, String pw) throws SQLException;
	boolean isCorrect(int idx, String pw) throws SQLException;
}
