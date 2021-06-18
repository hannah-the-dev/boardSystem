package com.hannahj.noticeBoard.dao;

public enum PostColumns {
	BOARD_IDX(1, "게시판"),
	IDX(2, "번호"), 
	USER_NAME(3, "작성자"), 
	PW(4,"비밀번호"), 
	TITLE(5, "글 제목"), 
	DATETIME(6, "작성일"), 
	CONTENT(7, "내용"),
	PARENT_IDX(8, "원글");
	public final int order;
	public final String alias;
	
	private PostColumns(int order, String alias) {
		this.order = order;
		this.alias = alias;
		// TODO Auto-generated constructor stub
	}
	
	public static int getOrder(PostColumns item) {
		return item.order;
	}
	
	public static String getColumn(PostColumns item) {
		return item.alias;
	}
}
