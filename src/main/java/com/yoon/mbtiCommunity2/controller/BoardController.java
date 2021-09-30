package com.yoon.mbtiCommunity2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.yoon.mbtiCommunity2.DTO.BoardDTO;
import com.yoon.mbtiCommunity2.DTO.MemberDTO;
import com.yoon.mbtiCommunity2.Service.BoardOptionService;
import com.yoon.mbtiCommunity2.Service.BoardService;
import com.yoon.mbtiCommunity2.Service.MemberService;



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
		if (session.getAttribute("member") == null) { //로그인을 안했다면 login 페이지로 리다이렉트
			return "redirect:/login";
		} else if( memberService.findMbtiOptionSeqById((String)session.getAttribute("memberId")) != boardOptionSeq) { //게시판 option과 나의 mbti 유형이 다를 경우
			model.addAttribute("msg", "자신의 mbti 게시판에서 글을 작성해 주세요.");
			return "msg";
		}

		return "create";
	}

	// 글 작성 post
	@PostMapping("/boards/{id}/post")
	public String enrollBoard(@PathVariable("id") int boardOptionId, @ModelAttribute BoardDTO boardDTO,
			HttpSession session) {
		if (session.getAttribute("member") == null) {
			return "redirect:/login";
		}
		boardDTO.setMemberDTO((MemberDTO) session.getAttribute("member"));
		int boardSeq = boardService.save(boardDTO, boardOptionId);
		return "redirect:/board/" + boardSeq;
	}

	// 글 수정 페이지
	@GetMapping("/boards/post/{id}")
	public String edit(HttpSession session) {
		if (session.getAttribute("member") == null) {
			return "redirect:/login";
		}
		return "create";
	}

	// 글 상세 페이지
	@GetMapping("/board/{id}")
	public String view(HttpSession session) {

		return "view";
	}
}
