import { Component, ElementRef, Renderer2, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Defination } from 'src/app/definition/defination';
import { ActiveUserDto } from 'src/app/dto/active-user-dto';
import { BusinessListingService } from 'src/app/services/business-listing.service';
import { CommonService } from 'src/app/services/common.service';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-comman-contacts',
  templateUrl: './comman-contacts.component.html',
  styleUrls: ['./comman-contacts.component.css']
})
export class CommanContactsComponent {

  @ViewChild('name') name!: ElementRef;
  @ViewChild('message') city!: ElementRef;
  @ViewChild('phone') phone!: ElementRef;
  @ViewChild('email') email!: ElementRef;
  sellerContactForm: FormGroup;  
  allCountries: any;
  currentBusinessListingType:string="BUSINESS";
  currentBusinessListingId:number=0;
  businessListingDetails:any;
  showMessage:string='';
  activeUser:ActiveUserDto;
  constructor(private routerActive: ActivatedRoute,private dataService:DataService,private commonService:CommonService, private formBuilder: FormBuilder,private router: Router,private businessListingService:BusinessListingService,private renderer: Renderer2){
    this.sellerContactForm = this.formBuilder.group({
      name: ['', Validators.required],
      countryCode: ['91', Validators.required],
      phone: ['',  [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],     
      email: ['', Validators.required],
      message: ['', Validators.required],
      businessId: ['', Validators.required] 
  });
  this.activeUser=new ActiveUserDto();
   }
  ngOnInit() {
    this.activeUser=this.dataService.getActiveUserDetails();
    
  }
  onPress(event: any) {
    const inputValue = event.target.value;
    if (inputValue.length >= 14) {      
      event.preventDefault(); 
    }
  }

  submitContactToseller()
  {    
    if(this.validation())
    {
        return;
    }
    else
    {
        this.showMessage='';
        this.sellerContactForm.patchValue({
          businessId: this.currentBusinessListingId
        });
        this.businessListingService.sellerBusinessContactSubmit(this.sellerContactForm.value).subscribe(
          (data: any) => {
                        if(data.status=="FAILED")
                        {
                          alert(data.message);
                          if(data.message.includes(this.sellerContactForm.value.email))
                          {
                            this.showMessage=data.message;  
                            this.renderer.setStyle(this.email.nativeElement, 'border', '2px solid lightred');    
                            this.email.nativeElement.focus();      
                          }
                        }
                        else{
                          
                          this.sellerContactForm.reset();                          
                          alert("Successfully submitted");                       
                        }
                        
                    },
                    error => {
                      alert("FAIELD");
                    });
    }
  }

  validation():boolean
  {    
    if(this.sellerContactForm.value.name==='')
    {
      this.showMessage="Please enter name ";      
      this.name.nativeElement.focus();        
      return true;
    }
    else if(this.sellerContactForm.value.phone==='' )
    {
      this.showMessage="Please Enter phone no ";
      this.phone.nativeElement.focus();   
      return true;
    }
    else if(this.sellerContactForm.value.phone.length < Defination.MOBILE_MIN_LENGTH || this.sellerContactForm.value.phone.length > Defination.MOBILE_MAX_LENGTH)
    {      
        this.showMessage="Phone number should be between "+Defination.MOBILE_MIN_LENGTH+" and "+Defination.MOBILE_MAX_LENGTH+" digit";
        this.phone.nativeElement.focus();   
        return true;      
    }
    else if(this.sellerContactForm.value.email==='')
    {
      this.showMessage="Please enter email ";
      this.email.nativeElement.focus();   
      return true;
    }
    else if (!this.validateEmail(this.sellerContactForm.value.email)) {
      this.showMessage = "Please enter a valid email address";
      this.email.nativeElement.focus();   
      return true;
    }
    else if(this.sellerContactForm.value.message==='')
    {
      this.showMessage="Please enter message ";
      return true;
    }

    return false;
  }



  validateEmail(email: string): boolean {
    // Regular expression for validating an email address
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailPattern.test(email);
  }
}
