package com.ezen.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Setter
@Getter
public class PagingVO {
	private int pageNo;
	private int qty;
	private String type;
	private String keyword;
	
	//아무것도 없는 빈 생성자는 따로 작업(@NoArgsConstructor)
	public PagingVO() {
		this.pageNo = 1;
		this.qty = 10;
	}
	
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	//DB에서 사용될 시작 번지 구하기
	//select * from board limit 번지, 개수 -> 번지는 0부터 시작
	//1page limit 0, 10 -> 2page로 바뀌게 되면 limit 10, 10 -> 3page로 바뀌게 되면 limit 20, 10
	public int getPageStart() { //getPageStart -> 구해오는것이기 때문에 앞에 get을 붙였음
		return (this.pageNo-1) * this.qty;
	}
	
	//type의 복합 타입을 배열로 생성
	//복합 타입의 키워드일 경우 각자 검색하게 하기 위해 배열로 생성
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
		//type이 null 이라면 배열에 빈값을 담아 그렇지 않다면(null이 아니라면) 내 타입을 하나씩 "" 떼어서 배열에 담아줘
	}
}