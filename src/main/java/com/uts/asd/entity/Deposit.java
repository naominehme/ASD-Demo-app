package com.uts.asd.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.fabiomaffioletti.firebase.document.FirebaseDocument;
import com.github.fabiomaffioletti.firebase.document.FirebaseId;

@FirebaseDocument("Deposit")
@JsonIgnoreProperties
public class Deposit {
	
	@FirebaseId
	private Integer id;
	@JsonProperty("uid")
	private String uid;
	@JsonProperty("pid")
	private Integer pid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Deposit() {
		super();
	}
	public Deposit(Integer id, String uid, Integer pid) {
		super();
		this.id = id;
		this.uid = uid;
		this.pid = pid;
	}
	
	
}
