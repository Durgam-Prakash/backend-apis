package com.software.dpweb.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SignupAPIData {

	@NotNull(message = " name should not be null")
	@NotBlank(message = "please enter your name....")
	private String name;
	
	@NotNull(message = "email should not be null")
	@NotBlank(message = "email should not be blank")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|in|org|net)$",
	        message = "Email must end with .com, .in, .org, or .net")
	private String email;
	
	@NotNull(message = "password should not be null")
	@NotBlank(message = "please enter your password")
	private String password;
	
	@NotNull(message = "mobile should not be null")
	@NotBlank(message = "Please enter you mobile number")
	
	private String mobile;
	
}
