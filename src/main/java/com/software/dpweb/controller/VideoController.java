package com.software.dpweb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.dpweb.entity.Video;
import com.software.dpweb.pojo.VideoSearchApiData;
import com.software.dpweb.service.VideoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/video")
public class VideoController {
	
	@Autowired
	private VideoService videoService;
	
	@PostMapping("/search")
	public ResponseEntity<?> videoSearch(@Valid @RequestBody VideoSearchApiData videoSearchApiData){
		
		List<Video> videoSearch = videoService.videoSearch(videoSearchApiData);
		Map<String, Object> response = new HashMap<>();
		response.put("Result", "Success");
		response.put("Message", "video Detaisl");
		response.put("Data", videoSearch);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
