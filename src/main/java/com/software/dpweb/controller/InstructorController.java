package com.software.dpweb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.dpweb.entity.Instructor;
import com.software.dpweb.service.InstructorService;
import com.software.dpweb.utils.ResponseData;

@RestController
@RequestMapping("/api")
public class InstructorController {
	
	@Autowired
	private InstructorService instructorService;
	
	@GetMapping("/instructor/{id}")
	public ResponseEntity<?>getInstructorData(@PathVariable Long id){
		Instructor instructorData = instructorService.getInstructorData(id);
		Map<String, Object> response = new HashMap<>();
		response.put(ResponseData.RESULT, ResponseData.SUCCESS);
		response.put(ResponseData.DATA, instructorData);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
