package com.tobuz.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tobuz.constant.Constants;
import com.tobuz.dto.ChangePasswordDTO;
import com.tobuz.dto.ResponseDTO;
import com.tobuz.dto.UserMenuDTO;
import com.tobuz.model.AppUser;
import com.tobuz.model.OtpDetails;
import com.tobuz.object.RegisterDTO;
import com.tobuz.service.BusinessService;
import com.tobuz.service.EmailService;
import com.tobuz.service.UserService;
import com.tobuz.utils.EncryptionUtility;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private EmailService mailService;
	
	@Value("${application.endURL}")
	private String endURL;
	
	@RequestMapping(value = "/register", produces = { "application/json" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> register(@RequestBody RegisterDTO register) {
		return new ResponseEntity<ResponseDTO<?>>(userService.registerUser(register),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/check_mail_exist/{email}", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> check_mail_exist(@PathVariable String email) {
		return new ResponseEntity<ResponseDTO<?>>(userService.checkMailId(email),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login", produces = { "application/json" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> login(@RequestBody RegisterDTO register) {
		return new ResponseEntity<ResponseDTO<?>>(userService.login(register),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add/listing", produces = { "application/json" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> addBusinessListing(@RequestBody RegisterDTO register) {
		return new ResponseEntity<ResponseDTO<?>>(userService.registerUser(register),HttpStatus.OK);
	} 	
	
	
	@GetMapping(value = "/menu/{userrole}")	
	public ResponseEntity<ResponseDTO<?>> getUserMenu(@PathVariable String userrole) {
		ResponseDTO<List<UserMenuDTO>> response= new ResponseDTO<List<UserMenuDTO>>();
		response.setMessage(Constants.MSG_DATA_FOUND);
		response.setStatus(Constants.STATUS_SUCCESS);
		response.setResult(userService.getUserMenu(userrole));
		return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> forgotPassword(@RequestBody ChangePasswordDTO dto) {
		ResponseDTO<List<String>> response= new ResponseDTO<List<String>>();
		try
		{
		AppUser user = businessService.findByEmail(dto.getEmail());
		if (user == null) {
			response.setMessage(Constants.MSG_USER_NOT_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.BAD_REQUEST);
		}
		
		if (user.getPassword().equals(dto.getPassword())) {
			response.setMessage(Constants.MSG_BOTH_PASSWORD_SAME);
			response.setStatus(Constants.STATUS_FAILED);
			return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.OK);
		}
		
		AppUser userUopdated=userService.updatePassword(user,dto.getPassword());
		if (userUopdated == null) {
			response.setMessage(Constants.MSG_FAILED_ACTION);
			response.setStatus(Constants.STATUS_FAILED);
			return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
			response.setMessage(Constants.MSG_PASSWORD_UPDATED);
			response.setStatus(Constants.STATUS_SUCCESS);
			return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.OK);
		
		}
		catch (Exception e) {
			response.setMessage(Constants.MSG_FAILED_ACTION);
			response.setStatus(Constants.STATUS_FAILED);
			return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> resetPass(@RequestBody String email) {
		ResponseDTO<List<String>> response= new ResponseDTO<List<String>>();
		try
		{
		AppUser user = businessService.findByEmail(email);
		if (user == null) {
			response.setMessage(Constants.MSG_USER_NOT_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.BAD_REQUEST);
		}
		LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expiryTime = currentTime.plusMinutes(5);
        
        // Format datetime to string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedExpiryTime = expiryTime.format(formatter);
                
        
        // Concatenate email and expiry time
        String dataToEncrypt = EncryptionUtility.encryptDate(formattedExpiryTime);
        
        // Encrypt the data
        String emailEnc = EncryptionUtility.encrypt(email);
        
        System.out.println("PASS="+dataToEncrypt +"=>"+EncryptionUtility.decryptDate(dataToEncrypt));
        
        // Create reset link with encrypted string
        String resetLink = endURL + "/reset-password/" + emailEnc+"**"+dataToEncrypt;

		String emailContent = "Dear " + user.getName() + ",\n\n"
				+ "You are receiving this email because you requested to reset your password on Tobuz.\n"
				+ "To reset your password, please click the following link, or copy and paste it into your web browser:\n\n"
				+ resetLink + "\n\n"
				+ "If you do not wish to reset your password, please delete this email.\n\n"
				+ "Please contact us if you need any assistance.\n\n"
				+ "Tobuz brings Buyers, Sellers, Franchisers, Business and Commercial Property brokers together, "
				+ "to help them buy, sell and lease Businesses and Commercial Properties.\n\n"
				+ "Kind Regards,\n"
				+ "Tobuz Team";

			mailService.sendResetEmail(email,emailContent);
			response.setMessage(Constants.MSG_FORGOT_PASSWORD);
			response.setStatus(Constants.STATUS_SUCCESS);
			return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.OK);
		
		}
		catch (Exception e) {
			response.setMessage(Constants.MSG_FAILED_ACTION);
			response.setStatus(Constants.STATUS_FAILED);
			return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
	
	@RequestMapping(value = "/sendOpt", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> sendOpt(@RequestBody String email) {
		ResponseDTO<String> response= new ResponseDTO<String>();
		try
		{			
		AppUser user = businessService.findByEmail(email);
		if (user == null) {
			response.setMessage(Constants.MSG_USER_NOT_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.OK);
		}
		
		OtpDetails optDe=userService.saveOptDetails(new OtpDetails(email,UserService.generateOTP(),"FORGOT_PASSWORD"));
		String emailContent = "Opt : "+optDe.getOtp();

			mailService.sendResetEmail(email,emailContent);
			response.setMessage(Constants.MSG_OPT_SEND);
			response.setStatus(Constants.STATUS_SUCCESS);
			response.setResult(optDe.getOtp());
			return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.OK);
		
		}
		catch (Exception e) {
			response.setMessage(Constants.MSG_FAILED_ACTION);
			response.setStatus(Constants.STATUS_FAILED);
			return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	

}
