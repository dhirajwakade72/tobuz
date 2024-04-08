package com.tobuz.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tobuz.model.AppUser;
import com.tobuz.model.BusinessFeature;
import com.tobuz.model.BusinessListingFeatureInfo;
import com.tobuz.model.BusinessListingOutLet;
import com.tobuz.model.Category;
import com.tobuz.model.Country;
import com.tobuz.model.FileEntity;
import com.tobuz.model.FranchiseFor;
import com.tobuz.model.FranchisePartnerType;
import com.tobuz.model.ListingKeyword;
import com.tobuz.model.Role;

import lombok.Data;
@Data
public class BusinessListingDTO {
	
	private AppUser listedByUser;

	private Role role;

	private String listingId;

	private FranchiseFor franchiseFor;

	private String franchiseName;

	private String suggestedTitle;

	private String  listingType;
	
	private String  status;

	private String listingFor;

	private FranchisePartnerType franchisePartnerType;

	private String title;

	private String listingDescription;

	private Category Category;

	private String seoKeyword;

	private List<ListingKeyword> keywordList = new ArrayList<ListingKeyword>();

	private Country businessAddressCountry;

	private String slugTitle;

	private String metaTitle;

	private String metaDescription;

	private String metaUrl;

	private String businessListingStatus;

	private String name;

	private String email;

	private String countryCode;

	private String contactNumber;

	private Boolean showContactDetailsOnListing = Boolean.FALSE;

	private Date postedOn;

	private String postedOnStr;
	
	private Date soldOn;

	private AppUser soldMarkedBy;
	
	private BusinessListingOutLet businessListingOutLet;

	private Boolean isDistressSale = Boolean.FALSE;

	private String description;

	private String searchIndex;

	private Boolean isRecomendedByAdmin = Boolean.FALSE;

	private Date adminRecomendedOn;

	private Integer noOfOutlets = 1;

	private Date expiredOn;
	
	private String expiredOnStr;

	private Date adminApprovedOn;

	private Date adminDeletedOn;

	private AppUser removedBy;

	private Boolean isAdminResponded = Boolean.FALSE;

	private String adminComment;

	private String listingKeywords;

	private Double latitude;

	private Double longitude;

	private Boolean isDraftReminderSent = Boolean.FALSE;

	private Boolean isListingMarkedWorldWide = Boolean.FALSE;
	
	public List<FileEntity> listingGallery ;
	
	//private List<BusinessListingFeatureInfo> businessListingFeatureInfoList;
	
	private List<BusinessFeature> businessFeatures;
	
	private Boolean isFovorite;


}
