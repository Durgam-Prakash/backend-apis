package com.software.dpweb.pojo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ForgotPasswordAPIData {
	
	@NotNull(message = "email required")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|in|org|net)$",
    message = "Email must end with .com, .in, .org, or .net")
	private String email;

}
