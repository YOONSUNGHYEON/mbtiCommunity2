package mbti.service;

import mbti.entity.Member;

public interface RecommendService {
	int findCheckByMemberSeqAndBoardSeq(int boardSeq, int memberSeq);
	void recommend(int boardSeq, Member member);
	int countByboardSeq(int boardSeq);
}