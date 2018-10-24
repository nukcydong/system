package com.gopher.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopher.system.model.vo.request.LoginRequst;
import com.gopher.system.model.vo.request.RegisterRequst;
import com.gopher.system.service.AuthService;
@RestController
@RequestMapping(path="/auth")
public class AuthController {
	@Autowired
	private AuthService authService;
	
	@PostMapping(path="/register")
	public void register(@ModelAttribute RegisterRequst registerRequst){
		authService.register(registerRequst);
	}
	
	@PostMapping(path="/login")
	public void login(@ModelAttribute LoginRequst loginRequst){
		authService.login(loginRequst);
	}
	

}
