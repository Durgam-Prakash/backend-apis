package com.software.dpweb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.software.dpweb.service.CsvToDbService;
import com.software.dpweb.service.FileUploadService;
import com.software.dpweb.service.JwtService;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private JwtService jwtService;
	
	
	@Autowired
	private CsvToDbService csvToDbService;

	@PostMapping("/images")
	public ResponseEntity<?> uploadImages(@RequestHeader("Authorization")String jwtToken, @RequestParam("input_file") MultipartFile inputFile) throws Exception{
		
		System.out.println(jwtToken);
		
		if(jwtToken==null || jwtToken.startsWith("Bearer")==false) {
			throw new Exception("Unauthorized.you are not allowed to do this operation");
			
		}
		System.out.println(jwtToken);
		
		jwtToken=jwtToken.substring(7);
		System.out.println(jwtToken);
		
		Boolean isTokenValid = jwtService.verifyJwtToken(jwtToken);
		System.out.println(isTokenValid);
		
		Claims claims = jwtService.getJwtClaims(jwtToken);
		System.out.println(claims);
		
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
	
	
	
	
	@PostMapping("/csvToDb")
	public ResponseEntity<?> uploadCsvToDb(@RequestParam("csv_file") MultipartFile inputCsvFile) throws Exception{
		
		csvToDbService.handleCsvUploadToDb(inputCsvFile);
		
		Map<String, Object> response = new HashMap<>();
		response.put("Result","Success");
		response.put("Message", "CSV file imported to DB ");
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
}
