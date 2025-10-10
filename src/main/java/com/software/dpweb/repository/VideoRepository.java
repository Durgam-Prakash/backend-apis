package com.software.dpweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.software.dpweb.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {


	@Query("select v from Video v where v.title like %:searchWord% ")
	List<Video> searchVideos(@Param("searchWord") String searchWord);
	
//
//	@Query("SELECT v FROM Video v WHERE v.title LIKE CONCAT('%', :searchWord, '%')")
//	List<Video> searchVideos(@Param("searchWord") String searchWord);




}
