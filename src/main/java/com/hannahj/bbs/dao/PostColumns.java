package com.hannahj.bbs.dao;

public enum PostColumns {
	BOARD_IDX(1, "게시판"),
	IDX(2, "번호"), 
	USER_NAME(3, "작성자"), 
//	PW(4,"비밀번호"), 
	TITLE(4, "글 제목"), 
	DATETIME(5, "작성일"), 
	CONTENT(6, "내용"),
	PARENT_IDX(7, "원글");
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
