package com.uts.asd.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.fabiomaffioletti.firebase.document.FirebaseDocument;
import com.github.fabiomaffioletti.firebase.document.FirebaseId;

@FirebaseDocument("/CustomerService")
@JsonIgnoreProperties
public class CustomerServiceEntity {
	
	@FirebaseId
	private Integer id;
	@JsonProperty("problem")
	private String problem;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@JsonIgnore	
	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}
	
	public CustomerServiceEntity(Integer id, String problem) {
		super();
		this.id = id;
		this.problem = problem;
	}

	public CustomerServiceEntity() {
		super();
	}
	
}
