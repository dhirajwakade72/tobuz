package com.tobuz.object;

import java.io.Serializable;

public class UserPackageInfoDTO implements Serializable{
	
	String packageType;
	
	String createdOn ;
	
	String advertListCount;
	
	String noContactAccess;
	
	String activatedOn;
	
	String packageActiveFor;
	
	String userPackageStatus;
	
	String expairyDate ;

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getAdvertListCount() {
		return advertListCount;
	}

	public void setAdvertListCount(String advertListCount) {
		this.advertListCount = advertListCount;
	}

	public String getNoContactAccess() {
		return noContactAccess;
	}

	public void setNoContactAccess(String noContactAccess) {
		this.noContactAccess = noContactAccess;
	}

	public String getActivatedOn() {
		return activatedOn;
	}

	public void setActivatedOn(String activatedOn) {
		this.activatedOn = activatedOn;
	}

	public String getPackageActiveFor() {
		return packageActiveFor;
	}

	public void setPackageActiveFor(String packageActiveFor) {
		this.packageActiveFor = packageActiveFor;
	}

	public String getUserPackageStatus() {
		return userPackageStatus;
	}

	public void setUserPackageStatus(String userPackageStatus) {
		this.userPackageStatus = userPackageStatus;
	}

	public String getExpairyDate() {
		return expairyDate;
	}

	public void setExpairyDate(String expairyDate) {
		this.expairyDate = expairyDate;
	}
	
	

}
