package com.software.dpweb.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error->{
			errors.put(error.getField(), error.getDefaultMessage());
			
			});
		
		Map<String, Object> response = new HashMap<>();
		response.put("Message", "Unable to Process Your Request");
		response.put("Status", "Failed");
		response.put("Errors", errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGenericException(Exception ex){
		
		Map<String, String> response = new HashMap<>();
		response.put("Result", "Failed");
		response.put("Message", ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		
	}
	
	
	
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<?> handleHttpClientErrorException(HttpClientErrorException ex){
		
		Map<String, String> response = new HashMap<>();
		response.put("Result", "Failed");
		response.put("Message", ex.getMessage());
		return ResponseEntity.status(ex.getStatusCode()).body(response);
		
	}
	
}
