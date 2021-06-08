package com.af.taxi_service.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.af.taxi_service.model.Category;
import com.af.taxi_service.model.Vehicle;
import com.af.taxi_service.model.VehicleCategoryMapping;
import com.af.taxi_service.repository.CategoryRepository;
import com.af.taxi_service.repository.VehicleCategoryMappingRepository;
import com.af.taxi_service.repository.VehicleRepository;
import com.af.taxi_service.resource.VehicleAddResource;
import com.af.taxi_service.resource.VehicleCategoryMappingAddResource;
import com.af.taxi_service.service.VehicleService;
import com.af.taxi_service.util.IdGenerator;

@Component
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private VehicleCategoryMappingRepository vehicleCategoryMappingRepository;
	
	private int generateVehicleId() {
		List<Vehicle> vehicleList = vehicleRepository.findAll();
		List<Integer> vehicleIdList = new ArrayList<>();
		
		for(Vehicle vehicleObject : vehicleList) {
			vehicleIdList.add(vehicleObject.getId());
		}
		
		return IdGenerator.generateIDs(vehicleIdList);	
	}
	
	private int generateVehicleCategoryMappingId() {
		List<VehicleCategoryMapping> vehicleCategoryList = vehicleCategoryMappingRepository.findAll();
		List<Integer> vehicleCategoryIdList = new ArrayList<>();
		
		for(VehicleCategoryMapping vehicleCategoryObject : vehicleCategoryList) {
			vehicleCategoryIdList.add(vehicleCategoryObject.getId());
		}
		
		return IdGenerator.generateIDs(vehicleCategoryIdList);	
	}
	
	@Override
	public List<Vehicle> findAll() {
		return vehicleRepository.findAll();
	}

	@Override
	public Optional<Vehicle> findById(int id) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(id);
		if (vehicle.isPresent()) {
			return Optional.ofNullable(vehicle.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Integer saveVehicle(VehicleAddResource vehicleAddResource) {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(generateVehicleId());
		vehicle.setName(vehicleAddResource.getName());
		vehicle.setCode(vehicleAddResource.getCode());
		vehicle.setModel(vehicleAddResource.getModel());
		vehicle.setType(vehicleAddResource.getType());
		vehicleRepository.save(vehicle);
		
		if(vehicleAddResource.getCategories() !=null && !vehicleAddResource.getCategories().isEmpty()) {
			
			for (VehicleCategoryMappingAddResource vehicleCategoryMappingAddResource : vehicleAddResource.getCategories()) {
				
				VehicleCategoryMapping vehicleCategoryMapping = new VehicleCategoryMapping();
				vehicleCategoryMapping.setId(generateVehicleCategoryMappingId());
				vehicleCategoryMapping.setVehicles(vehicle);
				
				Optional<Category> isPresentCategory = categoryRepository.findById(Integer.parseInt(vehicleCategoryMappingAddResource.getCategoryId()));
				if (isPresentCategory.isPresent()) {
					vehicleCategoryMapping.setCategorys(isPresentCategory.get());
				}
				vehicleCategoryMapping.setChargeForKM(BigDecimal.ZERO);
				vehicleCategoryMapping.setKilometersNumber(0);
				vehicleCategoryMappingRepository.save(vehicleCategoryMapping);
			}
		}
		
		return vehicle.getId();
	}

}
