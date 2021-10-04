package com.yoon.mbtiCommunity2.Service;

import com.yoon.mbtiCommunity2.Entity.Member;

public interface RecommendService {
	int findCheckByMemberSeqAndBoardSeq(int boardSeq, int memberSeq);
	void recommend(int boardSeq, Member member);
	int countByboardSeq(int boardSeq);
}