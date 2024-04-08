import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ActiveUserDto } from 'src/app/dto/active-user-dto';
import { BusinessListingService } from 'src/app/services/business-listing.service';
import { CommonService } from 'src/app/services/common.service';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-my-details',
  templateUrl: './my-details.component.html',
  styleUrls: ['./my-details.component.css']
})
export class MyDetailsComponent {
  addButtonUrl:string='';
  addButton:string='';
  loggedInUSerId:number=0;
  allMyListing:any;
  activeUser: ActiveUserDto;
  constructor(private commonService:CommonService,private dataService:DataService,
    private router: Router,private businessService:BusinessListingService){
        
      this.activeUser = new ActiveUserDto(); 
      
  }
  ngOnInit() {    
    this.dataService.checkUserLoginData(); 
    this.activeUser=this.dataService.getActiveUserDetails();
    this.changeButtonName(); 
        
   }
  changeButtonName()
  {
    if(this.activeUser.userRole==='BUYER')
    {
      this.addButtonUrl='user/create-adverts';
      this.addButton='Create Advert';
    }
    else
    {
      this.addButtonUrl='user/add-listing'
      this.addButton='Add Listing';
    }

  }

}
