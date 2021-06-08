package com.af.taxi_service.model;

import java.math.BigDecimal;
import javax.persistence.Transient;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "VehicleCategoryMapping")
public class VehicleCategoryMapping {

	@Id
	private Integer id;
	
	@JsonIgnore
	private Category categorys;
	
	@Transient
    private Integer categoryId;
	
	@Transient
    private String categoryName;
	
	@JsonIgnore
	private Vehicle vehicles;
	
	@Transient
    private Integer vehicleId;
	
	@Transient
    private String vehicleName;
	
	private BigDecimal chargeForKM;
	
	private Integer kilometersNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategorys() {
		return categorys;
	}

	public void setCategorys(Category categorys) {
		this.categorys = categorys;
	}

	public Vehicle getVehicles() {
		return vehicles;
	}

	public void setVehicles(Vehicle vehicles) {
		this.vehicles = vehicles;
	}

	public BigDecimal getChargeForKM() {
		return chargeForKM;
	}

	public void setChargeForKM(BigDecimal chargeForKM) {
		this.chargeForKM = chargeForKM;
	}

	public Integer getKilometersNumber() {
		return kilometersNumber;
	}

	public void setKilometersNumber(Integer kilometersNumber) {
		this.kilometersNumber = kilometersNumber;
	}

	public Integer getCategoryId() {
		if(categorys != null) {
			return categorys.getId();
		} else {
			return null;
		}
	}

	public String getCategoryName() {
		if(categorys != null) {
			return categorys.getName();
		} else {
			return null;
		}
	}

	public Integer getVehicleId() {
		if(vehicles != null) {
			return vehicles.getId();
		} else {
			return null;
		}
	}

	public String getVehicleName() {
		if(vehicles != null) {
			return vehicles.getName();
		} else {
			return null;
		}
	}
	
}
