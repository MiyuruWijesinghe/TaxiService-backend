package com.af.taxi_service.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.af.taxi_service.model.Vehicle;
import com.af.taxi_service.resource.VehicleAddResource;

@Service
public interface VehicleService {

	public List<Vehicle> findAll();
	
	public Optional<Vehicle> findById(int id);
	
	public Integer saveVehicle(VehicleAddResource vehicleAddResource);
	
}