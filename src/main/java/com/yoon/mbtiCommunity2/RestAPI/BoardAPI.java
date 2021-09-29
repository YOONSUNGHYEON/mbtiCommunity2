package com.yoon.mbtiCommunity2.RestAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoon.mbtiCommunity2.DTO.BoardDTO;
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

}
