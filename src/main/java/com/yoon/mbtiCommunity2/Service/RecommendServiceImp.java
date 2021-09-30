package com.yoon.mbtiCommunity2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoon.mbtiCommunity2.DTO.BoardDTO;
import com.yoon.mbtiCommunity2.DTO.MemberDTO;
import com.yoon.mbtiCommunity2.DTO.RecommendDTO;
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
	public boolean recommend(int boardSeq, Member member) {
		// TODO Auto-generated method stub
			Recommend recommend =  recommendRepository.findByBoardSeqAndMemberSeq(boardSeq, member.getSeq());
			RecommendDTO recommendDTO = new RecommendDTO();

	        if(recommend == null) {
				recommendDTO.setBoardDTO(new BoardDTO(boardRepository.findBySeq(boardSeq)));
				recommendDTO.setMemberDTO(new MemberDTO(member));
				recommendDTO.setCheck(1);
	        	recommendRepository.save(new Recommend(recommendDTO));
	            return true;
	        }
	        if(recommend.getCheck()==0) {
	        	recommendRepository.upadr
	            $this->oRecommendDAO->update(1, $aRecommend['nRecommendSeq']);
	            return true;
	        }
	        else {
	            $this->oRecommendDAO->update(0, $aRecommend['nRecommendSeq']);
	            return false;
	        }
	}



}
