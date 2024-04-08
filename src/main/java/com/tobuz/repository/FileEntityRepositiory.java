package com.tobuz.repository;

import java.util.List;

import com.tobuz.projection.BusinessByFilter;
import com.tobuz.projection.FavouriteBusinessDetailsCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.tobuz.projection.FavouriteBusinessDetails;
import com.tobuz.projection.TopBusinessListingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tobuz.model.BusinessListing;
import com.tobuz.model.BusinessListingFeatureInfo;
import com.tobuz.model.FileEntity;
import com.tobuz.object.BusinessListingQueryDTO;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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

	@Query(nativeQuery = true, value = "SELECT f.file_path AS filepath, b.title AS title, b.listing_description AS listingdescription, blo.total_business_sale_price AS totalbusinesssaleprice,b.suggested_title AS suggestedtitle, b.id AS businessid FROM ( SELECT business_listing_id, MAX(file_path) AS file_path FROM file_entity GROUP BY business_listing_id) f JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND b.listing_type = 'BUSINESS' ORDER BY b.id DESC")
	Page<BusinessListingQueryDTO> getTopBusinessListings(Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT f.file_path as filepath, b.title as title, b.listing_description as listingdescription, " +
			"blo.total_business_sale_price as totalbusinesssaleprice, b.suggested_title as suggestedtitle, b.id as businessid, " +
			"CASE WHEN fb.business_listing_id IS NOT NULL THEN true ELSE false END AS addedToFavourites, fb.id " +
			"FROM (SELECT business_listing_id, MAX(file_path) AS file_path FROM file_entity GROUP BY business_listing_id ) f " +
			"JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON  b.business_listing_out_let_id = blo.id " +
			"LEFT JOIN favourite_business_listing fb ON b.id = fb.business_listing_id AND fb.user_id = :userId " +
			"WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND b.listing_type = 'BUSINESS' ORDER BY b.id DESC")
	Page<BusinessListingQueryDTO> getTopBusinessListingsByUser(@Param("userId") Long userId, Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT count(b.id), b.id as businessListingId\n" +
			"FROM ( SELECT f.file_path AS filePath, b.id AS businessId, ROW_NUMBER() OVER (PARTITION BY b.id ORDER BY f.file_path) AS rn\n" +
			"FROM file_entity f JOIN business_listing b ON f.business_listing_id = b.id WHERE f.business_listing_id IS NOT NULL ) \n" +
			"AS f JOIN business_listing b ON f.businessId = b.id \n" +
			"INNER JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id \n" +
			"INNER JOIN Favourite_Business_Listing fbl ON b.id = fbl.business_listing_id\n" +
			"WHERE blo.total_business_sale_price IS NOT null and b.listing_type ='BUSINESS' and f.rn = 1 group by b.id\n")
	List<FavouriteBusinessDetailsCount> getFavouriteBusinessCountByBusinessIdForBusinessList();

	@Query(nativeQuery = true, value = "SELECT f.file_path AS filePath, b.title AS title, b.listing_description AS listingDescription, " +
			"blo.total_business_sale_price AS totalBusinessSalePrice, b.suggested_title AS suggestedTitle, b.id AS businessId FROM business_listing b " +
			"JOIN ( SELECT business_listing_id, MAX(file_path) AS file_path FROM file_entity GROUP BY business_listing_id ) f ON f.business_listing_id = b.id " +
			"JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id " +
			"WHERE f.file_path IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND b.listing_type = 'COMMERCIAL' " +
			"ORDER BY b.id DESC LIMIT 300")
	List<TopBusinessListingDetails> topCommercialListings();

	@Query(nativeQuery = true, value = "SELECT f.file_path AS filePath, b.title AS title, b.listing_description AS listingDescription, " +
			"blo.total_business_sale_price AS totalBusinessSalePrice, b.suggested_title AS suggestedTitle, b.id AS businessId, " +
			"CASE WHEN fb.business_listing_id IS NOT NULL THEN true ELSE false END AS addedToFavourites FROM business_listing b " +
			"JOIN ( SELECT business_listing_id, MAX(file_path) AS file_path FROM file_entity GROUP BY business_listing_id ) f ON f.business_listing_id = b.id " +
			"JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id " +
			"LEFT JOIN favourite_business_listing fb ON b.id = fb.business_listing_id AND fb.user_id = :userId " +
			"WHERE f.file_path IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND b.listing_type = 'COMMERCIAL' " +
			"ORDER BY b.id DESC LIMIT 300")
	List<TopBusinessListingDetails> topCommercialListingsByUser(@Param("userId") Long userId);

	@Query(nativeQuery = true, value = "SELECT count(b.id), b.id as businessListingId\n" +
			"FROM ( SELECT f.file_path AS filePath, b.id AS businessId, ROW_NUMBER() OVER (PARTITION BY b.id ORDER BY f.file_path) AS rn\n" +
			"FROM file_entity f JOIN business_listing b ON f.business_listing_id = b.id WHERE f.business_listing_id IS NOT NULL ) \n" +
			"AS f JOIN business_listing b ON f.businessId = b.id \n" +
			"INNER JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id \n" +
			"INNER JOIN Favourite_Business_Listing fbl ON b.id = fbl.business_listing_id\n" +
			"WHERE blo.total_business_sale_price IS NOT null and b.listing_type ='COMMERCIAL' and f.rn = 1 group by b.id\n")
	List<FavouriteBusinessDetailsCount> getFavouriteBusinessCountByBusinessIdForCommercialList();

	@Query(nativeQuery = true, value = "SELECT f.file_path AS filePath, b.title AS title, b.listing_description AS listingDescription, " +
			"blo.total_business_sale_price AS totalBusinessSalePrice, b.suggested_title AS suggestedTitle, b.id AS businessId FROM " +
			"( SELECT business_listing_id, MAX(file_path) AS file_path FROM file_entity GROUP BY business_listing_id ) f " +
			"JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id " +
			"WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND b.listing_type = 'FRANCHISE' " +
			"ORDER BY b.id DESC LIMIT 300")
	List<TopBusinessListingDetails> topFranchesieListings();

	@Query(nativeQuery = true, value = "SELECT f.file_path AS filePath, b.title AS title, b.listing_description AS listingDescription, blo.total_business_sale_price AS totalBusinessSalePrice, " +
			"b.suggested_title AS suggestedTitle, b.id AS businessId, CASE WHEN fbl.business_listing_id IS NULL THEN FALSE ELSE TRUE END AS addedToFavourites " +
			"FROM ( SELECT business_listing_id, MAX(file_path) AS file_path FROM file_entity GROUP BY business_listing_id ) f " +
			"JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id " +
			"LEFT JOIN favourite_business_listing fbl ON b.id = fbl.business_listing_id AND fbl.user_id = :userId " +
			"WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND b.listing_type = 'FRANCHISE' " +
			"ORDER BY b.id DESC LIMIT 300")
	List<TopBusinessListingDetails> topFranchesieListingsByUser(@Param("userId") Long userId);

	@Query(nativeQuery = true, value = "SELECT count(b.id), b.id as businessListingId\n" +
			"FROM ( SELECT f.file_path AS filePath, b.id AS businessId, ROW_NUMBER() OVER (PARTITION BY b.id ORDER BY f.file_path) AS rn\n" +
			"FROM file_entity f JOIN business_listing b ON f.business_listing_id = b.id WHERE f.business_listing_id IS NOT NULL ) \n" +
			"AS f JOIN business_listing b ON f.businessId = b.id \n" +
			"INNER JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id \n" +
			"INNER JOIN Favourite_Business_Listing fbl ON b.id = fbl.business_listing_id\n" +
			"WHERE blo.total_business_sale_price IS NOT null and b.listing_type ='FRANCHISE' and f.rn = 1 group by b.id\n")
	List<FavouriteBusinessDetailsCount> getFavouriteBusinessCountByBusinessIdForFranchesieList();

	@Query(value = "select f.file_path, b.title , b.listing_description ,blo.total_business_sale_price  , b.suggested_title , b.id ,(SELECT name FROM country WHERE id = b.business_address_country_id) AS CountryName,b.business_address_country_id as countryId from file_entity f join business_listing b "
			+ "			on f.business_listing_id = b.id "
			+ "			join Business_Listing_Out_Let blo on   b.business_listing_out_let_id  = blo.id "
			+ "			where f.business_listing_id is not null and blo.total_business_sale_price is not null and (:ids IS NULL OR b.category_id IN :ids) and b.listing_type= :lType  order by f.id desc   "
			+ "			", nativeQuery = true)
    public List<Object[]> getTopBusinessListingsByCategory(List<Long> ids , String lType);
    
    @Query(value = " FROM FileEntity where businessListing =?1  AND fileType ='IMAGE'")
	  public List<FileEntity> findByListingId(BusinessListing businessListing);
     
    @Query(value = " FROM BusinessListingFeatureInfo where businessListing =?1 ")
	    public List<BusinessListingFeatureInfo> findBusinessListingFeatureInfoByListingId(BusinessListing businessListing);
   
    @Query(value = "select id , user_name , about_user ,description  from testimonial  order by id limit 300 ", nativeQuery = true)
    public List<Object[]> getTestimonials();



	@Query(nativeQuery = true, value = "SELECT f.filePath as filePath, b.title as title, b.listing_description as listingDescription, " +
			"blo.total_business_sale_price as totalBusinessSalePrice, b.suggested_title as suggestedTitle, b.id as businessListingId " +
			"FROM ( SELECT f.file_path AS filePath, b.id AS businessId, ROW_NUMBER() OVER (PARTITION BY b.id ORDER BY f.file_path) AS rn FROM file_entity f " +
			"JOIN business_listing b ON f.business_listing_id = b.id WHERE f.business_listing_id IS NOT NULL ) AS f " +
			"JOIN business_listing b ON f.businessId = b.id INNER JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id " +
			"INNER JOIN Favourite_Business_Listing fbl ON b.id = fbl.business_listing_id " +
			"WHERE blo.total_business_sale_price IS NOT NULL AND fbl.user_id = :userId AND f.rn = 1 LIMIT 20")
	List<FavouriteBusinessDetails> getFavouriteBusiness(long userId);

	
    @Query(value = "SELECT f.file_path AS FilePath, b.title AS Title, b.listing_description AS ListingDescription, blo.total_business_sale_price AS Price, b.suggested_title AS SuggestedTitle, b.id AS Id, b.business_address_country_id as countryId, (SELECT name FROM country WHERE id = b.business_address_country_id) AS CountryName FROM file_entity f JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND (:id IS NULL OR b.category_id = :id) AND (b.listing_type = :lType OR :lType IS NULL) AND (b.title LIKE CONCAT('%', :word, '%') OR b.listing_description LIKE CONCAT('%', :word, '%')) OR :word IS NULL ORDER BY CASE WHEN :isTitleASC = true THEN lower(b.title) END ASC, CASE WHEN :isTitleDSC = true THEN lower(b.title) END DESC, CASE WHEN :isPriceASC = true THEN blo.total_business_sale_price END ASC,CASE WHEN :isPriceDSC = true THEN blo.total_business_sale_price END DESC ", nativeQuery = true)
	public Page<BusinessByFilter> getBusinessByFilter(@Param("id") Long id, @Param("lType") String lType, @Param("isTitleASC") Boolean isTitleASC, @Param("isTitleDSC") Boolean isTitleDSC, @Param("isPriceASC") Boolean isPriceASC, @Param("isPriceDSC") Boolean isPriceDSC, @Param("word") String word, Pageable pageable);

	@Query(value = "SELECT f.file_path AS FilePath, b.title AS Title, b.listing_description AS ListingDescription, blo.total_business_sale_price AS Price, b.suggested_title AS SuggestedTitle, b.id AS Id, b.business_address_country_id as countryId,(SELECT name FROM country WHERE id = b.business_address_country_id) AS CountryName FROM file_entity f JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND (b.category_id = :id OR :id IS NULL) AND (b.listing_type = :lType OR :lType IS NULL) AND (b.franchise_for = :fType OR :fType IS NULL) AND (b.title LIKE CONCAT('%', :word, '%') OR b.listing_description LIKE CONCAT('%', :word, '%')) OR :word IS NULL ORDER BY CASE WHEN :isTitleASC = true THEN lower(b.title) END ASC, CASE WHEN :isTitleDSC = true THEN lower(b.title) END DESC, CASE WHEN :isPriceASC = true THEN blo.total_business_sale_price END ASC,CASE WHEN :isPriceDSC = true THEN blo.total_business_sale_price END DESC ", nativeQuery = true)
	public Page<BusinessByFilter> getBusinessWithFranchiseFilter(@Param("id") Long id, @Param("lType") String lType, @Param("fType") String fType, @Param("isTitleASC") Boolean isTitleASC, @Param("isTitleDSC") Boolean isTitleDSC, @Param("isPriceASC") Boolean isPriceASC, @Param("isPriceDSC") Boolean isPriceDSC, @Param("word") String word, Pageable pageable);

	@Query(value = "SELECT f.file_path AS FilePath, b.title AS Title, b.listing_description AS ListingDescription, blo.total_business_sale_price AS Price, b.suggested_title AS SuggestedTitle, b.id AS Id, b.business_address_country_id as countryId,(SELECT name FROM country WHERE id = b.business_address_country_id) AS CountryName FROM file_entity f JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND (b.listing_type = :lType OR :lType IS NULL) AND (b.franchise_for = :fType OR :fType IS NULL) AND (b.title LIKE CONCAT('%', :word, '%') OR b.listing_description LIKE CONCAT('%', :word, '%')) OR :word IS NULL ORDER BY CASE WHEN :isTitleASC = true THEN lower(b.title) END ASC, CASE WHEN :isTitleDSC = true THEN lower(b.title) END DESC, CASE WHEN :isPriceASC = true THEN blo.total_business_sale_price END ASC,CASE WHEN :isPriceDSC = true THEN blo.total_business_sale_price END DESC ", nativeQuery = true)
	public Page<BusinessByFilter> getBusinessWithFranchiseFilter(@Param("lType") String lType, @Param("fType") String fType, @Param("isTitleASC") Boolean isTitleASC, @Param("isTitleDSC") Boolean isTitleDSC, @Param("isPriceASC") Boolean isPriceASC, @Param("isPriceDSC") Boolean isPriceDSC, @Param("word") String word, Pageable pageable);


	@Query(value = "SELECT f.file_path AS FilePath, b.title AS Title, b.listing_description AS ListingDescription, blo.total_business_sale_price AS Price, b.suggested_title AS SuggestedTitle, b.id AS Id, b.business_address_country_id as countryId,(SELECT name FROM country WHERE id = b.business_address_country_id) AS CountryName FROM file_entity f JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND (b.listing_type = :lType OR :lType IS NULL) AND (b.title LIKE CONCAT('%', :word, '%') OR b.listing_description LIKE CONCAT('%', :word, '%')) OR :word IS NULL ORDER BY CASE WHEN :isTitleASC = true THEN lower(b.title) END ASC, CASE WHEN :isTitleDSC = true THEN lower(b.title) END DESC, CASE WHEN :isPriceASC = true THEN blo.total_business_sale_price END ASC,CASE WHEN :isPriceDSC = true THEN blo.total_business_sale_price END DESC ", nativeQuery = true)
	public Page<BusinessByFilter> getBusinessByFilter(@Param("lType") String lType, @Param("isTitleASC") Boolean isTitleASC, @Param("isTitleDSC") Boolean isTitleDSC, @Param("isPriceASC") Boolean isPriceASC, @Param("isPriceDSC") Boolean isPriceDSC, @Param("word") String word, Pageable pageable);


	@Modifying
	@Transactional
	@Query(value = "INSERT INTO testimonial (email, description, user_name, about_user, created_on, last_update) " +
			"VALUES (:email, :description, :username, :aboutUser, '2021-10-10 13:56:37.042', '2021-10-10 13:56:37.042')", nativeQuery = true)
	void addTestimonial(String email, String description, String username, String aboutUser);

	@Query(value = "SELECT f.file_path AS FilePath, b.title AS Title, b.listing_description AS ListingDescription, blo.total_business_sale_price AS Price, b.suggested_title AS SuggestedTitle, b.id AS Id, b.business_address_country_id as countryId, (SELECT name FROM country WHERE id = b.business_address_country_id) AS CountryName FROM file_entity f JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND (:id IS NULL OR b.category_id = :id) AND (b.listing_type = :lType OR :lType IS NULL) AND (b.title LIKE CONCAT('%', :word, '%') OR b.listing_description LIKE CONCAT('%', :word, '%')) OR :word IS NULL ORDER BY CASE WHEN :isTitleASC = true THEN lower(b.title) END ASC, CASE WHEN :isTitleDSC = true THEN lower(b.title) END DESC, CASE WHEN :isPriceASC = true THEN blo.total_business_sale_price END ASC,CASE WHEN :isPriceDSC = true THEN blo.total_business_sale_price END DESC ", nativeQuery = true)
	public List<BusinessByFilter> getBusinessByFilter(@Param("id") Long id, @Param("lType") String lType, @Param("isTitleASC") Boolean isTitleASC, @Param("isTitleDSC") Boolean isTitleDSC, @Param("isPriceASC") Boolean isPriceASC, @Param("isPriceDSC") Boolean isPriceDSC, @Param("word") String word);

	@Query(value = "SELECT f.file_path AS FilePath, b.title AS Title, b.listing_description AS ListingDescription, blo.total_business_sale_price AS Price, b.suggested_title AS SuggestedTitle, b.id AS Id, b.business_address_country_id as countryId, (SELECT name FROM country WHERE id = b.business_address_country_id) AS CountryName, (CASE WHEN fb.business_listing_id IS NOT NULL THEN true ELSE false END) as addedToFavourites FROM (SELECT business_listing_id, MAX(file_path) AS file_path FROM file_entity GROUP BY business_listing_id ) f JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id LEFT JOIN favourite_business_listing fb ON b.id = fb.business_listing_id AND fb.user_id = :userId WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND (:id IS NULL OR b.category_id = :id) AND (b.listing_type = :lType OR :lType IS NULL) AND (b.title LIKE CONCAT('%', :word, '%') OR b.listing_description LIKE CONCAT('%', :word, '%')) OR :word IS NULL ORDER BY CASE WHEN :isTitleASC = true THEN lower(b.title) END ASC, CASE WHEN :isTitleDSC = true THEN lower(b.title) END DESC, CASE WHEN :isPriceASC = true THEN blo.total_business_sale_price END ASC,CASE WHEN :isPriceDSC = true THEN blo.total_business_sale_price END DESC ", nativeQuery = true)
	public List<BusinessByFilter> getBusinessByUserFilter(@Param("id") Long id, @Param("lType") String lType, @Param("isTitleASC") Boolean isTitleASC, @Param("isTitleDSC") Boolean isTitleDSC, @Param("isPriceASC") Boolean isPriceASC, @Param("isPriceDSC") Boolean isPriceDSC, @Param("word") String word, @Param("userId") Long userId);

	@Query(value = "SELECT f.file_path AS FilePath, b.title AS Title, b.listing_description AS ListingDescription, blo.total_business_sale_price AS Price, b.suggested_title AS SuggestedTitle, b.id AS Id, b.business_address_country_id as countryId,(SELECT name FROM country WHERE id = b.business_address_country_id) AS CountryName FROM file_entity f JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND (b.category_id = :id OR :id IS NULL) AND (b.listing_type = :lType OR :lType IS NULL) AND (b.franchise_for = :fType OR :fType IS NULL) AND (b.title LIKE CONCAT('%', :word, '%') OR b.listing_description LIKE CONCAT('%', :word, '%')) OR :word IS NULL ORDER BY CASE WHEN :isTitleASC = true THEN lower(b.title) END ASC, CASE WHEN :isTitleDSC = true THEN lower(b.title) END DESC, CASE WHEN :isPriceASC = true THEN blo.total_business_sale_price END ASC,CASE WHEN :isPriceDSC = true THEN blo.total_business_sale_price END DESC ", nativeQuery = true)
	public List<BusinessByFilter> getBusinessWithFranchiseFilter(@Param("id") Long id, @Param("lType") String lType, @Param("fType") String fType, @Param("isTitleASC") Boolean isTitleASC, @Param("isTitleDSC") Boolean isTitleDSC, @Param("isPriceASC") Boolean isPriceASC, @Param("isPriceDSC") Boolean isPriceDSC, @Param("word") String word);

	@Query(value = "SELECT f.file_path AS FilePath, b.title AS Title, b.listing_description AS ListingDescription, blo.total_business_sale_price AS Price, b.suggested_title AS SuggestedTitle, b.id AS Id, b.business_address_country_id as countryId,(SELECT name FROM country WHERE id = b.business_address_country_id) AS CountryName, (CASE WHEN fb.business_listing_id IS NOT NULL THEN true ELSE false END) as addedToFavourites FROM (SELECT business_listing_id, MAX(file_path) AS file_path FROM file_entity GROUP BY business_listing_id ) f JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id LEFT JOIN favourite_business_listing fb ON b.id = fb.business_listing_id AND fb.user_id = :userId WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND (b.category_id = :id OR :id IS NULL) AND (b.listing_type = :lType OR :lType IS NULL) AND (b.franchise_for = :fType OR :fType IS NULL) AND (b.title LIKE CONCAT('%', :word, '%') OR b.listing_description LIKE CONCAT('%', :word, '%')) OR :word IS NULL ORDER BY CASE WHEN :isTitleASC = true THEN lower(b.title) END ASC, CASE WHEN :isTitleDSC = true THEN lower(b.title) END DESC, CASE WHEN :isPriceASC = true THEN blo.total_business_sale_price END ASC,CASE WHEN :isPriceDSC = true THEN blo.total_business_sale_price END DESC ", nativeQuery = true)
	public List<BusinessByFilter> getBusinessWithFranchiseUserFilter(@Param("id") Long id, @Param("lType") String lType, @Param("fType") String fType, @Param("isTitleASC") Boolean isTitleASC, @Param("isTitleDSC") Boolean isTitleDSC, @Param("isPriceASC") Boolean isPriceASC, @Param("isPriceDSC") Boolean isPriceDSC, @Param("word") String word, @Param("userId") Long userId);

	@Query(value = "SELECT f.file_path AS FilePath, b.title AS Title, b.listing_description AS ListingDescription, blo.total_business_sale_price AS Price, b.suggested_title AS SuggestedTitle, b.id AS Id, b.business_address_country_id as countryId,(SELECT name FROM country WHERE id = b.business_address_country_id) AS CountryName FROM file_entity f JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND (b.listing_type = :lType OR :lType IS NULL) AND (b.franchise_for = :fType OR :fType IS NULL) AND (b.title LIKE CONCAT('%', :word, '%') OR b.listing_description LIKE CONCAT('%', :word, '%')) OR :word IS NULL ORDER BY CASE WHEN :isTitleASC = true THEN lower(b.title) END ASC, CASE WHEN :isTitleDSC = true THEN lower(b.title) END DESC, CASE WHEN :isPriceASC = true THEN blo.total_business_sale_price END ASC,CASE WHEN :isPriceDSC = true THEN blo.total_business_sale_price END DESC ", nativeQuery = true)
	public List<BusinessByFilter> getBusinessWithFranchiseFilter(@Param("lType") String lType, @Param("fType") String fType, @Param("isTitleASC") Boolean isTitleASC, @Param("isTitleDSC") Boolean isTitleDSC, @Param("isPriceASC") Boolean isPriceASC, @Param("isPriceDSC") Boolean isPriceDSC, @Param("word") String word);

	@Query(value = "SELECT f.file_path AS FilePath, b.title AS Title, b.listing_description AS ListingDescription, blo.total_business_sale_price AS Price, b.suggested_title AS SuggestedTitle, b.id AS Id, b.business_address_country_id as countryId,(SELECT name FROM country WHERE id = b.business_address_country_id) AS CountryName, (CASE WHEN fb.business_listing_id IS NOT NULL THEN true ELSE false END) as addedToFavourites  FROM (SELECT business_listing_id, MAX(file_path) AS file_path FROM file_entity GROUP BY business_listing_id ) f JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id LEFT JOIN favourite_business_listing fb ON b.id = fb.business_listing_id AND fb.user_id = :userId WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND (b.listing_type = :lType OR :lType IS NULL) AND (b.franchise_for = :fType OR :fType IS NULL) AND (b.title LIKE CONCAT('%', :word, '%') OR b.listing_description LIKE CONCAT('%', :word, '%')) OR :word IS NULL ORDER BY CASE WHEN :isTitleASC = true THEN lower(b.title) END ASC, CASE WHEN :isTitleDSC = true THEN lower(b.title) END DESC, CASE WHEN :isPriceASC = true THEN blo.total_business_sale_price END ASC,CASE WHEN :isPriceDSC = true THEN blo.total_business_sale_price END DESC ", nativeQuery = true)
	public List<BusinessByFilter> getBusinessWithFranchiseUserFilter(@Param("lType") String lType, @Param("fType") String fType, @Param("isTitleASC") Boolean isTitleASC, @Param("isTitleDSC") Boolean isTitleDSC, @Param("isPriceASC") Boolean isPriceASC, @Param("isPriceDSC") Boolean isPriceDSC, @Param("word") String word, @Param("userId") Long userId);

	@Query(value = "SELECT f.file_path AS FilePath, b.title AS Title, b.listing_description AS ListingDescription, blo.total_business_sale_price AS Price, b.suggested_title AS SuggestedTitle, b.id AS Id, b.business_address_country_id as countryId,(SELECT name FROM country WHERE id = b.business_address_country_id) AS CountryName FROM file_entity f JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND (b.listing_type = :lType OR :lType IS NULL) AND (b.title LIKE CONCAT('%', :word, '%') OR b.listing_description LIKE CONCAT('%', :word, '%')) OR :word IS NULL ORDER BY CASE WHEN :isTitleASC = true THEN lower(b.title) END ASC, CASE WHEN :isTitleDSC = true THEN lower(b.title) END DESC, CASE WHEN :isPriceASC = true THEN blo.total_business_sale_price END ASC,CASE WHEN :isPriceDSC = true THEN blo.total_business_sale_price END DESC ", nativeQuery = true)
	public List<BusinessByFilter> getBusinessByFilter(@Param("lType") String lType, @Param("isTitleASC") Boolean isTitleASC, @Param("isTitleDSC") Boolean isTitleDSC, @Param("isPriceASC") Boolean isPriceASC, @Param("isPriceDSC") Boolean isPriceDSC, @Param("word") String word);

	@Query(value = "SELECT f.file_path AS FilePath, b.title AS Title, b.listing_description AS ListingDescription, blo.total_business_sale_price AS Price, b.suggested_title AS SuggestedTitle, b.id AS Id, b.business_address_country_id as countryId,(SELECT name FROM country WHERE id = b.business_address_country_id) AS CountryName, (CASE WHEN fb.business_listing_id IS NOT NULL THEN true ELSE false END) as addedToFavourites FROM (SELECT business_listing_id, MAX(file_path) AS file_path FROM file_entity GROUP BY business_listing_id ) f JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id LEFT JOIN favourite_business_listing fb ON b.id = fb.business_listing_id AND fb.user_id = :userId  WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND (b.listing_type = :lType OR :lType IS NULL) AND (b.title LIKE CONCAT('%', :word, '%') OR b.listing_description LIKE CONCAT('%', :word, '%')) OR :word IS NULL ORDER BY CASE WHEN :isTitleASC = true THEN lower(b.title) END ASC, CASE WHEN :isTitleDSC = true THEN lower(b.title) END DESC, CASE WHEN :isPriceASC = true THEN blo.total_business_sale_price END ASC,CASE WHEN :isPriceDSC = true THEN blo.total_business_sale_price END DESC ", nativeQuery = true)
	public List<BusinessByFilter> getBusinessByUserFilter(@Param("lType") String lType, @Param("isTitleASC") Boolean isTitleASC, @Param("isTitleDSC") Boolean isTitleDSC, @Param("isPriceASC") Boolean isPriceASC, @Param("isPriceDSC") Boolean isPriceDSC, @Param("word") String word, @Param("userId") Long userId);

	@Query(value = "SELECT count(b.id), b.id as businessListingId FROM (SELECT business_listing_id, MAX(file_path) AS file_path FROM file_entity GROUP BY business_listing_id ) f JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id LEFT JOIN favourite_business_listing fb ON b.id = fb.business_listing_id WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND (b.listing_type = :lType OR :lType IS NULL) AND (b.title LIKE CONCAT('%', :word, '%') OR b.listing_description LIKE CONCAT('%', :word, '%')) OR :word IS NULL group by b.id ", nativeQuery = true)
	List<FavouriteBusinessDetailsCount> getFavouriteBusinessCountByBusinessType(@Param("lType") String lType, @Param("word") String word);
	
	
	@Query(value = "SELECT f FROM FileEntity f where f.businessListing.id=?1")
	List<FileEntity> findByBusinessListingId(Long listingId);
	
}
