package com.yoon.mbtiCommunity2.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yoon.mbtiCommunity2.DTO.MemberDTO;

import lombok.Getter;


@Entity(name = "Member")
@Table(name = "tMemberList")
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nMemberSeq", nullable = false)
    private int seq;

    @Column(name = "sID", nullable = false, unique = true)
    private String id;

    @Column(name = "sPassword", nullable = false)
    private String password;

    @Column(name = "dtJoinDate", nullable = false)
    private String joinDate ;

    @ManyToOne
    @JoinColumn(name = "nMbtiOptionSeq", nullable = false)
    private MbtiOption mbtiOption;

    @Column(name = "nAdmin", nullable = true)
    private int admin;

    public Member() { }


	public Member(MemberDTO memberDTO) {
		this.seq = memberDTO.getSeq();
		this.id = memberDTO.getId();
		this.password = memberDTO.getPassword();
		this.joinDate = memberDTO.getJoinDate();
		this.mbtiOption = memberDTO.getMbtiOption();
		this.admin = memberDTO.getAdmin();
	}


	public Member(String id, String password, String joinDate, MbtiOption mbtiOption) {
		this.id = id;
		this.password = password;
		this.joinDate =joinDate;
		this.mbtiOption = mbtiOption;

	}



}
