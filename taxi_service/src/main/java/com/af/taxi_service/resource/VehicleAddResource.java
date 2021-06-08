package com.af.taxi_service.resource;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class VehicleAddResource {

	private String code;
	
	private String model;
	
	private String type;
	
	private String name;

	@Valid
	private List<VehicleCategoryMappingAddResource> categories;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<VehicleCategoryMappingAddResource> getCategories() {
		return categories;
	}

	public void setCategories(List<VehicleCategoryMappingAddResource> categories) {
		this.categories = categories;
	}
	
}
