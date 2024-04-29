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
public class FileVO {
/* create table file(
uuid varchar(256) not null,
save_dir varchar(256) not null,
file_name varchar(256) not null,
file_type tinyint(1) default 0,
file_size int,
bno int,
reg_date datetime default now(),
primary key(uuid));
*/
	
	private String uuid;
	private String save_dir; //저장경로
	private String file_name; //파일명
	private int file_type; //파일타입
	private int bno;
	private long file_size; //파일크기(리턴타입 long-DB에서는 bigint)
	private String reg_date;
	
	
}
