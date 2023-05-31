package com.tobuz.object;

import java.io.Serializable;
import java.util.List;

public class CategoryDTO implements Serializable{

	private long id ;
	
	private String name ;
	
	String sequence;
	
	String featuredCategory;
	
	String isCommercialCategory;
	
	Long imageId ;
	
	String imagePath;
	
	 String subCategoryName;
	
	 private List<SubCategoryDTO> subCategoryList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
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

	public List<SubCategoryDTO> getSubCategoryList() {
		return subCategoryList;
	}

	public void setSubCategoryList(List<SubCategoryDTO> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}


	
}
