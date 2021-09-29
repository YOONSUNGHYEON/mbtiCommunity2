package com.yoon.mbtiCommunity2.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yoon.mbtiCommunity2.DTO.BoardOptionDTO;

import lombok.Getter;

@Entity(name="BoardOption")
@Table(name = "tBoardListOption")
@Getter
public class BoardOption {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nBoardOptionSeq")
    private int seq;

    @Column(name = "sName", nullable = false)
    private String name;

    public BoardOption() {

    }

	public BoardOption(BoardOptionDTO boardOptionDTO) {
		this.seq = boardOptionDTO.getSeq();
		this.name = boardOptionDTO.getName();
	}
}
