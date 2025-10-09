package com.software.dpweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.software.dpweb.entity.Product;
import com.software.dpweb.repository.ProductRepository;

@Service
public class CsvToDbBatchProcess {

	@Autowired
	private ProductRepository productRepository;
	
	@Async("csvAsyncConfig")
	public void processBatchUpload(List<Product> productsList) {
		
		try {
			System.out.println("inside process batchupload : " + Thread.currentThread().getName());
			productRepository.saveAll(productsList);
		} catch (Exception e) {
			
			System.out.println("inside exception : " + e.getMessage());
			System.out.println("inside exception : " + productsList);
			
		}
	}
}
