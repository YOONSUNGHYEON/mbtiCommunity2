package com.yoon.mbtiCommunity2.Service;

import com.yoon.mbtiCommunity2.DTO.MemberDTO;

public interface MemberService {

	boolean register(String id, String password, String mbtiOption);

    MemberDTO login(MemberDTO memberDTO);

    int findMbtiOptionSeqById(String memberSeq);


}