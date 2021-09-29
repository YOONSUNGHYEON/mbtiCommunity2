package com.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.yoon.mbtiCommunity2.Entity.BoardOption;
import com.yoon.mbtiCommunity2.Repository.BoardOptionRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BoardOptionTest {

	@Autowired
	private BoardOptionRepository boardOptionRepository;

	@Test
	public void getBoardOptionList() {
		Optional<BoardOption> booardList = boardOptionRepository.findById(14);
		assertNotNull(booardList);
	}


}
