package com.software.dpweb.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.dpweb.convertors.VideoConvertor;
import com.software.dpweb.dto.VideoDto;
import com.software.dpweb.entity.Video;
import com.software.dpweb.pojo.VideoSearchApiData;
import com.software.dpweb.repository.VideoRepository;

@Service
public class VideoService {

	@Autowired
	private VideoRepository videoRepository;
	
	
	public List<VideoDto> videoSearch(VideoSearchApiData videoSearchApiData) {
		List<Video> searchVideos = videoRepository.searchVideos(videoSearchApiData.getSearchWord());
		System.out.println(searchVideos);
		
		Video video = searchVideos.get(0);
		System.out.println(video);
		
		
		VideoDto videoDto = VideoConvertor.convertVideoToVideoDto(video);
		
		System.out.println(videoDto);
		
		List<VideoDto> collect = searchVideos.stream().map(VideoConvertor::convertVideoToVideoDto).collect(Collectors.toList());
		System.out.println(collect);
		return collect;
	
		
	}
	
}
