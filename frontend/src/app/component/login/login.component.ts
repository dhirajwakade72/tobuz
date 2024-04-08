import { Component, Renderer2 } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BusinessListingService } from 'src/app/services/business-listing.service';
import { CommonService } from 'src/app/services/common.service';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  showPassword:boolean=false;
  constructor(private formBuilder: FormBuilder,private renderer: Renderer2,private router: Router,private businessListingService: BusinessListingService,private dataService:DataService)
  { 
      // redirect to home if already logged in
      /*if (this.authenticationService.currentUserValue) { 
          this.router.navigate(['/']);
      }*/

      this.loginForm = this.formBuilder.group({     
        email: ['', Validators.required],         
        password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }


onSubmit() {  
  if(this.validation())
  {
      return;
  }
  else
  {
      // stop here if form is invalid      
      this.businessListingService.loginUser(this.loginForm.value).subscribe(
        (data: any) => {
                     if(data.status=="FAILED")
                      {
                        alert(data.message);
                      }
                      else{                       
                        alert("Logined in successfully");
                        this.dataService.updateUserLoggedIn(true);
                        this.dataService.updateDisplayName(data.result.name);
                        this.addUserSession(data.result);
                        if("ADMIN"===data.result.userDefaultRole)
                        this.router.navigate(['/admin/home']);
                        else
                        this.router.navigate(['/user/my-dashboard']);
                      }
                      
                  },
                  (error:any) => {
                    alert("Failed to login");                     
                  });
  }
}

validation():boolean
{  
  if(this.loginForm.value.email==='')
  {
    alert("Please enter email ");
    return true;
  }
  else if(this.loginForm.value.password==='')
  {
    alert("Please enter password ");
    return true;
  }
  return false;
}

addUserSession(data:any)
{
  console.log("User="+JSON.stringify(data));
  if (data) {
    sessionStorage.setItem("USER_MAIL", data.email);
    sessionStorage.setItem("USER_ID", data.id);
    sessionStorage.setItem("USER_NAME", data.name);
    sessionStorage.setItem("USER_ROLE", data.userDefaultRole);
  }
  console.log("USER_NAME="+sessionStorage.getItem("USER_NAME"));
 
}

togglePasswordVisibility()
  {    
    const eyeId_ele = document.getElementById('password_eyeId');
    this.showPassword=!this.showPassword;      
      if(this.showPassword===true)
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
