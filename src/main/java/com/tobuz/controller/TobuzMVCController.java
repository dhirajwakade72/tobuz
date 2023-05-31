package com.tobuz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.tobuz.model.BusinessListing;
import com.tobuz.object.BusinessAdvertDTO;
import com.tobuz.object.BusinessFeatureDTO;
import com.tobuz.object.BusinessListingDTO;
import com.tobuz.object.BusinessListingFeatureInfoDTO;
import com.tobuz.object.TobuzPackageDTO;
import com.tobuz.object.UserPackageInfoDTO;

@Controller
public class TobuzMVCController {

	 @RequestMapping(value="/",method=RequestMethod.GET) 
	 public ModelAndView in () {
	      ModelAndView modelAndView = new ModelAndView();
	      modelAndView.setViewName("index");
	      return modelAndView;
	  }
		  
	 @RequestMapping(value="/index",method=RequestMethod.GET) 
	 public ModelAndView index () {
	      ModelAndView modelAndView = new ModelAndView();
	      modelAndView.setViewName("index");
	      return modelAndView;
	  }
		  
		  
		  @RequestMapping(value="/login",method=RequestMethod.GET) 
			 public ModelAndView getLogin () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("login");
			      return modelAndView;
			  }
				  
		  
		
		  @RequestMapping(value="/register",method=RequestMethod.GET) 
			 public ModelAndView register () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("register");
			      return modelAndView;
			  }
		  
		  				
		  @RequestMapping(value="/about",method=RequestMethod.GET) 
			 public ModelAndView about () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("about");
			      return modelAndView;
			  }
		  @RequestMapping(value="/faq",method=RequestMethod.GET) 
			 public ModelAndView faq () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("faq");
			      return modelAndView;
			  }
		  @RequestMapping(value="/testimonial",method=RequestMethod.GET) 
			 public ModelAndView testimonial () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("testimonial");
			      return modelAndView;
			  }
		  
		  @RequestMapping(value="/blog",method=RequestMethod.GET) 
			 public ModelAndView blog () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("blog");
			      return modelAndView;
			  }
		  
		 
	  @RequestMapping(value="/businessForSale",method=RequestMethod.GET) 
			 public ModelAndView businessForSale () {
			      ModelAndView modelAndView = new ModelAndView();
			      HttpSession session = getSession();
			      System.out.println("session.getAttribute(\"appUser\")  " +session.getAttribute("appUser") );
					if ( session.getAttribute("appUser") != null) {
						modelAndView.setViewName("businessForSale");
					}
					else {
						 modelAndView.setViewName("login");
					}
					
			      return modelAndView;
			  }
		  
		
		  @RequestMapping(value="/buyerHome",method=RequestMethod.GET) 
			 public ModelAndView buyerHome () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("buyerHome");
			      return modelAndView;
			  }
		  
		  @RequestMapping(value="/sellerHome",method=RequestMethod.GET) 
			 public ModelAndView sellerHome () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("sellerHome");
			      return modelAndView;
			  }
		  
		  @RequestMapping(value="/adminDashboard",method=RequestMethod.GET) 
			 public ModelAndView adminDashboard () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("adminDashboard");
			      return modelAndView;
			  }
		  @RequestMapping(value="/myAdvertsBuyeDashboard",method=RequestMethod.GET) 
			 public ModelAndView myadvertsBuyer () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("myAdvertsBuyeDashboard");
			      return modelAndView;
			  }
		  
		  @RequestMapping(value="/myPackagesBuyer",method=RequestMethod.GET) 
			 public ModelAndView myPackagesBuyer () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("myPackagesBuyer");
			      return modelAndView;
			  }
		  @RequestMapping(value="/myPackagesSeller",method=RequestMethod.GET) 
			 public ModelAndView myPackagesSeller () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("myPackagesSeller");
			      return modelAndView;
			  }
		  
		  @RequestMapping(value="/myPackagesBroker",method=RequestMethod.GET) 
			 public ModelAndView myPackagesBroker () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("myPackagesBroker");
			      return modelAndView;
			  }
		  
		  
		  @RequestMapping(value="/myFavouritesBuyer",method=RequestMethod.GET) 
			 public ModelAndView myFavouritesBuyer () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("myFavouritesBuyer");
			      return modelAndView;
			  }
		  @RequestMapping(value="/myFavouritesSeller",method=RequestMethod.GET) 
			 public ModelAndView myFavouritesSeller () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("myFavouritesSeller");
			      return modelAndView;
			  }
		  @RequestMapping(value="/myadvertsSeller",method=RequestMethod.GET) 
			 public ModelAndView myadvertsSeller () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("myAdvertsSellerDashboard");
			      return modelAndView;
			  }
		  
		  
		  @RequestMapping(value="/brokerHome",method=RequestMethod.GET) 
			 public ModelAndView brokerHome () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("brokerHome");
			      return modelAndView;
			  }
		  @RequestMapping(value="/businessAdvisory",method=RequestMethod.GET) 
			 public ModelAndView  businessAdvisory () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("businessAdvisory");
			      return modelAndView;
			  }
		  
		  @RequestMapping(value="/business-setup",method=RequestMethod.GET) 
			 public ModelAndView    businessSetup () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("businessSetup");
			      return modelAndView;
			  }
		  @RequestMapping(value="/business-valuation",method=RequestMethod.GET) 
			 public ModelAndView    businessValuation () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("businessValuation");
			      return modelAndView;
			  }
		  
		  @RequestMapping(value="/createBusinessListings",method=RequestMethod.GET) 
			 public ModelAndView    createBusinessListings () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("createBusinessListings");
			      return modelAndView;
			  }
		  @RequestMapping(value="/franchiseeOpportunitiesGrid",method=RequestMethod.GET) 
			 public ModelAndView    franchiseeOpportunitiesGrid () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("franchiseeOpportunitiesGrid");
			      return modelAndView;
			  }
		  @RequestMapping(value="/businesServices",method=RequestMethod.GET) 
			 public ModelAndView    businesServices () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("businesServices");
			      return modelAndView;
			  }
		  @RequestMapping(value="/commercialForSaleGrid",method=RequestMethod.GET) 
			 public ModelAndView    commercialForSaleGrid () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("commercialForSaleGrid");
			      return modelAndView;
			  }
		  
		  @RequestMapping(value="/adminListings",method=RequestMethod.GET) 
			 public ModelAndView    adminListings () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("adminListings");
			      return modelAndView;
			  }
		  
		  @RequestMapping(value="/adminAdverts",method=RequestMethod.GET) 
			 public ModelAndView    adminAdverts () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("adminAdverts");
			      return modelAndView;
			  }
		  @RequestMapping(value="/adminPackages",method=RequestMethod.GET) 
			 public ModelAndView    adminPackages () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("adminPackages");
			      return modelAndView;
			  }
		  @RequestMapping(value="/adminData",method=RequestMethod.GET) 
			 public ModelAndView    adminData () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("adminData");
			      return modelAndView;
			  }
		  
		  @RequestMapping(value="/adminUsers",method=RequestMethod.GET) 
			 public ModelAndView    adminUsers () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("adminUsers");
			      return modelAndView;
			  }
		  
		  
		  @RequestMapping(value="/adminDashboardPayment",method=RequestMethod.GET) 
			 public ModelAndView    adminDashboardPayment () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("adminDashboardPayment");
			      return modelAndView;
			  }
		  
		  
		  @RequestMapping(value="/adminUserRequests",method=RequestMethod.GET) 
			 public ModelAndView    adminUserRequests () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("adminUserRequests");
			      return modelAndView;
			  }
		  
		  
		  @RequestMapping(value="/distress",method=RequestMethod.GET) 
			 public ModelAndView    distress () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("distress");
			      return modelAndView;
			  }
		  @RequestMapping(value="/brokers",method=RequestMethod.GET) 
			 public ModelAndView    brokers () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("brokers");
			      return modelAndView;
			  }
		 
		  @RequestMapping(value="/contact",method=RequestMethod.GET) 
			 public ModelAndView    contact () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("contact");
			      return modelAndView;
			  }
		  
		  @RequestMapping(value="/adminContactUs",method=RequestMethod.GET) 
			 public ModelAndView    adminContactUs () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("adminContactUs");
			      return modelAndView;
			  }
		  
		  @RequestMapping(value="/adminMessages",method=RequestMethod.GET) 
			 public ModelAndView    adminMessages () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("adminMessages");
			      return modelAndView;
			  }
		  
		  @RequestMapping(value="/myMessagesSeller",method=RequestMethod.GET) 
			 public ModelAndView    myMessagesSeller () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("myMessagesSeller");
			      return modelAndView;
			  }
		  @RequestMapping(value="/myMessagesBuyer",method=RequestMethod.GET) 
			 public ModelAndView    myMessagesBuyer () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("myMessagesBuyer");
			      return modelAndView;
			  }
		  
	 
		  @RequestMapping(value="/contactSeller",method=RequestMethod.GET) 
			 public ModelAndView    contactSeller () {
			      ModelAndView modelAndView = new ModelAndView();
			      HttpSession session = getSession();
			      System.out.println("session.getAttribute(\"appUser\")  " +session.getAttribute("appUser") );
					if ( session.getAttribute("appUser") != null) {
						  modelAndView.setViewName("contactSeller");
					}
					else {
						 modelAndView.setViewName("login");
					}
			    
			      return modelAndView;
			  }
		  @RequestMapping(value="/termCondition",method=RequestMethod.GET) 
			 public ModelAndView    termCondition () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("termCondition");
			      return modelAndView;
			  }
		  
		  @RequestMapping(value="/howitworks",method=RequestMethod.GET) 
			 public ModelAndView    howitworks () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("howitworks");
			      return modelAndView;
			  }
		  
		@RequestMapping(value="/privacyPolicy",method=RequestMethod.GET) 
			 public ModelAndView    privacyPolicy () {
			      ModelAndView modelAndView = new ModelAndView();
			      modelAndView.setViewName("privacyPolicy");
			      return modelAndView;
			  }
		  
		@RequestMapping(value="/realEstate",method=RequestMethod.GET) 
		 public ModelAndView    realEstate () {
		      ModelAndView modelAndView = new ModelAndView();
		      modelAndView.setViewName("commercialForSaleGrid");
		      return modelAndView;
		  }
		
		@RequestMapping(value="/adminCategories",method=RequestMethod.GET) 
		 public ModelAndView    adminCategories () {
		      ModelAndView modelAndView = new ModelAndView();
		      modelAndView.setViewName("adminCategories");
		      return modelAndView;
		  }
		@RequestMapping(value="/myListingsSllerDashboard",method=RequestMethod.GET) 
		 public ModelAndView    myListingsSllerDashboard () {
		      ModelAndView modelAndView = new ModelAndView();
		      modelAndView.setViewName("myListingsSllerDashboard");
		      return modelAndView;
		  }
		
		
	  
		
		public static HttpSession getSession() {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			return attr.getRequest().getSession(true); // true == allow create
		}

		
		
			 
}
