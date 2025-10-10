package com.software.dpweb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.dpweb.entity.Vehicle;
import com.software.dpweb.pojo.VehiclesSearchApiData;
import com.software.dpweb.service.VehiclesService;
import com.software.dpweb.utils.ResponseData;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vehicle")
public class VehiclesController {
	
	
	@Autowired
	private VehiclesService vehiclesService;

	@PostMapping("/search")
	public ResponseEntity<?> searchVehicles(@Valid @RequestBody VehiclesSearchApiData vehiclesSearchApiData){
		
		List<Vehicle> hanldeVehiclesSearch = vehiclesService.hanldeVehiclesSearch(vehiclesSearchApiData);
		
		Map<String, Object> response = new HashMap<>();
		response.put(ResponseData.RESULT, ResponseData.SUCCESS);
		response.put(ResponseData.MESSAGE, "Vehicles Details are");
		response.put(ResponseData.DATA, hanldeVehiclesSearch);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
}
