import { Component, ElementRef, Renderer2 } from '@angular/core';
import { Router } from '@angular/router';
import { CommonService } from 'src/app/services/common.service';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent {
  isUserLoggedIn:boolean=false;
  displayName='';
  constructor(private commonService:CommonService,private router: Router,private dataService:DataService,private elementRef:ElementRef,private renderer: Renderer2){}
  ngOnInit() {
    this.displayName=sessionStorage.getItem("USER_NAME")??'';
    
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

}
