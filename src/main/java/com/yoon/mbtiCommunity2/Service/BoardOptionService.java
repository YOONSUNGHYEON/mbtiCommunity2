package com.yoon.mbtiCommunity2.Service;

import java.util.List;

import com.yoon.mbtiCommunity2.DTO.BoardOptionDTO;

public interface BoardOptionService {

	// 게시판 옵션 리스트 모두 가져오기
	List<BoardOptionDTO> findAll();

	BoardOptionDTO findNameBySeq(int boardOptionId);



}