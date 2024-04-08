package com.tobuz.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tobuz.constant.Constants;
import com.tobuz.controller.CommanRestController;
import com.tobuz.dto.BusinessListingDTO;
import com.tobuz.dto.CategoriesDTO;
import com.tobuz.dto.ResponseDTO;
import com.tobuz.model.AppUser;
import com.tobuz.model.BusinessAdvert;
import com.tobuz.model.BusinessFeature;
import com.tobuz.model.BusinessListing;
import com.tobuz.model.BusinessListingFeatureInfo;
import com.tobuz.model.Category;
import com.tobuz.model.Country;
import com.tobuz.model.FileEntity;
import com.tobuz.model.SubCategory;
import com.tobuz.object.BusinessAdvertDTO;
import com.tobuz.projection.CountryList;
import com.tobuz.repository.BusinessAdvertRepository;
import com.tobuz.repository.BusinessListingRepository;
import com.tobuz.repository.CategoryRepository;
import com.tobuz.repository.CountryRepository;
import com.tobuz.repository.FileEntityRepositiory;
import com.tobuz.repository.SubCategoryRepository;
import com.tobuz.utils.DateUtils;

@Service
public class CommonService {

	private static final Logger logger = LoggerFactory.getLogger(CommanRestController.class);

	public static final String CLASS_NAME = "CommonService";
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private BusinessListingRepository businessListingRepository;

