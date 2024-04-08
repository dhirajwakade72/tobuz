package com.tobuz.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tobuz.constant.Constants;
import com.tobuz.controller.CommanRestController;
import com.tobuz.dto.ResponseDTO;
import com.tobuz.dto.UserMenuDTO;
import com.tobuz.model.AppUser;
import com.tobuz.model.OtpDetails;
import com.tobuz.model.Role;
import com.tobuz.object.RegisterDTO;
import com.tobuz.repository.OtpDetailsRepository;
import com.tobuz.repository.RoleRepository;
import com.tobuz.repository.UserRepository;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(CommanRestController.class);
	 
	 public static final String CLASS_NAME="UserService";
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OtpDetailsRepository otpDetailsRepository;
	
	
	@Autowired
	private RoleRepository roleRepository;
	
	public AppUser findUserById(Long userId)
	{
		Optional<AppUser>userOpt=userRepository.findById(userId);
		return userOpt.isPresent()?userOpt.get():null;
	}
	
	public AppUser saveUser(AppUser user) {
		user.setCreatedOn(new Timestamp(System.currentTimeMillis()));
		user.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		return userRepository.save(user);
	}

	public ResponseDTO<AppUser> registerUser(RegisterDTO registerDTO) {

		ResponseDTO<AppUser> response = new ResponseDTO<AppUser>();
		try {
			
			AppUser users=userRepository.findUserInfoByEmail(registerDTO.getEmail());
			
			if (users != null) {
				response.setMessage(Constants.MSG_USER_ALREADY_EXIST);
				response.setStatus(Constants.STATUS_FAILED);
				return response;
			}
			AppUser user = new AppUser();
			user.setName(registerDTO.getName());
			user.setEmail(registerDTO.getEmail());
			user.setMobileNumber(registerDTO.getPhoneNo());
			user.setPassword(registerDTO.getPassword());
			user.setLoginType("FACEBOOK");
			user.setUserDefaultRole(registerDTO.getRole());
			AppUser savedUser = saveUser(user);
			Role role=new Role();
			role.setAppUser(savedUser);
			role.setUserRole(registerDTO.getRole());
			role.setCreatedOn(Timestamp.from(Instant.now()));
			roleRepository.save(role);
			
			
			if (savedUser != null) {
				response.setMessage(Constants.MSG_USER_REGISTER_SUCCESS);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(user);
			} else {
				response.setMessage(Constants.MSG_USER_REGISTER_FAILED);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(user);
			}

		} catch (Exception e) {
			response.setMessage(Constants.MSG_USER_REGISTER_FAILED);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			logger.info(CLASS_NAME+":registerUser:"+e.getMessage());
		}
		return response;
	}
	
	public ResponseDTO<Boolean> checkMailId(String email) {

		ResponseDTO<Boolean> response = new ResponseDTO<Boolean>();
		try {
			
			AppUser users=userRepository.findUserInfoByEmail(email);
			if (users != null) {
				response.setMessage(Constants.MSG_USER_ALREADY_EXIST);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(true);
			}
			else
			{
				response.setMessage(Constants.MSG_USER_NOT_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(false);
			}
			return response;
			

		} catch (Exception e) {
			response.setMessage(Constants.MSG_USER_REGISTER_FAILED);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			logger.info(CLASS_NAME+":registerUser:"+e.getMessage());
			return response;
		}
		
	}
	
	
	
	public ResponseDTO<AppUser> login(RegisterDTO registerDTO) {

		ResponseDTO<AppUser> response = new ResponseDTO<AppUser>();
		try {
			AppUser users=userRepository.findUserInfoByEmail(registerDTO.getEmail());					
			
			if (users != null && registerDTO.getPassword().equals(users.getPassword()))
			{
				response.setMessage(Constants.MSG_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(users);
			}
			else if(users != null)
			{
				response.setMessage(Constants.MSG_INVALID_USERNAME_PASS);
				response.setStatus(Constants.STATUS_FAILED);
			}
			else {
				response.setMessage(Constants.MSG_USER_NOT_FOUND);
				response.setStatus(Constants.STATUS_FAILED);
			}

		} catch (Exception e) {
			response.setMessage(Constants.MSG_USER_REGISTER_FAILED);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			logger.info(CLASS_NAME+":login:"+e.getMessage());
		}
		return response;
	}
	
	
	public List<UserMenuDTO> getUserMenu(String userRole)
	{
		List<UserMenuDTO>list=new ArrayList<>();		
		if("SELLER".equals(userRole))
		{			
			list.add(new UserMenuDTO("My Dashboard","Dashboard","/user/my-dashboard","my-destboard"));			
			list.add(new UserMenuDTO("My Listings","Listings","/user/my-listings","listing"));
			list.add(new UserMenuDTO("My Package","Package","/user/my-package","My-Packages"));			
			//list.add(new UserMenuDTO("Favorites","Favorites","/my-favorites","Favorites"));			
			//list.add(new UserMenuDTO("Saved Search","saved-search","/saved-search","Saved-Search"));			
			//list.add(new UserMenuDTO("Messages","Messages","/messages","Messages"));			
			//list.add(new UserMenuDTO("Update-Profile","Update Profile","/update-profile","Update-Profile"));
			
		}
		else if("BUSINESS_SERVICE".equals(userRole))
		{
			list.add(new UserMenuDTO("My Dashboard","Dashboard","/user/my-dashboard","my-destboard"));			
			list.add(new UserMenuDTO("My Listings","Listings","/user/my-listings","listing"));
			list.add(new UserMenuDTO("My Package","Package","/user/my-package","My-Packages"));			
		}
		else if("BUYER".equals(userRole))
		{
			list.add(new UserMenuDTO("My Dashboard","Dashboard","/user/my-dashboard","my-destboard"));			
			list.add(new UserMenuDTO("My Package","Package","/user/my-package","My-Packages"));
			list.add(new UserMenuDTO("My Adverts","Adverts","/user/my-adverts","My-Adverts"));
			//list.add(new UserMenuDTO("Favorites","Favorites","/my-favorites","Favorites"));			
			//list.add(new UserMenuDTO("Saved Search","saved-search","/saved-search","Saved-Search"));			
			//list.add(new UserMenuDTO("Messages","Messages","/messages","Messages"));			
			//list.add(new UserMenuDTO("Update-Profile","Update Profile","/update-profile","Update-Profile"));
		}
		else if("FRANCHISOR".equals(userRole))
		{
			list.add(new UserMenuDTO("My Dashboard","Dashboard","/dashboard","my-destboard"));			
			list.add(new UserMenuDTO("My Package","Package","/package","My-Packages"));
			//list.add(new UserMenuDTO("My Adverts","Adverts","/adverts","My-Adverts"));
			//list.add(new UserMenuDTO("Favorites","Favorites","/favorites","Favorites"));			
			//list.add(new UserMenuDTO("Saved Search","saved-search","/saved-search","Saved-Search"));			
			//list.add(new UserMenuDTO("Messages","Messages","/messages","Messages"));			
			//list.add(new UserMenuDTO("Update-Profile","Update Profile","/update-profile","Update-Profile"));
		}
		else if("ADMIN".equals(userRole) || "TOBUZ_ADMIN".equals(userRole))
		{
			list.add(new UserMenuDTO("My Dashboard","Dashboard","/dashboard","my-destboard"));
			//list.add(new UserMenuDTO("Payments","Payments","/payments",""));
			list.add(new UserMenuDTO("Listings","Listings","/user/my-listings","listing"));
			list.add(new UserMenuDTO("Adverts","Adverts","/adverts","My-Adverts"));
			list.add(new UserMenuDTO("Packages & Features"," packages-features","/packages-and-features","My-Packages"));
			//list.add(new UserMenuDTO("Categories","Categories","/categories",""));
			//list.add(new UserMenuDTO("Users","users","/Users",""));
			//list.add(new UserMenuDTO("Roles","roles","/roles",""));
			//list.add(new UserMenuDTO("Data","data","/data",""));
			//list.add(new UserMenuDTO("User Requests","UserRequests","/user-requests",""));
			//list.add(new UserMenuDTO("Contact US Details","Contact-US","/contact-us",""));
		}
		
		return list;
	}
	
	/*@Transactional
	public ResponseDTO<ResponseDTO<Boolean>> addListingLikeDisLike(LikeDislikeDTO likeDislikeDTO) 
	{
		logger.info(CLASS_NAME + ":addListingLikeDisLike: Started");
		ResponseDTO<ResponseDTO<Boolean>> response = new ResponseDTO<ResponseDTO<Boolean>>();
				
		try {

			List<UserIntraction> userIntraction = userIntractionRepository
					.getUserIntraction(likeDislikeDTO.getListingId(), likeDislikeDTO.getListingType());
			Boolean isFound = false;
			if (userIntraction == null || userIntraction.size() == 0) {
				UserIntraction exitsing = new UserIntraction();
				exitsing.setListingId(likeDislikeDTO.getListingId());
				exitsing.setCreatedOn(Timestamp.from(Instant.now()));
				exitsing.setListingType(likeDislikeDTO.getListingType());
				if ("LIKE".equals(likeDislikeDTO.getLikeOrDisLike())) {
					exitsing.setLikeCount(1L);
					exitsing.setDisLikeCount(0L);					
				} else {
					exitsing.setLikeCount(0L);
					exitsing.setDisLikeCount(1L);
				}
				userIntractionRepository.save(exitsing);
				isFound = true;
			}
			else {

				if ("LIKE".equals(likeDislikeDTO.getLikeOrDisLike()) && !isFound) {
					userIntractionRepository.updateIncreaseLikeCount(likeDislikeDTO.getListingId(),
							likeDislikeDTO.getListingType());
				} else if ("DISLIKE".equals(likeDislikeDTO.getLikeOrDisLike()) && !isFound) {
					userIntractionRepository.updateIncreaseDisLikeCount(likeDislikeDTO.getListingId(),
							likeDislikeDTO.getListingType());
				}
			}

			response.setMessage(Constants.MSG_DATA_SUCCESS_LIKE_OR_DISLIKE + likeDislikeDTO.getLikeOrDisLike());
			response.setStatus(Constants.STATUS_SUCCESS);
			response.setResult(null);

		} catch (Exception e) {
			response.setMessage(Constants.MSG_USER_REGISTER_FAILED);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			logger.info(CLASS_NAME + ":login:" + e.getMessage());
		}
		return response;
	}
	
	public Long getLikeCount(Long id,String type)
	{
		List<UserIntraction> userIntraction = userIntractionRepository
				.getUserIntraction(id,type);
		if (userIntraction != null && userIntraction.size() > 0) {
			return userIntraction.get(0).getLikeCount();
		}
		return 0L;
		
	}*/
	
	public OtpDetails saveOptDetails(OtpDetails otp)
	{
		return otpDetailsRepository.save(otp);
	}
	public static String generateOTP() {
		return String.valueOf(ThreadLocalRandom.current().nextInt(100000, 999999 + 1));
	}
	
	public AppUser updatePassword(AppUser user,String pass)
	{
		try
		{
		user.setPassword(pass);
		return userRepository.save(user);
		}
		catch (Exception e) {
		 return null;
		}
	}

}
