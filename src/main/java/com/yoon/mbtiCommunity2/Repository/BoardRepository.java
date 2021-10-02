package com.yoon.mbtiCommunity2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yoon.mbtiCommunity2.Entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board,Integer>{

	@Override
	Board save(Board board);

	Board findBySeq(int boardSeq);

	@Override
	void delete(Board entity);

	List<Board> findByBoardOptionSeqOrderBySeqDesc(int boardSeq);

	Board findMemberSeqBySeq(int boardSeq);

}

