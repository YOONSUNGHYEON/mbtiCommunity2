package mbti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mbti.dto.BoardDTO;
import mbti.dto.MemberDTO;
import mbti.dto.RecommendDTO;
import mbti.entity.Recommend;
import mbti.repository.BoardRepository;
import mbti.repository.RecommendRepository;

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
	public boolean recommend(int boardSeq, MemberDTO memberDTO) {
		Recommend recommend =  recommendRepository.findByBoardSeqAndMemberSeq(boardSeq, memberDTO.getSeq());

		RecommendDTO recommendDTO = new RecommendDTO();
		if(recommend == null) {
			recommendDTO.setBoardDTO(new BoardDTO(boardRepository.findBySeq(boardSeq)));
			recommendDTO.setMemberDTO(memberDTO);
			recommendDTO.setCheck(1);
			recommendRepository.save(new Recommend(recommendDTO));
			return true;
		}else {
			System.out.println(recommend.getSeq());
			recommendDTO = new RecommendDTO(recommend);
			if(recommend.getCheck()==0) {
				recommendDTO.setCheck(1);
				recommendRepository.update(recommendDTO.getCheck(), recommendDTO.getSeq());
				return true;
			} else {
				recommendDTO.setCheck(0);
				recommendRepository.update(recommendDTO.getCheck(), recommendDTO.getSeq());
				return false;
			}
		}
	}

	@Override
	public int countByboardSeq(int boardSeq) {
		return recommendRepository.countByboardSeq(boardSeq);
	}




}
