package mbti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mbti.dto.CommentDTO;
import mbti.entity.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

	boolean save(CommentDTO commentDTO);

	List<Comment> findByBoardSeqOrderBySeqDesc(int boardSeq);

	Comment findMemberIdBySeq(int commentSeq);

	int countByboardSeq(int boardSeq);

}
