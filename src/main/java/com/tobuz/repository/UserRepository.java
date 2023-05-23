package com.tobuz.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tobuz.model.AppUser;

public interface UserRepository  extends JpaRepository<AppUser, Long> {
	
	@Modifying
	 @Query(value = "insert into app_user (is_active , name , email , mobile_number , password,created_on ,last_update ,user_default_role )  values (:active , :name, :email, :mobile, :password,  :createdOn,:lastUpdate , :role)" , nativeQuery = true)
	 public  void insertUser(@Param("active")  boolean active , @Param("name") String name,  @Param("email")  String email,  @Param("mobile") String mobile,
			 @Param("password") String password,  @Param("createdOn")  Date createdOn, @Param("lastUpdate") Date lastUpdate , @Param("role") String role );
	 
	
	  @Query(value = "select id , name , email , user_default_role from APP_USER where email = :email and password = :password", nativeQuery = true)
	    public List<Object[]> getLoginInfo( @Param("email")  String email , @Param("password")  String password );
	    
	    @Query(value = " from AppUser u where u.email =?1  and u.password =?2" )
	    List<AppUser> findLoginInfo(String email,String password);
	    
	    
	    @Query(value = "select count (*) from  app_user where is_active = true ", nativeQuery = true)
	    public int getNumberOfActiveUsers () ;
	    
	    @Query(value = "select count (*) from business_listing ", nativeQuery = true)
	    public int getTotalActiveListings () ;
	    
	    @Query(value = "select count(*)  from business_listing  where sold_on is not null ", nativeQuery = true)
	    public int getSoldBusiness () ;
	    
	    @Query(value = "select count (*) from  app_user  ", nativeQuery = true)
	    public int getNumberOfUsers () ;
	    
	    
	    
	    @Query(value = " "
	    		+  " "
	    		+ " "
	       		+ " select au.id , au.name , au.email,au.country_code, au.user_default_role  from app_user au  where is_active =true   "
	    		+ " "
	    		+ " "
	    		+ " ", nativeQuery = true)
	    public List<Object[]> getAdminUsers();
	    
	    @Query(value = " "
	    		+  " "
	    		+ " "
	       		+ "select name , company_name ,email,message , phone_number , business_funding ,created_on , business_status from transform_business   "
	    		+ " "
	    		+ " "
	    		+ " ", nativeQuery = true)
	    public List<Object[]> getAdminUserRequests();
	    
	    
	    @Query(value = " "
	    		+  " "
	    		+ " "
	       		+ " select au.name ,au.user_default_role , pm.transaction_description , pm.paid_amount , pm.created_on, pm.transaction_status ,  au.country_code from payment pm inner join  app_user au  "
	       		+ " on pm.app_user_id = au.id  order by pm.id desc "
	    		+ " "
	    		+ " ", nativeQuery = true)
	    public List<Object[]> getAllAdminPayments();
	    
	    
	    
	    
}
