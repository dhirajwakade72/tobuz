package com.tobuz.service;

import com.tobuz.model.*;
import com.tobuz.model.tobuzpackage.TobuzPackage;
import com.tobuz.model.tobuzpackage.TobuzPackageService;
import com.tobuz.object.*;
import com.tobuz.projection.BrokerList;
import com.tobuz.projection.BusinessByFilter;
import com.tobuz.projection.BusinessServiseTypeList;
import com.tobuz.projection.CategoryByFilter;
import com.tobuz.projection.CountryList;
import com.tobuz.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.LongStream;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service

public class TobuzBusinessService {
	@Autowired
	FileEntityRepositiory fileRepository;
	//getting all books record by using the method findaAll() of CrudRepository

	@Autowired
	UserRepository userRepository;

	@Autowired
	BusinessListingRepository businessListingRepository;

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
	MessageRepository messageRepository;

	@Autowired
	SubCategoryRepository subCategoryRepository;

	@Autowired
	NewsLetterRepository newsLetterRepository;

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

	 public int addBusinessListings (BusinessListingDTO businessListingDTO) {

		 try {
			 Date date = new Date();
			 Timestamp ts = new Timestamp(date.getTime());

			 Category category = new Category();
			 category.setIsActive(true);
			 SubCategory subcatgory = new SubCategory();
			 subcatgory.setName(businessListingDTO.getCategoryList());
			 subcatgory.setCreatedOn(ts);
			 List<SubCategory> subCategories = new ArrayList<SubCategory>();
			 subCategories.add(subcatgory);
			 category.setSubCategoryList(subCategories);

			 BusinessListing businessListing = new BusinessListing();

			 businessListing.setListingType(ListingType.BUSINESS);
			 businessListing.setCategory(category);
			// businessListing.setFranchiseFor(FranchiseFor.SALE);
			 System.out.println("businessListingDTO.getDescription() : "+businessListingDTO.getDescription());
			 businessListing.setDescription(businessListingDTO.getListingDescription());
			 businessListing.setTitle(businessListingDTO.getYourTitle());
			 businessListing.setSuggestedtitle(businessListingDTO.getSuggestedTitle());
			 businessListing.setSuggestedTitle(businessListingDTO.getSuggestedTitle());
			 businessListing.setListingKeywords(businessListingDTO.getListingKeywords());
			 businessListing.setIsActive(true);
			 businessListing.setName(businessListingDTO.getContactName());
			 businessListing.setContactNumber(businessListingDTO.getContactNumber());
			 businessListing.setListingFor(ListingFor.SALE);
			 businessListing.setTitle(businessListingDTO.getYourTitle());
			 businessListing.setLastUpdate(ts);
			 businessListing.setListingDescription(businessListingDTO.getListingDescription());
			 //businessListing.setWebsiteURL(businessListingDTO.getContactWeb());
			 businessListing.setPostedOn(date);
		 	 businessListing.setBusinessListingStatus(businessListingDTO.getBusinessListingStatus());
			 businessListing.setBusinessAddressCountry(null);
			 businessListing.setCreatedOn(ts);
			 HttpSession session = getSession();
			 AppUser appUser = (AppUser) session.getAttribute("appUser");
			 System.out.println("appUser : "+appUser);
			 businessListing.setListedByUser(appUser);
			 category.setCreatedOn(ts);
			 BusinessAdvisor businessAdvisor = new BusinessAdvisor();
			 businessAdvisor.setIsActive(true);
			 businessAdvisor.setIsApprovedByAdmin(true);
			 businessAdvisor.setCompanyName(businessListingDTO.getCompanyType());
			 Role role = new Role();
			 role.setAppUser(appUser);
			 System.out.println("businessListingDTO.getBusinessListingStatus() : "+businessListingDTO.getBusinessListingStatus());
			 role.setUserRole(UserRole.SELLER);
			 role.setCreatedOn(ts);
			 Country country = countryRepository.findCountryByIsoCode(businessListingDTO.getIsoCode());
			 System.out.println("country : "+country.getName());
			 businessListing.setCountryCode(country.getDialingCode());
			 roleRepository.save(role);
			 businessListing.setRole(role);
			 categoryRepository.save(category);
			 businessListingRepository.save(businessListing);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// businessListing.setBusinessAddressCountry(Co);
		// businessListing.set
		 return 0;
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

						Country country = countryRepository.findCountryByDialingCode(bTypeDTO.getIsoCode());
						bTypeDTO.setCountryName(country.getName());
						System.out.println("country NAME : "+bTypeDTO.getCountryName());
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
							 Country country = countryRepository.findCountryByDialingCode(objArray[6].toString());
							if(country != null) {
								 System.out.println("country : "+country.getName());

								 bTypeDTO.setCountry(country);
								 bTypeDTO.setCountryName(country.getName());
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



		public List<BusinessListingDTO> getTopBusinessListings() {
			List<BusinessListingDTO> business = new ArrayList<BusinessListingDTO>();
			List<Object[]> businessList = fileRepository.getTopBusinessListings();
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
							businessListingDTO.setBusinessListingId((objArray[5].toString()));

						business.add(businessListingDTO);

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

		public List<BusinessListingDTO> topCommercialListings() {
			List<BusinessListingDTO> business = new ArrayList<BusinessListingDTO>();
			List<Object[]> businessList = fileRepository.topCommercialListings();
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
							businessListingDTO.setBusinessListingId((objArray[5].toString()));

						business.add(businessListingDTO);

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

		public List<BusinessListingDTO> topFranchesieListings() {
			List<BusinessListingDTO> business = new ArrayList<BusinessListingDTO>();
			List<Object[]> businessList = fileRepository.topFranchesieListings();
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
							businessListingDTO.setBusinessListingId((objArray[5].toString()));

						business.add(businessListingDTO);

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



		public List<BusinessListingDTO> getTopBusinessListingsByCategory(List<String> catId,String listingType) {
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
						business.add(businessListingDTO);

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
											Country country = countryRepository.findCountryByDialingCode(bTypeDTO.getCountry());
											bTypeDTO.setCountry(country.getName());
											System.out.println("country NAME : "+bTypeDTO.getCountry());
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
			List<BusinessListingDTO> business = new ArrayList<BusinessListingDTO>();
			 HttpSession session = getSession();
			 AppUser appUser = (AppUser) session.getAttribute("appUser");
			List<Object[]> businessList = fileRepository.getFavouriteBusiness(appUser.getId());
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
						business.add(businessListingDTO);

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

	public List<BusinessListingDTO> getBusinessByFilter(BusinessListingDTO businessListingDTO) {

		List<BusinessListingDTO> businessListingDTOList = new ArrayList<>();
		List<Long> longList = new ArrayList<>();
		if (businessListingDTO.getCategoryIds() != null && !businessListingDTO.getCategoryIds().isEmpty()) {
			for (String str : businessListingDTO.getCategoryIds()) {
				longList.add(Long.parseLong(str));
			}
		}

		List<BusinessByFilter> businessByFilter = new ArrayList<>();

		if (businessListingDTO.getFranchiseType() == null) {
			if (longList != null && !longList.isEmpty()) {
				for (Long l : longList) {
					businessByFilter = fileEntityRepositiory.getBusinessByFilter(l, businessListingDTO.getListingType(), businessListingDTO.getSearchKey());
					getBusinessListDto(businessByFilter, businessListingDTOList);
				}
			} else {
				businessByFilter = fileEntityRepositiory.getBusinessByFilter(businessListingDTO.getListingType(), businessListingDTO.getSearchKey());
				getBusinessListDto(businessByFilter, businessListingDTOList);

			}
		} else {
			if (longList != null && !longList.isEmpty()) {
				for (Long l : longList) {
					for (String fType : businessListingDTO.getFranchiseType()) {
						businessByFilter = fileEntityRepositiory.getBusinessWithFranchiseFilter(l, businessListingDTO.getListingType(), fType, businessListingDTO.getSearchKey());
						getBusinessListDto(businessByFilter, businessListingDTOList);
					}
				}
			} else {
				for (String fType : businessListingDTO.getFranchiseType()) {
					businessByFilter = fileEntityRepositiory.getBusinessWithFranchiseFilter(businessListingDTO.getListingType(), fType, businessListingDTO.getSearchKey());
					getBusinessListDto(businessByFilter, businessListingDTOList);
				}
			}

		}
		if (businessListingDTO.getCountryIds() != null && !businessListingDTO.getCountryIds().isEmpty() && !businessListingDTOList.isEmpty()) {
			List<Long> countryIdList = new ArrayList<>();
			for (String str : businessListingDTO.getCountryIds()) {
				countryIdList.add(Long.parseLong(str));
			}
			return businessListingDTOList.stream().filter(dto -> countryIdList.contains(dto.getCountryId())).collect(Collectors.toList());
		} else if (businessListingDTO.getPrefredCountryId() != null) {
			return businessListingDTOList.stream().filter(dto -> businessListingDTO.getPrefredCountryId().equals(dto.getCountryId().toString())).collect(Collectors.toList());
		}
		return businessListingDTOList;
	}

    private void getBusinessListDto(List<BusinessByFilter> businessByFilter, List<BusinessListingDTO> businessListingDTOList) {
        if (Objects.nonNull(businessByFilter)) {
            for (BusinessByFilter data : businessByFilter) {
                BusinessListingDTO response = new BusinessListingDTO();
                response.setFilePath(data.getFilePath());
                response.setTitle(data.getTitle());
                response.setDescription(data.getListingDescription());
                response.setPrice(data.getPrice());
                response.setSuggestedTitle(data.getSuggestedTitle());
                response.setBusinessListingId(data.getSuggestedTitle());
				response.setCountryId(data.getCountryId());
				businessListingDTOList.add(response);
            }
        }
    }
	@Transactional
	public NewsLetterSubscription saveNewsletter(String email) {
		Integer appUserId = userRepository.getUserIdFromAppUser(email);
		Integer roleId = userRepository.getRoleIdFromRole(appUserId);
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

	public List<BrokerListingDTO> getBrokerList(BrokerListingDTO brokerListingDTO) {
		List<BrokerListingDTO> brokerListingDTOS = new ArrayList<>();
		List<Long> ids = new ArrayList<>();
		if (brokerListingDTO.getBusinessServiceIds() != null && !brokerListingDTO.getBusinessServiceIds().isEmpty()) {
			for (String str : brokerListingDTO.getBusinessServiceIds()) {
					ids.add(Long.parseLong(str));
			}

		List<BrokerList> brokerLists = new ArrayList<>();
		for (Long l : ids){
			brokerLists = userRepository.getBrokerList(l);
			getBrokerListDTO(brokerLists, brokerListingDTOS);
		}
		}else{
			List<BrokerList> brokerLists = new ArrayList<>();
			brokerLists = userRepository.getBrokerList();
			getBrokerListDTO(brokerLists, brokerListingDTOS);
		}

		if(brokerListingDTO.getCountryIds() != null && !brokerListingDTO.getCountryIds().isEmpty() && !brokerListingDTOS.isEmpty()){

			List<Long> countryIdList = new ArrayList<>();
				for (String str : brokerListingDTO.getCountryIds()) {
					countryIdList.add(Long.parseLong(str));
				}
			return  brokerListingDTOS.stream().filter(dto -> countryIdList.contains(dto.getCountryId())).collect(Collectors.toList());
		}else if (brokerListingDTO.getPrefredCountryId() != null) {
			return brokerListingDTOS.stream().filter(dto -> brokerListingDTO.getPrefredCountryId().equals(dto.getCountryId().toString())).collect(Collectors.toList());
		}
			return brokerListingDTOS;
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

	public List<BrokerListingDTO> getAllBrokerList(){
		List<BrokerListingDTO> list = new ArrayList<>();
		List<Object[]> allBrokerList = userRepository.getAllBrokerList();
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
		return list;
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

	public List<BusinessServiceTypeDTO> getAllBusinessServiseTypeList(){

		List<BusinessServiseTypeList> businessServiseTypeLists = new ArrayList<>();
		businessServiseTypeLists= businessListingRepository.getAllBusinessServiseTypeList();
		List<BusinessServiceTypeDTO> businessServiceTypeDTOList = new ArrayList<>();

		for (BusinessServiseTypeList data : businessServiseTypeLists) {
			BusinessServiceTypeDTO response = new BusinessServiceTypeDTO();
			response.setId(data.getId());
			response.setServiceType(data.getBusinessServiceType());

			businessServiceTypeDTOList.add(response);
		}

		return businessServiceTypeDTOList;
	}
}
