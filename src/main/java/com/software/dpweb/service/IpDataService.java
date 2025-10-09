package com.software.dpweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.software.dpweb.pojo.IpData;

@Service
public class IpDataService {
	
	@Value("${ipinfo.token}")
	String ipInfoToken;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public IpData getIpDataFromIpInfo(String ipAddress) {
		
		try {
			ipAddress ="";
			System.out.println(ipAddress);
			
			
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			
			String inputData = "";
			
			HttpEntity<?> requestEntity = new HttpEntity<>(inputData,headers);
			
			String apiUrl ="https://ipinfo.io/"+ipAddress + "?" + ipInfoToken;
			ResponseEntity<IpData> response = restTemplate.exchange(apiUrl, HttpMethod.GET,requestEntity,IpData.class);
			IpData ipData = response.getBody();
			return ipData;
			
			
		} catch (HttpClientErrorException e) {
			
			throw new HttpClientErrorException(e.getStatusCode(),e.getMessage());
		}
	}

}
