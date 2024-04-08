package com.tobuz.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tobuz.model.BusinessAdvert;
@Repository
public interface BusinessAdvertRepository extends JpaRepository<BusinessAdvert, Long> {

	
	@Modifying
	@Query(value = "INSERT INTO favourite_business_listing (is_active, user_id, role_id, business_advert_id, added_on, created_on, last_update) VALUES (true, :userId, :roleId, :advertId, :currentTimeStamp, :currentTimeStamp, :currentTimeStamp )", nativeQuery = true)
	void addFavouriteBusiness(@Param("userId") Long userId, @Param("roleId") Integer roleId, @Param("advertId") Long advertId, @Param("currentTimeStamp") Timestamp currentTimeStamp);

	@Modifying
	@Query(value = "DELETE FROM favourite_business_listing WHERE user_id = :userId AND business_advert_id = :advertId", nativeQuery = true)
	void removeFavouriteBusiness(@Param("userId") Long userId, @Param("advertId") Long advertId);
	
}
