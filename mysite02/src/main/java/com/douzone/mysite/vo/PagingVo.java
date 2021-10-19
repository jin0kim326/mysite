package com.douzone.mysite.vo;

public class PagingVo {
	private Long totalBoard;			// 총게시물 개수
	private Long totalPage;				// 총 페이지 :  Math.floor(총게시물수 / 한페이지에 보일 페이지 개수)  
	private Long nowPage;				// 현재페이지
	private Long pageSize;				// 한 페이지에 보일 페이지개수 ex)5 => [ 1, 2, 3, 4, 5]
	private Long beginPage;				// 시작페이지 
	private Long endPage;				// 마지막 페이지 
	public Long getTotalBoard() {
		return totalBoard;
	}
	public void setTotalBoard(Long totalBoard) {
		this.totalBoard = totalBoard;
	}
	public Long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}
	public Long getNowPage() {
		return nowPage;
	}
	public void setNowPage(Long nowPage) {
		this.nowPage = nowPage;
	}
	public Long getPageSize() {
		return pageSize;
	}
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}
	public Long getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(Long beginPage) {
		this.beginPage = beginPage;
	}
	public Long getEndPage() {
		return endPage;
	}
	public void setEndPage(Long endPage) {
		this.endPage = endPage;
	}
	@Override
	public String toString() {
		return "PagingVo [totalBoard=" + totalBoard + ", totalPage=" + totalPage + ", nowPage=" + nowPage
				+ ", pageSize=" + pageSize + ", beginPage=" + beginPage + ", endPage=" + endPage + "]";
	}
}
