package com.software.dpweb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.dpweb.pojo.SignupAPIData;
import com.software.dpweb.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/create-account")
	public ResponseEntity<?> createAccount(@Valid @RequestBody SignupAPIData signupAPIData) throws Exception{
		
		Object account = authService.createAccount(signupAPIData);
		Map<String,Object> responseMap = new HashMap<>();
		responseMap.put("Result", "Success");
		responseMap.put("Message", "Account is Creared Successfully....!");
		responseMap.put("Data", account);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
	}

}
