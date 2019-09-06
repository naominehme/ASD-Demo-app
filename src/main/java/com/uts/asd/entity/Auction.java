package com.uts.asd.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.fabiomaffioletti.firebase.document.FirebaseDocument;
import com.github.fabiomaffioletti.firebase.document.FirebaseId;

@FirebaseDocument("Auction")
@JsonIgnoreProperties
public class Auction {
	
	@FirebaseId
	private Integer id;
	@JsonProperty("deposit")
	private double deposit;
	@JsonProperty("increased")
	private double increased;
	@JsonProperty("start")
	private double start;
	@JsonProperty("time")
	private Long time;
	@JsonProperty("pid")
	private Integer pid;
	
	public Auction() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	public double getIncreased() {
		return increased;
	}
	public void setIncreased(double increased) {
		this.increased = increased;
	}
	public double getStart() {
		return start;
	}
	public void setStart(double start) {
		this.start = start;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	
}
