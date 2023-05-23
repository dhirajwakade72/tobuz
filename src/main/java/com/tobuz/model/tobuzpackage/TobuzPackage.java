package com.tobuz.model.tobuzpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.JsonIgnore;
import com.tobuz.model.AppUser;
import com.tobuz.model.BaseEntity;
import com.tobuz.model.Country;
import com.tobuz.model.TobuzPackageType;

/**
 * @author thrymr
 *
 */
@Entity
public class TobuzPackage extends BaseEntity {

	// Silver/Gold/Free etc
	private String tobuzPackageType;

	@Column(columnDefinition = "TEXT")
	private String description;

	private Float cost;

	@ManyToOne
	@JsonIgnore
	private Country country;

	private Integer expiryPeriodInMonths;

	private String userRole;

	private Boolean isFreePackage = Boolean.FALSE;

	private Integer sequence;

	private Integer advertListCount = 0;

	private Integer fileUploadCount = 5;

	private Integer noOfContactsAccess = 10;

	public static Model.Finder<Long, TobuzPackage> find = new Model.Finder<Long, TobuzPackage>(TobuzPackage.class);

	public Integer getNoOfContactsAccess() {
		return noOfContactsAccess;
	}

	public void setNoOfContactsAccess(Integer noOfContactsAccess) {
		this.noOfContactsAccess = noOfContactsAccess;
	}

	public Integer getAdvertListCount() {
		return advertListCount;
	}

	public void setAdvertListCount(Integer advertListCount) {
		this.advertListCount = advertListCount;
	}

	public Integer getFileUploadCount() {
		return fileUploadCount;
	}

	public void setFileUploadCount(Integer fileUploadCount) {
		this.fileUploadCount = fileUploadCount;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}


	public String getTobuzPackageType() {
		return tobuzPackageType;
	}

	public void setTobuzPackageType(String tobuzPackageType) {
		this.tobuzPackageType = tobuzPackageType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Integer getExpiryPeriodInMonths() {
		return expiryPeriodInMonths;
	}

	public void setExpiryPeriodInMonths(Integer expiryPeriodInMonths) {
		this.expiryPeriodInMonths = expiryPeriodInMonths;
	}

	public Boolean getIsFreePackage() {
		if (this.getTobuzPackageType().equals(TobuzPackageType.FREE)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public void setIsFreePackage(Boolean isFreePackage) {
		this.isFreePackage = isFreePackage;
	}

	/*
	 * public static List<TobuzPackage> getPackagesByCountryAndRole(Country
	 * country,AppUser appUser){ ExpressionList<TobuzPackage> packages =
	 * TobuzPackage.find.where().eq("country", country); if(appUser != null) {
	 * packages.eq("userRole",appUser.getRole().getUserRole()); } return
	 * packages.eq("isActive", Boolean.TRUE).orderBy().asc("sequence").findList(); }
	 * 
	 * public static List<TobuzPackage> getPackagesByCountryAndRoleAndCost(Country
	 * country,AppUser appUser,Float cost){ ExpressionList<TobuzPackage> packages =
	 * TobuzPackage.find.where().eq("country", country); if(appUser != null) {
	 * packages.eq("userRole",appUser.getRole().getUserRole()); } if(cost != null) {
	 * packages.ge("cost", cost); } return packages.eq("isActive",
	 * Boolean.TRUE).orderBy().asc("sequence").findList(); }
	 * 
	 * public static TobuzPackage getFreePackageByCountry(Country country){
	 * TobuzPackage tPackage = TobuzPackage.find.where().eq("country",
	 * country).eq("isActive", Boolean.TRUE).eq("tobuzPackageType",
	 * TobuzPackageType.FREE).setMaxRows(1).findUnique(); return tPackage; }
	 * 
	 * public static List<TobuzPackage> getPackageListBySearchKey(SearchBean
	 * searchBean){ ExpressionList<TobuzPackage> tobuzPackageWhere =
	 * TobuzPackage.find.where().eq("isActive", Boolean.TRUE); List<String>
	 * countryIsoCodeList = searchBean.countryIsoCodeList; List<String> userRoleList
	 * = searchBean.userRoleList; List<String> allCountryList = new ArrayList<>();
	 * 
	 * if(countryIsoCodeList != null && !countryIsoCodeList.isEmpty()){
	 * if(countryIsoCodeList.contains("ALL")){ List<Country> countrysList =
	 * Country.countryList(); for (Country country : countrysList) {
	 * allCountryList.add(country.getIsoCode()); } tobuzPackageWhere.in("country",
	 * allCountryList.stream().map(countryIsoCode->Country.getCountryByIsoCode(
	 * countryIsoCode)).collect(Collectors.toList())); }else{
	 * tobuzPackageWhere.in("country",
	 * countryIsoCodeList.stream().map(countryIsoCode->Country.getCountryByIsoCode(
	 * countryIsoCode)).collect(Collectors.toList()));
	 * 
	 * } } if(userRoleList != null && !userRoleList.isEmpty()){
	 * tobuzPackageWhere.in("userRole",userRoleList.stream().map(strRole->UserRole.
	 * valueOf(strRole)).distinct().collect(Collectors.toList())); } return
	 * tobuzPackageWhere.findList(); }
	 */

	
	
	@Override
	public String toString() {
		return "TobuzPackage [tobuzPackageType=" + tobuzPackageType + ", description=" + description + ", cost=" + cost
				+ ", expiryPeriodInMonths=" + expiryPeriodInMonths + ", userRole=" + userRole + ", isFreePackage="
				+ isFreePackage + ", sequence=" + sequence + ", advertListCount=" + advertListCount
				+ ", fileUploadCount=" + fileUploadCount + ", noOfContactsAccess=" + noOfContactsAccess + "]";
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
