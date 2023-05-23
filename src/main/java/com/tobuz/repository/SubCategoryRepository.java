package com.tobuz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tobuz.model.Category;
import com.tobuz.model.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
	
	@Query(value = " from SubCategory sc where sc.category =?1")
	   public  List<SubCategory> findSubCategotyByCategory(Category category);
	  

}
