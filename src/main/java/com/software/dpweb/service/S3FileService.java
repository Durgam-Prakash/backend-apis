package com.software.dpweb.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3FileService {
//	@Value("${aws.accessKeyId}")
//	private String accessKeyId;
//	
//	@Value("${aws.secretKey}")
//	private String secretKey;
//	
//	
//	@Value("${aws.region}")
//	private String region;
	
	@Value("${aws.s3.bucket.name}")
	private String bucketName;

	
	private S3Client s3Client;
	
	public S3FileService(@Value("${aws.accessKeyId}") String accessKeyId,@Value("${aws.secretKey}")String secretKey,@Value("${aws.region}")String region) {
		
		AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(accessKeyId, secretKey);
		s3Client = S3Client.builder().region(Region.of(region)).credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials)).build();
		
		
		
	}
	
	
	public void uploadFileToS3(MultipartFile inputFile,String fileName) throws Exception {
		
		PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(fileName).build();
		s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputFile.getInputStream() , inputFile.getSize()));
		
	}

	
	
public void uploadLogFilesToS3(File inputFile,String fileName) throws Exception {
		
		PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(fileName).build();
		s3Client.putObject(putObjectRequest,inputFile.toPath() );
		
	}

}
