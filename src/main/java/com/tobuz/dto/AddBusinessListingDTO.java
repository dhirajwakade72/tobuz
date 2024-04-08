package com.tobuz.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tobuz.object.BusinessListingFeatureInfoDTO;

import lombok.Data;

@Data
public class AddBusinessListingDTO 
{
	private Long appUserId;
	
	private Long[] subCategoryIds;
	
	private String title;
	
	private Double price;
	
	private String status;

	private String businessType;
	
	private String categoryList;
	
	private Long categoryId;
	
	private String listingDescription;
	private String listingKeywords;
	private String features;
	private String preferdLocation;

	private String preferedCity;
	private String suggestedTitle;
	private String contactName;
	private String contactNumber;
	
	private Integer favCount;

	private String companyType;

	private String businessTurnOver;
	private String businessTurnOverPer;
	private String rentAmount;
	private String wagesAmount;

	private String outGoingAmount;
	private String electricityAmount;
	private String insuranceAmount;
	private String gasAmount;
	private String moreExpence;
	private String grossProfit;

	private String netProfit;

	private Double totalPrice;

	private String moreAboutBusiness;

	private Integer numberOfEmployees;

	private String numberOfYearsinTrading;

	private String tradingHours;

	private String businessListingStatus;

	private String isoCode;

	private long id;

	private String listingFor;

	private String countryName;

	private String businessListingId;
	
	private String webSiteUrl;

	private String logoFile;
	
	private List<String> listingGallary;

	//public List<BusinessListingFeatureInfoDTO> businessListingFeatureInfos;

	public String businessTurnover;
	
	private String businessTurnoverPer;

	public String businessTotalExpenses;
	
	private String businessExpensesPer;

	private String listingType;

	private String favourites;

	private String userRole;

	private String createdOn;

	private List<String> franchiseType;
	
	private Boolean sortByTitle;
	
	private Boolean sortByPrice;

	private String searchKey;

	private Long countryId;

	private Long stateId;

	@JsonProperty("isAddedToFavourites")
	private boolean addedToFavourites;
	
	
}

