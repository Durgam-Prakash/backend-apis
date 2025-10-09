package com.software.dpweb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.dpweb.pojo.ForgotPasswordAPIData;
import com.software.dpweb.pojo.LoginAPIData;
import com.software.dpweb.pojo.ResetPassword;
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
	
	
	@PostMapping("/login")
	public ResponseEntity<?> userLogin(@Valid @RequestBody LoginAPIData loginAPIData) throws Exception{
		
		Map<String, Object> userLogIn = authService.userLogIn(loginAPIData);
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("Result", "Success");
		responseMap.put("Message", "You have logged in success");
		responseMap.put("Data", userLogIn);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", userLogIn.get("token").toString());
		
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(responseMap);
	}
	
	
	
	@PostMapping("/forgot")
	public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordAPIData forgotPasswordAPIData) throws Exception{
		
		authService.forgotPassword(forgotPasswordAPIData);
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("Result", "Success");
		responseMap.put("Message", "sent an email please check for rest your password");
		responseMap.put("Data", forgotPasswordAPIData);
		
		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
		
	}
	
	
	@PostMapping("/reset")
	
      public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPassword resetPassword) throws Exception{
		
		authService.resetPassword(resetPassword);
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("Result", "Success");
		responseMap.put("Message", "sent an email please check for rest your password");
		responseMap.put("Data", resetPassword);
		
		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
		
	}
	
	
	
	
	

}
