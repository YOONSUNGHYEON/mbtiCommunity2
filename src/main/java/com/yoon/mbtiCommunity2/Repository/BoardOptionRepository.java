package com.yoon.mbtiCommunity2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yoon.mbtiCommunity2.Entity.BoardOption;

@Repository
public interface BoardOptionRepository extends JpaRepository<BoardOption,Integer> {

	BoardOption findBySeq(int boardOptionId);
	BoardOption findNameBySeq(int boardOptionSeq);
}
