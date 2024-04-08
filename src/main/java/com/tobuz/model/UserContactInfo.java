package com.tobuz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user_contact_info")
public class UserContactInfo extends BaseEntity{

	@Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "mobile_derived_country_id")
    private Long mobileDerivedCountryId;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "business_listing_id")
    private Long businessListingId;

    @Column(name = "enable_sms_email_alerts")
    private Boolean enableSmsEmailAlerts;

    @Column(name = "enable_similar_property_alert")
    private Boolean enableSimilarPropertyAlert;

    @Column(name = "enable_business_provider_advise")
    private Boolean enableBusinessProviderAdvise;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Long getMobileDerivedCountryId() {
		return mobileDerivedCountryId;
	}

	public void setMobileDerivedCountryId(Long mobileDerivedCountryId) {
		this.mobileDerivedCountryId = mobileDerivedCountryId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getBusinessListingId() {
		return businessListingId;
	}

	public void setBusinessListingId(Long businessListingId) {
		this.businessListingId = businessListingId;
	}

	public Boolean getEnableSmsEmailAlerts() {
		return enableSmsEmailAlerts;
	}

	public void setEnableSmsEmailAlerts(Boolean enableSmsEmailAlerts) {
		this.enableSmsEmailAlerts = enableSmsEmailAlerts;
	}

	public Boolean getEnableSimilarPropertyAlert() {
		return enableSimilarPropertyAlert;
	}

	public void setEnableSimilarPropertyAlert(Boolean enableSimilarPropertyAlert) {
		this.enableSimilarPropertyAlert = enableSimilarPropertyAlert;
	}

	public Boolean getEnableBusinessProviderAdvise() {
		return enableBusinessProviderAdvise;
	}

	public void setEnableBusinessProviderAdvise(Boolean enableBusinessProviderAdvise) {
		this.enableBusinessProviderAdvise = enableBusinessProviderAdvise;
	}
  }