	@Autowired
	private FileEntityRepositiory fileEntityRepositiory;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	private BusinessAdvertRepository advertRepository;
	
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private UserService userService;
	

	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}

	public List<SubCategory> findAllSubCategories() {
		return subCategoryRepository.findAll();
	}

	public List<SubCategory> findSubCategoriesByCatId(Long catId) {
		return subCategoryRepository.findByCategoryId(catId);
	}

	public List<CategoriesDTO> findAllCategoriesWithSubCat() {
		List<CategoriesDTO> cats = new ArrayList<CategoriesDTO>();
		for (Category c : categoryRepository.findAllByOrderByNameAsc()) {
			CategoriesDTO catDTO = new CategoriesDTO();
			catDTO.setId(c.getId());
			catDTO.setName(c.getName());
			catDTO.setIsCommercial(c.getIsCommercialCategory());
			catDTO.setImageId(c.getImageId());
			catDTO.setSubCategoryList(findSubCategoriesByCatId(c.getId()));
			cats.add(catDTO);
		}
		cats.sort(Comparator.comparingInt((CategoriesDTO c) -> c.getSubCategoryList().size()).reversed());
		List<CategoriesDTO> cats1=cats.stream().filter(cat->cat.getSubCategoryList().size()>0).collect(Collectors.toList());
		return cats1;
	}
	
	public ResponseDTO<List<BusinessListingDTO>> getMyBusinessListingById(Long user_id) {
		ResponseDTO<List<BusinessListingDTO>> response = new ResponseDTO<List<BusinessListingDTO>>();
		logger.info(CLASS_NAME + " : getMyBusinessListingById() : Started ***");
		
		try {
			AppUser user=userService.findUserById(user_id);
			if (user==null)
			{
				response.setMessage(Constants.MSG_USER_NOT_FOUND);
				response.setStatus(Constants.STATUS_FAILED);
				response.setResult(null);
				return response;
			}
			List<BusinessListing> businessListingOpt = businessListingRepository.findAllByUserId(user_id);
			if (businessListingOpt==null || businessListingOpt.size()==0)
			{
				response.setMessage(Constants.MSG_NOT_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(null);
				return response;
			}
			List<BusinessListingDTO> list=new ArrayList<BusinessListingDTO>();
			for(BusinessListing listng:businessListingOpt)
			{
			//BusinessListing listng = businessListingOpt.get(0);
			List<FileEntity> fileEntities = fileEntityRepositiory.findByBusinessListingId(listng.getId());

			BusinessListingDTO businessDTO = modelMapper.map(listng, BusinessListingDTO.class);
			businessDTO.setListingGallery(fileEntities);
			businessDTO.setBusinessFeatures(getBusinessFeatureInfo(listng.getId()));			
			businessDTO.setPostedOnStr(DateUtils.getFormalDate(businessDTO.getPostedOn()));
			businessDTO.setExpiredOnStr(DateUtils.getFormalDate(businessDTO.getExpiredOn()));
			businessDTO.setStatus(listng.getIsActive()?"Active":"In Active");
			
			list.add(businessDTO);
			}
			response.setMessage(Constants.MSG_DATA_FOUND);
			response.setStatus(Constants.STATUS_SUCCESS);
			response.setResult(list);
			logger.info(CLASS_NAME + " : getMyBusinessListingById() : End ***");
			return response;

		} catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			logger.error(CLASS_NAME + " : getMyBusinessListingById() : Error ="+ e.getMessage(),e);
			return response;
		}
	}

		
	
	public ResponseDTO<BusinessListingDTO> getBusinessListingById(Long listing_id,Long userId) {
		ResponseDTO<BusinessListingDTO> response = new ResponseDTO<BusinessListingDTO>();
		ArrayList<Long>ids=new ArrayList<>();
		ids.add(listing_id);
		List<BusinessListing> businessListingOpt = businessListingRepository.findAllById(ids);
		try {
			if (businessListingOpt==null || businessListingOpt.size()==0)
			{
				response.setMessage(Constants.MSG_NOT_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(null);
				return response;
			}
			BusinessListing listng = businessListingOpt.get(0);
			List<FileEntity> fileEntities = fileEntityRepositiory.findByBusinessListingId(listng.getId());
			List<Long>fId=new ArrayList<>();
			if(userId!=null && userId!=0)
			{
				fId=businessListingRepository.getUserFavoriteListing(userId);
			}	
			BusinessListingDTO businessDTO = modelMapper.map(listng, BusinessListingDTO.class);
			businessDTO.setListingGallery(fileEntities);
			businessDTO.setBusinessFeatures(getBusinessFeatureInfo(listng.getId()));			
			businessDTO.setPostedOnStr(DateUtils.getFormalDate(businessDTO.getPostedOn()));
			if (userId != null && userId != 0) {
				businessDTO.setIsFovorite(getUserFavorite(fId,listng.getId()));
			} else {
				businessDTO.setIsFovorite(false);
			}
			response.setMessage(Constants.MSG_DATA_FOUND);
			response.setStatus(Constants.STATUS_SUCCESS);
			response.setResult(businessDTO);
			return response;

		} catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return response;
		}
	}
	public Boolean getUserFavorite(List<Long> fids, Long listingId) {
	    return fids.stream()
	               .anyMatch(fid -> fid != null && fid.equals(listingId));		
	}	
	
	public static Date dateConvert(Date dateStr) throws ParseException {        
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDateString = outputFormat.format(dateStr);
        return outputFormat.parse(formattedDateString);
    }
	
	public List<BusinessFeature> getBusinessFeatureInfo(Long listing_id) 
	{
		String sql="select bf.id,bf.name,bf.created_on,bf.last_update,bf.is_active from business_listing_feature_info blfi inner join business_feature bf on blfi.business_feature_id=bf.id where blfi.business_listing_id="+listing_id;
		Query query = entityManager.createNativeQuery(sql, BusinessFeature.class);
		List<BusinessFeature> businessFeatures = query.getResultList();
		return businessFeatures;
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
	

	public ResponseDTO<BusinessAdvertDTO> getAdvertListingById(Long advertId,Long userId) {
		ResponseDTO<BusinessAdvertDTO> response = new ResponseDTO<BusinessAdvertDTO>();
		try {			
			BusinessAdvertDTO businessDTO = businessService.getAdvertBusinessById(advertId,userId);				
			
			response.setMessage(Constants.MSG_DATA_FOUND);
			response.setStatus(Constants.STATUS_SUCCESS);
			response.setResult(businessDTO);
			return response;

		} catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return response;
		}
	}
	
	public ResponseDTO<BusinessAdvertDTO> getBusinessServiceById(Long serviceId,Long userId) {
		ResponseDTO<BusinessAdvertDTO> response = new ResponseDTO<BusinessAdvertDTO>();
		try {			
			BusinessAdvertDTO businessDTO = businessService.getAdvertBusinessById(serviceId,userId);				
			
			response.setMessage(Constants.MSG_DATA_FOUND);
			response.setStatus(Constants.STATUS_SUCCESS);
			response.setResult(businessDTO);
			return response;

		} catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return response;
		}
	}
	
}
