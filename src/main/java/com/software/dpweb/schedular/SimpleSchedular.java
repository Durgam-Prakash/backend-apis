package com.software.dpweb.schedular;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.software.dpweb.service.S3FileService;

@Component
public class SimpleSchedular {
	
	@Autowired
	private S3FileService s3FileService;
	
	
	@Scheduled(fixedRate = 5000)
	public void printSomeData() {
		System.out.println("this is running from schedualr........!");
	}
	
	
	//cron - "seconds(0-59), minutes(0-59), hours(0-23), date(0-30),month(0-11), week-day(SUN , MON)"
	
//	@Scheduled(cron = "0 0 1 * * *")
	@Scheduled(fixedRate = 1000000)
	public void uploadLogsToS3() throws Exception {
		
		/*
		 * logs directory
		 * get all files from log folder
		 * loop -> upload each file
		 * 
		 */
		
		System.out.println("uploading files to AWS S3");
		
		String logsFolder = System.getProperty("user.dir") + "/logs";
		
		File logsDirectory =new File(logsFolder);
		
		File[] listLogFiles = logsDirectory.listFiles();
		
		for(File logFile :listLogFiles) {
			s3FileService.uploadLogFilesToS3(logFile, "logs/"+logFile.getName());
			System.out.println("uploaded : logs/" + logFile.getName());
		}
	}
	

}
