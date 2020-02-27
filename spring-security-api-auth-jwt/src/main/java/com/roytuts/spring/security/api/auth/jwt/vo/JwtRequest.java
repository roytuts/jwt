package com.roytuts.spring.security.api.auth.jwt.vo;

public class JwtRequest {

	private String username;
	private String userpwd;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

}
