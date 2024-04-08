package com.tobuz.dto;

import com.tobuz.model.AppUser;

import lombok.Data;

@Data
public class BusinessAdvisorDTO {

	private Long id;
	
	private String serviceType;
	
	private String companyName;
	
	private String address;
	
	private AppUser user;
	
	private String website;
	
	private String firmName;
	
	private String socialMedia;
	
	private String createdOn;
	
	private String country;
	
	private String state;
	
	private String city;
	
	private String zipCode;
	
	private String userName;
	
	private String userMail;
	
	private String userContact;
	
	private String isActive;
	
	private String companyRegisterNumber;
	private String companyRegisterYear;
	private String companyRegisterCountry;
	private String taxIdentificationNumber;
	private String passortDetails;
	
	
	
}
