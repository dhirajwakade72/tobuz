package com.tobuz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.ui.Model;

@Entity
public class Address extends BaseEntity{
	
	@Column(columnDefinition = "TEXT")
	private String detailedAddress;
	
	@ManyToOne
	private City city;
	
	@ManyToOne
	private Country country;
	
	@ManyToOne
	private State state;
	
	private Double latitude;
	
	private Double longitude;
	
	private String zipCode;
	
	@Column(columnDefinition="TEXT")
	private String googleAddress;
	
	@ManyToOne
	private BusinessAdvert businessAdvert;
	
	public BusinessAdvert getBusinessAdvert() {
		return businessAdvert;
	}

	public void setBusinessAdvert(BusinessAdvert businessAdvert) {
		this.businessAdvert = businessAdvert;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getDetailedAddress() {
		return detailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getGoogleAddress() {
		return googleAddress;
	}

	public void setGoogleAddress(String googleAddress) {
		this.googleAddress = googleAddress;
	}

	/*
	 * public static Address getBusinessAdvertAddress(BusinessAdvert businessAdvert)
	 * { return Address.find.where().eq("businessAdvert",
	 * businessAdvert).setMaxRows(1).findUnique(); }
	 */
	/*
	 * public String getAddressText() { String addressText = ""; if(this.getCity()
	 * != null) { addressText += this.getCity().getName()+" , "; }
	 * if(this.getState() != null) { addressText += this.getState().getName()+" , ";
	 * } if(this.getCountry() != null) { addressText +=
	 * this.getCountry().getName()+" , "; }
	 * if(StringUtils.isNotEmpty(this.getGoogleAddress())) { addressText +=
	 * this.getGoogleAddress(); } return addressText; }
	 * 
	 * public String getAddressTextWithoutGoogleSuggestion() { String addressText =
	 * ""; if(this.getCity() != null) { addressText +=
	 * this.getCity().getName()+", "; } if(this.getState() != null) { addressText +=
	 * this.getState().getName()+", "; } if(this.getCountry() != null) { addressText
	 * += this.getCountry().getName()+"  "; } //
	 * if(StringUtils.isNotEmpty(this.getGoogleAddress())) { // addressText +=
	 * this.getGoogleAddress(); // } return addressText;
	 */
	}