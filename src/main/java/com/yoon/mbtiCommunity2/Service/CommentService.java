package com.yoon.mbtiCommunity2.Service;

import java.util.List;

import com.yoon.mbtiCommunity2.DTO.CommentDTO;

public interface CommentService {


	void deleteBySeq(int commentSeq);

	boolean save(CommentDTO commentDTO, int boardSeq, String memberId);
	List<CommentDTO> findListByBoardSeq(int boardSeq);
	String findMemberIdBySeq(int commentSeq);
	int countByboardSeq(int boardSeq);
}