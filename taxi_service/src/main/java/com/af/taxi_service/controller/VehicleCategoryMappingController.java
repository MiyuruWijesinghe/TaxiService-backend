package com.af.taxi_service.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.af.taxi_service.model.VehicleCategoryMapping;
import com.af.taxi_service.resource.CalculateChargeRequestResource;
import com.af.taxi_service.resource.SuccessAndErrorDetailsResource;
import com.af.taxi_service.resource.VehicleCategoryMappingUpdateResource;
import com.af.taxi_service.service.VehicleCategoryMappingService;

@RestController
@RequestMapping(value = "/vehicles-categories")
@CrossOrigin(origins = "*")
public class VehicleCategoryMappingController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private VehicleCategoryMappingService vehicleCategoryMappingService;
	
	
	/**
	 * Gets the all vehicle category mappings.
	 *
	 * @return the all vehicle category mappings
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllVehicleCategoryMappings() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<VehicleCategoryMapping> vehicleCategoryMapping = vehicleCategoryMappingService.findAll();
		if (!vehicleCategoryMapping.isEmpty()) {
			return new ResponseEntity<>((Collection<VehicleCategoryMapping>) vehicleCategoryMapping, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the vehicle category mapping by id.
	 *
	 * @param id - the id
	 * @return the vehicle category mapping by id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getVehicleCategoryMappingById(@PathVariable(value = "id", required = true) int id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<VehicleCategoryMapping> isPresentVehicleCategoryMapping = vehicleCategoryMappingService.findById(id);
		if (isPresentVehicleCategoryMapping.isPresent()) {
			return new ResponseEntity<>(isPresentVehicleCategoryMapping, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the vehicle category mapping by vehicle id.
	 *
	 * @param vehicleId - the vehicle id
	 * @return the vehicle category mapping by vehicle id
	 */
	@GetMapping(value = "/vehicle/{vehicleId}")
	public ResponseEntity<Object> getVehicleCategoryMappingByVehicleId(@PathVariable(value = "vehicleId", required = true) int vehicleId) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<VehicleCategoryMapping> vehicleCategoryMapping = vehicleCategoryMappingService.findByVehicleId(vehicleId);
		if (!vehicleCategoryMapping.isEmpty()) {
			return new ResponseEntity<>((Collection<VehicleCategoryMapping>) vehicleCategoryMapping, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the vehicle category mapping by category id.
	 *
	 * @param categoryId - the category id
	 * @return the vehicle category mapping by category id
	 */
	@GetMapping(value = "/category/{categoryId}")
	public ResponseEntity<Object> getVehicleCategoryMappingByCategoryId(@PathVariable(value = "categoryId", required = true) int categoryId) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<VehicleCategoryMapping> vehicleCategoryMapping = vehicleCategoryMappingService.findByCategoryId(categoryId);
		if (!vehicleCategoryMapping.isEmpty()) {
			return new ResponseEntity<>((Collection<VehicleCategoryMapping>) vehicleCategoryMapping, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the vehicle category mapping by category id and vehicle id.
	 *
	 * @param categoryId - the category id
	 * @param vehicleId - the vehicle id
	 * @return the vehicle category mapping by category id and vehicle id
	 */
	@GetMapping(value = "/category/{categoryId}/vehicle/{vehicleId}")
	public ResponseEntity<Object> getVehicleCategoryMappingByCategoryIdAndVehicleId(@PathVariable(value = "categoryId", required = true) int categoryId,
			@PathVariable(value = "vehicleId", required = true) int vehicleId) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<VehicleCategoryMapping> isPresentVehicleCategoryMapping = vehicleCategoryMappingService.findByCategoryIdAndVehicleId(categoryId, vehicleId);
		if (isPresentVehicleCategoryMapping.isPresent()) {
			return new ResponseEntity<>(isPresentVehicleCategoryMapping, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Update vehicle category mapping.
	 *
	 * @param id - the id
	 * @param vehicleCategoryMappingUpdateResource - the vehicle category mapping update resource
	 * @return the response entity
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> updateVehicleCategoryMapping(@PathVariable(value = "id", required = true) int id,
			@RequestBody VehicleCategoryMappingUpdateResource vehicleCategoryMappingUpdateResource) {
		VehicleCategoryMapping vehicleCategoryMapping = vehicleCategoryMappingService.updateVehicleCategoryMapping(id, vehicleCategoryMappingUpdateResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource("Successfully Updated.", vehicleCategoryMapping.getId().toString());
		return new ResponseEntity<>(successDetailsDto, HttpStatus.OK);
	}
	
	
	/**
	 * Calculate trip charge.
	 *
	 * @param id - the id
	 * @param calculateChargeRequestResource - the calculate charge request resource
	 * @return the response entity
	 */
	@PostMapping(value = "/calculate/{id}")
	public ResponseEntity<Object> calculateTripCharge(@PathVariable(value = "id", required = true) int id,
			@RequestBody CalculateChargeRequestResource calculateChargeRequestResource) {
		String message = vehicleCategoryMappingService.calculateTripCharge(id, calculateChargeRequestResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(message);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
}
