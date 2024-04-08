package com.tobuz.dto;

import java.util.List;

import lombok.Data;

@Data
public class SearchDTO {
	List<Integer>categoriesIds;
	List<Integer>countryIds;
	List<String>investmentFilterValues;
	List<Integer>businessServicesId;
	String businessType;
	String selectedCategory;
	Long userId;
}
