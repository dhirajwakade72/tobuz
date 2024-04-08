package com.tobuz.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tobuz.constant.Constants;
import com.tobuz.dto.AddBusinessListingDTO;
import com.tobuz.dto.ResponseDTO;
import com.tobuz.model.AppUser;
import com.tobuz.model.BusinessListing;
import com.tobuz.model.ContactUs;
import com.tobuz.model.Country;
import com.tobuz.model.NewsLetterSubscription;
import com.tobuz.model.UserContactInfo;
import com.tobuz.object.BrokerListingDTO;
import com.tobuz.object.BusinessAdvertDTO;
import com.tobuz.object.BusinessFavouriteDTO;
import com.tobuz.object.BusinessFeatureDTO;
import com.tobuz.object.BusinessListingDTO;
import com.tobuz.object.BusinessListingFeatureInfoDTO;
import com.tobuz.object.BusinessListingQueryDTO;
import com.tobuz.object.BusinessServiceTypeDTO;
import com.tobuz.object.CategoryDTO;
import com.tobuz.object.ContactDTO;
import com.tobuz.object.MessageDTO;
import com.tobuz.object.PageBrokerListDto;
import com.tobuz.object.PageBusinessListingDto;
import com.tobuz.object.PageBusinessServiceTypeDTO;
import com.tobuz.object.PaymentDTO;
import com.tobuz.object.RegisterDTO;
import com.tobuz.object.TestimonialDTO;
import com.tobuz.object.TobuzPackageDTO;
import com.tobuz.object.TobuzfeatureDTO;
import com.tobuz.object.UserDTO;
import com.tobuz.object.UserPackageInfoDTO;
import com.tobuz.object.UserRequestDTO;
import com.tobuz.object.Vo;
import com.tobuz.service.BusinessService;

@RestController
@RequestMapping("/api/business")
public class BusinessRestController {

	private static final Logger logger = LoggerFactory.getLogger(CommanRestController.class);

	public static final String CLASS_NAME = "TobuzRestController";
	
	@Autowired
	private BusinessService businessService;

	@Autowired
	private JavaMailSender emailSender;

	@Value("${application.endURL}")
	private String endURL;
	

