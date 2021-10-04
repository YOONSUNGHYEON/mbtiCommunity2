package mbti.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mbti.dto.BoardOptionDTO;
import mbti.service.BoardOptionService;

@RestController
@RequestMapping("/api")
public class BoardOptionAPI {
	@Autowired
	BoardOptionService boardOptionService;

	@GetMapping("boardOptions")
	public List<BoardOptionDTO> findAll(Model model) {
		List<BoardOptionDTO> boardOptionDTOList = boardOptionService.findAll();
		if(boardOptionDTOList==null) {

		}
		return boardOptionDTOList;
	}

	@GetMapping("boardOption/{seq}")
	public BoardOptionDTO findNameBySeq(@PathVariable("seq") int boardOptionSeq) {
		BoardOptionDTO boardOptionDTO = boardOptionService.findNameBySeq(boardOptionSeq);
		return boardOptionDTO;
	}

}
