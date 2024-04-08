import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonService } from 'src/app/services/common.service';

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
  constructor(private router: ActivatedRoute,private commonService:CommonService){
  }
  ngOnInit() {
    const currentPath = this.router.snapshot.url.join('/'); 
    this.currentUrl="/"+currentPath;
    this.userRole=sessionStorage.getItem("USER_ROLE")?? "";
    this.getUserMenu();  
    console.log("cu="+this.currentUrl);
    
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
