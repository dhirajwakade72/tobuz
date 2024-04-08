package com.tobuz.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tobuz.constant.Constants;
import com.tobuz.dto.BrokerDTO;
import com.tobuz.dto.BusinessAdvisorDTO;
import com.tobuz.dto.CategoriesDTO;
import com.tobuz.dto.ResponseDTO;
import com.tobuz.dto.SearchDTO;
import com.tobuz.model.BusinessServiceType;
import com.tobuz.model.Category;
import com.tobuz.model.Country;
import com.tobuz.model.State;
import com.tobuz.object.BusinessAdvertDTO;
import com.tobuz.object.BusinessListingDTO;
import com.tobuz.object.BusinessListingQueryDTO;
import com.tobuz.projection.CountryList;
import com.tobuz.service.CommonService;
import com.tobuz.service.FilesStorageService;
import com.tobuz.service.BusinessService;

import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class CommanRestController {

	private static final Logger logger = LoggerFactory.getLogger(CommanRestController.class);

	public static final String CLASS_NAME = "CommanRestController";

	@Autowired
	private BusinessService businessService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private FilesStorageService storageService;
	
	@GetMapping("/hello")
	public String hello()
	{
		return "Hello Tobuz.com";
	}

	@RequestMapping(value = "/get_business_listing/{page}", produces = {
			"application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> getBusinessListing(@PathVariable(name = "page") Integer page) {
		ResponseDTO<List<BusinessListingQueryDTO>> response = new ResponseDTO<List<BusinessListingQueryDTO>>();
		try {
			List<BusinessListingQueryDTO> business = businessService.getBusinessListing(page);
			if (business != null) {
				response.setMessage(Constants.MSG_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			} else {
				response.setMessage(Constants.MSG_NOT_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			}
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get_recent_business_listing")
	public ResponseEntity<ResponseDTO<?>> getRecentBusinessListing() {
		ResponseDTO<List<BusinessListingQueryDTO>> response = new ResponseDTO<List<BusinessListingQueryDTO>>();
		try {
			List<BusinessListingQueryDTO> business = businessService.getBusinessListing(1);
			if (business != null) {
				response.setMessage(Constants.MSG_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			} else {
				response.setMessage(Constants.MSG_NOT_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			}
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/get_business_listing_with_type/{listingType}/{page}", produces = {
			"application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> getBusinessListingByTypeWithPage(
			@PathVariable("listingType") String listingType, @PathVariable(name = "page") Integer page) {
		ResponseDTO<List<BusinessListingQueryDTO>> response = new ResponseDTO<List<BusinessListingQueryDTO>>();
		try {
			logger.info(CLASS_NAME + ":getBusinessListingByTypeWithPage:" + listingType + " , Page=" + page);
			List<BusinessListingQueryDTO> business = businessService.getBusinessListingByTypeWithPage(listingType,
					page);
			if (business != null) {
				response.setMessage(Constants.MSG_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			} else {
				response.setMessage(Constants.MSG_NOT_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			}
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/get/business_listing_by_id/{listing_id}", produces = {
			"application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> getBusinessListingById(@PathVariable("listing_id") Long listing_id) {
		ResponseDTO<com.tobuz.dto.BusinessListingDTO> response = commonService.getBusinessListingById(listing_id,0L);
		return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/get/listing/{listing_type}/{listing_id}/{userId}", produces = {
			"application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> getBusinessListingById(@PathVariable("listing_type") String listingType,
			@PathVariable("listing_id") Long listing_id,@PathVariable("userId") Long userId) {
		ResponseDTO<?> response = null;
		if ("ADVERT".equals(listingType))
			response = commonService.getAdvertListingById(listing_id,userId);
		else if ("BUSINESS_SERVICE".equals(listingType))
			response = commonService.getAdvertListingById(listing_id,userId);
		else
			response = commonService.getBusinessListingById(listing_id,userId);

		return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/get/my-listing/{user_id}")

	public ResponseEntity<ResponseDTO<?>> getMyBusinessListing(@PathVariable("user_id") Long userId) 
	{
		
		//if ("ADVERT".equals(listingType))
			//response = commonService.getAdvertListingById(userId);
		//else if ("BUSINESS_SERVICE".equals(listingType))
			//response = commonService.getAdvertListingById(userId);
		//else
			ResponseDTO<?> response = commonService.getMyBusinessListingById(userId);

		return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/get/all/categies", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> getAllCategories() {
		ResponseDTO<List<CategoriesDTO>> response = new ResponseDTO<List<CategoriesDTO>>();
		try {
			List<CategoriesDTO> categories = commonService.findAllCategoriesWithSubCat();
			if (categories != null && categories.size() > 0) {
				response.setMessage(Constants.MSG_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(categories);
			} else {
				response.setMessage(Constants.MSG_NOT_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(categories);
			}
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/get/subcategories/{categoryId}", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> getSubcategoriesById(@PathVariable Long categoryId) {
		ResponseDTO<List<CategoriesDTO>> response = new ResponseDTO<List<CategoriesDTO>>();
		try {
			List<CategoriesDTO> categories = commonService.findAllCategoriesWithSubCat();
			if (categories != null && categories.size() > 0) {
				response.setMessage(Constants.MSG_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(categories);
			} else {
				response.setMessage(Constants.MSG_NOT_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(categories);
			}
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/get/list/countries", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> getAllCountryList() {
		ResponseDTO<List<Country>> response = new ResponseDTO<List<Country>>();
		try {
			List<Country> countries = businessService.getAllCountryList();
			if (countries != null && countries.size() > 0) {
				response.setMessage(Constants.MSG_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(countries);
			} else {
				response.setMessage(Constants.MSG_NOT_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(countries);
			}
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/get/state/{countryId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResponseDTO<?>> findStateByCountry(@PathVariable Long countryId) {
		ResponseDTO<List<State>> response = new ResponseDTO<List<State>>();
		try {
			List<State> countries = businessService.findStateByCountry(countryId);
			if (countries != null && countries.size() > 0) {
				response.setMessage(Constants.MSG_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(countries);
			} else {
				response.setMessage(Constants.MSG_NOT_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(countries);
			}
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/get/business_listing_by_categories/{page}")
	public ResponseEntity<ResponseDTO<?>> getBusinessListingByCategories(@RequestBody List<Integer> categoriesIds,
			@PathVariable Integer page) {
		ResponseDTO<List<BusinessListingQueryDTO>> response = new ResponseDTO<List<BusinessListingQueryDTO>>();
		try {
			List<BusinessListingQueryDTO> business = businessService
					.getBusinessListingByCategoriesWithPage(categoriesIds, page);
			if (business != null) {
				response.setMessage(Constants.MSG_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			} else {
				response.setMessage(Constants.MSG_NOT_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			}
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/business/search/{page}")
	public ResponseEntity<ResponseDTO<?>> getBusinessListing(@RequestBody SearchDTO searchDto,
			@PathVariable Integer page) {
		ResponseDTO<List<BusinessListingQueryDTO>> response = new ResponseDTO<List<BusinessListingQueryDTO>>();
		try {
			List<BusinessListingQueryDTO> business = businessService.searchBusinessListing(searchDto, page);
			if (business != null && business.size() > 0) {
				response.setMessage(Constants.MSG_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			} else {
				response.setMessage(Constants.MSG_NOT_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			}
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/advert/search/{page}")
	public ResponseEntity<ResponseDTO<?>> searchAdvert(@RequestBody SearchDTO searchDto, @PathVariable Integer page) {
		ResponseDTO<List<BusinessAdvertDTO>> response = new ResponseDTO<List<BusinessAdvertDTO>>();
		try {
			List<BusinessAdvertDTO> business = businessService.searchBusinessAdverts(searchDto, page);
			if (business != null && business.size() > 0) {
				response.setMessage(Constants.MSG_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			} else {
				response.setMessage(Constants.MSG_NOT_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			}
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/business_service/search/{page}")
	public ResponseEntity<ResponseDTO<?>> searchBusinessServices(@RequestBody SearchDTO searchDto,
			@PathVariable Integer page) {
		ResponseDTO<List<BusinessAdvisorDTO>> response = new ResponseDTO<List<BusinessAdvisorDTO>>();
		try {
			List<BusinessAdvisorDTO> business = businessService.searchBusinessService(searchDto, page);
			if (business != null && business.size() > 0) {
				response.setMessage(Constants.MSG_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			} else {
				response.setMessage(Constants.MSG_NOT_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			}
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/business_service/{businessServiceId}")
	public ResponseEntity<ResponseDTO<?>> getBusinessServicesById(@PathVariable Long businessServiceId) {
		ResponseDTO<BusinessAdvisorDTO> response = new ResponseDTO<BusinessAdvisorDTO>();
		try {
			BusinessAdvisorDTO business = businessService.getBusinessServicesById(businessServiceId);
			if (business != null) {
				response.setMessage(Constants.MSG_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			} else {
				response.setMessage(Constants.MSG_NOT_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			}
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/get/all/business_service_type")
	public ResponseEntity<ResponseDTO<?>> getBusiness_service_type() {
		ResponseDTO<List<BusinessServiceType>> response = new ResponseDTO<List<BusinessServiceType>>();
		try {
			List<BusinessServiceType> list = businessService.findAllBusinessServiceType();
			if (list != null && list.size() > 0) {
				response.setMessage(Constants.MSG_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(list);
			} else {
				response.setMessage(Constants.MSG_NOT_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(list);
			}
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/upload")
	public ResponseEntity<ResponseDTO<?>> uploadFiles(@RequestParam("files") MultipartFile[] files) {
		String message = "";
		logger.info("*****************upload file*****************");
		ResponseDTO<List<String>> response = new ResponseDTO<List<String>>();
		try {
			if (files.length==0) {
				message = "Please select file";
				response.setMessage(message);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}

			List<String> fileNames = new ArrayList<>();

			Arrays.asList(files).stream().forEach(file -> {
				String name=storageService.uploadFile(file);
				fileNames.add(name);
			});

			message = "Uploaded the files successfully";
			response.setResult(fileNames);
			response.setStatus(Constants.STATUS_SUCCESS);
			response.setMessage(message);
			
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch (Exception e) {
			message = "Fail to upload files!";
			response.setMessage(message);
			response.setStatus(Constants.STATUS_FAILED);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
		}
	}
	
	@PostMapping(value = "/broker/search/{page}")
	public ResponseEntity<ResponseDTO<?>> searchBroker(@RequestBody SearchDTO searchDto,@PathVariable Integer page) {
		ResponseDTO<List<BrokerDTO>> response = new ResponseDTO<List<BrokerDTO>>();
		try {
			List<BrokerDTO> business = businessService.searchBroker(searchDto, page);
			if (business != null && business.size() > 0) {
				response.setMessage(Constants.MSG_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			} else {
				response.setMessage(Constants.MSG_NOT_DATA_FOUND);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(business);
			}
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMessage(Constants.MSG_NOT_DATA_FOUND);
			response.setStatus(Constants.STATUS_FAILED);
			response.setResult(null);
			return new ResponseEntity<ResponseDTO<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
