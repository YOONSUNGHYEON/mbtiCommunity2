package mbti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mbti.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {

	Member findById(String id);

	Member findByIdAndPassword(String id, String password);
	Member findMbtiOptionSeqById(String memberSeq);
}
