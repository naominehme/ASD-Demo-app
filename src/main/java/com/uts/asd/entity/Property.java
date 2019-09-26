package com.uts.asd.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.fabiomaffioletti.firebase.document.FirebaseDocument;
import com.github.fabiomaffioletti.firebase.document.FirebaseId;

@FirebaseDocument("/Property")
@JsonIgnoreProperties
public class Property {
	
	@FirebaseId
	private Integer id;
	@JsonProperty("address")
	private String address;
	@JsonProperty("bedroom")
	private Integer bedroom;
	@JsonProperty("bathroom")
	private Integer bathroom;
	@JsonProperty("garage")
	private Integer garage;
	@JsonProperty("state")
	private Integer state;
	@JsonProperty("url")
	private String url;
	@JsonProperty("description")
	private String description;
	@JsonProperty("title")
	private String title;
	@JsonProperty("sqm")
	private Integer sqm;
	@JsonProperty("suburb")
	private String suburb;
	
	@JsonIgnore
	private Auction auction;
	@JsonIgnore
	private List<Deposit> deposit = new ArrayList<Deposit>();
	private List<Bid> bid = new ArrayList<Bid>();
	private List<String[]> list = new ArrayList<String[]>();
	
	public List<Deposit> getDeposit() {
		return deposit;
	}

	public void setDeposit(List<Deposit> deposit) {
		this.deposit = deposit;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public void setBid(List<Bid> bid) {
		this.bid = bid;
	}

	public List<Bid> getBid() {
		return bid;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public Auction getAuction() {
		return auction;
	}

	public List<String[]> getList() {
		return list;
	}

	public Integer getSqm() {
		return sqm;
	}

	public void setSqm(Integer sqm) {
		this.sqm = sqm;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public Property(Integer id) {
		super();
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Property(Integer id, String address, Integer bedroom, Integer bathroom, Integer garage, Integer state,String title,Integer sqm,String suburb) {
		super();
		this.id = id;
		this.address = address;
		this.bedroom = bedroom;
		this.bathroom = bathroom;
		this.garage = garage;
		this.state = state;
		this.title = title;
		this.sqm = sqm;
		this.suburb = suburb;
	}

	public Property(Integer id, String address, Integer bedroom, Integer bathroom, Integer garage, Integer state,String title,Integer sqm,String suburb,String url) {
		super();
		this.id = id;
		this.address = address;
		this.bedroom = bedroom;
		this.bathroom = bathroom;
		this.garage = garage;
		this.state = state;
		this.title = title;
		this.sqm = sqm;
		this.suburb = suburb;
		this.url =url;
	}
	public Property() {
		super();
	}

	@Override
	public String toString() {
		return "Property{" +
				"id=" + id +
				", address='" + address + '\'' +
				", bedroom=" + bedroom +
				", bathroom=" + bathroom +
				", garage=" + garage +
				", state=" + state +
				", url='" + url + '\'' +
				", description='" + description + '\'' +
				", title='" + title + '\'' +
				", sqm=" + sqm +
				", suburb='" + suburb + '\'' +
				", auction=" + auction +
				", bid=" + bid +
				", list=" + list +
				'}';
	}
}
