package com.yoon.mbtiCommunity2.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoon.mbtiCommunity2.DTO.CommentDTO;

@Service
@Transactional
public class CommentServiceImp implements CommentService{

	@Override
	public int save(CommentDTO commetDTO, int boardSeq) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBySeq(int commentSeq) {
		// TODO Auto-generated method stub
		return 0;
	}

}
