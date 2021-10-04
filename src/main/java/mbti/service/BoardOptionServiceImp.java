package mbti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mbti.dto.BoardOptionDTO;
import mbti.entity.BoardOption;
import mbti.repository.BoardOptionRepository;

@Service
public class BoardOptionServiceImp implements BoardOptionService {

	@Autowired
	BoardOptionRepository boardOptionRepository;

	@Override
	public List<BoardOptionDTO> findAll() {
		List<BoardOption> boardOptionList = boardOptionRepository.findAll();
		List<BoardOptionDTO> boardOptionDTOList = new ArrayList();
		for (int i = 0; i < boardOptionList.size(); i++) {
			boardOptionDTOList.add(new BoardOptionDTO(boardOptionList.get(i)));
		}
		return boardOptionDTOList;
	}

	@Override
	public BoardOptionDTO findNameBySeq(int boardOptionSeq) {
		BoardOption boardOption = boardOptionRepository.findBySeq(boardOptionSeq);
		return new BoardOptionDTO(boardOption);
	}



}
