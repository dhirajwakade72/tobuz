package com.tobuz.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class AppUser extends BaseEntity {

	@Column(nullable = false)
	private String name;

	
	@Column(unique = true, nullable = false)
	private String email;

	private String mobileNumber;

	private String countryCode;

	@ManyToOne
	private Country mobileDerivedCountry;

	private String password;

	private String salt;
	
	/*
	 * @OneToMany(cascade=CascadeType.ALL) private List<Role> roleList = new
	 * ArrayList<Role>();
	 * 
	 * @OneToMany(cascade=CascadeType.ALL) private List<AppUserLog> appUserLogList =
	 * new ArrayList<AppUserLog>();
	 * 
	 * @OneToMany(cascade=CascadeType.ALL) private List<UserTobuzServicePackageInfo>
	 * userPackages = new ArrayList<UserTobuzServicePackageInfo>();
	 */

	

	private String userDefaultRole;


	/* private Address address; */

	

	private String loginType;

	@OneToOne
	private FileEntity profileImage;

	private Boolean isFreePackageListingApplicable = Boolean.TRUE;

	
	private String  gender;

	private Date dateOfBirth;

	private Date joiningDate;

	private Boolean isEmailVerified = Boolean.FALSE;

	private String emailVerificationCode;

	private Double latitude;

	private Double longitude;

	private Boolean isOldUser = Boolean.FALSE;

	private Boolean isPasswordUpdated = Boolean.TRUE;

	private Boolean isSendWelcomeMail = Boolean.FALSE;

	private String randomTokenForLinkExpiry;

	/*
	 * @OneToMany(cascade=CascadeType.ALL,mappedBy="appUser") private
	 * List<SubAdminAccessCountry> subAdminAccessCountryList = new
	 * ArrayList<SubAdminAccessCountry>();
	 */

	public Boolean getIsSendWelcomeMail() {
		return isSendWelcomeMail;
	}

	public void setIsSendWelcomeMail(Boolean isSendWelcomeMail) {
		this.isSendWelcomeMail = isSendWelcomeMail;
	}

	public Boolean getIsOldUser() {
		return isOldUser;
	}

	public void setIsOldUser(Boolean isOldUser) {
		this.isOldUser = isOldUser;
	}

	public Boolean getIsPasswordUpdated() {
		return isPasswordUpdated;
	}

	public void setIsPasswordUpdated(Boolean isPasswordUpdated) {
		this.isPasswordUpdated = isPasswordUpdated;
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

	/*
	 * public List<SubAdminAccessCountry> getSubAdminAccessCountryList() { return
	 * subAdminAccessCountryList; }
	 * 
	 * public void setSubAdminAccessCountryList(List<SubAdminAccessCountry>
	 * subAdminAccessCountryList) { this.subAdminAccessCountryList =
	 * subAdminAccessCountryList; }
	 */
	/*
	 * public static Model.Finder<Long, AppUser> find = new Model.Finder<Long,
	 * AppUser>(AppUser.class);
	 */

	public Boolean getIsFreePackageListingApplicable() {
		return isFreePackageListingApplicable;
	}

	public void setIsFreePackageListingApplicable(Boolean isFreePackageApplicable) {
		this.isFreePackageListingApplicable = isFreePackageApplicable;
	}

	public String getName() {
		return name;
	}

	public Boolean getIsEmailVerified() {
		return isEmailVerified;
	}

	public String getEmailVerificationCode() {
		return emailVerificationCode;
	}

	public void setEmailVerificationCode(String emailVerificationCode) {
		this.emailVerificationCode = emailVerificationCode;
	}

	public void setIsEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Country getMobileDerivedCountry() {
		return mobileDerivedCountry;
	}

	public void setMobileDerivedCountry(Country mobileDerivedCountry) {
		this.mobileDerivedCountry = mobileDerivedCountry;
	}

	public String getPassword() {
		return password;
	}

	public String getRandomTokenForLinkExpiry() {
		return randomTokenForLinkExpiry;
	}

	public void setRandomTokenForLinkExpiry(String randomTokenForLinkExpiry) {
		this.randomTokenForLinkExpiry = randomTokenForLinkExpiry;
	}
	/*
	 * public void setPassword(String password){ final Random random = new
	 * SecureRandom(); final byte[] saltArray = new byte[32];
	 * random.nextBytes(saltArray); final String randomSalt =
	 * Base64.encodeBase64String(saltArray);
	 * 
	 * final String passwordWithSalt = password + randomSalt; MessageDigest sha256 =
	 * null; try { sha256 = MessageDigest.getInstance("SHA-256"); } catch
	 * (NoSuchAlgorithmException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } final byte[] passBytes = passwordWithSalt.getBytes();
	 * final String hashedPasswordWithSalt = Base64.encodeBase64String(sha256
	 * .digest(passBytes));
	 * 
	 * this.salt = randomSalt; this.password = hashedPasswordWithSalt; }
	 * 
	 * public Boolean isPasswordMatched(String password) { // TODO Auto-generated
	 * method stub try { final String passwordWithSalt = password + this.getSalt();
	 * final MessageDigest sha256 = MessageDigest.getInstance("SHA-256"); final
	 * byte[] passBytes = passwordWithSalt.getBytes(); final String hashedPassword =
	 * Base64.encodeBase64String(sha256.digest(passBytes)); if
	 * (hashedPassword.compareTo(this.getPassword()) == 0) { return true; } else {
	 * return false; } } catch (final Exception e) { e.printStackTrace(); return
	 * false; } }
	 * 
	 * 
	 * public String getSalt() { return salt; }
	 * 
	 * public void setSalt(String salt) { this.salt = salt; }
	 * 
	 * public List<Role> getRoleList() { return roleList; }
	 * 
	 * public void setRoleList(List<Role> roleList) { this.roleList = roleList; }
	 * 
	 * public List<AppUserLog> getAppUserLogList() { return appUserLogList; }
	 * 
	 * public void setAppUserLogList(List<AppUserLog> appUserLogList) {
	 * this.appUserLogList = appUserLogList; }
	 * 
	 * public List<UserTobuzServicePackageInfo> getUserPackages() { return
	 * UserTobuzServicePackageInfo.find.where().eq("user", this).eq("isActive",
	 * Boolean.TRUE).findList(); }
	 */

	/*
	 * public void setUserPackages(List<UserTobuzServicePackageInfo> userPackages) {
	 * this.userPackages = userPackages; }
	 */

	/*
	 * public Address getAddress() { return address; }
	 * 
	 * public void setAddress(Address address) { this.address = address; }
	 */

	


	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUserDefaultRole() {
		return userDefaultRole;
	}

	public void setUserDefaultRole(String userDefaultRole) {
		this.userDefaultRole = userDefaultRole;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public FileEntity getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(FileEntity profileImage) {
		this.profileImage = profileImage;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	/*
	 * public String generateAuthToken() { String randomKey =
	 * StringUtils.getRandomAlphaNumericId(15); String key = randomKey +
	 * this.getEmail().concat(String.valueOf(System.currentTimeMillis())); byte[]
	 * authTokenBytes = java.util.Base64.getEncoder().encode(key.getBytes()); String
	 * authToken = new String(authTokenBytes); return authToken; }
	 */

	/*
	 * public AppUserPayLoadBean toAppUserPayLoadBean() { AppUserPayLoadBean bean =
	 * new AppUserPayLoadBean(); Role role = Role.find.where().eq("appUser",
	 * this).setMaxRows(1).findUnique(); List<SubAdminAccessCountry>
	 * subAdminAccessCountries = SubAdminAccessCountry.find.where().eq("appUser",
	 * this).eq("isActive",Boolean.TRUE).findList(); //
	 * Logger.info("Size of subAdminAccessCountries==>"+subAdminAccessCountries.size
	 * ()+"==="+this); List<String> accessCountryList = new ArrayList<String>();
	 * if(subAdminAccessCountries!=null){ for (SubAdminAccessCountry
	 * subAdminAccessCountry : subAdminAccessCountries) {
	 * accessCountryList.add(subAdminAccessCountry.getCountry().getIsoCode()); } }
	 * bean.setAccessCountryList(accessCountryList); bean.setName(this.getName());
	 * if(this.getCountryCode()!=null){ bean.setCountryCode(this.getCountryCode());
	 * }
	 * 
	 * bean.setMobileNumber(this.getMobileNumber());
	 * bean.setAuthKey(SessionProperty.getUserSessionAuthKey());
	 * bean.setRole(role.getUserRole().toString()); bean.setRoleId(role.getId());
	 * bean.setUserId(this.getId()); bean.setEmail(this.getEmail()); if
	 * (this.dateOfBirth != null) {
	 * bean.setDateOfBirth(DateUtil.getFormattedDate(this.dateOfBirth)); }
	 * if(this.getCountryCode()==null){ bean.setCountryCode(" "); }
	 * 
	 * if (this.joiningDate != null) {
	 * bean.setJoiningDate(DateUtil.getFormattedDate(this.joiningDate)); }
	 * if(this.getGender()!=null && (this.getGender().equals(Gender.MALE)||
	 * this.getGender().equals(Gender.FEMALE))){
	 * bean.setGender(this.getGender().toString()); } if(this.getProfileImage() !=
	 * null) { bean.setImageId(this.getProfileImage().getId());
	 * bean.setImageUrl(this.getProfileImage().getFilePath()); }
	 * if(this.getAddress() != null && this.getAddress().getCountry() != null) {
	 * bean.setAddressId(this.getAddress().getId());
	 * if(this.getAddress().getCountry() != null) {
	 * bean.setCountryId(this.getAddress().getCountry().getId());
	 * //bean.setCountryCode(this.getAddress().getCountry().getDialingCode());
	 * bean.setCountryName(this.getAddress().getCountry().getName()); }
	 * if(this.getAddress().getCity() != null) {
	 * bean.setCity(CityBean.toCityBean(this.getAddress().getCity()));
	 * bean.setCityId(this.getAddress().getCity().getId()); }
	 * if(this.getAddress().getState() != null) {
	 * bean.setStateId(this.getAddress().getState().getId());
	 * bean.setStateName(this.getAddress().getState().getName()); }
	 * bean.setPincode(this.getAddress().getZipCode());
	 * bean.setAddressDescription(this.getAddress().getDetailedAddress());
	 * bean.isLocationUpdated = Boolean.TRUE; }
	 * if(this.getUserCurrentActivatedPackage() != null) { bean.isPackageUpdated =
	 * Boolean.TRUE; } bean.unreadMessageCount = Message.getUnreadMessages(this,
	 * role, null, null).size(); bean.unreadNotificationCount =
	 * Notification.getUserUnreadNotifications(this, role).size(); //
	 * System.out.println();http://javadevnotes.com/java-double-to-string-examples
	 * if(role!=null && role.getUserRole().equals(UserRole.BROKER)){
	 * if(this.getBroker() != null && this.getBroker().getFileEntity() != null) {
	 * FileEntity fileEntity = this.getBroker().getFileEntity();
	 * if(fileEntity!=null) { bean.setBrokerCompanyLogo(fileEntity.getFilePath()); }
	 * bean.brokerCompanyLogoId = this.getBroker().getFileEntity().getId(); }
	 * 
	 * bean.companyDescription = this.getBroker() != null ?
	 * this.getBroker().getCompanyDescription() : null; }else {
	 * bean.brokerCompanyLogoId = 0l; }
	 * 
	 * 
	 * if(this.getRole() != null &&
	 * this.getRole().getUserRole().equals(UserRole.BROKER)) { if(this.getBroker()
	 * != null && this.getBroker().getFileEntity() != null) { FileEntity fileEntity
	 * = this.getBroker().getFileEntity(); if(fileEntity!=null) {
	 * bean.setBrokerCompanyLogo(fileEntity.getFilePath()); }
	 * bean.brokerCompanyLogoId = this.getBroker().getFileEntity().getId(); }
	 * bean.companyDescription = this.getBroker() != null ?
	 * this.getBroker().getCompanyDescription() : null; }else {
	 * bean.brokerCompanyLogoId = 0l; } return bean; }
	 */

	/*
	 * public Role getRole() { String roleId = SessionProperty.getUserRoleId(); try
	 * { if (StringUtils.isNotEmpty(roleId)) { return
	 * Role.find.byId(Long.parseLong(roleId)); } } catch (Exception e) { // TODO:
	 * handle exception e.printStackTrace(); } return
	 * Role.find.where().eq("appUser", this).eq("isActive",
	 * Boolean.TRUE).setMaxRows(1).findUnique(); }
	 * 
	 * public List<TobuzPackage> getUserActivatedPackages(ListingType listingType) {
	 * List<TobuzPackage> packageList = new LinkedList<TobuzPackage>();
	 * ExpressionList<UserTobuzServicePackageInfo> packageMappingList =
	 * UserTobuzServicePackageInfo.find.where() .eq("user", this).eq("role",
	 * this.getRole()).eq("isActive", Boolean.TRUE); if (listingType != null) {
	 * packageMappingList.eq("packageActiveFor", listingType); } for
	 * (UserTobuzServicePackageInfo serviceInfo : packageMappingList.findList()) {
	 * packageList.add(serviceInfo.getTobuzPackage()); } return packageList; }
	 * 
	 * public List<UserTobuzServicePackageInfo> getUserPreviousPackages() { return
	 * UserTobuzServicePackageInfo.find.where().eq("user", this).eq("role",
	 * this.getRole()) .eq("isActive", Boolean.TRUE).findList(); }
	 * 
	 * public Role getAppUserDefaultRole() { return Role.find.where().eq("appUser",
	 * this).eq("userRole", this.getUserDefaultRole()) .eq("isActive",
	 * Boolean.TRUE).setMaxRows(1).findUnique(); }
	 * 
	 * public Buyer getBuyer() { return Buyer.find.where().eq("user",
	 * this).eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique(); }
	 * 
	 * public Seller getSeller() { return Seller.find.where().eq("user",
	 * this).eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique(); }
	 * 
	 * public Franchiser getFranchisor() { return Franchiser.find.where().eq("user",
	 * this).eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique(); }
	 * 
	 * public Broker getBroker() { return Broker.find.where().eq("user",
	 * this).eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique(); }
	 * 
	 * public BusinessAdvisor getBusinessAdvisor() { return
	 * BusinessAdvisor.find.where().eq("adviosrByUser", this).eq("isActive",
	 * Boolean.TRUE).setMaxRows(1) .findUnique(); }
	 * 
	 * public Country getUserCountry() { if (this.getAddress() != null) { return
	 * this.getAddress().getCountry(); } return null; }
	 * 
	 * public TobuzPackage getUserCurrentActivatedPackage() {
	 * UserTobuzServicePackageInfo packageMapping =
	 * UserTobuzServicePackageInfo.find.where().eq("user", this) .eq("role",
	 * this.getRole()).eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique(); if
	 * (packageMapping != null) { return packageMapping.getTobuzPackage(); } return
	 * null; }
	 * 
	 * public List<FavouriteBusinessListing>
	 * getLoggedInUserFavouriteListingsAndAdverts() { return
	 * FavouriteBusinessListing.find.where().eq("isActive", Boolean.TRUE).eq("user",
	 * this) .eq("role", this.getRole()).findList(); }
	 * 
	 * public List<BusinessAdvert> getLoggedInUserBusinessAdverts() { return
	 * BusinessAdvert.find.where().eq("isActive", Boolean.TRUE).eq("advertedByUser",
	 * this) .eq("role", this.getRole()).findList(); }
	 * 
	 * public List<UserSearch> getLoggedInUserSearchList() { return
	 * UserSearch.getUserSearch(this, this.getRole()); }
	 * 
	 * public Role createNewUserRole(UserRole selectedRole) { Role updatedRole =
	 * null; AppUser appUser = this; if (selectedRole.equals(UserRole.BUYER)) {
	 * Buyer buyer = Buyer.find.where().eq("user", appUser).eq("isActive",
	 * Boolean.TRUE).setMaxRows(1) .findUnique(); Role userole =
	 * Role.find.where().eq("appUser", appUser).eq("userRole", UserRole.BUYER)
	 * .eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique(); if (buyer == null)
	 * { buyer = new Buyer(); buyer.setUser(appUser); if (userole == null) { userole
	 * = new Role(); userole.setAppUser(appUser);
	 * userole.setUserRole(UserRole.BUYER); userole.setIsActive(Boolean.TRUE);
	 * userole.save(); } else { userole.setIsActive(Boolean.TRUE); userole.update();
	 * } buyer.setRole(userole); buyer.setIsActive(Boolean.TRUE); buyer.save(); }
	 * else { buyer.setIsActive(Boolean.TRUE); userole = buyer.getRole();
	 * userole.setIsActive(Boolean.TRUE); userole.update(); buyer.update(); }
	 * updatedRole = userole; } else if (selectedRole.equals(UserRole.SELLER)) {
	 * Seller seller = Seller.find.where().eq("user", appUser).eq("isActive",
	 * Boolean.TRUE).setMaxRows(1) .findUnique(); Role userole =
	 * Role.find.where().eq("appUser", appUser).eq("userRole", UserRole.SELLER)
	 * .eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique(); if (seller == null)
	 * { seller = new Seller(); seller.setUser(appUser); if (userole == null) {
	 * userole = new Role(); userole.setAppUser(appUser);
	 * userole.setUserRole(UserRole.SELLER); userole.setIsActive(Boolean.TRUE);
	 * userole.save(); } else { userole.setIsActive(Boolean.TRUE); userole.update();
	 * } seller.setRole(userole); seller.setIsActive(Boolean.TRUE); seller.save(); }
	 * else { seller.setIsActive(Boolean.TRUE); userole = seller.getRole();
	 * userole.setIsActive(Boolean.TRUE); userole.update(); seller.update(); }
	 * updatedRole = userole; } else if (selectedRole.equals(UserRole.FRANCHISOR)) {
	 * Franchiser franchiser = Franchiser.find.where().eq("user",
	 * appUser).eq("isActive", Boolean.TRUE) .setMaxRows(1).findUnique(); Role
	 * userole = Role.find.where().eq("appUser", appUser).eq("userRole",
	 * UserRole.FRANCHISOR) .eq("isActive",
	 * Boolean.TRUE).setMaxRows(1).findUnique(); if (franchiser == null) {
	 * franchiser = new Franchiser(); franchiser.setUser(appUser); if (userole ==
	 * null) { userole = new Role(); userole.setAppUser(appUser);
	 * userole.setUserRole(UserRole.FRANCHISOR); userole.setIsActive(Boolean.TRUE);
	 * userole.save(); } else { userole.setIsActive(Boolean.TRUE); userole.update();
	 * } franchiser.setRole(userole); franchiser.setIsActive(Boolean.TRUE);
	 * franchiser.save(); } else { franchiser.setIsActive(Boolean.TRUE); userole =
	 * franchiser.getRole(); userole.setIsActive(Boolean.TRUE); userole.update();
	 * franchiser.update(); } updatedRole = userole; } return updatedRole; }
	 * 
	 * public Country getAppUserAddressCountry() { if (this.getAddress() != null &&
	 * this.getAddress().getCountry() != null) { return
	 * this.getAddress().getCountry(); } return null; }
	 * 
	 * public String getAppUserAddressCountryCurrency() { if (this.getAddress() !=
	 * null && this.getAddress().getCountry() != null &&
	 * this.getAddress().getCountry().getIsTobuzOperatedCountry()) { return
	 * this.getAddress().getCountry().getCurrencyCode(); } return
	 * Country.getMasterPackageCountry().getCurrencyCode(); }
	 * 
	 * public List<Message> getUnreadMessages() { return
	 * Message.getUnreadMessages(this, this.getRole(), null, null); }
	 * 
	 * public static List<AppUser> getAllAppUsers() { return
	 * AppUser.find.where().eq("isActive", Boolean.TRUE).ne("userDefaultRole",
	 * UserRole.TOBUZ_SUB_ADMIN) .findList(); }
	 * 
	 * public static List<AppUser> getCountryAppUsers(List<Country> countryList) {
	 * return AppUser.find.where().eq("isActive",
	 * Boolean.TRUE).in("address.country.id", countryList).findList(); }
	 * 
	 * public TobuzPackage getUserPackageByListingType(ListingType listingType) {
	 * ExpressionList<UserTobuzServicePackageInfo> packageMappingQuery =
	 * UserTobuzServicePackageInfo.find.where() .eq("user", this).eq("role",
	 * this.getRole()); UserTobuzServicePackageInfo packageMapping = null; if
	 * (listingType != null) { packageMappingQuery.eq("packageActiveFor",
	 * listingType).eq("isActive", Boolean.TRUE); } packageMapping =
	 * packageMappingQuery.setMaxRows(1).findUnique(); if (packageMapping != null) {
	 * return packageMapping.getTobuzPackage(); } return null; }
	 * 
	 * public Integer advertsOrListingsEnabled(String listingType) { Integer
	 * availableAdvertsCount = 0; // Logger.debug(listingType+"  listingType"); //
	 * Integer availableAdvertsCount = 0; // ListingType lType = null; //
	 * if(StringUtils.isNotEmpty(listingType)) { // lType =
	 * ListingType.valueOf(listingType.trim()); // } //
	 * if(this.getUserPackageByListingType(lType) != null) { //
	 * if(this.getUserPackageByListingType(lType).getIsFreePackage()) { //
	 * if(this.isFreePackageListingApplicable) { // availableAdvertsCount = 1; // }
	 * // }else { // if(this.getRole().getUserRole().equals(UserRole.BUYER)) { //
	 * return this.getBuyer().getNoOfNewAdvertsAvailable(); // }else
	 * if(this.getRole().getUserRole().equals(UserRole.FRANCHISOR)) { // return
	 * this.getFranchisor().getNoOfNewListingsAvailable(); // }else
	 * if(this.getRole().getUserRole().equals(UserRole.SELLER)) { // if(lType !=
	 * null) { // if(lType.equals(ListingType.BUSINESS)) { // return
	 * this.getSeller().getNoOfNewBusinessListingsAvailable(); // }else
	 * if(lType.equals(ListingType.COMMERCIAL)) { // return
	 * this.getSeller().getNoOfNewCommercialListingsAvailable(); // }else
	 * if(lType.equals(ListingType.FRANCHISE)) { // return
	 * this.getSeller().getNoOfNewFranchiseListingsAvailable(); // } // } // }else
	 * if(this.getRole().getUserRole().equals(UserRole.BROKER)) { // if(lType !=
	 * null) { // if(lType.equals(ListingType.BUSINESS)) { // return
	 * this.getBroker().getNoOfNewBusinessListingsAvailable(); // }else
	 * if(lType.equals(ListingType.COMMERCIAL)) { // return
	 * this.getBroker().getNoOfNewCommercialListingsAvailable(); // }else
	 * if(lType.equals(ListingType.FRANCHISE)) { // return
	 * this.getBroker().getNoOfNewFranchiseListingsAvailable(); // } // } // } // }
	 * // } return availableAdvertsCount; }
	 * 
	 * // public void updateAdvertsOrListingsEnabled(UserTobuzServicePackageInfo
	 * tobuzPackageInfo) { // ListingType lType = null; //
	 * if(tobuzPackageInfo.getPackageActiveFor() != null) { // lType =
	 * tobuzPackageInfo.getPackageActiveFor(); // } // AppUser appUser =
	 * tobuzPackageInfo.getUser(); // Role role = tobuzPackageInfo.getRole(); //
	 * if(role.getUserRole().equals(UserRole.BUYER)) { // Buyer buyer =
	 * appUser.getBuyer(); //
	 * buyer.setNoOfNewAdvertsAvailable(tobuzPackageInfo.getTobuzPackage().
	 * getAdvertListCount()); // buyer.update(); // }else
	 * if(role.getUserRole().equals(UserRole.FRANCHISOR)) { // Franchiser franchiser
	 * = appUser.getFranchisor(); //
	 * franchiser.setNoOfNewListingsAvailable(tobuzPackageInfo.getTobuzPackage().
	 * getAdvertListCount()); // franchiser.update(); // }else
	 * if(role.getUserRole().equals(UserRole.SELLER)) { // Seller seller =
	 * appUser.getSeller(); // if(lType != null) { //
	 * if(lType.equals(ListingType.BUSINESS)) { //
	 * seller.setNoOfNewBusinessListingsAvailable(tobuzPackageInfo.getTobuzPackage()
	 * .getAdvertListCount()); // }else if(lType.equals(ListingType.COMMERCIAL)) {
	 * //
	 * seller.setNoOfNewCommercialListingsAvailable(tobuzPackageInfo.getTobuzPackage
	 * ().getAdvertListCount()); // }else if(lType.equals(ListingType.FRANCHISE)) {
	 * //
	 * seller.setNoOfNewFranchiseListingsAvailable(tobuzPackageInfo.getTobuzPackage(
	 * ).getAdvertListCount()); // } // seller.update(); // } // }else
	 * if(this.getRole().getUserRole().equals(UserRole.BROKER)) { // Broker broker =
	 * appUser.getBroker(); // if(lType != null) { //
	 * if(lType.equals(ListingType.BUSINESS)) { //
	 * broker.setNoOfNewBusinessListingsAvailable(tobuzPackageInfo.getTobuzPackage()
	 * .getAdvertListCount()); // }else if(lType.equals(ListingType.COMMERCIAL)) {
	 * //
	 * broker.setNoOfNewCommercialListingsAvailable(tobuzPackageInfo.getTobuzPackage
	 * ().getAdvertListCount()); // }else if(lType.equals(ListingType.FRANCHISE)) {
	 * //
	 * broker.setNoOfNewFranchiseListingsAvailable(tobuzPackageInfo.getTobuzPackage(
	 * ).getAdvertListCount()); // } // broker.update();} // } // }
	 * 
	 * public Role appUserRole(UserRole userRole) { return
	 * Role.find.where().eq("appUser", this).eq("userRole", userRole).eq("isActive",
	 * Boolean.TRUE).setMaxRows(1) .findUnique(); }
	 * 
	 * public List<Role> appUserAllRoles() { ExpressionList<Role> roleQuery =
	 * Role.find.where().eq("appUser", this).eq("isActive", Boolean.TRUE); if
	 * (this.getRole() != null) { roleQuery.ne("id", this.getRole().getId()); }
	 * return roleQuery.findList(); }
	 * 
	 * public static List<AppUser> getAppUserBySearchKey(SearchBean searchBean,
	 * Boolean flag) { AppUser appUser = LoginController.getLoggedInUser(); //
	 * adminBean.userRole= appUser.getRole().getUserRole(); List<String>
	 * countryIsoCodeList = searchBean.countryIsoCodeList; List<String> userRoleList
	 * = searchBean.userRoleList; List<String> allCountryList = new ArrayList<>();
	 * String fromDate = searchBean.fromDate; String toDate = searchBean.toDate;
	 * String searchKey = searchBean.searchKey;
	 * 
	 * ExpressionList<AppUser> appUserWhere = find.where().eq("isActive",
	 * Boolean.TRUE); countryIsoCodeList =
	 * countryIsoCodeList.stream().filter(Objects::nonNull).collect(Collectors.
	 * toList());
	 * 
	 * if (StringUtils.isNotEmpty(searchKey)) { appUserWhere.or(Expr.ilike("name",
	 * searchKey.trim() + "%"), Expr.ilike("email", searchKey.trim() + "%")); }
	 * 
	 * if (countryIsoCodeList != null && !countryIsoCodeList.isEmpty()) { if
	 * (countryIsoCodeList.contains("ALL")) { List<Country> countrysList =
	 * Country.countryList(); for (Country country : countrysList) {
	 * allCountryList.add(country.getIsoCode()); }
	 * appUserWhere.in("address.country", allCountryList.stream().map(countryIsoCode
	 * -> Country.getCountryByIsoCode(countryIsoCode))
	 * .collect(Collectors.toList())); } else { appUserWhere.in("address.country",
	 * countryIsoCodeList.stream().map(countryIsoCode ->
	 * Country.getCountryByIsoCode(countryIsoCode)) .collect(Collectors.toList()));
	 * } }
	 * 
	 * if (userRoleList != null && !userRoleList.isEmpty()) {
	 * appUserWhere.in("roleList.userRole", userRoleList.stream().map(strRole ->
	 * UserRole.valueOf(strRole)) .distinct().collect(Collectors.toList())); }
	 * 
	 * if (StringUtils.isNotEmpty(fromDate)) { appUserWhere.ge("createdOn",
	 * DateUtil.getFormattedDate(fromDate)); } if (StringUtils.isNotEmpty(toDate)) {
	 * appUserWhere.le("createdOn",
	 * DateUtil.getEndDate(DateUtil.getFormattedDate(toDate))); } //
	 * appUserWhere.notIn("roleList.userRole", //
	 * UserRole.TOBUZ_SUB_ADMIN,UserRole.TOBUZ_ACCOUNTANT,UserRole.TOBUZ_ADMIN); if
	 * (flag) { return appUserWhere.ne("userDefaultRole", UserRole.TOBUZ_ACCOUNTANT)
	 * .ne("userDefaultRole", UserRole.TOBUZ_SUB_ADMIN).ne("userDefaultRole",
	 * UserRole.TOBUZ_ADMIN) .orderBy().desc("createdOn").findList(); } else {
	 * return appUserWhere.ne("userDefaultRole", UserRole.TOBUZ_ACCOUNTANT)
	 * .ne("userDefaultRole", UserRole.TOBUZ_SUB_ADMIN).ne("userDefaultRole",
	 * UserRole.TOBUZ_ADMIN)
	 * .orderBy().desc("createdOn").findPagedList(searchBean.pageNum,
	 * 100).getList(); }
	 * 
	 * }
	 * 
	 * public Integer getAllPackageContactsAccessCount() { return
	 * this.getUserPackages().stream() .filter(userPackageInfo -> userPackageInfo !=
	 * null && userPackageInfo.getNoOfContacts() != null) .mapToInt(userPackageInfo
	 * -> userPackageInfo.getNoOfContacts()).sum(); }
	 * 
	 * public static List<AppUser> getAdminUsersBySearchKey(SearchBean searchBean) {
	 * List<String> countryIsoCodeList = searchBean.countryIsoCodeList;
	 * countryIsoCodeList =
	 * countryIsoCodeList.stream().filter(Objects::nonNull).collect(Collectors.
	 * toList()); List<String> allCountryList = new ArrayList<>();
	 * ExpressionList<AppUser> appUserWhere = find.where().eq("isActive",
	 * Boolean.TRUE).in("roleList.userRole", UserRole.TOBUZ_SUB_ADMIN,
	 * UserRole.TOBUZ_ACCOUNTANT); countryIsoCodeList =
	 * countryIsoCodeList.stream().filter(Objects::nonNull).collect(Collectors.
	 * toList()); if (countryIsoCodeList != null && !countryIsoCodeList.isEmpty()) {
	 * if (countryIsoCodeList.contains("ALL")) { List<Country> countrysList =
	 * Country.countryList(); for (Country country : countrysList) {
	 * allCountryList.add(country.getIsoCode()); }
	 * appUserWhere.in("address.country", allCountryList.stream().map(countryIsoCode
	 * -> Country.getCountryByIsoCode(countryIsoCode))
	 * .collect(Collectors.toList())); } else { appUserWhere.in("address.country",
	 * countryIsoCodeList.stream().map(countryIsoCode ->
	 * Country.getCountryByIsoCode(countryIsoCode)) .collect(Collectors.toList()));
	 * } } return appUserWhere.orderBy().desc("createdOn").findList(); }
	 * 
	 * public Boolean isPaidPackageActive() { Boolean isPaidPackageActive =
	 * Boolean.FALSE; List<UserTobuzServicePackageInfo> packageMapping =
	 * UserTobuzServicePackageInfo.find.where().eq("user", this) .eq("role",
	 * this.getRole()).eq("isActive", Boolean.TRUE).findList(); for
	 * (UserTobuzServicePackageInfo mappingInfo : packageMapping) { if
	 * (!isPaidPackageActive) { if
	 * (!mappingInfo.getTobuzPackage().getIsFreePackage() &&
	 * mappingInfo.getUserPackageStatus().equals(UserPackageStatus.ACTIVE)) {
	 * isPaidPackageActive = Boolean.TRUE; } } } return isPaidPackageActive; }
	 * 
	 * public List<UserTobuzServicePackageInfo> getUserActivatedPackages() {
	 * List<UserTobuzServicePackageInfo> packageMappingInfos =
	 * UserTobuzServicePackageInfo.find.where() .eq("user", this).eq("role",
	 * this.getRole()).eq("userPackageStatus", UserPackageStatus.ACTIVE)
	 * .eq("isActive", Boolean.TRUE).orderBy().asc("createdOn").findList(); return
	 * packageMappingInfos; }
	 * 
	 * public List<UserTobuzServicePackageInfo> getUserActivatedPackagesByRole(Role
	 * role) { List<UserTobuzServicePackageInfo> packageMappingInfos =
	 * UserTobuzServicePackageInfo.find.where() .eq("user", this).eq("role",
	 * role).eq("userPackageStatus", UserPackageStatus.ACTIVE) .eq("isActive",
	 * Boolean.TRUE).orderBy().asc("createdOn").findList(); return
	 * packageMappingInfos; }
	 * 
	 * public Boolean isPaidUser() { List<UserTobuzServicePackageInfo>
	 * packageMappingInfos = getUserActivatedPackages(); if
	 * (this.getRole().getUserRole().equals(UserRole.BUYER) ||
	 * this.getRole().getUserRole().equals(UserRole.BUSINESS_SERVICE)) { for
	 * (UserTobuzServicePackageInfo servicePackageInfo : packageMappingInfos) { if
	 * (!servicePackageInfo.getTobuzPackage().getIsFreePackage()) { return
	 * Boolean.TRUE; } } } else if
	 * (this.getRole().getUserRole().equals(UserRole.BROKER) ||
	 * this.getRole().getUserRole().equals(UserRole.SELLER) ||
	 * this.getRole().getUserRole().equals(UserRole.FRANCHISOR)) { for
	 * (UserTobuzServicePackageInfo servicePackageInfo : packageMappingInfos) { if
	 * (servicePackageInfo.getTobuzPackage().getTobuzPackageType().equals(
	 * TobuzPackageType.GOLD) ||
	 * servicePackageInfo.getTobuzPackage().getTobuzPackageType().equals(
	 * TobuzPackageType.BRONZE) ||
	 * servicePackageInfo.getTobuzPackage().getTobuzPackageType().equals(
	 * TobuzPackageType.SILVER)) { return Boolean.TRUE; } } } return Boolean.FALSE;
	 * }
	 * 
	 * public static Boolean isPaidUser(AppUser appUser, Role role) {
	 * List<UserTobuzServicePackageInfo> packageMappingInfos =
	 * UserTobuzServicePackageInfo.find.where() .eq("user", appUser).eq("role",
	 * role).eq("userPackageStatus", UserPackageStatus.ACTIVE) .eq("isActive",
	 * Boolean.TRUE).orderBy().asc("createdOn").findList(); if
	 * (role.getUserRole().equals(UserRole.BUYER) ||
	 * role.getUserRole().equals(UserRole.BUSINESS_SERVICE)) { for
	 * (UserTobuzServicePackageInfo servicePackageInfo : packageMappingInfos) { if
	 * (!servicePackageInfo.getTobuzPackage().getIsFreePackage()) { return
	 * Boolean.TRUE; } } } else if (role.getUserRole().equals(UserRole.BROKER) ||
	 * role.getUserRole().equals(UserRole.SELLER) ||
	 * role.getUserRole().equals(UserRole.FRANCHISOR)) { for
	 * (UserTobuzServicePackageInfo servicePackageInfo : packageMappingInfos) { if
	 * (servicePackageInfo.getTobuzPackage().getTobuzPackageType().equals(
	 * TobuzPackageType.GOLD)) { return Boolean.TRUE; } } } return Boolean.FALSE; }
	 * 
	 * public String getFullMobileNumber() { String mobileNum = ""; if
	 * (StringUtils.isNotEmpty(this.getCountryCode())) { mobileNum +=
	 * this.getCountryCode(); } if (StringUtils.isNotEmpty(this.getMobileNumber()))
	 * { mobileNum += " " + this.getMobileNumber(); } return mobileNum; }
	 * 
	 * public String getSMSFullMobileNumber() { String mobileNum =
	 * this.getCountryCode(); if (this.getMobileNumber() != null) { mobileNum += ""
	 * + this.getMobileNumber(); } return mobileNum; }
	 * 
	 * public List<UserRole> getUserAddProfileUserRoles() { List<UserRole>
	 * userRoleList = new LinkedList<UserRole>(); if
	 * (this.getRole().getUserRole().equals(UserRole.BUYER)) { if
	 * (this.appUserRole(UserRole.SELLER) == null) {
	 * userRoleList.add(UserRole.SELLER); } if
	 * (this.appUserRole(UserRole.FRANCHISOR) == null) {
	 * userRoleList.add(UserRole.FRANCHISOR); } } else if
	 * (this.getRole().getUserRole().equals(UserRole.SELLER)) { if
	 * (this.appUserRole(UserRole.BUYER) == null) {
	 * userRoleList.add(UserRole.BUYER); } if (this.appUserRole(UserRole.FRANCHISOR)
	 * == null) { userRoleList.add(UserRole.FRANCHISOR); } } else if
	 * (this.getRole().getUserRole().equals(UserRole.FRANCHISOR)) { if
	 * (this.appUserRole(UserRole.SELLER) == null) {
	 * userRoleList.add(UserRole.SELLER); } if (this.appUserRole(UserRole.BUYER) ==
	 * null) { userRoleList.add(UserRole.BUYER); } } return userRoleList; }
	 * 
	 *//**
		 * Action to find User is LoggedIn
		 *//*
			 * public static AppUser getLoggedInUser() { // TODO Auto-generated method stub
			 * AppUser appUser = SessionProperty.getAppUserWithSessionAuthKey(); if (appUser
			 * != null) { return appUser; } return null; }
			 * 
			 * public static List<AppUser> getAdminUserSearch(String searchKey) { return
			 * AppUser.find.where().eq("isActive", Boolean.TRUE).ilike("name", searchKey +
			 * "%") .ilike("email", searchKey + "%").findPagedList(0, 100).getList(); }
			 */

}