package com.tobuz.repository;

import java.util.List;

import com.tobuz.object.CategoryDTO;
import com.tobuz.projection.BusinessByFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tobuz.model.BusinessListing;
import com.tobuz.model.BusinessListingFeatureInfo;
import com.tobuz.model.FileEntity;
import org.springframework.data.repository.query.Param;

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
			+ "			where f.business_listing_id is not null and blo.total_business_sale_price is not null and (:ids IS NULL OR b.category_id IN :ids) and b.listing_type= :lType  order by f.id desc limit 200  "
			+ "			", nativeQuery = true)
    public List<Object[]> getTopBusinessListingsByCategory(List<Long> ids , String lType);
    
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
    @Query(value = "SELECT f.file_path AS FilePath, b.title AS Title, b.listing_description AS ListingDescription, blo.total_business_sale_price AS Price, b.suggested_title AS SuggestedTitle, b.id AS Id, b.business_address_country_id as countryId FROM file_entity f JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND (:id IS NULL OR b.category_id = :id) AND (b.listing_type = :lType OR :lType IS NULL) AND (b.title LIKE CONCAT('%', :word, '%') OR b.listing_description LIKE CONCAT('%', :word, '%')) OR :word IS NULL ORDER BY CASE WHEN :isTitleASC IS NOT NULL THEN b.title END ASC,CASE WHEN :isTitleASC IS NOT NULL THEN b.title END DESC,CASE WHEN :isPricASC IS NOT NULL THEN blo.total_business_sale_price END ASC,CASE WHEN :isPricASC IS NOT NULL THEN blo.total_business_sale_price END DESC LIMIT 200", nativeQuery = true)
	public List<BusinessByFilter> getBusinessByFilter(@Param("id") Long id, @Param("lType") String lType, @Param("isTitleASC") Boolean isTitleASC, @Param("isPricASC") Boolean isPricASC, @Param("word") String word);

	@Query(value = "SELECT f.file_path AS FilePath, b.title AS Title, b.listing_description AS ListingDescription, blo.total_business_sale_price AS Price, b.suggested_title AS SuggestedTitle, b.id AS Id, b.business_address_country_id as countryId FROM file_entity f JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND (b.category_id = :id OR :id IS NULL) AND (b.listing_type = :lType OR :lType IS NULL) AND (b.franchise_for = :fType OR :fType IS NULL) AND (b.title LIKE CONCAT('%', :word, '%') OR b.listing_description LIKE CONCAT('%', :word, '%')) OR :word IS NULL ORDER BY CASE WHEN :isTitleASC IS NOT NULL THEN b.title END ASC,CASE WHEN :isTitleASC IS NOT NULL THEN b.title END DESC,CASE WHEN :isPricASC IS NOT NULL THEN blo.total_business_sale_price END ASC,CASE WHEN :isPricASC IS NOT NULL THEN blo.total_business_sale_price END DESC LIMIT 200", nativeQuery = true)
	public List<BusinessByFilter> getBusinessWithFranchiseFilter(@Param("id") Long id, @Param("lType") String lType,@Param("fType") String fType, @Param("isTitleASC") Boolean isTitleASC, @Param("isPricASC") Boolean isPricASC, @Param("word") String word);

	@Query(value = "SELECT f.file_path AS FilePath, b.title AS Title, b.listing_description AS ListingDescription, blo.total_business_sale_price AS Price, b.suggested_title AS SuggestedTitle, b.id AS Id, b.business_address_country_id as countryId FROM file_entity f JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND (b.listing_type = :lType OR :lType IS NULL) AND (b.franchise_for = :fType OR :fType IS NULL) AND (b.title LIKE CONCAT('%', :word, '%') OR b.listing_description LIKE CONCAT('%', :word, '%')) OR :word IS NULL ORDER BY CASE WHEN :isTitleASC IS NOT NULL THEN b.title END ASC,CASE WHEN :isTitleASC IS NOT NULL THEN b.title END DESC,CASE WHEN :isPricASC IS NOT NULL THEN blo.total_business_sale_price END ASC,CASE WHEN :isPricASC IS NOT NULL THEN blo.total_business_sale_price END DESC LIMIT 200", nativeQuery = true)
	public List<BusinessByFilter> getBusinessWithFranchiseFilter(@Param("lType") String lType,@Param("fType") String fType, @Param("isTitleASC") Boolean isTitleASC, @Param("isPricASC") Boolean isPricASC, @Param("word") String word);


	@Query(value = "SELECT f.file_path AS FilePath, b.title AS Title, b.listing_description AS ListingDescription, blo.total_business_sale_price AS Price, b.suggested_title AS SuggestedTitle, b.id AS Id, b.business_address_country_id as countryId FROM file_entity f JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND (b.listing_type = :lType OR :lType IS NULL) AND (b.title LIKE CONCAT('%', :word, '%') OR b.listing_description LIKE CONCAT('%', :word, '%')) OR :word IS NULL ORDER BY CASE WHEN :isTitleASC IS NOT NULL THEN b.title END ASC,CASE WHEN :isTitleASC IS NOT NULL THEN b.title END DESC,CASE WHEN :isPricASC IS NOT NULL THEN blo.total_business_sale_price END ASC,CASE WHEN :isPricASC IS NOT NULL THEN blo.total_business_sale_price END DESC LIMIT 200", nativeQuery = true)
	public List<BusinessByFilter> getBusinessByFilter(@Param("lType") String lType, @Param("isTitleASC") Boolean isTitleASC, @Param("isPricASC") Boolean isPricASC, @Param("word") String word);

}
