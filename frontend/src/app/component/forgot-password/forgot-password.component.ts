import { Component, ElementRef, Renderer2, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Meta, Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Defination } from 'src/app/definition/defination';
import { BusinessListingService } from 'src/app/services/business-listing.service';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {
  forgotPassForm: FormGroup;
  showMessage:boolean=false;
  showErrorMessage:boolean=false;
  currentOpt:string='';
  buttonName:string='Send Otp';
  displayMessage:string='';
  isOptSent:boolean=false;
  isOptVerified:boolean=false;
  displayErrorMessage:string='';

  showConfirmPassword:boolean=false;
  showPassword:boolean=false;

  @ViewChild('otp') otp!: ElementRef;
  @ViewChild('email') email!: ElementRef;
  @ViewChild('password') password!: ElementRef;
  @ViewChild('cpassword') cpassword!: ElementRef;

  constructor(private meta: Meta, private title: Title,private formBuilder: FormBuilder,private router: Router,private el: ElementRef,private renderer: Renderer2,private businessListingService: BusinessListingService,private dataService:DataService)
  {     
      this.forgotPassForm = this.formBuilder.group({
        email: ['', Validators.required],
        opt :['', Validators.required],
        password:['', Validators.required],
        cpassword:['', Validators.required]        
    });
  }

  ngOnInit() {
    this.updateMeta();
  }

  public onSubmit()
  {
    if(this.buttonName==='Send Otp')
    {
      this.SendOpt();
    }
    else if(this.buttonName==='Verify Otp')
    {
      this.email.nativeElement.disabled = true; 
      if(this.forgotPassForm.value.opt==='')
      {
        this.showErrorMessage=true;
        this.showMessage=false;        
        this.displayErrorMessage='Please Enter OTP';
        this.otp.nativeElement.focus(); 
      }
      else if(this.forgotPassForm.value.opt===this.currentOpt)
      {        
         this.isOptVerified=true;
         this.showMessage=true;
         this.displayMessage="OTP verifed successfully"
         this.showErrorMessage=false;
         this.buttonName='Set Password';
         this.otp.nativeElement.disabled = true;          
      }
      else
      {
        this.showErrorMessage=true;
        this.showMessage=false;        
        this.displayErrorMessage='Please Enter Correct OTP';
        this.otp.nativeElement.focus(); 
      }
    }    
    else if('Set Password'===this.buttonName)
    {
      if(this.forgotPassForm.value.password==='')
      {
        this.showErrorMessage=true;
        this.showMessage=false;        
        this.displayErrorMessage='Please Enter Password';
        this.password.nativeElement.focus(); 
      }
      else if(this.forgotPassForm.value.cpassword==='')
      {
        this.showErrorMessage=true;
        this.showMessage=false;        
        this.displayErrorMessage='Please Enter confirm Password';
        this.cpassword.nativeElement.focus(); 
      }
      else if(this.forgotPassForm.value.password!==this.forgotPassForm.value.cpassword)
      {
        this.showErrorMessage=true;
        this.showMessage=false;        
        this.displayErrorMessage='Password is not matching with confirm password';
        this.password.nativeElement.focus(); 
        this.cpassword.nativeElement.focus();
      }
      else
      {
        this.businessListingService.forgotPassword(this.forgotPassForm.value.email,this.forgotPassForm.value.password).subscribe((data: any) => {
          if(data.status=="SUCCESS")
          {                      
            this.showMessage=true; 
            this.showErrorMessage=false;  
            this.displayMessage=data.message;
            
            setTimeout(() => {
              this.router.navigate(['/login']);
            }, 5000);         
          }
          else
          {
            this.showMessage=false;
            this.showErrorMessage=true;             
            this.displayErrorMessage=data.message;
          } 
        });
      }
  }
    
  }

  resendOtp()
  {
    this.showErrorMessage=false;

        this.businessListingService.sendOpt(this.forgotPassForm.value.email).subscribe((data: any) => 
        {          
          if(data.status=="SUCCESS")
          {                      
            this.showMessage=true;
            this.isOptSent=true;
            this.displayMessage="Resent OTP successfully";
            this.currentOpt=data.result;  
            this.email.nativeElement.disabled = true;         
          }
          else
          {
            this.showErrorMessage=true;
            this.displayErrorMessage=data.message;
          }                
      },
      error => {                  
        this.showMessage=false;
        this.showErrorMessage=true;
        this.displayErrorMessage='Failed...try again'
      });
  }

  public SendOpt()
  {
    console.log("SendOTP");
    if(!this.validation())
    {
        this.showErrorMessage=false;

        this.businessListingService.sendOpt(this.forgotPassForm.value.email).subscribe((data: any) => 
        {          
          if(data.status=="SUCCESS")
          {                      
            this.showMessage=true;
            this.isOptSent=true;
            this.buttonName="Verify Otp"
            this.displayMessage=data.message;
            this.currentOpt=data.result;  
            this.email.nativeElement.disabled = true;         
          }
          else
          {
            this.showErrorMessage=true;
            this.displayErrorMessage=data.message;
          }                
      },
      error => {                  
        this.showMessage=false;
        this.showErrorMessage=true;
        this.displayErrorMessage='Failed...try again'
      });
    }    
  }

  
  validation():boolean
  {     
    if(this.forgotPassForm.value.email==='')
    {
      this.showErrorMessage=true;
      this.displayErrorMessage='Please Enter Email Id';
      this.email.nativeElement.focus(); 
      return true;
    }
    else if (!/\S+@\S+\.\S+/.test(this.forgotPassForm.value.email)) {
      this.showErrorMessage=true;
      this.displayErrorMessage='Please Enter valid Email Id';
      this.email.nativeElement.focus();       
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
      else
      {
        this.showConfirmPassword=!this.showConfirmPassword;
      }

      if(this.showConfirmPassword===true || this.showPassword===true)
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


updateMeta()
{
  this.title.setTitle("Tobuz Forgot Password");
  this.meta.updateTag({ name: Defination.META_NAME, content: "Tobuz.com Forgot Password"});
  this.dataService.addCommanMeta(this.title,this.meta);
}

}
