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
import com.yoon.mbtiCommunity2.DTO.Pagination;
import com.yoon.mbtiCommunity2.Entity.Board;
import com.yoon.mbtiCommunity2.Repository.BoardOptionRepository;
import com.yoon.mbtiCommunity2.Repository.BoardRepository;

@Service
@Transactional
public class BoardServiceImp implements BoardService {

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
	public boolean update(BoardDTO boardDTO, int boardOptionSeq, int boardSeq) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		boardDTO.setBoardOptionDTO(new BoardOptionDTO(boardOptionRepository.findBySeq(boardOptionSeq)));
		boardDTO.setCreateDate(format.format(time));
		boardDTO.setSeq(boardSeq);
		Board tempBoard = new Board(boardDTO);
		if(boardRepository.save(tempBoard)==null) {
			return false;
		}
		return true;
	}
	@Override
	public BoardDTO findBySeq(int boardSeq) {
		BoardDTO boardDTO = new BoardDTO(boardRepository.findBySeq(boardSeq));
		return boardDTO;
	}

	@Override
	public List<BoardDTO> findListByBoardOptionSeq(Pagination pagination, int boardOptionSeq) {
		List<Board> boardList = boardRepository.findByBoardOptionSeq(pagination, boardOptionSeq);
		List<BoardDTO> boardListDTO = new ArrayList<>();
		for (int i = 0; i < boardList.size(); i++) {
			boardListDTO.add(new BoardDTO(boardList.get(i)));
		}
		return boardListDTO;
	}

	@Override
	public int findMemberSeqByBoardSeq(int boardSeq) {
		Board board = boardRepository.findMemberSeqBySeq(boardSeq);
		return board.getMember().getSeq();

	}
	@Override
	public int countListByBoardOptionSeq(int boardOptionSeq) {
		// TODO Auto-generated method stub
		return boardRepository.countByboardOptionSeq(boardOptionSeq);
	}



}
