package com.tobuz.object;

import java.io.Serializable;

public class UserRequestDTO implements Serializable{

	String name ;
	
	String companyName ;
	
	String messsage ;
	
	String email;
	
	
	String phoneNumber;
	
	String businessFunding;
	
	String createdOn;
	
	String business_status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getMesssage() {
		return messsage;
	}

	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBusinessFunding() {
		return businessFunding;
	}

	public void setBusinessFunding(String businessFunding) {
		this.businessFunding = businessFunding;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getBusiness_status() {
		return business_status;
	}

	public void setBusiness_status(String business_status) {
		this.business_status = business_status;
	}
	
	
	
}
