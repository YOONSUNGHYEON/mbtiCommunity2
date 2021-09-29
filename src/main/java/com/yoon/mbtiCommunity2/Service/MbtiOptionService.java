package com.yoon.mbtiCommunity2.Service;

import java.util.List;

import com.yoon.mbtiCommunity2.DTO.MbtiOptionDTO;

public interface MbtiOptionService {

	// 게시판 옵션 리스트 모두 가져오기
	List< MbtiOptionDTO> findAll();


}