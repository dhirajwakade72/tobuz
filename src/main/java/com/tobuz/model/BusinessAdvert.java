package com.tobuz.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.tomcat.jni.Address;
import org.h2.expression.ExpressionList;
import org.springframework.ui.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BusinessAdvert extends BaseEntity {

	@JsonIgnore
	@ManyToOne
	private AppUser advertedByUser;

	@JsonIgnore
	@ManyToOne
	private Role role;

	private String advertId;

	/**
	 * Business details Business/Commercial/Franchise
	 */
	private String listingType;

	private Float investmentRangeFrom;

	private Float investmentRangeTo;

	@Column(columnDefinition = "TEXT")
	private String advertDescription;

	// Many addresses
//	@OneToOne
	/*
	 * // private Address preferredLocation;
	 * 
	 * @JsonIgnore
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "businessAdvert") private
	 * List<Address> addressList = new ArrayList<Address>();
	 */
	private String title;

	private Float spaceSize;

	/*
	 * @ManyToOne private AreaMetrics spaceUnits;
	 */

	// other information
	private String companyType;

	private String franchiseType;

	@ManyToOne
	private AppUser removedBy;

	private Date adminDeletedOn;

	public AppUser getRemovedBy() {
		return removedBy;
	}

	public void setRemovedBy(AppUser removedBy) {
		this.removedBy = removedBy;
	}

	public Date getAdminDeletedOn() {
		return adminDeletedOn;
	}

	public void setAdminDeletedOn(Date adminDeletedOn) {
		this.adminDeletedOn = adminDeletedOn;
	}

	public String getFranchiseType() {
		return franchiseType;
	}

	public void setFranchiseType(String franchiseType) {
		this.franchiseType = franchiseType;
	}

	private String sourceOfBusiness;

	// Time line to acquire the property
	private Integer timeLineInDays;

	private String referenceUrlType;
	// About advertizer
	/* private String referenceURL; */

	private Boolean allowContactDetailsToUsers = Boolean.FALSE;

	private String businessAdvertStatus;

	private ListingAdvertLable advertLable;

	private String suggestedTitle;

	@Column(columnDefinition = "TEXT")
	public String searchIndex;

	public Date adminApprovedOn;

	private Boolean isRecomendedByAdmin = Boolean.FALSE;

	private Date adminRecomendedOn;

	public Date getAdminRecomendedOn() {
		return adminRecomendedOn;
	}

	public void setAdminRecomendedOn(Date adminRecomendedOn) {
		this.adminRecomendedOn = adminRecomendedOn;
	}

	private Date expiredOn;
	/*
	 * @ManyToOne private UserTobuzServicePackageInfo userTobuzServicePackageInfo;
	 */

	@ManyToOne
	private Country advertCountry;

	@Column(columnDefinition = "TEXT")
	private String adminComment;

	private Boolean isAdminResponded = Boolean.FALSE;

	private Boolean isDraftReminderSent = Boolean.FALSE;

	public Boolean getIsAdminResponded() {
		return isAdminResponded;
	}

	public void setIsAdminResponded(Boolean isAdminResponded) {
		this.isAdminResponded = isAdminResponded;
	}

	public String getAdminComment() {
		return adminComment;
	}

	public Country getAdvertCountry() {
		return advertCountry;
	}

	public void setAdvertCountry(Country advertCountry) {
		this.advertCountry = advertCountry;
	}

	public void setAdminComment(String adminComment) {
		this.adminComment = adminComment;
	}

	public Boolean getIsDraftReminderSent() {
		return isDraftReminderSent;
	}

	public void setIsDraftReminderSent(Boolean isDraftReminderSent) {
		this.isDraftReminderSent = isDraftReminderSent;
	}

	public Date getExpiredOn() {
		return expiredOn;
	}

	public void setExpiredOn(Date expiredOn) {
		this.expiredOn = expiredOn;
	}

	@Column(columnDefinition = "TEXT")
	private String userDescription;

	public String getUserDescription() {
		return userDescription;
	}

	public Boolean getIsRecomendedByAdmin() {
		return isRecomendedByAdmin;
	}

	public void setIsRecomendedByAdmin(Boolean isRecomendedByAdmin) {
		this.isRecomendedByAdmin = isRecomendedByAdmin;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	private Double latitude;

	private Double longitude;

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	/*
	 * public static Model.Finder<Long, BusinessAdvert> find = new
	 * Model.Finder<Long, BusinessAdvert>(BusinessAdvert.class);
	 */

	public AppUser getAdvertedByUser() {
		return advertedByUser;
	}

	public Date getAdminApprovedOn() {
		return adminApprovedOn;
	}

	public void setAdminApprovedOn(Date adminApprovedOn) {
		this.adminApprovedOn = adminApprovedOn;
	}

	public void setAdvertedByUser(AppUser advertedByUser) {
		this.advertedByUser = advertedByUser;
	}

	
	public String getReferenceUrlType() {
		return referenceUrlType;
	}

	public void setReferenceUrlType(String referenceUrlType) {
		this.referenceUrlType = referenceUrlType;
	}

	public String getSuggestedTitle() {
		return suggestedTitle;
	}

	public void setSuggestedTitle(String suggestedTitle) {
		this.suggestedTitle = suggestedTitle;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	public String getListingType() {
		return listingType;
	}

	public void setListingType(String listingType) {
		this.listingType = listingType;
	}

	public ListingAdvertLable getAdvertLable() {
		return advertLable;
	}

	public void setAdvertLable(ListingAdvertLable advertLable) {
		this.advertLable = advertLable;
	}

	public Float getInvestmentRangeFrom() {
		return investmentRangeFrom;
	}

	public void setInvestmentRangeFrom(Float investmentRangeFrom) {
		this.investmentRangeFrom = investmentRangeFrom;
	}

	public Float getInvestmentRangeTo() {
		return investmentRangeTo;
	}

	public void setInvestmentRangeTo(Float investmentRangeTo) {
		this.investmentRangeTo = investmentRangeTo;
	}

	public String getAdvertDescription() {
		return advertDescription;
	}

	public void setAdvertDescription(String advertDescription) {
		this.advertDescription = advertDescription;
	}

	public String getAdvertId() {
		return advertId;
	}

	public void setAdvertId(String advertId) {
		this.advertId = advertId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getSourceOfBusiness() {
		return sourceOfBusiness;
	}

	public void setSourceOfBusiness(String sourceOfBusiness) {
		this.sourceOfBusiness = sourceOfBusiness;
	}

	

	public String getBusinessAdvertStatus() {
		return businessAdvertStatus;
	}

	public void setBusinessAdvertStatus(String businessAdvertStatus) {
		this.businessAdvertStatus = businessAdvertStatus;
	}

	/*
	 * public String getReferenceURL() { return referenceURL; }
	 * 
	 * public void setReferenceURL(String referenceURL) { this.referenceURL =
	 * referenceURL; }
	 */

	public Boolean getAllowContactDetailsToUsers() {
		return allowContactDetailsToUsers;
	}

	public void setAllowContactDetailsToUsers(Boolean allowContactDetailsToUsers) {
		this.allowContactDetailsToUsers = allowContactDetailsToUsers;
	}
	/*
	 * public List<Address> getAddressList() { return addressList; }
	 * 
	 * public void setAddressList(List<Address> addressList) { this.addressList =
	 * addressList; }
	 */

	public Float getSpaceSize() {
		return spaceSize;
	}

	public void setSpaceSize(Float spaceSize) {
		this.spaceSize = spaceSize;
	}

	/*
	 * public AreaMetrics getSpaceUnits() { return spaceUnits; }
	 * 
	 * public void setSpaceUnits(AreaMetrics spaceUnits) { this.spaceUnits =
	 * spaceUnits; }
	 */

	public Integer getTimeLineInDays() {
		return timeLineInDays;
	}

	public void setTimeLineInDays(Integer timeLineInDays) {
		this.timeLineInDays = timeLineInDays;
	}

	public String getSearchIndex() {
		return searchIndex;
	}

	public void setSearchIndex(String searchIndex) {
		this.searchIndex = searchIndex;
	}

	/*
	 * public UserTobuzServicePackageInfo getUserTobuzServicePackageInfo() { return
	 * userTobuzServicePackageInfo; }
	 * 
	 * public void setUserTobuzServicePackageInfo(UserTobuzServicePackageInfo
	 * userTobuzServicePackageInfo) { this.userTobuzServicePackageInfo =
	 * userTobuzServicePackageInfo; }
	 */
	/*
	 * @Override public void save(){ final StringBuilder stringBuilder = new
	 * StringBuilder();
	 * stringBuilder.append(this.getAdvertId()+this.getTitle().trim().toLowerCase()+
	 * this.getSuggestedTitle().trim().toLowerCase()+""+this.getBusinessAdvertStatus
	 * ().toString().toLowerCase()+""+this.getListingType().toString().toLowerCase()
	 * ); Address address = Address.getBusinessAdvertAddress(this); if(address !=
	 * null) { if(address.getCountry() != null) {
	 * stringBuilder.append(address.getCountry().getName()); } if(address.getState()
	 * != null) { stringBuilder.append(address.getState().getName()); }
	 * if(address.getCity() != null) {
	 * stringBuilder.append(address.getCity().getName()); } }
	 * this.setSearchIndex(stringBuilder.toString()); super.save(); }
	 */

	/*
	 * @Override public void update() { final StringBuilder stringBuilder = new
	 * StringBuilder();
	 * stringBuilder.append(this.getAdvertId()+this.getTitle().trim().toLowerCase()+
	 * this.getSuggestedTitle().trim().toLowerCase()+""+this.getBusinessAdvertStatus
	 * ().toString().toLowerCase()+""+this.getListingType().toString().toLowerCase()
	 * ); Address address = Address.getBusinessAdvertAddress(this); if(address !=
	 * null) { if(address.getCountry() != null) {
	 * stringBuilder.append(address.getCountry().getName()); } if(address.getState()
	 * != null) { stringBuilder.append(address.getState().getName()); }
	 * if(address.getCity() != null) {
	 * stringBuilder.append(address.getCity().getName()); } }
	 * this.setSearchIndex(stringBuilder.toString());
	 * List<BusinessCategoryAdvertInfo> categoryInfos =
	 * BusinessCategoryAdvertInfo.getCategoryByListingInfos(this);
	 * if(categoryInfos.size() > 0) { for(BusinessCategoryAdvertInfo category :
	 * categoryInfos) { stringBuilder.append(category.getCategory().getName()); } }
	 * List<BusinessAdvertSubcategoryInfo> subcategoryInfos =
	 * BusinessAdvertSubcategoryInfo.getSubCategoryByListingInfos(this);
	 * if(subcategoryInfos.size() > 0) { for(BusinessAdvertSubcategoryInfo
	 * subCategory : subcategoryInfos) {
	 * stringBuilder.append(subCategory.getSubCategory().getName()); } }
	 * List<BusinessAdvertKeywordInfo> keyWordInfos =
	 * BusinessAdvertKeywordInfo.getBusinessAdvertKeywordsByListing(this);
	 * if(keyWordInfos.size() > 0) { for(BusinessAdvertKeywordInfo keywordInfo :
	 * keyWordInfos) {
	 * stringBuilder.append(keywordInfo.getCategoryKeyword().getKeyWord()); } }
	 * super.update(); }
	 * 
	 * public static List<BusinessAdvert> getUserAdverts(AppUser appUser,Role role){
	 * return BusinessAdvert.find.where().eq("isActive",
	 * Boolean.TRUE).eq("advertedByUser", appUser).eq("role",
	 * role).orderBy().desc("createdOn").findList(); }
	 * 
	 * public static List<BusinessAdvert> getLoggedInUserAdverts(){ AppUser appUser
	 * = LoginController.getLoggedInUser(); if(appUser != null) { Role role =
	 * appUser.getRole(); return BusinessAdvert.find.where().eq("isActive",
	 * Boolean.TRUE).eq("advertedByUser", appUser).eq("role", role).findList(); }
	 * return new ArrayList<BusinessAdvert>(); }
	 * 
	 * public static BusinessAdvert getBusinessAdvertByAdvertId(String advertId){
	 * return BusinessAdvert.find.where().eq("isActive",
	 * Boolean.TRUE).eq("advertId", advertId).setMaxRows(1).findUnique(); } public
	 * String getInvestmentRange(){ String range = "";
	 * if(this.getInvestmentRangeFrom() != null && this.getInvestmentRangeFrom() >
	 * 0) { range += this.getInvestmentRangeFrom().intValue(); }
	 * if(this.getInvestmentRangeTo() != null) { if(this.getInvestmentRangeTo() > 0)
	 * { if(StringUtils.isNotEmpty(range) && this.getInvestmentRangeFrom() != null
	 * && this.getInvestmentRangeFrom() > 0) { range +=
	 * " - "+this.getInvestmentRangeTo().intValue(); }else { range +=
	 * this.getInvestmentRangeTo().intValue(); } }else { range = " >"+range; } }
	 * if(StringUtils.isNotEmpty(range)) {
	 * if(this.getAdvertedByUser().getAppUserAddressCountry().
	 * getIsTobuzOperatedCountry() &&
	 * this.getAdvertedByUser().getAppUserAddressCountry().getCurrencyCode()!=null){
	 * range =
	 * this.getAdvertedByUser().getAppUserAddressCountry().getCurrencyCode()+" "
	 * +range; }else{ range = "USD"+" "+range; } } return range; }
	 * 
	 * public List<Address> advertAddressList(){ return
	 * Address.find.where().eq("businessAdvert", this).eq("isActive",
	 * Boolean.TRUE).findList(); }
	 * 
	 * public List<BusinessListingVisitInfo> advertViewCount(){ return
	 * BusinessListingVisitInfo.find.where().eq("businessAdvert",
	 * this).eq("isActive", Boolean.TRUE).findList(); }
	 * 
	 * public static List<BusinessAdvert> getFeaturedAdverts(Country country){
	 * ExpressionList<BusinessAdvert> listingQuery =
	 * BusinessAdvert.find.where().eq("isActive",
	 * Boolean.TRUE).eq("isRecomendedByAdmin",
	 * Boolean.TRUE).not(Expr.in("businessAdvertStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList()));; if(country !=
	 * null) { listingQuery.eq("advertedByUser.address.country", country); } return
	 * listingQuery.orderBy().desc("adminRecomendedOn").findList(); }
	 * 
	 * public static List<BusinessAdvert> getAllBusinessAdverts() { return
	 * BusinessAdvert.find.where().eq("isActive",
	 * Boolean.TRUE).orderBy().desc("createdOn").findList(); }
	 * 
	 * public static List<BusinessAdvert> getCountryBusinessAdverts(List<Country>
	 * countryList) { return BusinessAdvert.find.where().eq("isActive",
	 * Boolean.TRUE).in("addressList.country.id",countryList).findList(); }
	 * 
	 * public static List<BusinessAdvert> getFeaturedPaginatedAdverts(Country
	 * country,Integer pageNum,Integer pageSize){ ExpressionList<BusinessAdvert>
	 * listingQuery = BusinessAdvert.find.where().eq("isActive",
	 * Boolean.TRUE).eq("isRecomendedByAdmin",
	 * Boolean.TRUE).eq("businessAdvertStatus", BusinessListingStatus.APPROVED);
	 * if(country != null && country.getIsTobuzOperatedCountry()) {
	 * listingQuery.eq("advertCountry", country); } return
	 * listingQuery.isNotNull("adminRecomendedOn").orderBy().desc(
	 * "adminRecomendedOn").setMaxRows(20).findPagedList(pageNum,
	 * pageSize).getList(); }
	 * 
	 * public static List<BusinessAdvert> getBusinessAdverts(SearchBean adminBean){
	 * 
	 * AppUser appUser = LoginController.getLoggedInUser(); //adminBean.userRole=
	 * appUser.getRole().getUserRole();
	 * 
	 * //Logger.debug("adminBean"+adminBean);
	 * 
	 * List<String> countryIsoCodeList = adminBean.countryIsoCodeList; List<String>
	 * allCountryList = new ArrayList<>(); String fromDate = adminBean.fromDate;
	 * String toDate = adminBean.toDate; String searchKey = adminBean.searchKey;
	 * List<BusinessAdvert> businessAdvertList = new ArrayList<>();
	 * ExpressionList<BusinessAdvert> businessAdvertWhere =
	 * find.where().eq("isActive",Boolean.TRUE).ne("businessAdvertStatus",
	 * BusinessListingStatus.DRAFTED); if(StringUtils.isNotEmpty(searchKey)){
	 * businessAdvertWhere.or(Expr.ilike("advertId", searchKey.trim()+"%"),
	 * Expr.ilike("title", searchKey.trim()+"%")); }
	 * if(appUser.getRole().getUserRole().equals(UserRole.TOBUZ_SUB_ADMIN)){
	 * List<SubAdminAccessCountry> subAdminAccessCountryList =
	 * SubAdminAccessCountry.find.where().eq("isActive", Boolean.TRUE).eq("appUser",
	 * appUser).findList(); if(!subAdminAccessCountryList.isEmpty() &&
	 * subAdminAccessCountryList.size()>0){ for (SubAdminAccessCountry
	 * subAdminAccessCountry : subAdminAccessCountryList) {
	 * allCountryList.add(subAdminAccessCountry.getCountry().getIsoCode()); }
	 * businessAdvertWhere.in("addressList.country",allCountryList.stream().map(
	 * countryIsoCode->Country.getCountryByIsoCode(countryIsoCode)).collect(
	 * Collectors.toList())); businessAdvertList =
	 * getBusinessAdvertByAccessCountry(countryIsoCodeList,fromDate,toDate,
	 * businessAdvertWhere,appUser,adminBean); } }else{ businessAdvertList =
	 * getBusinessAdvertByAccessCountry(countryIsoCodeList,fromDate,toDate,
	 * businessAdvertWhere,appUser,adminBean); } return businessAdvertList; }
	 * private static List<BusinessAdvert>
	 * getBusinessAdvertByAccessCountry(List<String> countryIsoCodeList, String
	 * fromDate, String toDate, ExpressionList<BusinessAdvert> businessAdvertWhere,
	 * AppUser appUser, SearchBean adminBean) { if(countryIsoCodeList == null ||
	 * countryIsoCodeList.isEmpty()){ countryIsoCodeList = new ArrayList<String>();
	 * for(java.util.Map.Entry<String, Integer> entry :
	 * BusinessListing.getListingPendingApprovalCountByCountry(appUser.getRole(),
	 * appUser).entrySet()) { countryIsoCodeList.add(entry.getKey()); } }
	 * countryIsoCodeList = countryIsoCodeList.stream().filter(Objects::nonNull)
	 * .collect(Collectors.toList()); if(countryIsoCodeList.size() > 0) {
	 * if(countryIsoCodeList.contains("ALL")){ List<Country> countrysList =
	 * Country.countryList(); for (Country country : countrysList) {
	 * allCountryList.add(country.getIsoCode()); }
	 * //businessAdvertWhere.in("addressList.country",allCountryList.stream().map(
	 * countryIsoCode->Country.getCountryByIsoCode(countryIsoCode)).collect(
	 * Collectors.toList())); }else{
	 * businessAdvertWhere.in("addressList.country",countryIsoCodeList.stream().map(
	 * countryIsoCode->Country.getCountryByIsoCode(countryIsoCode)).collect(
	 * Collectors.toList())); } }
	 * 
	 * if (StringUtils.isNotEmpty(fromDate)) {
	 * businessAdvertWhere.isNotNull("createdOn").ge("createdOn",
	 * DateUtil.getFormattedDate(fromDate)); } if (StringUtils.isNotEmpty(toDate)) {
	 * businessAdvertWhere.isNotNull("createdOn").le("createdOn",
	 * DateUtil.getEndDate(DateUtil.getFormattedDate(toDate))); } return
	 * businessAdvertWhere.orderBy().desc("createdOn").findPagedList(adminBean.
	 * pageNum, 100).getList(); }
	 * 
	 * public static List<BusinessAdvert>
	 * getBusinessAdvertsByPackage(UserTobuzServicePackageInfo
	 * userTobuzServicePackageInfo) { return find.where().eq("isActive",
	 * Boolean.TRUE).eq("userTobuzServicePackageInfo",userTobuzServicePackageInfo).
	 * orderBy().desc("createdOn").findList(); } public static List<BusinessAdvert>
	 * getApprovedBusinessAdvertBySearch(String searchKey){
	 * ExpressionList<BusinessAdvert> advertQuery =
	 * BusinessAdvert.find.where().not(Expr.in("businessAdvertStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE); for(String key : searchKey.split(" ")) {
	 * advertQuery.ilike("searchIndex", "%" + key + "%"); }
	 * if(Country.getUserLocaleCountry() != null) {
	 * advertQuery.isNotNull("advertCountry").eq("advertCountry",
	 * Country.getUserLocaleCountry()); } return new
	 * ArrayList<BusinessAdvert>(advertQuery.orderBy().desc("createdOn").findSet());
	 * }
	 * 
	 * public static List<BusinessAdvert>
	 * getApprovedBusinessAdvertBySearchForAllCountry(String searchKey){
	 * ExpressionList<BusinessAdvert> advertQuery =
	 * BusinessAdvert.find.where().not(Expr.in("businessAdvertStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE); for(String key : searchKey.split(" ")) {
	 * advertQuery.ilike("searchIndex", "%" + key + "%"); }
	 * 
	 * return new
	 * ArrayList<BusinessAdvert>(advertQuery.orderBy().desc("createdOn").findSet());
	 * }
	 * 
	 * public static List<BusinessAdvert> getApprovedBusinessAdvertByCountry(Country
	 * country,ListingType listingType){ ExpressionList<BusinessAdvert> listingQuery
	 * = BusinessAdvert.find.where().not(Expr.in("businessAdvertStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE); if(country != null) {
	 * listingQuery.isNotNull("advertCountry").eq("advertCountry", country); }
	 * if(listingType != null) { listingQuery.eq("listingType", listingType); }
	 * return new
	 * ArrayList<BusinessAdvert>(listingQuery.isNotNull("adminApprovedOn").orderBy()
	 * .desc("adminApprovedOn").findPagedList(0,
	 * ResponseMessage.buyerInvestorPageCount).getList()); }
	 * 
	 * public static List<BusinessAdvert>
	 * getApprovedBusinessAdvertByCountryWithOutPagination(Country
	 * country,ListingType listingType){ ExpressionList<BusinessAdvert> listingQuery
	 * = BusinessAdvert.find.where().not(Expr.in("businessAdvertStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE); if(country != null) {
	 * listingQuery.isNotNull("advertCountry").eq("advertCountry", country); }
	 * if(listingType != null) { listingQuery.eq("listingType", listingType); }
	 * return new
	 * ArrayList<BusinessAdvert>(listingQuery.isNotNull("adminApprovedOn").orderBy()
	 * .desc("adminApprovedOn").findList()); }
	 * 
	 * public static List<BusinessAdvert>
	 * getApprovedBusinessAdvertBySearchWithLimit(String searchKey,Integer
	 * limit,AppUser appUser){ searchKey = searchKey.replaceAll("-", " ");
	 * ExpressionList<BusinessAdvert> advertQuery =
	 * BusinessAdvert.find.where().ilike("searchIndex", "%" +
	 * searchKey.replaceAll("\\s+", " ") + "%").not(Expr.in("businessAdvertStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE); if(appUser != null && appUser.getUserCountry() != null &&
	 * appUser.getUserCountry().getIsTobuzOperatedCountry()) {
	 * advertQuery.isNotNull("advertCountry").eq("advertCountry",
	 * appUser.getUserCountry()); } return new
	 * ArrayList<BusinessAdvert>(advertQuery.orderBy("RANDOM()").setMaxRows(limit).
	 * findSet()); }
	 * 
	 * public static List<BusinessAdvert>
	 * getApprovedBusinessAdvertBySearchWithLimitAndTime(String searchKey,Integer
	 * limit,AppUser appUser){ Calendar from = Calendar.getInstance();
	 * from.add(Calendar.DATE, -1); from.set(Calendar.HOUR_OF_DAY, 0);
	 * from.set(Calendar.MINUTE, 0); from.set(Calendar.SECOND, 0);
	 * from.set(Calendar.MILLISECOND, 0);
	 * 
	 * Calendar to = Calendar.getInstance(); to.set(Calendar.HOUR_OF_DAY, 23);
	 * to.set(Calendar.MINUTE, 59); to.set(Calendar.SECOND, 59);
	 * to.set(Calendar.MILLISECOND, 999); ExpressionList<BusinessAdvert> advertQuery
	 * = BusinessAdvert.find.where().ilike("searchIndex", "%" + searchKey +
	 * "%").not(Expr.in("businessAdvertStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE); if(appUser != null && appUser.getUserCountry() != null) {
	 * advertQuery.isNotNull("advertCountry").eq("advertCountry",
	 * appUser.getUserCountry()); }
	 * advertQuery.isNotNull("adminApprovedOn").ge("adminApprovedOn",
	 * from.getTime()).le("adminApprovedOn", to.getTime()); return new
	 * ArrayList<BusinessAdvert>(advertQuery.setMaxRows(limit).findSet()); }
	 * 
	 * public List<BusinessAdvertKeywordInfo> getKeyWords(){ return
	 * BusinessAdvertKeywordInfo.getBusinessAdvertKeywordsByListing(this); }
	 * 
	 * public FavouriteBusinessListing isLoggedInUserFavAdvert() { AppUser appUser =
	 * LoginController.getLoggedInUser(); if(appUser != null) { return
	 * FavouriteBusinessListing.find.where().eq("user", appUser).eq("role",
	 * appUser.getRole()).eq("businessAdvert", this).eq("isActive",
	 * Boolean.TRUE).setMaxRows(1).findUnique(); } return null; }
	 * 
	 * public String generateAdvertFullViewURL() { String url =
	 * PaymentConstant.HOST;
	 * if(StringUtils.isNotEmpty(controllers.HomeController.getCachedIsoCodeValue())
	 * ) { url += "/"+controllers.HomeController.getCachedIsoCodeValue(); }else
	 * if(this.getAdvertCountry() != null){ url +=
	 * "/"+this.getAdvertCountry().getIsoCode(); }else { url += "/ww"; }
	 * 
	 * if(this.getListingType() != null) { url +=
	 * "/"+this.getListingType().toString().toLowerCase(); }else { url +=
	 * "/investors"; }
	 * 
	 * url += "/investors";
	 * 
	 * if(StringUtils.isNotEmpty(this.getSuggestedTitle())) { url +=
	 * "/"+StringUtils.updateTextToSEO(this.getSuggestedTitle())+"/advertt"; }else {
	 * url += "/"+StringUtils.updateTextToSEO(this.getTitle())+"/advertt"; } url
	 * +="/"+this.getAdvertId(); //Logger.debug(url); return url; }
	 * 
	 * public String generateAdvertFullViewURLWothoutHost() { String url = "";
	 * if(StringUtils.isNotEmpty(controllers.HomeController.getCachedIsoCodeValue())
	 * ) { url += "/"+controllers.HomeController.getCachedIsoCodeValue(); }else {
	 * url += "/ww"; }
	 * 
	 * if(this.getListingType() != null) { url +=
	 * "/"+this.getListingType().toString().toLowerCase(); }else { url +=
	 * "/investors"; } url += "/investors";
	 * 
	 * if(StringUtils.isNotEmpty(this.getSuggestedTitle())) { url +=
	 * "/"+StringUtils.updateTextToSEO(this.getSuggestedTitle())+"/advertt"; }else {
	 * url += "/"+StringUtils.updateTextToSEO(this.getTitle())+"/advertt"; } url
	 * +="/"+this.getAdvertId(); return url; }
	 * 
	 * public static List<BusinessAdvert> getBusinessAdvertBySearch(String
	 * searchKey){ ExpressionList<BusinessAdvert> listingQuery =
	 * BusinessAdvert.find.where().not(Expr.in("businessAdvertStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE); for(String key : searchKey.split(" ")) {
	 * listingQuery.ilike("searchIndex", "%" + key + "%"); } for(String
	 * key:searchKey.split(" ")) { listingQuery.ilike("advertId", "%" + key + "%");
	 * } if(Country.getUserLocaleCountry() != null) {
	 * listingQuery.isNotNull("advertCountry").eq("advertCountry",
	 * Country.getUserLocaleCountry()); } return listingQuery.findList(); }
	 * 
	 * public static Map<String, Integer>
	 * getListingPendingApprovalCountByCountry(Role role, AppUser appUser) {
	 * Map<String, Integer> map = new HashMap<>(); if (role != null &&
	 * role.getUserRole() == UserRole.TOBUZ_ADMIN) { List<Country> countries =
	 * Country.find.where().eq("isActive",Boolean.TRUE).eq("isTobuzOperatedCountry",
	 * Boolean.TRUE).orderBy("sequence asc").findList(); for (Country country :
	 * countries) {
	 * 
	 * map.put(country.getIsoCode(), BusinessAdvert.find.where().eq("isActive",
	 * Boolean.TRUE).eq("businessAdvertStatus",
	 * BusinessListingStatus.UNDER_REVIEW).in("advertCountry.isoCode",country.
	 * getIsoCode()).orderBy("sequence asc").findList().size());
	 * 
	 * } }else if (role.getUserRole() == UserRole.TOBUZ_SUB_ADMIN) {
	 * List<SubAdminAccessCountry> subAdminAccessCountryList =
	 * SubAdminAccessCountry.find.where().eq("appUser.id",appUser.getId()).eq(
	 * "isActive",true).findList(); List<Country> countries =
	 * subAdminAccessCountryList.stream().map(subAdminAccessCountry ->
	 * subAdminAccessCountry.getCountry()).collect(Collectors.toList()); for
	 * (Country country : countries) { map.put(country.getIsoCode(),
	 * BusinessAdvert.find.where().eq("isActive",
	 * Boolean.TRUE).eq("businessAdvertStatus",
	 * BusinessListingStatus.UNDER_REVIEW).in("advertCountry.isoCode",country.
	 * getIsoCode()).orderBy("sequence asc").findList().size());
	 * 
	 * } } //appResponse.setPayLoad(map); return map; }
	 * 
	 * public Boolean isPaidAdvert(Long advertId){ Boolean isPaidListing =
	 * Boolean.FALSE; if(advertId!=null){ BusinessAdvert businessAdvert =
	 * BusinessAdvert.find.byId(advertId); if(businessAdvert !=null &&
	 * businessAdvert.getUserTobuzServicePackageInfo()!=null &&
	 * businessAdvert.getUserTobuzServicePackageInfo().getTobuzPackage()!=null){
	 * if(!businessAdvert.getUserTobuzServicePackageInfo().getTobuzPackage().
	 * getTobuzPackageType().equals(TobuzPackageType.FREE)){ isPaidListing =
	 * Boolean.TRUE; } } } return isPaidListing ; }
	 * 
	 * public Boolean isAdvertDoneByUser(Long advertId, AppUser appUser){ Boolean
	 * isOwner = Boolean.FALSE; if(advertId!=null){ BusinessAdvert businessAdvert =
	 * BusinessAdvert.find.byId(advertId); if(businessAdvert != null && appUser !=
	 * null && businessAdvert.getAdvertedByUser().getId().longValue() ==
	 * appUser.getId().longValue()){ isOwner = Boolean.TRUE; } } return isOwner ; }
	 * 
	 * public static List<BusinessAdvert> getSEOAdverts(){ List<BusinessAdvert>
	 * advertList = BusinessAdvert.find.where().not(Expr.in("businessAdvertStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE)
	 * .isNotNull("adminApprovedOn").orderBy().desc("adminApprovedOn").setMaxRows(
	 * 3000).findList(); //System.out.println("Advert Size :: "+advertList.size());
	 * return advertList; }
	 * 
	 * public String generateAdvertFullViewURLCron() { String url =
	 * PaymentConstant.HOST; if(this.getAdvertCountry() != null){ url +=
	 * "/"+this.getAdvertCountry().getIsoCode(); }else { url += "/ww"; }
	 * 
	 * if(this.getListingType() != null) { url +=
	 * "/"+this.getListingType().toString().toLowerCase(); }else { url +=
	 * "/investors"; } url += "/investors";
	 * 
	 * if(StringUtils.isNotEmpty(this.getTitle())) { url +=
	 * "/"+StringUtils.updateTextToSEO(this.getTitle())+"/advertt"; }else { url +=
	 * "/"+StringUtils.updateTextToSEO(this.getSuggestedTitle())+"/advertt"; } url
	 * +="/"+this.getAdvertId(); //Logger.debug(url); return url; }
	 * 
	 * public String generateAdvertFullViewURLWothoutHostCron() { String url = "";
	 * url += "/ww";
	 * 
	 * if(this.getListingType() != null) { url +=
	 * "/"+this.getListingType().toString().toLowerCase(); }else { url +=
	 * "/investors"; }
	 * 
	 * url += "/investors";
	 * 
	 * if(StringUtils.isNotEmpty(this.getTitle())) { url +=
	 * "/"+StringUtils.updateTextToSEO(this.getTitle())+"/advertt"; }else { url +=
	 * "/"+StringUtils.updateTextToSEO(this.getSuggestedTitle())+"/advertt"; } url
	 * +="/"+this.getAdvertId(); return url; }
	 * 
	 * public String getFormattedDateForSEO(Date date){ return
	 * DateUtil.getFormattedDateForSEO(date);
	 * 
	 * }
	 */
}
