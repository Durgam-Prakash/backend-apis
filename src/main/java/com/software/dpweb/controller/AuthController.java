package com.software.dpweb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.dpweb.entity.User;
import com.software.dpweb.pojo.ForgotPasswordAPIData;
import com.software.dpweb.pojo.IpData;
import com.software.dpweb.pojo.LoginAPIData;
import com.software.dpweb.pojo.MoneyTransferApiData;
import com.software.dpweb.pojo.ResetPassword;
import com.software.dpweb.pojo.SignupAPIData;
import com.software.dpweb.service.AuthService;
import com.software.dpweb.service.IpDataService;
import com.software.dpweb.utils.ResponseData;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private IpDataService ipDataService;
	
	
	
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
	public ResponseEntity<?> userLogin(@Valid @RequestBody LoginAPIData loginAPIData,HttpServletRequest request) throws Exception{
		
		IpData ipData = ipDataService.getIpDataFromIpInfo(request.getRemoteAddr());
		
		Map<String, Object> userLogIn = authService.userLogIn(loginAPIData);
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("Result", "Success");
		responseMap.put("Message", "You have logged in success");
		responseMap.put("Data", userLogIn);
		responseMap.put("IpData", ipData);
		
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
	
	
	@PostMapping("/transfer")
	public ResponseEntity<?> transferMoney(@RequestBody MoneyTransferApiData moneyTransferApiData) throws Exception{
		
		authService.moneyTransfer(moneyTransferApiData);
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put(ResponseData.RESULT, ResponseData.SUCCESS);
		responseMap.put("Message", "Amount transfered successfully ");
		responseMap.put("Data", moneyTransferApiData);
		
		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
		
	}
	
	
	
	/* this is the code to get data from Redis by using Redis*/
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserData(@PathVariable Long id){
		User userData = authService.getUserData(id);
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put(ResponseData.RESULT, ResponseData.SUCCESS);
		responseMap.put("Message", "user data get from redis successfully ");
		responseMap.put("Data",userData );
		
		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
		
	}
	
	
	
	
	

}
