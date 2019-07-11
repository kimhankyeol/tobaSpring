package com.poly.toba.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.poly.toba.model.EmailDTO;
import com.poly.toba.model.UserDTO;

@Mapper
public interface UserMapper {
	// 회원등록
	public int regUser(UserDTO uDTO) throws Exception;
	// 중복확인
	public int getUserEmailCheck(String userEmail) throws Exception;
	public int getUserNickCheck(String userNick) throws Exception;
	// 인증키 발급
	public int regKey(EmailDTO eDTO) throws Exception;
	public int getEmailKey(String emailKey) throws Exception;
	public int changeStatus(EmailDTO eDTO) throws Exception;
}
