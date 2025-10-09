package com.software.dpweb.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.software.dpweb.entity.Product;

@Service
public class CsvToDbService {

	
	
	@Autowired
	private CsvToDbBatchProcess csvToDbBatchProcess;

	public void handleCsvUploadToDb(MultipartFile inputCsvFile) throws Exception {

		String fileName = StringUtils.cleanPath(inputCsvFile.getOriginalFilename());
		String fileExtension = StringUtils.getFilenameExtension(fileName);
		System.out.println(fileExtension);

		if (fileExtension.equals("csv") == false) {
			throw new Exception("Please Upload Csv File");
		}

		Reader reader = new BufferedReader(new InputStreamReader(inputCsvFile.getInputStream()));
		CSVReader csvReader = new CSVReader(reader);



		String[] csvRow = csvReader.readNext();
		csvRow = csvReader.readNext();

		List<Product> productsList = new ArrayList<>();

		while (csvRow != null) {
			System.out.println(Arrays.toString(csvRow));
			Product product = new Product();
			product.setCode(csvRow[0]);
			product.setDiscription(csvRow[1]);
			product.setUnit(csvRow[2]);
			product.setCaseNumber(csvRow[3]);

			product.setAnalysis(csvRow[4]);
			product.setAccurate(csvRow[5]);
			product.setAqua(csvRow[6]);
			product.setAgain(csvRow[7]);
			product.setAllValue(csvRow[8]);
			product.setAcids(csvRow[9]);

			productsList.add(product);

			if (productsList.size() == 1000) {

				csvToDbBatchProcess.processBatchUpload(new ArrayList<>(productsList));
				productsList.clear();
			}

			csvRow = csvReader.readNext();
		}

		if (productsList.size() > 0) {
			csvToDbBatchProcess.processBatchUpload(productsList);
		}

		csvReader.close();
	}
	
	
	

}
