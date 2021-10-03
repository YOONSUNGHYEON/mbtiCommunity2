package com.yoon.mbtiCommunity2.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yoon.mbtiCommunity2.DTO.Pagination;
import com.yoon.mbtiCommunity2.Entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

	@Override
	Board save(Board board);

	Board findBySeq(int boardSeq);

	@Override
	void delete(Board entity);

	List<Board> findByBoardOptionSeqOrderBySeqDesc(int boardSeq);

	Board findMemberSeqBySeq(int boardSeq);

	int countByboardOptionSeq(int boardOptionSeq);

	Page<Board> findByBoardOptionSeq(int departmentId, Pageable pageable);

	public default List<Board> findByBoardOptionSeq(Pagination pagination, int boardOptionSeq) {
		Page<Board> page = this.findByBoardOptionSeq(boardOptionSeq,
				PageRequest.of(pagination.getCurrentPage()-1, pagination.getBlockCount(), Sort.Direction.DESC, "seq"));
		pagination.setRowCount(((int) page.getTotalElements()));
		return page.getContent();
	}
}
