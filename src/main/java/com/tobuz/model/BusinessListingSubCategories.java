package com.tobuz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
	
@Entity
@Table(name="business_listing_subcategory_info")
@Data
public class BusinessListingSubCategories extends BaseEntity{

    @Column(name = "business_listing_id")
    private Long businessListingId;

    @Column(name = "sub_category_id")
    private Long subCategoryId;

    @Column(name = "category_id")
    private Long categoryId;
}
