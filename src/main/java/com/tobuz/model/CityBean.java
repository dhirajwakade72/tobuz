package com.tobuz.model;



public class CityBean {

	public Long cityId;

	public String name;

	public Long stateId;

	public String stateName;

	public Long countryId;

	public String countryName;

	public Boolean isUserDefaultCity = Boolean.FALSE;

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Boolean getIsUserDefaultCity() {
		return isUserDefaultCity;
	}

	public void setIsUserDefaultCity(Boolean isUserDefaultCity) {
		this.isUserDefaultCity = isUserDefaultCity;
	}

	public static CityBean toCityBean(City city) {
		if (city != null) {
			CityBean bean = new CityBean();
			bean.cityId = city.getId();
			bean.name = city.getName();
			if (city.getState() != null) {
				bean.stateId = city.getState().getId();
				bean.stateName = city.getState().getName();
			}
			if (city.getCountry() != null) {
				bean.countryId = city.getCountry().getId();
				bean.countryName = city.getCountry().getName();
			}
			return bean;
		}
		return null;
	}

	@Override
	public String toString() {
		return "CityBean [cityId=" + cityId + ", name=" + name + ", stateId=" + stateId + ", stateName=" + stateName
				+ ", countryId=" + countryId + ", countryName=" + countryName + "]";
	}
	
	
}
