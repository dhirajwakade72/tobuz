package com.tobuz.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class BusinessListing extends BaseEntity {

	@ManyToOne
	private AppUser listedByUser;

	@ManyToOne
	private Role role;

	private String listingId;

	private FranchiseFor franchiseFor;

	private String franchiseName;

	private String suggestedTitle;

	// Business,franchise,commercial and business_advert
	private String  listingType;

	// Rent / Sale(or)Lease / Others
	private String listingFor;

	private FranchisePartnerType franchisePartnerType;

	// Extra option for franchise resale Master/Unit
	// private FranchisePartnerType franchisePartnerType;

	// BusinessTitle
	private String title;

	@Column(columnDefinition = "TEXT")
	private String listingDescription;

	@ManyToOne
	private Category Category;

	@Column(columnDefinition = "TEXT")
	private String seoKeyword;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "businessListing")
	private List<ListingKeyword> keywordList = new ArrayList<ListingKeyword>();

	@ManyToOne
	private Country businessAddressCountry;

	// SEO params
	private String slugTitle;

	private String metaTitle;

	private String metaDescription;

	private String metaUrl;

	private String businessListingStatus;

	// contact details

	private String name;

	private String email;

	private String countryCode;

	private String contactNumber;

	/*
	 * @Column(name ="") private String websiteURL;
	 */

	private Boolean showContactDetailsOnListing = Boolean.FALSE;

	private Date postedOn;

	private Date soldOn;

	/* private ListingAdvertLable listingLable; */

	@ManyToOne
	private AppUser soldMarkedBy;

	
	@OneToOne 
	private BusinessListingOutLet businessListingOutLet;
	
	/*
	 * @ManyToOne private UserTobuzServicePackageInfo userTobuzServicePackageInfo;
	 */ 

	/**
	 * If it is distress sale Needs to be in paid package
	 */
	private Boolean isDistressSale = Boolean.FALSE;

	private String description;

	@Column(columnDefinition = "TEXT")
	private String searchIndex;

	private Boolean isRecomendedByAdmin = Boolean.FALSE;

	private Date adminRecomendedOn;

	private Integer noOfOutlets = 1;

	private Date expiredOn;

	private Date adminApprovedOn;

	private Date adminDeletedOn;

	@ManyToOne
	private AppUser removedBy;

	private Boolean isAdminResponded = Boolean.FALSE;

