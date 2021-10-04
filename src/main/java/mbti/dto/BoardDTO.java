package mbti.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import mbti.entity.Board;

@Data
public class BoardDTO {

	private int seq;
	@JsonIgnore
	private BoardOptionDTO boardOptionDTO;
	private MemberDTO memberDTO;
	private String title;
	private String content;
    private String createDate ;
    private int hit=0 ;
    private int commentCount;
    private int recommendCount;

    public BoardDTO() {
  		// TODO Auto-generated constructor stub

  	}

    public BoardDTO(Board board) {
		// TODO Auto-generated constructor stub
    	this.seq = board.getSeq();
    	this.boardOptionDTO = new BoardOptionDTO(board.getBoardOption());
    	this.title = board.getTitle();
    	this.content = board.getContent();
    	this.createDate = board.getCreateDate();
    	this.memberDTO = new MemberDTO(board.getMember());
	}
}

