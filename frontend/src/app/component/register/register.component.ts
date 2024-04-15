import { Component, Directive, ElementRef, HostListener, NgModule, OnInit, Renderer2, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgModel } from '@angular/forms';
import { BusinessListingService } from 'src/app/services/business-listing.service';
import { Defination } from 'src/app/definition/defination';
import { Meta, Title } from '@angular/platform-browser';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent{
  registerForm: FormGroup;
  loading = false;
  submitted = false;
  generatedCaptcha: string="";
  showPassword: boolean = false;
  showConfirmPassword: boolean = false; 
  isCaptchaValid: boolean = false; 
  
  showMessage:string="";
  @ViewChild('captchaValueId') captchaValueId: any;

  @ViewChild('name') name!: ElementRef;
  @ViewChild('city') city!: ElementRef;
  @ViewChild('phoneNo') phoneNo!: ElementRef;
  @ViewChild('email') email!: ElementRef;
  @ViewChild('password') password!: ElementRef;
  @ViewChild('cpassword') cpassword!: ElementRef;
  @ViewChild('role') role!: ElementRef;
  @ViewChild('captcha') captcha!: ElementRef;
  
  constructor(
    private meta: Meta, private title: Title,
    private formBuilder: FormBuilder,
    private router: Router,
    private el: ElementRef,
    private renderer: Renderer2,
    private businessListingService: BusinessListingService,
    private dataService:DataService)
  { 
  
    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      phoneNo: ['',  [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],
      role: ['RegisterAs', Validators.required],
      email: ['', Validators.required],
      city: ['', Validators.required],      
      password: ['', [Validators.required, Validators.minLength(6)]],
      cpassword: ['', [Validators.required, Validators.minLength(6)]]
  });
}

ngOnInit() {
  this.generateCaptcha();
  this.updateMeta();
}
updateMeta()
{
  this.title.setTitle("Tobuz Register");
  this.meta.updateTag({ name: Defination.META_NAME, content: "Tobuz.com Register"});
  this.dataService.addCommanMeta(this.title,this.meta);
}
  onSubmit() {
    this.submitted = true;
    if(this.validation())
    {
        return;
    }
    else
    {
        // stop here if form is invalid
        this.showMessage='';
        this.loading=true;
        this.businessListingService.registerUser(this.registerForm.value).subscribe(
          (data: any) => {
                        if(data.status=="FAILED")
                        {
                          alert(data.message);
                        }
                        else{
                          alert(data.message);
                          this.router.navigate(['/login']);
                        }
                        
                    },
                    error => {
                      alert("FAIELD");
                        this.loading = false;
                    });
                    
        this.loading=false;
    }
  }

  onPress(event: any) {
    const inputValue = event.target.value;
    if (inputValue.length >= 14) {      
      event.preventDefault(); 
    }
  }

  validation():boolean
  { 
    if(this.registerForm.value.name==='')
    {
      this.showMessage="Please enter name "; 
      this.name.nativeElement.focus();     
      return true;
    }/*else if (/\d/.test(this.registerForm.value.name) || /[^\w\s]/.test(this.registerForm.value.name)) {
      this.showMessage="Name should not contain digits or symbols";
      this.name.nativeElement.focus();
      return true;
    }*/
    else if(this.registerForm.value.email==='')
    {
      this.showMessage="Please enter email ";
      this.email.nativeElement.focus(); 
      return true;
    }
    else if (!/\S+@\S+\.\S+/.test(this.registerForm.value.email)) {
      this.showMessage = "Please entry valid email";
      this.email.nativeElement.focus();
      return true;
    }
    else if(this.registerForm.value.phoneNo==='' )
    {
      this.showMessage="Please enter phone no ";
      this.phoneNo.nativeElement.focus(); 
      return true;
    }
    else if(this.registerForm.value.phoneNo.length < 10 || this.registerForm.value.phoneNo.length > 14)
    {      
        this.showMessage="Please enter a valid phone number and should be 14 digit";
        this.phoneNo.nativeElement.focus(); 
        return true;      
    }
    else if(this.registerForm.value.city==='')
    {
      this.showMessage="Please enter city ";
      this.city.nativeElement.focus(); 
      return true;
    }       
    else if(this.registerForm.value.password==='')
    {
      this.showMessage="Please enter password ";
      this.password.nativeElement.focus(); 
      return true;
    }
    else if(this.registerForm.value.cpassword==='')
    {
      this.showMessage="Please enter confirm password ";
      this.cpassword.nativeElement.focus(); 
      return true;
    }
    else if (!/[!@#$%^&*(),.?":{}|<>]/.test(this.registerForm.value.cpassword)) {
      this.showMessage = "Password should contain at least one symbol or special character";
      this.cpassword.nativeElement.focus(); 
      return true;
    }
    else if(this.registerForm.value.password!==this.registerForm.value.cpassword)
    {
      this.showMessage="Password not matching to confirm password";
      return true;
    }
    else if(this.registerForm.value.role==='' || this.registerForm.value.role==='RegisterAs')
    {
      this.showMessage="Please select register as ";
      this.role.nativeElement.focus(); 
      return true;
    }
    else if(this.captcha.nativeElement.value==='')
    {
      this.showMessage="Please Enter Captcha ";
      this.captcha.nativeElement.focus(); 
      return true;
    }


    this.validateCaptcha();
    if(!this.isCaptchaValid)
    {
      this.showMessage="Invalid Captcha";
      this.generateCaptcha();
      this.captcha.nativeElement.focus(); 
      return true;
    }
    else
    {
      console.log("Capcha="+this.isCaptchaValid);
    }
    
    
    return false;
  }

  refreshCaptcha() {
    
    this.generateCaptcha();
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


  generateCaptcha() {
    // Logic to generate a random text for captcha
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    this.generatedCaptcha = '';
    for (let i = 0; i < 6; i++) {
      this.generatedCaptcha +=" "+characters.charAt(Math.floor(Math.random() * characters.length));
    }    
  }

  validateCaptcha() 
  { 
    this.isCaptchaValid = this.removeSpaces(this.generatedCaptcha)=== this.removeSpaces(this.captcha.nativeElement.value);    
  }

  removeSpaces(value:string) {
    return value.replace(/\s/g, '');
  }

  checkMailExist()
  {
    this.businessListingService.checkMail(this.registerForm.value.email).subscribe((data: any) => {
                    if(data.status=="SUCCESS" && data.result===true)
                    {                      
                      this.showMessage=data.message;                      
                    }
                    else
                    {
                      this.showMessage="";      
                    }                 
                },
                error => {                  
                    this.loading = false;
                });
  }

}
