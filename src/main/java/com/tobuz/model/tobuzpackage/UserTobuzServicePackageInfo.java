package com.tobuz.model.tobuzpackage;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Model;
import com.tobuz.model.AppUser;
import com.tobuz.model.BaseEntity;
import com.tobuz.model.Role;
import com.tobuz.model.UserPackageStatus;

@Entity
public class UserTobuzServicePackageInfo extends BaseEntity{
	
	@ManyToOne
	private AppUser user;
	
	@ManyToOne
	private Role role;
	
	@ManyToOne
	private TobuzPackage tobuzPackage;
	
	private Date activatedOn;
	
	private Date expiredOn;
	
	private String packageActiveFor;
	
	private UserPackageStatus userPackageStatus = UserPackageStatus.ACTIVE;
	
	private Float packageCost;
	
	private Integer noOfContacts = 0;
	
	private Integer noOfFiles = 0;
	
	private Integer noOfListings = 0;
	
	public static Model.Finder<Long, UserTobuzServicePackageInfo> find = new Model.Finder<Long, UserTobuzServicePackageInfo>(UserTobuzServicePackageInfo.class);

	public AppUser getUser() {
		return user;
	}

	public Integer getNoOfContacts() {
		return noOfContacts;
	}

	public void setNoOfContacts(Integer noOfContacts) {
		this.noOfContacts = noOfContacts;
	}

	public Integer getNoOfFiles() {
		return noOfFiles;
	}

	public Integer getNoOfListings() {
		if(this.noOfListings != null) {
		return noOfListings;
		}
		return this.getTobuzPackage().getAdvertListCount();
	}

	public void setNoOfListings(Integer noOfListings) {
		this.noOfListings = noOfListings;
	}

	public void setNoOfFiles(Integer noOfFiles) {
		this.noOfFiles = noOfFiles;
	}

	public Float getPackageCost() {
		return packageCost;
	}

