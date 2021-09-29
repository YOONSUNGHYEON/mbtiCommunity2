package com.yoon.mbtiCommunity2.Entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity(name="MbtiOption")
@Table(name = "tMbtiOption")
@Getter
public class MbtiOption {
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	  	@Column(name = "nMbtiOptionSeq")
	    private int seq;

	    @Column(name = "sName", nullable = false)
	    private String name;
}
