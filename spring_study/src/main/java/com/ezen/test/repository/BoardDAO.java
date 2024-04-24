package com.ezen.test.repository;

import java.util.List;

import com.ezen.test.domain.BoardVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO getDetail(int bno);

	void update(BoardVO bvo);

	void delete(int bno);

	void updateReadCount(int bno);

}
