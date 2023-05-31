package com.tobuz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tobuz.model.BusinessListing;
import com.tobuz.model.BusinessListingFeatureInfo;
import com.tobuz.model.FileEntity;

public interface FileEntityRepositiory  extends JpaRepository<FileEntity, Long> {

	@Query(value = "select  f.file_path, b.title , b.listing_description ,blo.total_business_sale_price  , b.suggested_title ,b.id from file_entity f join business_listing b "
			+ "			on f.business_listing_id = b.id "
			+ "			inner join Business_Listing_Out_Let blo on   b.business_listing_out_let_id  = blo.id "
			+ "			where f.business_listing_id is not null and blo.total_business_sale_price is not null   "
			+ "			 limit 20", nativeQuery = true)
    public List<Object[]> getTopTenBusiness();
    
    @Query(value = "select   f.file_path, b.title , b.listing_description ,blo.total_business_sale_price ,b.suggested_title, b.id   from file_entity f join business_listing b "
			+ "			on f.business_listing_id = b.id "
			+ "			inner join Business_Listing_Out_Let blo on   b.business_listing_out_let_id  = blo.id "
			+ "			where f.business_listing_id is not null and blo.total_business_sale_price is not null  order by "
			+ "			f.id desc  limit 20", nativeQuery = true)
    public List<Object[]> getTopTenRecentBusiness();
    
	@Query(value = "select f.file_path, b.title , b.listing_description ,blo.total_business_sale_price  , b.suggested_title , b.id from file_entity f join business_listing b "
			+ "			on f.business_listing_id = b.id "
			+ "			join Business_Listing_Out_Let blo on   b.business_listing_out_let_id  = blo.id "
			+ "			where f.business_listing_id is not null and blo.total_business_sale_price is not null and b.listing_type  = 'BUSINESS'  order by b.id desc  limit 300  "
			+ "			", nativeQuery = true)
    public List<Object[]> getTopBusinessListings();
    
	@Query(value = "select f.file_path, b.title , b.listing_description ,blo.total_business_sale_price  , b.suggested_title , b.id from file_entity f join business_listing b "
			+ "			on f.business_listing_id = b.id "
			+ "			join Business_Listing_Out_Let blo on   b.business_listing_out_let_id  = blo.id "
			+ "			where f.business_listing_id is not null and blo.total_business_sale_price is not null and b.listing_type  = 'COMMERCIAL'  order by b.id desc  limit 300  "
			+ "			", nativeQuery = true)
    public List<Object[]> topCommercialListings();
    
	@Query(value = "select f.file_path, b.title , b.listing_description ,blo.total_business_sale_price  , b.suggested_title , b.id from file_entity f join business_listing b "
			+ "			on f.business_listing_id = b.id "
			+ "			join Business_Listing_Out_Let blo on   b.business_listing_out_let_id  = blo.id "
			+ "			where f.business_listing_id is not null and blo.total_business_sale_price is not null and b.listing_type  = 'FRANCHISE'  order by b.id desc  limit 300  "
			+ "			", nativeQuery = true)
    public List<Object[]> topFranchesieListings();
    
	@Query(value = "select f.file_path, b.title , b.listing_description ,blo.total_business_sale_price  , b.suggested_title , b.id from file_entity f join business_listing b "
			+ "			on f.business_listing_id = b.id "
			+ "			join Business_Listing_Out_Let blo on   b.business_listing_out_let_id  = blo.id "
			+ "			where f.business_listing_id is not null and blo.total_business_sale_price is not null and category_id =:id and b.listing_type= :lType  order by f.id desc limit 200  "
			+ "			", nativeQuery = true)
    public List<Object[]> getTopBusinessListingsByCategory(long id , String lType);
    
    @Query(value = " FROM FileEntity where businessListing =?1  AND fileType ='IMAGE'")
	  public List<FileEntity> findByListingId(BusinessListing businessListing);
     
    @Query(value = " FROM BusinessListingFeatureInfo where businessListing =?1 ")
	    public List<BusinessListingFeatureInfo> findBusinessListingFeatureInfoByListingId(BusinessListing businessListing);
   
    @Query(value = "select id , user_name , about_user ,description  from testimonial where is_admin_approved = 'true'  order by id limit 3 ", nativeQuery = true)
    public List<Object[]> getTestimonials();
    
    
    
	@Query(value = " select  f.file_path, b.title , b.listing_description ,blo.total_business_sale_price  , b.suggested_title , "
			+ "    b.id from file_entity f join business_listing b 			on f.business_listing_id = b.id "
			+ "			inner join Business_Listing_Out_Let blo on   b.business_listing_out_let_id  = blo.id inner join "
			+ "    		Favourite_Business_Listing fbl on b.id=	fbl.business_listing_id	"
			+ "			where f.business_listing_id is not null and blo.total_business_sale_price is not null   and fbl.user_id=:userId  "
			+ "			 limit 20", nativeQuery = true)
    public List<Object[]> getFavouriteBusiness(long userId);
    
    
	
}
