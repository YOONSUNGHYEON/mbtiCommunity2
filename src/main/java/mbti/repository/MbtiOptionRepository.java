package mbti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mbti.entity.MbtiOption;

@Repository
public interface MbtiOptionRepository extends JpaRepository<MbtiOption,Integer>{

}
