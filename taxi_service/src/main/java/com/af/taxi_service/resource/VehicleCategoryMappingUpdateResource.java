package com.af.taxi_service.resource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class VehicleCategoryMappingUpdateResource {

	private String chargeForKM;
	
	private String kilometersNumber;

	public String getChargeForKM() {
		return chargeForKM;
	}

	public void setChargeForKM(String chargeForKM) {
		this.chargeForKM = chargeForKM;
	}

	public String getKilometersNumber() {
		return kilometersNumber;
	}

	public void setKilometersNumber(String kilometersNumber) {
		this.kilometersNumber = kilometersNumber;
	}
	
}
