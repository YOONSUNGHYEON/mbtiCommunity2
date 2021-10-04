package mbti.dto;

import lombok.Data;
import mbti.entity.MbtiOption;
import mbti.entity.Member;

@Data
public class MemberDTO {

	private int seq;
	private String id;
	private String password;
	private String joinDate;
	private MbtiOption mbtiOption;
    private int admin;

	public MemberDTO() {

	}

	public MemberDTO(Member member) {
		this.seq = member.getSeq();
		this.id = member.getId();
		this.mbtiOption = member.getMbtiOption();
		this.admin = member.getAdmin();
	}
}
