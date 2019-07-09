package com.poly.toba.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poly.toba.model.UserDTO;

@Mapper
public interface UserMapper {
	// 회원등록
	public int regUser(UserDTO uDTO) throws Exception;
	// 중복확인
	public int getUserEmailCheck(String userEmail) throws Exception;
	public int getUserNickCheck(String userNick) throws Exception;
}
