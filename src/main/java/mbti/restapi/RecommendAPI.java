package mbti.restapi;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mbti.dto.MemberDTO;
import mbti.service.RecommendService;


@RestController
@RequestMapping("/api")
public class RecommendAPI {

	@Autowired
	RecommendService recommendService;

	@GetMapping("recommend/{boardSeq}")
	public boolean findById(@PathVariable("boardSeq") int boardSeq, HttpSession session) {
		if(session.getAttribute("member")==null) {
			return false;
		}
		int check = recommendService.findCheckByMemberSeqAndBoardSeq(boardSeq, Integer.parseInt(session.getAttribute("memberSeq").toString()));

		if(check==0) {
			return false;
		}
		return true;
	}
	@PostMapping("/recommend/{boardSeq}")
	public Map<String, String> recommend( @PathVariable("boardSeq") int boardSeq, HttpSession session) {
		Map<String, String> response = new HashMap();
		if(session.getAttribute("member")==null) {
			response.put("message", "로그인 해주세요.");
			response.put("code", "400");
		} else {
			boolean recommandStatus = recommendService.recommend(boardSeq, (MemberDTO) session.getAttribute("member"));
			if(recommandStatus==true) {
				response.put("code", "200");
			} else {
				response.put("code", "202");
			}


		}
		return response;
	}

}
