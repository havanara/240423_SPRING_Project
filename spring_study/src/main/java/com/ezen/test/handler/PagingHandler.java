package com.ezen.test.handler;

import com.ezen.test.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PagingHandler {
	//list 화면 하단의 페이지 조작부 기능을 담당하는 변수들을 생성
	//시작페이지 번호, 끝 페이지 번호 (한 페이지에서 보여지는 페이지네이션)
	//진짜 끝 페이지 번호
	//이전, 다음이 생성되는 여부
	
	//전체 게시글 수, 현재 페이지 번호, 한 페이지에 들어갈 게시글 수(매개변수로 받기)
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int realEndPage;
	
	private int totalCount;
	private PagingVO pgvo;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		//컨트롤러에서 받아서 넣기
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		//첫번째 페이지는 1~10, 두번째 11~20, 세번째 21~30 ... 규칙
		//현재 pageNo -> 1 /(나누기) 10 -> 0.1(올림) 1 * 10 -> 10
		//현재 pageNo -> 2 /(나누기) 10 -> 0.2(올림) 1 * 10 -> 10
		//현재 pageNo -> 11 /(나누기) 10 -> 1.1(올림) 2 * 10 -> 20
		//정수 / 정수 -> 정수
		this.endPage = (int)Math.ceil(pgvo.getPageNo()/(double)10)*10;
		this.startPage = endPage-9;
		
		//진짜 끝 페이지 번호
		//103 -> 103 / 10 -> 10.3(올림) -> 11페이지
		this.realEndPage = (int)Math.ceil(totalCount/(double)pgvo.getQty());
		
		if(realEndPage < endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1; //1, 11, 21 규칙으로 진행되기 때문에 1만 아니면 실행되도록
		this.next = this.endPage < realEndPage;
	}
	
	
}
