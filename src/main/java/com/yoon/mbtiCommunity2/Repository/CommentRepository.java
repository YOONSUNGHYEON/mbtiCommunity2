package com.yoon.mbtiCommunity2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yoon.mbtiCommunity2.DTO.CommentDTO;
import com.yoon.mbtiCommunity2.Entity.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

	boolean save(CommentDTO commentDTO);

	List<Comment> findByBoardSeqOrderBySeqDesc(int boardSeq);

	Comment findMemberIdBySeq(int commentSeq);

	int countByboardSeq(int boardSeq);

}
