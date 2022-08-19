package com.varxyz.wgt.board.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
	private int number;
	private String title;
	private String content;
	private Date regDate;
	private String imgname;
	private int view;
	
	public Board() {
		super();
	}
	
	public String toString() {
		return "번호 : " + number +  "제목 : " + title 
				+ "내용 : " + content + "날짜 : " + regDate;
	}

}
