package mbti.dto;

import lombok.Data;
import mbti.entity.BoardOption;

@Data
public class BoardOptionDTO {
	private int seq;
	private String name;

	public BoardOptionDTO(BoardOption boardOption) {
		// TODO Auto-generated constructor stub
		this.seq = boardOption.getSeq();
		this.name = boardOption.getName();
	}

}