	public void setPackageCost(Float packageCost) {
		this.packageCost = packageCost;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getActivatedOn() {
		return activatedOn;
	}

	public void setActivatedOn(Date activatedOn) {
		this.activatedOn = activatedOn;
	}

	public Date getExpiredOn() {
		return expiredOn;
	}

	public void setExpiredOn(Date expiredOn) {
		this.expiredOn = expiredOn;
	}

	public UserPackageStatus getUserPackageStatus() {
		return userPackageStatus;
	}

	public void setUserPackageStatus(UserPackageStatus userPackageStatus) {
		this.userPackageStatus = userPackageStatus;
	}


	
	public TobuzPackage getTobuzPackage() {
		return tobuzPackage;
	}

	public void setTobuzPackage(TobuzPackage tobuzPackage) {
		this.tobuzPackage = tobuzPackage;
	}

	public String getPackageActiveFor() {
		return packageActiveFor;
	}

	public void setPackageActiveFor(String packageActiveFor) {
		this.packageActiveFor = packageActiveFor;
	}
	
	

	/*
	 * public static UserTobuzServicePackageInfo getUserServicePackageInfo(AppUser
	 * appUser,Role role,ListingType listingType) {
	 * ExpressionList<UserTobuzServicePackageInfo> infos =
	 * UserTobuzServicePackageInfo.find.where().eq("user", appUser).eq("role",
	 * role); if(listingType != null) {
	 * infos.isNotNull("packageActiveFor").eq("packageActiveFor", listingType); }
	 * return infos.setMaxRows(1).findUnique(); }
	 */
	
	/*
	 * public static List<UserTobuzServicePackageInfo>
	 * getUserServicePackageInfos(AppUser appUser,Role role,ListingType listingType)
	 * { ExpressionList<UserTobuzServicePackageInfo> infos =
	 * UserTobuzServicePackageInfo.find.where().eq("user", appUser).eq("role",
	 * role); if(listingType != null) {
	 * infos.isNotNull("packageActiveFor").eq("packageActiveFor", listingType); }
	 * return infos.findList(); }
	 * 
	 * public static UserTobuzServicePackageInfo
	 * getActiveUserServicePackageInfo(AppUser appUser,Role role,ListingType
	 * listingType) { ExpressionList<UserTobuzServicePackageInfo> infos =
	 * UserTobuzServicePackageInfo.find.where().eq("user", appUser).eq("role",
	 * role).eq("isActive", Boolean.TRUE); if(listingType != null) {
	 * infos.isNotNull("packageActiveFor").eq("packageActiveFor", listingType); }
	 * return infos.setMaxRows(1).findUnique(); }
	 * 
	 * public static UserTobuzServicePackageInfo
	 * getActiveUserServicePackageInfo(AppUser appUser,Role role,TobuzPackage
	 * tobuzPackage,ListingType listingType) {
	 * ExpressionList<UserTobuzServicePackageInfo> infos =
	 * UserTobuzServicePackageInfo.find.where().eq("user", appUser).eq("role",
	 * role).eq("isActive", Boolean.TRUE); if(listingType != null) {
	 * infos.isNotNull("packageActiveFor").eq("packageActiveFor", listingType); }
	 * if(tobuzPackage != null) { infos.eq("tobuzPackage", tobuzPackage); } return
	 * infos.setMaxRows(1).findUnique(); }
	 */
	/*
	 * public static UserTobuzServicePackageInfo
	 * createUserTobuzServicePackage(AppUser appUser,Role role,TobuzPackage
	 * tobuzPackage,ListingType listingType) { //
	 * //if(!tobuzPackage.getTobuzPackageType().equals(TobuzPackageType.FREE)) { //
	 * UserTobuzServicePackageInfo userTobuzServicePackageInfo =
	 * getActiveUserServicePackageInfo(appUser,role,listingType); //
	 * if(userTobuzServicePackageInfo == null) { // userTobuzServicePackageInfo =
	 * new UserTobuzServicePackageInfo(); //
	 * userTobuzServicePackageInfo.setActivatedOn(new Date()); //
	 * userTobuzServicePackageInfo.save(); // } //
	 * if(userTobuzServicePackageInfo.getTobuzPackage() == null ||
	 * userTobuzServicePackageInfo.getTobuzPackage().getId().longValue() !=
	 * tobuzPackage.getId().longValue()) { //
	 * userTobuzServicePackageInfo.setTobuzPackage(tobuzPackage); //
	 * userTobuzServicePackageInfo.setUser(appUser); //
	 * userTobuzServicePackageInfo.setRole(role); //
	 * userTobuzServicePackageInfo.setIsActive(Boolean.TRUE); //
	 * if(!tobuzPackage.getTobuzPackageType().equals(TobuzPackageType.FREE) &&
	 * tobuzPackage.getExpiryPeriodInMonths() != null){ // Calendar calendar =
	 * Calendar.getInstance(); // calendar.setTime(new Date()); //
	 * calendar.add(Calendar.MONTH, tobuzPackage.getExpiryPeriodInMonths()); //
	 * userTobuzServicePackageInfo.setExpiredOn(calendar.getTime()); // } //
	 * userTobuzServicePackageInfo.setPackageActiveFor(listingType); //
	 * userTobuzServicePackageInfo.update(); // } // return
	 * userTobuzServicePackageInfo; //// } //// return null;
	 * UserTobuzServicePackageInfo userTobuzServicePackageInfo = null;
	 * if(tobuzPackage != null && !tobuzPackage.getIsFreePackage()) {
	 * userTobuzServicePackageInfo = new UserTobuzServicePackageInfo();
	 * userTobuzServicePackageInfo.setActivatedOn(new Date());
	 * userTobuzServicePackageInfo.setTobuzPackage(tobuzPackage);
	 * userTobuzServicePackageInfo.setUser(appUser);
	 * userTobuzServicePackageInfo.setRole(role);
	 * userTobuzServicePackageInfo.setIsActive(Boolean.TRUE);
	 * if(!tobuzPackage.getTobuzPackageType().equals(TobuzPackageType.FREE) &&
	 * tobuzPackage.getExpiryPeriodInMonths() != null){ Calendar calendar =
	 * Calendar.getInstance(); calendar.setTime(new Date());
	 * calendar.add(Calendar.MONTH, tobuzPackage.getExpiryPeriodInMonths());
	 * userTobuzServicePackageInfo.setExpiredOn(calendar.getTime()); }
	 * userTobuzServicePackageInfo.setPackageActiveFor(listingType);
	 * userTobuzServicePackageInfo.setPackageCost(tobuzPackage.getCost());
	 * userTobuzServicePackageInfo.setNoOfContacts(tobuzPackage.
	 * getNoOfContactsAccess());
	 * userTobuzServicePackageInfo.setNoOfFiles(tobuzPackage.getFileUploadCount());
	 * userTobuzServicePackageInfo.setNoOfListings(tobuzPackage.getAdvertListCount()
	 * ); userTobuzServicePackageInfo.save(); }else { userTobuzServicePackageInfo =
	 * createFreePackage(appUser,role,listingType); } return
	 * userTobuzServicePackageInfo; }
	 * 
	 * public static UserTobuzServicePackageInfo createFreePackage(AppUser
	 * appUser,Role role) { UserTobuzServicePackageInfo userTobuzServicePackageInfo
	 * = getUserFreePackage(appUser,role); if(userTobuzServicePackageInfo == null) {
	 * TobuzPackage tobuzPackage = null; if(appUser.getUserCountry() != null &&
	 * appUser.getUserCountry().getIsTobuzOperatedCountry()) { tobuzPackage =
	 * TobuzPackage.find.where().eq("tobuzPackageType",
	 * TobuzPackageType.FREE).eq("country", appUser.getUserCountry()).eq("userRole",
	 * role.getUserRole()).setMaxRows(1).findUnique(); }else { tobuzPackage =
	 * TobuzPackage.find.where().eq("tobuzPackageType",
	 * TobuzPackageType.FREE).eq("country",
	 * Country.getMasterPackageCountry()).eq("userRole",
	 * role.getUserRole()).setMaxRows(1).findUnique(); } if(tobuzPackage != null) {
	 * userTobuzServicePackageInfo = new UserTobuzServicePackageInfo();
	 * userTobuzServicePackageInfo.setActivatedOn(new Date());
	 * userTobuzServicePackageInfo.setTobuzPackage(tobuzPackage);
	 * userTobuzServicePackageInfo.setUser(appUser);
	 * userTobuzServicePackageInfo.setRole(role);
	 * userTobuzServicePackageInfo.setIsActive(Boolean.TRUE);
	 * userTobuzServicePackageInfo.setPackageActiveFor(ListingType.BUSINESS);
	 * userTobuzServicePackageInfo.setPackageCost(0f);
	 * userTobuzServicePackageInfo.setNoOfContacts(tobuzPackage.
	 * getNoOfContactsAccess());
	 * userTobuzServicePackageInfo.setNoOfFiles(tobuzPackage.getFileUploadCount());
	 * userTobuzServicePackageInfo.setNoOfListings(tobuzPackage.getAdvertListCount()
	 * ); userTobuzServicePackageInfo.save(); } } return
	 * userTobuzServicePackageInfo; }
	 * 
	 * public static UserTobuzServicePackageInfo createFreePackage(AppUser
	 * appUser,Role role,ListingType listingType) { UserTobuzServicePackageInfo
	 * userTobuzServicePackageInfo = getUserFreePackage(appUser,role);
	 * if(userTobuzServicePackageInfo == null) { TobuzPackage tobuzPackage = null;
	 * if(appUser.getUserCountry() != null &&
	 * appUser.getUserCountry().getIsTobuzOperatedCountry()) { tobuzPackage =
	 * TobuzPackage.find.where().eq("tobuzPackageType",
	 * TobuzPackageType.FREE).eq("isActive",Boolean.TRUE).eq("userRole",role.
	 * getUserRole()).eq("country",
	 * appUser.getUserCountry()).setMaxRows(1).findUnique(); }else { tobuzPackage =
	 * TobuzPackage.find.where().eq("tobuzPackageType",
	 * TobuzPackageType.FREE).eq("isActive",Boolean.TRUE).eq("userRole",role.
	 * getUserRole()).eq("country",
	 * Country.getMasterPackageCountry()).setMaxRows(1).findUnique(); }
	 * userTobuzServicePackageInfo = new UserTobuzServicePackageInfo();
	 * userTobuzServicePackageInfo.setActivatedOn(new Date());
	 * userTobuzServicePackageInfo.setTobuzPackage(tobuzPackage);
	 * userTobuzServicePackageInfo.setUser(appUser);
	 * userTobuzServicePackageInfo.setRole(role);
	 * userTobuzServicePackageInfo.setIsActive(Boolean.TRUE);
	 * userTobuzServicePackageInfo.setPackageActiveFor(listingType);
	 * userTobuzServicePackageInfo.setPackageCost(0f);
	 * userTobuzServicePackageInfo.setNoOfContacts(tobuzPackage.
	 * getNoOfContactsAccess());
	 * userTobuzServicePackageInfo.setNoOfFiles(tobuzPackage.getFileUploadCount());
	 * userTobuzServicePackageInfo.setNoOfListings(tobuzPackage.getAdvertListCount()
	 * ); userTobuzServicePackageInfo.save(); } return userTobuzServicePackageInfo;
	 * }
	 * 
	 * public static UserTobuzServicePackageInfo getUserFreePackage(AppUser
	 * appUser,Role role) { return
	 * UserTobuzServicePackageInfo.find.where().eq("tobuzPackage.tobuzPackageType",
	 * TobuzPackageType.FREE).eq("user", appUser).eq("role",
	 * role).setMaxRows(1).findUnique(); }
	 * 
	 * public Integer listingsAvailableForPackage() { Integer availableListings =
	 * BusinessListing.getBusinessListingsByPackage(this).size();
	 * if(this.getNoOfListings() != null) { return
	 * this.getNoOfListings()-availableListings; } return 1; }
	 * 
	 * 
	 * public Integer advertsAvailableForPackage() { Integer availableListings =
	 * BusinessAdvert.getBusinessAdvertsByPackage(this).size();
	 * if(this.getNoOfListings() != null) { return
	 * this.getNoOfListings()-availableListings; } return 0; }
	 */
	
}
