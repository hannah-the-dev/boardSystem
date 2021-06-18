package com.hannahj.noticeBoard.service;

import java.sql.SQLException;
import java.util.List;

import com.hannahj.noticeBoard.dao.PostDaoImpl;
import com.hannahj.noticeBoard.domain.Post;

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
	public List<Post> list() throws SQLException {
		return dao.list();
	}
	
//	read all
	@Override
	public List<Post> list(int start, int sizer) throws SQLException {		
		return dao.list(start, sizer);
	}

//	read single data
	@Override
	public Post read(Post notice, String pw) throws SQLException {
		return dao.read(notice, pw);
	}

	@Override
	public void update(Post notice, String pw) throws SQLException {
		dao.update(notice, pw);
	}

	@Override
	public void delete(Post notice, String pw) throws SQLException {
		dao.delete(notice, pw);
	}

}
