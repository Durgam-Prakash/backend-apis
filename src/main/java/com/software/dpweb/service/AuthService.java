package com.software.dpweb.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.software.dpweb.entity.User;
import com.software.dpweb.pojo.ForgotPasswordAPIData;
import com.software.dpweb.pojo.LoginAPIData;
import com.software.dpweb.pojo.ResetPassword;
import com.software.dpweb.pojo.SignupAPIData;
import com.software.dpweb.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private JwtService jwtService;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public Object createAccount(SignupAPIData signupAPIData) throws Exception {
		
		Optional<User> dbData = userRepository.findByEmail(signupAPIData.getEmail());
		
		if(dbData.isEmpty()) {
			User user = new User();
			user.setName(signupAPIData.getName());
			user.setEmail(signupAPIData.getEmail());
			user.setPassword(passwordEncoder.encode(signupAPIData.getPassword()));
			user.setMobile(signupAPIData.getMobile());
			User dbUserData = userRepository.save(user);
			return dbUserData;
		}
		else{
			throw new Exception("User already Exist...!");
		}
		
		
		
		
	}
	
	
	
	
	
	public Map<String, Object> userLogIn(LoginAPIData loginAPIData) throws Exception {
		
		Optional<User> dbData = userRepository.findByEmail(loginAPIData.getEmail());
		
		if(dbData.isEmpty()) {
			throw new Exception("Email is not registred, Please Signup...!");
		}else {
			User userData = dbData.get();
			boolean isMatches = passwordEncoder.matches(loginAPIData.getPassword(), userData.getPassword());
			
			if(isMatches == true) {
				String jwtToken = jwtService.generateJwtToken(userData);
				Map<String, Object> response = new HashMap<>();
				response.put("token", jwtToken);
				response.put("userData", userData);
				return response;
			}else {
				throw new Exception("password is not matching..Please try again");
			}
		}
		
		
	}
	
	
	
	public void forgotPassword(ForgotPasswordAPIData forgotPasswordAPIData) throws Exception {
		
		Optional<User> dbData = userRepository.findByEmail(forgotPasswordAPIData.getEmail());
		
		if(dbData.isEmpty()) {
			throw new Exception("email is not registred...");
		}else {
			System.out.println(dbData.get());
			String passwordResetKey = UUID.randomUUID().toString();
			User userData = dbData.get();
			userData.setPasswordResetKey(passwordResetKey);
			userRepository.save(userData);
			String mailBody="Hi" + userData.getName()+"," 
					+ "please find the below link to reset your password."
					+ "password reset link : <a href='http://localhost:8081/password-reset-ui?linkid="+passwordResetKey +"'+>Click Here</a> <br/>"
					+ "<b>Regards"
					+ "<br/> Springboot </b>";
			emailService.sendHtmlMail("durgamprakash10@gmail.com", userData.getEmail(), "Password reset Link", mailBody);
		}
		
	}
	
	
	
	public void resetPassword(ResetPassword resetPassword) throws Exception {
		if(resetPassword.getPassword().equals(resetPassword.getConfirmPassword())==false) {
			throw new Exception("password and confirm password is not maching");
			
		}
		
		Optional<User> dbData = userRepository.findByPasswordResetKey(resetPassword.getLinkId());
		if(dbData.isEmpty()) {
			throw new Exception("Invalid password rest key or expired");
		}
		System.out.println(dbData.get());
		User usrData = dbData.get();
		usrData.setPassword(passwordEncoder.encode(resetPassword.getPassword()));
		usrData.setPasswordResetKey("");
		userRepository.save(usrData);
		
		
	}
	

}
