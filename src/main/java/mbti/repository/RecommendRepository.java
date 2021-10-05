package mbti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mbti.entity.Recommend;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend,Integer>{
	Recommend findByBoardSeqAndMemberSeq(int boardSeq, int memberSeq);
	int countByboardSeq(int boardSeq);
	@Transactional
	@Modifying
	@Query("UPDATE Recommend SET check = :check WHERE seq = :recommendSeq")
	void update(@Param("check") int check, @Param("recommendSeq") int recommendSeq);
	void deleteByBoardSeq(int boardSeq);
}
