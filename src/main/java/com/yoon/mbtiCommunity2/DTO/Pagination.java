package com.yoon.mbtiCommunity2.DTO;

import lombok.Data;

@Data
public class Pagination {
	private int blockCount = 10;
	private int blockPage = 5;
	private int currentPage;
	private int totalPage;
	private int rowCount;
	private int startCount;
	private float startPage;
	private float endPage;

	public Pagination(int rowCount, int currentPage) {
		this.totalPage = (rowCount + this.blockCount - 1) / this.blockCount;
		if (this.totalPage < currentPage) {
			currentPage = this.totalPage;
		}
		if (currentPage < 1) {
			currentPage = 1;
		}
		this.startCount = (currentPage - 1) * this.blockCount;

		if((this.blockPage+1)/2<currentPage) {
			this.startPage=currentPage-(this.blockPage/1)/2;
			this.endPage = this.startPage + this.blockPage-1;
		}else {
			this.startPage = 1;
			this.endPage = this.startPage + this.blockPage-1;
		}

		this.currentPage = currentPage;


		 if (this.endPage > this.totalPage)
			 this.endPage = this.totalPage;

	}
}
