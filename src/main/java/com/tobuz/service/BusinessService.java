package com.tobuz.service;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tobuz.constant.Constants;
import com.tobuz.dto.AddBusinessListingDTO;
import com.tobuz.dto.BrokerDTO;
import com.tobuz.dto.BusinessAdvisorDTO;
import com.tobuz.dto.ResponseDTO;
import com.tobuz.dto.SearchDTO;
import com.tobuz.model.AppUser;
import com.tobuz.model.BusinessAdvert;
import com.tobuz.model.BusinessAdvisor;
import com.tobuz.model.BusinessListing;
import com.tobuz.model.BusinessListingFeatureInfo;
import com.tobuz.model.BusinessListingOutLet;
import com.tobuz.model.BusinessListingSubCategories;
import com.tobuz.model.BusinessServiceType;
import com.tobuz.model.Category;
import com.tobuz.model.ContactUs;
import com.tobuz.model.Country;
import com.tobuz.model.FileEntity;
import com.tobuz.model.ListingFor;
import com.tobuz.model.ListingType;
import com.tobuz.model.NewsLetterSubscription;
import com.tobuz.model.Role;
import com.tobuz.model.State;
import com.tobuz.model.SubCategory;
import com.tobuz.model.Testimonial;
import com.tobuz.model.UserContactInfo;
import com.tobuz.model.UserRole;
import com.tobuz.model.tobuzpackage.TobuzPackage;
import com.tobuz.model.tobuzpackage.TobuzPackageService;
import com.tobuz.object.BrokerListingDTO;
import com.tobuz.object.BusinessAdvertDTO;
import com.tobuz.object.BusinessFavouriteDTO;
import com.tobuz.object.BusinessListingDTO;
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
import com.tobuz.object.SubCategoryDTO;
import com.tobuz.object.TestimonialDTO;
import com.tobuz.object.TobuzPackageDTO;
import com.tobuz.object.TobuzfeatureDTO;
import com.tobuz.object.UserDTO;
import com.tobuz.object.UserPackageInfoDTO;
import com.tobuz.object.UserRequestDTO;
import com.tobuz.projection.BrokerList;
import com.tobuz.projection.BusinessByFilter;
import com.tobuz.projection.BusinessServiseTypeList;
import com.tobuz.projection.CategoryByFilter;
import com.tobuz.projection.CountryList;
import com.tobuz.projection.FavouriteBusinessDetails;
import com.tobuz.projection.FavouriteBusinessDetailsCount;
import com.tobuz.projection.TopBusinessListingDetails;
import com.tobuz.repository.BusinessAdvertRepository;
import com.tobuz.repository.BusinessListingOutletRepository;
import com.tobuz.repository.BusinessListingRepository;
import com.tobuz.repository.BusinessListingSubCategoriesRepository;
import com.tobuz.repository.BusinessServiceTypeRepository;
import com.tobuz.repository.CategoryRepository;
import com.tobuz.repository.ContactUsRepository;
import com.tobuz.repository.CountryRepository;
import com.tobuz.repository.FileEntityRepositiory;
import com.tobuz.repository.MessageRepository;
import com.tobuz.repository.NewsLetterRepository;
import com.tobuz.repository.RoleRepository;
import com.tobuz.repository.StateRepository;
import com.tobuz.repository.SubCategoryRepository;
import com.tobuz.repository.TestimonialRepository;
import com.tobuz.repository.TobuzFeatureRepository;
import com.tobuz.repository.TobuzPackageRepository;
import com.tobuz.repository.UserContactInfoRepository;
import com.tobuz.repository.UserRepository;

@Service
public class BusinessService {
	
	private static final Logger logger = LoggerFactory.getLogger(BusinessService.class);
	 
	 public static final String CLASS_NAME="BusinessService ";
	
		@Value("${file.upload.path}")
		private String UploadPath;
	 
	@Autowired
	FileEntityRepositiory fileRepository;
	//getting all books record by using the method findaAll() of CrudRepository

	@Autowired
	UserRepository userRepository;

	@Autowired
	BusinessListingRepository businessListingRepository;
	
	@Autowired
	private BusinessAdvertRepository businessAdvertRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	RoleRepository roleRepository ;

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	FileEntityRepositiory fileEntityRepositiory ;

	@Autowired
	TobuzPackageRepository tobuzPackageRepository;

	@Autowired
	TobuzFeatureRepository tobuzFeatureRepository;

	@Autowired
	ContactUsRepository contactUsRepository;
	
	@Autowired
	private BusinessListingSubCategoriesRepository businessListingSubCatRepository;

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	SubCategoryRepository subCategoryRepository;

	@Autowired
	NewsLetterRepository newsLetterRepository;
	
	@Autowired
	private BusinessListingOutletRepository businessListingOutletRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private BusinessServiceTypeRepository businessServiceTypeRepository;
	
	@Autowired
	private TestimonialRepository testimonialRepository;
	
	@Autowired
	private UserContactInfoRepository userContactInfoRepository;

