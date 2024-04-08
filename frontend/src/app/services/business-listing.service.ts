import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Defination } from '../definition/defination';
import { Observable } from 'rxjs';
import { User } from '../dto/user';
import { ContactForm } from '../dto/contact-form';

@Injectable({
  providedIn: 'root'
})
export class BusinessListingService {

  constructor(private httpClient: HttpClient) { }
  
 /* public getRecentBusinessListing():Observable<any>
  {
    return this.httpClient.get(Defination.HOST+Defination.RECNET_BUSINESS_LISTING);
  }

  public getRecentBusinessListingPage(pageNo:number):Observable<any>
  {
    return this.httpClient.get(Defination.HOST+Defination.GET_BUSINESS_LISTING_WITH_PAGE+"/"+pageNo);
  }

  public getRecentBusinessListingWithTypePage(listingType:string,pageNo:number):Observable<any>
  {
    return this.httpClient.get(Defination.HOST+Defination.GET_BUSINESS_LISTING_BY_TYPE_WITH_PAGE+"/"+listingType+"/"+pageNo);
  }

  public getRecentBusinessListingWithCategoriesPage(categoriesId:number[],pageNo:number):Observable<any>
  {
    return this.httpClient.post(Defination.HOST+Defination.GET_BUSINESS_LISTING_BY_CATEGORIES_WITH_PAGE+"/"+pageNo,categoriesId);
  }*/




  public search(categoriesId:number[],countries:number[],businessType:string,pageNo:number):Observable<any>
  {
    if(categoriesId[0]===0 )
    {
      categoriesId=[];
    }
    const requestBody = {
      categoriesIds: categoriesId,
      countryIds: countries,
      businessType:businessType
    };
    return this.httpClient.post(Defination.HOST+Defination.GET_SEARCH_WITH_PAGE+"/"+pageNo,requestBody);
  }

  public searchBroker(countries:number[],pageNo:number):Observable<any>
  {    
    const requestBody = {      
      countryIds: countries
    };
    return this.httpClient.post(Defination.HOST+Defination.PSOT_BROKER_SEARCH+"/"+pageNo,requestBody);
  }

  public searchWithUserId(categoriesId:number[],countries:number[],businessType:string,pageNo:number,userId:number):Observable<any>
  {
    if(categoriesId[0]===0 )
    {
      categoriesId=[];
    }
    const requestBody = {
      categoriesIds: categoriesId,
      countryIds: countries,
      businessType:businessType,
      userId:userId
    };
    return this.httpClient.post(Defination.HOST+Defination.GET_SEARCH_WITH_PAGE+"/"+pageNo,requestBody);
  }

  public getBusinessListingDetailsById(listing_id:number):Observable<any>
  {
    return this.httpClient.get(Defination.HOST+"/get/business_listing_by_id/"+listing_id);
  }

  public getDetailsById(listing_type:string,listing_id:number,userId:number):Observable<any>
  {
    return this.httpClient.get(Defination.HOST+"/get/listing/"+listing_type+"/"+listing_id+"/"+userId);
  }

  public searchAdverts(categoriesId:number[],countries:number[],investmentFilterValue:string[],businessType:string,pageNo:number,userId:number):Observable<any>
  {
    const requestBody = {
      categoriesIds: categoriesId,
      countryIds: countries,
      investmentFilterValues: investmentFilterValue,
      businessType:businessType,
      userId:userId
    };
    return this.httpClient.post(Defination.HOST+Defination.GET_SEARCH_ADVERT_WITH_PAGE+"/"+pageNo,requestBody);
  }

  public searchBusinessService(businessServiceIds:number[],countryIds:number[],pageNo:number):Observable<any>
  {
    const requestBody = {
      countryIds: countryIds,
      businessServicesId: businessServiceIds
    };
    return this.httpClient.post(Defination.HOST+Defination.GET_SEARCH_SERVICE_WITH_PAGE+"/"+pageNo,requestBody);
  }

  public getBusinessServiceById(businessServiceId:number):Observable<any>
  {
   
    return this.httpClient.get(Defination.HOST+Defination.GET_BUSINESS_SERVICE_BY_ID+"/"+businessServiceId);
  }

  public registerUser(user:User)
  {   
    return this.httpClient.post(Defination.HOST+Defination.POST_REGISTER_USER,user);
  }

  public sellerContactSubmit(cform:ContactForm)
  {   
    return this.httpClient.post(Defination.HOST+Defination.POST_SUMBIT_SELLER_CONTACT,cform);
  }

  public sellerBusinessContactSubmit(cform:ContactForm)
  {   
    return this.httpClient.post(Defination.HOST+Defination.POST_SUMBIT_BUSINESS_CONTACT,cform);
  }


  public loginUser(user:User)
  {    
    return this.httpClient.post(Defination.HOST+Defination.POST_LOGIN_USER,user);
  }

  public checkMail(mail:string)
  {    
    return this.httpClient.get(Defination.HOST+Defination.GET_EMAIL_EXIST+"/"+mail);
  }

  addFavoriteListing(listing_id:number,user_id:number,act:string,businessType:string)
  {
    const requestBody = {
      userId: user_id,
      action:act,
      businessId: listing_id,
      businessType:businessType
    };
    return this.httpClient.post(Defination.HOST+Defination.POST_ADD_FAVORITE_LISTING,requestBody);
  }

  addTestimonial(name:string,email:string,message:string,aboutuser:string,filename:string)
  {
    const requestBody = {
      userName:name,
      email:email,
      description:message,
      companyName:aboutuser,
      fileName:filename

    };
    return this.httpClient.post(Defination.HOST+Defination.POST_ADD_TESTIMONIAL,requestBody);
  }

  addListing(body:any)
  {        
    return this.httpClient.post(Defination.HOST+Defination.POST_ADD_LISTING,body);
  }

  forgotPassword(email:string,password:string)
  {        
    const requestBody = {email:email,password:password};
    return this.httpClient.post(Defination.HOST+Defination.POST_FORGOT_PASSWORD,requestBody);
  }

  sendOpt(body:string)
  {        
    return this.httpClient.post(Defination.HOST+Defination.POST_SEND_OPT,body);
  }

  fetchMyListing(user_id:number)
  {
    return this.httpClient.get(Defination.HOST+Defination.GET_ALL_MY_LISTING+"/"+user_id);
  }
  
}
