package com.tobuz.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class State extends BaseEntity {

	private String name;

	@JsonIgnore
	@ManyToOne
	private Country country;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
	private List<City> cityList = new ArrayList<City>();
	/*
	 * public static Model.Finder<Long, State> find = new Model.Finder<Long,
	 * State>(State.class);
	 */

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	/*
	 * public static List<State> getStateList() { Country country =
	 * Country.getUserLocaleCountry(); if (country == null) { return
	 * State.find.where().eq("isActive",
	 * Boolean.TRUE).orderBy("name asc").findList(); } else { return
	 * State.find.where().eq("isActive", Boolean.TRUE).eq("country",
	 * country).orderBy("name asc") .findList(); } } public static List<State>
	 * getTobuzOperatedStateList() { List<Country> countryList =
	 * Country.getOperatedCountryList(); List<State> states = new ArrayList<>(); for
	 * (Country country : countryList) { List<State> stateList =
	 * State.find.where().eq("isActive", Boolean.TRUE).eq("country", country)
	 * .orderBy("name asc").findList(); states.addAll(stateList); } return states; }
	 * public static List<State> getStateListByCountry(Country country) {
	 * 
	 * return State.find.where().eq("isActive", Boolean.TRUE).eq("country",
	 * country).findList(); }
	 * 
	 * public static String getStateIdByCityId(String cityId) { String id = "";
	 * if(!cityId.equals("0")) { id =
	 * String.valueOf(City.find.byId(Long.valueOf(cityId)).getId()); } return id !=
	 * null?id:""; }
	 * 
	 * public static String getStateAndCountryIdByCityId(String cityId) { String id
	 * = ""; String countryId = ""; if(!cityId.equals("0") &&
	 * !cityId.equals("undefined")) { id =
	 * String.valueOf(City.find.byId(Long.valueOf(cityId)).getState().getId()!=null?
	 * City.find.byId(Long.valueOf(cityId)).getState().getId():0); } if(id!=null &&
	 * !id.equals("undefined")) { countryId =
	 * String.valueOf(City.find.byId(Long.valueOf(cityId)).getState().getCountry().
	 * getId()); //countryId =
	 * String.valueOf(State.find.byId(Long.valueOf(id)).getCountry().getId()); }
	 * String stateAndCountryId = id+","+countryId; return stateAndCountryId; }
	 */

}
