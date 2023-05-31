package com.tobuz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tobuz.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	 @Query(value = " "
	    		+  " "
	    		+ "  "
	    		+ "select id, name ,sequence , is_featured_category , created_on from category  "
	    		+ " "
	    		+ " ", nativeQuery = true)
	    public List<Object[]> getCategories();
	 
	    
	    @Query(value = " "
	    		+  " "
	    		+ "  "
	    		+ "select id, name ,sequence , is_featured_category , created_on from category  "
	    		+ " "
	    		+ " ", nativeQuery = true)
	    public List<Object[]> getTobuzCategoryById(long id);
	 
	    
	    
}
