package com.yoon.mbtiCommunity2.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoon.mbtiCommunity2.DTO.MemberDTO;
import com.yoon.mbtiCommunity2.Entity.Member;
import com.yoon.mbtiCommunity2.Repository.MbtiOptionRepository;
import com.yoon.mbtiCommunity2.Repository.MemberRepository;

@Service
@Transactional
public class MemberServiceImp implements MemberService{

	@Autowired
	MemberRepository memberRepository;
	@Autowired
	MbtiOptionRepository mbtiOptionRepository;


	@Override
	public MemberDTO login(MemberDTO memberDTO) {
		Member member = memberRepository.findByIdAndPassword(memberDTO.getId(), memberDTO.getPassword());
		if(member!=null) {
			return new MemberDTO(member);
		}
		return null;
	}

	@Override
	public boolean register(String id, String password, String mbtiOption) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		//중복 검사
		Member member = new Member(id, password,format.format(time), mbtiOptionRepository.getById(Integer.parseInt(mbtiOption)));
		if (memberRepository.findById(id)==null) {
			memberRepository.save(member);
			return true;
		}

		return false;
	}


}
