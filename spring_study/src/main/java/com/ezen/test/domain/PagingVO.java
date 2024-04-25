package com.ezen.test.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class PagingVO {
	//select * from board limit 0(시작번지),10(개수);
	
	//페이징 -> pageNo에 따라서 qty(개수) 설정
	private int pageNo;
	private int qty; //한 화면에 보여줄 게시글 수(10개)
	
	//검색 -> 검색에 대한 type(타입) / keyword(검색어)
	private String type;
	private String keyword;
	
	public PagingVO() {
		this.pageNo = 1;
		this.qty = 10;
	}
	
	public int getPageStart() {
		//DB상에서 limit의 시작 번지를 구하는 메서드
		//1페이지 일 때 0부터 시작, 2페이지 일 때 1 ...
		//해당 pageNo에서 1을 뺀 후 개수 곱하기
		return (this.pageNo-1)*this.qty;
	}
	
	//여러가지의 타입을 같이 검색하기 위해서 타입을 배열로 구분하는 메서드
	//ex) tcw -> t c w
	//배열이나 리스트가 아니면 forEach를 할 수 없어
	public String[] getTypeToArray() {
		return this.type == null? new String[] {} : this.type.split("");
	}
}
