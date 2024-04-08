
package com.tobuz.repository;

import java.sql.Timestamp;
import java.util.List;

import com.tobuz.projection.BusinessServiseTypeList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tobuz.model.BusinessListing;
import org.springframework.data.repository.query.Param;

public interface BusinessListingRepository extends JpaRepository<BusinessListing, Long> {
	
	  
    @Query(value = " "
    		+  " select id, business_service_type  from  business_service_type ", nativeQuery = true)
    public List<Object[]> getAllBusinessTypes();
    
    
    @Query(value = " "
    		+  " select id, name  from  category ", nativeQuery = true)
    public List<Object[]> getAllCategories();
     
    @Query(value = "select bl.id,bl.title,bl.listing_for,bl.country_code,bl.listing_id,bl.business_listing_status,au.user_default_role,bl.created_on from business_listing bl inner join app_user au on bl.listed_by_user_id = au.id where business_listing_status  in ('PUBLISHED' ,'APPROVED','REJECTED','SOLD' ,'UNDER_REVIEW' ) order by id desc limit 5000", nativeQuery = true)
    public List<Object[]> getAllpublishedListings();
    
    @Query(value = " "
    		+ " select ba.id ,ba.advert_id ,  ba.title ,business_advert_status  , "+
    		" au.user_default_role , ba.created_on ,investment_range_from , investment_range_to  from Business_Advert ba   inner join app_user au on ba.adverted_by_user_id = au.id " 
    		 +"  where business_advert_status  in ('PUBLISHED' ,'APPROVED','REJECTED','SOLD','UNDER_REVIEW' ) order by id desc limit 5000 "    		, nativeQuery = true)
    public List<Object[]> getAllAdverts();
    
    
    @Query(value="select bl from BusinessListing bl where bl.listedByUser.id=?1")
    public List<BusinessListing> findAllByUserId(Long userId);
    
    @Query(value = " "
    		+  " update business_listing set business_listing_status =:status where id =:id", nativeQuery = true)
    public int  updateBusinessListing(String status  , long id);
    
    @Query(value = " "
    		+  "select count (*) from Favourite_Business_Listing where user_id =:id", nativeQuery = true)
    public int  findFavouritesForUser( long id);
    
    @Query(value = " "
    	    		+ "select advert_id , title , created_on  , expired_on ,business_advert_status   from Business_Advert where adverted_by_user_id =:id", nativeQuery = true)
    public List<Object[]>   findBusinessAdvertsforUser( long id);
    
    
    @Query(value = " "
    		+ "select  tp.tobuz_package_type ,tp.created_on  ,tp.advert_list_count, tp.no_of_contacts_access , "
    		+ "					ut.activated_on , ut.package_active_for, ut.user_package_status  , ut.expired_on "
    		+ "					from tobuz_package tp inner join user_tobuz_service_package_info ut on tp.id = ut.tobuz_package_id  where user_id =:id", nativeQuery = true)
    public List<Object[]>   getPackageInfoForSeller( long id);

    @Query(value = " "
    		+ " "
    		+ "	 select distinct ba.id ,  ba.advert_id , ba.title ,ba.created_on,ba.expired_on ,tp.tobuz_package_type ,ba.business_advert_status from business_advert ba join user_tobuz_service_package_info ut on "
    		+ "	 ut.user_id = ba.adverted_by_user_id join tobuz_package tp on tp.id = ut.tobuz_package_id where ut.user_id=:id and ba.listing_type = :type"
    		+ "	 "
    		+ "	", nativeQuery = true)
    public List<Object[]>   getAdvertListingsForTypeAndUser( long id ,String type);

	@Query(value = "SELECT id, business_service_type as businessServiceType FROM business_service_type WHERE is_active is true ORDER BY id", nativeQuery = true)
	public Page<BusinessServiseTypeList> getAllBusinessServiseTypeList(Pageable pageable);

	@Modifying
	@Query(value = "INSERT INTO favourite_business_listing (is_active, user_id, role_id, business_listing_id, added_on, created_on, last_update) VALUES (true, :userId, :roleId, :businessListingId, :currentTimeStamp, :currentTimeStamp, :currentTimeStamp )", nativeQuery = true)
	void addFavouriteBusiness(@Param("userId") Long userId, @Param("roleId") Integer roleId, @Param("businessListingId") Long businessListingId, @Param("currentTimeStamp") Timestamp currentTimeStamp);

	@Modifying
	@Query(value = "DELETE FROM favourite_business_listing WHERE user_id = :userId AND business_listing_id = :businessListingId", nativeQuery = true)
	void removeFavouriteBusiness(@Param("userId") Long userId, @Param("businessListingId") Long businessListingId);
		
	@Query(value = "select count(*) as count from favourite_business_listing where business_listing_id=:businessListingId", nativeQuery = true)
	Long getFavoriteCount(@Param("businessListingId") Long businessListingId);
	
	@Query(value = "select count(*) as count from favourite_business_listing where business_advert_id=:advertId", nativeQuery = true)
	Long getAdvertFavoriteCount(@Param("advertId") Long advertId);
	
	@Query(value = "select business_listing_id from favourite_business_listing where user_id=:userId", nativeQuery = true)
	List<Long> getUserFavoriteListing(@Param("userId") Long userId);
	
	@Query(value = "select business_advert_id from favourite_business_listing where user_id=:userId", nativeQuery = true)
	List<Long> getUserFavoriteAdvert(@Param("userId") Long userId);

}
