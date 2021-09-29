package com.yoon.mbtiCommunity2.DTO;

import com.yoon.mbtiCommunity2.Entity.MbtiOption;
import com.yoon.mbtiCommunity2.Entity.Member;

import lombok.Data;

@Data
public class MemberDTO {

	private int seq;
	private String id;
	private String password;
	private String joinDate;
	private MbtiOption mbtiOption;

	public MemberDTO() {

	}

	public MemberDTO(Member member) {
		this.seq = member.getSeq();
		this.id = member.getId();
		this.mbtiOption = member.getMbtiOption();
	}
}
