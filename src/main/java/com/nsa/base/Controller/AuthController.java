package com.nsa.base.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsa.base.AuthDtos.AuthResponse;
import com.nsa.base.AuthDtos.LoginRequest;
import com.nsa.base.AuthDtos.SignupRequest;
import com.nsa.base.Service.AuthService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	 AuthService auth;
	  

	  @PostMapping("/signup")
	  public String signup(@Valid @RequestBody SignupRequest req) {
	    auth.signup(req);
	    return "Signup successful";
	  }

	  @PostMapping("/login")
	  public AuthResponse login(@Valid @RequestBody LoginRequest req) {
	    return auth.login(req);
	  }
}













