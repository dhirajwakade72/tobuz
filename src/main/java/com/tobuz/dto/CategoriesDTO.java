package com.tobuz.dto;

import java.util.ArrayList;
import java.util.List;

import com.tobuz.model.SubCategory;

public class CategoriesDTO 
{
	private long id ;
	
	private String name ;
	
	String sequence;
	
	String featuredCategory;
	
	String isCommercialCategory;
	
	Long imageId ;
	
	String imagePath;
	
	 String subCategoryName;	


	Boolean isCommercial;

	private List<SubCategory> subCategoryList = new ArrayList<SubCategory>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getFeaturedCategory() {
		return featuredCategory;
	}

	public void setFeaturedCategory(String featuredCategory) {
		this.featuredCategory = featuredCategory;
	}

	public String getIsCommercialCategory() {
		return isCommercialCategory;
	}

	public void setIsCommercialCategory(String isCommercialCategory) {
		this.isCommercialCategory = isCommercialCategory;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public Boolean getIsCommercial() {
		return isCommercial;
	}

	public void setIsCommercial(Boolean isCommercial) {
		this.isCommercial = isCommercial;
	}

	public List<SubCategory> getSubCategoryList() {
		return subCategoryList;
	}

	public void setSubCategoryList(List<SubCategory> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}


}
