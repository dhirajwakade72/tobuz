package com.tobuz.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.ui.Model;

@Entity
public class BusinessAdvertKeywordInfo extends BaseEntity{
	
	@ManyToOne
	private BusinessAdvert businessAdvert;
	/*
	 * @ManyToOne private CategoryKeyword categoryKeyword;
	 * 
	 * 
	 * public static Model.Finder<Long, BusinessAdvertKeywordInfo> find = new
	 * Model.Finder<Long,
	 * BusinessAdvertKeywordInfo>(BusinessAdvertKeywordInfo.class);
	 * 
	 */
	

	public BusinessAdvert getBusinessAdvert() {
		return businessAdvert;
	}


	public void setBusinessAdvert(BusinessAdvert businessAdvert) {
		this.businessAdvert = businessAdvert;
	}


	/*
	 * public CategoryKeyword getCategoryKeyword() { return categoryKeyword; }
	 * 
	 * 
	 * public void setCategoryKeyword(CategoryKeyword categoryKeyword) {
	 * this.categoryKeyword = categoryKeyword; }
	 * 
	 * public static BusinessAdvertKeywordInfo
	 * getBusinessAdvertKeyWordInfos(BusinessAdvert businessAdvert,CategoryKeyword
	 * categoryKeyword){ return
	 * BusinessAdvertKeywordInfo.find.where().and(Expr.eq("businessAdvert",
	 * businessAdvert),Expr.eq("categoryKeyword", categoryKeyword)).eq("isActive",
	 * Boolean.TRUE).setMaxRows(1).findUnique(); } public static
	 * List<BusinessAdvertKeywordInfo> getBusinessAdvertsByKeyWord(CategoryKeyword
	 * categoryKeyword){ return
	 * BusinessAdvertKeywordInfo.find.where().eq("categoryKeyword",
	 * categoryKeyword).eq("isActive", Boolean.TRUE).findList(); } public static
	 * List<BusinessAdvertKeywordInfo>
	 * getBusinessAdvertKeywordsByListing(BusinessAdvert businessAdvert){ return
	 * BusinessAdvertKeywordInfo.find.where().eq("businessAdvert",
	 * businessAdvert).eq("isActive", Boolean.TRUE).findList(); }
	 * 
	 * public static BusinessAdvertKeywordInfo
	 * createBusinessAdvertKeywordInfo(BusinessAdvert businessAdvert,CategoryKeyword
	 * categoryKeyword) { BusinessAdvertKeywordInfo subCategoryInfo =
	 * getBusinessAdvertKeyWordInfos(businessAdvert,categoryKeyword);
	 * if(subCategoryInfo == null) { subCategoryInfo = new
	 * BusinessAdvertKeywordInfo();
	 * subCategoryInfo.setCategoryKeyword(categoryKeyword);
	 * subCategoryInfo.setBusinessAdvert(businessAdvert);
	 * subCategoryInfo.setIsActive(Boolean.TRUE); subCategoryInfo.save(); } return
	 * subCategoryInfo; } public void save() { if(this.getBusinessAdvert() != null)
	 * { this.getBusinessAdvert().update(); } super.save(); }
	 * 
	 * @Override public void update() { if(this.getBusinessAdvert() != null) {
	 * this.getBusinessAdvert().update(); } super.update(); }
	 */
}
