package com.af.taxi_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.af.taxi_service.model.Category;


@Repository
public interface CategoryRepository extends MongoRepository<Category, Integer> {

}
