package com.tobuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tobuz.model.BusinessServiceType;
@Repository
public interface BusinessServiceTypeRepository extends JpaRepository<BusinessServiceType, Long> {

}
