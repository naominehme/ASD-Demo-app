package com.uts.asd.entity;

public class Increment {
	
	private Integer auctionid;
	private Integer bidid;
	private Integer propertyid;
	private Integer transactionid;
	private Integer depositid;
	public Integer getAuctionid() {
		return auctionid;
	}
	public void setAuctionid(Integer auctionid) {
		this.auctionid = auctionid;
	}
	public Integer getBidid() {
		return bidid;
	}
	public void setBidid(Integer bidid) {
		this.bidid = bidid;
	}
	public Integer getPropertyid() {
		return propertyid;
	}
	public void setPropertyid(Integer propertyid) {
		this.propertyid = propertyid;
	}
	public Integer getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(Integer transactionid) {
		this.transactionid = transactionid;
	}
	public Integer getDepositid() {
		return depositid;
	}
	public void setDepositid(Integer depositid) {
		this.depositid = depositid;
	}
	public Increment() {
		super();
	}
	public Increment(Integer auctionid, Integer bidid, Integer propertyid, Integer transactionid, Integer depositid) {
		super();
		this.auctionid = auctionid;
		this.bidid = bidid;
		this.propertyid = propertyid;
		this.transactionid = transactionid;
		this.depositid = depositid;
	}
	
}
