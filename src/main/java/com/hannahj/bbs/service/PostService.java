package com.hannahj.bbs.service;

import java.sql.SQLException;
import java.util.List;

import com.hannahj.bbs.domain.Post;

// CRUD
public interface PostService {
	void write(Post notice) throws SQLException;
	List<Post> getList(int boardIdx, int page) throws SQLException;
	Post read(int boardIdx, int idx) throws SQLException;
	void update(Post notice) throws SQLException;
	void delete(int boardIdx, int idx) throws SQLException;
	int getNextId() throws SQLException;
	public String getClean(String string);
	public List<Post> search(String string, int page) throws SQLException; 
	public int getStartIdx(int boardIdx, int page) throws SQLException;
	public int getSearchStartIdx(int page, String regexp) throws SQLException;
	public int getPostCount(int boardIdx) throws SQLException;
	public int getSearchCount(String regexp) throws SQLException;
	public String getRegExp (String string);
}
