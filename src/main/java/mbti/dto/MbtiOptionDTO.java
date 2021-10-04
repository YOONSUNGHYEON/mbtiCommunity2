package mbti.dto;

import lombok.Data;
import mbti.entity.MbtiOption;

@Data
public class MbtiOptionDTO {

    private int seq;

    private String name;

    public MbtiOptionDTO(MbtiOption mbtiOption) {
		// TODO Auto-generated constructor stub
		this.seq = mbtiOption.getSeq();
		this.name = mbtiOption.getName();
	}
}
