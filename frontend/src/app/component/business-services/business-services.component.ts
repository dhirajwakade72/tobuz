import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BusinessListingService } from 'src/app/services/business-listing.service';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-business-services',
  templateUrl: './business-services.component.html',
  styleUrls: ['./business-services.component.css']
})
export class BusinessServicesComponent {
  businessServiceFilterId: number[] = [];
  countryIds: number[] = [];
  investmentFilterValue: string[] = [];
  currentBusinessListingPage:number=0;
  businessServiceList: any;
  currentBusinessListingType:string="ADVERT";
  pageTitle:string="";
  constructor(private router: ActivatedRoute,private businessListingService:BusinessListingService,private dataService:DataService){
  }

  ngOnInit() {
    this.dataService.updateHeaderActiveMenu("SellABusiness");
    this.dataService.updateInvestmentFilterVisible(false);      
    this.dataService.updateBSTFilterVisible(true); 
    this.dataService.updateCategoriesFilterVisible(false);       
    this.subscribeData(); 
    this.searchBusinessService();    
  }
  subscribeData()
  {
    this.dataService.countriesIds$.subscribe(
      ids => {
        this.countryIds = ids;
         
          this.currentBusinessListingPage=0;
        console.log("Location ="+JSON.stringify(ids));
        this.searchBusinessService();
      }
    );
    
    this.dataService.bstFilterValue$.subscribe(
      ids => {
        this.businessServiceFilterId = ids;
        { 
          this.currentBusinessListingPage=0;
        }
        this.searchBusinessService();
      }
    ); 

    
  }

  public searchBusinessService()
  {
    
    this.businessListingService.searchBusinessService(this.businessServiceFilterId,this.countryIds,this.currentBusinessListingPage).subscribe(
      (response) => {                
        this.businessServiceList=response.result;
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

  public prevPage()
  {
    if(this.currentBusinessListingPage!=0)
    {
      this.currentBusinessListingPage--;
      this.searchBusinessService()
    }
    else
    {
      alert("This is main page");
    }    
  }

  public nextPage()
  {    

    if(this.businessServiceList.length<10)
    {
      alert("This is last page");
    }
    else
    {
      this.currentBusinessListingPage++;
      this.searchBusinessService()
    }    
  }  
}
