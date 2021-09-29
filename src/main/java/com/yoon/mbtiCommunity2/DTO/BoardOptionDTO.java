package com.yoon.mbtiCommunity2.DTO;

import com.yoon.mbtiCommunity2.Entity.BoardOption;

import lombok.Data;

@Data
public class BoardOptionDTO {
	private int seq;
	private String name;

	public BoardOptionDTO(BoardOption boardOption) {
		// TODO Auto-generated constructor stub
		this.seq = boardOption.getSeq();
		this.name = boardOption.getName();
	}

}
