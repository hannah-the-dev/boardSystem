package com.hannahj.noticeBoard.domain;

public class Board {
	private int boardIdx;
	private String title;
	

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getIdx() {
		return boardIdx;
	}
	public Board() {
	}
	public Board(int idx, String title) {
		super();
		this.boardIdx = idx;
		this.title = title;
	}
}

