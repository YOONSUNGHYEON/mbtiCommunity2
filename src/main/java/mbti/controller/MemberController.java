package mbti.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mbti.dto.MemberDTO;
import mbti.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;

	@GetMapping("/login")
	public String login(HttpSession session) {
		if (session.getAttribute("member") != null) {
			return "redirect:/index";
		}
		return "login";
	}

	@GetMapping("/register")
	public String register(HttpSession session) {
		if (session.getAttribute("member") != null) {
			return "redirect:/index";
		}
		return "register";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute MemberDTO memberDTO, Model model, HttpSession session) {
		MemberDTO result = memberService.login(memberDTO);
		if (result != null) {
			session.setAttribute("member", result);
			session.setAttribute("memberId", result.getId());
			session.setAttribute("memberSeq", result.getSeq());
			if(result.getAdmin()== 1) {
				session.setAttribute("adminId", result.getId());
			}
			return "redirect:/";

		} else {
			model.addAttribute("msg", "입력하신 내용틀립니다");
			return "msg";
		}
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}



}
