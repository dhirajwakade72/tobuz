package com.tobuz.object;

public class BusinessListingQueryDTO {

	private Long businessid;
	private String filepath;
	private String title;
	private String listingdescription;	
	private Double totalbusinesssaleprice;
	private String suggestedtitle;
	private Boolean addedToFavourites;
	private String countryName;
	private String currency;
	private String currencyImage;
	private Long likeCount;
	
	public Long getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCurrencyImage() {
		return currencyImage;
	}
	public void setCurrencyImage(String currencyImage) {
		this.currencyImage = currencyImage;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public Boolean getAddedToFavourites() {
		return addedToFavourites;
	}
	public void setAddedToFavourites(Boolean addedToFavourites) {
		this.addedToFavourites = addedToFavourites;
	}
	public Long getBusinessid() {
		return businessid;
	}
	public void setBusinessid(Long businessid) {
		this.businessid = businessid;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getListingdescription() {
		return listingdescription;
	}
	public void setListingdescription(String listingdescription) {
		this.listingdescription = listingdescription;
	}
	public Double getTotalbusinesssaleprice() {
		return totalbusinesssaleprice;
	}
	public void setTotalbusinesssaleprice(Double totalbusinesssaleprice) {
		this.totalbusinesssaleprice = totalbusinesssaleprice;
	}
	public String getSuggestedtitle() {
		return suggestedtitle;
	}
	public void setSuggestedtitle(String suggestedtitle) {
		this.suggestedtitle = suggestedtitle;
	}
	
	
}
