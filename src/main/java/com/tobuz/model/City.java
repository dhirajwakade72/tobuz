package com.tobuz.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class City extends BaseEntity{

	private String name;
	
	@ManyToOne
	private State state;
	
	@ManyToOne
	private Country country;
	
	/*
	 * public static Model.Finder<Long, City> find = new Model.Finder<Long,
	 * City>(City.class);
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	/*
	 * public static List<City> cityList(){ Country country =
	 * Country.getUserLocaleCountry(); if(country == null) { return
	 * City.find.where().eq("isActive",Boolean.TRUE).orderBy("name asc").findList();
	 * }else { return City.find.where().eq("isActive",Boolean.TRUE).eq("country",
	 * country).orderBy("name asc").findList(); }
	 * 
	 * }
	 * 
	 * public static List<City> allCityList(){ return
	 * City.find.where().eq("isActive",Boolean.TRUE).orderBy("name asc").findList();
	 * 
	 * } public static List<City> tobuzOperatedCityList(){
	 * 
	 * List<Country> countryList = Country.getOperatedCountryList(); List<City>
	 * cities = new ArrayList<>(); for(Country country:countryList) { List<City>
	 * cityList = City.find.where().eq("isActive",Boolean.TRUE).eq("country",
	 * country).orderBy("name asc").findList(); cities.addAll(cityList); }
	 * //System.out.println(cities.size()+"====>cities"); return cities;
	 * 
	 * }
	 * 
	 * public static List<City> cityListByCountry(Country country){
	 * ExpressionList<City> cityWhere =
	 * City.find.where().eq("isActive",Boolean.TRUE); if(country != null){ return
	 * cityWhere.eq("country", country).orderBy("name asc").findList(); } return
	 * cityWhere.orderBy("name asc").findList(); }
	 * 
	 * public static List<City> cityListByCountryAndName(Country country,String
	 * searchKey){ ExpressionList<City> cityWhere =
	 * City.find.where().eq("isActive",Boolean.TRUE); if(country != null){
	 * cityWhere.eq("country", country).orderBy("name asc").findList(); }
	 * if(StringUtils.isNotEmpty(searchKey)) { cityWhere.ilike("name",
	 * searchKey+"%"); } return
	 * cityWhere.orderBy("name asc").setMaxRows(10).findList(); }
	 * 
	 */
	
	
	
	
}
