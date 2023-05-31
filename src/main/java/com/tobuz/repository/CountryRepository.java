package com.tobuz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tobuz.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{
	  @Query(value = " from Country c where c.isoCode =?1")
	   public  Country findCountryByIsoCode(String isoCode);
	  
	  @Query(value = " from Country c where c.dialingCode =?1")
	   public  Country findCountryByDialingCode(String dialingCode);
	  
	   

}
