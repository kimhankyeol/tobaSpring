package com.poly.toba.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poly.toba.model.LoginParams;
import com.poly.toba.service.impl.IUserService;

@RestController
public class LoginController {
	@Autowired
	private IUserService userService;
	
	private final AuthenticationManager authenticationManager;
	public LoginController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@RequestMapping(value="/api/login")
	public ResponseEntity<Object> login(@RequestBody LoginParams params, HttpSession session) {
		System.out.println("시작한다");
		System.out.println(" Email : " + params.getUserEmail());
		System.out.println(" Password : " + params.getUserPassword());
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(params.getUserEmail(), params.getUserPassword());
		System.out.println(token);
		
		try {
			Authentication authentication = authenticationManager.authenticate(token);
			System.out.println(authentication);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.ok(false);
		}
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value="/api/test", method=RequestMethod.GET)
	public ResponseEntity<Principal> test(Principal principal) {
		return ResponseEntity.ok(principal);
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value="/api/roles", method=RequestMethod.GET)
	public ResponseEntity roles(HttpServletRequest req, HttpSession session) {
		return ResponseEntity.ok(req.getUserPrincipal());
	}
	
	// 로그아웃
	@RequestMapping(value="/api/logout", method = RequestMethod.POST)
	public ResponseEntity logout(HttpServletRequest req, HttpServletResponse res) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(req, res, authentication);
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}
	// 로그인 체크
	@RequestMapping(value="/api/check_login", method=RequestMethod.POST)
	public ResponseEntity check_login(HttpServletRequest req, HttpServletResponse res, Principal principal) throws IOException, ServletException {
		boolean isLogin = req.isUserInRole("ROLE_USER");
		if(isLogin) {
			return ResponseEntity.ok(principal.getName());
		}
		return ResponseEntity.ok(false);
	}
}
