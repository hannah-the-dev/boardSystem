package com.hannahj.bbs.service;

import java.sql.SQLException;
import java.util.List;

import com.hannahj.bbs.dao.PostDaoImpl;
import com.hannahj.bbs.domain.Post;

public class PostServiceImpl implements PostService {	
//	create
	PostDaoImpl dao = new PostDaoImpl();
	public PostServiceImpl() throws ClassNotFoundException, SQLException {
	}
	
	@Override
	public void write(Post notice) throws SQLException {
		dao.write(notice);
	}

//	read all
	@Override
	public List<Post> getList(int boardIdx, int idx) throws SQLException {
		return dao.getList(boardIdx, idx);
	}
	
//	read single data
	@Override
	public Post read(int boardIdx, int idx) throws SQLException {
		
		Post post = dao.read(boardIdx, idx);
		List<Post> comments = dao.getComments(boardIdx, idx);
		if (!comments.isEmpty()) {
			post.setComment(comments);
		}
		System.out.println(post.toString());
		return post;
	}

	@Override
	public void update(Post post) throws SQLException {
		dao.update(post);
	}

	@Override
	public void delete(int boardIdx, int idx) throws SQLException {
		dao.delete(boardIdx, idx);
	}

	@Override
	public int getNextId() throws SQLException {
		return dao.getMaxID() + 1;
	}
	
	@Override
	public String getClean(String string) {
		String clean = 
				string.replaceAll("<","&lt;")
				.replaceAll(">","&gt;")
				.replaceAll("\"","&quot;")
				.replaceAll("\'","&#39;")
				.replaceAll("\r\n", "<br>");
		return clean;
	}
	@Override
	public List<Post> search(String string, int page)  throws SQLException {
		String regexp = getRegExp(string);
		int start = getSearchStartIdx(page, regexp);
		return dao.search(regexp, start);
	}
	
	@Override
	public int getStartIdx(int boardIdx, int page) throws SQLException {
		int postCount = getPostCount(boardIdx);
		page = (page > (postCount / PostDaoImpl.BOARDSIZE) +1) ? 
				(postCount / PostDaoImpl.BOARDSIZE) +1 : page;
		page = (page <= 0) ? 1 : page;
		int start = (page-1) * PostDaoImpl.BOARDSIZE;
		return start;
	}
	@Override
	public int getSearchStartIdx(int page, String regexp) throws SQLException {
		int searchCount = dao.getSearchCount(regexp);
		page = (page > (searchCount / PostDaoImpl.BOARDSIZE) +1 ) ? 
				(searchCount / PostDaoImpl.BOARDSIZE) +1 : page;
		page = (page <= 0) ? 1 : page;
		int start = (page-1) * PostDaoImpl.BOARDSIZE;
		return start;
	}
	@Override
	public int getPostCount(int boardIdx) throws SQLException {
		return dao.getPostCount(boardIdx);
	}
	@Override
	public int getSearchCount(String regexp) throws SQLException {
		return dao.getSearchCount(regexp);
	}
	@Override
	public String getRegExp (String string) {
		String[] expArr = string.split(" ");
		String regexp = String.join("|", expArr);
		return regexp;
	}
}
