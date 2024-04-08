package com.tobuz.object;

import java.io.Serializable;

import com.tobuz.model.ListingAdvertLable;

import lombok.Data;
@SuppressWarnings("serial")
@Data
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
	
	String countryCode;
	
	String currencyCode;
	
	String searchIndex;
	
	String advertDescription;
	
	String companyType;
	String listingType;
	
	String category;
	String subCategory;
	
	
	long id ;
	
	ListingAdvertLable advertLable;
	
	Boolean addedToFavourites;
	
	Long likeCount;
	
}
