import { Component, ElementRef, Renderer2, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Meta, Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ActiveUserDto } from 'src/app/dto/active-user-dto';
import { BusinessListingService } from 'src/app/services/business-listing.service';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent {
  forgotPassForm: FormGroup;

  isSuccessMessage:boolean=false;
  isErrorMessage:boolean=false;
  showMessage:string=''; 

  showConfirmPassword:boolean=false;
  showPassword:boolean=false;
  showOldPassword:boolean=false;
  activeUser:ActiveUserDto;
  @ViewChild('oldPassword') oldPassword!: ElementRef;
  @ViewChild('newPassword') newPassword!: ElementRef;
  @ViewChild('confirmPassword') confirmPassword!: ElementRef;

  constructor(private meta: Meta, private title: Title,private formBuilder: FormBuilder,private router: Router,private el: ElementRef,private renderer: Renderer2,private businessListingService: BusinessListingService,private dataService:DataService)
  {     
      this.forgotPassForm = this.formBuilder.group({
        oldPassword: ['', Validators.required],        
        newPassword: ['', Validators.required],
        confirmPassword: ['', Validators.required]
        
    });
    this.activeUser=new ActiveUserDto();
  }

  ngOnInit() {
    this.dataService.checkUserLoginData();
    this.activeUser=this.dataService.getActiveUserDetails();
    this.updateMeta();
  }

  updateMeta()
  {
    this.title.setTitle('Tobuz.com | Add Listing');
    this.meta.updateTag({name: 'description', content: 'tobuz.com : Add Listing page'});
    this.meta.updateTag({ name: 'title', content: this.title.getTitle() });
    this.dataService.addCommanMeta(this.title,this.meta);
  }

  public onSubmit()
  {
    if(!this.validation())
    {
      this.businessListingService.changePassword(this.activeUser.userMail,this.forgotPassForm.value.newPassword,this.forgotPassForm.value.oldPassword).subscribe((data: any) => 
      {          
        if(data.status=="SUCCESS")
        {                      
          this.isErrorMessage=false;
          this.isSuccessMessage=true; 
          this.showMessage=data.message; 
          this.disableInputFields();
        }
        else
        {
          this.isErrorMessage=true;
          this.isSuccessMessage=false; 
          this.showMessage=data.message;
        }                
    },
    error => {                  
      this.isErrorMessage=true;
      this.isSuccessMessage=false; 
      this.showMessage='Failed...try again'
    });
    }   
  }

  disableInputFields()
  {
    this.oldPassword.nativeElement.disabled = true; 
    this.newPassword.nativeElement.disabled = true; 
    this.confirmPassword.nativeElement.disabled = true; 
  }

  validation():boolean
  {  
    this.isErrorMessage=false;
    this.isSuccessMessage=false;       
    if(this.forgotPassForm.value.oldPassword==='')
    {
      this.isErrorMessage=true;      
      this.showMessage='Please Enter Old Password';
      this.oldPassword.nativeElement.focus(); 
      return true;
    }
    else if(this.forgotPassForm.value.newPassword==='')
    {
      this.isErrorMessage=true;      
      this.showMessage='Please Enter New Password';
      this.newPassword.nativeElement.focus(); 
      return true;
    }
    else if(this.forgotPassForm.value.confirmPassword==='')
    {
      this.isErrorMessage=true;      
      this.showMessage='Please Enter Confirm Password';
      this.confirmPassword.nativeElement.focus(); 
      return true;
    }
    else if(this.forgotPassForm.value.confirmPassword !==this.forgotPassForm.value.newPassword)
    {
      this.isErrorMessage=true;      
      this.showMessage='New Password and Confirm Password Not Matching';
      this.newPassword.nativeElement.focus();
      this.confirmPassword.nativeElement.focus(); 
      return true;
    }

    return false;
  }

  togglePasswordVisibility(id:string)
  {    
    const eyeId_ele = document.getElementById(id+'_eyeId'); 
      if(id==='password')
      {      
        this.showPassword=!this.showPassword;
      }
      else if(id==='oldpassword')
      {
        this.showOldPassword=!this.showOldPassword;
      }
      else
      {
        this.showConfirmPassword=!this.showConfirmPassword;
      }

      if(this.showConfirmPassword===true || this.showPassword===true || this.showOldPassword )
      {
        this.renderer.addClass(eyeId_ele, 'fa-eye');
        this.renderer.removeClass(eyeId_ele, 'fa-eye-slash');
      }
      else
      { 
        this.renderer.addClass(eyeId_ele, 'fa-eye-slash');
        this.renderer.removeClass(eyeId_ele, 'fa-eye'); 
      }
  }
}
