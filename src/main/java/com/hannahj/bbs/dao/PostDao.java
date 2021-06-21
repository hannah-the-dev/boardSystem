package com.hannahj.bbs.dao;

import java.sql.SQLException;
import java.util.List;

import com.hannahj.bbs.domain.Post;

// CRUD
public interface PostDao {
	void write(Post notice) throws SQLException;
	List<Post> getList(int idx) throws SQLException;
	List<Post> getList(int idx, int page, int sizer) throws SQLException;
//	NoticeBoard read(NoticeBoard notice) throws SQLException;
	Post read(int boardIdx, int idx) throws SQLException;
	boolean update(Post notice) throws SQLException;
	boolean delete(int boardIdx, int idx) throws SQLException;
	boolean isCorrect(int idx, String pw) throws SQLException;
	public int getMaxID() throws SQLException;
}
