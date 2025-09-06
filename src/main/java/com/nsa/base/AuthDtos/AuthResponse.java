package com.nsa.base.AuthDtos;

import org.springframework.stereotype.Component;

@Component
public class AuthResponse {

	
	private String accessToken;
	private String tokenType = "Bearer";
	public String getAccessToken() {
		return accessToken;
	}
	public String getTokenType() {
		return tokenType;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	
	
	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthResponse(String accessToken, String tokenType) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
	}
	public AuthResponse(String token) {
		this.accessToken = token;
	}

	







}
