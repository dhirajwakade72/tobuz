import { Component, ElementRef } from '@angular/core';
import { CommonService } from './services/common.service';
import { Router } from '@angular/router';
import { DataService } from './services/data.service';
import { SessionStorageService } from './services/session-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Tobuz.com';
  displayName='';
  constructor(private commonService:CommonService,private router: Router,private dataService:DataService,private sessionStorage:SessionStorageService){
  }
  ngOnInit() {
    this.displayName=this.sessionStorage.getItem("USER_NAME")??'';
    
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
