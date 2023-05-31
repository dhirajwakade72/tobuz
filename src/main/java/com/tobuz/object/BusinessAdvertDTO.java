package com.tobuz.object;

import java.io.Serializable;

public class BusinessAdvertDTO implements Serializable {
	
	String advertId;
	
	String title;
	
	String views ;
	
	String packag;
	
	String createdOn;
	
	String expiredOn;
	
	String status;
	
	String role;
	
	String investmentRangeFrom;
	
	String investmentRangeTo;
	
	long id ;
	
	
	


	public String getAdvertId() {
		return advertId;
	}

	public void setAdvertId(String advertId) {
		this.advertId = advertId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	public String getPackag() {
		return packag;
	}

	public void setPackag(String packag) {
		this.packag = packag;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getExpiredOn() {
		return expiredOn;
	}

	public void setExpiredOn(String expiredOn) {
		this.expiredOn = expiredOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getInvestmentRangeFrom() {
		return investmentRangeFrom;
	}

	public void setInvestmentRangeFrom(String investmentRangeFrom) {
		this.investmentRangeFrom = investmentRangeFrom;
	}

	public String getInvestmentRangeTo() {
		return investmentRangeTo;
	}

	public void setInvestmentRangeTo(String investmentRangeTo) {
		this.investmentRangeTo = investmentRangeTo;
	}
	
	
}
