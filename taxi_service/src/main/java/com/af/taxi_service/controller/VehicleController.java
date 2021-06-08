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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.af.taxi_service.model.Vehicle;
import com.af.taxi_service.resource.SuccessAndErrorDetailsResource;
import com.af.taxi_service.resource.VehicleAddResource;
import com.af.taxi_service.service.VehicleService;


@RestController
@RequestMapping(value = "/vehicle")
@CrossOrigin(origins = "*")
public class VehicleController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private VehicleService vehicleService;


	/**
	 * Gets the all vehicles.
	 *
	 * @return the all vehicles
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllVehicles() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Vehicle> vehicle = vehicleService.findAll();
		if (!vehicle.isEmpty()) {
			return new ResponseEntity<>((Collection<Vehicle>) vehicle, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the vehicle by id.
	 *
	 * @param id - the id
	 * @return the vehicle by id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getVehicleById(@PathVariable(value = "id", required = true) int id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Vehicle> isPresentVehicle = vehicleService.findById(id);
		if (isPresentVehicle.isPresent()) {
			return new ResponseEntity<>(isPresentVehicle, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Adds the vehicle.
	 *
	 * @param vehicleAddResource - the vehicle add resource
	 * @return the response entity
	 */
	@PostMapping(value = "/add")
	public ResponseEntity<Object> addVehicle(@RequestBody VehicleAddResource vehicleAddResource) {
		Integer vehicleId = vehicleService.saveVehicle(vehicleAddResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource("Successfully Added.", vehicleId.toString());
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
}