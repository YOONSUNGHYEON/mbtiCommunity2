package com.yoon.mbtiCommunity2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yoon.mbtiCommunity2.Entity.Recommend;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend,Integer>{


	Recommend findByBoardSeqAndMemberSeq(int boardSeq, int memberSeq);
	int countByboardSeq(int boardSeq);
}
