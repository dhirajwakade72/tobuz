import { Component, ElementRef, Renderer2, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Meta, Title } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Defination } from 'src/app/definition/defination';
import { BusinessListingService } from 'src/app/services/business-listing.service';
import { DataService } from 'src/app/services/data.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';
import { Utility } from 'src/app/util/utility';

@Component({
  selector: 'app-business-service-details',
  templateUrl: './business-service-details.component.html',
  styleUrls: ['./business-service-details.component.css']
})
export class BusinessServiceDetailsComponent {
  sellerContactForm!: FormGroup;
  advertId:number=0;
  businessService:any;
  @ViewChild('name') name!: ElementRef;  
  @ViewChild('phone') phone!: ElementRef;
  @ViewChild('email') email!: ElementRef;
  @ViewChild('message') message!: ElementRef;
  showMessage:string='';
  constructor(private meta: Meta, private title: Title,private router: ActivatedRoute,private renderer: Renderer2,private businessListingService:BusinessListingService,private formBuilder: FormBuilder,private dataService:DataService,private sessionStorage:SessionStorageService){ }
  ngOnInit() {
    this.router.params.subscribe(params => {      
      this.advertId = +params['service_id'];   
      this.getBusinessServiceById(); 
      this.updateSeoMetaTag();     
    });
    this.dataService.updateHeaderActiveMenu("SellABusiness");
    this.sellerContactForm = this.formBuilder.group({
      name: ['', Validators.required],
      countryCode: ['91', Validators.required],
      phone: ['',  [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],     
      email: ['', Validators.required],
      message: ['', Validators.required]
  });
  }
  
  updateSeoMetaTag()
  {    
    this.title.setTitle('Tobuz.com | '+this.businessService.title);
    this.meta.updateTag({name: 'description', content: this.businessService.listingDescription});
    this.meta.updateTag({name: 'title', content: this.businessService.title});
    this.dataService.addCommanMeta(this.title,this.meta);   
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
                          this.renderer.setStyle(this.email.nativeElement, 'border', '1px solid #707070');                  
                        }
                        
                    },
                    error => {
                      alert("FAIELD");
                    });
    }
  }

  public getBusinessServiceById()
  {
    
    this.businessListingService.getBusinessServiceById(this.advertId).subscribe(
      (response) => { 
             
        this.businessService=response.result; 
        console.log(JSON.stringify(this.businessService));       
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

  addToFavorite()
  {
    const userId=this.sessionStorage.getItem("USER_ID");
    if(userId==undefined)
    {
     // this.router.navigate(['/login']);
    }
    else
    {
     // this.addFavoriteListing(this.currentBusinessListingId,Number(userId),'add',Defination.ADVERT_TYPE);      
    }
   
  }

  public addFavoriteListing(listingid:number,userId:number,action:string,businessType:string)
  {   
    this.businessListingService.addFavoriteListing(listingid,userId,action,businessType).subscribe(
      (response:any) => {        
        if (response && response.message &&response.status==='SUCCESS') {          
         // this.businessListingDetails.addedToFavourites=true;
        } else {
          console.error('Invalid response format:', response);
        }
        
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }
  
  

  validation():boolean
  {    
    if(this.sellerContactForm.value.name==='' || this.sellerContactForm.value.name===' ')
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


}
