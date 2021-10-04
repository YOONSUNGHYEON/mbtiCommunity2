package mbti.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mbti.dto.MemberDTO;
import mbti.entity.Member;
import mbti.repository.MbtiOptionRepository;
import mbti.repository.MemberRepository;

@Service
@Transactional
public class MemberServiceImp implements MemberService {

	@Autowired
	MemberRepository memberRepository;
	@Autowired
	MbtiOptionRepository mbtiOptionRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public MemberDTO login(MemberDTO memberDTO) {
		Member member = memberRepository.findById(memberDTO.getId());
		if (!passwordEncoder.matches(memberDTO.getPassword(), member.getPassword()) && member == null) {
			return null;
		}
		return new MemberDTO(member);
	}

	@Override
	public boolean register(String id, String password, String mbtiOption) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		// 중복 검사
		String encodedPassword = passwordEncoder.encode(password);
		Member member = new Member(id, encodedPassword, format.format(time),
				mbtiOptionRepository.getById(Integer.parseInt(mbtiOption)));
		if (memberRepository.findById(id) == null) {
			memberRepository.save(member);
			return true;
		}

		return false;
	}

	@Override
	public int findMbtiOptionSeqById(String memberId) {
		Member member = memberRepository.findMbtiOptionSeqById(memberId);
		return member.getMbtiOption().getSeq();
	}

}
