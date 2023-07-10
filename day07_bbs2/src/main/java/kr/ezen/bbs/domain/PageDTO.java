package kr.ezen.bbs.domain;

import lombok.Data;

@Data
public class PageDTO {
	private int viewPage=1; //현재페이지
	private int blockSize=5;
	private int blockNum; //블럭의 위치
	private int blockStart;
	private int blockEnd;
	private int prePage;
	private int nextPage;
	private int totalPage;
	
	private int startIndex; //각페이지별 시작 값(offset)
	private int cntPerPage=10; //페이별 게시글 수
	
	public void setValue(int totalCnt, int cntPerPage) {
		//전체 페이지 수
		this.totalPage=(int)Math.ceil((double)totalCnt/cntPerPage);
		
		//페이지별 시작값(시작인덱스):0,10,20 
		this.startIndex=(viewPage-1)*cntPerPage;
		
		//현재 페이지의 블럭위치 :0부터 시작
		this.blockNum=(viewPage-1)/blockSize;
		
		//블럭의 시작값 1,6,11,16...
		this.blockStart=(blockSize*blockNum)+1;
		
		//블럭의 마지막값
		this.blockEnd=blockStart+(blockSize-1);
		if(blockEnd>totalPage)blockEnd=totalPage;
		
		//이전 페이지
		this.prePage=blockStart-1;
		
		//다음 페이지
		this.nextPage=blockEnd+1;
		
		// nextPage는 전체 페이지수를 초과할 수 없음.
		if(nextPage>totalPage)nextPage=totalPage;
		
	}
	
}
