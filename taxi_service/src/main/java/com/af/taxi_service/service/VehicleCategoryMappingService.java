package com.af.taxi_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.af.taxi_service.model.VehicleCategoryMapping;
import com.af.taxi_service.resource.CalculateChargeRequestResource;
import com.af.taxi_service.resource.VehicleCategoryMappingUpdateResource;

@Service
public interface VehicleCategoryMappingService {

	public List<VehicleCategoryMapping> findAll();

	public Optional<VehicleCategoryMapping> findById(int id);
	
	public Optional<VehicleCategoryMapping> findByCategoryIdAndVehicleId(int categoryId, int vehicleId);
	
	public List<VehicleCategoryMapping> findByCategoryId(int categoryId);
	
	public List<VehicleCategoryMapping> findByVehicleId(int vehicleId);

	public VehicleCategoryMapping updateVehicleCategoryMapping(int id, VehicleCategoryMappingUpdateResource vehicleCategoryMappingUpdateResource);
	
	public String calculateTripCharge(int id, CalculateChargeRequestResource calculateChargeRequestResource);
	
}
