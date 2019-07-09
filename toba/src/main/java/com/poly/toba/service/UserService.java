package com.poly.toba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.toba.mapper.UserMapper;
import com.poly.toba.model.UserDTO;
import com.poly.toba.service.impl.ICommonService;
import com.poly.toba.service.impl.IUserService;

@Service
public class UserService implements IUserService{
	@Autowired
	private UserMapper userMapper;

	@Override
	public int regUser(UserDTO uDTO) throws Exception {
		return userMapper.regUser(uDTO);
	}
	// 중복확인
	@Override
	public int getUserEmailCheck(String userEmail) throws Exception {
		return userMapper.getUserEmailCheck(userEmail);
	}
	@Override
	public int getUserNickCheck(String userNick) throws Exception {
		return userMapper.getUserNickCheck(userNick);
	}

}
