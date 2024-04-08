import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { ActiveUserDto } from '../dto/active-user-dto';

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

  constructor(private router: Router) {
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
    const userId=sessionStorage.getItem("USER_ID");

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
    const userId=sessionStorage.getItem("USER_ID");
    const userMail=sessionStorage.getItem("USER_MAIL");
    const userName=sessionStorage.getItem("USER_NAME");
    const userRole=sessionStorage.getItem("USER_ROLE");
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

}
