package com.uts.asd.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.fabiomaffioletti.firebase.document.FirebaseDocument;
import com.github.fabiomaffioletti.firebase.document.FirebaseId;

@FirebaseDocument("/Property")
@JsonIgnoreProperties
public class Property {
	
	@FirebaseId
	private String PropertyID;
	@JsonProperty("Address")
	private String address;
	@JsonProperty("Bedroom")
	private Integer bedroom;
	@JsonProperty("Bathroom")
	private Integer bathroom;
	@JsonProperty("Garage")
	private Integer garage;
	
	public String getPropertyID() {
		return PropertyID;
	}

	public void setPropertyID(String propertyID) {
		PropertyID = propertyID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getBedroom() {
		return bedroom;
	}

	public void setBedroom(Integer bedroom) {
		this.bedroom = bedroom;
	}

	public Integer getBathroom() {
		return bathroom;
	}

	public void setBathroom(Integer bathroom) {
		this.bathroom = bathroom;
	}

	public Integer getGarage() {
		return garage;
	}

	public void setGarage(Integer garage) {
		this.garage = garage;
	}

	public Property(String propertyID) {
		super();
		PropertyID = propertyID;
	}

	public Property() {
		super();
	}
	
}
