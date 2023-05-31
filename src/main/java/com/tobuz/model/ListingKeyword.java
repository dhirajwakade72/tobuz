package com.tobuz.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class ListingKeyword extends BaseEntity{
	
	@ManyToOne
	private BusinessListing businessListing;
	
	private String keyword;
	
	public BusinessListing getBusinessListing() {
		return businessListing;
	}

	public void setBusinessListing(BusinessListing businessListing) {
		this.businessListing = businessListing;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	/*
	 * public static ListingKeyword createListingKeyword(BusinessListing
	 * businessListing,String keyword) { ListingKeyword keyword2 =
	 * ListingKeyword.find.where().eq("businessListing",
	 * businessListing).ieq("keyword", keyword.trim()).eq("isActive",
	 * Boolean.TRUE).setMaxRows(1).findUnique(); if(keyword2 == null) { keyword2 =
	 * new ListingKeyword(); keyword2.setBusinessListing(businessListing);
	 * keyword2.setKeyword(keyword.trim()); keyword2.setIsActive(Boolean.TRUE);
	 * keyword2.save(); } return keyword2; }
	 */

}
