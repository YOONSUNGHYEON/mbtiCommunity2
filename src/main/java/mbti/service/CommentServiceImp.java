package mbti.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mbti.dto.BoardDTO;
import mbti.dto.CommentDTO;
import mbti.dto.MemberDTO;
import mbti.entity.Comment;
import mbti.repository.BoardRepository;
import mbti.repository.CommentRepository;
import mbti.repository.MemberRepository;

@Service
@Transactional
public class CommentServiceImp implements CommentService{

	@Autowired
	CommentRepository commentRepository;
	@Autowired
	BoardRepository boardRepository;
	@Autowired
	MemberRepository memberRepository;

	@Override
	public void deleteBySeq(int commentSeq) {
		// TODO Auto-generated method stub
		commentRepository.deleteById(commentSeq);
	}

	@Override
	public boolean save(CommentDTO commentDTO, int boardSeq, String memberId) {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		commentDTO.setCreateDate(format.format(time));
		commentDTO.setMemberDTO(new MemberDTO(memberRepository.findById(memberId)));
		commentDTO.setBoardDTO(new BoardDTO(boardRepository.findBySeq(boardSeq)));
		Comment comment = new Comment(commentDTO);
		if(commentRepository.save(comment)==null) {
			return false;
		}
		return true;
	}

	@Override
	public List<CommentDTO> findListByBoardSeq(int boardSeq) {
		List<Comment> commentList = commentRepository.findByBoardSeqOrderBySeqDesc(boardSeq);
		List<CommentDTO> commentListDTO = new ArrayList<>();
		for(int i=0; i<commentList.size(); i++) {
			commentListDTO.add(new CommentDTO(commentList.get(i)));
		}
		return commentListDTO;
	}

	@Override
	public String findMemberIdBySeq(int commentSeq) {
		Comment comment = commentRepository.findMemberIdBySeq(commentSeq);
		return comment.getMember().getId();
	}

	@Override
	public int countByboardSeq(int boardSeq) {
		return commentRepository.countByboardSeq(boardSeq);
	}

}
