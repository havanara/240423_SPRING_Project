package com.ezen.test.domain;

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
public class CommentVO {
/* 
create table comment(
cno int auto_increment,
bno int not null,
writer varchar(100) not null,
content varchar(500),
reg_date datetime default now(),
primary key(cno));
*/

	private int cno;
	private int bno;
	private String writer;
	private String content;
	private String reg_date;

	
}
