package com.tobuz.model.tobuzpackage;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.JsonIgnore;
import com.tobuz.model.BaseEntity;
import com.tobuz.model.Country;
import com.tobuz.model.UserRole;

@Entity
public class TobuzPackageService extends BaseEntity {

	private String title;

	@Column(columnDefinition = "TEXT")
	private String description;

	@ManyToOne
	@JsonIgnore
	private Country country;

	private String userRole;

	/*
	 * public static Model.Finder<Long, TobuzPackageService> find = new
	 * Model.Finder<Long, TobuzPackageService>( TobuzPackageService.class);
	 */

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	/*
	 * public static List<TobuzPackageService>
	 * getPackageServicesByCountryAndUserRole(Country country, UserRole userRole) {
	 * return TobuzPackageService.find.where().eq("country", country).eq("userRole",
	 * userRole) .eq("isActive", Boolean.TRUE).orderBy().asc("id").findList(); }
	 */

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}



	/*
	 * public static List<TobuzPackageService> getTobuzPackageServiceInfo(SearchBean
	 * searchBean) { ExpressionList<TobuzPackageService> tobuzPackageServiceQurey =
	 * TobuzPackageService.find.where().eq("isActive", Boolean.TRUE);
	 * 
	 * List<String> countryIsoCodeList = searchBean.countryIsoCodeList; List<String>
	 * userRoleList = searchBean.userRoleList; List<String> allCountryList = new
	 * ArrayList<>();
	 * 
	 * if(countryIsoCodeList != null && !countryIsoCodeList.isEmpty()){
	 * if(countryIsoCodeList.contains("ALL")){ List<Country> countrysList =
	 * Country.countryList(); for (Country country : countrysList) {
	 * allCountryList.add(country.getIsoCode()); }
	 * tobuzPackageServiceQurey.in("country",
	 * allCountryList.stream().map(countryIsoCode->Country.getCountryByIsoCode(
	 * countryIsoCode)).collect(Collectors.toList())); }else{
	 * tobuzPackageServiceQurey.in("country",
	 * countryIsoCodeList.stream().map(countryIsoCode->Country.getCountryByIsoCode(
	 * countryIsoCode)).collect(Collectors.toList()));
	 * 
	 * } } if(userRoleList != null && !userRoleList.isEmpty()){
	 * tobuzPackageServiceQurey.in("userRole",
	 * userRoleList.stream().map(strRole->UserRole.valueOf(strRole)).distinct().
	 * collect(Collectors.toList())); } return tobuzPackageServiceQurey
	 * .orderBy().asc("createdOn") .findList(); }
	 */

}
