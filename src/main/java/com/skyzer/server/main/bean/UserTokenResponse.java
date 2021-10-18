package com.skyzer.server.main.bean;

public class UserTokenResponse {

	private final String jwttoken;
	
	public UserTokenResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
