package com.software.dpweb.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserLoginResponse {
	
	private String name;
	private String email;
	private String mobile;
	private LocalDateTime lastLogin;
	public UserLoginResponse(String name, String email, String mobile, LocalDateTime lastLogin) {
		
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.lastLogin = lastLogin;
	}
	
//	public UserLoginResponse(String name, String email, String mobile) {
//		
//		this.name = name;
//		this.email = email;
//		this.mobile = mobile;
//	}
	
	
	

}
