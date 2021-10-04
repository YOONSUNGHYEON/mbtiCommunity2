package com.yoon.mbtiCommunity2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoon.mbtiCommunity2.Entity.Member;
import com.yoon.mbtiCommunity2.Entity.Recommend;
import com.yoon.mbtiCommunity2.Repository.BoardRepository;
import com.yoon.mbtiCommunity2.Repository.RecommendRepository;

@Service
@Transactional
public class RecommendServiceImp implements RecommendService{

	@Autowired
	RecommendRepository recommendRepository;
	@Autowired
	BoardRepository boardRepository;


	@Override
	public int findCheckByMemberSeqAndBoardSeq(int boardSeq, int memberSeq) {
		// TODO Auto-generated method stub
		Recommend recommend = recommendRepository.findByBoardSeqAndMemberSeq(boardSeq, memberSeq);
		if(recommend != null) {
			return recommend.getCheck();
		}
		return 0;
	}

	@Override
	public void recommend(int boardSeq, Member member) {
		// TODO Auto-generated method stub


	}

	@Override
	public int countByboardSeq(int boardSeq) {
		return recommendRepository.countByboardSeq(boardSeq);
	}



}
