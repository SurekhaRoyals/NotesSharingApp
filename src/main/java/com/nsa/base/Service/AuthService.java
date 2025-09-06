package com.nsa.base.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nsa.base.AuthDtos.AuthResponse;
import com.nsa.base.AuthDtos.LoginRequest;
import com.nsa.base.AuthDtos.SignupRequest;
import com.nsa.base.Entity.User;
import com.nsa.base.Repository.UserRepository;
import com.nsa.base.Security.JwtUtil;

import jakarta.transaction.Transactional;

@Service
public class AuthService {

	@Autowired
	UserRepository users;
	@Autowired
	 PasswordEncoder encoder;
	@Autowired
	  JwtUtil jwt;
	@Autowired
	AuthenticationManager authenticationManager;

	  

	  @Transactional
	  public void signup(SignupRequest req) {
	    if (users.existsByUsername(req.getUsername()))
	      throw new RuntimeException("Username already taken");
	    var u = new User();
	    u.setUsername(req.getUsername());
	    u.setPasswordHash(encoder.encode(req.getPassword()));
	    users.save(u);
	  }

//	  public AuthResponse login(LoginRequest req) {
//	    var user = users.findByUsername(req.getUsername())
//	      .orElseThrow(() -> new RuntimeException("Invalid credentials"));
//	    if (!encoder.matches(req.getPassword(), user.getPasswordHash())) {
//	      throw new RuntimeException("Invalid credentials");
//	    }
//	    return new AuthResponse(jwt.generate(user.getUsername()));
////	    return new AuthResponse(jwt.generate(user.getUsername()));
//	  }
	  
	  public AuthResponse login(LoginRequest request) {
	        authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
	        String token = jwt.generate(request.getUsername());
	        return new AuthResponse(token);
	    }
}







