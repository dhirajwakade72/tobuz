package com.tobuz.object;

import java.io.Serializable;

import com.tobuz.model.Country;

public class TobuzPackageDTO implements Serializable{

	private String description;

	private String cost;

	private long id ;
	private Country country;

	private String expiryPeriodInMonths;

	private String userRole;

	private Boolean isFreePackage ;

	private Integer sequence;

	private Integer advertListCount = 0;
	
	private String packageType;
	
	private String fileUploadCount;
	
	private String noOfContactAccess;
	
	private String countryName;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getExpiryPeriodInMonths() {
		return expiryPeriodInMonths;
	}

	public void setExpiryPeriodInMonths(String expiryPeriodInMonths) {
		this.expiryPeriodInMonths = expiryPeriodInMonths;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Boolean getIsFreePackage() {
		return isFreePackage;
	}

	public void setIsFreePackage(Boolean isFreePackage) {
		this.isFreePackage = isFreePackage;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getAdvertListCount() {
		return advertListCount;
	}

	public void setAdvertListCount(Integer advertListCount) {
		this.advertListCount = advertListCount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getFileUploadCount() {
		return fileUploadCount;
	}

	public void setFileUploadCount(String fileUploadCount) {
		this.fileUploadCount = fileUploadCount;
	}

	public String getNoOfContactAccess() {
		return noOfContactAccess;
	}

	public void setNoOfContactAccess(String noOfContactAccess) {
		this.noOfContactAccess = noOfContactAccess;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	
}
