import { Component, Renderer2 } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Defination } from 'src/app/definition/defination';
import { ActiveUserDto } from 'src/app/dto/active-user-dto';
import { BusinessListingService } from 'src/app/services/business-listing.service';
import { DataService } from 'src/app/services/data.service';


@Component({
  selector: 'app-investment-orbuyers',
  templateUrl: './investment-orbuyers.component.html',
  styleUrls: ['./investment-orbuyers.component.css']
})
export class InvestmentORBuyersComponent {

  categoriesIds: number[] = [];
  countryIds: number[] = [];
  investmentFilterValue: string[] = [];
  currentBusinessListingPage:number=0;
  AdvertBusinessListing: any;
  currentBusinessListingType:string="ADVERT";
  searchFuntionEnable:any=false;
  activeUser:ActiveUserDto;
  pageTitle:string="";  
  constructor(private meta: Meta, private title: Title,private router: ActivatedRoute,private renderer: Renderer2,private businessListingService:BusinessListingService,private dataService:DataService){
    this.activeUser=new ActiveUserDto();
  }

  ngOnInit() {
    this.activeUser=this.dataService.getActiveUserDetails();
    this.updateMeta();
    this.dataService.updateHeaderActiveMenu("SellABusiness");
    this.dataService.updateInvestmentFilterVisible(true);
    this.dataService.updateBSTFilterVisible(false);
    this.subscribeData();
    this.searchFuntionEnable=true;    
    this.searchAdverts();

  }
  updateMeta()
  {
    this.title.setTitle('Tobuz.com | Investment Or Buyers');
    this.meta.updateTag({ name: Defination.META_NAME, content: "Find Investment Or Buyers , For Your Business Or Sell It Online"});
    this.meta.updateTag({ name: 'title', content: 'Tobuz.com | Investment Or Buyers'});
    this.dataService.addCommanMeta(this.title,this.meta);
  }

  public callEnableSearchFunction()
  {    
    this.currentBusinessListingPage=0;
    this.searchAdverts(); 
  }

  subscribeData()
  {       

    this.dataService.searchFunctionEnable$.subscribe(
      value => {
        this.searchFuntionEnable= value;
        if(this.searchFuntionEnable)        
        {
          this.callEnableSearchFunction();
        }       
      }
    );

    this.dataService.investmentFilterValue$.subscribe(
      ids => {
        this.investmentFilterValue = ids;        
        if(this.investmentFilterValue.length>0) 
        { 
          this.currentBusinessListingPage=0;
        }        
        if(this.searchFuntionEnable)  
        { 
          this.searchAdverts(); 
        }       
      }
    );
    this.dataService.categoriesIds$.subscribe(
      ids => {
        this.categoriesIds = ids;
        if(this.categoriesIds.length>0) 
        { 
          this.currentBusinessListingPage=0;
        }        
        if(this.searchFuntionEnable)  
        { 
          this.searchAdverts(); 
        } 
      }
    );

    this.dataService.countriesIds$.subscribe(
      ids => {
        this.countryIds = ids;
        {     
          if(this.searchFuntionEnable)  
          { 
            this.searchAdverts(); 
          }          
          
          this.currentBusinessListingPage=0;
        }  

      }
    );
  }
  public searchAdverts()
  {   

    if(this.activeUser.userId===0 || this.activeUser.userId===null) 
    {
      this.businessListingService.searchAdverts(this.categoriesIds,this.countryIds,this.investmentFilterValue,this.currentBusinessListingType,this.currentBusinessListingPage,0).subscribe(
        (response) => {                
          this.AdvertBusinessListing=response.result;
        },
        (error) => {
          console.error('Error:', error);
        }
      );
    }
    else{
      this.businessListingService.searchAdverts(this.categoriesIds,this.countryIds,this.investmentFilterValue,this.currentBusinessListingType,this.currentBusinessListingPage,this.activeUser.userId).subscribe(
        (response) => {                
          this.AdvertBusinessListing=response.result;
        },
        (error) => {
          console.error('Error:', error);
        }
      );
    }


  }

  public prevPage()
  {
    if(this.currentBusinessListingPage!=0)
    {
      this.currentBusinessListingPage--;
      this.searchAdverts()
    }
    else
    {
      alert("This is main page");
    }    
  }

  public nextPage()
  {    

    if(this.AdvertBusinessListing.length<21)
    {
      alert("This is last page");
    }
    else
    {
      this.currentBusinessListingPage++;
      this.searchAdverts()
    }    
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
        this.addFavoriteListing(listingid,this.activeUser.userId,'add',Defination.ADVERT_TYPE);  
      } 
      else if(likeCountId_i && !likeCountId_i.className.includes('fa-heart-0') && likeCountId)
      {
             
        let currentCount = parseInt(likeCountId.innerText);       
        currentCount--;  
       
       likeCountId_i.classList.remove('fa', 'fa-heart');    
        likeCountId_i.classList.add('fa', 'fa-heart-o');
        likeCountId_i.style.color = "red";
        likeCountId_i.innerHTML = " "+currentCount.toString();      
            
      this.addFavoriteListing(listingid,this.activeUser.userId,'remove',Defination.ADVERT_TYPE);  
      }    
  }


public addFavoriteListing(listingid:number,userId:number,action:string,businessType:string)
{   
  this.businessListingService.addFavoriteListing(listingid,userId,action,businessType).subscribe(
    (response:any) => {        
      if (response && response.message &&response.status==='SUCCESS') {               
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

}
