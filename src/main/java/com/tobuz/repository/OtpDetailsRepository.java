package com.tobuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tobuz.model.OtpDetails;
@Repository
public interface OtpDetailsRepository extends JpaRepository<OtpDetails, String> {

}
