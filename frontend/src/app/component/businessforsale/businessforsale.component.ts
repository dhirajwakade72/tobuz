import { Component, Renderer2 } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { ElementSchemaRegistry } from '@angular/compiler';

import { Subscription } from 'rxjs';
import { BusinessListingService } from 'src/app/services/business-listing.service';
import { DataService } from 'src/app/services/data.service';
import { CommonService } from 'src/app/services/common.service';
import { ActiveUserDto } from 'src/app/dto/active-user-dto';
import { Defination } from 'src/app/definition/defination';
import { Meta, Title } from '@angular/platform-browser';
@Component({
  selector: 'app-businessforsale',
  templateUrl: './businessforsale.component.html',
  styleUrls: ['./businessforsale.component.css']
})
export class BusinessforsaleComponent {
  
  categoriesIds: number[] = [];
  countryIds: number[] = [];
  currentBusinessListingPage:number=0;
  recentBusinessListing: any;
  currentBusinessListingType:string="BUSINESS";
  pageTitle:string="";
  pageSubTitle:string="";
  activeUser:ActiveUserDto;
  selectedCategoryForSearch:Number=0;
  searchFunctionEnable:any=true;
  constructor(private meta: Meta, private title: Title,private router: ActivatedRoute,private routerac: Router,private renderer: Renderer2,private businessListingService:BusinessListingService,private dataService:DataService){
    this.activeUser=new ActiveUserDto();
  }

  ngOnInit() {
    this.updateMeta();
    this.dataService.updateHeaderActiveMenu("BuyABusiness");
    this.activeUser=this.dataService.getActiveUserDetails();
    this.router.params.subscribe(params => {
      const listingtype = params['listingtype'];
      this.currentBusinessListingType=listingtype;
      this.setPageTitle();     
    });    
    this.updateFilter();    
    this.subscribeData();
  }

  updateMeta()
  {
    this.title.setTitle('Tobuz.com | Buy a Business');
    this.meta.updateTag({name: 'description', content: 'Tobuz all business listing for buy'});
    this.meta.updateTag({ name: 'title', content: "Business for Sale, Investment Opportunities- Buy, Sell, Transform Your Business | Tobuz" });    
    this.dataService.addCommanMeta(this.title,this.meta);
  }

  updateFilter()
  {
    this.dataService.updateInvestmentFilterVisible(false);
    this.dataService.updateBSTFilterVisible(false); 
  }

  subscribeData()
  {

    this.dataService.searchFunctionEnable$.subscribe(
      value => {
        this.searchFunctionEnable= value;
        if(this.searchFunctionEnable)        
        {
          this.callEnableSearchFunction();
        }       
      }
    );

    this.dataService.categoriesIds$.subscribe(
      ids => {
        this.categoriesIds = ids;
        if(this.searchFunctionEnable) 
        {
          this.search();
          this.currentBusinessListingPage=0;
        }
      }
    );

    this.dataService.countriesIds$.subscribe(
      ids => {
        this.countryIds = ids;        
        if(this.searchFunctionEnable) 
        {          
          this.search();
          this.currentBusinessListingPage=0;
        }     
      }
    );

    this.dataService.selectedCategoryId$.subscribe(
      cat => {
        this.selectedCategoryForSearch = cat;         
        const categoryIdToAdd: number = this.selectedCategoryForSearch.valueOf();
        this.categoriesIds=[];
        this.categoriesIds.push(categoryIdToAdd);
        if(this.searchFunctionEnable && this.selectedCategoryForSearch!=0) 
        {               
          this.search();
        }                   
      }
    );

  }  

  public callEnableSearchFunction()
  {
    this.search();
    this.currentBusinessListingPage=0;
  }

  public prevPage()
  {
    if(this.currentBusinessListingPage!=0)
    {
      this.currentBusinessListingPage--;
    }
    else
    {
      alert("This is main page");
    }
    this.search()
  }

  public nextPage()
  {
    this.currentBusinessListingPage++;
    this.search()
  }


  public search()
  {   
    console.log("Search API called ......");
    if(this.activeUser.userId===0 || this.activeUser.userId===null) 
    {
      this.businessListingService.search(this.categoriesIds,this.countryIds,this.currentBusinessListingType,this.currentBusinessListingPage).subscribe(
        (response) => {                
          this.recentBusinessListing=response.result;
        },
        (error) => {
          console.error('Error:', error);
        }
      );
    }
    else{
      this.businessListingService.searchWithUserId(this.categoriesIds,this.countryIds,this.currentBusinessListingType,this.currentBusinessListingPage,this.activeUser.userId).subscribe(
        (response) => {                
          this.recentBusinessListing=response.result;
        },
        (error) => {
          console.error('Error:', error);
        }
      );
    }
  }

  public addFavoriteListing(listingid:number,userId:number,action:string,businessType:string)
  {   
    this.businessListingService.addFavoriteListing(listingid,userId,action,businessType).subscribe(
      (response:any) => {        
        if (response && response.message &&response.status==='SUCCESS') {
          //alert("Successfully Add As Favorite");          
          const heartElement = document.getElementById('favoriteHeartId');
          this.renderer.addClass(heartElement, 'fa-heart');
          this.renderer.removeClass(heartElement,'fa-heart-o');
        } else {
          console.error('Invalid response format:', response);
        }
        
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

  public addLike(listingtype: string, listingid: number,isFavorite:boolean) {
    
    this.dataService.checkUserLoginData();
    this.activeUser=this.dataService.getActiveUserDetails();
    const likeCountId = document.getElementById(listingtype + '_' + listingid);

    const likeCountId_i = document.getElementById(listingtype + '_' + listingid+'i');
    
    
        
    if (likeCountId_i && likeCountId_i.className.includes('fa-heart-o') && likeCountId) {
      
        let currentCount = parseInt(likeCountId.innerText);       
        currentCount++;        
        likeCountId_i.classList.remove('fa', 'fa-heart-o');
        likeCountId_i.classList.add('fa', 'fa-heart');
        likeCountId_i.style.color = "red";
        likeCountId_i.innerHTML = " "+currentCount.toString();
        this.addFavoriteListing(listingid,this.activeUser.userId,'add',Defination.BUSINESS_TYPE);  
      } 
      else if(likeCountId_i && !likeCountId_i.className.includes('fa-heart-0') && likeCountId)
      {
             
        let currentCount = parseInt(likeCountId.innerText);       
        currentCount--;  
       
       likeCountId_i.classList.remove('fa', 'fa-heart');    
        likeCountId_i.classList.add('fa', 'fa-heart-o');
        likeCountId_i.style.color = "red";
        likeCountId_i.innerHTML = " "+currentCount.toString();      
            
       this.addFavoriteListing(listingid,this.activeUser.userId,'remove',Defination.BUSINESS_TYPE);  
      }
    
}

  setPageTitle() {
    if(this.currentBusinessListingType=='BUSINESS'){
    this.pageTitle="Business for sale";
    this.pageSubTitle="Business For Sale - Buy Business - Investment Opportunities";
    }
    else if(this.currentBusinessListingType=='COMMERCIAL'){
    this.pageTitle="Commercial For Sale";
    this.pageSubTitle="Commercial Property And Land For Sale";
    }
    else if(this.currentBusinessListingType=='DISTRESS'){
    this.pageTitle=" Distress Sale"
    this.pageSubTitle="Buy Distress Business And Property For Sale";
    }
    else {
    this.pageTitle="Franchise";
    this.pageSubTitle="Franchise Business For Sale And Prime Franchise Opportunities";
    }
  }

}
