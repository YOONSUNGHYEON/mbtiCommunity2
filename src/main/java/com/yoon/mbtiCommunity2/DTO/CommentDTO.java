package com.yoon.mbtiCommunity2.DTO;
import com.yoon.mbtiCommunity2.Entity.Comment;

import lombok.Data;

@Data
public class CommentDTO {

	private int seq;
	private BoardDTO boardDTO;
	private MemberDTO memberDTO;
	private String content;
    private String createDate ;

    public CommentDTO() {

    }

    public CommentDTO(Comment comment) {
    	this.boardDTO = new BoardDTO(comment.getBoard());
		this.memberDTO = new MemberDTO(comment.getMember());
		this.content = comment.getContent();
		this.createDate = comment.getCreateDate();
		this.seq = comment.getSeq();

	}
}
