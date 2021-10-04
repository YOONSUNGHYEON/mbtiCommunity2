package mbti.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mbti.dto.BoardDTO;
import mbti.dto.MemberDTO;
import mbti.service.BoardOptionService;
import mbti.service.BoardService;
import mbti.service.MemberService;

@Controller
public class BoardController {

	@Autowired
	BoardOptionService boardOptionService;

	@Autowired
	BoardService boardService;

	@Autowired
	MemberService memberService;

	@GetMapping("/boards/{id}")
	public String board(@PathVariable("id") String id) {
		return "board";
	}

	// 글 작성 페이지
	@GetMapping("/boards/{boardOptionSeq}/post")
	public String createBoard(@PathVariable("boardOptionSeq") int boardOptionSeq, HttpSession session, Model model) {
		if (session.getAttribute("member") == null) { // 로그인을 안했다면 login 페이지로 리다이렉트
			return "redirect:/login";
		} else if (memberService.findMbtiOptionSeqById((String) session.getAttribute("memberId")) != boardOptionSeq) {
			model.addAttribute("msg", "자신의 mbti 게시판에서 글을 작성해 주세요.");
			return "msg";
		}
		return "create";
	}

	// 글 작성 post
	@PostMapping("/boards/{boardOptionSeq}/post")
	public String enrollBoard(@PathVariable("boardOptionSeq") int boardOptionSeq, @ModelAttribute BoardDTO boardDTO,
			HttpSession session) {
		if (session.getAttribute("member") == null) {
			return "redirect:/login";
		}
		boardDTO.setMemberDTO((MemberDTO) session.getAttribute("member"));
		int boardSeq = boardService.save(boardDTO, boardOptionSeq);
		return "redirect:/board/"+boardOptionSeq+"/"+boardSeq + "?page=1";
	}

	// 글 수정 페이지
	@GetMapping("/boards/{boardOptionSeq}/post/{boardSeq}")
	public String edit(@PathVariable("boardOptionSeq") int boardOptionSeq, @PathVariable("boardSeq") int boardSeq,
			HttpSession session) {
		if (session.getAttribute("member") == null) {
			return "redirect:/login";
		}
		int writerSeq = boardService.findMemberSeqByBoardSeq(boardSeq);
		if (writerSeq!=Integer.parseInt(session.getAttribute("memberSeq").toString())) {
			return "redirect:/board/"+boardOptionSeq+"/"+boardSeq;
		}
		return "edit";
	}

	// 글 상세 페이지
	@GetMapping("/board/{boardOptionId}/{boardId}")
	public String view(HttpSession session, @RequestParam(value="page") int page) {
		return "view";
	}

}
