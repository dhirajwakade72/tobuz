package com.tobuz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tobuz.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{
	
	
	
	 @Query(value = " "
	    		+  " "
	    		+ "  "
	    		+ " select id , from_user_name , from_user_email ,from_user_dialing_code , from_user_mobile_number , created_on  from message order by id desc  "
	    		+ " "
	    		+ " ", nativeQuery = true)
	    public List<Object[]> getAdminMessages();


		 @Query(value = " "
		    		+  " "
		    		+ "  "
		    		+ " select id , from_user_name , from_user_email ,from_user_dialing_code , from_user_mobile_number , created_on , subject ,  "
		    		+ " bodytext from message  where  to_app_user_id =:id  order by id desc "
		    		+ " "
		    		+ " ", nativeQuery = true)
		    public List<Object[]> getUserMessages(long id);

		    
}
