package mbti.restapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mbti.dto.CommentDTO;
import mbti.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentAPI {

	@Autowired
	CommentService commentService;


	@GetMapping("comments/{boardSeq}")
	public List<CommentDTO> findListByBoardSeq(@PathVariable("boardSeq") int boardSeq) {
		List<CommentDTO> commentDTOList = commentService.findListByBoardSeq(boardSeq);

		return commentDTOList;
	}

	@DeleteMapping("comment/{commentSeq}")
	public boolean deleteBySeq(@PathVariable("commentSeq") int commentSeq,  HttpSession session) {
		commentService.deleteBySeq(commentSeq);
		return true;

	}

	@PostMapping("/comment/{boardSeq}")
	public Map<String, String> save(@ModelAttribute CommentDTO commentDTO, @PathVariable("boardSeq") int boardSeq, HttpSession session) {
		Map<String, String> response = new HashMap();
		if(commentDTO.getContent().isEmpty()) {
			response.put("message", "댓글을 입력해 주세요.");
			response.put("code", "400");
		} else if (session.getAttribute("member") == null) {
			response.put("message", "로그인부터 해주세요.");
			response.put("code", "400");
		}
		else {
			boolean commentResult = commentService.save(commentDTO, boardSeq, session.getAttribute("memberId").toString());
			if(commentResult) {
				response.put("message", "댓글 등록 성공");
				response.put("code", "200");
			}else {
				response.put("message", "서버 상의 문제로 댓글 등록 실패했습니다.");
				response.put("code", "400");
			}
		}
		return response;
	}
}
