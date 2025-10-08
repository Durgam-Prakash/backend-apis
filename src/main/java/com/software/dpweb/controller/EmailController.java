package com.software.dpweb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.dpweb.service.EmailService;

@RestController
@RequestMapping("/api/email")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	
	@GetMapping("/send")
	public ResponseEntity<?> sendPlainMail(){
		        String fromEmail="prakashdurgam1411@gmail.com";
				String toEmail="prakashdurgam8779@gmail.com"; 
				String subject="test mail";
				String mailBody=" hi, this is the mail test";
		
	Object send=	emailService.sendPlainEmail(fromEmail, toEmail, subject, mailBody);
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("Result", "Success");
		responseMap.put("Message", "Mail Sent...");
		responseMap.put("Data",send);
		
		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
		
		
		
	}
	
	
	
	@GetMapping("/send1")
	public ResponseEntity<?> sendHtmlMail() throws Exception {
	    String fromEmail = "durgamprakash10@gmail.com"; 
	    String toEmail = "prakashdurgam8779@gmail.com";
	    String subject = "Test Mail";

	    String mailBody = "Hai Prakash,<br/>"
	            + "This is a test email from Spring Boot API. Please use the link below:<br/>"
	            + "<a href='https://www.youtube.com'>Click here</a><br/><br/>"
	            + "<b>Regards,<br/>Prakash</b>";

	    emailService.sendHtmlMail(fromEmail, toEmail, subject, mailBody);

	    Map<String, Object> responseMap = new HashMap<>();
	    responseMap.put("Result", "Success");
	    responseMap.put("Message", "Mail Sent...");
	    responseMap.put("Data", "");

	    return ResponseEntity.status(HttpStatus.OK).body(responseMap);
	}

	
	@GetMapping("/send2")
	public ResponseEntity<?> sendHtmlTemplateMail() throws Exception {
	    String fromEmail = "durgamprakash10@gmail.com"; 
	    String toEmail = "prakashdurgam8779@gmail.com";
	    String subject = "Test Mail";

	    

	    emailService.sendHtmlTemplateMail(fromEmail, toEmail, subject, "test-email");

	    Map<String, Object> responseMap = new HashMap<>();
	    responseMap.put("Result", "Success");
	    responseMap.put("Message", "Mail Sent...");
	    responseMap.put("Data", "");

	    return ResponseEntity.status(HttpStatus.OK).body(responseMap);
	}

}
