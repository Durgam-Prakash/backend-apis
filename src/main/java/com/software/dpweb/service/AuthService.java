package com.software.dpweb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.dpweb.entity.User;
import com.software.dpweb.pojo.SignupAPIData;
import com.software.dpweb.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public Object createAccount(SignupAPIData signupAPIData) throws Exception {
		
		Optional<User> dbData = userRepository.findByEmail(signupAPIData.getEmail());
		
		if(dbData.isEmpty()) {
			User user = new User();
			user.setName(signupAPIData.getName());
			user.setEmail(signupAPIData.getEmail());
			user.setPassword(signupAPIData.getPassword());
			user.setMobile(signupAPIData.getMobile());
			User dbUserData = userRepository.save(user);
			return dbUserData;
		}
		else{
			throw new Exception("User already Exist...!");
		}
		
		
		
		
	}

}
