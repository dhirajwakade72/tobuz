package com.tobuz.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.tomcat.jni.Address;
import org.h2.expression.ExpressionList;
import org.springframework.ui.Model;

import javassist.compiler.ast.Expr;


@Entity
public class BusinessAdvisor extends BaseEntity{

	@OneToOne
	private AppUser adviosrByUser;

	private String companyName;

	private String firmName;

	/*
	 * @OneToOne private Address address;
	 */
	private String webSiteAddress;

	private Boolean isCompanyRegister = Boolean.FALSE;

	private String companyRegisterNumber;

	private String companyRegisterYear;

	private String countryOfRegistration;

	private String taxIdentificationNumber;

	private String passportDetails;

	private String nationality;

	//private Boolean isActive;

	@OneToOne
	private FileEntity licensedId;

	@OneToOne
	private FileEntity corporateProfileId;

	private String socialMedia;

	private String socialMediaProfileUrl;

	@Column(columnDefinition="TEXT")
	private String aboutCorporateProfile;

	/*
	 * @ManyToOne private BusinessServiceType businessServiceType;
	 */

	private Boolean isApprovedByAdmin = Boolean.FALSE;

	private Boolean isProfileVerified = Boolean.FALSE;

	/*
	 * public static Model.Finder<Long, BusinessAdvisor> find = new
	 * Model.Finder<Long, BusinessAdvisor>(BusinessAdvisor.class);
	 * 
	 */
	public Boolean getIsApprovedByAdmin() {
		return isApprovedByAdmin;
	}

	public void setIsApprovedByAdmin(Boolean isApprovedByAdmin) {
		this.isApprovedByAdmin = isApprovedByAdmin;
	}

	public Boolean getIsProfileVerified() {
		return isProfileVerified;
	}

	public void setIsProfileVerified(Boolean isProfileVerified) {
		this.isProfileVerified = isProfileVerified;
	}

	/*
	 * public BusinessServiceType getBusinessServiceType() { return
	 * businessServiceType; }
	 * 
	 * public void setBusinessServiceType(BusinessServiceType businessServiceType) {
	 * this.businessServiceType = businessServiceType; }
	 */

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	/*
	 * public Address getAddress() { return address; }
	 * 
	 * public void setAddress(Address address) { this.address = address; }
	 */

	public String getWebSiteAddress() {
		return webSiteAddress;
	}

	public void setWebSiteAddress(String webSiteAddress) {
		this.webSiteAddress = webSiteAddress;
	}

	public Boolean getIsCompanyRegister() {
		return isCompanyRegister;
	}

	public void setIsCompanyRegister(Boolean isCompanyRegister) {
		this.isCompanyRegister = isCompanyRegister;
	}

	public String getCompanyRegisterNumber() {
		return companyRegisterNumber;
	}

	public void setCompanyRegisterNumber(String companyRegisterNumber) {
		this.companyRegisterNumber = companyRegisterNumber;
	}

	public String getCompanyRegisterYear() {
		return companyRegisterYear;
	}

	public void setCompanyRegisterYear(String companyRegisterYear) {
		this.companyRegisterYear = companyRegisterYear;
	}

	public String getCountryOfRegistration() {
		/*if(util.StringUtils.isNotEmpty(this.countryOfRegistration)){
			try{
				if(Country.find.byId(Long.parseLong(this.countryOfRegistration)) != null){
				return Country.find.byId(Long.parseLong(this.countryOfRegistration)).getName();
			}
			}catch(Exception ex){
return "";
			}
		}*/
		return countryOfRegistration;
	}

	public void setCountryOfRegistration(String countryOfRegistration) {
		this.countryOfRegistration = countryOfRegistration;
	}

	public String getTaxIdentificationNumber() {
		return taxIdentificationNumber;
	}

	public void setTaxIdentificationNumber(String taxIdentificationNumber) {
		this.taxIdentificationNumber = taxIdentificationNumber;
	}




	public FileEntity getLicensedId() {
		return licensedId;
	}

	public void setLicensedId(FileEntity licensedId) {
		this.licensedId = licensedId;
	}

	public FileEntity getCorporateProfileId() {
		return corporateProfileId;
	}

	public void setCorporateProfileId(FileEntity otherFileId) {
		this.corporateProfileId = otherFileId;
	}

