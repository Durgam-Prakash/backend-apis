package com.software.dpweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.dpweb.entity.Instructor;
import com.software.dpweb.repository.InstructorRepository;

@Service
public class InstructorService {
	
	
	@Autowired
	private InstructorRepository instructorRepository;
	
	public Instructor getInstructorData(Long id) {
		
		Instructor instructorDataById = instructorRepository.getInstructorDataById(id);
		
		return instructorDataById;
	}

}
