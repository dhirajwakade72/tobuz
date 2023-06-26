package com.tobuz.repository;

import java.util.List;

import com.avaje.ebeaninternal.server.lib.util.Str;
import com.tobuz.object.BusinessListingDTO;
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
    

	@Query(value = "select\n" +
			"    f.file_path as FilePath,\n" +
			"    b.title as Title,\n" +
			"    b.listing_description as ListingDescription,\n" +
			"    blo.total_business_sale_price as Price,\n" +
			"    b.suggested_title as SuggestedTitle ,\n" +
			"    b.id as Id\n" +
			"from\n" +
			"    file_entity f\n" +
			"join business_listing b on\n" +
			"    f.business_listing_id = b.id\n" +
			"join Business_Listing_Out_Let blo on\n" +
			"    b.business_listing_out_let_id = blo.id\n" +
			"where\n" +
			"    f.business_listing_id is not null\n" +
			"    and blo.total_business_sale_price is not null\n" +
			"    and (b.category_id in (:ids)\n" +
			"        or :ids is null)\n" +
			"    and (b.listing_type = :lType\n" +
			"        or :lType is null)\n" +
			"    and (b.franchise_for = :fType\n" +
			"        or :fType is null)\n" +
			"order by\n" +
			"    case\n" +
			"        when :isTrue = true then b.title\n" +
			"    end asc,\n" +
			"    case\n" +
			"        when :isTrue = false then b.title\n" +
			"    end desc\n" +
			"limit 200", nativeQuery = true)
	public List<BusinessByFilter> getBusinessByTitleFilter(@Param("ids") List<Long> ids, @Param("lType") String lType, @Param("fType") String fType, @Param("isTrue") boolean isTrue);


	@Query(value = "select\n" +
			"    f.file_path as FilePath,\n" +
			"    b.title as Title,\n" +
			"    b.listing_description as ListingDescription,\n" +
			"    blo.total_business_sale_price as Price,\n" +
			"    b.suggested_title as SuggestedTitle,\n" +
			"    b.id as Id\n" +
			"from\n" +
			"    file_entity f\n" +
			"join business_listing b on\n" +
			"    f.business_listing_id = b.id\n" +
			"join Business_Listing_Out_Let blo on\n" +
			"    b.business_listing_out_let_id = blo.id\n" +
			"where\n" +
			"    f.business_listing_id is not null\n" +
			"    and blo.total_business_sale_price is not null\n" +
			"    and (b.category_id in (:ids)\n" +
			"        or :ids is null)\n" +
			"    and (b.listing_type = :lType\n" +
			"        or :lType is null)\n" +
			"    and (b.franchise_for = :fType\n" +
			"        or :fType is null)\n" +
			"order by\n" +
			"    case\n" +
			"        when :bPrice = true then blo.total_business_sale_price\n" +
			"    end asc,\n" +
			"    case\n" +
			"        when :bPrice = false then blo.total_business_sale_price\n" +
			"    end desc\n" +
			"limit 200", nativeQuery = true)
	public List<BusinessByFilter> getBusinessByPriceFilter(@Param("ids") List<Long> ids, @Param("lType") String lType, @Param("fType") String fType, @Param("bPrice") boolean bPrice);
}
