package com.yoon.mbtiCommunity2.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoon.mbtiCommunity2.DTO.BoardDTO;
import com.yoon.mbtiCommunity2.DTO.BoardOptionDTO;
import com.yoon.mbtiCommunity2.Entity.Board;
import com.yoon.mbtiCommunity2.Repository.BoardOptionRepository;
import com.yoon.mbtiCommunity2.Repository.BoardRepository;

@Service
@Transactional
public class BoardServiceImp implements BoardService{


	@Autowired
	BoardOptionRepository boardOptionRepository;

	@Autowired
	BoardRepository boardRepository;


	@Override
	public int save(BoardDTO boardDTO, int boardOptionId) {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		boardDTO.setBoardOptionDTO(new BoardOptionDTO(boardOptionRepository.findBySeq(boardOptionId)));
		boardDTO.setCreateDate(format.format(time));
		Board tempBoard = new Board(boardDTO);
		return boardRepository.save(tempBoard).getSeq();
	}


	@Override
	public BoardDTO findBySeq(int boardSeq) {
		BoardDTO boardDTO = new BoardDTO(boardRepository.findBySeq(boardSeq));
		return boardDTO;
	}


	@Override
	public List<BoardDTO> findListByBoardOptionSeq(int boardOptionSeq) {
		List<Board> boardList =  boardRepository.findByBoardOptionSeqOrderBySeqDesc(boardOptionSeq);
		List<BoardDTO> boardListDTO = new ArrayList<>();
		for(int i=0; i<boardList.size(); i++) {
			boardListDTO.add(new BoardDTO(boardList.get(i)));
		}
		return boardListDTO;
	}

}
