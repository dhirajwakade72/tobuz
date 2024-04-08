package com.tobuz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tobuz.model.ContactUs;

public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {
	
	
	
	 @Query(value = " "
	    		+  " "
	    		+ "  "
	    		+ " select name , email , phone , city , message from contact_us order by id desc  "
	    		+ " "
	    		+ " ", nativeQuery = true)
	    public List<Object[]> getAdminContactus();

	    
	    @Query(value = "SELECT c FROM ContactUs c WHERE c.email=?1")
	    List<ContactUs> findByEmail(String email);
	    

}
