package mbti.service;


import mbti.dto.MemberDTO;


public interface RecommendService {
	int findCheckByMemberSeqAndBoardSeq(int boardSeq, int memberSeq);
	int countByboardSeq(int boardSeq);
	boolean recommend(int boardSeq, MemberDTO memberDTO);
}