package com.tobuz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tobuz.model.UserContactInfo;
@Repository
public interface UserContactInfoRepository extends JpaRepository<UserContactInfo, Long> {

    @Query(value = "SELECT c FROM UserContactInfo c WHERE c.email=?1 and c.businessListingId=?2")
	List<UserContactInfo> findByEmailAndBusinessId(String email,Long businessId);
}
