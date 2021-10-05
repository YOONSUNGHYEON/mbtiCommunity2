package mbti.restapi;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mbti.dto.MemberDTO;
import mbti.service.MemberService;

@RestController
@RequestMapping("/api")
public class MemberAPI {
	@Autowired
	MemberService memberService;

	@GetMapping("/session")
	public boolean session(HttpSession session) {
		if (session.getAttribute("member") == null) {
			return false;
		} else {
			return true;
		}

	}

	@GetMapping("/member")
	public MemberDTO getMember(HttpSession session) {
		MemberDTO memberDTO = new MemberDTO();
		if (session.getAttribute("member") == null) {
			return memberDTO;
		} else {
			return (MemberDTO) session.getAttribute("member");
		}
	}

	@PostMapping("/register/{mbtiOptionSeq}")
	public Map<String, String> register(@ModelAttribute MemberDTO memberDTO, @PathVariable("mbtiOptionSeq") int mbtiOptionSeq ) {
		Map<String, String> response = new HashMap();
		System.out.println(memberDTO.getId());
		System.out.println(memberDTO.getPassword());
		System.out.println(mbtiOptionSeq);
		if (memberDTO.getId().isEmpty() || memberDTO.getPassword().isEmpty() || memberDTO.getPassword2().isEmpty()) {
			response.put("message", "빈칸을 채워 주세요.");
			response.put("code", "400");
		} else if (!memberDTO.getPassword().equals(memberDTO.getPassword2())) {
			response.put("message", "비밀 번호가 달라요.");
			response.put("code", "400");
		} else if (memberDTO.getId().trim().length() > 32) {
			response.put("message", "id는 32자 이하로 작성해 주세요.");
			response.put("code", "400");
		} else if (memberDTO.getPassword2().trim().length() > 40) {
			response.put("message", "비밀번호 40자 이하로 작성해 주세요.");
			response.put("code", "400");
		} else {
			boolean registerResult = memberService.register(memberDTO.getId().trim(), memberDTO.getPassword2(), mbtiOptionSeq);
			if (registerResult) {
				System.out.println("dddddddddd");
				response.put("message", "성공적으로 회원가입 했습니다.");
				response.put("code", "200");
			} else {
				response.put("message", "아이디가 이미 존재 합니다.");
				response.put("code", "400");
			}
		}
		return response;
	}
}
