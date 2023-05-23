package com.tobuz.object;

import java.io.Serializable;
import java.util.List;

public class BusinessListingDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filePath ;
	private String title;
	private String description ;
	private double price ;
	
	private String businessType ;
	private String categoryList ;
	private String listingDescription ;
	private String listingKeywords ;
	private String features ;
	private String preferdLocation ;
			
	private String preferdCity ;
	private String preferedCity ;
	private String suggestedTitle ;
	private String yourTitle ;
	private String contactName ;
	private String contactNumber ;
	private String contactWeb ;
			
	private String companyType;

		private String businessTurnOver ;
		private String financialPer ;
		private String rentAmount ;
		private String wagesAmount ;
			
			private String  outgoingAmount ;
			private String  electricityAmount ;
			private String  insuranceAmount ;
			private String  gasAmount ;
			private String  moreExpence ;
			private String  grossProfit ;
			
			
			private String  netProfit ;

			private String  totalPrice;
			
			private String  moreAboutBusiness ;

			private String  numberOfEmployees ;

			private String  numberOfYearsinTrading ;
			
			private String  tradingHours ;
			
			private String businessListingStatus;
			
			private String isoCode;
			
			private long id;
			
			private String listingFor ;
			
			private String countryName;
			
			private String categoryId;
			
			private String businessListingId ;
			
			private String filePath1;
			
			private String filePath2;
			
			public List<BusinessListingFeatureInfoDTO> businessListingFeatureInfos ;
			
			public String businessTurnover;
			private String businessTurnoverPer;
			
			public String businessTotalExpenses;
			private String businessExpensesPer;

			private String listingType;
			
			private String favourites ;
			
			private String userRole;
			
			private String createdOn;
			
			
	
	public String getBusinessTurnover() {
				return businessTurnover;
			}
			public void setBusinessTurnover(String businessTurnover) {
				this.businessTurnover = businessTurnover;
			}
			public String getBusinessTurnoverPer() {
				return businessTurnoverPer;
			}
			public void setBusinessTurnoverPer(String businessTurnoverPer) {
				this.businessTurnoverPer = businessTurnoverPer;
			}
			public String getBusinessTotalExpenses() {
				return businessTotalExpenses;
			}
			public void setBusinessTotalExpenses(String businessTotalExpenses) {
				this.businessTotalExpenses = businessTotalExpenses;
			}
			public String getBusinessExpensesPer() {
				return businessExpensesPer;
			}
			public void setBusinessExpensesPer(String businessExpensesPer) {
				this.businessExpensesPer = businessExpensesPer;
			}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(String categoryList) {
		this.categoryList = categoryList;
	}
	public String getListingDescription() {
		return listingDescription;
	}
	public void setListingDescription(String listingDescription) {
		this.listingDescription = listingDescription;
	}
	public String getListingKeywords() {
		return listingKeywords;
	}
	public void setListingKeywords(String listingKeywords) {
		this.listingKeywords = listingKeywords;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public String getPreferdLocation() {
		return preferdLocation;
	}
	public void setPreferdLocation(String preferdLocation) {
		this.preferdLocation = preferdLocation;
	}
	public String getPreferdCity() {
		return preferdCity;
	}
	public void setPreferdCity(String preferdCity) {
		this.preferdCity = preferdCity;
	}
	public String getPreferedCity() {
		return preferedCity;
	}
	public void setPreferedCity(String preferedCity) {
		this.preferedCity = preferedCity;
	}
	public String getSuggestedTitle() {
		return suggestedTitle;
	}
	public void setSuggestedTitle(String suggestedTitle) {
		this.suggestedTitle = suggestedTitle;
	}
	public String getYourTitle() {
		return yourTitle;
	}
	public void setYourTitle(String yourTitle) {
		this.yourTitle = yourTitle;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getContactWeb() {
		return contactWeb;
	}
	public void setContactWeb(String contactWeb) {
		this.contactWeb = contactWeb;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getBusinessTurnOver() {
		return businessTurnOver;
	}
	public void setBusinessTurnOver(String businessTurnOver) {
		this.businessTurnOver = businessTurnOver;
	}
	public String getFinancialPer() {
		return financialPer;
	}
	public void setFinancialPer(String financialPer) {
		this.financialPer = financialPer;
	}
	public String getRentAmount() {
		return rentAmount;
	}
	public void setRentAmount(String rentAmount) {
		this.rentAmount = rentAmount;
	}
	public String getWagesAmount() {
		return wagesAmount;
	}
	public void setWagesAmount(String wagesAmount) {
		this.wagesAmount = wagesAmount;
	}
	public String getOutgoingAmount() {
		return outgoingAmount;
	}
	public void setOutgoingAmount(String outgoingAmount) {
		this.outgoingAmount = outgoingAmount;
	}
	public String getElectricityAmount() {
		return electricityAmount;
	}
	public void setElectricityAmount(String electricityAmount) {
		this.electricityAmount = electricityAmount;
	}
	public String getInsuranceAmount() {
		return insuranceAmount;
	}
	public void setInsuranceAmount(String insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}
	public String getGasAmount() {
		return gasAmount;
	}
	public void setGasAmount(String gasAmount) {
		this.gasAmount = gasAmount;
	}
	public String getMoreExpence() {
		return moreExpence;
	}
	public void setMoreExpence(String moreExpence) {
		this.moreExpence = moreExpence;
	}
	public String getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(String grossProfit) {
		this.grossProfit = grossProfit;
	}
	public String getNetProfit() {
		return netProfit;
	}
	public void setNetProfit(String netProfit) {
		this.netProfit = netProfit;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getMoreAboutBusiness() {
		return moreAboutBusiness;
	}
	public void setMoreAboutBusiness(String moreAboutBusiness) {
		this.moreAboutBusiness = moreAboutBusiness;
	}
	public String getNumberOfEmployees() {
		return numberOfEmployees;
	}
	public void setNumberOfEmployees(String numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}
	public String getNumberOfYearsinTrading() {
		return numberOfYearsinTrading;
	}
	public void setNumberOfYearsinTrading(String numberOfYearsinTrading) {
		this.numberOfYearsinTrading = numberOfYearsinTrading;
	}
	public String getTradingHours() {
		return tradingHours;
	}
	public void setTradingHours(String tradingHours) {
		this.tradingHours = tradingHours;
	}
	public String getBusinessListingStatus() {
		return businessListingStatus;
	}
	public void setBusinessListingStatus(String businessListingStatus) {
		this.businessListingStatus = businessListingStatus;
	}
	public String getIsoCode() {
		return isoCode;
	}
	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getListingFor() {
		return listingFor;
	}
	public void setListingFor(String listingFor) {
		this.listingFor = listingFor;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getBusinessListingId() {
		return businessListingId;
	}
	public void setBusinessListingId(String businessListingId) {
		this.businessListingId = businessListingId;
	}
	public String getFilePath1() {
		return filePath1;
	}
	public void setFilePath1(String filePath1) {
		this.filePath1 = filePath1;
	}
	public String getFilePath2() {
		return filePath2;
	}
	public void setFilePath2(String filePath2) {
		this.filePath2 = filePath2;
	}
	public List<BusinessListingFeatureInfoDTO> getBusinessListingFeatureInfos() {
		return businessListingFeatureInfos;
	}
	public void setBusinessListingFeatureInfos(List<BusinessListingFeatureInfoDTO> businessListingFeatureInfos) {
		this.businessListingFeatureInfos = businessListingFeatureInfos;
	}
	public String getListingType() {
		return listingType;
	}
	public void setListingType(String listingType) {
		this.listingType = listingType;
	}
	public String getFavourites() {
		return favourites;
	}
	public void setFavourites(String favourites) {
		this.favourites = favourites;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	

}
