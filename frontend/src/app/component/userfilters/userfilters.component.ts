import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonService } from 'src/app/services/common.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';

@Component({
  selector: 'app-userfilters',
  templateUrl: './userfilters.component.html',
  styleUrls: ['./userfilters.component.css']
})
export class UserfiltersComponent {

  userRole:string="";
  userMenu: any;
  currentUrl:string='';
  activeMenu: string = '';
  constructor(private router: ActivatedRoute,private commonService:CommonService,private sessionStorage:SessionStorageService){
  }
  ngOnInit() {
    const currentPath = this.router.snapshot.url.join('/'); 
    this.currentUrl="/"+currentPath;
    this.userRole=this.sessionStorage.getItem("USER_ROLE")?? "";
    this.getUserMenu();  
    console.log("cu="+this.currentUrl);
    
  }
  public getUserMenu()  {   
    
    console.log("ROLE="+this.userRole);
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
