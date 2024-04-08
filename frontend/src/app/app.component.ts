import { Component, ElementRef } from '@angular/core';
import { CommonService } from './services/common.service';
import { Router } from '@angular/router';
import { DataService } from './services/data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Tobuz.com';
  displayName='';
  constructor(private commonService:CommonService,private router: Router,private dataService:DataService){
  }
  ngOnInit() {
    this.displayName=sessionStorage.getItem("USER_NAME")??'';
    
    if(this.displayName.length>1)
    {
      this.dataService.updateUserLoggedIn(true);  
    }
    else
    {
      this.dataService.updateUserLoggedIn(false);      
    }
    
 }
  
}
