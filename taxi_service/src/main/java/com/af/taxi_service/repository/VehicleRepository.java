package com.af.taxi_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.af.taxi_service.model.Vehicle;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, Integer> {

}