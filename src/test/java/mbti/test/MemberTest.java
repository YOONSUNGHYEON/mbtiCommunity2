package mbti.test;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mbti.entity.Member;
import mbti.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberTest {
	@Autowired
	MemberRepository memberRepository;

	@Test
	public void findById() {
		Member member = memberRepository.findById("");
		System.out.println(member.toString());
	}

}
