package com.hannahj.bbs.dao;

public enum BoardInfo {
	NOTICE(1, "notice_board", "공지사항");
	
	public final int idx;
	public final String columnName;
	public final String alias;
	
	private BoardInfo(int idx, String columnName, String alias) {
		this.idx = idx;
		this.columnName = columnName;
		this.alias = alias;
	}
	
	public static BoardInfo getBoardInfo(int idx) {
		BoardInfo board = null;
		for (BoardInfo b : values()) {
	    	if (b.idx == idx) {
	    		board = b;
	        } 
	    }
		return board;
	}
	
}