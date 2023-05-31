package com.tobuz.object;

import java.io.Serializable;

public class UserDTO implements Serializable{

	private  long id ;
	String name ;
	String email ;
	String country;
	String role ;
	
	String listings;
	
	String adverts;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getListings() {
		return listings;
	}
	public void setListings(String listings) {
		this.listings = listings;
	}
	public String getAdverts() {
		return adverts;
	}
	public void setAdverts(String adverts) {
		this.adverts = adverts;
	}
	
	
	
}
