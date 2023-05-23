package com.tobuz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Country extends BaseEntity {

	private String name;

	private String shortName;

	private String dialingCode;

	private String isoCode;

	private String inetCode;

	private String currencyCode;

	private Boolean isTobuzOperatedCountry = Boolean.FALSE;

	private Boolean isMasterPackageCountry = Boolean.FALSE;

	// private String htmlCurrenyCode;
	//
	// public String getHtmlCurrenyCode() {
	// return htmlCurrenyCode;
	// }
	//
	// public void setHtmlCurrenyCode(String htmlCurrenyCode) {
	// this.htmlCurrenyCode = htmlCurrenyCode;
	// }

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
	@JsonIgnore
	private List<State> stateList = new ArrayList<State>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
	@JsonIgnore
	private List<City> cityList = new ArrayList<City>();

	private String flagName;

	private String timeZone;

	private Integer timeOffsetInMins;

	private Integer sequence;

	public Boolean getIsTobuzOperatedCountry() {
		return isTobuzOperatedCountry;
	}

	public void setIsTobuzOperatedCountry(Boolean isTobuzOperatedCountry) {
		this.isTobuzOperatedCountry = isTobuzOperatedCountry;
	}

	public Integer getTimeOffsetInMins() {
		return timeOffsetInMins;
	}

	public void setTimeOffsetInMins(Integer timeOffsetInMins) {
		this.timeOffsetInMins = timeOffsetInMins;
	}

	/*
	 * public static Model.Finder<Long, Country> find = new Model.Finder<Long,
	 * Country>(Country.class);
	 */

	public String getInetCode() {
		return inetCode;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public void setInetCode(String inetCode) {
		this.inetCode = inetCode;
	}

	public String getName() {
		return name;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public String getFlagName() {
		return flagName;
	}

	public void setFlagName(String flagName) {
		this.flagName = flagName;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDialingCode() {
		return dialingCode;
	}

	public void setDialingCode(String dialingCode) {
		this.dialingCode = dialingCode;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public List<State> getStateList() {
		return stateList;
	}

	public void setStateList(List<State> stateList) {
		this.stateList = stateList;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	/* */

}
