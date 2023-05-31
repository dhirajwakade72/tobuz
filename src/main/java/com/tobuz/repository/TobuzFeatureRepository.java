package com.tobuz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tobuz.model.tobuzpackage.TobuzPackageService;

public interface TobuzFeatureRepository  extends JpaRepository<TobuzPackageService, Long>{


	   @Query(value = " "
	    		+  " "
	    		+ " select id , user_name ,  about_user , description , email from testimonial "
	    		+ " "
	    		+ " ", nativeQuery = true)
	    public List<Object[]>  getAdminTestimonials();
	    
	    
	    @Query(value = " "
	    		+  " "
	    	
	    		+ "select id , content_type , description , video_url ,  image_url  from tobuz_content_management where content_type = 'ABOUT_TOBUZ'  "
	    		+ " "
	    		+ " "
	    		+ " ", nativeQuery = true)
	    public List<Object[]>  getAboutTobuz();
}
