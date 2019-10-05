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
public class CS {
	
	@FirebaseId
	private Integer id;
	@JsonProperty("problem")
	private String problem;
	@JsonProperty("status")
	private String status;
	@JsonProperty("time")
	private String time;
	@JsonProperty("feedback")
	private String feedback;

	
	@JsonIgnore
	private Auction auction;
	private List<Bid> bid = new ArrayList<Bid>();
	private List<String[]> list = new ArrayList<String[]>();
	
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
	
	@JsonIgnore	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonIgnore	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	@JsonIgnore	
	public String getFeedback() {
		return feedback;
	}
	
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	public CS(Integer id, String problem, String status, String time,String feedback) {
		super();
		this.id = id;
		this.problem = problem;
		this.status = status;
		this.time = time;	
		this.feedback = feedback;
	}

	public CS(int i) {
		this.id =i;
	}
	
	public CS() {
		super();
	}
}
