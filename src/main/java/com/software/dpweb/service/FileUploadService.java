package com.software.dpweb.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	@Value("${file.upload.images.path}")
	String IMAGE_UPLOAD_PATH;
	
	
	@Value("${file.upload.pdfs.path}")
	String PDF_UPLOAD_PATH;
	
	private int MAX_ALLOWED_IMAGE_SIZE = 5 * 1024 * 1024;
	private int MAX_ALLOWED_PDF_SIZE = 10 * 1024 * 1024;
	
	@Autowired
	private S3FileService s3FileService;
	
	public void imageUpload(MultipartFile inputFile) throws Exception {
		
		String fileName = StringUtils.cleanPath(inputFile.getOriginalFilename());
		String fileType = StringUtils.getFilenameExtension(fileName);
		
		
		String[] allowedFileTypes= {"png","jpeg","jpg","gif"};
		Boolean isFileTypeAllowed = Arrays.stream(allowedFileTypes).anyMatch(fileType::equals);
		
		if(isFileTypeAllowed==false) {
			throw new Exception(fileType + " file is not allowed");
		}
		
		if(inputFile.getSize()> MAX_ALLOWED_IMAGE_SIZE) {
			throw new Exception("Max 5MB Allowed");
		}
		
		
		String uploadImageName = UUID.randomUUID().toString() + " ." + fileType;
		
		Path uploadPath = Paths.get(IMAGE_UPLOAD_PATH +uploadImageName);
		Files.copy(inputFile.getInputStream(), uploadPath);
		
		
	}
	
	
	public void pdfUploads(MultipartFile inputFile) throws Exception {
		
		String fileName = StringUtils.cleanPath(inputFile.getOriginalFilename());
		String fileType = StringUtils.getFilenameExtension(fileName);
		
		if(fileType.equals("pdf")==false) {
			throw new Exception(fileType +" file is not allowed");
		}
		
		if(inputFile.getSize()> MAX_ALLOWED_PDF_SIZE) {
			throw new Exception("Max 10MB Allowed");
		}
		
		String uploadPdfFileName = UUID.randomUUID().toString() + " .pdf"  ;
		
		Path uploadPath = Paths.get(PDF_UPLOAD_PATH +uploadPdfFileName);
		Files.copy(inputFile.getInputStream(), uploadPath);
		//s3FileService.uploadFileToS3(inputFile, uploadPdfFileName);
		
	}

}
