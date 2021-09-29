package com.yoon.mbtiCommunity2.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;

@Entity(name = "Comment")
@Table(name = "tCommentList")
@Getter
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nCommentSeq", nullable = false)
	private int seq;

	@ManyToOne
	@JoinColumn(name = "nBoardSeq", nullable = false)
	private Board board;

	@ManyToOne
	@JoinColumn(name = "nMemberSeq", nullable = false)
	private Member member;

	@Column(name = "sContent", nullable = false)
	private String content;

    @Column(name = "dtCreateDate", nullable = false)
    private String createDate ;
}