package com.tobuz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;



@Entity
public class BusinessListingOutLet extends BaseEntity {
	@OneToOne
	private Address businessAddress;
	
	// company information
	public String companyType;
	public Boolean isCompanyActive;
	public String businessTurnover;
	private String businessTurnoverPer;
	
	
	// businessExpenses;
	public String businessTotalExpenses;
	private String businessExpensesPer;


	/**
	 *  profit and price information
	 */
	private String  grossProfit;
	private String netProfit;
	private Boolean isPlantFixturesFittingsIncluded = Boolean.FALSE;
	private Boolean isEstimatedStockIncluded = Boolean.TRUE;
	private String listingSalePriceType;
	private Float totalBusinessSalePrice;
	

	/**
	 * Logo,gallery & video urls
	 */
	private  Long logoId;
	private Long documentId;
	/* private String videoURL; */


	/**
	 * 	Business operational information
	 */
	// private String size;
	private Float size;
	@ManyToOne
	private AreaMetrics metrics;
	private Integer yearOfEstablishment;
	private Integer noOfEmployees;
	private Integer noOfTradingHours;
	private Integer noOfTradingHoursTo;
	private Boolean isBusinessSupportingAndTrading = Boolean.FALSE;
	@Column(columnDefinition="TEXT")
	private String businessDescription;
	
	private String noOfYearsTrading;
	
	/**
	 * 	describe your business
	 */
	//1facebook 2.linkedin 3.google+ 4.twitter
	private String businessLinkType;
	/* private String businessURL; */

	
	//Property Information
	private String propertyAge;
	private String buildUpArea;
	@ManyToOne
	private AreaMetrics buildUpAreaUnits;
	private String propertyAvailability;
	
	
	//Franchise Information
	
	private String totalInvestmentRequired;
	
	
	private String minimumFranchiseFrom;
	private String liquidCapitalRequired;
	private String capitalUpto;
	private Integer noOfOutletForNewFranchisor;
	
	// More Expenses
	
	private String rent;
	
	private String wages;
	
	private  String outGoings;
	
	private String insurance;
	
	private String electricity;
	
	private String gas;
	
	private String moreExpenses;

