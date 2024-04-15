import { Component, ElementRef, Renderer2 } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { CommonService } from 'src/app/services/common.service';
import { DataService } from 'src/app/services/data.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent {
  isUserLoggedIn:boolean=false;
  displayName='';
  constructor(private meta: Meta, private title: Title ,private commonService:CommonService,private router: Router,private dataService:DataService,private elementRef:ElementRef,private renderer: Renderer2,private sessionStorage:SessionStorageService){}
  ngOnInit() {
    this.displayName=this.sessionStorage.getItem("USER_NAME")??'';
    
    if(this.displayName.length>1)
    {
      this.dataService.updateUserLoggedIn(true);  
    }
    else
    {
      this.dataService.updateUserLoggedIn(false);  
      this.router.navigate(['/']);    
    }    
  }  

  updateMeta() {
    this.title.setTitle("Tobuz.com | User Home");
    this.meta.updateTag({ name: 'description', content: "Tobuz is a Global Marketplace for Buying, Franchising, Selling, Funding, or Transforming your business. Search the extensive database of 36940 Businesses, Consultants and Advisors." });
    this.dataService.addCommanMeta(this.title,this.meta);
  }

  

}
