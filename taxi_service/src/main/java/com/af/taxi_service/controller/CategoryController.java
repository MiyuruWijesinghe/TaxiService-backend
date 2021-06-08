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
import com.af.taxi_service.model.Category;
import com.af.taxi_service.resource.CategoryAddResource;
import com.af.taxi_service.resource.SuccessAndErrorDetailsResource;
import com.af.taxi_service.service.CategoryService;

@RestController
@RequestMapping(value = "/category")
@CrossOrigin(origins = "*")
public class CategoryController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private CategoryService categoryService;


	/**
	 * Gets the all categories.
	 *
	 * @return the all categories
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllCategories() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Category> category = categoryService.findAll();
		if (!category.isEmpty()) {
			return new ResponseEntity<>((Collection<Category>) category, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the category by id.
	 *
	 * @param id - the id
	 * @return the category by id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getCategoryById(@PathVariable(value = "id", required = true) int id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Category> isPresentCategory = categoryService.findById(id);
		if (isPresentCategory.isPresent()) {
			return new ResponseEntity<>(isPresentCategory, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Adds the category.
	 *
	 * @param categoryAddResource - the category add resource
	 * @return the response entity
	 */
	@PostMapping(value = "/add")
	public ResponseEntity<Object> addCategory(@RequestBody CategoryAddResource categoryAddResource) {
		Integer categoryId = categoryService.saveCategory(categoryAddResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource("Successfully Added.", categoryId.toString());
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
}