//	@OneToMany(cascade = CascadeType.ALL)
	/*
	 * private List<FileEntity> documentList = new ArrayList<FileEntity>();
	 */
	@Column(columnDefinition = "TEXT")
	private String adminComment;

	@Column(columnDefinition = "TEXT")
	private String listingKeywords;

	private Double latitude;

	private Double longitude;

	private Boolean isDraftReminderSent = Boolean.FALSE;

	private Boolean isListingMarkedWorldWide = Boolean.FALSE;
	
	@Transient
	public List<FileEntity> listingGallery ;
	
	@Transient
	private List<BusinessListingFeatureInfo> businessListingFeatureInfoList ;
	
	

	public List<FileEntity> getListingGallery() {
		return listingGallery;
	}

	public void setListingGallery(List<FileEntity> listingGallery) {
		this.listingGallery = listingGallery;
	}

	public Boolean getIsListingMarkedWorldWide() {
		return isListingMarkedWorldWide;
	}

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

	public void setIsListingMarkedWorldWide(Boolean isListingMarkedWorldWide) {
		this.isListingMarkedWorldWide = isListingMarkedWorldWide;
	}

	public Boolean getIsDraftReminderSent() {
		return isDraftReminderSent;
	}

	public Date getAdminRecomendedOn() {
		return adminRecomendedOn;
	}

	public void setAdminRecomendedOn(Date adminRecomendedOn) {
		this.adminRecomendedOn = adminRecomendedOn;
	}

	public void setIsDraftReminderSent(Boolean isDraftReminderSent) {
		this.isDraftReminderSent = isDraftReminderSent;
	}

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

	public String getAdminComment() {
		return adminComment;
	}

	public void setAdminComment(String adminComment) {
		this.adminComment = adminComment;
	}

	public Boolean getIsAdminResponded() {
		return isAdminResponded;
	}

	public void setIsAdminResponded(Boolean isAdminResponded) {
		this.isAdminResponded = isAdminResponded;
	}

	/*
	 * public static Model.Finder<Long, BusinessListing> find = new
	 * Model.Finder<Long, BusinessListing>(BusinessListing.class);
	 */

	public String getListingKeywords() {
		return listingKeywords;
	}

	public void setListingKeywords(String listingKeywords) {
		this.listingKeywords = listingKeywords;
	}

	public String getSuggestedTitle() {
		return suggestedTitle;
	}

	public void setSuggestedTitle(String suggestedTitle) {
		this.suggestedTitle = suggestedTitle;
	}

	public Date getExpiredOn() {
		return expiredOn;
	}

	public void setExpiredOn(Date expiredOn) {
		this.expiredOn = expiredOn;
	}

	public Date getAdminApprovedOn() {
		return adminApprovedOn;
	}

	public void setAdminApprovedOn(Date adminApprovedOn) {
		this.adminApprovedOn = adminApprovedOn;
	}

	public Boolean getIsRecomendedByAdmin() {
		return isRecomendedByAdmin;
	}

	public void setIsRecomendedByAdmin(Boolean isRecomendedByAdmin) {
		this.isRecomendedByAdmin = isRecomendedByAdmin;
	}

	public String getFranchiseName() {
		return franchiseName;
	}

	public void setFranchiseName(String franchiseName) {
		this.franchiseName = franchiseName;
	}

	public Integer getNoOfOutlets() {
		return noOfOutlets;
	}

	public void setNoOfOutlets(Integer noOfOutlets) {
		this.noOfOutlets = noOfOutlets;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getListingId() {
		return this.listingId = "L"+getId();
	}

	public String getSuggestedtitle() {
		return suggestedTitle;
	}

	public void setSuggestedtitle(String suggestedtitle) {
		this.suggestedTitle = suggestedtitle;
	}

	public void setListingId(String listingId) {
		this.listingId = "L"+getId();
	}

	public String getSearchIndex() {
		return searchIndex;
	}

	public void setSearchIndex(String searchIndex) {
		this.searchIndex = searchIndex;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getListingDescription() {
		return listingDescription;
	}

	public void setListingDescription(String listingDescription) {
		this.listingDescription = listingDescription;
	}

	
	public String getListingFor() {
		return listingFor;
	}

	public void setListingFor(String listingFor) {
		this.listingFor = listingFor;
	}

	public Category getCategory() {
		return Category;
	}

	public void setCategory(Category category) {
		Category = category;
	}

	public String getSeoKeyword() {
		return seoKeyword;
	}

	public void setSeoKeyword(String seoKeyword) {
		this.seoKeyword = seoKeyword;
	}

	public Country getBusinessAddressCountry() {
		return businessAddressCountry;
	}

	public void setBusinessAddressCountry(Country businessAddressCountry) {
		this.businessAddressCountry = businessAddressCountry;
	}

	/*
	 * public BusinessListingOutLet getBusinessListingOutLet() { return
	 * businessListingOutLet; }
	 * 
	 * public void setBusinessListingOutLet(BusinessListingOutLet
	 * businessListingOutLet) { this.businessListingOutLet = businessListingOutLet;
	 * }
	 */

	public String getSlugTitle() {
		return slugTitle;
	}

	public void setSlugTitle(String slugTitle) {
		this.slugTitle = slugTitle;
	}

	public String getMetaTitle() {
		return metaTitle;
	}

	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getMetaUrl() {
		return metaUrl;
	}

	public void setMetaUrl(String metaUrl) {
		this.metaUrl = metaUrl;
	}

	
	public String getBusinessListingStatus() {
		return businessListingStatus;
	}

	public void setBusinessListingStatus(String businessListingStatus) {
		this.businessListingStatus = businessListingStatus;
	}

	public AppUser getListedByUser() {
		return listedByUser;
	}

	public void setListedByUser(AppUser listedByUser) {
		this.listedByUser = listedByUser;
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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/*
	 * public String getWebsiteURL() { return websiteURL; }
	 */
	/*
	 * public void setWebsiteURL(String websiteURL) { this.websiteURL = websiteURL;
	 * }
	 */
	public Boolean getShowContactDetailsOnListing() {
		return showContactDetailsOnListing;
	}

	public void setShowContactDetailsOnListing(Boolean showContactDetailsOnListing) {
		this.showContactDetailsOnListing = showContactDetailsOnListing;
	}

	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	public Date getSoldOn() {
		return soldOn;
	}

	public void setSoldOn(Date soldOn) {
		this.soldOn = soldOn;
	}

	public AppUser getSoldMarkedBy() {
		return soldMarkedBy;
	}

	public void setSoldMarkedBy(AppUser soldMarkedBy) {
		this.soldMarkedBy = soldMarkedBy;
	}

	public FranchiseFor getFranchiseFor() {
		return franchiseFor;
	}

	public void setFranchiseFor(FranchiseFor franchiseFor) {
		this.franchiseFor = franchiseFor;
	}

	public List<ListingKeyword> getKeywordList() {
		return keywordList;
	}

	public void setKeywordList(List<ListingKeyword> keywordList) {
		this.keywordList = keywordList;
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

	public Boolean getIsDistressSale() {
		return isDistressSale;
	}

	public void setIsDistressSale(Boolean isDistressSale) {
		this.isDistressSale = isDistressSale;
	}

	public FranchisePartnerType getFranchisePartnerType() {
		return franchisePartnerType;
	}

	public void setFranchisePartnerType(FranchisePartnerType franchisePartnerType) {
		this.franchisePartnerType = franchisePartnerType;
	}

	public BusinessListingOutLet getBusinessListingOutLet() {
		return businessListingOutLet;
	}

	public void setBusinessListingOutLet(BusinessListingOutLet businessListingOutLet) {
		this.businessListingOutLet = businessListingOutLet;
	}

	public List<BusinessListingFeatureInfo> getBusinessListingFeatureInfoList() {
		return businessListingFeatureInfoList;
	}

	public void setBusinessListingFeatureInfoList(List<BusinessListingFeatureInfo> businessListingFeatureInfoList) {
		this.businessListingFeatureInfoList = businessListingFeatureInfoList;
	}

	
	
	/*
	 * public List<FileEntity> getDocumentList() { return documentList; }
	 * 
	 * public void setDocumentList(List<FileEntity> documentList) {
	 * this.documentList = documentList; }
	 */
	
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
	 * stringBuilder.append(this.getTitle().trim().toLowerCase()+""+this.
	 * getListingId()!=null?this.getListingId():""+""+this.getBusinessListingStatus(
	 * ).toString().toLowerCase()!=null?this.getBusinessListingStatus().toString().
	 * toLowerCase():""+""+this.getListingType().toString().toLowerCase());
	 * if(this.listingFor != null) {
	 * stringBuilder.append(this.getListingFor().toString().toLowerCase()); }else {
	 * stringBuilder.append("sale"); } if(this.getBusinessListingOutLet() != null &&
	 * this.getBusinessListingOutLet().getBusinessAddress() != null){ Address
	 * address = this.getBusinessListingOutLet().getBusinessAddress();
	 * if(StringUtils.isNotEmpty(address.getDetailedAddress())){
	 * stringBuilder.append(address.getDetailedAddress().toLowerCase()); }
	 * if(address.getCountry() != null){
	 * stringBuilder.append(address.getCountry().getName().trim().toLowerCase()); }
	 * if(address.getState() != null){
	 * stringBuilder.append(address.getState().getName().trim().toLowerCase()); }
	 * if(address.getCity() != null){
	 * stringBuilder.append(address.getCity().getName().trim().toLowerCase()); } }
	 * if(this.getCategory() != null){
	 * stringBuilder.append(this.getCategory().getName().trim().toLowerCase()); }
	 * this.setSearchIndex(stringBuilder.toString()); super.save(); }
	 * 
	 * @Override public void update() { final StringBuilder stringBuilder = new
	 * StringBuilder();
	 * stringBuilder.append(this.getTitle().trim().toLowerCase()+""+this.
	 * getListingId()+""+this.getBusinessListingStatus().toString().toLowerCase()+""
	 * +this.getListingType().toString().toLowerCase()); if(this.listingFor != null)
	 * { stringBuilder.append(this.getListingFor().toString().toLowerCase()); }else
	 * { stringBuilder.append("sale"); } if(this.getBusinessListingOutLet() != null
	 * && this.getBusinessListingOutLet().getBusinessAddress() != null){ Address
	 * address = this.getBusinessListingOutLet().getBusinessAddress();
	 * if(StringUtils.isNotEmpty(address.getDetailedAddress())){
	 * stringBuilder.append(address.getDetailedAddress().toLowerCase()); }
	 * if(address.getCountry() != null){
	 * stringBuilder.append(address.getCountry().getName().trim().toLowerCase()); }
	 * if(address.getState() != null){
	 * stringBuilder.append(address.getState().getName().trim().toLowerCase()); }
	 * if(address.getCity() != null){
	 * stringBuilder.append(address.getCity().getName().trim().toLowerCase()); } }
	 * if(this.getCategory() != null){
	 * stringBuilder.append(this.getCategory().getName().trim().toLowerCase()); }
	 * List<BusinessListingSubcategoryInfo> subcategoryInfos =
	 * BusinessListingSubcategoryInfo.getSubCategoryByListingInfos(this);
	 * if(subcategoryInfos.size() > 0) { for(BusinessListingSubcategoryInfo
	 * subCategory : subcategoryInfos) {
	 * stringBuilder.append(subCategory.getSubCategory().getName()); } }
	 * List<BusinessListingKeywordInfo> keyWordInfos =
	 * BusinessListingKeywordInfo.getBusinessListngBySubCategoryInfosByListing(this)
	 * ; if(keyWordInfos.size() > 0) { for(BusinessListingKeywordInfo keywordInfo :
	 * keyWordInfos) {
	 * stringBuilder.append(keywordInfo.getCategoryKeyword().getKeyWord()); } }
	 * this.setSearchIndex(stringBuilder.toString()); super.update();
	 * 
	 * }
	 * 
	 * 
	 * public static List<BusinessListing> getUserListings(AppUser appUser,Role
	 * role){ return BusinessListing.find.where().eq("isActive",
	 * Boolean.TRUE).eq("listedByUser", appUser).eq("role", role).findList(); }
	 * 
	 * public static List<BusinessListing> getFeaturedListings(Country country){
	 * ExpressionList<BusinessListing> listingQuery =
	 * BusinessListing.find.where().eq("isActive",
	 * Boolean.TRUE).eq("isRecomendedByAdmin",
	 * Boolean.TRUE).or(Expr.eq("businessListingStatus",
	 * BusinessListingStatus.APPROVED),Expr.eq("businessListingStatus",
	 * BusinessListingStatus.SOLD)); ExpressionList<BusinessListing> duplicateQuery
	 * = BusinessListing.find.where().eq("isActive",
	 * Boolean.TRUE).eq("isRecomendedByAdmin",
	 * Boolean.TRUE).or(Expr.eq("businessListingStatus",
	 * BusinessListingStatus.APPROVED),Expr.eq("businessListingStatus",
	 * BusinessListingStatus.SOLD)); if(country != null &&
	 * country.getIsTobuzOperatedCountry()) {
	 * listingQuery.isNotNull("businessListingOutLet").eq(
	 * "businessListingOutLet.businessAddress.country", country); }
	 * if(listingQuery.findList().size() == 0) { listingQuery = duplicateQuery; }
	 * return
	 * listingQuery.orderBy().desc("adminRecomendedOn").setMaxRows(20).findList(); }
	 * 
	 * public static List<BusinessListing> getFeaturedListingsLandingPage(Country
	 * country){ ExpressionList<BusinessListing> listingQuery =
	 * BusinessListing.find.where().eq("isActive", Boolean.TRUE)
	 * .eq("isRecomendedByAdmin", Boolean.TRUE) .eq("businessListingStatus",
	 * BusinessListingStatus.APPROVED); if(country != null &&
	 * country.getIsTobuzOperatedCountry()) {
	 * listingQuery.eq("businessAddressCountry", country); } //
	 * if(listingQuery.findList().size() == 0) { // listingQuery = duplicateQuery;
	 * // } return
	 * listingQuery.orderBy().desc("adminRecomendedOn").setMaxRows(20).findList(); }
	 * 
	 * public static List<BusinessListing> getFeaturedListingsLandingPageGlobal(){
	 * ExpressionList<BusinessListing> listingQuery =
	 * BusinessListing.find.where().eq("isActive", Boolean.TRUE)
	 * .eq("isRecomendedByAdmin", Boolean.TRUE) .eq("businessListingStatus",
	 * BusinessListingStatus.APPROVED); return
	 * listingQuery.orderBy().desc("adminRecomendedOn").setMaxRows(20).findList(); }
	 * 
	 * public static List<BusinessListing> getFeaturedPaginatedListings(Country
	 * country,Integer pageSize, Integer pageNum){ ExpressionList<BusinessListing>
	 * listingQuery = BusinessListing.find.where().eq("isActive",
	 * Boolean.TRUE).eq("isRecomendedByAdmin",
	 * Boolean.TRUE).or(Expr.eq("businessListingStatus",
	 * BusinessListingStatus.APPROVED),Expr.eq("businessListingStatus",
	 * BusinessListingStatus.SOLD)); if(country != null &&
	 * country.getIsTobuzOperatedCountry()) {
	 * listingQuery.isNotNull("businessListingOutLet").eq(
	 * "businessListingOutLet.businessAddress.country", country); } return
	 * listingQuery.orderBy().desc("lastUpdate").findPagedList(pageNum,
	 * pageSize).getList(); }
	 * 
	 * public static List<BusinessListing> getDistressListings(Country country){
	 * ExpressionList<BusinessListing> listingQuery =
	 * BusinessListing.find.where().eq("isActive",
	 * Boolean.TRUE).eq("isDistressSale", Boolean.TRUE); if(country != null &&
	 * country.getIsTobuzOperatedCountry()) {
	 * listingQuery.isNotNull("businessListingOutLet").eq(
	 * "businessListingOutLet.businessAddress.country", country); } return
	 * listingQuery.findList(); }
	 * 
	 * 
	 * public static List<BusinessListing> getDistressPaginatedListings(Country
	 * country,Integer pageSize, Integer pageNum){ ExpressionList<BusinessListing>
	 * listingQuery = BusinessListing.find.where().eq("isActive",
	 * Boolean.TRUE).not(Expr.in("businessListingStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq(
	 * "isDistressSale", Boolean.TRUE); if(country != null &&
	 * country.getIsTobuzOperatedCountry()) {
	 * listingQuery.isNotNull("businessListingOutLet").eq(
	 * "businessListingOutLet.businessAddress.country", country); } return
	 * listingQuery.orderBy().desc("lastUpdate").findPagedList(pageNum,
	 * pageSize).getList(); }
	 * 
	 * public List<BusinessListingVisitInfo> listingViewCount(){ return
	 * BusinessListingVisitInfo.find.where().eq("businessListing",
	 * this).eq("isActive", Boolean.TRUE).findList(); }
	 * 
	 * public static List<BusinessListing> getUserListings(AppUser appUser,Role
	 * role,ListingType listingType){ return
	 * BusinessListing.find.where().eq("isActive", Boolean.TRUE).eq("listedByUser",
	 * appUser).eq("role", role).eq("listingType",
	 * listingType).orderBy().desc("createdOn").findList(); }
	 * 
	 * public List<FileEntity> getListingGallery(){ return
	 * FileEntity.find.where().eq("businessListing", this).eq("fileType",
	 * "IMAGE").findList(); }
	 * 
	 * public List<BusinessListingAmenityInfo> getListingAmenities(){ return
	 * BusinessListingAmenityInfo.find.where().eq("businessListing",
	 * this).findList(); }
	 * 
	 * public List<BusinessListingFeatureInfo> getListingFeatures(){ return
	 * BusinessListingFeatureInfo.find.where().eq("businessListing",
	 * this).findList(); }
	 * 
	 * public String getSeoTitleName() { return
	 * StringUtils.updateTextToSEO(this.getTitle()); }
	 * 
	 * public static List<BusinessListing> getUserListings(AppUser appUser, Role
	 * role, FranchiseFor franchiseFor) { return
	 * BusinessListing.find.where().eq("isActive", Boolean.TRUE).eq("listedByUser",
	 * appUser).eq("role", role).eq("franchiseFor",
	 * franchiseFor).orderBy().desc("createdOn").findList(); }
	 * 
	 * public static List<BusinessListing> getCountryBusinessListings(List<Country>
	 * countryList) { return find.where().eq("isActive",
	 * Boolean.TRUE).in("businessAddressCountry",countryList).findList(); }
	 * 
	 * public static List<BusinessListing>
	 * getBusinessListingsByPackage(UserTobuzServicePackageInfo
	 * userTobuzServicePackageInfo) { return find.where().eq("isActive",
	 * Boolean.TRUE).eq("userTobuzServicePackageInfo",userTobuzServicePackageInfo).
	 * findList(); }
	 * 
	 * 
	 * public static List<BusinessListing> getBusinessListings(SearchBean
	 * searchBean, String searchKey){
	 * 
	 * AppUser appUser = LoginController.getLoggedInUser(); searchBean.userRole=
	 * appUser.getRole().getUserRole(); List<String> countryIsoCodeList =
	 * searchBean.countryIsoCodeList; List<String> userRoleList =
	 * searchBean.userRoleList; String fromDate = searchBean.fromDate; String toDate
	 * = searchBean.toDate; List<String> allCountryList = new ArrayList<>();
	 * List<BusinessListing> businessListingsList = new ArrayList<>();
	 * ExpressionList<BusinessListing> businessListingWhere =
	 * find.where().eq("isActive", Boolean.TRUE).ne("businessListingStatus",
	 * BusinessListingStatus.DRAFTED); if(StringUtils.isNotEmpty(searchKey)){
	 * businessListingWhere.or(Expr.ilike("listingId", searchKey.trim()+"%"),
	 * Expr.ilike("title", searchKey.trim()+"%")); }
	 * if(appUser.getRole().getUserRole().equals(UserRole.TOBUZ_SUB_ADMIN)){
	 * List<SubAdminAccessCountry> subAdminAccessCountryList =
	 * SubAdminAccessCountry.find.where().eq("isActive", Boolean.TRUE).eq("appUser",
	 * appUser).findList(); if(!subAdminAccessCountryList.isEmpty() &&
	 * subAdminAccessCountryList.size()>0){ for (SubAdminAccessCountry
	 * subAdminAccessCountry : subAdminAccessCountryList) {
	 * allCountryList.add(subAdminAccessCountry.getCountry().getIsoCode()); }
	 * businessListingWhere.in("businessAddressCountry",allCountryList.stream().map(
	 * countryIsoCode->Country.getCountryByIsoCode(countryIsoCode)).collect(
	 * Collectors.toList()));
	 * 
	 * businessListingsList=getBusinessListingsByAccessCountry(countryIsoCodeList,
	 * userRoleList,fromDate,toDate,businessListingWhere,searchBean,appUser);
	 * 
	 * } }else{
	 * businessListingsList=getBusinessListingsByAccessCountry(countryIsoCodeList,
	 * userRoleList,fromDate,toDate,businessListingWhere,searchBean,appUser); }
	 * 
	 * return businessListingsList; }
	 * 
	 * private static List<BusinessListing>
	 * getBusinessListingsByAccessCountry(List<String> countryIsoCodeList,
	 * List<String> userRoleList, String fromDate, String toDate,
	 * ExpressionList<BusinessListing> businessListingWhere, SearchBean searchBean,
	 * AppUser appUser) {
	 * 
	 * countryIsoCodeList = countryIsoCodeList.stream().filter(Objects::nonNull)
	 * .collect(Collectors.toList()); if(countryIsoCodeList == null ||
	 * countryIsoCodeList.isEmpty()){ countryIsoCodeList = new ArrayList<String>();
	 * for(java.util.Map.Entry<String, Integer> entry :
	 * getListingPendingApprovalCountByCountry(appUser.getRole(),appUser).entrySet()
	 * ) { countryIsoCodeList.add(entry.getKey()); } } if(countryIsoCodeList.size()
	 * > 0) { if(countryIsoCodeList.contains("ALL")){ List<Country> countrysList =
	 * Country.countryList(); for (Country country : countrysList) {
	 * allCountryList.add(country.getIsoCode()); }
	 * businessListingWhere.in("businessAddressCountry",allCountryList.stream().map(
	 * countryIsoCode->Country.getCountryByIsoCode(countryIsoCode)).collect(
	 * Collectors.toList())); }else{
	 * businessListingWhere.in("businessAddressCountry",countryIsoCodeList.stream().
	 * map(countryIsoCode->Country.getCountryByIsoCode(countryIsoCode)).collect(
	 * Collectors.toList())); } } if(userRoleList != null &&
	 * !userRoleList.isEmpty()){
	 * businessListingWhere.in("role.userRole",userRoleList.stream().map(strRole->
	 * UserRole.valueOf(strRole)).distinct().collect(Collectors.toList())); }
	 * 
	 * if (StringUtils.isNotEmpty(fromDate)) { businessListingWhere.ge("createdOn",
	 * DateUtil.getFormattedDate(fromDate)); } if (StringUtils.isNotEmpty(toDate)) {
	 * businessListingWhere.le("createdOn",
	 * DateUtil.getEndDate(DateUtil.getFormattedDate(toDate))); }
	 * if(searchBean.userRole == UserRole.TOBUZ_ADMIN){
	 * 
	 * }else if(searchBean.userRole == UserRole.TOBUZ_SUB_ADMIN){
	 * 
	 * } return
	 * businessListingWhere.orderBy().desc("createdOn").findPagedList(searchBean.
	 * pageNum, 100).getList(); }
	 * 
	 * public static List<BusinessListing> getBusinessListingBySearch(String
	 * searchKey){ ExpressionList<BusinessListing> listingQuery =
	 * BusinessListing.find.where().not(Expr.in("businessListingStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE); for(String key : searchKey.split(" ")) {
	 * listingQuery.ilike("searchIndex", "%" + key + "%"); } for(String
	 * key:searchKey.split("")) { listingQuery.ilike("listingId", "%"+key+"%"); }
	 * if(Country.getUserLocaleCountry() != null) {
	 * listingQuery.isNotNull("businessAddressCountry").eq("businessAddressCountry",
	 * Country.getUserLocaleCountry()); } return listingQuery.findList(); } public
	 * static List<BusinessListing> getBusinessListingBySearchCategory(String
	 * searchKey){ ExpressionList<BusinessListing> listingQuery =
	 * BusinessListing.find.where().not(Expr.in("businessListingStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE); for(String key : searchKey.split(" ")) {
	 * listingQuery.ilike("searchIndex", "%" + key + "%"); }
	 * if(Country.getUserLocaleCountry() != null) {
	 * listingQuery.isNotNull("businessAddressCountry").eq("businessAddressCountry",
	 * Country.getUserLocaleCountry()); } return listingQuery.findList(); }
	 * 
	 * public static List<BusinessListing> getBusinessListingGlobalSearch(String
	 * searchKey,ListingType type){ ExpressionList<BusinessListing> listingQuery =
	 * BusinessListing.find.where().not(Expr.in("businessListingStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE); for(String key : searchKey.split(" ")) {
	 * listingQuery.ilike("searchIndex", "%" + key + "%"); } for(String
	 * key:searchKey.split("")) { listingQuery.ilike("listingId", "%"+key+"%"); }
	 * if(Country.getUserLocaleCountry() != null) {
	 * listingQuery.isNotNull("businessAddressCountry").eq("businessAddressCountry",
	 * Country.getUserLocaleCountry()); } if(type !=null) {
	 * listingQuery.eq("listingType", type); } listingQuery.setMaxRows(10); return
	 * listingQuery.findList(); }
	 * 
	 * public static List<BusinessListing> getApprovedBusinessListingBySearch(String
	 * searchKey){ //Logger.debug("search key == "+searchKey);
	 * ExpressionList<BusinessListing> listingQuery =
	 * BusinessListing.find.where().not(Expr.in("businessListingStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE); //
	 * Logger.debug("search key1 == "+listingQuery.findList().size()); for(String
	 * key : searchKey.split("-")) { listingQuery.ilike("searchIndex", "%" + key +
	 * "%"); } if(Country.getUserLocaleCountry() != null) {
	 * listingQuery.isNotNull("businessAddressCountry").eq("businessAddressCountry",
	 * Country.getUserLocaleCountry()); }
	 * //Logger.debug("search key2 == "+listingQuery.findList().size()+
	 * "searchKey==>"+searchKey); return new
	 * ArrayList<BusinessListing>(listingQuery.orderBy().desc("createdOn").findSet()
	 * ); } public static List<BusinessListing>
	 * getApprovedBusinessListingBySearchForAllCountry(String searchKey){
	 * //Logger.debug("search key == "+searchKey); ExpressionList<BusinessListing>
	 * listingQuery =
	 * BusinessListing.find.where().not(Expr.in("businessListingStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE); //
	 * Logger.debug("search key1 == "+listingQuery.findList().size()); for(String
	 * key : searchKey.split("-")) { listingQuery.ilike("searchIndex", "%" + key +
	 * "%"); }
	 * 
	 * //Logger.debug("search key2 == "+listingQuery.findList().size()+
	 * "searchKey==>"+searchKey); return new
	 * ArrayList<BusinessListing>(listingQuery.orderBy().desc("createdOn").findSet()
	 * ); } public static List<BusinessListing>
	 * getApprovedBusinessListingByCountry(Country country,ListingType listingType){
	 * Date date = DateUtil.getDateDifferenceByTwoYear(new Date());
	 * ExpressionList<BusinessListing> listingQuery =
	 * BusinessListing.find.where().not(Expr.in("businessListingStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE); if(country != null) {
	 * listingQuery.or(Expr.and(Expr.isNotNull("businessAddressCountry"),Expr.eq(
	 * "businessAddressCountry",country)),Expr.eq("isListingMarkedWorldWide",Boolean
	 * .TRUE)); }else {
	 * listingQuery.or(Expr.isNotNull("businessAddressCountry"),Expr.eq(
	 * "isListingMarkedWorldWide",Boolean.TRUE)); } if(listingType != null) {
	 * listingQuery.eq("listingType",listingType); } return new
	 * ArrayList<BusinessListing>(listingQuery.ge("adminApprovedOn",
	 * date).orderBy().desc("createdOn").findSet()); }
	 * 
	 * public static List<BusinessListing>
	 * getApprovedDistressListingByCountry(Country country,ListingType listingType){
	 * ExpressionList<BusinessListing> listingQuery =
	 * BusinessListing.find.where().eq("isDistressSale",
	 * Boolean.TRUE).not(Expr.in("businessListingStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE); if(country != null) {
	 * listingQuery.or(Expr.and(Expr.isNotNull("businessAddressCountry"),Expr.eq(
	 * "businessAddressCountry",country)),Expr.eq("isListingMarkedWorldWide",Boolean
	 * .TRUE)); }else {
	 * listingQuery.or(Expr.isNotNull("businessAddressCountry"),Expr.eq(
	 * "isListingMarkedWorldWide",Boolean.TRUE)); } if(listingType != null) {
	 * listingQuery.eq("listingType", listingType); } return new
	 * ArrayList<BusinessListing>(listingQuery.findSet()); }
	 * 
	 * public FavouriteBusinessListing isLoggedInUserFavListing() { AppUser appUser
	 * = LoginController.getLoggedInUser(); if(appUser != null) { return
	 * FavouriteBusinessListing.find.where().eq("user", appUser).eq("role",
	 * appUser.getRole()).eq("businessListing", this).eq("isActive",
	 * Boolean.TRUE).setMaxRows(1).findUnique(); } return null; }
	 * 
	 * public static List<BusinessListing> getUserAvailableListings(AppUser
	 * appUser){ return BusinessListing.find.where().eq("isActive",
	 * Boolean.TRUE).eq("listedByUser",
	 * appUser).not(Expr.in("businessListingStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).findList();
	 * 
	 * }
	 * 
	 * public static List<BusinessListing>
	 * getUserListingsByPackage(UserTobuzServicePackageInfo
	 * userTobuzServicePackageInfo,ListingType listingType){ return
	 * BusinessListing.find.where().eq("isActive",
	 * Boolean.TRUE).eq("userTobuzServicePackageInfo",userTobuzServicePackageInfo).
	 * eq("listingType", listingType).orderBy().desc("createdOn").findList(); }
	 * 
	 * public static List<BusinessListing>
	 * getUserListingsByPackageAndFranchise(UserTobuzServicePackageInfo
	 * userTobuzServicePackageInfo, FranchiseFor franchiseFor) { return
	 * BusinessListing.find.where().eq("isActive", Boolean.TRUE).eq("listingType",
	 * ListingType.FRANCHISE).eq("userTobuzServicePackageInfo",
	 * userTobuzServicePackageInfo).eq("franchiseFor",
	 * franchiseFor).orderBy().desc("createdOn").findList(); }
	 * 
	 * public static List<BusinessListing>
	 * getApprovedBusinessListingBySearchWithLimit(String searchKey,Integer
	 * limit,AppUser appUser){ searchKey = searchKey.replaceAll("-", " ");
	 * ExpressionList<BusinessListing> listingQuery =
	 * BusinessListing.find.where().ilike("searchIndex", "%" +
	 * searchKey.replaceAll("\\s+", " ") + "%").not(Expr.in("businessListingStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE); if(appUser != null && appUser.getUserCountry() != null &&
	 * appUser.getUserCountry().getIsTobuzOperatedCountry()) {
	 * listingQuery.or(Expr.and(Expr.isNotNull("businessAddressCountry"),Expr.eq(
	 * "businessAddressCountry",appUser.getUserCountry())),Expr.eq(
	 * "isListingMarkedWorldWide",Boolean.TRUE)); }else {
	 * listingQuery.or(Expr.isNotNull("businessAddressCountry"),Expr.eq(
	 * "isListingMarkedWorldWide",Boolean.TRUE)); } return new
	 * ArrayList<BusinessListing>(listingQuery.orderBy("RANDOM()").setMaxRows(limit)
	 * .findSet()); }
	 * 
	 * public static List<BusinessListing>
	 * getApprovedBusinessListingBySearchWithLimitAndTime(String searchKey,Integer
	 * limit,AppUser appUser){ Calendar from = Calendar.getInstance();
	 * from.add(Calendar.DATE, -1); from.set(Calendar.HOUR_OF_DAY, 0);
	 * from.set(Calendar.MINUTE, 0); from.set(Calendar.SECOND, 0);
	 * from.set(Calendar.MILLISECOND, 0);
	 * 
	 * Calendar to = Calendar.getInstance(); to.set(Calendar.HOUR_OF_DAY, 23);
	 * to.set(Calendar.MINUTE, 59); to.set(Calendar.SECOND, 59);
	 * to.set(Calendar.MILLISECOND, 999);
	 * 
	 * ExpressionList<BusinessListing> listingQuery =
	 * BusinessListing.find.where().ilike("searchIndex", "%" + searchKey +
	 * "%").not(Expr.in("businessListingStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE); if(appUser != null && appUser.getUserCountry() != null) {
	 * listingQuery.isNotNull("businessAddressCountry").eq("businessAddressCountry",
	 * appUser.getUserCountry()); }
	 * listingQuery.isNotNull("adminApprovedOn").ge("adminApprovedOn",
	 * from.getTime()).le("adminApprovedOn", to.getTime()); return new
	 * ArrayList<BusinessListing>(listingQuery.setMaxRows(limit).findSet()); }
	 * 
	 * public static List<BusinessListing>
	 * getApprovedBusinessListingWithLimit(AppUser appUser,City city,Integer limit){
	 * ExpressionList<BusinessListing> listingQuery =
	 * BusinessListing.find.where().not(Expr.in("businessListingStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE); if(city!= null) {
	 * listingQuery.isNotNull("businessListingOutLet").isNotNull(
	 * "businessListingOutLet.businessAddress").eq(
	 * "businessListingOutLet.businessAddress.city", city); }
	 * listingQuery.eq("isDistressSale", Boolean.FALSE); return new
	 * ArrayList<BusinessListing>(listingQuery.setMaxRows(limit).orderBy().desc(
	 * "adminApprovedOn").findSet()); }
	 * 
	 * public static List<BusinessListing>
	 * getApprovedAndDistressedBusinessListingWithLimit(AppUser appUser,City
	 * city,Integer limit){ ExpressionList<BusinessListing> listingQuery =
	 * BusinessListing.find.where().not(Expr.in("businessListingStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE); if(city != null) {
	 * listingQuery.isNotNull("businessListingOutLet").isNotNull(
	 * "businessListingOutLet.businessAddress").eq(
	 * "businessListingOutLet.businessAddress.city", city); }
	 * listingQuery.eq("isDistressSale", Boolean.TRUE); return new
	 * ArrayList<BusinessListing>(listingQuery.setMaxRows(limit).orderBy().desc(
	 * "adminApprovedOn").findSet()); }
	 * 
	 * public String generateListingFullViewURL() { String url =
	 * PaymentConstant.HOST;
	 * if(StringUtils.isNotEmpty(controllers.HomeController.getCachedIsoCodeValue())
	 * ) { url += "/"+controllers.HomeController.getCachedIsoCodeValue(); }else
	 * if(this.getBusinessAddressCountry() != null){ url +=
	 * "/"+this.getBusinessAddressCountry().getIsoCode(); }else { url += "/ww"; }
	 * 
	 * if(this.getListingType() != null) { url +=
	 * "/"+this.getListingType().toString().toLowerCase(); }else { url +=
	 * "/business"; }
	 * 
	 * if(this.getCategory() != null) { url +=
	 * "/"+StringUtils.updateTextToSEO(this.getCategory().getName()); }else { url +=
	 * "/category"; }
	 * 
	 * if(StringUtils.isNotEmpty(this.getTitle())) { url +=
	 * "/"+StringUtils.updateTextToSEO(this.getTitle())+"/l"; }else { url +=
	 * "/"+StringUtils.updateTextToSEO(this.getSuggestedtitle())+"/l"; } url
	 * +="/"+this.getListingId(); // Logger.debug(url); return url; }
	 * 
	 * public String getFormattedDateForSEO(Date date){ return
	 * DateUtil.getFormattedDateForSEO(date);
	 * 
	 * } public String generateListingFullViewURLWothoutHost() { String url = "";
	 * if(StringUtils.isNotEmpty(controllers.HomeController.getCachedIsoCodeValue())
	 * ) { url += "/"+controllers.HomeController.getCachedIsoCodeValue(); }else {
	 * url += "/"+controllers.HomeController.getCachedIsoCodeValue(); }
	 * 
	 * if(this.getListingType() != null) { url +=
	 * "/"+this.getListingType().toString().toLowerCase(); }else { url +=
	 * "/business"; }
	 * 
	 * if(this.getCategory() != null) { url +=
	 * "/"+StringUtils.updateTextToSEO(this.getCategory().getName()); }else { url +=
	 * "/category"; }
	 * 
	 * if(StringUtils.isNotEmpty(this.getTitle())) { url +=
	 * "/"+StringUtils.updateTextToSEO(this.getTitle())+"/l"; }else { url +=
	 * "/"+StringUtils.updateTextToSEO(this.getSuggestedtitle())+"/l"; } url
	 * +="/"+this.getListingId(); // Logger.debug(url); return url; }
	 * 
	 * public String getFullMobileNuber() { String mobileNum =
	 * this.getCountryCode(); if(this.getContactNumber() != null) { mobileNum +=
	 * this.getContactNumber() ; } return mobileNum; }
	 * 
	 * public static Map<String, Integer>
	 * getListingPendingApprovalCountByCountry(Role role, AppUser appUser) { int
	 * allCountryPR = 0; Map<String, Integer> map = new HashMap<>(); if (role !=
	 * null && role.getUserRole().equals(UserRole.TOBUZ_ADMIN)) { List<Country>
	 * countries = Country.getOperatedCountryList(); for (Country country :
	 * countries) { map.put(country.getIsoCode(),
	 * BusinessListing.find.where().eq("isActive",
	 * Boolean.TRUE).eq("businessListingStatus",
	 * BusinessListingStatus.UNDER_REVIEW).in("businessAddressCountry.isoCode",
	 * country.getIsoCode()).orderBy("sequence asc").findList().size());
	 * //allCountryPR = allCountryPR+BusinessListing.find.where().eq("isActive",
	 * Boolean.TRUE).eq("businessListingStatus",
	 * BusinessListingStatus.UNDER_REVIEW).in("businessAddressCountry.isoCode",
	 * country.getIsoCode()).orderBy("sequence asc").findList().size(); } }else if
	 * (role.getUserRole().equals(UserRole.TOBUZ_SUB_ADMIN) ||
	 * role.getUserRole().equals(UserRole.TOBUZ_ACCOUNTANT) ) {
	 * List<SubAdminAccessCountry> subAdminAccessCountryList =
	 * SubAdminAccessCountry.find.where().eq("appUser.id",appUser.getId()).eq(
	 * "isActive",true).findList(); List<Country> countries =
	 * subAdminAccessCountryList.stream().map(subAdminAccessCountry ->
	 * subAdminAccessCountry.getCountry()).collect(Collectors.toList()); for
	 * (Country country : countries) { map.put(country.getIsoCode(),
	 * BusinessListing.find.where().eq("isActive",
	 * Boolean.TRUE).eq("businessListingStatus",
	 * BusinessListingStatus.UNDER_REVIEW).in("businessAddressCountry.isoCode",
	 * country.getIsoCode()).orderBy("sequence asc").findList().size());
	 * //allCountryPR = allCountryPR+BusinessListing.find.where().eq("isActive",
	 * Boolean.TRUE).eq("businessListingStatus",
	 * BusinessListingStatus.UNDER_REVIEW).in("businessAddressCountry.isoCode",
	 * country.getIsoCode()).orderBy("sequence asc").findList().size(); } }
	 * //appResponse.setPayLoad(map); return map; }
	 * 
	 * public Boolean isPaidListing(Long listingId){ Boolean isPaidListing =
	 * Boolean.FALSE; if(listingId!=null){ BusinessListing businessListing =
	 * BusinessListing.find.byId(listingId); if(businessListing!=null &&
	 * businessListing.getUserTobuzServicePackageInfo()!=null &&
	 * businessListing.getUserTobuzServicePackageInfo().getTobuzPackage()!=null){
	 * if(!businessListing.getUserTobuzServicePackageInfo().getTobuzPackage().
	 * getTobuzPackageType().equals(TobuzPackageType.FREE)){ isPaidListing =
	 * Boolean.TRUE; } } } return isPaidListing ; }
	 * 
	 * public static List<BusinessListing> getWorldWideListings(){ return
	 * BusinessListing.find.where().not(Expr.in("businessListingStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE).eq("isListingMarkedWorldWide", Boolean.TRUE).findList(); }
	 * 
	 * 
	 * public Boolean isListingDoneByUser(AppUser appUser,String listingId){ Boolean
	 * flag = Boolean.FALSE; if(!StringUtils.isEmpty(listingId)){ BusinessListing
	 * businessListing = BusinessListing.find.where().eq("listedByUser",
	 * appUser).eq("listingId", listingId).findUnique(); if(businessListing!=null){
	 * flag = Boolean.TRUE; } } return flag; }
	 * 
	 * public String generateListingFullViewURL(String isoCode) { String url =
	 * PaymentConstant.HOST; if(StringUtils.isNotEmpty(isoCode)) { url +=
	 * "/"+isoCode; }else { url += "/ww"; }
	 * 
	 * if(this.getListingType() != null) { url +=
	 * "/"+this.getListingType().toString().toLowerCase(); }else { url +=
	 * "/business"; }
	 * 
	 * if(this.getCategory() != null) { url +=
	 * "/"+StringUtils.updateTextToSEO(this.getCategory().getName()); }else { url +=
	 * "/category"; }
	 * 
	 * if(StringUtils.isNotEmpty(this.getTitle())) { url +=
	 * "/"+StringUtils.updateTextToSEO(this.getTitle())+"/l"; }else { url +=
	 * "/"+StringUtils.updateTextToSEO(this.getSuggestedtitle())+"/l"; } url
	 * +="/"+this.getListingId(); // Logger.debug(url); return url; }
	 * 
	 * public String generateListingFullViewURLWothoutHost(String isoCode) { String
	 * url = ""; if(StringUtils.isNotEmpty(isoCode)) { url += "/"+isoCode; }else {
	 * url += "/ww"; }
	 * 
	 * if(this.getListingType() != null) { url +=
	 * "/"+this.getListingType().toString().toLowerCase(); }else { url +=
	 * "/business"; }
	 * 
	 * if(this.getCategory() != null) { url +=
	 * "/"+StringUtils.updateTextToSEO(this.getCategory().getName()); }else { url +=
	 * "/category"; }
	 * 
	 * if(StringUtils.isNotEmpty(this.getTitle())) { url +=
	 * "/"+StringUtils.updateTextToSEO(this.getTitle())+"/l"; }else { url +=
	 * "/"+StringUtils.updateTextToSEO(this.getSuggestedtitle())+"/l"; } url
	 * +="/"+this.getListingId(); // Logger.debug(url); return url; }
	 * 
	 * public static List<BusinessListing> getSEOListings(UserRole userRole){
	 * if(userRole.equals(UserRole.BROKER)) { List<BusinessListing> list =
	 * BusinessListing.find.where().not(Expr.in("businessListingStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE) .isNotNull("role").eq("role.userRole",
	 * userRole).isNotNull("adminApprovedOn").orderBy().desc("adminApprovedOn").
	 * setMaxRows(15000).findList();
	 * Logger.debug(list.size()+"  Size of Sitemap xml  "+userRole); return list;
	 * }else { List<BusinessListing> list =
	 * BusinessListing.find.where().not(Expr.in("businessListingStatus",
	 * BusinessListingStatus.getListingNotAvailableStatusList())).eq("isActive",
	 * Boolean.TRUE) .isNotNull("role").eq("role.userRole",
	 * userRole).isNotNull("adminApprovedOn").orderBy().desc("adminApprovedOn").
	 * setMaxRows(5000).findList();
	 * Logger.debug(list.size()+" Size of Sitemap xml "+userRole); return list; } }
	 * public static List<BusinessListingBean>
	 * getSEOListingsPaginationFirst(UserRole userRole){ String rawSql
	 * ="select listing_id,listing_type,category_id,title,suggested_title,business_listing.created_on as createdDate,business_address_country_id from business_listing "
	 * + "inner join role on role.id = business_listing.role_id " +
	 * "where business_listing.is_active ='true'" +
	 * " and role.user_role='"+userRole+"'" +
	 * "and business_listing_status in ('APPROVED','SOLD')" +
	 * "order by admin_approved_on desc; ";
	 * 
	 * List<BusinessListingBean> beanList = getListingBean(rawSql); return beanList;
	 * }
	 * 
	 * public static List<BusinessListingBean>
	 * getSEOListingsPaginationSecond(UserRole userRole){ String rawSql
	 * ="select listing_id,listing_type,category_id,title,suggested_title,business_listing.created_on as createdDate,business_address_country_id from business_listing "
	 * + "inner join role on role.id = business_listing.role_id " +
	 * "where business_listing.is_active ='true'" +
	 * " and role.user_role='"+userRole+"'" +
	 * "and business_listing_status in ('APPROVED','SOLD') " +
	 * "order by admin_approved_on desc  OFFSET 2500 ROWS FETCH NEXT 2500 ROWS ONLY"
	 * ;
	 * 
	 * List<BusinessListingBean> beanList = getListingBean(rawSql); return beanList;
	 * } public static List<BusinessListingBean>
	 * getSEOListingsPaginationThird(UserRole userRole){ String rawSql
	 * ="select listing_id,listing_type,category_id,title,suggested_title,business_listing.created_on as createdDate,business_address_country_id from business_listing "
	 * + "inner join role on role.id = business_listing.role_id " +
	 * "where business_listing.is_active ='true'" +
	 * " and role.user_role='"+userRole+"'" +
	 * "and business_listing_status in ('APPROVED','SOLD') " +
	 * "order by admin_approved_on desc  OFFSET 5000 ROWS FETCH NEXT 5000 ROWS ONLY"
	 * ;
	 * 
	 * List<BusinessListingBean> beanList = getListingBean(rawSql); return beanList;
	 * } public static List<BusinessListingBean>
	 * getSEOListingsPaginationFourth(UserRole userRole){ String rawSql
	 * ="select listing_id,listing_type,category_id,title,suggested_title,business_listing.created_on as createdDate,business_address_country_id from business_listing "
	 * + "inner join role on role.id = business_listing.role_id " +
	 * "where business_listing.is_active ='true'" +
	 * " and role.user_role='"+userRole+"'" +
	 * "and business_listing_status in ('APPROVED','SOLD') " +
	 * "order by admin_approved_on desc  OFFSET 10000 ROWS FETCH NEXT 5000 ROWS ONLY"
	 * ;
	 * 
	 * List<BusinessListingBean> beanList = getListingBean(rawSql); return beanList;
	 * } private static List<BusinessListingBean> getListingBean(String rawSql) {
	 * SqlQuery sqlQuery = Ebean.createSqlQuery(rawSql); List<BusinessListingBean>
	 * beanList = sqlQuery.findList().stream().map(sqlRow -> { BusinessListingBean
	 * bean = new BusinessListingBean();
	 * bean.setListingId(sqlRow.getString("listing_id"));
	 * bean.setListingType(sqlRow.getString("listing_type"));
	 * bean.setBusinessListingId(sqlRow.getLong("category_id"));
	 * bean.setTitle(sqlRow.getString("title"));
	 * bean.setSuggestedTitle(sqlRow.getString("suggested_title"));
	 * bean.setBusinessAddressCountryId(sqlRow.getLong("business_address_country_id"
	 * )); bean.setCreatedOn(sqlRow.getDate("createdDate")); return bean;
	 * }).collect(Collectors.toList()); return beanList; }
	 * 
	 * public String getListingImageUrl() { BusinessListing businessListing = this;
	 * if(businessListing.getBusinessListingOutLet() != null &&
	 * businessListing.getBusinessListingOutLet().getLogoId() != null &&
	 * businessListing.getBusinessListingOutLet().getLogoId() != 0) { FileEntity
	 * fileEntity =
	 * FileEntity.find.byId(businessListing.getBusinessListingOutLet().getLogoId());
	 * if(fileEntity != null) { return fileEntity.getFilePath(); } }else
	 * if(businessListing.getCategory() != null &&
	 * businessListing.getCategory().getImageId() != null &&
	 * businessListing.getCategory().getImageId() != 0){ FileEntity fileEntity =
	 * FileEntity.find.byId(businessListing.getCategory().getImageId());
	 * if(fileEntity != null) { return fileEntity.getFilePath(); } }else {
	 * if(businessListing.getListingType().equals(ListingType.COMMERCIAL)) { return
	 * "https://d17k0mmjntslb9.cloudfront.net/commercial-property.png"; }else
	 * if(businessListing.getListingType().equals(ListingType.BUSINESS)) { return
	 * "https://tobuz.com:8443/DisplayImage/showImage?image=Business-Supports-Business.jpg";
	 * } } return "https://d17k0mmjntslb9.cloudfront.net/logot.ico"; }
	 * 
	 * public static List<BusinessListing> getAdminListingSearch(String searchKey) {
	 * return BusinessListing.find.where().eq("isActive",
	 * Boolean.TRUE).or(Expr.ilike("listingId", searchKey+"%"), Expr.ilike("title",
	 * searchKey+"%")).findPagedList(0, 100).getList(); }
	 */
}
