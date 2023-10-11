package com.ivansuarez.demo.model;

public class JWTResponse {
    
    private String jwt;

	public JWTResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
}
