package com.software.dpweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.dpweb.entity.Vehicle;
import com.software.dpweb.pojo.VehiclesSearchApiData;
import com.software.dpweb.repository.VehiclesJDBCRepository;

@Service
public class VehiclesService {
	
	
	@Autowired
	private VehiclesJDBCRepository vehiclesJDBCRepository;
	
	public List<Vehicle> hanldeVehiclesSearch(VehiclesSearchApiData vehiclesSearchApiData) {
	
		return vehiclesJDBCRepository.dbVehiclesSearch(vehiclesSearchApiData.getYear(),vehiclesSearchApiData.getBrand(),vehiclesSearchApiData.getModel());
	}

}
