package mbti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import mbti.repository.MbtiOptionRepository;
import mbti.service.BoardOptionService;

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