	@RequestMapping(value = "/toTenBusiness", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public List<BusinessListingDTO> toTenBusiness() {
		System.out.println("In controller");
		return businessService.getTopTenBusiness();
	}

	@RequestMapping(value = "/toTenRecentBusiness", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public List<BusinessListingDTO> toTenRecentBusiness() {
		System.out.println("In controller");
		return businessService.getTopTenRecentBusiness();
	}

	@RequestMapping(value = "/topBusinessListings", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public PageBusinessListingDto getTopBusinessListings(@RequestParam(value = "page",defaultValue = "0",required = false)int page, @RequestParam(value = "size",defaultValue = "6",required = false)int size) {
		System.out.println("In controller");
		return businessService.getTopBusinessListings(page,size);
	}

	@RequestMapping(value = "/topCommercialListings", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public List<BusinessListingDTO> topCommercialListings() {
		System.out.println("In topCommercialListings controller");
		return businessService.topCommercialListings();
	}

	@RequestMapping(value = "/topFranchesieListings", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public List<BusinessListingDTO> topFranchesieListings() {
		System.out.println("In topFranchesieListings controller");
		return businessService.topFranchisieListings();
	}

	@RequestMapping(value = "/getTestimonials", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public List<TestimonialDTO> getTestimonials() {
		System.out.println("In getTestimonials controller");
		return businessService.getTestimonials();
	}

	@RequestMapping(value = "/getFavouriteBusiness", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public List<BusinessListingDTO> getFavouriteBusiness() {
		System.out.println("In getFavouriteBusiness controller");
		return businessService.getFavouriteBusiness();
	}

	@RequestMapping(value = "/getTopBusinessListingsByCategory", produces = {
			"application/json" }, method = RequestMethod.POST)
	@ResponseBody
	public List<BusinessListingDTO> getTopBusinessListingsByCategory(@RequestBody BusinessListingDTO businessListing) {
		System.out.println("<<<<<<<<<<<<getTopBusinessListingsByCategory>>>>>>>>>>>");
		return businessService.getTopBusinessListingsByCategory(businessListing.getCategoryIds(),
				businessListing.getListingType(), businessListing.getPrefredCountryId());
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public int registerUser(@RequestBody RegisterDTO registerDTO) {
		System.out.println("In controller registerUser");
		registerDTO.setCreatedOn(new Date());
		registerDTO.setLastUpdate(new Date());
		return businessService.registerUser(registerDTO);
	}

	

	@RequestMapping(value = "/addListing", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> addListings(@RequestBody AddBusinessListingDTO businessListing) {	
		logger.info(CLASS_NAME + " : addListings () : Started ***");
		 return businessService.addBusinessListings(businessListing);
	}
	

	@RequestMapping(value = "/loginUser", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<RegisterDTO> loginUser(@RequestBody RegisterDTO registerDTO) {
		System.out.println(" *****In controller Login User ********");
		System.out.println(" EMAIL " + registerDTO.getEmail());
		System.out.println(" PASSWORD " + registerDTO.getPassword());
		registerDTO = businessService.findLoginInfo(registerDTO.getEmail(), registerDTO.getPassword());

		if (registerDTO.getName().equalsIgnoreCase("ADMIN")) {
			registerDTO.setRole("ADMIN");
			return ResponseEntity.ok(registerDTO);
		}

		return ResponseEntity.ok(registerDTO);

	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<Void> forgotPassword(@RequestParam("email") String email) {
		AppUser user = businessService.findByEmail(email);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		String resetToken = UUID.randomUUID().toString();
		HttpSession session = getSession();
		session.setAttribute("resetToken", resetToken);
		session.setAttribute("user", user);
		businessService.saveAppUser(user);
		String resetLink = endURL+"/resetPassword?token=" + resetToken;

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

		sendResetEmail(email,emailContent);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/reset-password", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Void> resetPassword(@RequestParam("token") String token, @RequestParam(value = "password") String password) {
		HttpSession session = getSession();
		String resetToken = (String) session.getAttribute("resetToken");
		AppUser user = (AppUser) session.getAttribute("user");
		if (resetToken == null || user == null || !resetToken.equals(token)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		session.removeAttribute("resetToken");
		session.removeAttribute("user");
		user.setPassword(password);
		businessService.saveAppUser(user);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/validateToken", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Void> validateToken(@RequestParam("token") String token) {
		HttpSession session = getSession();
		String resetToken = (String) session.getAttribute("resetToken");
		if (resetToken == null || !resetToken.equals(token)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private void sendResetEmail(String email,String emailContent) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ankit@7span.com");
		message.setTo(email);
		message.setSubject("Your Tobuz Password Reset Link");
		message.setText(emailContent);
		emailSender.send(message);
	}


	@GetMapping({ "/listAllUsers" })
	public ResponseEntity<Vo> listAllUsers() {
		List<String[]> obj = new ArrayList<>();
		// Dummy list of values
		List<BusinessListingDTO> list = businessService.getAllpublishedListings();
		System.out.println("list >>>>>>>>>>>>>>" + list.size());
		if (null != list)
			for (int i = 0; i < list.size(); i++) {
				BusinessListingDTO bDto = list.get(i);
				String listId = bDto.getBusinessListingId();
				String title = bDto.getTitle();
				String listingFor = bDto.getListingFor();
				String countryName = bDto.getCountryName();
				String userRole = bDto.getUserRole();
				String createdDate = bDto.getCreatedOn();
				String buttons = "<select><option value=''>--Select--</option>";
				buttons += "<option value='Approve' onSelect=doAction('Approve'," + bDto.getId() + ")>Approve</option>";
				buttons += "<option value='Review' onSelect=doAction('Review'," + bDto.getId() + ")>Review</option>";
				buttons += "<option value='Reject' onSelect=doAction('Reject'," + bDto.getId() + ")>Reject</option>";
				buttons += "</select>";
				String mStringArray[] = { listId, title, listingFor, countryName, userRole, createdDate, buttons };
				obj.add(mStringArray);

			}
		Vo vo = new Vo();
		vo.setData(obj);
		return new ResponseEntity<>(vo, HttpStatus.OK);

	}

	@GetMapping({ "/getAllAdverts" })
	public ResponseEntity<Vo> getAllAdverts() {
		List<String[]> obj = new ArrayList<>();
		// Dummy list of values
		List<BusinessAdvertDTO> list = businessService.getAllAdverts();
		System.out.println("list >>>>>>>>>>>>>>" + list.size());
		if (null != list)
			for (int i = 0; i < list.size(); i++) {
				BusinessAdvertDTO bDto = list.get(i);
				String advertId = bDto.getAdvertId();
				String title = bDto.getTitle();
				String status = bDto.getStatus();
				String createdOn = bDto.getCreatedOn();
				String expairedOn = bDto.getExpiredOn();
				String investmentRangeFrom = bDto.getInvestmentRangeFrom();
				String investmentRangeTo = bDto.getInvestmentRangeTo();
				String buttons = "<select><option value=''>--Select--</option>";
				buttons += "<option value='Approve' onSelect=doAction('Approve'," + bDto.getId() + ")>Approve</option>";
				buttons += "<option value='Review' onSelect=doAction('Review'," + bDto.getId() + ")>Review</option>";
				buttons += "<option value='Reject' onSelect=doAction('Reject'," + bDto.getId() + ")>Reject</option>";
				buttons += "</select>";
				String mStringArray[] = { advertId, title, status, createdOn, expairedOn, investmentRangeFrom,
						investmentRangeTo, buttons };
				obj.add(mStringArray);

			}
		Vo vo = new Vo();
		vo.setData(obj);
		return new ResponseEntity<>(vo, HttpStatus.OK);

	}

	@GetMapping({ "/getCategoriesForAdmin" })
	public ResponseEntity<Vo> getCategoriesForAdmin() {
		List<String[]> obj = new ArrayList<>();
		// Dummy list of values
		List<CategoryDTO> list = businessService.getCategories();
		System.out.println("list >>>>>>>>>>>>>>" + list.size());
		if (null != list)
			for (int i = 0; i < list.size(); i++) {
				CategoryDTO bDto = list.get(i);
				String name = bDto.getName();
				String sequence = bDto.getSequence();
				String featured = bDto.getFeaturedCategory();

				String buttons = "<img src=/images/edit.png onclick='editCategory("+bDto.getId()+")'   alt=edit/> &nbsp;<img src=/images/view.jpg onclick='viewProfile(\"+bDto.getId()+\")'  style=' width: 24px !important; height: 24px !important;'  alt=edit/>  ";
				String mStringArray[] = { i + "", name, sequence, featured, buttons };
				obj.add(mStringArray);

			}
		Vo vo = new Vo();
		vo.setData(obj);
		return new ResponseEntity<>(vo, HttpStatus.OK);

	}

	@GetMapping({ "/getAdminUsers" })
	public ResponseEntity<Vo> getAdminUsers() {
		List<String[]> obj = new ArrayList<>();
		// Dummy list of values
		List<UserDTO> list = businessService.getAdminUsers();
		System.out.println("list >>>>>>>>>>>>>>" + list.size());
		if (null != list)
			for (int i = 0; i < list.size(); i++) {
				UserDTO bDto = list.get(i);
				String name = bDto.getName();
				String email = bDto.getEmail();
				String country = bDto.getCountry();
				String role = bDto.getRole();
				String buttons = "<img src=/images/edit.png    alt=edit/> "
						+ "&nbsp; <button type='button'  class='btn btn-secondary btn-sm'>Profile</button>";
				String mStringArray[] = { (i + 1) + "", name, email, country, role, buttons };
				obj.add(mStringArray);

			}
		Vo vo = new Vo();
		vo.setData(obj);
		return new ResponseEntity<>(vo, HttpStatus.OK);

	}

	@GetMapping({ "/getAdminUserRequests" })
	public ResponseEntity<Vo> getAdminUserRequests() {
		List<String[]> obj = new ArrayList<>();
		// Dummy list of values
		List<UserRequestDTO> list = businessService.getAdminUserRequests();
		System.out.println("list >>>>>>>>>>>>>>" + list.size());
		if (null != list)
			for (int i = 0; i < list.size(); i++) {
				UserRequestDTO bDto = list.get(i);
				String name = bDto.getName();
				String company = bDto.getCompanyName();
				String email = bDto.getEmail();
				String message = bDto.getMesssage();
				String phone = bDto.getPhoneNumber();
				String business_funding = bDto.getBusinessFunding();
				String createdOn = bDto.getCreatedOn();
				String business_status = bDto.getBusiness_status();

				String mStringArray[] = { (i + 1) + "", name, company, email, message, phone, business_funding,
						createdOn, business_status };
				obj.add(mStringArray);

			}
		Vo vo = new Vo();
		vo.setData(obj);
		return new ResponseEntity<>(vo, HttpStatus.OK);

	}

	@GetMapping({ "/getAdminMessages" })
	public ResponseEntity<Vo> getAdminMessages() {
		List<String[]> obj = new ArrayList<>();
		// Dummy list of values
		List<MessageDTO> list = businessService.getAdminMessages();
		System.out.println("getAdminMessages list >>>>>>>>>>>>>>" + list.size());
		if (null != list)
			for (int i = 0; i < list.size(); i++) {
				MessageDTO bDto = list.get(i);
				long id = bDto.getId();
				String name = bDto.getName();
				String email = bDto.getEmail();
				String code = bDto.getDialingCode();
				String mobile = bDto.getMobile();
				String ceatedDate = bDto.getCreatedOn();
				String mStringArray[] = { (i + 1) + "", id + "", name, email, code, mobile, ceatedDate };
				obj.add(mStringArray);

			}
		Vo vo = new Vo();
		vo.setData(obj);
		return new ResponseEntity<>(vo, HttpStatus.OK);

	}

	@GetMapping({ "/getAllAdminPayments" })
	public ResponseEntity<?> getAllAdminPayments() {
		List<String[]> obj = new ArrayList<>();
		// Dummy list of values
		List<PaymentDTO> list = businessService.getAllAdminPayments();
		System.out.println("getAdminMessages list >>>>>>>>>>>>>>" + list.size());
		if (null != list)
			for (int i = 0; i < list.size(); i++) {
				PaymentDTO bDto = list.get(i);

				String name = bDto.getUserName();
				String role = bDto.getRole();
				String description = bDto.getDescription();
				String countryName = bDto.getCountryName();
				String amount = bDto.getAmount();
				String date = bDto.getTransactionDate();
				String status = bDto.getStatus();
				String buttons = "<a href='#'>INVOICE </a>  ";
				String mStringArray[] = { (i + 1) + "", name, role, description, countryName, amount, date, status,
						buttons };
				obj.add(mStringArray);

			}
		Vo vo = new Vo();
		vo.setData(obj);
		return new ResponseEntity<>(vo, HttpStatus.OK);

	}

	@GetMapping({ "/getAdminContactus" })
	public ResponseEntity<Vo> getAdminContactus() {
		List<String[]> obj = new ArrayList<>();
		// Dummy list of values
		List<ContactDTO> list = businessService.getAdminContactus();
		System.out.println("list >>>>>>>>>>>>>>" + list.size());
		if (null != list)
			for (int i = 0; i < list.size(); i++) {
				ContactDTO bDto = list.get(i);
				String name = bDto.getName();
				String email = bDto.getEmail();
				String phone = bDto.getPhone();
				String city = bDto.getCity();
				String message = bDto.getMessage();
				String buttons = "<img src=/images/edit.png    alt=edit/> "
						+ "&nbsp; <button type='button'  class='btn btn-secondary btn-sm'>Profile</button>";
				String mStringArray[] = { (i + 1) + "", name, email, phone, city, message };
				obj.add(mStringArray);

			}
		Vo vo = new Vo();
		vo.setData(obj);
		return new ResponseEntity<>(vo, HttpStatus.OK);

	}

	@GetMapping({ "/getAdminTestimonials" })
	public ResponseEntity<Vo> getAdminTestimonials() {
		List<String[]> obj = new ArrayList<>();
		// Dummy list of values
		List<TestimonialDTO> list = businessService.getAdminTestimonials();
		System.out.println("list >>>>>>>>>>>>>>" + list.size());
		if (null != list)
			for (int i = 0; i < list.size(); i++) {
				TestimonialDTO bDto = list.get(i);
				String name = bDto.getUserName();
				String email = bDto.getEmail();
				String companyName = bDto.getAboutUser();
				String description = bDto.getDescription();

				String buttons = "<img src=/images/edit.png  onclick='editTestimonial(\"+bDto.getId()+\")' alt=edit/> "
						+ "&nbsp;<img src=/images/delete.png onclick='deleteTestimonial(\\\"+bDto.getId()+\\\")'    alt=edit/> ";
				String mStringArray[] = { name, email, companyName, description, buttons };
				obj.add(mStringArray);

			}
		Vo vo = new Vo();
		vo.setData(obj);
		return new ResponseEntity<>(vo, HttpStatus.OK);

	}

	@GetMapping({ "/getTobuzPackagesBySearchKey" })
	public ResponseEntity<Vo> getTobuzPackagesBySearchKey() {
		List<String[]> obj = new ArrayList<>();
		// Dummy list of values
		List<TobuzPackageDTO> list = businessService.getTobuzPackagesBySearchKey();
		System.out.println("list >>>>>>>>>>>>>>" + list.size());
		if (null != list)
			for (int i = 0; i < list.size(); i++) {
				TobuzPackageDTO bDto = list.get(i);
				String packageName = bDto.getPackageType();
				String timeLine = bDto.getExpiryPeriodInMonths();
				String cost = bDto.getCost();
				String userRole = bDto.getUserRole();

				if (timeLine == null) {
					timeLine = "0";
				}
				if (cost == null) {
					cost = "0.0";
				}
				String buttons = "<img src='/images/edit.png'  alt='edit'  onclick='editPackage(" + bDto.getId()
						+ ")' />";
				String mStringArray[] = { i + "", packageName, timeLine, cost, buttons };
				obj.add(mStringArray);

			}
		Vo vo = new Vo();
		vo.setData(obj);
		return new ResponseEntity<>(vo, HttpStatus.OK);

	}

	@GetMapping({ "/getTobuzFeatures" })
	public ResponseEntity<Vo> getTobuzFeatures() {
		List<String[]> obj = new ArrayList<>();
		// Dummy list of values
		List<TobuzfeatureDTO> list = businessService.getTobuzFeatures();
		System.out.println("list >>>>>>>>>>>>>>" + list.size());
		if (null != list)
			for (int i = 0; i < list.size(); i++) {
				TobuzfeatureDTO bDto = list.get(i);

				String title = bDto.getTitle();

				String buttons = "<img src='/images/edit.png'  alt='edit'  onclick='editFeature(" + bDto.getId()
						+ ")' />";
				String mStringArray[] = { i + "", title, buttons };
				obj.add(mStringArray);

			}
		Vo vo = new Vo();
		vo.setData(obj);
		return new ResponseEntity<>(vo, HttpStatus.OK);

	}

	@GetMapping({ "/getTobuzFeatureById" })

	@RequestMapping(value = "/getTobuzFeatureById/{id}", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public List<TobuzfeatureDTO> getTobuzFeatureById(@PathVariable String id) {
		System.out.println("id : " + id);
		return businessService.getTobuzFeatureById(Long.parseLong(id));
	}

	@RequestMapping(value = "/getTobuzPackagesByUser", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<Vo> getTobuzPackagesByUser(@RequestBody TobuzPackageDTO packageDTO) {
		List<String[]> obj = new ArrayList<>();
		// Dummy list of values
		System.out.println("User XXXX: " + packageDTO.getUserRole());
		List<TobuzPackageDTO> list = businessService.getTobuzPackagesByUser(packageDTO.getUserRole());
		System.out.println(packageDTO.getUserRole() + "  list >>>>>>>>>>>>>>" + list.size());
		if (null != list)
			for (int i = 0; i < list.size(); i++) {
				TobuzPackageDTO bDto = list.get(i);
				String packageName = bDto.getPackageType();
				String timeLine = bDto.getExpiryPeriodInMonths();
				String cost = bDto.getCost();
				String userRole = bDto.getUserRole();

				if (timeLine == null) {
					timeLine = "0";
				}
				if (cost == null) {
					cost = "0.0";
				}
				String buttons = "<img src=/images/edit.png  alt=edit onclick='editPackage(\"+bDto.getId()+\")' / >";
				String mStringArray[] = { i + "", packageName, timeLine, cost, buttons };
				obj.add(mStringArray);

			}
		Vo vo = new Vo();
		vo.setData(obj);
		return new ResponseEntity<>(vo, HttpStatus.OK);

	}

	@RequestMapping(value = "/getTobuzFeaturesByUser", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<Vo> getTobuzFeaturesByUser(@RequestBody TobuzfeatureDTO packageDTO) {
		List<String[]> obj = new ArrayList<>();
		// Dummy list of values
		System.out.println("User XXXX: " + packageDTO.getUserRole());
		List<TobuzfeatureDTO> list = businessService.getTobuzFeaturesByUser(packageDTO.getUserRole());
		System.out.println(packageDTO.getUserRole() + "  list >>>>>>>>>>>>>>" + list.size());
		if (null != list)
			for (int i = 0; i < list.size(); i++) {
				TobuzfeatureDTO bDto = list.get(i);

				String title = bDto.getTitle();
				String buttons = "<img src='/images/edit.png'  alt='edit'  onclick='editFeature(" + bDto.getId()
						+ ")' />";
				String mStringArray[] = { i + "", title, buttons };
				obj.add(mStringArray);

			}
		Vo vo = new Vo();
		vo.setData(obj);
		return new ResponseEntity<>(vo, HttpStatus.OK);

	}

	@PostMapping("/updateBusinessListing")
	public ResponseEntity<Void> updateBusinessListing(@RequestBody BusinessListingDTO bListingDTO) {
		businessService.updateBusinessListing(bListingDTO);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/updateAdminPackage")
	public ResponseEntity<Void> updateAdminPackage(@RequestBody TobuzPackageDTO tobuzPackageDTO) {
		businessService.updateAdminPackage(tobuzPackageDTO);
		System.out.println("tobuzPackageDTO getId:" + tobuzPackageDTO.getId());
		System.out.println("tobuzPackageDTO getCountryName:" + tobuzPackageDTO.getCountryName());
		System.out.println("tobuzPackageDTO getDescription:" + tobuzPackageDTO.getDescription());
		System.out.println("tobuzPackageDTO getPackageType:" + tobuzPackageDTO.getPackageType());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/updateAdminFeature")
	public ResponseEntity<Void> updateAdminFeature(@RequestBody TobuzfeatureDTO tobuzPackageDTO) {
		businessService.updateAdminPackage(tobuzPackageDTO);
		System.out.println("tobuzPackageDTO getId:" + tobuzPackageDTO.getId());
		System.out.println("tobuzPackageDTO getCountryName:" + tobuzPackageDTO.getCountryName());
		System.out.println("tobuzPackageDTO getDescription:" + tobuzPackageDTO.getDescription());
		System.out.println("tobuzPackageDTO getTitle:" + tobuzPackageDTO.getTitle());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllBusinessTypes", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	List<BusinessServiceTypeDTO> getAllBusinessTypes() {
		return businessService.getAllBusinessTypes();
	}

	@RequestMapping(value = "/getAllCategories", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	List<CategoryDTO> getAllCategories() {
		return businessService.getAllCategories();
	}

	@RequestMapping(value = "/getNumberOfActiveUsers", method = RequestMethod.GET)
	@ResponseBody
	public int getNumberOfActiveUsers() {

		return businessService.getNumberOfActiveUsers();

	}

	@RequestMapping(value = "/getNumberOfUsers", method = RequestMethod.GET)
	@ResponseBody
	public int getNumberOfUsers() {

		return businessService.getNumberOfUsers();

	}

	@RequestMapping(value = "/getTotalActiveListings", method = RequestMethod.GET)
	@ResponseBody
	public int getTotalActiveListings() {

		return businessService.getTotalActiveListings();

	}

	@RequestMapping(value = "/getSoldBusiness", method = RequestMethod.GET)
	@ResponseBody
	public int getSoldBusiness() {

		return businessService.getSoldBusiness();

	}

	@RequestMapping(value = "/getTobuzPackagesById/{id}", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public List<TobuzPackageDTO> getTobuzPackagesById(@PathVariable String id) {
		System.out.println("id : " + id);
		return businessService.getTobuzPackagesById(Long.parseLong(id));
	}

	@RequestMapping(value = "/getTobuzCategoryById/{id}", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public CategoryDTO getTobuzCategoryById(@PathVariable String id) {
		System.out.println("id : " + id);
		return businessService.getTobuzCategoryById(Long.parseLong(id));
	}

	@RequestMapping(value = "/getAdvertListingsForTypeAndUser/{type}", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public List <BusinessAdvertDTO> getAdvertListingsForTypeAndUser(@PathVariable String type) {
		System.out.println("type : " + type);
		return businessService.getAdvertListingsForTypeAndUser(type);

	}


	@RequestMapping(value = "/getBusineeListingbyListingId/{id}", produces = {
			"application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public List<BusinessListingDTO> getBusineeListingbyListingId(@PathVariable String id) {
		System.out.println("bDto business Listing id :" + id);
		List<BusinessListing> busineeList = businessService.getBusineeListingbyListingId(Long.parseLong(id));
		List<BusinessListingDTO> returnList = new ArrayList<BusinessListingDTO>();
		for (BusinessListing businessListing : busineeList) {
			BusinessListingDTO businessListingDTO = new BusinessListingDTO();
			businessListingDTO.setBusinessListingId("" + businessListing.getListingId());
			businessListingDTO.setBusinessListingStatus(businessListing.getBusinessListingStatus());
			businessListingDTO.setTitle(businessListing.getTitle());
			businessListingDTO.setDescription(businessListing.getListingDescription());
			businessListingDTO.setSuggestedTitle(businessListing.getSuggestedTitle());
			businessListingDTO.setCategoryList(businessListing.getCategory().getName());
			businessListingDTO.setListingFor(businessListing.getListingFor());
			businessListingDTO.setListingType(businessListing.getListingType());
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			System.out.println(businessListing.getBusinessListingStatus());
			System.out.println(businessListing.getTitle());
			System.out.println(businessListing.getSuggestedtitle());
			System.out.println("*****************************");
			System.out.println(businessListing.getSuggestedTitle());
			if (businessListing.getListingGallery() != null && businessListing.getListingGallery().size() > 0)
				if (businessListing.getListingGallery().get(0) != null) {
					System.out.println(businessListing.getListingGallery().get(0).getFilePath());
					businessListingDTO.setFilePath1(businessListing.getListingGallery().get(0).getFilePath());
				}
			if (businessListing.getListingGallery() != null && businessListing.getListingGallery().size() > 1)
				if (businessListing.getListingGallery().get(1) != null) {
					System.out.println(businessListing.getListingGallery().get(1).getFilePath());
					businessListingDTO.setFilePath2(businessListing.getListingGallery().get(1).getFilePath());
				}

			if (businessListing.getBusinessListingFeatureInfoList() != null
					&& businessListing.getBusinessListingFeatureInfoList().size() > 0) {
				List<BusinessListingFeatureInfoDTO> businessListingFeatureInfoDTOs = new ArrayList<BusinessListingFeatureInfoDTO>();
				BusinessListingFeatureInfoDTO businessListingFeatureInfoDTO = new BusinessListingFeatureInfoDTO();
				BusinessFeatureDTO businessFeatureDTO = new BusinessFeatureDTO();
				if (businessListing.getBusinessListingFeatureInfoList().get(0).getBusinessFeature() != null) {

					businessFeatureDTO.setName(
							businessListing.getBusinessListingFeatureInfoList().get(0).getBusinessFeature().getName());
					System.out.println("businessFeatureDTO.getName() : " + businessFeatureDTO.getName());
				}

				businessListingFeatureInfoDTO.setBusinessFeatureDTO(businessFeatureDTO);
				businessListingFeatureInfoDTOs.add(businessListingFeatureInfoDTO);
				businessListingDTO.setBusinessListingFeatureInfos(businessListingFeatureInfoDTOs);

			}
			if (businessListing.getBusinessListingOutLet() != null) {
				businessListingDTO
						.setBusinessTurnover(businessListing.getBusinessListingOutLet().getBusinessTurnover());
				businessListingDTO
						.setBusinessTurnOver(businessListing.getBusinessListingOutLet().getBusinessTurnover());
				businessListingDTO.setBusinessTotalExpenses(
						businessListing.getBusinessListingOutLet().getBusinessTotalExpenses());
				businessListingDTO.setNetProfit(businessListing.getBusinessListingOutLet().getNetProfit());
				businessListingDTO.setGrossProfit(businessListing.getBusinessListingOutLet().getGrossProfit());
			}

			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			returnList.add(businessListingDTO);
		}

		return returnList;
	}

	@RequestMapping(value = "/findFavouritesForUser", method = RequestMethod.GET)
	@ResponseBody
	public BusinessListingDTO findFavouritesForUser() {
		return businessService.findFavouritesForUser();
	}

	@RequestMapping(value = "/findBusinessAdvertsforUser", method = RequestMethod.GET)
	@ResponseBody
	public List<BusinessAdvertDTO> findBusinessAdvertsforUser() {
		return businessService.findBusinessAdvertsforUser();
	}

	@RequestMapping(value = "/getPackageInfoForSeller", method = RequestMethod.GET)
	@ResponseBody
	public List<UserPackageInfoDTO> getPackageInfoForSeller() {
		return businessService.getPackageInfoForSeller();
	}

	public static HttpSession getSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true); // true == allow create
	}


	@GetMapping({ "/getUserMessages" })
	public ResponseEntity<Vo> getUserMessages() {
		List<String[]> obj = new ArrayList<>();
		// Dummy list of values
		List<MessageDTO> list = businessService.getUserMessages();
		System.out.println("getAdminMessages list >>>>>>>>>>>>>>" + list.size());
		if (null != list)
			for (int i = 0; i < list.size(); i++) {
				MessageDTO bDto = list.get(i);
				long id = bDto.getId();
				String name = bDto.getName();
				String email = bDto.getEmail();
				String code = bDto.getDialingCode();
				String mobile = bDto.getMobile();
				String subject = bDto.getSubject();
				String ceatedDate = bDto.getCreatedOn();
				String buttons = "<img src=/images/view.jpg onclick='viewMessage(\"+bDto.getId()+\")'  style=' width: 24px !important; height: 24px !important;'  alt=view/>  ";

				String mStringArray[] = { (i + 1) + "", id+"",name, email, code, mobile, ceatedDate , subject ,buttons};
				obj.add(mStringArray);

			}
		Vo vo = new Vo();
		vo.setData(obj);
		return new ResponseEntity<>(vo, HttpStatus.OK);

	}

	@RequestMapping(value = "/getBusinessByFilter",produces = {
			"application/json" }, method = RequestMethod.POST)
	@ResponseBody
	public PageBusinessListingDto getBusinessByFilter(@RequestBody BusinessListingDTO businessListingDTO, @RequestParam(value = "page",defaultValue = "0",required = false)int page, @RequestParam(value = "size",defaultValue = "6",required = false)int size){
		return businessService.getBusinessByFilter(businessListingDTO,page,size);

	}

	@RequestMapping(value = "/saveNewsletter", method = RequestMethod.POST,produces = {"application/json"})
	@ResponseBody
	public ResponseEntity<NewsLetterSubscription> saveNewsletter(@RequestParam("text") String email) {
		return new ResponseEntity<>(businessService.saveNewsletter(email),HttpStatus.OK);
	}

	@RequestMapping(value = "/getBrokerList", method = RequestMethod.POST, produces = {"application/json"})
	@ResponseBody
	public PageBrokerListDto getBrokerList(@RequestBody BrokerListingDTO brokerListingDTO, @RequestParam(value = "page",defaultValue = "0",required = false)int page, @RequestParam(value = "size",defaultValue = "6",required = false)int size){
		return businessService.getBrokerList(brokerListingDTO,page,size);
	}


	@RequestMapping(value = "/getAllBroker", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public PageBrokerListDto getAllBrokerList(@RequestParam(value = "page",defaultValue = "0",required = false)int page, @RequestParam(value = "size",defaultValue = "6",required = false)int size) {
		return businessService.getAllBrokerList(page,size);
	}

	@RequestMapping(value = "/getAllCategoryList", method = RequestMethod.GET)
	@ResponseBody
	public List<CategoryDTO> getAllCategoryList() {
		return businessService.getAllCategoryList();
	}

	@RequestMapping(value = "/getAllCountryList", method = RequestMethod.GET)
	@ResponseBody
	public List<Country> getAllCountryList() {
		return businessService.getAllCountryList();
	}

	@RequestMapping(value = "/getAllBusinessServiseTypeList", method = RequestMethod.GET)
	@ResponseBody
	public PageBusinessServiceTypeDTO getAllBusinessServiseTypeList(@RequestParam(value = "page",defaultValue = "0",required = false)int page, @RequestParam(value = "size",defaultValue = "6",required = false)int size) {
		return businessService.getAllBusinessServiseTypeList(page,size);

	}

	@RequestMapping(value = "/manageFavouriteBusiness", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> manageFavouriteBusinesses(@RequestBody BusinessFavouriteDTO businessFavouriteDTO)
	{
		return businessService.manageFavouriteBusinesses(businessFavouriteDTO);
	}

	@PostMapping(value = "/addTestimonial")	
	public ResponseEntity<ResponseDTO<?>> addTestimonial(@RequestBody TestimonialDTO testimonialDTO){
		return businessService.addTestimonial(testimonialDTO);
	}
	
	
	@RequestMapping(value = "/get_business_listing/{page}", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> getBusinessListing(@PathVariable(name="page")Integer page) {
		ResponseDTO<List<BusinessListingQueryDTO>> response= new ResponseDTO<List<BusinessListingQueryDTO>>();
		try
		{
			List<BusinessListingQueryDTO> business= businessService.getBusinessListing(page);
			if(business!=null)
			{
				response.setMessage(Constants.MSG_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			}
			else
			{
				response.setMessage(Constants.MSG_NOT_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			}
			return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.OK);
		}
		catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@RequestMapping(value = "/saveContact", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> saveContact(@RequestBody ContactDTO contactDTO) {
		
		ResponseDTO<ContactDTO> response= new ResponseDTO<ContactDTO>();
		try
		{
			List<ContactUs> contacts= businessService.findContactByEmailId(contactDTO.getEmail());
			
			if(contacts!=null &&  contacts.size()>0)
			{
				response.setMessage(contactDTO.getEmail() +" already used for contact");
				response.setStatus(Constants.STATUS_FAILED);
				return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.OK);
				/*ContactUs c=contacts.get(0);				
				c.setCity(contactDTO.getCity());				
				c.setMessage(contactDTO.getMessage());				
				c.setPhone(contactDTO.getPhone());
				businessService.saveContactus(c);				*/
			}
			else
			{
				contactDTO.setCreatedOn(new Date());
				contactDTO.setLastUpdate(new Date());
				businessService.saveContact(contactDTO);
			}
			
				response.setMessage(Constants.STATUS_SUCCESS);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(contactDTO);
			
			return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.OK);
		}
		catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@RequestMapping(value = "/saveBusinessContact", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> saveBusinessContact(@RequestBody ContactDTO contactDTO) {
		
		ResponseDTO<ContactDTO> response= new ResponseDTO<ContactDTO>();
		try
		{
			List<UserContactInfo> contacts= businessService.findUserContactByEmailIdAndBusinessId(contactDTO.getEmail(),contactDTO.getBusinessId());
			
			if(contacts!=null &&  contacts.size()>0)
			{
				response.setMessage(contactDTO.getEmail() +" already used for contact");
				response.setStatus(Constants.STATUS_FAILED);
				return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.OK);
				
			}
			else
			{
				contactDTO.setCreatedOn(new Date());
				contactDTO.setLastUpdate(new Date());
				businessService.saveUserContact(contactDTO);
			}
			
				response.setMessage(Constants.STATUS_SUCCESS);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(contactDTO);
			
			return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.OK);
		}
		catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}


}
