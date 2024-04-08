package com.tobuz.repository;

import java.util.List;

import com.tobuz.object.CategoryDTO;
import com.tobuz.projection.CategoryByFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tobuz.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	 @Query(value = "select id, name ,sequence , is_featured_category , created_on from category ", nativeQuery = true)
	    public List<Object[]> getCategories();
	 
	    
	    @Query(value = " "
	    		+  " "
	    		+ "  "
	    		+ "select id, name ,sequence , is_featured_category , created_on from category  "
	    		+ " "
	    		+ " ", nativeQuery = true)
	    public List<Object[]> getTobuzCategoryById(long id);

	@Query(value = "SELECT id, name,is_commercial_category as isCommercial FROM category WHERE is_active is true ORDER BY name", nativeQuery = true)
	public List<CategoryByFilter> getAllCategoryList();
	
	
	public List<Category> findAllByOrderByNameAsc();
	    
}
