import { Component, ElementRef, Renderer2, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Meta, Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Defination } from 'src/app/definition/defination';
import { ActiveUserDto } from 'src/app/dto/active-user-dto';
import { BusinessListingService } from 'src/app/services/business-listing.service';
import { CommonService } from 'src/app/services/common.service';
import { DataService } from 'src/app/services/data.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';
import { Utility } from 'src/app/util/utility';

@Component({
  selector: 'app-advert-listing-details',
  templateUrl: './advert-listing-details.component.html',
  styleUrls: ['./advert-listing-details.component.css']
})
export class AdvertListingDetailsComponent {
  sellerContactForm!: FormGroup;
  currentBusinessListingType:string="BUSINESS";
  currentBusinessListingId:number=0;
  advertListingDetails:any;
  activeUser:ActiveUserDto;
  allCountries: any;
  businessListingDetails:any;
  showMessage:string='';

  @ViewChild('name') name!: ElementRef;  
  @ViewChild('phone') phone!: ElementRef;
  @ViewChild('email') email!: ElementRef;
  @ViewChild('message') message!: ElementRef;

  constructor(private meta: Meta, private title: Title,private router: ActivatedRoute,private router1: Router,private dataService:DataService,private businessListingService:BusinessListingService,private routerActive: ActivatedRoute,private commonService:CommonService, private formBuilder: FormBuilder,private routers: Router,private renderer: Renderer2,private sessionStorage:SessionStorageService){ 
    this.activeUser=new ActiveUserDto();
  }
    ngOnInit() 
      {
        this.dataService.updateHeaderActiveMenu("SellABusiness");
        this.getCountries();
          this.sellerContactForm = this.formBuilder.group({
            name: ['', Validators.required],
            countryCode: ['91', Validators.required],
            phone: ['',  [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],     
            email: ['', Validators.required],
            message: ['', Validators.required]
        });

      this.router.params.subscribe(params => {
        this.currentBusinessListingType = params['listingtype'];
        this.currentBusinessListingId = +params['listing_id'];
        
      });
      
      this.activeUser=this.dataService.getActiveUserDetails();
      this.getDetailsById();
    }

    updateMeta()
    {
      this.title.setTitle('Tobuz.com | '+this.advertListingDetails.title);
      this.meta.updateTag({name: 'description', content: this.advertListingDetails.advertDescription});
      this.meta.updateTag({ name: 'title', content: this.advertListingDetails.title });
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

  public getDetailsById()
  {

    if(this.activeUser.userId===0 || this.activeUser.userId===null) 
    {
      this.businessListingService.getDetailsById(this.currentBusinessListingType,this.currentBusinessListingId,0).subscribe(
        (response) => {        
          this.advertListingDetails=response.result;
          this.updateMeta();
        },
        (error) => {
          console.error('Error:', error);
        }
      );
    }
    else
    {
      this.businessListingService.getDetailsById(this.currentBusinessListingType,this.currentBusinessListingId,this.activeUser.userId).subscribe(
        (response) => {        
          this.advertListingDetails=response.result;
        },
        (error) => {
          console.error('Error:', error);
        }
      );
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

        this.businessListingService.sellerContactSubmit(this.sellerContactForm.value).subscribe(
          (data: any) => {
                        if(data.status=="FAILED")
                        {
                          alert(data.message);
                          if(data.message.includes(this.sellerContactForm.value.email))
                          {
                            this.showMessage=data.message;  
                            this.renderer.setStyle(this.email.nativeElement, 'border', '2px solid red');    
                            this.email.nativeElement.focus();      
                          }
                        }
                        else{                          
                    
                          alert("Successfully submitted");  
                          this.sellerContactForm.reset(); 
                          this.showMessage='';                    
                        }
                        
                    },
                    error => {
                      alert("FAIELD");
                    });
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
    console.log("FPR="+this.sellerContactForm.value);
    if(this.sellerContactForm.value.name==='')
    {
      this.showMessage="Please enter name ";
      this.name.nativeElement.focus();              
      return true;
    }
    else if(this.sellerContactForm.value.phone==='' )
    {
      this.showMessage="Please enter phone number ";
      this.phone.nativeElement.focus();
      return true;
    }
    else if(this.sellerContactForm.value.phone.length < Defination.MOBILE_MIN_LENGTH || this.sellerContactForm.value.phone.length > Defination.MOBILE_MAX_LENGTH)
    {      
        this.showMessage="Contact number should be between "+Defination.MOBILE_MIN_LENGTH+" to "+Defination.MOBILE_MAX_LENGTH;
        this.phone.nativeElement.focus();
        return true;      
    }    
    else if(this.sellerContactForm.value.email==='')
    {
      this.showMessage="Please enter email ";
      this.email.nativeElement.focus();
      return true;
    }
    else if(Utility.validEmail(this.sellerContactForm.value.email))
    {
      this.showMessage="Please enter valid email";
      this.email.nativeElement.focus();
      return true;
    }
    else if(this.sellerContactForm.value.message==='')
    {
      this.showMessage="Please enter message ";
      this.message.nativeElement.focus();
      return true;
    }

    return false;
  }

  addToFavorite()
  {
    const userId=this.sessionStorage.getItem("USER_ID");
    if(userId==undefined)
    {
      this.router1.navigate(['/login']);
    }
    else
    {
      this.addFavoriteListing(this.currentBusinessListingId,Number(userId),'add',Defination.ADVERT_TYPE);      
    }
   
  }

  public addFavoriteListing(listingid:number,userId:number,action:string,businessType:string)
  {   
    this.businessListingService.addFavoriteListing(listingid,userId,action,businessType).subscribe(
      (response:any) => {        
        if (response && response.message &&response.status==='SUCCESS') {          
          this.businessListingDetails.addedToFavourites=true;
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
