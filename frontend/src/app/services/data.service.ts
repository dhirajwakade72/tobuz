import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { ActiveUserDto } from '../dto/active-user-dto';
import { DatePipe } from '@angular/common';
import { Meta, Title } from '@angular/platform-browser';
import { SessionStorageService } from './session-storage.service';

@Injectable({
  providedIn: 'root'
})
export class DataService {  

    activeUser: ActiveUserDto;

  categoriesIdsSubject = new BehaviorSubject<number[]>([]);
  categoriesIds$ = this.categoriesIdsSubject.asObservable();
  
  countryIdsSubject = new BehaviorSubject<number[]>([]);
  countriesIds$ = this.countryIdsSubject.asObservable();

  investmentFilterValueSubject = new BehaviorSubject<string[]>([]);
  investmentFilterValue$ = this.investmentFilterValueSubject.asObservable();


  investmentFilterVisibleSubject= new BehaviorSubject(Boolean);
  investmentFilterVisible$ = this.investmentFilterVisibleSubject.asObservable();

  bstFilterValueSubject = new BehaviorSubject<number[]>([]);
  bstFilterValue$ = this.bstFilterValueSubject.asObservable();


  bstFilterVisibleSubject= new BehaviorSubject(Boolean);
  bstFilterVisible$ = this.bstFilterVisibleSubject.asObservable();

  categoryFilterVisibleSubject= new BehaviorSubject(Boolean);
  categoriesFilterVisible$ = this.categoryFilterVisibleSubject.asObservable();

  searchFunctionEnableSubject= new BehaviorSubject(Boolean);
  searchFunctionEnable$ = this.searchFunctionEnableSubject.asObservable();

  

  isUserLoggedInSubject= new BehaviorSubject<boolean>(false);
    isUserLoggedInValue$ = this.isUserLoggedInSubject.asObservable();

  displayNameSubject= new BehaviorSubject(String);
  displayName$ = this.displayNameSubject.asObservable();

  selectedCategoryIdSubject= new BehaviorSubject(new Number);
  selectedCategoryId$ = this.selectedCategoryIdSubject.asObservable();
  

  headerActiveMenuSubject= new BehaviorSubject(String);
  headerActiveMenu$ = this.headerActiveMenuSubject.asObservable();

  constructor(private router: Router,private sessionStorage:SessionStorageService){
    this.activeUser = new ActiveUserDto(); 
   }

  updateCategories(ids: number[]) 
  {
      this.categoriesIdsSubject.next(ids);     
  }

  updateCountry(ids: number[]) 
  {
      this.countryIdsSubject.next(ids);     
  }
  updateInvestmentFilterValue(ids: string[]) 
  {
      this.investmentFilterValueSubject.next(ids);     
  }  

  updateInvestmentFilterVisible(info:any) 
  {
      this.investmentFilterVisibleSubject.next(info);     
  }

  updateBSTFilterValue(ids: number[]) 
  {
      this.bstFilterValueSubject.next(ids);     
  }  

  updateBSTFilterVisible(info:any) 
  {
      this.bstFilterVisibleSubject.next(info);     
  }

  updateCategoriesFilterVisible(info:any) 
  {
      this.categoryFilterVisibleSubject.next(info);     
  }

  updateSearchFunctionEnable(value:any) 
  {
      this.searchFunctionEnableSubject.next(value);     
  }

  updateUserLoggedIn(info:boolean) 
  {
      this.isUserLoggedInSubject.next(info);     
  }

  updateDisplayName(info:any) 
  {
      this.displayNameSubject.next(info);     
  }

  updateHeaderActiveMenu(info:any) 
  {
      this.headerActiveMenuSubject.next(info);     
  }

  updateSelectedCategory(info:Number) 
  {
      this.selectedCategoryIdSubject.next(info);     
  }
 
  checkUserLoginData()
  {    
    const userId=this.sessionStorage.getItem("USER_ID");

    if(userId!==undefined && userId!==null && userId!=='null')
    { 
      this.updateUserLoggedIn(true);
    }
    else
    {  
        this.updateUserLoggedIn(false);         
        this.router.navigate(['/login']);
      
    }
  }

