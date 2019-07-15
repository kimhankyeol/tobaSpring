package com.poly.toba.controller;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.poly.toba.model.EmailDTO;
import com.poly.toba.model.UserDTO;
import com.poly.toba.service.impl.IUserService;
import com.poly.toba.util.Email;
import com.poly.toba.util.EmailSender;
import com.poly.toba.util.TempKey;
@SpringBootApplication
@MapperScan(basePackages = "com.poly.toba")
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private IUserService userService;
	@Autowired
	private EmailSender emailSender;
	
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
	@PostMapping("/changeStatus")
	public ResponseEntity<String> changeStatus(@RequestBody EmailDTO eDTO) throws Exception {
		System.out.println("Status 변경");
		if(eDTO.getEmailKey() == null) {
			System.out.println("실패 1");
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} else {
			int result = userService.changeStatus(eDTO);
			System.out.println("성공");
			return new ResponseEntity<String>(HttpStatus.OK);
		}
	}
	@PostMapping("/register")
	public ResponseEntity<String> userRegister(@RequestBody UserDTO uDTO) throws Exception{
		System.out.println("가입 시작");
		//비밀번호 암호화 해야함
		if(uDTO.getUserEmail() == null) {
			System.out.println("test");
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} else {
			// 회원가입
			int result = userService.regUser(uDTO);
			System.out.println("가입됨");
			// 인증번호 발급
			TempKey key = new TempKey();
			System.out.println(key);
			String emailKey = key.getKey(10, false);
			System.out.println(emailKey);
			EmailDTO eDTO = new EmailDTO(); 
			eDTO.setEmailKey(emailKey);
			eDTO.setUserEmail(uDTO.getUserEmail());
			userService.regKey(eDTO);
			System.out.println("발급 키 : " + emailKey);
			
			// 회원가입 후 메일 발송
			Email sendEmail = new Email();
			sendEmail.setReciver(uDTO.getUserEmail());
			sendEmail.setSubject("toba 회원가입 인증메일 발송 안내");
			sendEmail.setContent(sendEmail.setContents(eDTO));
			emailSender.SendEmail(sendEmail);
			System.out.println("메일발송 : " + sendEmail.getReciver());
			System.out.println("내용 : " + sendEmail.getContent());
			
			return new ResponseEntity<String>(HttpStatus.OK);
		}
	}
	@GetMapping("/authCheck/{emailKey}")
	public ResponseEntity<String> authCheck(@PathVariable String emailKey) throws Exception {
		int count = 0;
		count = userService.getEmailKey(emailKey);
		if(count!=0) {
			return new ResponseEntity<String>("1", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("0", HttpStatus.OK);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody UserDTO uDTO, HttpSession session, 
										HttpServletResponse res) throws Exception { 
		System.out.println(this.getClass() +" 로그인 시작");
		uDTO = userService.getUserLogin(uDTO);
		if(uDTO == null) {
			System.out.println(this.getClass() + " 로그인 실패"); 
			return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<UserDTO>(uDTO, HttpStatus.OK);
		}
	}
}