	public void setPassportDetails(String passportDetails) {
		this.passportDetails = passportDetails;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPassportDetails() {
		return passportDetails;
	}
	public AppUser getAdviosrByUser() {
		return adviosrByUser;
	}

	public void setAdviosrByUser(AppUser adviosrByUser) {
		this.adviosrByUser = adviosrByUser;
	}

	public String getSocialMedia() {
		return socialMedia;
	}

	public void setSocialMedia(String socialMedia) {
		this.socialMedia = socialMedia;
	}

	public String getSocialMediaProfileUrl() {
		return socialMediaProfileUrl;
	}

	public void setSocialMediaProfileUrl(String socialMediaProfileUrl) {
		this.socialMediaProfileUrl = socialMediaProfileUrl;
	}

	public String getAboutCorporateProfile() {
		return aboutCorporateProfile;
	}

	public void setAboutCorporateProfile(String aboutCorporateProfile) {
		this.aboutCorporateProfile = aboutCorporateProfile;
	}

	/*
	 * @Override public String toString() { return "BusinessAdvisor [adviosrByUser="
	 * + adviosrByUser + ", companyName=" + companyName + ", firmName=" + firmName +
	 * ", address=" + address + ", webSiteAddress=" + webSiteAddress +
	 * ", isCompanyRegister=" + isCompanyRegister + ", companyRegisterNumber=" +
	 * companyRegisterNumber + ", companyRegisterYear=" + companyRegisterYear +
	 * ", countryOfRegistration=" + countryOfRegistration +
	 * ", taxIdentificationNumber=" + taxIdentificationNumber + ", passportDetails="
	 * + passportDetails + ", nationality=" + nationality + ", licensedId=" +
	 * licensedId + ", otherFileId=" + corporateProfileId + "]"; }
	 * 
	 * public static Map<Integer, List<BusinessAdvisorViewBean>>
	 * getAdvisorData(SearchFilterBean filterBean) { Country currentCountry =
	 * Country.getUserLocaleCountry(); BusinessServiceType businessServiceType =
	 * null;
	 * 
	 * List<BusinessAdvisorViewBean> beanList = new
	 * LinkedList<BusinessAdvisorViewBean>(); ExpressionList<BusinessAdvisor>
	 * businessAdvisorQuery = BusinessAdvisor.find.where().eq("isActive",
	 * Boolean.TRUE).eq("isApprovedByAdmin", Boolean.TRUE);
	 * 
	 * 
	 * 
	 * if (filterBean.getBusinessServiceTypeId() != null &&
	 * filterBean.getBusinessServiceTypeId() != 0) { businessServiceType =
	 * BusinessServiceType.find.byId(filterBean.getBusinessServiceTypeId()); }
	 * 
	 * if (businessServiceType != null) {
	 * businessAdvisorQuery.eq("businessServiceType", businessServiceType); }
	 * 
	 * boolean hasLocation = false;
	 * 
	 * List<City> cityList = filterBean.getCityList(); List<State> stateList =
	 * filterBean.getStateList(); List<Country> countryList =
	 * filterBean.getCountryList();
	 * 
	 * if (!cityList.isEmpty() || !stateList.isEmpty() || !countryList.isEmpty()) {
	 * hasLocation = true;
	 * businessAdvisorQuery.isNotNull("address").or(Expr.and(Expr.isNotNull(
	 * "address.city"), Expr.in("address.city", cityList)), Expr.or(
	 * Expr.and(Expr.isNotNull("address.state"), Expr.in("address.state",
	 * stateList)), Expr.and(Expr.isNotNull("address.country"),
	 * Expr.in("address.country", countryList)))); } // if (cityList != null &&
	 * !cityList.isEmpty()) { // hasLocation = true; //
	 * businessAdvisorQuery.isNotNull("address").in("address.city", cityList); // }
	 * // Logger.info("stateList"+stateList); // if (stateList != null &&
	 * !stateList.isEmpty()) { // hasLocation = true; //
	 * businessAdvisorQuery.isNotNull("address").in("address.state", stateList); //
	 * } // if (countryList != null && !countryList.isEmpty()) { // hasLocation =
	 * true; // businessAdvisorQuery.isNotNull("address").in("address.country",
	 * countryList); // } if(!hasLocation){ if(currentCountry != null) {
	 * businessAdvisorQuery.isNotNull("address").eq("address.country",
	 * currentCountry); }else{ List<Country> country = Country.find.all();
	 * businessAdvisorQuery.isNotNull("address").in("address.country", country);
	 * 
	 * } } List<BusinessAdvisor> businessAdvisorList = new LinkedList<>(); Integer
	 * allBusinessServicesCount = businessAdvisorQuery.findRowCount();
	 * Map<Integer,List<BusinessAdvisorViewBean>> businessAdvisorMap = new
	 * HashMap<>(); if(filterBean.getPageNumber() != null) { businessAdvisorList =
	 * businessAdvisorQuery.findPagedList(filterBean.getPageNumber()-1,
	 * 30).getList(); }else { businessAdvisorList = businessAdvisorQuery.findList();
	 * } //List<BusinessAdvisor> businessAdvisorList =
	 * businessAdvisorQuery.findPagedList(filterBean.getPageNumber(),
	 * PublicController.businessServicesPageCount).getList(); for (BusinessAdvisor
	 * businessAdvisor : businessAdvisorList) {
	 * //if(AppUser.isPaidUser(businessAdvisor.getAdviosrByUser(),businessAdvisor.
	 * getAdviosrByUser().getAppUserDefaultRole())) {
	 * beanList.add(BusinessAdvisorViewBean.toBusinessAdvisorViewBean(
	 * businessAdvisor)); // } } businessAdvisorMap.put(allBusinessServicesCount,
	 * beanList); return businessAdvisorMap;
	 * 
	 * }
	 */
	public List<String> getFirmnames(){
		List<String> firmList = new ArrayList<>();
		firmList.add("Private");
		firmList.add("Public");
		return firmList;
	}


}
