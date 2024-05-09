package com.ezen.www.repository;

import java.util.List;

import com.ezen.www.domain.AuthVO;
import com.ezen.www.domain.UserVO;

public interface UserDAO {

	int insert(UserVO uvo);

	int insetAuthInit(String email);

	UserVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	int updateLastLogin(String authEmail);

	List<UserVO> getList();

	String getPwd(String email);

	void updateModify(UserVO uvo);

	void authDelete(String id);
	
	void delete(String id);


}
