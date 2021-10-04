package mbti.dto;

import lombok.Data;
import mbti.entity.Recommend;

@Data
public class RecommendDTO {
	private int seq;

	private BoardDTO boardDTO;

	private MemberDTO memberDTO;

	private int check;

	public RecommendDTO() {

	}

	public RecommendDTO(Recommend recommend) {
		this.boardDTO = new BoardDTO(recommend.getBoard());
		this.memberDTO = new MemberDTO(recommend.getMember());
		this.seq = recommend.getSeq();
		this.check=recommend.getCheck();
	}

}
