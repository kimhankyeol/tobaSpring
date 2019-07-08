package com.poly.toba.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.toba.model.UserDTO;
@RestController
@RequestMapping("/users")
public class UserController {
	@PostMapping("/register")
	public ResponseEntity<String> userRegister(@RequestBody UserDTO uDTO) throws Exception{
		System.out.println("김확인"+uDTO.getUserId());

		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
