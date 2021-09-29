package com.yoon.mbtiCommunity2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.yoon.mbtiCommunity2.Repository.MbtiOptionRepository;
import com.yoon.mbtiCommunity2.Service.BoardOptionService;

@Controller
public class HomeController {

	@Autowired
	MbtiOptionRepository mbtiOptionRepository;

	@Autowired
	BoardOptionService boardOptionService;

	@GetMapping("/")
	public String index() {
		return "/index";
	}



}