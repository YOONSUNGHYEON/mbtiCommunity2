package mbti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mbti.entity.Recommend;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend,Integer>{
	Recommend findByBoardSeqAndMemberSeq(int boardSeq, int memberSeq);
	int countByboardSeq(int boardSeq);
}
