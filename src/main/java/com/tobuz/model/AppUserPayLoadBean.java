package com.tobuz.model;

import java.util.ArrayList;
import java.util.List;

public class AppUserPayLoadBean {

	public Long userId;

	public String authKey;

	public String name;

	public String email;

	public String role;

	public Long roleId;

	public Long countryId;

	public Long addressId;

	public Boolean isLocationUpdated = Boolean.FALSE;

	public Boolean isPackageUpdated = Boolean.FALSE;

	public Long cityId;

	public Long stateId;

	public String pincode;

	public String addressDescription;

	public String countryCode;

	public String mobileNumber;

	public String stateName;

	public String countryName;

	public String imageUrl;

	public Long imageId;

	public CityBean city;

	public Integer unreadMessageCount;

	public Integer unreadNotificationCount;

	public Long brokerCompanyLogoId;

	public String brokerCompanyLogo;

	public String companyDescription;

	public String gender;

	public String dateOfBirth;

	public String joiningDate;

	public List<String> accessCountryList = new ArrayList<String>();

	public Long getBrokerCompanyLogoId() {
		return brokerCompanyLogoId;
	}

	public void setBrokerCompanyLogoId(Long brokerCompanyLogoId) {
		this.brokerCompanyLogoId = brokerCompanyLogoId;
	}

	public String getBrokerCompanyLogo() {
		return brokerCompanyLogo;
	}

	public CityBean getCity() {
		return city;
	}

	public void setCity(CityBean city) {
		this.city = city;
	}

	public void setBrokerCompanyLogo(String brokerCompanyLogo) {
		this.brokerCompanyLogo = brokerCompanyLogo;
	}

	public String getCompanyDescription() {
		return companyDescription;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Integer getUnreadMessageCount() {
		return unreadMessageCount;
	}

	public void setUnreadMessageCount(Integer unreadMessageCount) {
		this.unreadMessageCount = unreadMessageCount;
	}

	public Integer getUnreadNotificationCount() {
		return unreadNotificationCount;
	}

	public void setUnreadNotificationCount(Integer unreadNotificationCount) {
		this.unreadNotificationCount = unreadNotificationCount;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getStateName() {
		return stateName;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
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

	public Long getUserId() {
		return userId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Boolean getIsLocationUpdated() {
		return isLocationUpdated;
	}

	public void setIsLocationUpdated(Boolean isLocationUpdated) {
		this.isLocationUpdated = isLocationUpdated;
	}

	public Boolean getIsPackageUpdated() {
		return isPackageUpdated;
	}

	public void setIsPackageUpdated(Boolean isPackageUpdated) {
		this.isPackageUpdated = isPackageUpdated;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getAddressDescription() {
		return addressDescription;
	}

	public void setAddressDescription(String addressDescription) {
		this.addressDescription = addressDescription;
	}

	/*
	 * public AppResponse validateBean() { if(this.getUserId() == null ||
	 * this.getUserId() == 0) { return new
	 * AppResponse(ResponseMessage.ACCESS_DENIED); }
	 * if(StringUtils.isEmpty(this.getName())) { return new
	 * AppResponse(ResponseMessage.NAME_REQUIRED); }
	 * if(StringUtils.isEmpty(this.mobileNumber)) { return new
	 * AppResponse(ResponseMessage.MOBILE_REQUIRED); } if((this.getAddressId() ==
	 * null || this.getAddressId() == 0)) { return new
	 * AppResponse("Address is Required"); }
	 * 
	 * if(this.getCityId() == null || this.getCityId() == 0) { return new
	 * AppResponse(ResponseMessage.CITY_REQUIRED); }
	 * 
	 * if(StringUtils.isEmpty(this.getPincode())) { return new
	 * AppResponse(ResponseMessage.ZIPCODE_REQUIRED); }
	 * 
	 * if(StringUtils.isEmpty(this.getAddressDescription())) { return new
	 * AppResponse(ResponseMessage.ADDRESS); }
	 * 
	 * return new AppResponse(AlertType.SUCCESS); }
	 */
	public String getBuyerCompanyLogo() {
		return brokerCompanyLogo;
	}

	public void setBuyerCompanyLogo(String buyerCompanyLogo) {
		this.brokerCompanyLogo = buyerCompanyLogo;
	}

	/*
	 * public AppResponse updateProfile() { AppResponse response = validateBean();
	 * if (response.isSuccess()) { try { AppUser appuser =
	 * AppUser.find.byId(this.userId); Address address =
	 * Address.find.byId(this.addressId); appuser.setName(this.getName());
	 * appuser.setCountryCode(this.getCountryCode());
	 * appuser.setMobileNumber(this.getMobileNumber()); City city =
	 * City.find.byId(this.getCityId()); address.setCity(city);
	 * address.setState(city.getState()); address.setCountry(city.getCountry());
	 * address.setZipCode(this.pincode);
	 * address.setDetailedAddress(this.getAddressDescription()); address.update();
	 * if (this.getImageId() != null && this.getImageId() != 0) {
	 * appuser.setProfileImage(FileEntity.find.byId(this.getImageId())); }
	 * appuser.update(); } catch (Exception e) { // TODO: handle exception //
	 * System.out.println(e.getMessage()); response = new
	 * AppResponse(AlertType.FAILURE); } } return response; }
	 */

	/*
	 * public void updateBroker(AppUser appUser, Long companyImageId) { Broker
	 * broker = Broker.find.where().eq("user", appUser).findUnique(); if (broker !=
	 * null) { if (companyImageId != null) {
	 * broker.setFileEntity(FileEntity.find.byId(companyImageId)); } if
	 * (StringUtils.isNotEmpty(this.getCompanyDescription())) {
	 * broker.setCompanyDescription(this.getCompanyDescription()); }
	 * 
	 * broker.update(); } }
	 */
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public List<String> getAccessCountryList() {
		return accessCountryList;
	}

	public void setAccessCountryList(List<String> accessCountryList) {
		this.accessCountryList = accessCountryList;
	}

}
