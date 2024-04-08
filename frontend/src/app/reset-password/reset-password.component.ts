import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonService } from '../services/common.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent {

  showMessage:boolean=false;
  token:string='';
  constructor(private routerActive: ActivatedRoute,private commonService:CommonService){
        
   }
  ngOnInit() {
    this.routerActive.params.subscribe(params => {
      this.token = params['token']; 
      alert("token="+this.token);
    });
  }
}
