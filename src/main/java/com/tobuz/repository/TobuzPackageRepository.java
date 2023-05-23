package com.tobuz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tobuz.model.tobuzpackage.TobuzPackage;
import com.tobuz.model.tobuzpackage.TobuzPackageService;

public interface TobuzPackageRepository extends JpaRepository<TobuzPackage, Long>{

	 @Query(value = " "
	    		+  " "
	    		+ "  select id ,  tobuz_package_type ,  expiry_period_in_months , cost ,user_role from public.tobuz_package where is_active = 'true'  ; "
	    		+ " "
	    		+ " ", nativeQuery = true)
	    public List<Object[]> getTobuzPackagesBySearchKey();
	    
	    
	    @Query(value = " "
	    		+  " "
	    		+ "  select id ,  tobuz_package_type ,  expiry_period_in_months , cost ,user_role from public.tobuz_package where is_active = 'true'  and user_role = :userRole  "
	    		+ " "
	    		+ " ", nativeQuery = true)
	    public List<Object[]> getTobuzPackagesByUser(String userRole);
	    
	    
	    @Query(value = " "
	    		+  " "
	    		+ "  select id, title from tobuz_package_service   where user_role =:userRole "
	    		+ " "
	    		+ " ", nativeQuery = true)
	    public List<Object[]> getTobuzFeaturesByUser(String userRole);
	    
	    
	    @Query(value = " "
	    		+  " "
	    		+ "  FROM TobuzPackageService where id =:id "
	    		+ " "
	    		+ " ", nativeQuery = true)
	    public TobuzPackageService findfeatureById(long id);
	    
	    
	    
	    
	    @Query(value = " "
	    		+  " "
	    		+ "  select country_id, user_role , tobuz_package_type , description , cost ,expiry_period_in_months , sequence ,  "
	    		+ "  advert_list_count , file_upload_count , no_of_contacts_access  "
	    		+ "  from tobuz_package  where id =:id  ; "
	    		+ " "
	    		+ " ", nativeQuery = true)
	    public List<Object[]> getTobuzPackagesById(long id);
	    
	    
	    @Query(value = " "
	    		+  " "
	    		+ " "
	    		+ "	select id, title from tobuz_package_service  "
	    		+ " "
	    		+ " ", nativeQuery = true)
	    public List<Object[]> getTobuzFeatures();
	    
	    @Query(value = " "
	    		+  " "
	    		+ " select id, title ,country_id ,  user_role , description  from tobuz_package_service where id =:id   "
	    		+ " "
	    		+ " ", nativeQuery = true)
	    public List<Object[]> getTobuzFeatureById(long id);
	    
	    
	    
	    
}
