package mbti.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mbti.dto.MbtiOptionDTO;
import mbti.service.MbtiOptionService;

@RestController
@RequestMapping("/api")
public class MbtiOptionAPI {
	@Autowired
	MbtiOptionService mbtiOptionService;

	@GetMapping("mbtiOptions")
	public List<MbtiOptionDTO> index(Model model) {
		List<MbtiOptionDTO> mbtiOptionDTOList = mbtiOptionService.findAll();
		return mbtiOptionDTOList;
	}
}
