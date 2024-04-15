import { Component, Renderer2 } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Defination } from 'src/app/definition/defination';
import { ActiveUserDto } from 'src/app/dto/active-user-dto';
import { BusinessListingService } from 'src/app/services/business-listing.service';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-broker',
  templateUrl: './broker.component.html',
  styleUrls: ['./broker.component.css']
})
export class BrokerComponent {

  countryIds: number[] = [];
  currentBusinessListingPage:number=0;
  brokerList:any;
  pageTitle:string="";
  activeUser:ActiveUserDto;
  
  constructor(private meta: Meta, private title: Title,private router: ActivatedRoute,private routerac: Router,private renderer: Renderer2,private businessListingService:BusinessListingService,private dataService:DataService){
    this.activeUser=new ActiveUserDto();
  }

  ngOnInit() {    
    this.activeUser=this.dataService.getActiveUserDetails();
    this.dataService.updateHeaderActiveMenu("SellABusiness");    
    this.updateMeta();
    this.searchBroker();  
    this.updateFilter();    
    this.subscribeData();            
  }
  updateMeta()
  {
    
    this.title.setTitle('Tobuz.com | Brokers');
    this.meta.updateTag({ name: 'description', content: 'Find the best Business Services providers at Tobuz. We offer 1056+ verified Business Consultants, Accountants, and Legal Advisors.'});
    this.meta.updateTag({ name: 'title', content: 'Tobuz.com | Business Services' });
    this.dataService.addCommanMeta(this.title,this.meta);
  }


  updateFilter()
  {
    this.dataService.updateInvestmentFilterVisible(false);
    this.dataService.updateBSTFilterVisible(false); 
    this.dataService.updateCategoriesFilterVisible(false);
  }

  subscribeData()
  {
    this.dataService.countriesIds$.subscribe(
      ids => {
        this.countryIds = ids;     
        this.searchBroker();          
        this.currentBusinessListingPage=0; 
        
      }
    );
  }  

  public searchBroker()
  {   
    //if(this.activeUser.userId===0 || this.activeUser.userId===null) 
   // {
      this.businessListingService.searchBroker(this.countryIds,this.currentBusinessListingPage).subscribe(
        (response) => {                
          this.brokerList=response.result;
        },
        (error) => {
          console.error('Error:', error);
        }
      );
    /*}
    else{
      this.businessListingService.searchWithUserId(this.categoriesIds,this.countryIds,this.currentBusinessListingType,this.currentBusinessListingPage,this.activeUser.userId).subscribe(
        (response) => {                
          this.recentBusinessListing=response.result;
        },
        (error) => {
          console.error('Error:', error);
        }
      );
    }*/
  }



}
