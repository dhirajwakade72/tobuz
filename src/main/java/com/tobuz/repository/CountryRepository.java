package com.tobuz.repository;

import java.util.List;

import com.tobuz.projection.CategoryByFilter;
import com.tobuz.projection.CountryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tobuz.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{
	  @Query(value = " from Country c where c.isoCode =?1")
	   public  Country findCountryByIsoCode(String isoCode);
	  
	  @Query(value = " from Country c where c.dialingCode =?1")
	   public  Country findCountryByDialingCode(String dialingCode);

	@Query(value = "SELECT id, name,short_name as shortName, dialing_code as dialingCode, iso_code as isoCode, currency_code AS currencyCode FROM country WHERE is_active is true ORDER BY name", nativeQuery = true)
	public List<CountryList> getAllCountryList();

}
