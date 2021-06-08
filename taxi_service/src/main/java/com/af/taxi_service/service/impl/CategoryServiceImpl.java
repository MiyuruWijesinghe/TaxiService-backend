package com.af.taxi_service.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.af.taxi_service.model.Category;
import com.af.taxi_service.repository.CategoryRepository;
import com.af.taxi_service.resource.CategoryAddResource;
import com.af.taxi_service.service.CategoryService;
import com.af.taxi_service.util.IdGenerator;

@Component
public class CategoryServiceImpl implements CategoryService {

	
	@Autowired
	private CategoryRepository categoryRepository;
	
	private int generateId() {
		List<Category> categoryList = categoryRepository.findAll();
		List<Integer> categoryIdList = new ArrayList<>();
		
		for(Category categoryObject : categoryList) {
			categoryIdList.add(categoryObject.getId());
		}
		
		return IdGenerator.generateIDs(categoryIdList);	
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Optional<Category> findById(int id) {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			return Optional.ofNullable(category.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Integer saveCategory(CategoryAddResource categoryAddResource) {
		Category category = new Category();
		category.setId(generateId());
		category.setName(categoryAddResource.getName());
		categoryRepository.save(category);
		return category.getId();
	}
		
}
