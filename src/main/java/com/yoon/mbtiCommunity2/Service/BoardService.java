package com.yoon.mbtiCommunity2.Service;

import java.util.List;

import com.yoon.mbtiCommunity2.DTO.BoardDTO;

public interface BoardService {

	int save(BoardDTO boardDTO, int boardOptionId);

	BoardDTO findBySeq(int boardSeq);

	List<BoardDTO> findListByBoardOptionSeq(int boardOptionSeq);

	int findMemberSeqByBoardSeq(int boardSeq);

	boolean update(BoardDTO boardDTO, int boardOptionSeq, int boardSeq);

}