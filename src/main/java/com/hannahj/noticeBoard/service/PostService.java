package com.hannahj.noticeBoard.service;

import java.sql.SQLException;
import java.util.List;

import com.hannahj.noticeBoard.domain.Post;

// CRUD
public interface PostService {
	void write(Post notice) throws SQLException;
	List<Post> list() throws SQLException;
	List<Post> list(int page, int sizer) throws SQLException;
	Post read(Post notice, String pw) throws SQLException;
//	void update(NoticeBoard notice) throws SQLException;
	void delete(Post notice, String pw) throws SQLException;
	void update(Post notice, String pw) throws SQLException;
}
