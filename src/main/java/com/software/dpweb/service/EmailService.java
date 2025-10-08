package com.software.dpweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	public Object sendPlainEmail(String fromEmail, String toEmail, String subject, String mailBody) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(fromEmail);
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(mailBody);
		
	    javaMailSender.send(message);
		return message;
	}
	
	
	
	
	public void sendHtmlMail(String fromEmail, String toEmail, String subject, String mailBody) throws Exception {
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
		
		helper.setFrom(fromEmail);
		helper.setTo(toEmail);
		helper.setSubject(subject);
		helper.setText(mailBody,true);
		
		javaMailSender.send(message);
			
	}
	
	
	
	public void sendHtmlTemplateMail(String fromEmail, String toEmail, String subject, String fileName) throws Exception {
		Context context = new Context();
		
		context.setVariable("title", "TEST");
	    context.setVariable("name", "Praksh");
	    context.setVariable("message", "Please check out this below link");  // can contain HTML, use th:utext
	    context.setVariable("ctaLink", "https://youtube.com");
	    context.setVariable("ctaText", "Youtube");

		
		String mailBody = templateEngine.process(fileName, context);
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
		
		helper.setFrom(fromEmail);
		helper.setTo(toEmail);
		helper.setSubject(subject);
		helper.setText(mailBody,true);
		
		javaMailSender.send(message);
		
	}
	
	
	
	

}
