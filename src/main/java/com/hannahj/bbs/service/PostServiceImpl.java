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
	public List<Post> getList(int idx) throws SQLException {
		return dao.getList(idx);
	}
	
//	read all
	@Override
	public List<Post> getList(int idx, int start, int sizer) throws SQLException {		
		return dao.getList(idx, start, sizer);
	}

//	read single data
	@Override
	public Post read(int boardIdx, int idx) throws SQLException {
		
		Post post = dao.read(boardIdx, idx);
		List<Post> comments = dao.getComments(boardIdx, idx);
		post.setComment(comments);
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
	
	public String getClean(String string) {
		String clean = 
				string.replaceAll("\r\n", "<br>")
				.replaceAll("<","&lt;")
				.replaceAll(">","&gt;")
				.replaceAll("\"","&quot;")
				.replaceAll("\'","&#39;");
		return clean;
	}
	public List<Post> search(String string)  throws SQLException {
		return dao.search(string);
	}
}
