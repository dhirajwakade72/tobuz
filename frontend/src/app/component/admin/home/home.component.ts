import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonService } from 'src/app/services/common.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  userRole:string="";
  userMenu: any;
  constructor(private router: ActivatedRoute,private commonService:CommonService,private sessionStorage:SessionStorageService){
  }
  ngOnInit() {
    this.userRole=this.sessionStorage.getItem("USER_ROLE")?? "";
    console.log("ROLL="+this.userRole);
    this.getUserMenu();
    
  }
  public getUserMenu()  {
    
    this.commonService.getUserMenu(this.userRole).subscribe(
      (response) => {                
        this.userMenu=response.result;        
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

}
