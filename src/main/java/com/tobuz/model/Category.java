package com.tobuz.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.h2.expression.ExpressionList;
import org.springframework.ui.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category extends BaseEntity {

	private String name;

	private Long imageId;

	private Boolean isCommercialCategory = Boolean.FALSE;

	private Integer sequence;

	private Boolean isFeaturedCategory = Boolean.FALSE;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<SubCategory> subCategoryList = new ArrayList<SubCategory>();
	/*
	 * public static Model.Finder<Long, Category> find = new Model.Finder<Long,
	 * Category>(Category.class);
	 */

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Boolean getIsFeaturedCategory() {
		return isFeaturedCategory;
	}

	public void setIsFeaturedCategory(Boolean isFeaturedCategory) {
		this.isFeaturedCategory = isFeaturedCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SubCategory> getSubCategoryList() {
		return subCategoryList;
	}

	public void setSubCategoryList(List<SubCategory> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Boolean getIsCommercialCategory() {
		return isCommercialCategory;
	}

	public void setIsCommercialCategory(Boolean isCommercialCategory) {
		this.isCommercialCategory = isCommercialCategory;
	}
	/*
	 * public static List<Category> getCategoriesByBusinessType(ListingType
	 * listingType){
	 * 
	 * 
	 * if(listingType.equals(ListingType.COMMERCIAL)) { return
	 * Category.find.where().eq("isCommercialCategory", Boolean.TRUE).eq("isActive",
	 * Boolean.TRUE).orderBy().asc("name").findList(); }else
	 * if(listingType.equals(ListingType.FRANCHISE)){ return
	 * Category.find.where().eq("isActive",
	 * Boolean.TRUE).orderBy().asc("name").findList(); }else { return
	 * Category.find.where().eq("isCommercialCategory",
	 * Boolean.FALSE).eq("isActive", Boolean.TRUE).orderBy().asc("name").findList();
	 * }
	 * 
	 * }
	 */
	/*
	 * public static Map<Category, List<SubCategory>>
	 * getCategoriesAndSubCategoriesByBusinessType(ListingType listingType){
	 * Map<Category, List<SubCategory>> map = new HashMap<>(); List<Category>
	 * categories = new ArrayList<>(); categories =
	 * Category.find.where().eq("isCommercialCategory",
	 * Boolean.FALSE).eq("isActive", Boolean.TRUE).orderBy().asc("name").findList();
	 * for(Category c : categories) { List<SubCategory> subCategoryIds = new
	 * ArrayList<>(); subCategoryIds = SubCategory.find.where().eq("isActive",
	 * Boolean.TRUE).eq("category", c).orderBy().asc("name").findList(); map.put(c,
	 * subCategoryIds); } return map; }
	 * 
	 * 
	 * public static List<Category> getLiveCategories(){ return
	 * Category.find.where().eq("isActive",
	 * Boolean.TRUE).orderBy().asc("name").findList(); }
	 * 
	 * public static List<Category> getLiveCategoriesBySearch(String searchKey){
	 * ExpressionList<Category> categoryQuery = Category.find.where().eq("isActive",
	 * Boolean.TRUE); for(String key : searchKey.split(" ")) {
	 * categoryQuery.ilike("name", "%" + key + "%"); } return
	 * categoryQuery.findList(); }
	 * 
	 * 
	 * public static Function<? super Category, ? extends CategoryBean>
	 * categoryBeanMapper = category -> { CategoryBean categoryBean = new
	 * CategoryBean(); categoryBean.id = category.getId(); categoryBean.name =
	 * category.getName(); categoryBean.filePath = category.getImageId() != null ?
	 * category.getImageId().toString() : null;
	 * categoryBean.isCommercialCategory=category.isCommercialCategory;
	 * categoryBean.isFeaturedCategory=category.isFeaturedCategory;
	 * categoryBean.sequence=category.sequence; if(category.getImageId() != null){
	 * categoryBean.filePath =
	 * FileEntity.getfilePath.apply(FileEntity.find.byId(category.getImageId())); }
	 * categoryBean.subCategoryList =
	 * category.getSubCategoryList().stream().filter(subCat->subCat.getIsActive() ==
	 * true ).map(SubCategory::getName).collect(Collectors.toList());
	 * categoryBean.subCategory =
	 * category.getSubCategoryList().stream().filter(subCat->subCat.getIsActive() ==
	 * true ).map(SubCategory::getName).collect(Collectors.joining(","));
	 * categoryBean.seoName = StringUtils.updateTextToSEO(category.getName());
	 * return categoryBean; };
	 * 
	 * public static boolean sequenceExisted(Long categoryId ,Integer sequence) {
	 * ExpressionList<Category> categoryWhere =
	 * Category.find.where().eq("isActive",true).eq("sequence",sequence); int
	 * categoryCount = categoryWhere.findRowCount(); if (categoryId != null) {
	 * Category category = Category.find.byId(categoryId); return categoryCount == 0
	 * || (category != null && category.getSequence() != null &&
	 * category.getSequence().equals(sequence)) ? false: true; } else { return
	 * categoryCount != 0; } }
	 * 
	 * public static int getFeaturedCategoryCount(){ return
	 * Category.find.where().eq("isFeaturedCategory",Boolean.TRUE).eq("isActive",
	 * true).findRowCount(); }
	 * 
	 */

}
