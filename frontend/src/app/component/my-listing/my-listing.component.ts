import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ActiveUserDto } from 'src/app/dto/active-user-dto';
import { BusinessListingService } from 'src/app/services/business-listing.service';
import { CommonService } from 'src/app/services/common.service';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-my-listing',
  templateUrl: './my-listing.component.html',
  styleUrls: ['./my-listing.component.css']
})
export class MyListingComponent {

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
    this.fetchMyListing();
     
   }

   public async fetchMyListing() {
    try {
      const response: any = await this.businessService.fetchMyListing(this.activeUser.userId).toPromise();
      if (response.status === "SUCCESS") {
        console.log("SSS=" + JSON.stringify(response.result));
        this.allMyListing = response.result;
      } else {
        alert("No listing found");
      }
    } catch (error) {
      console.error('Error:', error);
    }
  }

}
