package com.hannahj.noticeBoard.domain;

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
	public int getParentIdx() {
		return parentIdx;
	}
	public void setParentIdx(int parentIdx) {
		this.parentIdx = parentIdx;
	}	

	@Override
	public String toString() {
		return "Post [idx=" + idx + ", userName=" + userName + ", pw=" + pw + ", title=" + title + ", dateTime="
				+ dateTime + ", content=" + content + ", parent=" + parentIdx + ", comment=" + comment + ", boards="
				+ boardIdx + "]";
	}

//	public Post(String user_name, String pw, String title, String content) {
//		this.userName = user_name;
//		this.pw = pw;
//		this.title = title;
//		this.content = content;
//	}
	// 일반적인 게시글 작성 시작
	public Post(int boardIdx, int idx, String userName, String pw, String title, Date dateTime,  
			String content) {
		super();
		this.boardIdx = boardIdx;
		this.idx = idx;
		this.userName = userName;
		this.pw = pw;
		this.title = title;
		this.dateTime = dateTime;
		this.content = content;
		this.parentIdx = 0;
		this.comment = null;
	}
	// 댓글
	public Post(int boardIdx, int idx, String userName, String pw, String title, Date dateTime,  
			String content, int parentIdx) {
		this(boardIdx, idx, userName, pw, title, dateTime, content);
		this.parentIdx = parentIdx;
	}
	
	public Post(int boardIdx, int idx, String userName, String pw, String title, Date dateTime, String content, int parentIdx,
			List<Post> comment) {
		this(boardIdx, idx, userName, pw, title, dateTime, content, parentIdx);
		this.comment = comment;
	}
	public Post() {
	}
	
}

