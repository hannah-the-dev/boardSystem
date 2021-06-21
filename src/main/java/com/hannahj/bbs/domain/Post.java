package com.hannahj.bbs.domain;

import java.sql.Date;
import java.util.List;

public class Post {
	private int boardIdx;  //where it is nested
	private int idx;
	private String userName;
	private String pw;
	private String title;
	private Date dateTime;
	private String content;
	private int parentIdx;
	private List<Post> comment;
	
	public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	public List<Post> getComment() {
		return comment;
	}
	public void setComment(List<Post> comment) {
		this.comment = comment;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String user_name) {
		this.userName = user_name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getIdx() {
		return idx;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public Integer getParentIdx() {
		return parentIdx;
	}
	public void setParentIdx(int parentIdx) {
		this.parentIdx = parentIdx;
	}	



@Override
	public String toString() {
		return "Post [boardIdx=" + boardIdx + ", idx=" + idx + ", userName=" + userName + ", pw=" + pw + ", title="
				+ title + ", dateTime=" + dateTime + ", content=" + content + ", parentIdx=" + parentIdx + ", comment="
				+ comment + "]";
	}
	//	public Post(String user_name, String pw, String title, String content) {
//		this.userName = user_name;
//		this.pw = pw;
//		this.title = title;
//		this.content = content;
//	}
	// 게시글 작성 시작
	public Post(int boardIdx, String userName, String title,  String content) {
		super();
		this.boardIdx = boardIdx;
		
		this.userName = userName;
		this.pw = null;
		this.title = title;
		this.dateTime = null;
		this.content = content;
		
		this.comment = null;
	}
	public Post(int boardIdx, int idx, String userName, String title,  String content) {
		super();
		this.boardIdx = boardIdx;
		this.idx = idx;
		this.userName = userName;
		this.title = title;
		this.content = content;
	}
	// 게시글 받아오기
	public Post(int boardIdx, int idx, String userName, String title, Date dateTime,  
			String content) {
		super();
		this.boardIdx = boardIdx;
		this.idx = idx;
		this.userName = userName;
		this.pw = null;
		this.title = title;
		this.dateTime = dateTime;
		this.content = content;
		
		this.comment = null;
	}
	// 댓글 받아오기
	public Post(int boardIdx, int idx, String userName, String title, Date dateTime,  
			String content, int parentIdx) {
		this(boardIdx, idx, userName, title, dateTime, content);
		this.parentIdx = parentIdx;
	}
	
	// 댓글 작성
	public Post(int boardIdx, String userName, String title,  
			String content, int parentIdx) {
		this(boardIdx, userName, title, content);
		this.parentIdx = parentIdx;
	}
	
	public Post(int boardIdx, int idx, String userName, String title, Date dateTime, String content, int parentIdx,
			List<Post> comment) {
		this(boardIdx, idx, userName, title, dateTime, content, parentIdx);
		this.comment = comment;
	}
	public Post() {
	}
	
}

