import { Component, ElementRef, Renderer2 } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BusinessListingService } from 'src/app/services/business-listing.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent {
  forgotPassForm: FormGroup;
  showMessage:boolean=false;
  constructor(private formBuilder: FormBuilder,private router: Router,private el: ElementRef,private renderer: Renderer2,private businessListingService: BusinessListingService)
  {     
      this.forgotPassForm = this.formBuilder.group({
        oldPassword: ['', Validators.required],        
        newPassword: ['RegisterAs', Validators.required],
        confirmPassword: ['', Validators.required]
        
    });
  }

  ngOnInit() {
    
  }

  public onSubmit()
  {

  }

  public validated()
  {

  }
}
