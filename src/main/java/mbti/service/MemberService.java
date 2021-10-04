package mbti.service;

import mbti.dto.MemberDTO;

public interface MemberService {

	boolean register(String id, String password, String mbtiOption);

    MemberDTO login(MemberDTO memberDTO);

    int findMbtiOptionSeqById(String memberSeq);


}