package mbti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Getter;
import mbti.dto.BoardDTO;

@Entity(name = "Board")
@Table(name = "tBoardList")
@Getter
public class Board {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nBoardSeq", nullable = false)
	private int seq;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "nBoardOptionSeq")
	private BoardOption boardOption;

	@ManyToOne
	@JoinColumn(name = "nMemberSeq", nullable = false)
	private Member member;

	@Column(name = "sTitle", nullable = false)
	private String title;

	@Column(name = "sContent", nullable = false)
	private String content;

    @Column(name = "dtCreateDate", nullable = false)
    private String createDate ;

    @Column(name = "nHit", nullable = false)
    private int hit ;

    public Board() {

	}

	public Board(BoardDTO boardDTO) {
		this.seq = boardDTO.getSeq();
    	this.boardOption = new BoardOption(boardDTO.getBoardOptionDTO());
    	this.title = boardDTO.getTitle();
    	this.content = boardDTO.getContent();
    	this.createDate = boardDTO.getCreateDate();
    	this.member = new Member(boardDTO.getMemberDTO());
	}

}
