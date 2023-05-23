package com.tobuz.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class FavouriteBusinessListing extends BaseEntity{
	
	@ManyToOne
	private AppUser user;
	
	@ManyToOne
	private Role role;
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne
	private BusinessListing businessListing;
	
	/*
	 * @ManyToOne private BusinessAdvert businessAdvert;
	 */
	private Date addedOn;

	/*
	 * public static Model.Finder<Long, FavouriteBusinessListing> find = new
	 * Model.Finder<Long, FavouriteBusinessListing>(FavouriteBusinessListing.class);
	 */
	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public BusinessListing getBusinessListing() {
		return businessListing;
	}

	public void setBusinessListing(BusinessListing businessListing) {
		this.businessListing = businessListing;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	/*
	 * public BusinessAdvert getBusinessAdvert() { return businessAdvert; }
	 * 
	 * public void setBusinessAdvert(BusinessAdvert businessAdvert) {
	 * this.businessAdvert = businessAdvert; }
	 * 
	 * public static FavouriteBusinessListing getUserFavourites(AppUser appUser,Role
	 * role,BusinessAdvert businessAdvert,BusinessListing businessListing){
	 * ExpressionList<FavouriteBusinessListing> favListing =
	 * FavouriteBusinessListing.find.where().eq("user", appUser).eq("role", role);
	 * if(businessAdvert != null) {
	 * favListing.isNotNull("businessAdvert").eq("businessAdvert", businessAdvert);
	 * } if(businessListing != null) {
	 * favListing.isNotNull("businessListing").eq("businessListing",
	 * businessListing); }
	 * 
	 * return favListing.orderBy().desc("addedOn").setMaxRows(1).findUnique(); }
	 * 
	 * public static List<FavouriteBusinessListing>
	 * getUserFavouriteBusinessAdverts(AppUser appUser,Role role){ return
	 * FavouriteBusinessListing.find.where().eq("isActive", Boolean.TRUE).eq("user",
	 * appUser).eq("role",
	 * role).isNotNull("businessAdvert").orderBy().desc("addedOn").findList(); }
	 * 
	 * public static List<FavouriteBusinessListing>
	 * getPaginatedUserFavouriteBusinessAdverts(AppUser appUser,Role role,Integer
	 * pageNum,Integer pageSize){ return
	 * FavouriteBusinessListing.find.where().eq("isActive", Boolean.TRUE).eq("user",
	 * appUser).eq("role", role).isNotNull("businessAdvert")
	 * .not(Expr.in("businessAdvert.businessAdvertStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).orderBy().desc(
	 * "addedOn").findPagedList(pageNum, pageSize).getList(); }
	 * 
	 * public static List<FavouriteBusinessListing> getAllUserFavourites(AppUser
	 * appUser,Role role){ return
	 * FavouriteBusinessListing.find.where().eq("isActive", Boolean.TRUE).eq("user",
	 * appUser).eq("role", role).orderBy().desc("addedOn").findList(); }
	 * 
	 * public static List<FavouriteBusinessListing>
	 * getUserFavouriteBusinessListings(AppUser appUser,Role role){ return
	 * FavouriteBusinessListing.find.where().eq("isActive", Boolean.TRUE).eq("user",
	 * appUser).eq("role",
	 * role).isNotNull("businessListing").orderBy().desc("addedOn").findList(); }
	 * 
	 * public static List<FavouriteBusinessListing>
	 * getPaginatedUserFavouriteBusinessListings(AppUser appUser,Role role,Integer
	 * pageNum,Integer pageSize){ return
	 * FavouriteBusinessListing.find.where().eq("isActive", Boolean.TRUE).eq("user",
	 * appUser).eq("role", role) .isNotNull("businessListing").not(Expr.in(
	 * "businessListing.businessListingStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).orderBy().desc(
	 * "addedOn").findPagedList(pageNum, pageSize).getList(); } public static
	 * Boolean isUserFavourite(AppUser appUser,Role role,BusinessAdvert
	 * businessAdvert,BusinessListing businessListing){ Boolean executeQuery =
	 * Boolean.FALSE; ExpressionList<FavouriteBusinessListing> favQuery =
	 * FavouriteBusinessListing.find.where().eq("isActive", Boolean.TRUE).eq("user",
	 * appUser).eq("role", role); if(businessListing != null) { executeQuery =
	 * Boolean.TRUE; favQuery.isNotNull("businessListing").eq("businessListing",
	 * businessListing); }else if(businessAdvert != null) { executeQuery =
	 * Boolean.TRUE; favQuery.isNotNull("businessAdvert").eq("businessAdvert",
	 * businessAdvert); } if(favQuery.orderBy().desc("addedOn").findList().size() >
	 * 0 && executeQuery) { return Boolean.TRUE; } return Boolean.FALSE; }
	 * 
	 * public static List<FavouriteBusinessListing>
	 * getFavouriteBusinessAdvertUsers(BusinessAdvert businessAdvert){ return
	 * FavouriteBusinessListing.find.where().eq("isActive",
	 * Boolean.TRUE).isNotNull("businessAdvert").eq("businessAdvert",
	 * businessAdvert).orderBy().desc("addedOn").findList(); }
	 * 
	 * public static List<FavouriteBusinessListing>
	 * getFavouriteBusinessListingUsers(BusinessListing businessListing){ return
	 * FavouriteBusinessListing.find.where().eq("isActive",
	 * Boolean.TRUE).isNotNull("businessListing").eq("businessListing",
	 * businessListing).orderBy().desc("addedOn").findList(); }
	 * 
	 * 
	 * 
	 * public static FavouriteBusinessListing createFavoriteAdvertOrListing(AppUser
	 * appUser,Role role,BusinessAdvert businessAdvert,BusinessListing
	 * businessListing) { FavouriteBusinessListing favouriteBusinessListing =
	 * FavouriteBusinessListing.getUserFavourites(appUser,role,businessAdvert,
	 * businessListing); if(favouriteBusinessListing != null) {
	 * favouriteBusinessListing.setIsActive(Boolean.TRUE);
	 * favouriteBusinessListing.setAddedOn(new Date());
	 * favouriteBusinessListing.update(); }else { favouriteBusinessListing = new
	 * FavouriteBusinessListing(); favouriteBusinessListing.setUser(appUser);
	 * favouriteBusinessListing.setRole(role);
	 * favouriteBusinessListing.setBusinessAdvert(businessAdvert);
	 * favouriteBusinessListing.setBusinessListing(businessListing);
	 * favouriteBusinessListing.setIsActive(Boolean.TRUE);
	 * favouriteBusinessListing.setAddedOn(new Date());
	 * favouriteBusinessListing.save(); } return favouriteBusinessListing; }
	 */
}
