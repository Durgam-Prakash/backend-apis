package com.software.dpweb.convertors;

import com.software.dpweb.dto.VideoDto;
import com.software.dpweb.entity.Video;

public class VideoConvertor {
	
	public static  VideoDto convertVideoToVideoDto(Video video) {
		
		VideoDto videoDto = new VideoDto();
		videoDto.setId(video.getId());
		videoDto.setTitle(video.getTitle());
		videoDto.setDescription(video.getDescription());
		return videoDto;
	}

}
