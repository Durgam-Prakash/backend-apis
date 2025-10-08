package com.software.dpweb.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResetPassword {
	
	@NotNull(message = "password rest key is required")
	private String linkId;
	
	@NotNull(message = "password required")
	@NotBlank(message = "password required")
	private String password;
	
	@NotNull(message = "confirm password required")
	@NotBlank(message = "confirm password required")
	private String confirmPassword;

}
