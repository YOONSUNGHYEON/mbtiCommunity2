package mbti.restapi;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping("/register")
	public Map<String, String> register(@RequestBody Map<String, String> param) {
		Map<String, String> response = new HashMap();
		if(param.get("id").isEmpty() || param.get("password").isEmpty() || param.get("password2").isEmpty()) {
			response.put("message", "빈칸을 채워 주세요.");
			response.put("code", "400");
		}
		else if(!param.get("password").equals(param.get("password2"))) {
			response.put("message", "비밀 번호가 달라요.");
			response.put("code", "400");
		}
		else if (param.get("id").trim().length()>32) {
			response.put("message", "id는 32자 이하로 작성해 주세요.");
			response.put("code", "400");
		}
		else if (param.get("password").trim().length()>40) {
			response.put("message", "비밀번호 40자 이하로 작성해 주세요.");
			response.put("code", "400");
		}
		else {
			if(memberService.register(param.get("id").trim(), param.get("password").trim(), param.get("mbtiOption"))) {
				response.put("message", "성공적으로 회원가입 했습니다.");
				response.put("code", "200");
			}
			else {
				response.put("message", "아이디가 이미 존재 합니다.");
				response.put("code", "400");
			}
		}
		return response;
	}
}
