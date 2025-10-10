package com.software.dpweb.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VideoSearchApiData {
	
	@NotNull(message = "seach word required")
	@NotBlank(message = "seach word should not be blank")
	private String searchWord;

}
