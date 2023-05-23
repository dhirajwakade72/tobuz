package com.tobuz.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SubCategory extends BaseEntity {

	private String name;

	@ManyToOne
	@JsonIgnore
	private Category category;

	/*
	 * public static Model.Finder<Long, SubCategory> find = new Model.Finder<Long,
	 * SubCategory>(SubCategory.class);
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	/*
	 * public static List<SubCategory> getSubCategoryListByListingType(ListingType
	 * listingType) { if (listingType.equals(ListingType.COMMERCIAL)) { return
	 * SubCategory.find.where().eq("category.isCommercialCategory", Boolean.TRUE)
	 * .eq("isActive", Boolean.TRUE).eq("category.isActive",
	 * Boolean.TRUE).findList(); } else { return
	 * SubCategory.find.where().eq("category.isCommercialCategory", Boolean.FALSE)
	 * .eq("isActive", Boolean.TRUE).eq("category.isActive",
	 * Boolean.TRUE).findList(); } }
	 * 
	 * public static SubCategory getSubCategory(String name, Category category) {
	 * name = name != null ? name.trim() : name; return
	 * SubCategory.find.where().ieq("name", name).eq("category.id",
	 * category.getId()).setMaxRows(1) .findUnique(); }
	 * 
	 * public static List<SubCategory> getLiveSubCategories() { Set<SubCategory>
	 * categories = new LinkedHashSet<SubCategory>(); for
	 * (BusinessListingSubcategoryInfo info :
	 * BusinessListingSubcategoryInfo.find.where().isNotNull("subCategory")
	 * .eq("isActive", Boolean.TRUE).findList()) {
	 * categories.add(info.getSubCategory()); } for (BusinessAdvertSubcategoryInfo
	 * info : BusinessAdvertSubcategoryInfo.find.where().isNotNull("subCategory")
	 * .eq("isActive", Boolean.TRUE).findList()) {
	 * categories.add(info.getSubCategory()); } return new
	 * LinkedList<SubCategory>(categories); }
	 * 
	 * public static List<SubCategory> getLiveSubCategoriesByCategory(Category
	 * category) {
	 * 
	 * return SubCategory.find.where().eq("category", category).eq("isActive",
	 * Boolean.TRUE) .eq("category.isActive", Boolean.TRUE).findList(); }
	 * 
	 * public static Map<Category, List<SubCategory>>
	 * getSubCategoriesByCategory(List<Category> categoryList) { Map<Category,
	 * List<SubCategory>> categoryWithSubCategory = new LinkedHashMap<Category,
	 * List<SubCategory>>(); List<SubCategory> subCategories = new LinkedList<>();
	 * for (Category category : categoryList) { subCategories =
	 * SubCategory.find.where().eq("category", category).eq("isActive",
	 * Boolean.TRUE) .eq("category.isActive", Boolean.TRUE).findList();
	 * categoryWithSubCategory.put(category, subCategories); }
	 * 
	 * return categoryWithSubCategory;
	 * 
	 * } public static List<SubCatData> getSubCategoryWithCategory(List<Category>
	 * categoryList){ List<SubCatData> subCatDatas = new ArrayList<>();
	 * Map<Category, List<SubCategory>> categoryWithSubCategory =
	 * getSubCategoriesByCategory(categoryList);
	 * if(!categoryWithSubCategory.isEmpty()) { for(Map.Entry<Category,
	 * List<SubCategory>> entry : categoryWithSubCategory.entrySet()) { Category
	 * category = entry.getKey(); List<SubCategory> categories = entry.getValue();
	 * for(SubCategory category2 : categories) { SubCatData subCatData = new
	 * SubCatData(); subCatData.setSubId(category2.getId());
	 * subCatData.setSubCatName(category2.getName());
	 * subCatData.setCatId(category.getId()); subCatDatas.add(subCatData); } } }
	 * return subCatDatas; }
	 */
	/*
	 * public static List<SubCategory> getLiveCategoriesBySearch(String searchKey){
	 * ExpressionList<SubCategory> subCategoryQuery =
	 * SubCategory.find.where().findList(); for(String key : searchKey.split(" ")) {
	 * subCategoryQuery.ilike("name", "%" + key + "%"); } return
	 * subCategoryQuery.findList(); }
	 */
}
