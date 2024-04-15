import { Component, ElementRef, Renderer2, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Meta, Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Defination } from 'src/app/definition/defination';
import { ActiveUserDto } from 'src/app/dto/active-user-dto';
import { ApiResponse } from 'src/app/dto/api-response';
import { BusinessListingService } from 'src/app/services/business-listing.service';
import { CommonService } from 'src/app/services/common.service';
import { DataService } from 'src/app/services/data.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';


@Component({
  selector: 'app-business-listing-details',
  templateUrl: './business-listing-details.component.html',
  styleUrls: ['./business-listing-details.component.css']
})
export class BusinessListingDetailsComponent {
  sellerContactForm: FormGroup;  
  allCountries: any;
  currentBusinessListingType:string="BUSINESS";
  currentBusinessListingId:number=0;
  businessListingDetails:any;
  showMessage:string='';
  isEmailDuplicate:boolean=true;
  activeUser:ActiveUserDto;
  @ViewChild('name') name!: ElementRef;
  @ViewChild('message') city!: ElementRef;
  @ViewChild('phone') phone!: ElementRef;
  @ViewChild('email') email!: ElementRef;

  constructor(private meta: Meta, private title: Title,private routerActive: ActivatedRoute,private dataService:DataService,private commonService:CommonService, private formBuilder: FormBuilder,private router: Router,private businessListingService:BusinessListingService,private renderer: Renderer2,private sessionStorage:SessionStorageService){
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
    this.dataService.updateHeaderActiveMenu("BuyABusiness");   
    this.activeUser=this.dataService.getActiveUserDetails();
    this.routerActive.params.subscribe(params => {
      this.currentBusinessListingType = params['listingtype'];
      this.currentBusinessListingId = +params['listing_id'];     
      this.getDetailsById();     
      this.getCountries();
      
    });
  }

  updateSeoMetaTag()
  {    
    this.title.setTitle('Tobuz.com | '+this.businessListingDetails.title);
    this.meta.updateTag({name: 'description', content: this.businessListingDetails.listingDescription});
    this.meta.updateTag({name: 'title', content: this.businessListingDetails.title});
    this.dataService.addCommanMeta(this.title,this.meta);  
  }

  public getCountries()
  {  
    this.commonService.getCountries().subscribe(
      (response) => {        
        this.allCountries=response.result;            
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

  public getBusinessListingDetailsById()
  {
    this.businessListingService.getBusinessListingDetailsById(this.currentBusinessListingId).subscribe(
      (response) => {        
        this.businessListingDetails=response.result;
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

  onPress(event: any) {
    const inputValue = event.target.value;
    if (inputValue.length >= 14) {      
      event.preventDefault(); 
    }
  }

  public getDetailsById()
  {
    this.businessListingService.getDetailsById(this.currentBusinessListingType,this.currentBusinessListingId,this.activeUser.userId).subscribe(
      (response) => {        
        this.businessListingDetails=response.result; 
         this.updateSeoMetaTag();
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

  addToFavorite()
  {
    const userId=this.sessionStorage.getItem("USER_ID");
    if(userId==undefined)
    {
      this.router.navigate(['/login']);
    }
    else
    {
      this.addFavoriteListing(this.currentBusinessListingId,Number(userId),'add',Defination.BUSINESS_TYPE);      
    }
   
  }
  
  handleImageError(event: any) {
    event.target.src = './assets/images/img/NO-IMAGE-2.jpg';
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
 
public addFavoriteListing(listingid:number,userId:number,action:string,businessType:string)
{   
  this.businessListingService.addFavoriteListing(listingid,userId,action,businessType).subscribe(
    (response:any) => {        
      if (response && response.message &&response.status==='SUCCESS') {          
        this.businessListingDetails.isFovorite=true;
      } else {
        console.error('Invalid response format:', response);
      }
      
    },
    (error) => {
      console.error('Error:', error);
    }
  );
}


}
