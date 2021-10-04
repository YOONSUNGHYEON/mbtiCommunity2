package mbti.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mbti.dto.BoardDTO;
import mbti.dto.BoardOptionDTO;
import mbti.dto.Pagination;
import mbti.entity.Board;
import mbti.repository.BoardOptionRepository;
import mbti.repository.BoardRepository;
import mbti.repository.CommentRepository;
import mbti.repository.RecommendRepository;

@Service
@Transactional
public class BoardServiceImp implements BoardService {

	@Autowired
	BoardOptionRepository boardOptionRepository;
	@Autowired
	BoardRepository boardRepository;
	@Autowired
	RecommendRepository recommendRepository;
	@Autowired
	CommentRepository commentRepository;

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
			BoardDTO boardDTO = new BoardDTO(boardList.get(i));
			boardDTO.setCommentCount(commentRepository.countByboardSeq(boardDTO.getSeq()));
			boardDTO.setRecommendCount(recommendRepository.countByboardSeq(boardDTO.getSeq()));
			boardListDTO.add(boardDTO);
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
