package com.ezen.www.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class BoardDTO {

	private BoardVO bvo;
	private List<FileVO> flist; //하나의 board에 file 여러개 라는 의미를 가짐
}