	public List<BusinessListingDTO> getTopTenBusiness()
	{
	List<BusinessListingDTO> business = new ArrayList<BusinessListingDTO>();
	List <Object[]>businessList =  fileRepository.getTopTenBusiness();
	if (null != businessList) {
		try {
			for (Object [] objArray : businessList) {
				BusinessListingDTO businessListingDTO = new BusinessListingDTO ();
				if (null != objArray[0])
				businessListingDTO.setFilePath(objArray[0].toString());
				if (null != objArray[1])
					businessListingDTO.setTitle(objArray[1].toString());
				if (null != objArray[2])
					businessListingDTO.setDescription(objArray[2].toString());
				if (null != objArray[3])
					businessListingDTO.setPrice(Double.parseDouble(objArray[3]+""));
				if (null != objArray[4])
					businessListingDTO.setSuggestedTitle(objArray[4].toString());
				if (null != objArray[5])
					businessListingDTO.setId(Long.parseLong(objArray[5]+""));

				business.add(businessListingDTO);

			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("businessList : "+businessList.size());
	}
	else {
		System.out.println("businessList : NULL");
	}
	business.sort(Comparator.comparing(BusinessListingDTO :: getId));
	List<BusinessListingDTO> unique = business.stream()
            .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(BusinessListingDTO::getId))),
                                       ArrayList::new));
	System.out.println("businessList unique: "+unique.size());
	return unique;
	}


	public List<BusinessListingDTO> getTopTenRecentBusiness()
	{
	List<BusinessListingDTO> business = new ArrayList<BusinessListingDTO>();
	List <Object[]>businessList =  fileRepository.getTopTenRecentBusiness();
	if (null != businessList) {
		try {
			for (Object [] objArray : businessList) {
				BusinessListingDTO businessListingDTO = new BusinessListingDTO ();
				if (null != objArray[0])
				businessListingDTO.setFilePath(objArray[0].toString());
				if (null != objArray[1])
					businessListingDTO.setTitle(objArray[1].toString());
				if (null != objArray[2])
					businessListingDTO.setDescription(objArray[2].toString());
				if (null != objArray[3])
					businessListingDTO.setPrice(Double.parseDouble(objArray[3]+""));
				if (null != objArray[4])
					businessListingDTO.setSuggestedTitle(objArray[4].toString());
				if (null != objArray[5])
					businessListingDTO.setId(Long.parseLong(objArray[5]+""));
				business.add(businessListingDTO);

			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("businessList recent : "+businessList.size());
	}
	else {
		System.out.println("businessList recent: NULL");
	}
	business.sort(Comparator.comparing(BusinessListingDTO :: getId));
	List<BusinessListingDTO> unique = business.stream()
            .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(BusinessListingDTO::getId))),
                                       ArrayList::new));
	for(BusinessListingDTO bDto : unique) {
		System.out.println("businessList id: "+bDto.getId());
	}
	System.out.println("businessList recent: "+unique.size());
	return unique;
	}


	@Transactional
	public int registerUser (RegisterDTO registerDTO) {
		int value =1;
		try {
			System.out.println("value : "+value);
			userRepository.insertUser(true, registerDTO.getName(), registerDTO.getEmail(), registerDTO.getPhoneNo(), registerDTO.getPassword(), registerDTO.getCreatedOn(), registerDTO.getLastUpdate(),registerDTO.getRole());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			value = 0;
			e.printStackTrace();
		}
		return value ;
	}

	public RegisterDTO  getLoginInfo(String email , String password)
	{
		RegisterDTO registerDTO = new RegisterDTO ();
	List <Object[]>loginInfo =  userRepository.getLoginInfo(email, password);
	System.out.println("loginInfo :" +loginInfo);
	if (null != loginInfo) {
		try {
			for (Object [] objArray : loginInfo) {

				if (null != objArray[0]) {
					registerDTO.setName(objArray[0].toString());
				}
				if (null != objArray[1]) {
					registerDTO.setEmail(objArray[1].toString());
				}
				if (null != objArray[2]) {
					registerDTO.setRole(objArray[2].toString());
				}
				}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("role  :" +registerDTO.getRole());
	}
	return registerDTO;
	}


	public RegisterDTO findLoginInfo (String email , String password)   {
		RegisterDTO registerDTO = new RegisterDTO ();
		List <AppUser>loginInfo = (List <AppUser>) userRepository.findLoginInfo(email, password);
		System.out.println ("List >>>>"+loginInfo);
		AppUser appUser = loginInfo.get(0);
		System.out.println ("Name  >>>>"+appUser.getName());
		registerDTO.setId(appUser.getId());
		registerDTO.setName(appUser.getName());
		registerDTO.setPhoneNo(appUser.getMobileNumber());
		registerDTO.setEmail(appUser.getEmail());
		registerDTO.setRole(appUser.getUserDefaultRole()+"");
		HttpSession session = getSession();
		session.setAttribute("appUser", appUser);
		return registerDTO;
	}


	 public int getNumberOfActiveUsers () {
		int i = 0;
		try {
			i = userRepository.getNumberOfActiveUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i ;
	 }

	 public int getNumberOfUsers () {
			int i = 0;
			try {
				i = userRepository.getNumberOfUsers();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return i ;
		 }

	 public int getTotalActiveListings () {
			int i = 0;
			try {
				i = userRepository.getTotalActiveListings();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return i ;
		 }

	 public int getSoldBusiness () {
			int i = 0;
			try {
				i = userRepository.getSoldBusiness();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return i ;
		 }

	 public  BusinessListingDTO findFavouritesForUser (){
		 HttpSession session = getSession();
		 AppUser appUser = (AppUser) session.getAttribute("appUser");
		 BusinessListingDTO businessListingDTO = new BusinessListingDTO ();
		 System.out.println("appUser.getId() : "+appUser.getId());
		 int count  = businessListingRepository.findFavouritesForUser(appUser.getId());
		 System.out.println ("count :"+count);
		 businessListingDTO.setFavourites(""+count);
		 return businessListingDTO;

	 }
	 public List <BusinessAdvertDTO> findBusinessAdvertsforUser () {
		 HttpSession session = getSession();
		 AppUser appUser = (AppUser) session.getAttribute("appUser");
		 List <BusinessAdvertDTO> advertList = new ArrayList<BusinessAdvertDTO>();
		 BusinessAdvertDTO advertDTO = new BusinessAdvertDTO();


		 List <Object[]>loginInfo =   businessListingRepository.findBusinessAdvertsforUser(appUser.getId());
			System.out.println("findBusinessAdvertsforUser :" +loginInfo.size());
			if (null != loginInfo) {
				try {
					for (Object [] objArray : loginInfo) {

							if (null != objArray[0]) {
								advertDTO.setAdvertId(objArray[0].toString());
							}
							if (null != objArray[1]) {
								advertDTO.setTitle( objArray[1].toString());
							}
							if (null != objArray[2]) {
								advertDTO.setCreatedOn(objArray[2].toString());
							}
							if (null != objArray[3]) {
								advertDTO.setExpiredOn(objArray[3].toString());
							}
							if (null != objArray[4]) {
								advertDTO.setStatus((objArray[4].toString()));
							}
							advertList.add(advertDTO);
						}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("Advert Id  :" +advertDTO.getAdvertId());
			}

		 return advertList;

	 }
	 public List <UserPackageInfoDTO> getPackageInfoForSeller () {
		 HttpSession session = getSession();
		 AppUser appUser = (AppUser) session.getAttribute("appUser");
		 List <UserPackageInfoDTO>packageList = new ArrayList<UserPackageInfoDTO>();
		 System.out.println("getPackageInfoForSeller appUser.getId() : "+appUser.getId());
		 UserPackageInfoDTO packageDTO = new UserPackageInfoDTO();
		 List <Object[]>loginInfo =   businessListingRepository.getPackageInfoForSeller(appUser.getId());
			System.out.println("getPackageInfoForSeller :" +loginInfo.size());
			if (null != loginInfo) {
				try {
					for (Object [] objArray : loginInfo) {

							if (null != objArray[0]) {
								packageDTO.setPackageType(objArray[0].toString());
							}
							if (null != objArray[1]) {
								packageDTO.setCreatedOn(objArray[1].toString());
							}
							if (null != objArray[2]) {
								packageDTO.setAdvertListCount(objArray[2].toString());
							}
							if (null != objArray[3]) {
								packageDTO.setNoContactAccess(objArray[3].toString());
							}
							if (null != objArray[4]) {
								packageDTO.setActivatedOn(objArray[4].toString());
							}
							if (null != objArray[5]) {
								packageDTO.setPackageActiveFor(objArray[5].toString());
							}
							if (null != objArray[6]) {
								packageDTO.setUserPackageStatus(objArray[6].toString());
							}
							if (null != objArray[7]) {
								packageDTO.setNoContactAccess(objArray[7].toString().toString());
							}
							packageList.add(packageDTO);
						}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		return packageList;
	 }




	 public  List<BusinessServiceTypeDTO> getAllBusinessTypes () {
		 List<BusinessServiceTypeDTO> business = new ArrayList<BusinessServiceTypeDTO>();
			List <Object[]>businessList =  businessListingRepository.getAllBusinessTypes();
			if (null != businessList) {
				try {
					for (Object [] objArray : businessList) {
						BusinessServiceTypeDTO bTypeDTO = new BusinessServiceTypeDTO ();
						if (objArray[0] != null)
							bTypeDTO.setId(Long.parseLong(objArray[0].toString()));
						if (objArray[1] != null)
							bTypeDTO.setServiceType(objArray[1].toString());
						business.add(bTypeDTO);
					}

					}
					catch (Exception e) {
						e.printStackTrace();
					}
			}
			return business;
		 }

	 public  List<CategoryDTO> getAllCategories () {
		 List<CategoryDTO> business = new ArrayList<CategoryDTO>();
			List <Object[]>businessList =  businessListingRepository.getAllCategories();
			if (null != businessList) {
				try {
					for (Object [] objArray : businessList) {
						CategoryDTO bTypeDTO = new CategoryDTO ();
						if (objArray[0] != null)
							bTypeDTO.setId(Long.parseLong(objArray[0].toString()));
						if (objArray[1] != null)
							bTypeDTO.setName(objArray[1].toString());
						business.add(bTypeDTO);
					}

					}
					catch (Exception e) {
						e.printStackTrace();
					}
			}
			return business;
		 }

	 
	 
	 public ResponseEntity<ResponseDTO<?>> addBusinessListings (AddBusinessListingDTO dto) 
	 {

		 ResponseDTO<BusinessListing> response = new ResponseDTO<BusinessListing>();
		 try {
			 
			 if(dto.getCategoryId()==null ||dto.getCategoryId()==0)
			 {
				 response.setMessage("Category Id should Not empty");
				 response.setStatus(Constants.STATUS_FAILED);
				 return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.BAD_REQUEST);
			 }
			 if(dto.getAppUserId()==null ||dto.getAppUserId()==0)
			 {
				 response.setMessage("AppUser Id should Not empty");
				 response.setStatus(Constants.STATUS_FAILED);
				 return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.BAD_REQUEST);
			 }
			 Optional<Category> catOpt=categoryRepository.findById(dto.getCategoryId());
			
			 if(!catOpt.isPresent())
			 {
				 response.setMessage("Invalid Category Id");
				 response.setStatus(Constants.STATUS_FAILED);
				 return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			 }
			 Category category=catOpt.get();
			 Optional<AppUser> appUseropt = userRepository.findById(dto.getAppUserId());
			 if(!appUseropt.isPresent())
			 {
				 response.setMessage("App User not Found");
				 response.setStatus(Constants.STATUS_FAILED);
				 return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			 }
			 AppUser appUser=appUseropt.get();
			 
			 Optional<Country> countryOpt = countryRepository.findById(dto.getCountryId());
			
			 if(!countryOpt.isPresent())
			 {
				 response.setMessage("Selected Country Not found");
				 response.setStatus(Constants.STATUS_FAILED);
				 return new ResponseEntity<ResponseDTO<?>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			 }
			 Country country=countryOpt.get();
			 
			 
			 Date date = new Date();
			 Timestamp ts = new Timestamp(date.getTime());
						 
			 BusinessListing businessListing = new BusinessListing();

			 businessListing.setListingType(ListingType.BUSINESS);
			 businessListing.setCategory(category);		 		 
			 
			 businessListing.setDescription(dto.getListingDescription());			 
			 businessListing.setSuggestedtitle(dto.getSuggestedTitle());			 
			 businessListing.setListingKeywords(dto.getListingKeywords());
			 businessListing.setIsActive(true);
			 businessListing.setName(dto.getContactName());
			 businessListing.setContactNumber(dto.getContactNumber());
			 businessListing.setListingFor(ListingFor.SALE);
			 businessListing.setTitle(dto.getTitle());
			 businessListing.setLastUpdate(ts);
			 businessListing.setListingDescription(dto.getListingDescription());
			 businessListing.setWebsiteURL(dto.getWebSiteUrl());
			 businessListing.setPostedOn(date);
		 	 businessListing.setBusinessListingStatus(dto.getBusinessListingStatus());			 
			 businessListing.setCreatedOn(ts);
			 businessListing.setBusinessListingStatus(dto.getStatus());
			 businessListing.setListedByUser(appUser);
			 businessListing.setCreatedOn(ts);
			 BusinessAdvisor businessAdvisor = new BusinessAdvisor();
			 businessAdvisor.setIsActive(true);
			 businessAdvisor.setIsApprovedByAdmin(true);
			 businessAdvisor.setCompanyName(dto.getCompanyType());
			 
			 Role role = new Role();
			 role.setAppUser(appUser);			 
			 role.setUserRole(UserRole.SELLER);
			 role.setCreatedOn(ts);
			
			 			 
			 businessListing.setCountryCode(country.getDialingCode());
			 businessListing.setBusinessAddressCountry(country);
			 
			 roleRepository.save(role);
			 businessListing.setRole(role);
			 
			 BusinessListingOutLet outlet=new BusinessListingOutLet();
			 outlet.setBusinessDescription(dto.getListingDescription());
			 outlet.setNetProfit(dto.getNetProfit());
			 outlet.setGrossProfit(dto.getGrossProfit());
			 outlet.setGas(dto.getGasAmount());
			 outlet.setNoOfEmployees(dto.getNumberOfEmployees());			 
			 outlet.setRent(dto.getRentAmount());
			 outlet.setCompanyType(dto.getCompanyType());
			 outlet.setBusinessTurnover(dto.getBusinessTurnOver());
			 outlet.setBusinessTurnoverPer(dto.getBusinessTurnOverPer());
			 outlet.setBusinessTotalExpenses(dto.getBusinessTotalExpenses());
			 outlet.setCreatedOn(ts);
			 outlet.setTotalBusinessSalePrice(dto.getTotalPrice());
			 BusinessListingOutLet savedOutlet= businessListingOutletRepository.save(outlet);
			 businessListing.setBusinessListingOutLet(savedOutlet);
			 BusinessListing saved=businessListingRepository.save(businessListing);			 
		
			 addSubCategoryToBusinessListing(saved.getId(),dto.getSubCategoryIds());			 
			 
			 addImageFileToBusinessListing(dto.getListingGallary(),saved);
			 
			 response.setMessage("Successfully Saved Business");
			 response.setStatus(Constants.STATUS_SUCCESS);
			 response.setResult(saved);
			 
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
			
		} catch (Exception e) {
			logger.error("Add Business "+e.getStackTrace());
			response.setMessage(e.getMessage());
			response.setStatus(Constants.STATUS_FAILED);
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			 
		}
		 
	 }
	
	public void addSubCategoryToBusinessListing(Long businessId, Long[] subcategoryIds)
	{
	 for(Long subcategoryId:subcategoryIds)
	 {
		Optional<SubCategory> subOpt=subCategoryRepository.findById(subcategoryId);		
		if(subOpt.isPresent())
		{
			BusinessListingSubCategories bsc=new BusinessListingSubCategories();
			bsc.setCreatedOn(currentTimeStamp());
			bsc.setBusinessListingId(businessId);
			bsc.setCategoryId(subOpt.get().getCategory().getId());
			bsc.setSubCategoryId(subcategoryId);
			businessListingSubCatRepository.save(bsc);	
		}
	 }
		
	}
	
	public Timestamp currentTimeStamp()
	{
		Date date = new Date();
		 Timestamp ts = new Timestamp(date.getTime());
		 return ts;
	}
		
	
	public void addImageFileToBusinessListing(List<String> files,BusinessListing listing)
	{
		if(files!=null && files.size()>0)
		files.forEach(f->{
			FileEntity fe=new FileEntity();
			fe.setBusinessListing(listing);
			fe.setCreatedOn(currentTimeStamp());
			fe.setFileName(f);
			fe.setFilePath(UploadPath+"\\"+f);
			fe.setFileKey(f);			
			fileEntityRepositiory.save(fe);
		});
		
	}
	 

	public  List<BusinessListingDTO> getAllpublishedListings(){

	 List<BusinessListingDTO> business = new ArrayList<BusinessListingDTO>();
		List <Object[]>businessList =  businessListingRepository.getAllpublishedListings();
		if (null != businessList) {
			try {
				for (Object [] objArray : businessList) {
					BusinessListingDTO bTypeDTO = new BusinessListingDTO ();
					if (objArray[0] != null)
						bTypeDTO.setId(Long.parseLong(objArray[0].toString()));
					if (objArray[1] != null)
						bTypeDTO.setTitle(objArray[1].toString());
					if (objArray[2] != null)
						bTypeDTO.setListingFor(objArray[2].toString());
					if (objArray[3] != null)
						bTypeDTO.setIsoCode(objArray[3].toString());
					if (objArray[4] != null)
						bTypeDTO.setBusinessListingId(objArray[4].toString() );
					if (objArray[5] != null)
							bTypeDTO.setBusinessListingStatus(objArray[5].toString());
					if (objArray[6] != null)
								bTypeDTO.setUserRole(objArray[6].toString());
					if (objArray[7] != null)
						bTypeDTO.setCreatedOn(objArray[6].toString());

					System.out.println("ID="+bTypeDTO.getIsoCode());
						List<Country> country = countryRepository.findCountryByDialingCode(bTypeDTO.getIsoCode());
						if(country!=null && country.size()>0)
						{
							bTypeDTO.setCountryName(country.get(0).getName());
							bTypeDTO.setCountryId(country.get(0).getId());
							System.out.println("country NAME : "+bTypeDTO.getCountryName());
						}
						business.add(bTypeDTO);
					}


				}

				catch (Exception e) {
					e.printStackTrace();
				}
		}
		return business;
	 }

	public  List<PaymentDTO> getAllAdminPayments(){

		 List<PaymentDTO> business = new ArrayList<PaymentDTO>();
			List <Object[]>businessList =  userRepository.getAllAdminPayments();
			if (null != businessList) {
				try {
					for (Object [] objArray : businessList) {
						PaymentDTO bTypeDTO = new PaymentDTO ();

						if (objArray[0] != null)
							bTypeDTO.setUserName(objArray[0].toString());
						if (objArray[1] != null)
							bTypeDTO.setRole(objArray[1] .toString());
						if (objArray[2] != null)
							bTypeDTO.setDescription(objArray[2].toString());
						if (objArray[3] != null)
							bTypeDTO.setAmount(objArray[3] .toString());
						if (objArray[4] != null)
								bTypeDTO.setTransactionDate(objArray[4].toString());
						if (objArray[5] != null)
									bTypeDTO.setStatus(objArray[5].toString());
						if (objArray[6] != null) {
							/* Country country = countryRepository.findCountryByDialingCode(objArray[6].toString());
							if(country != null) {
								 System.out.println("country : "+country.getName());

								 bTypeDTO.setCountry(country);
								 bTypeDTO.setCountryName(country.getName());
							}*/
							
							List<Country> country = countryRepository.findCountryByDialingCode(objArray[6].toString());
							if(country!=null && country.size()>0)
							{
								bTypeDTO.setCountryName(country.get(0).getName());
								bTypeDTO.setCountry(country.get(0));
								System.out.println("country NAME : "+bTypeDTO.getCountryName());
							}


						}
						business.add(bTypeDTO);
						}


					}

					catch (Exception e) {
						e.printStackTrace();
					}
			}
			return business;
		 }



	public  List<BusinessAdvertDTO> getAllAdverts(){

		 List<BusinessAdvertDTO> business = new ArrayList<BusinessAdvertDTO>();
			List <Object[]>businessList =  businessListingRepository.getAllAdverts();
			if (null != businessList) {
				try {
					for (Object [] objArray : businessList) {
						BusinessAdvertDTO bTypeDTO = new BusinessAdvertDTO ();
						if (objArray[0] != null)
							bTypeDTO.setId(Long.parseLong(objArray[0].toString()));
						if (objArray[1] != null)
							bTypeDTO.setAdvertId(objArray[1].toString());
						if (objArray[2] != null)
							bTypeDTO.setTitle(objArray[2].toString());

						if (objArray[3] != null)
							bTypeDTO.setStatus(objArray[3].toString());
						if (objArray[4] != null)
								bTypeDTO.setRole(objArray[4].toString());
						if (objArray[5] != null)
									bTypeDTO.setCreatedOn(objArray[5].toString());
						if (objArray[6] != null)
							bTypeDTO.setInvestmentRangeFrom(objArray[6].toString());
						if (objArray[7] != null)
							bTypeDTO.setInvestmentRangeTo(objArray[7].toString());
								/*
								 * if (objArray[7] != null) bTypeDTO.setCreatedOn(objArray[6].toString());
								 */
								/*
								 * Country country =
								 * countryRepository.findCountryByDialingCode(bTypeDTO.getIsoCode());
								 * bTypeDTO.setCountryName(country.getName());
								 * System.out.println("country NAME : "+bTypeDTO.getCountryName());
								 *
								 */
						business.add(bTypeDTO);
						}


					}

					catch (Exception e) {
						e.printStackTrace();
					}
			}
			return business;
		 }



	public PageBusinessListingDto getTopBusinessListings(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		HttpSession session = getSession();
		AppUser appUser = (AppUser) session.getAttribute("appUser");
		
		Long userId = (appUser != null) ? appUser.getId() : null;

		List<BusinessListingDTO> business = new ArrayList<>();
		Page<BusinessListingQueryDTO> businessListQueryResponse;
		List<FavouriteBusinessDetailsCount> queryBusinessResponseListFavCount;


		// userId only used to fetch favorite businesses (if user is logged in)
		if(userId == null)
			businessListQueryResponse = fileRepository.getTopBusinessListings(pageable);
		else
			businessListQueryResponse = fileRepository.getTopBusinessListingsByUser(userId,pageable);
		
		    queryBusinessResponseListFavCount = fileRepository.getFavouriteBusinessCountByBusinessIdForBusinessList();


		BusinessListingDTO businessListingDTO;
		for (BusinessListingQueryDTO queryResponse : businessListQueryResponse.getContent()) {
			businessListingDTO = new BusinessListingDTO();
			businessListingDTO.setFilePath(queryResponse.getFilepath());
			businessListingDTO.setTitle(queryResponse.getTitle());
			businessListingDTO.setDescription(queryResponse.getListingdescription());
			businessListingDTO.setPrice(queryResponse.getTotalbusinesssaleprice());
			businessListingDTO.setSuggestedTitle(queryResponse.getSuggestedtitle());
			businessListingDTO.setBusinessListingId(queryResponse.getBusinessid() != null ? queryResponse.getBusinessid().toString() : null);
			businessListingDTO.setAddedToFavourites(queryResponse.getAddedToFavourites() != null && queryResponse.getAddedToFavourites());
//			if (null != objArray[6])
//				businessListingDTO.setCountryName((objArray[6].toString()));

			List<FavouriteBusinessDetailsCount> favouriteBusinessDetailsCountList = queryBusinessResponseListFavCount.stream()
					.filter(item -> Objects.equals(item.getBusinessListingId(), queryResponse.getBusinessid()))
					.collect(Collectors.toList());

			if(!favouriteBusinessDetailsCountList.isEmpty()) {
				businessListingDTO.setFavCount(favouriteBusinessDetailsCountList.get(0).getCount());
			}
			business.add(businessListingDTO);
		}
		Page<BusinessListingDTO> businessByFiltersPage = new PageImpl<>(business, pageable, business.size());
		int totalPages = businessListQueryResponse.getTotalPages();
		int currentPage = businessByFiltersPage.getNumber();
		int pageSize = businessByFiltersPage.getSize();

		return new PageBusinessListingDto(business,totalPages,currentPage,pageSize);
	}



	public List<BusinessListingDTO> topCommercialListings()
	{
		HttpSession session = getSession();
		AppUser appUser = (AppUser) session.getAttribute("appUser");

		Long userId = (appUser != null) ? appUser.getId() : null;

		List<BusinessListingDTO> business = new ArrayList<>();
		List<TopBusinessListingDetails> queryBusinessResponseList;
		List<FavouriteBusinessDetailsCount> queryBusinessResponseListFavCount;

		// userId only used to fetch favorite businesses (if user is logged in)
		if(userId == null)
			queryBusinessResponseList = fileRepository.topCommercialListings();
		else
			queryBusinessResponseList = fileRepository.topCommercialListingsByUser(userId);
		    queryBusinessResponseListFavCount = fileRepository.getFavouriteBusinessCountByBusinessIdForCommercialList();

		BusinessListingDTO businessListingDTO;
		for (TopBusinessListingDetails queryResponse : queryBusinessResponseList)
		{
			businessListingDTO = new BusinessListingDTO();
			businessListingDTO.setFilePath(queryResponse.getFilePath());
			businessListingDTO.setTitle(queryResponse.getTitle());
			businessListingDTO.setDescription(queryResponse.getListingDescription());
			businessListingDTO.setPrice(queryResponse.getTotalBusinessSalePrice());
			businessListingDTO.setSuggestedTitle(queryResponse.getSuggestedTitle());
			List<FavouriteBusinessDetailsCount> favouriteBusinessDetailsCountList = queryBusinessResponseListFavCount.stream()
					.filter(item -> Objects.equals(item.getBusinessListingId(), queryResponse.getBusinessId()))
					.collect(Collectors.toList());

			if(!favouriteBusinessDetailsCountList.isEmpty()) {
				businessListingDTO.setFavCount(favouriteBusinessDetailsCountList.get(0).getCount());
			}
			businessListingDTO.setBusinessListingId(queryResponse.getBusinessId() != null ? queryResponse.getBusinessId().toString() : null);
			businessListingDTO.setAddedToFavourites(queryResponse.getAddedToFavourites() != null && queryResponse.getAddedToFavourites());
			business.add(businessListingDTO);
		}

		System.out.println("businessList recent : " + business.size());
		return business;
	}

	public List<BusinessListingDTO> topFranchisieListings() {

		HttpSession session = getSession();
		AppUser appUser = (AppUser) session.getAttribute("appUser");

		Long userId = (appUser != null) ? appUser.getId() : null;

		List<BusinessListingDTO> business = new ArrayList<>();
		List<TopBusinessListingDetails> queryBusinessResponseList;
		List<FavouriteBusinessDetailsCount> queryBusinessResponseListFavCount;


		// userId only used to fetch favorite businesses (if user is logged in)
		if(userId == null)
			queryBusinessResponseList = fileRepository.topFranchesieListings();
		else
			queryBusinessResponseList = fileRepository.topFranchesieListingsByUser(userId);
		    queryBusinessResponseListFavCount = fileRepository.getFavouriteBusinessCountByBusinessIdForFranchesieList();

		BusinessListingDTO businessListingDTO;
		for (TopBusinessListingDetails queryResponse : queryBusinessResponseList)
		{
			businessListingDTO = new BusinessListingDTO();
			businessListingDTO.setFilePath(queryResponse.getFilePath());
			businessListingDTO.setTitle(queryResponse.getTitle());
			businessListingDTO.setDescription(queryResponse.getListingDescription());
			businessListingDTO.setPrice(queryResponse.getTotalBusinessSalePrice());
			businessListingDTO.setSuggestedTitle(queryResponse.getSuggestedTitle());
			businessListingDTO.setBusinessListingId(queryResponse.getBusinessId() != null ? queryResponse.getBusinessId().toString() : null);
			businessListingDTO.setAddedToFavourites(queryResponse.getAddedToFavourites() != null && queryResponse.getAddedToFavourites());
//			if (null != businessListingDTO.get)
//				businessListingDTO.setCountryName((objArray[6].toString()));


			List<FavouriteBusinessDetailsCount> favouriteBusinessDetailsCountList = queryBusinessResponseListFavCount.stream()
					.filter(item -> Objects.equals(item.getBusinessListingId(), queryResponse.getBusinessId()))
					.collect(Collectors.toList());

			if(!favouriteBusinessDetailsCountList.isEmpty()) {
				businessListingDTO.setFavCount(favouriteBusinessDetailsCountList.get(0).getCount());
			}

			business.add(businessListingDTO);
		}

		System.out.println("businessList recent : " + business.size());
		return business;
	}


		public List<BusinessListingDTO> getTopBusinessListingsByCategory(List<String> catId, String listingType, String prefredCountryId) {
			List<BusinessListingDTO> business = new ArrayList<BusinessListingDTO>();
			List<Long> longList = new ArrayList<>();
			if(catId != null && !catId.isEmpty()) {
				for (String str : catId) {
					longList.add(Long.parseLong(str));
				}
			}
			List<Object[]> businessList = fileRepository.getTopBusinessListingsByCategory(longList,listingType);
			if (null != businessList) {
				try {
					for (Object[] objArray : businessList) {
						BusinessListingDTO businessListingDTO = new BusinessListingDTO();
						if (null != objArray[0])
							businessListingDTO.setFilePath(objArray[0].toString());
						if (null != objArray[1])
							businessListingDTO.setTitle(objArray[1].toString());
						if (null != objArray[2])
							businessListingDTO.setDescription(objArray[2].toString());
						if (null != objArray[3])
							businessListingDTO.setPrice(Double.parseDouble(objArray[3] + ""));
						if (null != objArray[4])
							businessListingDTO.setSuggestedTitle(objArray[4].toString());
						if (null != objArray[5])
							businessListingDTO.setBusinessListingId(objArray[5].toString());
						if (null != objArray[6])
							businessListingDTO.setCountryName(objArray[6].toString());
						if (null != objArray[7])
							businessListingDTO.setCountryId(Long.parseLong(objArray[7].toString()));

						if(prefredCountryId != null && !prefredCountryId.trim().isEmpty()){
								if(businessListingDTO.getCountryId().toString().equals(prefredCountryId)){
									business.add(businessListingDTO);
								}
						}else {
							business.add(businessListingDTO);
						}
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("businessList recent : " + businessList.size());
			} else {
				System.out.println("businessList recent: NULL");
			}
			return business;
		}





		public  List<UserDTO> getAdminUsers(){

			 List<UserDTO> business = new ArrayList<UserDTO>();
				List <Object[]>businessList =  userRepository.getAdminUsers();
				if (null != businessList) {
					try {
						for (Object [] objArray : businessList) {
							UserDTO bTypeDTO = new UserDTO ();
							if (objArray[0] != null)
								bTypeDTO.setId(Long.parseLong(objArray[0].toString()));
							if (objArray[1] != null)
								bTypeDTO.setName(objArray[1].toString());
							if (objArray[2] != null)
								bTypeDTO.setEmail(objArray[2].toString());
							if (objArray[3] != null)
								bTypeDTO.setCountry(objArray[3].toString());
							if (objArray[4] != null)
								bTypeDTO.setRole(null);
							/*
							 * if (objArray[5] != null)
							 * bTypeDTO.setBusinessListingStatus(objArray[5].toString()); if (objArray[6] !=
							 * null) bTypeDTO.setUserRole(objArray[6].toString()); if (objArray[7] != null)
							 * bTypeDTO.setCreatedOn(objArray[6].toString());
							 */
										if(bTypeDTO.getCountry() != null)		{
											/*Country country = countryRepository.findCountryByDialingCode(bTypeDTO.getCountry());
											bTypeDTO.setCountry(country.getName());
											System.out.println("country NAME : "+bTypeDTO.getCountry());*/
											
											List<Country> country = countryRepository.findCountryByDialingCode(bTypeDTO.getCountry());
											if(country!=null && country.size()>0)
											{
												bTypeDTO.setCountry(country.get(0).getName());												
											}
										}

								business.add(bTypeDTO);
							}


						}

						catch (Exception e) {
							e.printStackTrace();
						}
				}
				return business;
			 }


	public List<BusinessListingDTO> getFavouriteBusiness() {
		List<BusinessListingDTO> business = new ArrayList<>();
		HttpSession session = getSession();
		AppUser appUser = (AppUser) session.getAttribute("appUser");

		if(appUser == null)
			return Collections.emptyList();

		List<FavouriteBusinessDetails> businessList = fileRepository.getFavouriteBusiness(appUser.getId());

		if (!CollectionUtils.isEmpty(businessList))
		{
			BusinessListingDTO businessListingDTO;
			for (FavouriteBusinessDetails favouriteBusinessDetails : businessList)
			{
				businessListingDTO = new BusinessListingDTO();
				businessListingDTO.setFilePath(favouriteBusinessDetails.getFilePath());
				businessListingDTO.setTitle(favouriteBusinessDetails.getTitle());
				businessListingDTO.setDescription(favouriteBusinessDetails.getListingDescription());
				businessListingDTO.setPrice(favouriteBusinessDetails.getTotalBusinessSalePrice());
				businessListingDTO.setBusinessListingId(favouriteBusinessDetails.getBusinessListingId());
				business.add(businessListingDTO);
			}
			System.out.println("businessList recent : " + business.size());
		} else {
			System.out.println("businessList recent: NULL");
		}
		return business;
	}

	 public static HttpSession getSession() {
		    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		    return attr.getRequest().getSession(true); // true == allow create
		}

	public void  updateBusinessListing(@RequestBody BusinessListingDTO  bListingDTO) {
		System.out.println("bListingDTO.getBusinessListingStatus() "+bListingDTO.getBusinessListingStatus() + "Id : "+bListingDTO.getId());
		BusinessListing listing = businessListingRepository.findById(bListingDTO.getId()).get();
		listing.setBusinessListingStatus(bListingDTO.getBusinessListingStatus());
		businessListingRepository.save(listing);

	}


	public List<BusinessListing> getBusineeListingbyListingId(long id) {
		List<BusinessListing> result = new ArrayList<BusinessListing>();
		List <Long> list = new ArrayList<Long>();
		list.add(id);
		result = businessListingRepository.findAllById(list);
		 System.out.println (" List size ######## : "+result.size());
		 List<FileEntity> fileEntities = fileEntityRepositiory.findByListingId(result.get(0));
		 System.out.println (" List size ######## : "+result.size());

		 List<BusinessListingFeatureInfo> infoList = fileEntityRepositiory.findBusinessListingFeatureInfoByListingId(result.get(0));
		 System.out.println (" infoList size ######## : "+infoList.size());
		 if (result.size() >0) {
			 System.out.println(" Price ########"+result.get(0).getBusinessListingOutLet().getTotalBusinessSalePrice());
			 result.get(0).setListingGallery(fileEntities);
			 result.get(0).setBusinessListingFeatureInfoList(infoList);
			 System.out.println(" getBusinessTurnover() ########"+result.get(0).getBusinessListingOutLet().getBusinessTurnover());
			 System.out.println(" getBusinessTotalExpenses() ########"+result.get(0).getBusinessListingOutLet().getBusinessTotalExpenses());
			 System.out.println(" getNetProfit()() ########"+result.get(0).getBusinessListingOutLet().getNetProfit());
			 System.out.println(" getGrossProfit()() ########"+result.get(0).getBusinessListingOutLet().getGrossProfit());
		 }
		 return result;

	}


	public List<TestimonialDTO> getTestimonials (){
	List<TestimonialDTO> business = new ArrayList<TestimonialDTO>();
	List <Object[]>testList =  fileRepository.getTestimonials();
	if (null != testList) {
		try {
			for (Object [] objArray : testList) {
				TestimonialDTO testimonialDTO = new TestimonialDTO ();
				if (null != objArray[0])
					testimonialDTO.setId(Long.parseLong(objArray[0].toString()));
				if (null != objArray[1])
					testimonialDTO.setUserName(objArray[1].toString());
				if (null != objArray[2])
					testimonialDTO.setAboutUser(objArray[2].toString());
				if (null != objArray[3])
					testimonialDTO.setDescription(objArray[3].toString());


				business.add(testimonialDTO);

			}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("testimonial : "+business.size());
		}
		else {
			System.out.println("testimonial : NULL");
		}
		return business;
		}


	public List<TestimonialDTO> getAdminTestimonials (){
		List<TestimonialDTO> business = new ArrayList<TestimonialDTO>();
		List <Object[]>testList =  tobuzFeatureRepository.getAdminTestimonials();
		if (null != testList) {
			try {
				for (Object [] objArray : testList) {
					TestimonialDTO testimonialDTO = new TestimonialDTO ();
					if (null != objArray[0])
						testimonialDTO.setId(Long.parseLong(objArray[0].toString()));
					if (null != objArray[1])
						testimonialDTO.setUserName(objArray[1].toString());
					if (null != objArray[2])
						testimonialDTO.setAboutUser(objArray[2].toString());
					if (null != objArray[3])
						testimonialDTO.setDescription(objArray[3].toString());
					if (null != objArray[4])
						testimonialDTO.setEmail( objArray[4].toString());


					business.add(testimonialDTO);

				}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("testimonial : "+business.size());
			}
			else {
				System.out.println("testimonial : NULL");
			}
			return business;
			}



	public List<CategoryDTO> getCategories (){
		List<CategoryDTO> business = new ArrayList<CategoryDTO>();
		List <Object[]>testList =  categoryRepository.getCategories();
		if (null != testList) {
			try {
				for (Object [] objArray : testList) {
					CategoryDTO categoryDTO = new CategoryDTO ();
					if (null != objArray[0])
						categoryDTO.setId(Long.parseLong(objArray[0].toString()));
					if (null != objArray[1])
						categoryDTO.setName(objArray[1].toString());
					if (null != objArray[2])
						categoryDTO.setSequence(objArray[2].toString());
					if (null != objArray[3])
						categoryDTO.setFeaturedCategory(objArray[3].toString());

					business.add(categoryDTO);

				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("testimonial : "+business.size());
		}
		else {
			System.out.println("testimonial : NULL");
		}
		return business;
		}



	public List<TobuzPackageDTO> getTobuzPackagesBySearchKey (){
		List<TobuzPackageDTO> business = new ArrayList<TobuzPackageDTO>();
		List <Object[]>testList =  tobuzPackageRepository.getTobuzPackagesBySearchKey();
		if (null != testList) {
			try {
				for (Object [] objArray : testList) {
					TobuzPackageDTO tobuzPackageDTO = new TobuzPackageDTO ();
					if (null != objArray[0])
						tobuzPackageDTO.setId(Long.parseLong(objArray[0].toString()));
					if (null != objArray[1])
						tobuzPackageDTO.setPackageType(objArray[1].toString());
					if (null != objArray[2])
						tobuzPackageDTO.setExpiryPeriodInMonths(objArray[2].toString());
					if (null != objArray[3])
						tobuzPackageDTO.setCost(objArray[3].toString());
					if (null != objArray[4])
						tobuzPackageDTO.setUserRole(objArray[4].toString());

					business.add(tobuzPackageDTO);

				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("testimonial : "+business.size());
		}
		else {
			System.out.println("testimonial : NULL");
		}
		return business;
		}
	public List<TobuzfeatureDTO> getTobuzFeatures (){
		List<TobuzfeatureDTO> business = new ArrayList<TobuzfeatureDTO>();
		List <Object[]>testList =  tobuzPackageRepository.getTobuzFeatures();
		if (null != testList) {
			try {
				for (Object [] objArray : testList) {
					TobuzfeatureDTO tobuzPackageDTO = new TobuzfeatureDTO ();
					if (null != objArray[0])
						tobuzPackageDTO.setId(Long.parseLong(objArray[0].toString()));
					if (null != objArray[1])
						tobuzPackageDTO.setTitle(objArray[1].toString());
					business.add(tobuzPackageDTO);

				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("testimonial : "+business.size());
		}
		else {
			System.out.println("testimonial : NULL");
		}
		return business;
		}


	public List<TobuzPackageDTO> getTobuzPackagesById (long id ){
		List<TobuzPackageDTO> business = new ArrayList<TobuzPackageDTO>();
		List <Object[]>testList =  tobuzPackageRepository.getTobuzPackagesById(id);
		if (null != testList) {
			try {
				for (Object [] objArray : testList) {
					TobuzPackageDTO tobuzPackageDTO = new TobuzPackageDTO ();
					if (null != objArray[0]) {
						 Optional<Country> couOptional = countryRepository.findById(Long.parseLong( objArray[0].toString()));
						 Country country = couOptional.get();
						 tobuzPackageDTO.setCountry(country);
						 tobuzPackageDTO.setCountryName(country.getName());
					}

					if (null != objArray[1])
						tobuzPackageDTO.setUserRole(objArray[1].toString());
					if (null != objArray[2])
						tobuzPackageDTO.setPackageType(objArray[2].toString());
					if (null != objArray[3])
						tobuzPackageDTO.setDescription(objArray[3].toString());
					if (null != objArray[4])
						tobuzPackageDTO.setCost(objArray[4].toString());
					if (null != objArray[5])
						tobuzPackageDTO.setExpiryPeriodInMonths(objArray[5].toString());
					if (null != objArray[6])
						tobuzPackageDTO.setSequence(Integer.parseInt(objArray[6].toString()));
					if (null != objArray[7])
						tobuzPackageDTO.setAdvertListCount(Integer.parseInt(objArray[7].toString()));
					if (null != objArray[8])
						tobuzPackageDTO.setFileUploadCount(objArray[8].toString());
					if (null != objArray[9])
						tobuzPackageDTO.setNoOfContactAccess(objArray[9].toString());

					business.add(tobuzPackageDTO);

				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("testimonial : "+business.size());
		}
		else {
			System.out.println("testimonial : NULL");
		}
		return business;
		}




	public CategoryDTO getTobuzCategoryById (long id ){
		List<CategoryDTO> business = new ArrayList<CategoryDTO>();
		CategoryDTO categoryDTO = new CategoryDTO ();

			 Optional<Category> couOptional = categoryRepository.findById(id);
			 Category category = couOptional.get();

			if(null != category) {
				categoryDTO.setSequence(category.getSequence()+"");
				categoryDTO.setFeaturedCategory(category.getIsFeaturedCategory()+"");

				if(null != category.getImageId()) {
					categoryDTO.setImageId(category.getImageId());
					Optional<FileEntity> opts = fileEntityRepositiory.findById(category.getImageId());
					FileEntity image = opts.get();
					categoryDTO.setImagePath(image.getFilePath());

				}
				categoryDTO.setIsCommercialCategory(category.getIsCommercialCategory()+"");
				categoryDTO.setName(category.getName());
				categoryDTO.setId(category.getId());
				List<SubCategory> list = subCategoryRepository.findSubCategotyByCategory(category);
				System.out.println("Subcatgory list  :"+list);
				List<SubCategoryDTO> suBLists = new ArrayList<SubCategoryDTO>();
				if(null != list) {
					for(SubCategory subCategory : list) {
						SubCategoryDTO subCategoryDTO = new SubCategoryDTO ();
						subCategoryDTO.setId(subCategory.getId());
						subCategoryDTO.setName(subCategory.getName());

						suBLists.add(subCategoryDTO);
						categoryDTO.setSubCategoryList(suBLists);
					}
				}

			}

		return categoryDTO;
		}


	public List<TobuzfeatureDTO> getTobuzFeatureById (long id ){
		List<TobuzfeatureDTO> business = new ArrayList<TobuzfeatureDTO>();
		List <Object[]>testList =  tobuzPackageRepository.getTobuzFeatureById(id);
		if (null != testList) {
			try {
				for (Object [] objArray : testList) {
					TobuzfeatureDTO tobuzPackageDTO = new TobuzfeatureDTO ();
					if (null != objArray[2]) {
						 Optional<Country> couOptional = countryRepository.findById(Long.parseLong( objArray[0].toString()));
						 Country country = couOptional.get();
						 tobuzPackageDTO.setCountry(country);
						 tobuzPackageDTO.setCountryName(country.getName());
					}

					if (null != objArray[0])
						tobuzPackageDTO.setId(Long.parseLong(objArray[0].toString()));
					if (null != objArray[1])
						tobuzPackageDTO.setTitle(objArray[1].toString());
					if (null != objArray[4])
						tobuzPackageDTO.setDescription(objArray[4].toString());
					if (null != objArray[3])
						tobuzPackageDTO.setUserRole(objArray[3].toString());

					business.add(tobuzPackageDTO);

				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("testimonial : "+business.size());
		}
		else {
			System.out.println("testimonial : NULL");
		}
		return business;
		}



	public List<TobuzPackageDTO> getTobuzPackagesByUser (String user ){
		List<TobuzPackageDTO> business = new ArrayList<TobuzPackageDTO>();
		List <Object[]>testList =  tobuzPackageRepository.getTobuzPackagesByUser(user);
		if (null != testList) {
			try {
				for (Object [] objArray : testList) {
					TobuzPackageDTO tobuzPackageDTO = new TobuzPackageDTO ();
					if (null != objArray[0])
						tobuzPackageDTO.setId(Long.parseLong(objArray[0].toString()));
					if (null != objArray[1])
						tobuzPackageDTO.setPackageType(objArray[1].toString());
					if (null != objArray[2])
						tobuzPackageDTO.setExpiryPeriodInMonths(objArray[2].toString());
					if (null != objArray[3])
						tobuzPackageDTO.setCost(objArray[3].toString());
					if (null != objArray[4])
						tobuzPackageDTO.setUserRole(objArray[4].toString());


					business.add(tobuzPackageDTO);

				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("testimonial : "+business.size());
		}
		else {
			System.out.println("testimonial : NULL");
		}
		return business;
		}

	public List<TobuzfeatureDTO> getTobuzFeaturesByUser (String user ){
		List<TobuzfeatureDTO> business = new ArrayList<TobuzfeatureDTO>();
		List <Object[]>testList =  tobuzPackageRepository.getTobuzFeaturesByUser(user);
		if (null != testList) {
			try {
				for (Object [] objArray : testList) {
					TobuzfeatureDTO tobuzPackageDTO = new TobuzfeatureDTO ();
					if (null != objArray[0])
						tobuzPackageDTO.setId(Long.parseLong(objArray[0].toString()));
					if (null != objArray[1])
						tobuzPackageDTO.setTitle(objArray[1].toString());
					business.add(tobuzPackageDTO);

				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("testimonial : "+business.size());
		}
		else {
			System.out.println("testimonial : NULL");
		}
		return business;
		}

	public List<MessageDTO> getAdminMessages (){
		List<MessageDTO> business = new ArrayList<MessageDTO>();
		List <Object[]>testList =  messageRepository.getAdminMessages();
		if (null != testList) {
			try {
				for (Object [] objArray : testList) {
					MessageDTO messageDTO = new MessageDTO ();
					if (null != objArray[0])
						messageDTO.setId(Long.parseLong(objArray[0].toString()));
					if (null != objArray[1])
						messageDTO.setName(objArray[1].toString());
					if (null != objArray[2])
						messageDTO.setEmail(objArray[2].toString());
					if (null != objArray[3])
						messageDTO.setDialingCode(objArray[3].toString());
					if (null != objArray[4])
						messageDTO.setMobile(objArray[4].toString());
					if (null != objArray[5])
						messageDTO.setCreatedOn(objArray[5].toString());
					business.add(messageDTO);

				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("contacts : "+business.size());
		}
		else {
			System.out.println("contacts : NULL");
		}
		return business;
		}


	public List<ContactDTO> getAdminContactus (){
		List<ContactDTO> business = new ArrayList<ContactDTO>();
		List <Object[]>testList = contactUsRepository.getAdminContactus();
		if (null != testList) {
			try {
				for (Object [] objArray : testList) {
					ContactDTO contactDTO = new ContactDTO ();
					if (null != objArray[0])
						contactDTO.setName(objArray[0].toString());
					if (null != objArray[1])
						contactDTO.setEmail(objArray[1].toString());
					if (null != objArray[2])
						contactDTO.setPhone(objArray[2].toString());
					if (null != objArray[3])
						contactDTO.setCity(objArray[3].toString());
					if (null != objArray[4])
						contactDTO.setMessage(objArray[4].toString());

					business.add(contactDTO);

				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("messages : "+business.size());
		}
		else {
			System.out.println("messages : NULL");
		}
		return business;
		}


	public List<UserRequestDTO> getAdminUserRequests (){
		List<UserRequestDTO> business = new ArrayList<UserRequestDTO>();
		List <Object[]>testList = userRepository.getAdminUserRequests();
		if (null != testList) {
			try {
				for (Object [] objArray : testList) {
					UserRequestDTO userRequestDTO = new UserRequestDTO ();
					if (null != objArray[0])
						userRequestDTO.setName(objArray[0].toString());
					if (null != objArray[1])
						userRequestDTO.setCompanyName(objArray[1].toString());
					if (null != objArray[2])
						userRequestDTO.setEmail(objArray[2].toString());
					if (null != objArray[3])
						userRequestDTO.setMesssage(objArray[3].toString());
					if (null != objArray[4])
						userRequestDTO.setPhoneNumber(objArray[4].toString());
					if (null != objArray[5])
						userRequestDTO.setBusinessFunding(objArray[5].toString());
					if (null != objArray[6])
						userRequestDTO.setCreatedOn( objArray[6].toString());
					if (null != objArray[7])
						userRequestDTO.setBusiness_status(objArray[7].toString());

					business.add(userRequestDTO);

				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("AdminUserRequests : "+business.size());
		}
		else {
			System.out.println("AdminUserRequests : NULL");
		}
		return business;
		}


	public List<ContactUs>findContactByEmailId(String email)
	{
		return contactUsRepository.findByEmail(email);
	}
	
	public List<UserContactInfo>findUserContactByEmailIdAndBusinessId(String email,Long businessId)
	{
		return userContactInfoRepository.findByEmailAndBusinessId(email,businessId);
	}

	public Long saveContact (ContactDTO contactDTO) {
		
		ContactUs contactUs =new ContactUs();
		contactUs.setCity(contactDTO.getCity());
		contactUs.setName(contactDTO.getName());
		contactUs.setEmail(contactDTO.getEmail());
		contactUs.setMessage(contactDTO.getMessage());
		contactUs.setPhone(contactDTO.getPhone());
		contactUs.setCreatedOn( new Timestamp(contactDTO.getCreatedOn().getTime()));
		contactUs.setLastUpdate( new Timestamp(contactDTO.getLastUpdate().getTime()));
		contactUs = contactUsRepository.save(contactUs);
		return contactUs.getId();
	}
	
	public Long saveUserContact (ContactDTO contactDTO) {
		
		UserContactInfo contactUs =new UserContactInfo();
		contactUs.setUserName(contactDTO.getName());
		contactUs.setEmail(contactDTO.getEmail());
		contactUs.setMessage(contactDTO.getMessage());
		contactUs.setMobileNumber(contactDTO.getPhone());
		contactUs.setBusinessListingId(contactDTO.getBusinessId());
		contactUs.setCreatedOn( new Timestamp(contactDTO.getCreatedOn().getTime()));
		contactUs.setLastUpdate( new Timestamp(contactDTO.getLastUpdate().getTime()));
		contactUs = userContactInfoRepository.save(contactUs);
		return contactUs.getId();
	}

	public ContactUs saveContactus(ContactUs c)
	{
		return contactUsRepository.save(c);
	}


	public void updateAdminPackage (TobuzPackageDTO  tobuzPackageDTO) {

		Optional<TobuzPackage>  tobuzPackageOPT = tobuzPackageRepository.findById(tobuzPackageDTO.getId());
		TobuzPackage tobuzPackage = tobuzPackageOPT.get();
		tobuzPackage.setAdvertListCount(tobuzPackageDTO.getAdvertListCount());
		if(tobuzPackageDTO.getCost() != null)
		tobuzPackage.setCost(Float.parseFloat(tobuzPackageDTO.getCost()));
		tobuzPackage.setDescription(tobuzPackageDTO.getDescription());
		if(tobuzPackageDTO.getExpiryPeriodInMonths() != null) {
			tobuzPackage.setExpiryPeriodInMonths(Integer.parseInt(tobuzPackageDTO.getExpiryPeriodInMonths() ));
		}
		if(tobuzPackageDTO.getFileUploadCount() != null) {
			tobuzPackage.setFileUploadCount(Integer.parseInt(tobuzPackageDTO.getFileUploadCount()));
		}
		tobuzPackage.setLastUpdate(new Timestamp(new Date().getTime()));
		if(tobuzPackageDTO.getNoOfContactAccess() != null ) {
			tobuzPackage.setNoOfContactsAccess(Integer.parseInt(tobuzPackageDTO.getNoOfContactAccess()));
		}
		tobuzPackage.setSequence(tobuzPackageDTO.getSequence());

		tobuzPackageRepository.save(tobuzPackage);

	}

public void updateAdminPackage (TobuzfeatureDTO  tobuzPackageDTO) {

	 Optional<TobuzPackageService> service = tobuzFeatureRepository.findById(tobuzPackageDTO.getId());
	TobuzPackageService tobuzPackageService = service.get();

	tobuzPackageService.setUserRole(tobuzPackageDTO.getUserRole());
	tobuzPackageService.setTitle(tobuzPackageDTO.getTitle());
	tobuzPackageService.setDescription(tobuzPackageDTO.getDescription());
	tobuzFeatureRepository.save(tobuzPackageService);

	}

public List <BusinessAdvertDTO> getAdvertListingsForTypeAndUser (String listingType) {
	 HttpSession session = getSession();
	 AppUser appUser = (AppUser) session.getAttribute("appUser");
	 List <BusinessAdvertDTO> advertList = new ArrayList<BusinessAdvertDTO>();
	 BusinessAdvertDTO advertDTO = new BusinessAdvertDTO();
	 System.out.println("appUser Id  :" +appUser.getId());

	 List <Object[]>loginInfo =   businessListingRepository.getAdvertListingsForTypeAndUser(appUser.getId(),listingType);
		System.out.println("getAdvertListingsForTypeAndUser :" +loginInfo.size());
		if (null != loginInfo) {
			try {
				for (Object [] objArray : loginInfo) {

						if (null != objArray[0]) {
							advertDTO.setId(Long.parseLong(objArray[0].toString()));
						}

						if (null != objArray[1]) {
							advertDTO.setAdvertId(objArray[1].toString());
						}
						if (null != objArray[2]) {
							advertDTO.setTitle( objArray[2].toString());
						}
						if (null != objArray[3]) {
							advertDTO.setCreatedOn(objArray[3].toString());
						}
						if (null != objArray[4]) {
							advertDTO.setExpiredOn(objArray[4].toString());
						}
						if (null != objArray[5]) {
							advertDTO.setPackag(objArray[5].toString());
						}
						if (null != objArray[6]) {
							advertDTO.setStatus((objArray[6].toString()));
						}
						advertList.add(advertDTO);
					}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Advert Id  :" +advertDTO.getAdvertId());
		}

	 return advertList;

}


public List<MessageDTO> getUserMessages(){
	List<MessageDTO> business = new ArrayList<MessageDTO>();
	 HttpSession session = getSession();
	 AppUser appUser = (AppUser) session.getAttribute("appUser");
	 System.out.println("appUser ID "+appUser.getId());
	List <Object[]>testList =  messageRepository.getUserMessages(appUser.getId());
	if (null != testList) {
		try {
			for (Object [] objArray : testList) {
				MessageDTO messageDTO = new MessageDTO ();
				if (null != objArray[0])
					messageDTO.setId(Long.parseLong(objArray[0].toString()));
				if (null != objArray[1])
					messageDTO.setName(objArray[1].toString());
				if (null != objArray[2])
					messageDTO.setEmail(objArray[2].toString());
				if (null != objArray[3])
					messageDTO.setDialingCode(objArray[3].toString());
				if (null != objArray[4])
					messageDTO.setMobile(objArray[4].toString());
				if (null != objArray[5])
					messageDTO.setCreatedOn(objArray[5].toString());
				if (null != objArray[6])
					messageDTO.setSubject( objArray[6].toString());

				if (null != objArray[7])
					messageDTO.setBodyText(objArray[7].toString());
				business.add(messageDTO);

			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("contacts : "+business.size());
	}
	return business;
}

	public PageBusinessListingDto getBusinessByFilter(BusinessListingDTO businessListingDTO, int page, int size) {

		Boolean isTitleASC = false;
		Boolean isTitleDsc = false;
		Boolean isPriceASC = false;
		Boolean isPriceDSC = false;

		Pageable pageable = PageRequest.of(page, size);

		HttpSession session = getSession();
		AppUser appUser = (AppUser) session.getAttribute("appUser");

		Long userId = (appUser != null) ? appUser.getId() : null;


		if(businessListingDTO.getSortByTitle() != null){
			isTitleASC = businessListingDTO.getSortByTitle();
			isTitleDsc = !businessListingDTO.getSortByTitle();
		}

		if(businessListingDTO.getSortByPrice() != null){
			isPriceDSC = businessListingDTO.getSortByPrice();
			isPriceASC = !businessListingDTO.getSortByPrice();
		}


		List<BusinessListingDTO> businessListingDTOList = new ArrayList<>();
		List<Long> longList = new ArrayList<>();
		if (businessListingDTO.getCategoryIds() != null && !businessListingDTO.getCategoryIds().isEmpty()) {
			for (String str : businessListingDTO.getCategoryIds()) {
				if(!str.trim().isEmpty()) {
					longList.add(Long.parseLong(str));
				}
			}
		}

		List<BusinessByFilter> businessByFilter = null;
		List<FavouriteBusinessDetailsCount> queryBusinessResponseListFavCount = null;

		if (businessListingDTO.getFranchiseType() == null || businessListingDTO.getFranchiseType().isEmpty()) {
			if (longList != null && !longList.isEmpty()) {
				for (Long l : longList) {
					if(Objects.nonNull(userId)){
						businessByFilter = fileEntityRepositiory.getBusinessByUserFilter(l, businessListingDTO.getListingType(), isTitleASC, isTitleDsc, isPriceASC, isPriceDSC, businessListingDTO.getSearchKey(),userId);
//						queryBusinessResponseListFavCount = fileRepository.getFavouriteBusinessCountByBusinessType(businessListingDTO.getListingType(),businessListingDTO.getSearchKey());
					}else {
						businessByFilter = fileEntityRepositiory.getBusinessByFilter(l, businessListingDTO.getListingType(), isTitleASC, isTitleDsc, isPriceASC, isPriceDSC, businessListingDTO.getSearchKey());
					}
					getBusinessListDto(businessByFilter, businessListingDTOList,queryBusinessResponseListFavCount);
				}
			} else {
				if(Objects.nonNull(userId)){
					businessByFilter = fileEntityRepositiory.getBusinessByUserFilter(businessListingDTO.getListingType(), isTitleASC, isTitleDsc, isPriceASC, isPriceDSC, businessListingDTO.getSearchKey(),userId);
//					queryBusinessResponseListFavCount = fileRepository.getFavouriteBusinessCountByBusinessType(businessListingDTO.getListingType(),businessListingDTO.getSearchKey());
				}else{
					businessByFilter = fileEntityRepositiory.getBusinessByFilter(businessListingDTO.getListingType(),isTitleASC, isTitleDsc, isPriceASC, isPriceDSC, businessListingDTO.getSearchKey());
				}
				getBusinessListDto(businessByFilter, businessListingDTOList,queryBusinessResponseListFavCount);
			}
		} else {
			if (longList != null && !longList.isEmpty()) {
				for (Long l : longList) {
					for (String fType : businessListingDTO.getFranchiseType()) {
						if(Objects.nonNull(userId)) {
							businessByFilter = fileEntityRepositiory.getBusinessWithFranchiseUserFilter(l, businessListingDTO.getListingType(), fType,isTitleASC, isTitleDsc, isPriceASC, isPriceDSC, businessListingDTO.getSearchKey(),userId);
//							queryBusinessResponseListFavCount = fileRepository.getFavouriteBusinessCountByBusinessType(businessListingDTO.getListingType(),businessListingDTO.getSearchKey());
						}else {
							businessByFilter = fileEntityRepositiory.getBusinessWithFranchiseFilter(l, businessListingDTO.getListingType(), fType,isTitleASC, isTitleDsc, isPriceASC, isPriceDSC, businessListingDTO.getSearchKey());
						}
						getBusinessListDto(businessByFilter, businessListingDTOList,queryBusinessResponseListFavCount);
					}
				}
			} else {
				for (String fType : businessListingDTO.getFranchiseType()) {
					if(Objects.nonNull(userId)) {
						businessByFilter = fileEntityRepositiory.getBusinessWithFranchiseUserFilter(businessListingDTO.getListingType(), fType,isTitleASC, isTitleDsc, isPriceASC, isPriceDSC, businessListingDTO.getSearchKey(),userId);
//						queryBusinessResponseListFavCount = fileRepository.getFavouriteBusinessCountByBusinessType(businessListingDTO.getListingType(),businessListingDTO.getSearchKey());
					}
					else{
						businessByFilter = fileEntityRepositiory.getBusinessWithFranchiseFilter(businessListingDTO.getListingType(), fType,isTitleASC, isTitleDsc, isPriceASC, isPriceDSC, businessListingDTO.getSearchKey());
					}
					getBusinessListDto(businessByFilter, businessListingDTOList,queryBusinessResponseListFavCount);
				}
			}

		}
		Page<BusinessListingDTO> businessByFiltersPage = new PageImpl<>(businessListingDTOList, pageable, businessListingDTOList.size());
		int currentPage = businessByFiltersPage.getNumber();
		int pageSize = businessByFiltersPage.getSize();
		if (businessListingDTO.getCountryIds() != null && !businessListingDTO.getCountryIds().isEmpty() && !businessListingDTOList.isEmpty()) {
			List<Long> countryIdList = new ArrayList<>();
			for (String str : businessListingDTO.getCountryIds()) {
				countryIdList.add(Long.parseLong(str));
			}
			return getPageBusinessListDto(businessListingDTOList.stream().filter(dto -> countryIdList.contains(dto.getCountryId())).collect(Collectors.toList()),currentPage,pageSize);
		} else if (businessListingDTO.getPrefredCountryId() != null) {
			return getPageBusinessListDto(businessListingDTOList.stream().filter(dto -> businessListingDTO.getPrefredCountryId().equals(dto.getCountryId().toString())).collect(Collectors.toList()),currentPage,pageSize);
		}
		return getPageBusinessListDto(businessListingDTOList,currentPage,pageSize);
	}

    private void getBusinessListDto(List<BusinessByFilter> businessByFilter, List<BusinessListingDTO> businessListingDTOList,List<FavouriteBusinessDetailsCount> queryBusinessResponseListFavCount) {
        if (Objects.nonNull(businessByFilter)) {
            for (BusinessByFilter data : businessByFilter) {
				if (data.getPrice() != null) {
					BusinessListingDTO response = new BusinessListingDTO();
					response.setFilePath(data.getFilePath());
					response.setTitle(data.getTitle());
					response.setDescription(data.getListingDescription());
					response.setPrice(data.getPrice());
					response.setSuggestedTitle(data.getSuggestedTitle());
					response.setBusinessListingId(data.getId() != null ? data.getId().toString() : null);
					response.setCountryId(data.getCountryId());
					response.setCountryName(data.getCountryName());
					response.setAddedToFavourites(data.getAddedToFavourites() != null && data.getAddedToFavourites());
//					if(Objects.nonNull(queryBusinessResponseListFavCount) && !queryBusinessResponseListFavCount.isEmpty()) {
//						List<FavouriteBusinessDetailsCount> favouriteBusinessDetailsCountList = queryBusinessResponseListFavCount.stream()
//								.filter(item -> Objects.equals(item.getBusinessListingId(), data.getId()))
//								.collect(Collectors.toList());
//
//						if (Objects.nonNull(favouriteBusinessDetailsCountList) && !favouriteBusinessDetailsCountList.isEmpty()) {
//							response.setFavCount(favouriteBusinessDetailsCountList.get(0).getCount());
//						}
//					}
					businessListingDTOList.add(response);
				}
			}
        }
    }
	@Transactional
	public NewsLetterSubscription saveNewsletter(String email) {
		Integer appUserId = userRepository.getUserIdFromAppUser(email);
		Integer roleId = null;
		if(appUserId!=null){
			roleId = userRepository.getRoleIdFromRole(appUserId);
		}
		Integer cityId = userRepository.getCityIdFromCity(email);

		NewsLetterSubscription newsLetterSubscription = new NewsLetterSubscription();
		newsLetterSubscription.setIsActive(true);
		newsLetterSubscription.setEmail(email);
		newsLetterSubscription.setAppUserId(appUserId);
		newsLetterSubscription.setRoleId(roleId);
		newsLetterSubscription.setCityId(cityId);
		newsLetterSubscription.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
		newsLetterSubscription.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

		return newsLetterRepository.save(newsLetterSubscription);
	}

	public PageBrokerListDto getBrokerList(BrokerListingDTO brokerListingDTO, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		List<BrokerListingDTO> brokerListingDTOS = new ArrayList<>();
		List<Long> ids = new ArrayList<>();
		List<BrokerList> brokerLists = null;
		if (brokerListingDTO.getBusinessServiceIds() != null && !brokerListingDTO.getBusinessServiceIds().isEmpty()) {
			for (String str : brokerListingDTO.getBusinessServiceIds()) {
				ids.add(Long.parseLong(str));
			}
		for (Long l : ids){
			brokerLists = userRepository.getBrokerList(l);
			getBrokerListDTO(brokerLists, brokerListingDTOS);
		}
		}else{
			brokerLists = userRepository.getBrokerList();
			getBrokerListDTO(brokerLists, brokerListingDTOS);
		}

		if(brokerListingDTO.getSortByTitle() != null){
			if(brokerListingDTO.getSortByTitle()){
				Collections.sort(brokerListingDTOS, new Comparator<BrokerListingDTO>() {
					@Override
					public int compare(BrokerListingDTO o1, BrokerListingDTO o2) {
						return (o1.getUserName().toLowerCase().trim().compareTo(o2.getUserName().toLowerCase().trim()));
					}
				});
			}else{
				Collections.sort(brokerListingDTOS, new Comparator<BrokerListingDTO>() {
					@Override
					public int compare(BrokerListingDTO o1, BrokerListingDTO o2) {
						return (o2.getUserName().toLowerCase().trim().compareTo(o1.getUserName().toLowerCase().trim()));
					}
				});
			}
		}
//		int totalPages = brokerLists!=null ? brokerLists.getTotalPages() : 0;
//		int currentPage = brokerLists.getNumber();
//		int pageSize = brokerLists.getSize();

		List<Long> countryIdList = new ArrayList<>();
		if(brokerListingDTO.getCountryIds() != null && !brokerListingDTO.getCountryIds().isEmpty()) {
			for (String str : brokerListingDTO.getCountryIds()) {
				if(str != null && !str.trim().isEmpty()) {
					countryIdList.add(Long.parseLong(str));
				}
			}
		}
		if(!countryIdList.isEmpty() && !brokerListingDTOS.isEmpty()){


//				new PageBrokerListDto(brokerListingDTOS.stream().filter(dto -> countryIdList.contains(dto.getCountryId())).collect(Collectors.toList()), totalPages, currentPage, pageSize);
			PageBrokerListDto pageBrokerListDto = getPageBrokerListDto(brokerListingDTOS.stream().filter(dto -> countryIdList.contains(dto.getCountryId())).collect(Collectors.toList()),page,size);

			return pageBrokerListDto;
		}else if (brokerListingDTO.getPrefredCountryId() != null && !brokerListingDTO.getPrefredCountryId().trim().isEmpty()) {
			return getPageBrokerListDto(brokerListingDTOS.stream().filter(dto -> brokerListingDTO.getPrefredCountryId().equals(dto.getCountryId().toString())).collect(Collectors.toList()),page,size);
		}
			return getPageBrokerListDto(brokerListingDTOS,page,size);
		}

	private PageBrokerListDto getPageBrokerListDto(List<BrokerListingDTO> brokerListingDTOS,int page, int onePageDataSize) {

		int totalPages = (int) Math.ceil((double) brokerListingDTOS.size()/onePageDataSize);
		int startIndex = 0;
		int endIndex = onePageDataSize;
		if(page > 1){
			startIndex = onePageDataSize* (page-1);
			endIndex = startIndex+onePageDataSize;
		}

		return new PageBrokerListDto(brokerListingDTOS.size() >= endIndex+1 ? brokerListingDTOS.subList(startIndex,endIndex) : brokerListingDTOS.subList(startIndex, brokerListingDTOS.size()),totalPages,page,onePageDataSize);

	}

	private void getBrokerListDTO(List<BrokerList> brokerLists, List<BrokerListingDTO> brokerListingDTOS){
		if (Objects.nonNull(brokerLists)){
			for (BrokerList data : brokerLists){
				BrokerListingDTO response = new BrokerListingDTO();
				response.setUserName(data.getUserName());
				response.setMobileNumber(data.getMobileNumber());
				response.setCountryCode(data.getCountryCode());
				response.setStateName(data.getStateName());
				response.setCountryName(data.getCountryName());
				response.setCountryId(data.getCountryId());
				brokerListingDTOS.add(response);
			}
		}

	}

	public PageBrokerListDto getAllBrokerList(int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		List<BrokerListingDTO> list = new ArrayList<>();
		Page<Object[]> allBrokerList = userRepository.getAllBrokerList(pageable);
		if (null != allBrokerList){
			try {
				for (Object[] objects : allBrokerList){
					BrokerListingDTO brokerListingDTO = new BrokerListingDTO();
					if (null != objects[0])
						brokerListingDTO.setUserName(objects[0].toString());
					if (null != objects[1])
						brokerListingDTO.setMobileNumber(objects[1].toString());
					if (null != objects[2])
						brokerListingDTO.setCountryCode(objects[2].toString());
					if (null != objects[3])
						brokerListingDTO.setStateName(objects[3].toString());
					if (null != objects[4])
						brokerListingDTO.setCountryName(objects[4].toString());

					list.add(brokerListingDTO);
				}
			} catch (NumberFormatException e){
				e.printStackTrace();
			}
			System.out.println("broker list recent : " + list.size());
		} else {
			System.out.println("broker list recent: NULL");
		}
		Page<BrokerListingDTO> brokerListingDTOS = new PageImpl<>(list, pageable, list.size());
		int totalPages = allBrokerList!=null ? allBrokerList.getTotalPages() : 0;
		int currentPage = brokerListingDTOS.getNumber();
		int pageSize = brokerListingDTOS.getSize();
		return new PageBrokerListDto(list,totalPages,currentPage,pageSize);
	}


	public List<CategoryDTO> getAllCategoryList(){

		List<CategoryByFilter> categoryByFilterList = new ArrayList<>();
		categoryByFilterList= categoryRepository.getAllCategoryList();
		List<CategoryDTO> categoryDTOList = new ArrayList<>();

		for (CategoryByFilter data : categoryByFilterList) {
			CategoryDTO response = new CategoryDTO();
			response.setId(data.getId());
			response.setName(data.getName());
			response.setCommercial(data.getIsCommercial());
			categoryDTOList.add(response);
		}

		return categoryDTOList;
	}

	public List<Country> getAllCountryList(){

		List<CountryList> countryLists = new ArrayList<>();
		countryLists= countryRepository.getAllCountryList();
		List<Country> countryDTOList = new ArrayList<>();

		for (CountryList data : countryLists) {
			Country response = new Country();
			response.setId(data.getId());
			response.setName(data.getName());
			response.setCurrencyCode(data.getCurrencyCode());
			response.setDialingCode(data.getDialingCode());
			response.setShortName(data.getShortName());
			response.setIsoCode(data.getIsoCode());
			countryDTOList.add(response);
		}

		return countryDTOList;
	}
	
	public PageBusinessServiceTypeDTO getAllBusinessServiseTypeList(int page, int size){
		Pageable pageable = PageRequest.of(page, size);

		Page<BusinessServiseTypeList> businessServiseTypeLists;
		businessServiseTypeLists= businessListingRepository.getAllBusinessServiseTypeList(pageable);
		List<BusinessServiceTypeDTO> businessServiceTypeDTOList = new ArrayList<>();

		for (BusinessServiseTypeList data : businessServiseTypeLists.getContent()) {
			BusinessServiceTypeDTO response = new BusinessServiceTypeDTO();
			response.setId(data.getId());
			response.setServiceType(data.getBusinessServiceType());

			businessServiceTypeDTOList.add(response);
		}
		Page<BusinessServiceTypeDTO> businessServiceTypeDTOS = new PageImpl<>(businessServiceTypeDTOList, pageable, businessServiceTypeDTOList.size());
		int totalPages = businessServiseTypeLists.getTotalPages();
		int currentPage = businessServiceTypeDTOS.getNumber();
		int pageSize = businessServiceTypeDTOS.getSize();
		return new PageBusinessServiceTypeDTO(businessServiceTypeDTOList,totalPages,currentPage,pageSize);
	}

	public ResponseEntity<ResponseDTO<?>> addTestimonial(TestimonialDTO dto)
	{
		ResponseDTO<Testimonial> response = new ResponseDTO<Testimonial>();
		try
		{
		Testimonial t=new Testimonial();
		t.setAboutUser(dto.getAboutUser());
		t.setDescription(dto.getDescription());
		t.setEmail(dto.getEmail());
		t.setUserName(dto.getUserName());
		t.setCreatedOn(currentTimeStamp());
		Testimonial saved=testimonialRepository.save(t);
		if(saved!=null)
		{
		response.setMessage(Constants.MSG_DATA_SUCCESS_LIKE_OR_DISLIKE);
		response.setStatus(Constants.STATUS_SUCCESS);
		response.setResult(saved);
		return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
		}
		else
		{
			response.setMessage(Constants.MSG_FAILED_ACTION);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(saved);
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.BAD_REQUEST);
		}
		
		}
		catch (Exception e) {
			response.setMessage(Constants.MSG_FAILED_ACTION);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public void saveAppUser(AppUser user) {
		userRepository.save(user);
	}

	public AppUser findByEmail(String email) {
		return userRepository.findUserInfoByEmail(email);
	}


	@Transactional
	public ResponseEntity<ResponseDTO<?>> manageFavouriteBusinesses(BusinessFavouriteDTO businessFavouriteDTO)
	{
		ResponseDTO<Boolean> response = new ResponseDTO<Boolean>();
		try
		{
			String addAction = "add";
			String removeAction = "remove";
			String userAction = businessFavouriteDTO.getAction();
			Optional<AppUser> user=userRepository.findById(businessFavouriteDTO.getUserId());
			if(!user.isPresent())
			{
				response.setMessage(Constants.MSG_USER_NOT_FOUND);
				response.setStatus(Constants.STATUS_FAILED);
				response.setResult(false);
				return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.BAD_REQUEST);
			}
			if(businessFavouriteDTO.getBusinessType().equals(Constants.BUSINESS_TYPE))
			{
				Optional<BusinessListing> listing=businessListingRepository.findById(businessFavouriteDTO.getBusinessId());
				if(!listing.isPresent())
				{
					response.setMessage(Constants.MSG_DATA_NOT_FOUND);
					response.setStatus(Constants.STATUS_FAILED);
					response.setResult(false);
					return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.BAD_REQUEST);
				}
				if(addAction.equalsIgnoreCase(userAction))
				{			
					Timestamp currentTimeStamp = Timestamp.valueOf(LocalDateTime.now());
					Integer userRoleId = userRepository.getRoleIdFromRole(businessFavouriteDTO.getUserId().intValue());			
					businessListingRepository.addFavouriteBusiness(businessFavouriteDTO.getUserId(), userRoleId, businessFavouriteDTO.getBusinessId(), currentTimeStamp);			
				}
				else if (removeAction.equalsIgnoreCase(userAction)){
					businessListingRepository.removeFavouriteBusiness(businessFavouriteDTO.getUserId(), businessFavouriteDTO.getBusinessId());
				}
			}
			else if(businessFavouriteDTO.getBusinessType().equals(Constants.ADVERT_TYPE))
			{
				
				Optional<BusinessAdvert> advert=businessAdvertRepository.findById(businessFavouriteDTO.getBusinessId());
				if(!advert.isPresent())
				{
					response.setMessage(Constants.MSG_DATA_NOT_FOUND);
					response.setStatus(Constants.STATUS_FAILED);
					response.setResult(false);
					return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.BAD_REQUEST);
				}
				
				if(addAction.equalsIgnoreCase(userAction))
				{			
					Timestamp currentTimeStamp = Timestamp.valueOf(LocalDateTime.now());
					Integer userRoleId = userRepository.getRoleIdFromRole(businessFavouriteDTO.getUserId().intValue());			
					businessAdvertRepository.addFavouriteBusiness(businessFavouriteDTO.getUserId(), userRoleId, businessFavouriteDTO.getBusinessId(), currentTimeStamp);			
				}
				else if (removeAction.equalsIgnoreCase(userAction)){
					businessAdvertRepository.removeFavouriteBusiness(businessFavouriteDTO.getUserId(), businessFavouriteDTO.getBusinessId());
				}
				
			}
	
			
			response.setMessage(Constants.MSG_DATA_SUCCESS_LIKE_OR_DISLIKE);
			response.setStatus(Constants.STATUS_SUCCESS);
			response.setResult(true);
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
			
		}
		catch (Exception e) {
			response.setMessage(Constants.MSG_FAILED_ACTION);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(false);
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public PageBusinessServiceTypeDTO getAllBusinessByFilter(int page, int size){
		Pageable pageable = PageRequest.of(page, size);

		Page<BusinessServiseTypeList> businessServiseTypeLists;
		businessServiseTypeLists= businessListingRepository.getAllBusinessServiseTypeList(pageable);
		List<BusinessServiceTypeDTO> businessServiceTypeDTOList = new ArrayList<>();

		for (BusinessServiseTypeList data : businessServiseTypeLists.getContent()) {
			BusinessServiceTypeDTO response = new BusinessServiceTypeDTO();
			response.setId(data.getId());
			response.setServiceType(data.getBusinessServiceType());

			businessServiceTypeDTOList.add(response);
		}
		Page<BusinessServiceTypeDTO> businessServiceTypeDTOS = new PageImpl<>(businessServiceTypeDTOList, pageable, businessServiceTypeDTOList.size());
		int totalPages = businessServiseTypeLists.getTotalPages();
		int currentPage = businessServiceTypeDTOS.getNumber();
		int pageSize = businessServiceTypeDTOS.getSize();
		return new PageBusinessServiceTypeDTO(businessServiceTypeDTOList,totalPages,currentPage,pageSize);
	}

	private PageBusinessListingDto getPageBusinessListDto(List<BusinessListingDTO> brokerListingDTOS,int page, int onePageDataSize) {

		int totalPages = (int) Math.ceil((double) brokerListingDTOS.size()/onePageDataSize);
		int startIndex = 0;
		int endIndex = onePageDataSize;
		if(page > 1){
			startIndex = onePageDataSize* (page-1);
			endIndex = startIndex+onePageDataSize;
		}

		return new PageBusinessListingDto(brokerListingDTOS.size() >= endIndex+1 ? brokerListingDTOS.subList(startIndex,endIndex) : brokerListingDTOS.subList(startIndex, brokerListingDTOS.size()),totalPages,page,onePageDataSize);

	}
	
	public List<BusinessListingQueryDTO> getBusinessListing(Integer pageNo)
	{
		List<BusinessListingQueryDTO> business = new ArrayList<BusinessListingQueryDTO>();
		String sql="SELECT f.file_path AS filePath, b.title AS title, b.listing_description AS listingDescription, blo.total_business_sale_price AS totalBusinessSalePrice,b.suggested_title AS suggestedTitle, b.id AS businessId , c.\"name\" as countryName\n"
				+ "FROM ( SELECT business_listing_id, MAX(file_path) AS file_path FROM file_entity GROUP BY business_listing_id) f \n"
				+ "JOIN business_listing b ON f.business_listing_id = b.id \n"
				+ "JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id \n"
				+ "join country c on b.business_address_country_id =c.id \n"
				+ "WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND b.listing_type = 'BUSINESS' ORDER BY b.last_update desc OFFSET "+pageNo+" LIMIT 21;";
		Query query = entityManager.createNativeQuery(sql);
				
		List <Object[]>businessList =  query.getResultList();
		if (null != businessList) {
			try {
				for (Object [] objArray : businessList) {		
					BusinessListingQueryDTO listing = new BusinessListingQueryDTO();
			       String path=objArray[0].toString();
			        listing.setFilepath(path.contains("/assets")?path.replace("/assets",""):path);
			        listing.setTitle(objArray[1].toString());
			        listing.setListingdescription(objArray[2].toString().substring(0,100));
			        listing.setTotalbusinesssaleprice(Double.valueOf(objArray[3].toString()));
			        listing.setSuggestedtitle(objArray[4].toString());
			        listing.setBusinessid(Long.valueOf(objArray[5].toString()));
			        listing.setAddedToFavourites(true);
			        listing.setCountryName(objArray[6].toString());
			        business.add(listing);
					}
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
		
		return business;
	}
	
	
	public List<BusinessListingQueryDTO> getBusinessListingByTypeWithPage(String listingType,Integer pageNo)
	{
		List<BusinessListingQueryDTO> business = new ArrayList<BusinessListingQueryDTO>();
		String sql="";
		if("DISTRESS".equals(listingType))
		{
			sql="SELECT f.file_path AS filePath, b.title AS title, b.listing_description AS listingDescription, blo.total_business_sale_price AS totalBusinessSalePrice,b.suggested_title AS suggestedTitle, b.id AS businessId ,c.name as countryName	FROM ( SELECT business_listing_id, MAX(file_path) AS file_path FROM file_entity GROUP BY business_listing_id) f JOIN business_listing b ON f.business_listing_id = b.id JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id join country c on b.business_address_country_id =c.id WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT null and b.is_distress_sale=true ORDER BY b.last_update desc OFFSET "+pageNo+" LIMIT 21;";
		}
		else
		{	
		 sql="SELECT f.file_path AS filePath, b.title AS title, b.listing_description AS listingDescription, blo.total_business_sale_price AS totalBusinessSalePrice,b.suggested_title AS suggestedTitle, b.id AS businessId , c.\"name\" as countryName\n"
				+ "FROM ( SELECT business_listing_id, MAX(file_path) AS file_path FROM file_entity GROUP BY business_listing_id) f \n"
				+ "JOIN business_listing b ON f.business_listing_id = b.id \n"
				+ "JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id \n"
				+ "join country c on b.business_address_country_id =c.id \n"
				+ "WHERE f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL AND b.listing_type = '"+listingType+"' ORDER BY b.last_update desc OFFSET "+pageNo+" LIMIT 21;";
		}
		Query query = entityManager.createNativeQuery(sql);
				
		List <Object[]>businessList =  query.getResultList();
		if (null != businessList) {
			try {
				for (Object [] objArray : businessList) {		
					BusinessListingQueryDTO listing = new BusinessListingQueryDTO();
			       String path=objArray[0].toString();
			       	
			       if(path.contains("https://tobuz.com/public"))
			    	   listing.setFilepath(path);
			       else if(path.startsWith("/images"))
			    	   listing.setFilepath("./assets"+path);
			       else   
			    		listing.setFilepath("."+path);
			       			       	
			        listing.setTitle(objArray[1].toString());
			        listing.setListingdescription(objArray[2].toString().substring(0,100));
			        listing.setTotalbusinesssaleprice(Double.valueOf(objArray[3].toString()));
			        listing.setSuggestedtitle(objArray[4].toString());
			        listing.setBusinessid(Long.valueOf(objArray[5].toString()));
			        listing.setAddedToFavourites(true);
			        listing.setCountryName(objArray[6].toString());
			        business.add(listing);
					}
				}
				catch (Exception e) {
					// TODO: handle exception
				}
		}		
		return business;
	}

	public List<BusinessListingQueryDTO> getBusinessListingByCategoriesWithPage(List<Integer>categoriesId,Integer pageNo)
	{
		List<BusinessListingQueryDTO> business = new ArrayList<BusinessListingQueryDTO>();
		 StringJoiner categoriesIds = new StringJoiner(", ");
		    for (int i = 0; i < categoriesId.size(); i++) {
		    	categoriesIds.add(categoriesId.get(i).toString());
		    }
		String sql="SELECT f.file_path AS filePath, b.title AS title, b.listing_description AS listingDescription, blo.total_business_sale_price AS totalBusinessSalePrice,b.suggested_title AS suggestedTitle, b.id AS businessId , c.\"name\" as countryName\n"
				+ "FROM ( SELECT business_listing_id, MAX(file_path) AS file_path FROM file_entity GROUP BY business_listing_id) f \n"
				+ "JOIN business_listing b ON f.business_listing_id = b.id \n"
				+ "JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id \n"
				+ "join country c on b.business_address_country_id =c.id \n"
				+ "WHERE b.category_id IN (" + categoriesIds.toString()+") AND f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL ORDER BY b.last_update desc OFFSET "+pageNo+" LIMIT 21;";
		Query query = entityManager.createNativeQuery(sql);
				
		List <Object[]>businessList =  query.getResultList();
		if (null != businessList) {
			try {
				for (Object [] objArray : businessList) {		
					BusinessListingQueryDTO listing = new BusinessListingQueryDTO();
			       String path=objArray[0].toString();
			       	
			       if(path.contains("https://tobuz.com/public"))
			    	   listing.setFilepath(path);
			       else if(path.startsWith("/images"))
			    	   listing.setFilepath("./assets"+path);
			       else   
			    		listing.setFilepath("."+path);
			       			       	
			        listing.setTitle(objArray[1].toString());
			        listing.setListingdescription(objArray[2].toString());
			        listing.setTotalbusinesssaleprice(Double.valueOf(objArray[3].toString()));
			        listing.setSuggestedtitle(objArray[4].toString());
			        listing.setBusinessid(Long.valueOf(objArray[5].toString()));
			        listing.setAddedToFavourites(true);
			        listing.setCountryName(objArray[6].toString());
			        
			        business.add(listing);
					}
				}
				catch (Exception e) {
					// TODO: handle exception
				}
		}		
		return business;
	}
	
	public List<BusinessListingQueryDTO> searchBusinessListing(SearchDTO searchDTO,Integer pageNo)
	{
		List<BusinessListingQueryDTO> business = new ArrayList<BusinessListingQueryDTO>();
					
			String categories="";
			if(null!=searchDTO.getCategoriesIds() && searchDTO.getCategoriesIds().size()>0)
			{
				StringJoiner categoriesIds = new StringJoiner(", ");
			    for (int i = 0; i < searchDTO.getCategoriesIds().size(); i++) {
			    	categoriesIds.add(searchDTO.getCategoriesIds().get(i).toString());
			    }
			    
			  categories=" b.category_id IN (" + categoriesIds.toString()+") AND ";  
			}
			
			String countries="";
			if(null!=searchDTO.getCountryIds() && searchDTO.getCountryIds().size()>0)
			{
				StringJoiner countriesId = new StringJoiner(", ");
			    for (int i = 0; i < searchDTO.getCountryIds().size(); i++) {
			    	countriesId.add(searchDTO.getCountryIds().get(i).toString());
			    }
			    
			    countries=" b.business_address_country_id IN (" + countriesId.toString()+") AND ";  
			}
			
			String businessType="";
			if(null!=searchDTO.getBusinessType() && !"".equals(searchDTO.getBusinessType()))
			{
				businessType=" AND b.listing_type = '"+searchDTO.getBusinessType()+"'";
			}
			
		    
		  String result=categories.concat(countries);  
		    
		String sql="SELECT  b.id AS businessId,f.file_path AS filePath, b.title AS title, b.listing_description AS listingDescription, blo.total_business_sale_price AS totalBusinessSalePrice,b.suggested_title AS suggestedTitle, c.\"name\" as countryName,\n"
				+"c.currency_code as currencyCode,c.flag_name as flagName \n"
				+ "FROM ( SELECT distinct business_listing_id, MAX(file_path) AS file_path FROM file_entity GROUP BY business_listing_id) f \n"
				+ "JOIN business_listing b ON f.business_listing_id = b.id \n"
				+ "JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id \n"
				+ "join country c on b.business_address_country_id =c.id \n"
				+ "WHERE "+result+" f.business_listing_id IS NOT NULL AND blo.total_business_sale_price IS NOT NULL "+businessType+" OFFSET "+pageNo+" LIMIT 21;";
		
		Query query = entityManager.createNativeQuery(sql);
		
		List<Long>fId=new ArrayList<>();
		if(searchDTO.getUserId()!=null && searchDTO.getUserId()!=0)
		{
			
			List<Long>fId1=businessListingRepository.getUserFavoriteListing(searchDTO.getUserId());
			if(fId1!=null && fId1.size()>0)
				fId=fId1;
		}
		
		
		List <Object[]>businessList =  query.getResultList();
		if (null != businessList) {
			try {
				for (Object [] objArray : businessList) {		
					BusinessListingQueryDTO listing = new BusinessListingQueryDTO();
			       String path=objArray[1].toString();
			       	
			       if(path.contains("https://tobuz.com/public"))
			    	   listing.setFilepath(path);
			       else if(path.startsWith("/images"))
			    	   listing.setFilepath("./assets"+path);
			       else   
			    		listing.setFilepath("."+path);
			        listing.setBusinessid(Long.valueOf(objArray[0].toString()));				       			       	
			        listing.setTitle(objArray[2].toString());
			        listing.setListingdescription(objArray[3].toString());
			        listing.setTotalbusinesssaleprice(Double.valueOf(objArray[4].toString()));
			        listing.setSuggestedtitle(objArray[5].toString());
			        listing.setBusinessid(Long.valueOf(objArray[0].toString()));			        
			        listing.setCountryName(objArray[6].toString());
			        listing.setCurrency(objArray[7].toString());
			        listing.setCurrencyImage(objArray[8].toString());
			        listing.setLikeCount(getFavoriteCount(listing.getBusinessid()));
			        if(searchDTO.getUserId()!=null && searchDTO.getUserId()!=0)
					{
			        	listing.setAddedToFavourites(getUserFavorite(fId,Long.valueOf(objArray[0].toString())));
					}
			        else
			        {
			        	listing.setAddedToFavourites(false);
			        }
			        business.add(listing);
					}
				}
				catch (Exception e) {
					logger.error(CLASS_NAME +"searchBusinessListing() : Error : "+ e.getMessage());
				}
		}		
		return business;
	}
	
	public Boolean getUserFavorite(List<Long> fids, Long listingId) {
	    return fids.stream()
	               .anyMatch(fid -> fid != null && fid.equals(listingId));		
	}	
	
	public Long getFavoriteCount(Long listingid)
	{
		try
		{
			return businessListingRepository.getFavoriteCount(listingid);
		}
		catch (Exception e) {
			logger.error(CLASS_NAME +" getFavoriteCount() : Error : "+ e.getMessage());
			return 0L;
		}
	}
	
	public Long getAdvertFavoriteCount(Long advertId)
	{
		try
		{
			return businessListingRepository.getAdvertFavoriteCount(advertId);
		}
		catch (Exception e) {
			logger.error(CLASS_NAME +" getFavoriteCount() : Error : "+ e.getMessage());
			return 0L;
		}
	}
	
	
	
	
	public List<BusinessAdvertDTO> searchBusinessAdverts(SearchDTO searchDTO,Integer pageNo) throws ParseException
	{
		List<BusinessAdvertDTO> business = new ArrayList<BusinessAdvertDTO>();
			
			String categories="";
			if(null!=searchDTO.getCategoriesIds() && searchDTO.getCategoriesIds().size()>0)
			{
				StringJoiner categoriesIds = new StringJoiner(", ");
			    for (int i = 0; i < searchDTO.getCategoriesIds().size(); i++) {
			    	categoriesIds.add(searchDTO.getCategoriesIds().get(i).toString());
			    }
			    
			  categories=" ads.category_id IN (" + categoriesIds.toString()+") AND ";  
			}
			
			String countries="";
			if(null!=searchDTO.getCountryIds() && searchDTO.getCountryIds().size()>0)
			{
				StringJoiner countriesId = new StringJoiner(", ");
			    for (int i = 0; i < searchDTO.getCountryIds().size(); i++) {
			    	countriesId.add(searchDTO.getCountryIds().get(i).toString());
			    }
			    
			    countries=" ba.advert_country_id IN (" + countriesId.toString()+") AND ";  
			}
						
			String businessType="";
			if(null!=searchDTO.getInvestmentFilterValues() && searchDTO.getInvestmentFilterValues().size()>0)
			{
				String filtervalue="";
				List<String> values = searchDTO.getInvestmentFilterValues();
				for (int i = 0; i < values.size(); i++) {
				    filtervalue += "'" + values.get(i) + "'";
				    if (i < values.size() - 1) {
				        filtervalue += ", ";
				    }
				}			    
			    businessType=" ba.listing_type IN (" + filtervalue+") AND ";  
			}				
			
		    
		  String result=categories.concat(countries).concat(businessType);  
		    
		String sql="select distinct ba.id,ba.advert_id,ba.title ,business_advert_status,au.user_default_role,ba.created_on,ba.investment_range_from,\n"
				+ " ba.investment_range_to,c.name as countryName,c.currency_code as currencyCode,ba.search_index as searchIndex  from Business_Advert ba \n"
				+ " inner join app_user au on ba.adverted_by_user_id = au.id \n"
				+ " inner join country c on ba.advert_country_id=c.id \n"
				+ " inner join business_advert_subcategory_info ads on ads.business_advert_id=ba.id "
				+ " where "+result+" business_advert_status  in ('PUBLISHED' ,'APPROVED','REJECTED','SOLD','UNDER_REVIEW')\n"
				+ " ORDER BY ba.created_on desc OFFSET "+pageNo+" LIMIT 21;";
				
		Query query = entityManager.createNativeQuery(sql);		
		
		List<Long>fId=new ArrayList<>();
		if(searchDTO.getUserId()!=null && searchDTO.getUserId()!=0)
		{
			fId=businessListingRepository.getUserFavoriteAdvert(searchDTO.getUserId());
		}
		
		List <Object[]>advertList =  query.getResultList();
		if (null != advertList) {
			
				for (Object [] objArray : advertList) {		
					BusinessAdvertDTO advert = new BusinessAdvertDTO();	
					 
			       	advert.setId(Long.valueOf(objArray[0].toString()));			       
					advert.setTitle(objArray[2]!=null?objArray[2].toString():"");
					advert.setAdvertId(objArray[1].toString());
			        advert.setStatus(objArray[3]!=null?objArray[3].toString():"");
			        advert.setCreatedOn(dateConvert(objArray[5].toString()));
			        advert.setInvestmentRangeFrom(objArray[6]!=null?objArray[6].toString():"");
			        advert.setInvestmentRangeTo(objArray[7]!=null?objArray[7].toString():"");
			        advert.setCountryCode(objArray[8]!=null?objArray[8].toString():"");
			        advert.setCurrencyCode(objArray[9]!=null?objArray[9].toString():"");
			        advert.setSearchIndex(objArray[10]!=null?objArray[10].toString():"");
			        advert.setLikeCount(getAdvertFavoriteCount(advert.getId()));
			        //getAdvertFavoriteCount()
			        if(searchDTO.getUserId()!=null && searchDTO.getUserId()!=0)
					{
			        	advert.setAddedToFavourites(getUserFavorite(fId,advert.getId()));
					}
			        else
			        {
			        	advert.setAddedToFavourites(false);
			        }
			        
			        business.add(advert);
					}
				
		}		
		return business;
	}
	
	public static String dateConvert(String dateStr)
	{
		if(dateStr==null)
		return null;	
		try
		{
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
		    Date date = inputFormat.parse(dateStr);
	        String formattedDate = outputFormat.format(date);
	        return formattedDate;
		}
		catch (Exception e) {
			return "";	        
		}
	}
	public BusinessAdvertDTO getAdvertBusinessById(Long id,Long userId)
	{
		List<BusinessAdvertDTO> business = new ArrayList<BusinessAdvertDTO>();					
		    
		String sql="select ba.id,ba.advert_id,ba.title ,business_advert_status,au.user_default_role,ba.created_on,ba.investment_range_from,\n"
				+ " ba.investment_range_to,c.name as countryName,c.currency_code as currencyCode,ba.search_index as searchIndex, \n"
				+" ba.advert_description,ba.company_type,ba.listing_type,ads.sub_category_id,ads.category_id \n"
				+ " from Business_Advert ba \n"
				+ " inner join app_user au on ba.adverted_by_user_id = au.id \n"
				+ " inner join country c on ba.advert_country_id=c.id \n"
				+ " inner join business_advert_subcategory_info ads on ads.business_advert_id=ba.id"
				+ " where ba.id="+id;
		Query query = entityManager.createNativeQuery(sql);
		List<Long>fId=new ArrayList<>();
		if(userId!=null && userId!=0)
		{
			fId=businessListingRepository.getUserFavoriteAdvert(userId);
		}				
		List <Object[]>advertList =  query.getResultList();
		if (null != advertList) {
			try {
				for (Object [] objArray : advertList) {		
					BusinessAdvertDTO advert = new BusinessAdvertDTO();		       
			       	advert.setId(Long.valueOf(objArray[0].toString()));	       	
					advert.setTitle(objArray[2].toString());
					advert.setAdvertId(objArray[1].toString());
			        advert.setStatus(objArray[3]!=null?objArray[3].toString():"");
			        advert.setCreatedOn(dateConvert(objArray[5]!=null?objArray[5].toString():""));
			        advert.setInvestmentRangeFrom(objArray[6]!=null?objArray[6].toString():"");
			        advert.setInvestmentRangeTo(objArray[7]!=null?objArray[7].toString():"");
			        advert.setCountryCode(objArray[8]!=null?objArray[8].toString():"");
			        advert.setCurrencyCode(objArray[9]!=null?objArray[9].toString():"");
			        advert.setSearchIndex(objArray[10]!=null?objArray[10].toString():"");
			        advert.setAdvertDescription(objArray[11]!=null?objArray[11].toString():"");
			        advert.setCompanyType(objArray[12]!=null?objArray[12].toString():"");
			        advert.setListingType(objArray[13]!=null?objArray[13].toString():"");
			        Long catSubId=Long.valueOf(objArray[14]!=null?objArray[15].toString():"");
			        Long catId=Long.valueOf(objArray[15]!=null?objArray[15].toString():"");
			        advert.setCategory(findCategoryById(catId).getName());
			        advert.setSubCategory(findSubCategoryById(catSubId).getName());
			        
					if (userId != null && userId != 0) {
						advert.setAddedToFavourites(getUserFavorite(fId, advert.getId()));
					} else {
						advert.setAddedToFavourites(false);
					}
			        
			        business.add(advert);
					}
				}
				catch (Exception e) {
					// TODO: handle exception
				}
		}		
		return business.get(0);
	}
	
	

	
	
	public Category findCategoryById(Long id)
	{
		Optional<Category>cat=categoryRepository.findById(id);
		return cat.isPresent()?cat.get():null;
	}
	
	public SubCategory findSubCategoryById(Long id)
	{
		Optional<SubCategory>cat=subCategoryRepository.findById(id);
		return cat.isPresent()?cat.get():null;
	}
	
	public BusinessServiceType findBusinessServiceTypeById(Long id)
	{
		Optional<BusinessServiceType>cat=businessServiceTypeRepository.findById(id);
		return cat.isPresent()?cat.get():null;
	}
	
	public List<BusinessServiceType> findAllBusinessServiceType()
	{
		List<BusinessServiceType>cat=businessServiceTypeRepository.findAll();
		return cat;
	}
	
	public List<BusinessAdvisorDTO> searchBusinessService(SearchDTO searchDTO,Integer pageNo)
	{
		List<BusinessAdvisorDTO> servicesList = new ArrayList<BusinessAdvisorDTO>();
			
		String countries="";
		if(null!=searchDTO.getCountryIds() && searchDTO.getCountryIds().size()>0)
		{
			StringJoiner countriesId = new StringJoiner(", ");
		    for (int i = 0; i < searchDTO.getCountryIds().size(); i++) {
		    	countriesId.add(searchDTO.getCountryIds().get(i).toString());
		    }		    
		    countries=" a.country_id IN (" + countriesId.toString()+") AND ";  
		}
		
		String businessService="";
		if(null!=searchDTO.getBusinessServicesId() && searchDTO.getBusinessServicesId().size()>0)
		{
			StringJoiner businessServiceId = new StringJoiner(", ");
		    for (int i = 0; i < searchDTO.getBusinessServicesId().size(); i++) {
		    	businessServiceId.add(searchDTO.getBusinessServicesId().get(i).toString());
		    }		    
		    businessService=" ba.business_service_type_id IN (" + businessServiceId.toString()+") AND ";  
		}
		
		String result=countries.concat(businessService);//.concat(businessType);  
		
		String sql="select \n"
				+ "	distinct ba.id, \n"
				+ "	ba.company_name,\n"
				+ "	ba.firm_name,\n"
				+ "	ba.web_site_address,\n"
				+ "	ba.social_media,\n"
				+ "	ba.created_on,\n"
				+ "	bst.business_service_type,\n"
				+ "	a.detailed_address,\n"
				+ "	a.zip_code,\n"
				+ "	c.name as city,\n"
				+ "	cc.name as country,\n"
				+ " au.email,\n"
				+ " au.mobile_number,\n"
				+ " au.name\n"
				+ " from business_advisor ba \n"
				+ " inner join business_service_type bst on bst.id=ba.business_service_type_id\n"
				+ " inner join address a on a.id=ba.address_id\n"
				+ " inner join city c on c.id=a.city_id\n"
				+ " inner join state s on s.id=a.state_id\n"
				+ " inner join country cc on cc.id=a.country_id\n"
				+ " inner join app_user au on au.id=ba.adviosr_by_user_id\n"
				+ " where "+result +" ba.is_active=true "
				+ " ORDER BY ba.created_on desc OFFSET "+pageNo+" LIMIT 10;";
		Query query = entityManager.createNativeQuery(sql);
				
		List <Object[]>advertList =  query.getResultList();
		if (null != advertList) {
			try {
				for (Object [] objArray : advertList) {		
					BusinessAdvisorDTO advior = new BusinessAdvisorDTO();
					advior.setId(Long.valueOf(objArray[0]!=null?objArray[0].toString():""));
					advior.setCompanyName(objArray[1]!=null?objArray[1].toString():"");
					advior.setFirmName(objArray[2]!=null?objArray[2].toString():"");
					advior.setWebsite(objArray[3]!=null?objArray[3].toString():"");
					advior.setSocialMedia(objArray[4]!=null?objArray[4].toString():"");
					advior.setCreatedOn(dateConvert(objArray[5]!=null?objArray[5].toString():""));
					advior.setServiceType(objArray[6]!=null?objArray[6].toString():"");
					advior.setAddress(objArray[7]!=null?objArray[7].toString():"");
					advior.setZipCode(objArray[8]!=null?objArray[8].toString():"");
					advior.setCity(objArray[9]!=null?objArray[9].toString():"");
					advior.setCountry(objArray[10]!=null?objArray[10].toString():"");
					advior.setUserMail(objArray[11]!=null?objArray[11].toString():"");
					advior.setUserContact(objArray[12]!=null?objArray[12].toString():"");					
					advior.setUserName(objArray[13]!=null?objArray[13].toString():"");
					servicesList.add(advior);
					}
				}
				catch (Exception e) {
					// TODO: handle exception
				}
		}		
		return servicesList;
	}
	
	
	public BusinessAdvisorDTO getBusinessServicesById(Long businessAdvisorId)
	{
		String sql="select "
				+ "	ba.id as advisorId,ba.company_name,ba.firm_name,ba.web_site_address,ba.is_active,"
				+ "	au.name,au.email,au.mobile_number,ba.last_update,ba.is_company_register,"
				+ "	ba.company_register_number,ba.company_register_year,ba.country_of_registration,ba.tax_identification_number,ba.passport_details,"
				+ "	ba.nationality,	ba.licensed_id_id,ba.corporate_profile_id_id,ba.social_media,ba.social_media_profile_url,"
				+ "	ba.about_corporate_profile,ba.business_service_type_id,ba.is_approved_by_admin,ba.is_profile_verified,ba.created_on,"				
				+ "	bst.business_service_type,a.detailed_address,a.zip_code,c.name as city,cc.name as country"
				+ " from business_advisor ba inner join app_user au on ba.adviosr_by_user_id=au.id"
				+ " inner join business_service_type bst on bst.id=ba.business_service_type_id"
				+ " inner join address a on a.id=ba.address_id"
				+ " inner join city c on c.id=a.city_id"
				+ " inner join state s on s.id=a.state_id"
				+ " inner join country cc on cc.id=a.country_id"
				+ " where ba.id=23;";
		
		Query query = entityManager.createNativeQuery(sql);
		BusinessAdvisorDTO advior = new BusinessAdvisorDTO();
		List <Object[]>adviors =  query.getResultList();
		if (null != adviors) {
			try {
				for (Object [] objArray : adviors) {	
					
					advior.setId(Long.valueOf(objArray[0]!=null?objArray[0].toString():""));
					advior.setCompanyName(objArray[1]!=null?objArray[1].toString():"");
					advior.setFirmName(objArray[2]!=null?objArray[2].toString():"");
					advior.setWebsite(objArray[3]!=null?objArray[3].toString():"");
					
					advior.setUserName(objArray[5]!=null?objArray[5].toString():"");
					advior.setUserMail(objArray[6]!=null?objArray[6].toString():"");
					advior.setUserContact(objArray[7]!=null?objArray[7].toString():"");	
					
					advior.setCompanyRegisterNumber(objArray[10]!=null?objArray[10].toString():"");
					advior.setCompanyRegisterYear(objArray[11]!=null?objArray[11].toString():"");
					advior.setCompanyRegisterCountry(objArray[12]!=null?objArray[12].toString():"");
					advior.setTaxIdentificationNumber(objArray[13]!=null?objArray[13].toString():"");
					advior.setPassortDetails(objArray[14]!=null?objArray[14].toString():"");
					
					advior.setSocialMedia(objArray[18]!=null?objArray[18].toString():"");
					
					advior.setCreatedOn(dateConvert(objArray[24]!=null?objArray[24].toString():""));
					
					advior.setServiceType(objArray[25]!=null?objArray[25].toString():"");
					advior.setAddress(objArray[26]!=null?objArray[26].toString():"");
					advior.setZipCode(objArray[27]!=null?objArray[27].toString():"");
					advior.setCity(objArray[28]!=null?objArray[28].toString():"");
					advior.setCountry(objArray[29]!=null?objArray[29].toString():"");
					
					break;
					}
				
				return advior; 
				}
				catch (Exception e) {
					return null;
				}
		}
		else
		{
			return null;
		}	
		
		
	}


	public List<State> findStateByCountry(Long cid) {

		return stateRepository.findByCountry(cid);
	}


	@SuppressWarnings("deprecation")
	public List<BrokerDTO> searchBroker(SearchDTO searchDTO, Integer page) {
		logger.info("Broker Search Start ");
		String countries="";
		if(null!=searchDTO.getCountryIds() && searchDTO.getCountryIds().size()>0)
		{
			StringJoiner countriesId = new StringJoiner(", ");
		    for (int i = 0; i < searchDTO.getCountryIds().size(); i++) {
		    	countriesId.add(searchDTO.getCountryIds().get(i).toString());
		    }		    
		    countries=" where a.country_id IN (" + countriesId.toString()+") ";  
		}
		
		String sql="select distinct b.id as broker_id,au.email, au.mobile_number,c.name as city_name,c2.name as country_name,b.company_description,au.name,b.file_entity_id,c2.dialing_code from broker b inner join app_user au on b.user_id =au.id inner join address a on au.address_id =a.id "
				+ "inner join city c on a.city_id =c.id \n"
				+ "inner join country c2 on c2.id =a.country_id "+countries+" offset 0 limit 21;";
		Query query = entityManager.createNativeQuery(sql);
		List<BrokerDTO>brokerList=new ArrayList<BrokerDTO>();
		try {			
	
		List <Object[]>adviors =  query.getResultList();
		if (null != adviors) {		
				for (Object [] objArray : adviors) {
					//System.out.print
					BrokerDTO brokerDTO=new BrokerDTO();					
			        brokerDTO.setBrokerId(objArray[0] != null ? ((Number) objArray[0]).longValue() : null);
			        brokerDTO.setEmail(objArray[1] != null ? objArray[1].toString() : null);
			        brokerDTO.setMobileNumber(objArray[2] != null ? objArray[2].toString() : null);
			        brokerDTO.setCityName(objArray[3] != null ? objArray[3].toString() : null);
			        brokerDTO.setCountryName(objArray[4] != null ? objArray[4].toString() : null);			        
			        brokerDTO.setCompanyDesc(objArray[5] != null ? objArray[5].toString() : null);
					brokerDTO.setBrokerName(objArray[6] != null ? objArray[6].toString() : null);
					if(objArray[7] != null)
					brokerDTO.setProfileImg(getFileName(Long.valueOf(objArray[7].toString())));
					else
					brokerDTO.setProfileImg("./assets/images/img/NO-IMAGE-2.jpg");	
					brokerDTO.setCountryCode(objArray[8] != null ? objArray[8].toString() : null);
					
					brokerList.add(brokerDTO);
				}			
			}
		}
		catch (Exception e) {
			logger.error("Broker Search Failed : "+e.getMessage());
		}
		return brokerList;
	}
	
	
	public String getFileName(Long id)
	{
		Optional<FileEntity>fileOpt=fileEntityRepositiory.findById(id);
		if(fileOpt.isPresent())
			return fileOpt.get().getFilePath();
		else
			return "./assets/images/img/NO-IMAGE-2.jpg";
		
	}
}
