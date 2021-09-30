package com.yoon.mbtiCommunity2.Service;

import com.yoon.mbtiCommunity2.DTO.CommentDTO;

public interface CommentService {

	int save(CommentDTO commetDTO, int boardSeq);

	int deleteBySeq(int commentSeq);

}