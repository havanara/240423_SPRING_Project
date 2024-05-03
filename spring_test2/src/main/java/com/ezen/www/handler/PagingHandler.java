package com.ezen.www.handler;

import java.util.List;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PagingHandler {

	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int totalCount;
	private PagingVO pgvo;
	
	private int realEndPage;
	
	private List<CommentVO> cmtList;
	
	//생성자에서 모든 값들이 계산되어 설정되어야 하기 때문에 생성자 import는 하지 않고 따로 만듦
	//리스트용
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		//1~10 / 11~20 / 21~30
		//1 2 3 ... 10을 눌러도 endPage는 변함없이 10이어야 함
		//(1(pageNo(내가선택한수)) / 10)=0.1  나머지를 올려(ceil) 1로 만들어 * 10(페이지네이션 개수)
		//(2(pageNo(내가선택한수)) / 10)=0.2  나머지를 올려(ceil) 1로 만들어 * 10(페이지네이션 개수)
		//정수/정수 = 정수니까 하나라도 형변환을 해야 0.x을 만들 수 있음
		//정수로 리턴이 되어야 하니까 (int) 형변환 처리
		this.endPage = (int)Math.ceil(pgvo.getPageNo()/(double)10) * 10;
		this.startPage = endPage-9;
		
		//실제 마지막 페이지
		//(전체 글 수 / 한페이지의 표시되는 게시글 수) -> 올림 처리
		this.realEndPage = (int)Math.ceil(totalCount/(double)pgvo.getQty());
		
		//현재 정말 끝페이지(realEndPage)가 28page 라면 29, 30은 나오면 안돼
		//endPage = realEndPage 처리
		if(endPage>realEndPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1; //startPage는 1다음에 무조건 11
		this.next = this.endPage < this.realEndPage;
	}
	
	//댓글용
	public PagingHandler(PagingVO pgvo, int totalCount, List<CommentVO>cmtList) {
		this(pgvo, totalCount);
		this.cmtList = cmtList;
	}
}
