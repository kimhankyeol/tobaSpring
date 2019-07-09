package com.poly.toba.service.impl;

import com.poly.toba.model.UserDTO;

public interface IUserService {
	// 회원등록
	public int regUser(UserDTO uDTO) throws Exception;
	// 회원가입 중복체크
	public int getUserEmailCheck(String userEmail) throws Exception;
	public int getUserNickCheck(String userNick) throws Exception;
	
}
