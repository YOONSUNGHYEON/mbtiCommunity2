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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mbti.dto.BoardDTO;
import mbti.dto.MemberDTO;
import mbti.dto.Pagination;
import mbti.service.BoardService;

@RestController
@RequestMapping("/api")
public class BoardAPI {
	//게시판 목록

	@Autowired
	BoardService boardService;

	//게시물
	@GetMapping("board/{seq}")
	public BoardDTO findById(@PathVariable("seq") int boardSeq) {
		return boardService.findBySeq(boardSeq);
	}

	@GetMapping("boards/{boardOptionSeq}")
	public Map<String, Object> findListByBoardOptionId(@PathVariable("boardOptionSeq") int boardOptionSeq, @RequestParam(value="page") int page, HttpSession session) {
		Map<String, Object> response = new HashMap();
		int rowCount =boardService.countListByBoardOptionSeq(boardOptionSeq);
		Pagination pagination = new Pagination(rowCount, page);
		List<BoardDTO> boardDTOList = boardService.findListByBoardOptionSeq(pagination, boardOptionSeq);
		response.put("boardList", boardDTOList);
		response.put("pagination", pagination);
		if(session.getAttribute("adminId")!=null) {
			response.put("admin", true);
		}else {
			response.put("admin", false);
		}
		return response;
	}

	@DeleteMapping("board/{boardSeq}")
	public boolean delete(@PathVariable(name = "boardSeq") int boardSeq, HttpSession session){
		if (session.getAttribute("member") == null) {
			return false;
		}
		int writerSeq = boardService.findMemberSeqByBoardSeq(boardSeq);
		if (writerSeq!=Integer.parseInt(session.getAttribute("memberSeq").toString())) {
			return false;
		}
		boardService.delete(boardSeq);
		return true;
	}

	@PostMapping("/boards/{boardOptionSeq}/post/{boardSeq}")
	public Map<String, String> update(@ModelAttribute BoardDTO boardDTO, @PathVariable("boardOptionSeq") int boardOptionSeq, @PathVariable("boardSeq") int boardSeq,
			HttpSession session) {
		Map<String, String> response = new HashMap();
		boardDTO.setMemberDTO((MemberDTO) session.getAttribute("member"));
		boolean updateResult = boardService.update(boardDTO, boardOptionSeq, boardSeq);
		if(!updateResult) {
			response.put("message", "서버상 문제로 수정 실패했습니다.");
			response.put("code", "400");
		}else {
			response.put("message", "수정 성공");
			response.put("code", "200");
		}
		return response;
	}

}