	public Address getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(Address businessAddress) {
		this.businessAddress = businessAddress;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public Boolean getIsCompanyActive() {
		return isCompanyActive;
	}

	public void setIsCompanyActive(Boolean isCompanyActive) {
		this.isCompanyActive = isCompanyActive;
	}

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

	public Boolean getIsPlantFixturesFittingsIncluded() {
		return isPlantFixturesFittingsIncluded;
	}

	public void setIsPlantFixturesFittingsIncluded(Boolean isPlantFixturesFittingsIncluded) {
		this.isPlantFixturesFittingsIncluded = isPlantFixturesFittingsIncluded;
	}

	public Boolean getIsEstimatedStockIncluded() {
		return isEstimatedStockIncluded;
	}

	public void setIsEstimatedStockIncluded(Boolean isEstimatedStockIncluded) {
		this.isEstimatedStockIncluded = isEstimatedStockIncluded;
	}

	
	public String getListingSalePriceType() {
		return listingSalePriceType;
	}

	public void setListingSalePriceType(String listingSalePriceType) {
		this.listingSalePriceType = listingSalePriceType;
	}

	public Float getTotalBusinessSalePrice() {
		return totalBusinessSalePrice;
	}

	public void setTotalBusinessSalePrice(Float totalBusinessSalePrice) {
		this.totalBusinessSalePrice = totalBusinessSalePrice;
	}

	public Long getLogoId() {
		return logoId;
	}

	public void setLogoId(Long logoId) {
		this.logoId = logoId;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	/*
	 * public String getVideoURL() { return videoURL; }
	 * 
	 * public void setVideoURL(String videoURL) { this.videoURL = videoURL; }
	 */

	public Float getSize() {
		return size;
	}

	public void setSize(Float size) {
		this.size = size;
	}

	public AreaMetrics getMetrics() {
		return metrics;
	}

	public void setMetrics(AreaMetrics metrics) {
		this.metrics = metrics;
	}

	public Integer getYearOfEstablishment() {
		return yearOfEstablishment;
	}

	public void setYearOfEstablishment(Integer yearOfEstablishment) {
		this.yearOfEstablishment = yearOfEstablishment;
	}

	public Integer getNoOfEmployees() {
		return noOfEmployees;
	}

	public void setNoOfEmployees(Integer noOfEmployees) {
		this.noOfEmployees = noOfEmployees;
	}

	public Integer getNoOfTradingHours() {
		return noOfTradingHours;
	}

	public void setNoOfTradingHours(Integer noOfTradingHours) {
		this.noOfTradingHours = noOfTradingHours;
	}

	public Integer getNoOfTradingHoursTo() {
		return noOfTradingHoursTo;
	}

	public void setNoOfTradingHoursTo(Integer noOfTradingHoursTo) {
		this.noOfTradingHoursTo = noOfTradingHoursTo;
	}

	public Boolean getIsBusinessSupportingAndTrading() {
		return isBusinessSupportingAndTrading;
	}

	public void setIsBusinessSupportingAndTrading(Boolean isBusinessSupportingAndTrading) {
		this.isBusinessSupportingAndTrading = isBusinessSupportingAndTrading;
	}

	public String getBusinessDescription() {
		return businessDescription;
	}

	public void setBusinessDescription(String businessDescription) {
		this.businessDescription = businessDescription;
	}

	public String getNoOfYearsTrading() {
		return noOfYearsTrading;
	}

	public void setNoOfYearsTrading(String noOfYearsTrading) {
		this.noOfYearsTrading = noOfYearsTrading;
	}

	public String getBusinessLinkType() {
		return businessLinkType;
	}

	public void setBusinessLinkType(String businessLinkType) {
		this.businessLinkType = businessLinkType;
	}

	/*
	 * public String getBusinessURL() { return businessURL; }
	 * 
	 * public void setBusinessURL(String businessURL) { this.businessURL =
	 * businessURL; }
	 */
	public String getPropertyAge() {
		return propertyAge;
	}

	public void setPropertyAge(String propertyAge) {
		this.propertyAge = propertyAge;
	}

	public String getBuildUpArea() {
		return buildUpArea;
	}

	public void setBuildUpArea(String buildUpArea) {
		this.buildUpArea = buildUpArea;
	}

	public AreaMetrics getBuildUpAreaUnits() {
		return buildUpAreaUnits;
	}

	public void setBuildUpAreaUnits(AreaMetrics buildUpAreaUnits) {
		this.buildUpAreaUnits = buildUpAreaUnits;
	}

	public String getPropertyAvailability() {
		return propertyAvailability;
	}

	public void setPropertyAvailability(String propertyAvailability) {
		this.propertyAvailability = propertyAvailability;
	}

	public String getTotalInvestmentRequired() {
		return totalInvestmentRequired;
	}

	public void setTotalInvestmentRequired(String totalInvestmentRequired) {
		this.totalInvestmentRequired = totalInvestmentRequired;
	}

	public String getMinimumFranchiseFrom() {
		return minimumFranchiseFrom;
	}

	public void setMinimumFranchiseFrom(String minimumFranchiseFrom) {
		this.minimumFranchiseFrom = minimumFranchiseFrom;
	}

	public String getLiquidCapitalRequired() {
		return liquidCapitalRequired;
	}

	public void setLiquidCapitalRequired(String liquidCapitalRequired) {
		this.liquidCapitalRequired = liquidCapitalRequired;
	}

	public String getCapitalUpto() {
		return capitalUpto;
	}

	public void setCapitalUpto(String capitalUpto) {
		this.capitalUpto = capitalUpto;
	}

	public Integer getNoOfOutletForNewFranchisor() {
		return noOfOutletForNewFranchisor;
	}

	public void setNoOfOutletForNewFranchisor(Integer noOfOutletForNewFranchisor) {
		this.noOfOutletForNewFranchisor = noOfOutletForNewFranchisor;
	}

	public String getRent() {
		return rent;
	}

	public void setRent(String rent) {
		this.rent = rent;
	}

	public String getWages() {
		return wages;
	}

	public void setWages(String wages) {
		this.wages = wages;
	}

	public String getOutGoings() {
		return outGoings;
	}

	public void setOutGoings(String outGoings) {
		this.outGoings = outGoings;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public String getElectricity() {
		return electricity;
	}

	public void setElectricity(String electricity) {
		this.electricity = electricity;
	}

	public String getGas() {
		return gas;
	}

	public void setGas(String gas) {
		this.gas = gas;
	}

	public String getMoreExpenses() {
		return moreExpenses;
	}

	public void setMoreExpenses(String moreExpenses) {
		this.moreExpenses = moreExpenses;
	}
	
	
	
	
}
