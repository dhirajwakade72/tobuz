package com.tobuz.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BusinessAdvert extends BaseEntity {

	@JsonIgnore
	@ManyToOne
	private AppUser advertedByUser;

	@JsonIgnore
	@ManyToOne
	private Role role;

	private String advertId;

	private String listingType;

	private Float investmentRangeFrom;

	private Float investmentRangeTo;

	@Column(columnDefinition = "TEXT")
	private String advertDescription;

	private String title;

	private Float spaceSize;

	private String companyType;

	private String franchiseType;

	@ManyToOne
	private AppUser removedBy;

	private Date adminDeletedOn;	

	private String sourceOfBusiness;

	private Integer timeLineInDays;

	private String referenceUrlType;

	private Boolean allowContactDetailsToUsers = Boolean.FALSE;

	private String businessAdvertStatus;

	 @Enumerated(EnumType.STRING)
	private ListingAdvertLable advertLable;

	private String suggestedTitle;

	@Column(columnDefinition = "TEXT")
	public String searchIndex;

	public Date adminApprovedOn;

	private Boolean isRecomendedByAdmin = Boolean.FALSE;

	private Date adminRecomendedOn;
	
	private Date expiredOn;

	@ManyToOne
	private Country advertCountry;

	@Column(columnDefinition = "TEXT")
	private String adminComment;

	private Boolean isAdminResponded = Boolean.FALSE;

	private Boolean isDraftReminderSent = Boolean.FALSE;

	@Column(columnDefinition = "TEXT")
	private String userDescription;		


	private Double latitude;

	private Double longitude;
	
	public AppUser getRemovedBy() {
		return removedBy;
	}

	public void setRemovedBy(AppUser removedBy) {
		this.removedBy = removedBy;
	}

	public Date getAdminDeletedOn() {
		return adminDeletedOn;
	}

	public void setAdminDeletedOn(Date adminDeletedOn) {
		this.adminDeletedOn = adminDeletedOn;
	}

	public String getFranchiseType() {
		return franchiseType;
	}

	public void setFranchiseType(String franchiseType) {
		this.franchiseType = franchiseType;
	}


	public Date getAdminRecomendedOn() {
		return adminRecomendedOn;
	}

	public void setAdminRecomendedOn(Date adminRecomendedOn) {
		this.adminRecomendedOn = adminRecomendedOn;
	}	

	public Boolean getIsAdminResponded() {
		return isAdminResponded;
	}

	public void setIsAdminResponded(Boolean isAdminResponded) {
		this.isAdminResponded = isAdminResponded;
	}

	public String getAdminComment() {
		return adminComment;
	}

	public Country getAdvertCountry() {
		return advertCountry;
	}

	public void setAdvertCountry(Country advertCountry) {
		this.advertCountry = advertCountry;
	}

	public void setAdminComment(String adminComment) {
		this.adminComment = adminComment;
	}

	public Boolean getIsDraftReminderSent() {
		return isDraftReminderSent;
	}

	public void setIsDraftReminderSent(Boolean isDraftReminderSent) {
		this.isDraftReminderSent = isDraftReminderSent;
	}

	public Date getExpiredOn() {
		return expiredOn;
	}

	public void setExpiredOn(Date expiredOn) {
		this.expiredOn = expiredOn;
	}	

	public String getUserDescription() {
		return userDescription;
	}

	public Boolean getIsRecomendedByAdmin() {
		return isRecomendedByAdmin;
	}

	public void setIsRecomendedByAdmin(Boolean isRecomendedByAdmin) {
		this.isRecomendedByAdmin = isRecomendedByAdmin;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	/*
	 * public static Model.Finder<Long, BusinessAdvert> find = new
	 * Model.Finder<Long, BusinessAdvert>(BusinessAdvert.class);
	 */

	public AppUser getAdvertedByUser() {
		return advertedByUser;
	}

	public Date getAdminApprovedOn() {
		return adminApprovedOn;
	}

	public void setAdminApprovedOn(Date adminApprovedOn) {
		this.adminApprovedOn = adminApprovedOn;
	}

	public void setAdvertedByUser(AppUser advertedByUser) {
		this.advertedByUser = advertedByUser;
	}

	
	public String getReferenceUrlType() {
		return referenceUrlType;
	}

	public void setReferenceUrlType(String referenceUrlType) {
		this.referenceUrlType = referenceUrlType;
	}

	public String getSuggestedTitle() {
		return suggestedTitle;
	}

	public void setSuggestedTitle(String suggestedTitle) {
		this.suggestedTitle = suggestedTitle;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	public String getListingType() {
		return listingType;
	}

	public void setListingType(String listingType) {
		this.listingType = listingType;
	}

	public ListingAdvertLable getAdvertLable() {
		return advertLable;
	}

	public void setAdvertLable(ListingAdvertLable advertLable) {
		this.advertLable = advertLable;
	}

	public Float getInvestmentRangeFrom() {
		return investmentRangeFrom;
	}

	public void setInvestmentRangeFrom(Float investmentRangeFrom) {
		this.investmentRangeFrom = investmentRangeFrom;
	}

	public Float getInvestmentRangeTo() {
		return investmentRangeTo;
	}

	public void setInvestmentRangeTo(Float investmentRangeTo) {
		this.investmentRangeTo = investmentRangeTo;
	}

	public String getAdvertDescription() {
		return advertDescription;
	}

	public void setAdvertDescription(String advertDescription) {
		this.advertDescription = advertDescription;
	}

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

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getSourceOfBusiness() {
		return sourceOfBusiness;
	}

	public void setSourceOfBusiness(String sourceOfBusiness) {
		this.sourceOfBusiness = sourceOfBusiness;
	}	

	public String getBusinessAdvertStatus() {
		return businessAdvertStatus;
	}

	public void setBusinessAdvertStatus(String businessAdvertStatus) {
		this.businessAdvertStatus = businessAdvertStatus;
	}

	/*
	 * public String getReferenceURL() { return referenceURL; }
	 * 
	 * public void setReferenceURL(String referenceURL) { this.referenceURL =
	 * referenceURL; }
	 */

	public Boolean getAllowContactDetailsToUsers() {
		return allowContactDetailsToUsers;
	}

	public void setAllowContactDetailsToUsers(Boolean allowContactDetailsToUsers) {
		this.allowContactDetailsToUsers = allowContactDetailsToUsers;
	}
	/*
	 * public List<Address> getAddressList() { return addressList; }
	 * 
	 * public void setAddressList(List<Address> addressList) { this.addressList =
	 * addressList; }
	 */

	public Float getSpaceSize() {
		return spaceSize;
	}

	public void setSpaceSize(Float spaceSize) {
		this.spaceSize = spaceSize;
	}

	/*
	 * public AreaMetrics getSpaceUnits() { return spaceUnits; }
	 * 
	 * public void setSpaceUnits(AreaMetrics spaceUnits) { this.spaceUnits =
	 * spaceUnits; }
	 */

	public Integer getTimeLineInDays() {
		return timeLineInDays;
	}

	public void setTimeLineInDays(Integer timeLineInDays) {
		this.timeLineInDays = timeLineInDays;
	}

	public String getSearchIndex() {
		return searchIndex;
	}

	public void setSearchIndex(String searchIndex) {
		this.searchIndex = searchIndex;
	}

}
