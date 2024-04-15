import { Component, ElementRef, Renderer2 } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ActiveUserDto } from 'src/app/dto/active-user-dto';

import { CommonService } from 'src/app/services/common.service';
import { DataService } from 'src/app/services/data.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent {

  allCategories:any;  
  allCountry: any;
  displayname:any="" ;  
  countries = ["Afghanistan","Albania","Algeria","Andorra","Angola","Anguilla","Antigua &amp; Barbuda","Argentina","Armenia","Aruba","Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia","Bosnia &amp; Herzegovina","Botswana","Brazil","British Virgin Islands","Brunei","Bulgaria","Burkina Faso","Burundi","Cambodia","Cameroon","Cape Verde","Cayman Islands","Chad","Chile","China","Colombia","Congo","Cook Islands","Costa Rica","Cote D Ivoire","Croatia","Cruise Ship","Cuba","Cyprus","Czech Republic","Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Estonia","Ethiopia","Falkland Islands","Faroe Islands","Fiji","Finland","France","French Polynesia","French West Indies","Gabon","Gambia","Georgia","Germany","Ghana","Gibraltar","Greece","Greenland","Grenada","Guam","Guatemala","Guernsey","Guinea","Guinea Bissau","Guyana","Haiti","Honduras","Hong Kong","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland","Isle of Man","Israel","Italy","Jamaica","Japan","Jersey","Jordan","Kazakhstan","Kenya","Kuwait","Kyrgyz Republic","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macau","Macedonia","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Mauritania","Mauritius","Mexico","Moldova","Monaco","Mongolia","Montenegro","Montserrat","Morocco","Mozambique","Namibia","Nepal","Netherlands","Netherlands Antilles","New Caledonia","New Zealand","Nicaragua","Niger","Nigeria","Norway","Oman","Pakistan","Palestine","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal","Puerto Rico","Qatar","Reunion","Romania","Russia","Rwanda","Saint Pierre &amp; Miquelon","Samoa","San Marino","Satellite","Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","South Africa","South Korea","Spain","Sri Lanka","St Kitts &amp; Nevis","St Lucia","St Vincent","St. Lucia","Sudan","Suriname","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Timor L'Este","Togo","Tonga","Trinidad &amp; Tobago","Tunisia","Turkey","Turkmenistan","Turks &amp; Caicos","Uganda","Ukraine","United Arab Emirates","United Kingdom","Uruguay","Uzbekistan","Venezuela","Vietnam","Virgin Islands (US)","Yemen","Zambia","Zimbabwe"];
  filteredCountries: string[] = [];
  userInput: string = ''; 
  isUSerLoggedIn:boolean=false;
  selectedCategory: string = '';
  selectedMenu: any = '';
  selectCategoryId:number=0;
  addButtonUrl:string='';
  addButton:string='';
  activeUser:ActiveUserDto;
  constructor(private commonService:CommonService,private router: Router,private dataService:DataService,private elementRef:ElementRef,private renderer: Renderer2,private sessionStorage:SessionStorageService){
    this.activeUser=new ActiveUserDto();
  }
  ngOnInit() {

    this.activeUser=this.dataService.getActiveUserDetails();
    this.checkUserLoginData();    
     this.subscribe();
     this.getCategories();
     this.getCountry();     
     this.showUserData();
     if(this.activeUser.userId!==0)
     { 
     this.changeButtonName();
     }     
    
  }
  
  changeButtonName()
  {
    console.log("BUUS="+this.activeUser.userRole);
    if(this.activeUser.userRole==='BUYER')
    {
      this.addButtonUrl='user/create-adverts';
      this.addButton='Create Advert';
    }
    else if(this.activeUser.userRole==='SELLER')
    {      
      this.addButtonUrl='user/add-listing'
      this.addButton='Add Listing';
    }
  }

  checkUserLoginData()
  {    
    const userId=this.sessionStorage.getItem("USER_ID");    
    if(userId!==undefined || userId!==null)
    {
      this.isUSerLoggedIn=true;
      this.activeUser=this.dataService.getActiveUserDetails();
      this.changeButtonName();
    }
  }

  public logout()
  {
    this.sessionStorage.removeItem("USER_NAME");
    this.sessionStorage.removeItem("USER_ID");
    this.sessionStorage.clear();
    this.router.navigate(['/login']);
    this.isUSerLoggedIn=false;
    this.showUserData();
  }
  
  public showUserData()
  { 
    const login_register_element = document.getElementById('login_register');     
    if(this.isUSerLoggedIn)
    {      
      this.renderer.addClass(login_register_element, 'd-lg-none');  
      this.displayname=this.sessionStorage.getItem("USER_NAME")??"";
      const profile_add_listing_element = document.getElementById('profile_add_listing');
      this.renderer.removeClass(profile_add_listing_element, 'd-lg-none');
    }
    else
    {      
      this.renderer.removeClass(login_register_element, 'd-lg-none');  
      const profile_add_listing_element = document.getElementById('profile_add_listing');
      this.renderer.addClass(profile_add_listing_element, 'd-lg-none');
    }
  }
  
  public getCategories()
  {
    
    this.commonService.getCategories().subscribe(
      (response) => {        
        this.allCategories=response.result;      
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

  public getCountry()
  {
    
    this.commonService.getCountries().subscribe(
      (response) => {        
        this.allCountry=response.result;      
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }
 

  filterCountries(val: string): string[] {
    return this.countries.filter(country =>
      country.toLowerCase().includes(val.toLowerCase())
    );
  }
  selectCountry(country: string): void {
    if(country.length>=3)
    {
      this.userInput = country;
      this.filteredCountries = [];
    }
  }

  isRouteActive(route: string): boolean {
    return this.router.isActive(route, true);
  }

  subscribe()
  {
    this.dataService.isUserLoggedInValue$.subscribe(
        data => {
          this.isUSerLoggedIn = data;
          this.activeUser=this.dataService.getActiveUserDetails();
          this.showUserData();
          this.changeButtonName();          
        }
      );

      this.dataService.displayName$.subscribe(
        data => {
          this.displayname = data;
        }
      );

      this.dataService.headerActiveMenu$.subscribe(
        data => {
          this.selectedMenu = data;          
        }
      );
  }
  
  onInput(event: any): void {
    const userInput = event.target.value; 
     
    const selectCategoryId = this.allCategories.find((c: { name: string }) => c.name === userInput).id; 
    if(selectCategoryId!=0)
    {
      this.selectCategoryId=selectCategoryId;
      //this.dataService.updateSelectedCategory(selectCategoryId);
    }
    this.router.navigate(['buy-a-business/BUSINESS']);
    
  }

  onSubmit(): void {
   
    if(this.selectCategoryId!=0)
    {      
      this.dataService.updateSelectedCategory(this.selectCategoryId);
    }
    this.router.navigate(['buy-a-business/BUSINESS']);
    
  }

}
