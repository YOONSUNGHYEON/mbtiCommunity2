package mbti.service;

import java.util.List;

import mbti.dto.BoardDTO;
import mbti.dto.Pagination;

public interface BoardService {

	int save(BoardDTO boardDTO, int boardOptionId);

	BoardDTO findBySeq(int boardSeq);

	List<BoardDTO> findListByBoardOptionSeq(Pagination pagination, int boardOptionSeq);

	int findMemberSeqByBoardSeq(int boardSeq);

	boolean update(BoardDTO boardDTO, int boardOptionSeq, int boardSeq);

	int countListByBoardOptionSeq(int boardOptionSeq);
}