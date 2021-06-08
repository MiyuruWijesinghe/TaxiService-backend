package com.af.taxi_service.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.af.taxi_service.model.VehicleCategoryMapping;
import com.af.taxi_service.repository.VehicleCategoryMappingRepository;
import com.af.taxi_service.resource.CalculateChargeRequestResource;
import com.af.taxi_service.resource.VehicleCategoryMappingUpdateResource;
import com.af.taxi_service.service.VehicleCategoryMappingService;

@Component
public class VehicleCategoryMappingServiceImpl implements VehicleCategoryMappingService {
	
	@Autowired
	private VehicleCategoryMappingRepository vehicleCategoryMappingRepository;
	
	
	@Override
	public List<VehicleCategoryMapping> findAll() {
		return vehicleCategoryMappingRepository.findAll();
	}

	@Override
	public Optional<VehicleCategoryMapping> findById(int id) {
		Optional<VehicleCategoryMapping> vehicleCategoryMapping = vehicleCategoryMappingRepository.findById(id);
		if (vehicleCategoryMapping.isPresent()) {
			return Optional.ofNullable(vehicleCategoryMapping.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<VehicleCategoryMapping> findByCategoryIdAndVehicleId(int categoryId, int vehicleId) {
		Optional<VehicleCategoryMapping> vehicleCategoryMapping = vehicleCategoryMappingRepository.findByCategorysIdAndVehiclesId(categoryId, vehicleId);
		if (vehicleCategoryMapping.isPresent()) {
			return Optional.ofNullable(vehicleCategoryMapping.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<VehicleCategoryMapping> findByCategoryId(int categoryId) {
		return vehicleCategoryMappingRepository.findByCategorysId(categoryId);
	}

	@Override
	public List<VehicleCategoryMapping> findByVehicleId(int vehicleId) {
		return vehicleCategoryMappingRepository.findByVehiclesId(vehicleId);
	}

	@Override
	public VehicleCategoryMapping updateVehicleCategoryMapping(int id, VehicleCategoryMappingUpdateResource vehicleCategoryMappingUpdateResource) {
		
		Optional<VehicleCategoryMapping> isPresentVehicleCategoryMapping = vehicleCategoryMappingRepository.findById(id);
		
		if(isPresentVehicleCategoryMapping.isPresent()) {
			VehicleCategoryMapping vehicleCategoryMapping = isPresentVehicleCategoryMapping.get();
			vehicleCategoryMapping.setChargeForKM(new BigDecimal(vehicleCategoryMappingUpdateResource.getChargeForKM()));
			vehicleCategoryMapping.setKilometersNumber(Integer.parseInt(vehicleCategoryMappingUpdateResource.getKilometersNumber()));
			vehicleCategoryMappingRepository.save(vehicleCategoryMapping);
			return vehicleCategoryMapping;
		}
		
		return null;
	}

	@Override
	public String calculateTripCharge(int id, CalculateChargeRequestResource calculateChargeRequestResource) {
		
		BigDecimal chargeForKM = BigDecimal.ZERO;
		Integer numOfKM = 0;
		BigDecimal chargePerKM = BigDecimal.ZERO;
		BigDecimal duration = BigDecimal.ZERO;
		BigDecimal tripCharge = BigDecimal.ZERO;
		
		Optional<VehicleCategoryMapping> isPresentVehicleCategoryMapping = vehicleCategoryMappingRepository.findById(id);
		
		if(isPresentVehicleCategoryMapping.isPresent()) {
			if(isPresentVehicleCategoryMapping.get().getChargeForKM() != null) {
				chargeForKM = isPresentVehicleCategoryMapping.get().getChargeForKM();
			}
			
			if(isPresentVehicleCategoryMapping.get().getKilometersNumber() != null) {
				numOfKM = isPresentVehicleCategoryMapping.get().getKilometersNumber();
			}
			duration = new BigDecimal(calculateChargeRequestResource.getDuration());
			
			if (isPresentVehicleCategoryMapping.get().getChargeForKM() != null && isPresentVehicleCategoryMapping.get().getKilometersNumber() != null) {
				chargePerKM = chargeForKM.divide(new BigDecimal(numOfKM));
			}
			
			tripCharge = chargePerKM.multiply(duration);
		}	
		
		return "Trip charge is Rs. " + tripCharge.toString();
	}

	
}
