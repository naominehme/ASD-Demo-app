package com.uts.asd.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.fabiomaffioletti.firebase.document.FirebaseDocument;
import com.github.fabiomaffioletti.firebase.document.FirebaseId;

@FirebaseDocument("Transaction")
@JsonIgnoreProperties
public class Transaction {
	
	@FirebaseId
	private Integer id;
	@JsonProperty("amount")
	private double amount;
	@JsonProperty("time")
	private Long time;
	@JsonProperty("uid")
	private String uid;
	@JsonProperty("type")
	private String type;
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public Long getTime() {
		return time;
	}


	public void setTime(Long time) {
		this.time = time;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Transaction() {
		super();
	}


	public Transaction(Integer id, double amount, Long time, String uid,  String type) {
		super();
		this.id = id;
		this.amount = amount;
		this.time = time;
		this.uid = uid;
		this.type = type;
	}

	
	
}
