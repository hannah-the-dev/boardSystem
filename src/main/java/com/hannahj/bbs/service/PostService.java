package com.hannahj.bbs.service;

import java.sql.SQLException;
import java.util.List;

import com.hannahj.bbs.domain.Post;

// CRUD
public interface PostService {
	void write(Post notice) throws SQLException;
	List<Post> getList(int idx) throws SQLException;
	List<Post> getList(int idx, int page, int sizer) throws SQLException;
	Post read(int boardIdx, int idx) throws SQLException;
//	void update(NoticeBoard notice) throws SQLException;
	void delete(int boardIdx, int idx) throws SQLException;
	void update(Post notice) throws SQLException;
	int getNextId() throws SQLException;
}
