package com.software.dpweb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.software.dpweb.service.FileUploadService;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {
	
	@Autowired
	private FileUploadService fileUploadService;

	@PostMapping("/images")
	public ResponseEntity<?> uploadImages(@RequestParam("input_file") MultipartFile inputFile) throws Exception{
		
		
		fileUploadService.imageUpload(inputFile);
		
		Map<String,Object> responseMap = new HashMap<>();
		responseMap.put("Result", "Success");
		responseMap.put("Message", "image uploaded Successfully....!");
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);

		
		
	}
	
	
	
	
	@PostMapping("/pdfs")
	public ResponseEntity<?> uploadpdfs(@RequestParam("input_file") MultipartFile file) throws Exception{
		
		fileUploadService.pdfUploads(file);
		Map<String,Object> responseMap = new HashMap<>();
		responseMap.put("Result", "Success");
		responseMap.put("Message", "pdf file uploaded Successfully....!");
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);

		
		
	}
	
}
