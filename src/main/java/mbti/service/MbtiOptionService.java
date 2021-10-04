package mbti.service;

import java.util.List;

import mbti.dto.MbtiOptionDTO;

public interface MbtiOptionService {

	// 게시판 옵션 리스트 모두 가져오기
	List< MbtiOptionDTO> findAll();


}