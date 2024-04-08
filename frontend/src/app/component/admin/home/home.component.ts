import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonService } from 'src/app/services/common.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  userRole:string="";
  userMenu: any;
  constructor(private router: ActivatedRoute,private commonService:CommonService){
  }
  ngOnInit() {
    this.userRole=sessionStorage.getItem("USER_ROLE")?? "";
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
