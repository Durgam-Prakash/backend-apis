package com.software.dpweb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "videos")
public class Video {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "video_id") 
	private Long id;
	
	private String title;
	private String description;
	private String videoPath;
	private int channelId;
	private int noOfLikes;
	private int noOfDisLikes;
	private String thumbnailPath;
	

}
