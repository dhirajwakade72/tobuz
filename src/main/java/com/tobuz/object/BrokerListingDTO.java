package com.tobuz.object;

import java.io.Serializable;
import java.util.List;

public class BrokerListingDTO implements Serializable {
    private List<String> businessServiceIds;
    private String userName;
    private String mobileNumber;
    private String countryCode;
    private String stateName;
    private String countryName;

    private List<String> countryIds;

    private Long countryId;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public List<String> getCountryIds() {
        return countryIds;
    }

    public void setCountryIds(List<String> countryIds) {
        this.countryIds = countryIds;
    }

    public List<String> getBusinessServiceIds() {
        return businessServiceIds;
    }

    public void setBusinessServiceIds(List<String> businessServiceIds) {
        this.businessServiceIds = businessServiceIds;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
