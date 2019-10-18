package com.uts.asd.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.fabiomaffioletti.firebase.document.FirebaseDocument;
import com.github.fabiomaffioletti.firebase.document.FirebaseId;

@FirebaseDocument("/Users")
@JsonIgnoreProperties
public class User{
	@FirebaseId
	private String id;
	@JsonProperty("dob")
	private String dob;
	@JsonProperty("emailaddress")
	private String email;
	@JsonProperty("fname")
	private String fname;
	@JsonProperty("isAdmin")
	private boolean isAdmin;
	@JsonProperty("password")
	private String password;
	@JsonProperty("username")
	private String username;
	@JsonProperty("balances")
	private Double balance;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public User(String id, String dob, String email, String fname, boolean isAdmin, String password, String username,
			Double balance) {
		super();
		this.id = id;
		this.dob = dob;
		this.email = email;
		this.fname = fname;
		this.isAdmin = isAdmin;
		this.password = password;
		this.username = username;
		this.balance = balance;
	}
	
	public User(String id) {
		super();
		this.id = id;
	}

	public User() {
	}

}
