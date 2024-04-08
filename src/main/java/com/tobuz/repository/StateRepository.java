package com.tobuz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tobuz.model.State;
@Repository
public interface StateRepository extends JpaRepository<State, Long> {

	
	@Query("select s from State s where s.country.id=?1")
	List<State> findByCountry(Long countryId);
	
}
