package com.software.dpweb.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.software.dpweb.utils.ResponseData;

import net.coobird.thumbnailator.Thumbnails;

@RestController
@RequestMapping("/image")
public class ImagesController {
	
	@PostMapping("/resize")
	public ResponseEntity<?> imageResize(@RequestParam("inp_file")MultipartFile inpFile) throws Exception{
		
		File tempFile = File.createTempFile("upload_", ".png");
		inpFile.transferTo(tempFile);
		
		Thumbnails.of(tempFile).size(68, 68).keepAspectRatio(false).toFile("uploads/resized_68x68.png");
		Thumbnails.of(tempFile).size(100, 100).keepAspectRatio(false).toFile("uploads/resized1_100x100.png");
		
		
		Map<String,Object> response = new HashMap<>();
		response.put(ResponseData.RESULT,ResponseData.SUCCESS);
		response.put(ResponseData.DATA, "Thumbnail is created");
		
		
		return  ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	
	@PostMapping("/reduce-size")
	public ResponseEntity<?> imageReduceSize(@RequestParam("inp_file")MultipartFile inpFile) throws Exception{
		
		ByteArrayOutputStream resizedArrayOutputStream = new ByteArrayOutputStream();
		
		Thumbnails.of(inpFile.getInputStream()).scale(1.0).outputQuality(0.1).toOutputStream(resizedArrayOutputStream);
		
		byte[] imageBytes = resizedArrayOutputStream.toByteArray(); 
		
		File tempfile = new File("uploads/low_quality_img.jpg");
		try(FileOutputStream fileOutputStream = new FileOutputStream(tempfile)){
			fileOutputStream.write(imageBytes);
		}catch (Exception e) {
			
		}
		
		
		
		Map<String,Object> response = new HashMap<>();
		response.put(ResponseData.RESULT,ResponseData.SUCCESS);
		response.put(ResponseData.DATA, "image size is Reduced");
		
		
		return  ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	
	

}
