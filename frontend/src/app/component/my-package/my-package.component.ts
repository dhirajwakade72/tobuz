import { Component } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Defination } from 'src/app/definition/defination';
import { ActiveUserDto } from 'src/app/dto/active-user-dto';
import { BusinessListingService } from 'src/app/services/business-listing.service';
import { CommonService } from 'src/app/services/common.service';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-my-package',
  templateUrl: './my-package.component.html',
  styleUrls: ['./my-package.component.css']
})
export class MyPackageComponent {

  addButtonUrl:string='';
  addButton:string='';
  loggedInUSerId:number=0;
  allMyListing:any;
  activeUser: ActiveUserDto;
  constructor(private meta: Meta, private title: Title,private commonService:CommonService,private dataService:DataService,
    private router: Router,private businessService:BusinessListingService){
        
      this.activeUser = new ActiveUserDto(); 
      
  }
  ngOnInit() {    
    this.dataService.checkUserLoginData(); 
    this.activeUser=this.dataService.getActiveUserDetails();
    this.changeButtonName(); 
        
   }
   updateMeta()
   {
     this.title.setTitle("Tobuz.com | My Package");
     this.meta.updateTag({ name: Defination.META_NAME, content: "Tobuz.com |  My Package"});
     this.dataService.addCommanMeta(this.title,this.meta);
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
