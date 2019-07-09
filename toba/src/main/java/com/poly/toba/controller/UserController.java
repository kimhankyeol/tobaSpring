package com.poly.toba.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.toba.model.UserDTO;
import com.poly.toba.service.impl.ICommonService;
import com.poly.toba.service.impl.IUserService;
@SpringBootApplication
@MapperScan(basePackages = "com.poly.toba")
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private IUserService userService;
	// 중복확인
	@GetMapping("/emailCheck/{userEmail}")
	public ResponseEntity<String> emailCheck(@PathVariable String userEmail) throws Exception {
		int count = 0;
		count = userService.getUserEmailCheck(userEmail);
		System.out.println(this.getClass() + " count : " + count);
		
		if(count!=0) {
			return new ResponseEntity<String>("1", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("0", HttpStatus.OK);
		}
	}
	@GetMapping("/nickCheck/{userNick}")
	public ResponseEntity<String> nickCheck(@PathVariable String userNick) throws Exception {
		int count = 0;
		count = userService.getUserNickCheck(userNick);
		System.out.println(this.getClass() + " count : " + count);
		if(count!=0) {
			return new ResponseEntity<String>("1", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("0", HttpStatus.OK);
		}
	}
	@PostMapping("/register")
	public ResponseEntity<String> userRegister(@RequestBody UserDTO uDTO) throws Exception{
		//비밀번호 암호화 해야함
		if(uDTO.getUserEmail() == null) {
			System.out.println("test");
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} else {
			int result = userService.regUser(uDTO);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
	}
}
