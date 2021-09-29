package com.yoon.mbtiCommunity2.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoon.mbtiCommunity2.DTO.BoardOptionDTO;
import com.yoon.mbtiCommunity2.Entity.BoardOption;
import com.yoon.mbtiCommunity2.Repository.BoardOptionRepository;

@Service
public class BoardOptionServiceImp implements BoardOptionService {

	@Autowired
	BoardOptionRepository boardOptionRepository;

	@Override
	public List<BoardOptionDTO> findAll() {
		// TODO Auto-generated method stub
		List<BoardOption> boardOptionList = boardOptionRepository.findAll();
		List<BoardOptionDTO> boardOptionDTOList = new ArrayList();
		for (int i = 0; i < boardOptionList.size(); i++) {
			boardOptionDTOList.add(new BoardOptionDTO(boardOptionList.get(i)));
		}
		return boardOptionDTOList;
	}

	@Override
	public BoardOptionDTO findNameBySeq(int boardOptionSeq) {
		BoardOption boardOption = boardOptionRepository.findBySeq(boardOptionSeq);
		return new BoardOptionDTO(boardOption);
	}



}
