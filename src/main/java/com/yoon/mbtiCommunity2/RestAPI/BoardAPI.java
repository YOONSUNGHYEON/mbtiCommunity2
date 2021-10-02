package com.yoon.mbtiCommunity2.RestAPI;

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

import com.yoon.mbtiCommunity2.DTO.BoardDTO;
import com.yoon.mbtiCommunity2.DTO.MemberDTO;
import com.yoon.mbtiCommunity2.Service.BoardService;

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
	public List<BoardDTO> findListByBoardOptionId(@PathVariable("boardOptionSeq") int boardOptionSeq) {
		return boardService.findListByBoardOptionSeq(boardOptionSeq);
	}

	@DeleteMapping("board/{boardSeq}")
	public Map<String, String> deleteReview(@PathVariable(name = "boardSeq") int boardSeq){
		Map<String, String> response = new HashMap();
		return response;
	}

	@PostMapping("/boards/{boardOptionSeq}/post/{boardSeq}")
	public Map<String, String> update(@ModelAttribute BoardDTO boardDTO, @PathVariable("boardOptionSeq") int boardOptionSeq, @PathVariable("boardSeq") int boardSeq,
			HttpSession session) {
		System.out.println(boardDTO.getTitle());
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
