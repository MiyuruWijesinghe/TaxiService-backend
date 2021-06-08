package com.af.taxi_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.af.taxi_service.model.VehicleCategoryMapping;


@Repository
public interface VehicleCategoryMappingRepository extends MongoRepository<VehicleCategoryMapping, Integer> {

	public Optional<VehicleCategoryMapping> findByCategorysIdAndVehiclesId(int categoryId, int vehicleId);

	public List<VehicleCategoryMapping> findByCategorysId(int categoryId);

	public List<VehicleCategoryMapping> findByVehiclesId(int vehicleId);

}