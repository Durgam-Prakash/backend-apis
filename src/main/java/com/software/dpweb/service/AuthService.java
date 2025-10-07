package com.software.dpweb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.software.dpweb.entity.User;
import com.software.dpweb.pojo.LoginAPIData;
import com.software.dpweb.pojo.SignupAPIData;
import com.software.dpweb.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
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
	
	
	
	
	
	public Object userLogIn(LoginAPIData loginAPIData) throws Exception {
		
		Optional<User> dbData = userRepository.findByEmail(loginAPIData.getEmail());
		
		if(dbData.isEmpty()) {
			throw new Exception("Email is not registred, Please Signup...!");
		}else {
			User userData = dbData.get();
			boolean isMatches = passwordEncoder.matches(loginAPIData.getPassword(), userData.getPassword());
			if(isMatches==true) {
				return userData;
			}else {
				throw new Exception("password is not matching..Please try again");
			}
		}
		
		
	}

}
