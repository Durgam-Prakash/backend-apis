package com.software.dpweb.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.software.dpweb.pojo.LoginAPIData;
import com.software.dpweb.service.AuthService;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AuthService authService;
	
	
	
	@Test
	public void testEmailRequiredLogin() throws Exception {
		mockMvc.perform(
				post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"password\":\"123456\"}")
				).andExpect(status().isBadRequest())
		         .andExpect(jsonPath("$.Errors.email").value("email should not be blank"));
		
	}

	
	
	@Test
	public void testPasswordRequiredLogin()throws Exception{
		mockMvc.perform(post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"demo123@gmail.com\"}")
				).andExpect(status().isBadRequest())
		         .andExpect(jsonPath("$.Errors.password").value("please enter your password"));
		         
	}
	
	
	@Test
	public void testEmailPasswordBothRequiredLogin()throws Exception{
		
		mockMvc.perform(
				post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
//				.content("{\"email\":\"demo123@gmail.com\",\"password\":\"123456\"}")
				.content("{}")
				).andExpect(status().isBadRequest())
		 .andExpect(jsonPath("$.Errors.email").value("email should not be blank"))
		 .andExpect(jsonPath("$.Errors.password").value("please enter your password"));
		
	}
	
	
	@Test
	public void testLoginSuccess() throws Exception {
		
		LoginAPIData mockLoginAPIData = new LoginAPIData();
		mockLoginAPIData.setEmail("demo123@gmail.com");
		mockLoginAPIData.setPassword("123456");
		
		Map<String, Object> response = new HashMap<>();
		response.put("token", "fdfdfdsf");
		response.put("userData", "");
		
		Mockito.when(authService.userLogIn(mockLoginAPIData)).thenReturn(response);
		
		mockMvc.perform(
				post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"demo123@gmail.com\",\"password\":\"123456\"}")
				).andExpect(status().isOk());
	}
	
	
	@Test
	public void testEmailNotRegistred() throws Exception {
		
		LoginAPIData mockLoginAPIData = new LoginAPIData();
		mockLoginAPIData.setEmail("demo12333@gmail.com");
		mockLoginAPIData.setPassword("123456");
		

		
		Mockito.when(authService.userLogIn(mockLoginAPIData)).thenThrow(new Exception("Email is not registred, Please Signup...!"));
		
		mockMvc.perform(
				post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"demo12333@gmail.com\",\"password\":\"123456\"}")
				).andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.Message").value("Email is not registred, Please Signup...!"));
	}
	
	
	@Test
	public void testPasswordNotMatching() throws Exception {
		
		LoginAPIData mockLoginAPIData = new LoginAPIData();
		mockLoginAPIData.setEmail("demo12333@gmail.com");
		mockLoginAPIData.setPassword("123456");
		

		
		Mockito.when(authService.userLogIn(mockLoginAPIData)).thenThrow(new Exception("password is not matching..Please try again"));
		
		mockMvc.perform(
				post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"demo12333@gmail.com\",\"password\":\"123456\"}")
				).andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.Message").value("password is not matching..Please try again"));
	}
	
}
