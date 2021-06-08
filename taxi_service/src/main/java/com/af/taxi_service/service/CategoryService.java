package com.af.taxi_service.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.af.taxi_service.model.Category;
import com.af.taxi_service.resource.CategoryAddResource;

@Service
public interface CategoryService {

	public List<Category> findAll();
	
	public Optional<Category> findById(int id);
	
	public Integer saveCategory(CategoryAddResource categoryAddResource);
	
}