  getActiveUserDetails():ActiveUserDto
  {  
    const userId=this.sessionStorage.getItem("USER_ID");
    const userMail=this.sessionStorage.getItem("USER_MAIL");
    const userName=this.sessionStorage.getItem("USER_NAME");
    const userRole=this.sessionStorage.getItem("USER_ROLE");
    if(userId!==undefined && userId!==null && userId!=='null')
    { 
      this.activeUser.userMail=userMail??'';
      this.activeUser.userRole=userRole??'';
      this.activeUser.userName=userName??'';
      this.activeUser.userId=Number(userId);
      return this.activeUser;
    }
    return this.activeUser; 
  }

  getCurrentTime()
  {
   return new Date();
  }

  addCommanMeta(title:Title,meta:Meta)
  {
     const descriptionTag = meta.getTag('name=description');
     const descriptionContent = descriptionTag ? descriptionTag.content : '';    
    meta.updateTag({charset: 'utf-8', content: 'Tobuz all business listing for buy'});
    meta.updateTag({ name: 'title', content:  title.getTitle()});
    meta.updateTag({name: 'keywords', content: 'Tobuz, Business, Commercial, Brokers, Buyers, Investors, Franchisors, Distress, Sales, Services, Adverts, Listing'});    
    meta.updateTag({name: 'author', content: 'Tobuz'});
    meta.updateTag({name: 'og:type', content: 'website'});
    meta.updateTag({name: 'og:title', content: title.getTitle()});
    meta.updateTag({name: 'og:image', content: './assets/images/tobuz.gif'});
    meta.updateTag({name: 'og:description', content: descriptionContent});
    meta.updateTag({name: 'og:locale', content: 'en_US'});
    meta.updateTag({name: 'article:published_time', content: new Date().toDateString()});
    meta.updateTag({name: 'article:modified_time', content: new Date().toDateString()});
    meta.updateTag({name: 'og:updated_time', content: new Date().toDateString()});
    meta.updateTag({name: 'twitter:card', content: 'summary'});
    meta.updateTag({name: 'twitter:title', content: title.getTitle()});
    meta.updateTag({name: 'twitter:site', content: 'https://tobuz.com'});
    meta.updateTag({name: 'twitter:creator', content: new Date().toDateString()});
    meta.updateTag({name: 'twitter:description', content: descriptionContent});
    meta.updateTag({name: 'twitter:image', content: './assets/images/tobuz.gif'});
    meta.updateTag({name: 'MobileOptimized', content: 'width'});
    meta.updateTag({name: 'HandheldFriendly', content: 'true'});
    meta.updateTag({name: 'theme-color', content: '#415b96'});
    meta.updateTag({name: 'msapplication-navbutton-color', content: '#415b96'});
    meta.updateTag({name: 'apple-mobile-web-app-status-bar-style', content: '#415b96'});
    meta.updateTag({name: 'google-signin-client_id', content: '162231398540-89cts0fgh7ldfijnm19jfjlck961h7kf.apps.googleusercontent.com'});
    meta.updateTag({name: 'og:site_name', content: 'Tobuz'});
    meta.updateTag({name: 'msapplication-navbutton-color', content: '#415b96'}); 
    meta.updateTag({name: 'ahrefs-site-verification', content: 'afcf5b1fa07293af83d5df99bf3765f3cb0ec7655d2b0f7d0b37e96b45a5dddb'});
    meta.updateTag({name: 'google-site-verification', content: ''});
    meta.updateTag({name: 'viewport', content: 'width=device-width, initial-scale=1.0, minimum-scale=1.0'});
    meta.updateTag({name: 'msvalidate.01', content: '7800CEB97C813DA3483E3D5DFB83C11C'});    
    meta.updateTag({itemprop: 'position', content: '1'});
    meta.updateTag({itemprop: 'position', content: '2'});
    meta.updateTag({itemprop: 'position', content: '3'});
    meta.updateTag({itemprop: 'position', content: '+position+'});



  }

}
