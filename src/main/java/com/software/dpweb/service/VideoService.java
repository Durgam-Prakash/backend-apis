package com.software.dpweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.dpweb.entity.Video;
import com.software.dpweb.pojo.VideoSearchApiData;
import com.software.dpweb.repository.VideoRepository;

@Service
public class VideoService {

	@Autowired
	private VideoRepository videoRepository;
	
	
	public List<Video> videoSearch(VideoSearchApiData videoSearchApiData) {
		List<Video> searchVideos = videoRepository.searchVideos(videoSearchApiData.getSearchWord());
		return searchVideos;
	
		
	}
	
}
