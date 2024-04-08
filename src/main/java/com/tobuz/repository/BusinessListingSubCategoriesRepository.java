package com.tobuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tobuz.model.BusinessListingSubCategories;
@Repository
public interface BusinessListingSubCategoriesRepository extends JpaRepository<BusinessListingSubCategories, Long> 
{

}
