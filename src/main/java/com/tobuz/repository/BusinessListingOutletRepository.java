package com.tobuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tobuz.model.BusinessListingOutLet;
@Repository
public interface BusinessListingOutletRepository extends JpaRepository<BusinessListingOutLet, Long> {

}
