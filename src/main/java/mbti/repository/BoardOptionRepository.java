package mbti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mbti.entity.BoardOption;

@Repository
public interface BoardOptionRepository extends JpaRepository<BoardOption,Integer> {
	BoardOption findBySeq(int boardOptionId);
	BoardOption findNameBySeq(int boardOptionSeq);
}
