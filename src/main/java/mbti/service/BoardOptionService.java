package mbti.service;

import java.util.List;

import mbti.dto.BoardOptionDTO;

public interface BoardOptionService {

	// 게시판 옵션 리스트 모두 가져오기
	List<BoardOptionDTO> findAll();

	BoardOptionDTO findNameBySeq(int boardOptionId);



}