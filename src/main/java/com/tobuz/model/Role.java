package com.tobuz.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Role extends BaseEntity{
	
	private String userRole;
	
	@ManyToOne
	private AppUser appUser;

	



	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	/*
	 * public static Role getUserRole(AppUser appUser,UserRole userRole) { return
	 * Role.find.where().eq("appUser", appUser).eq("userRole",
	 * userRole).orderBy().asc("createdOn").setMaxRows(1).findUnique(); }
	 */
	
	
}
