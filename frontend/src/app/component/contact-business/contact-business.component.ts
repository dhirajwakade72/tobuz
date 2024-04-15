import { Component, ElementRef, Renderer2, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Meta, Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Defination } from 'src/app/definition/defination';
import { BusinessListingService } from 'src/app/services/business-listing.service';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-contact-business',
  templateUrl: './contact-business.component.html',
  styleUrls: ['./contact-business.component.css']
})
export class ContactBusinessComponent {
  @ViewChild('cnameInput') cnameInput!: ElementRef;
  @ViewChild('city') city!: ElementRef;
  @ViewChild('phone') phone!: ElementRef;
  @ViewChild('email') email!: ElementRef;
  @ViewChild('message') message!: ElementRef;
  sellerContactForm: FormGroup;
  businessListingId:number=0;
  businessListingType:any;
  showMessage:string='';
  isValidForm:boolean=true;
  constructor(private meta: Meta, private title: Title,private dataService:DataService,private router: ActivatedRoute,private router1: Router,private renderer: Renderer2,private formBuilder: FormBuilder,private businessListingService:BusinessListingService){ 
    this.sellerContactForm = this.formBuilder.group({
      name: ['', Validators.required],
      city: ['', Validators.required],
      phone: ['',  [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],
      email: ['', [Validators.required, Validators.email]],
      message: ['', Validators.required]
  });

  }
  ngOnInit() {
    this.router.params.subscribe(params => {  
      this.businessListingType= +params['listing_type'];    
      this.businessListingId = +params['listing_id'];   
      
    });
    this.updateMeta();
  }
  updateMeta()
  {
    this.title.setTitle('Tobuz.com | Contact US');
    this.meta.updateTag({name: 'description', content: 'tobuz.com : Contact US'});
    this.meta.updateTag({ name: 'title', content: this.title.getTitle() });
    this.dataService.addCommanMeta(this.title,this.meta);
  }
  submitContactToseller()
  {
    console.log("submitContactToseller");
    this.validation()
    if(!this.isValidForm)
    {
      console.log("FAIELDss");
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
                        else {                          
                          this.sellerContactForm.value.name='';
                          this.sellerContactForm.value.email='';
                          this.sellerContactForm.value.phone='';
                          this.sellerContactForm.value.message='';
                          this.router1.navigate(['buy-a-business/'+this.businessListingType+'/'+this.businessListingId]);
                          alert("Successfully submitted");                       
                        }                        
                    },
                    error => {
                      alert("FAIELD");
                    });
    }
  }

  public validation()
  {    
    console.log("VALIDA");
    if(this.sellerContactForm.value.name==='')
    {
      this.showMessage="Please enter name "; 
      this.cnameInput.nativeElement.focus();
      this.isValidForm=false;
    } 
    else if(this.sellerContactForm.value.email==='')
    {
      this.showMessage="Please enter email ";
      this.email.nativeElement.focus();
      this.isValidForm=false;
    }   
    else if (!/\S+@\S+\.\S+/.test(this.sellerContactForm.value.email)) {
      this.showMessage = "Please entry valid email";
      this.email.nativeElement.focus();
      this.isValidForm = false;
    }
    else if(this.sellerContactForm.value.phone==='' )
    {
      this.showMessage="Please enter phone number";
      this.phone.nativeElement.focus();
      this.isValidForm=false;
    }else if(this.sellerContactForm.value.phone.length < Defination.MOBILE_MIN_LENGTH || this.sellerContactForm.value.phone.length > Defination.MOBILE_MAX_LENGTH)
    {      
      this.showMessage="Contact number should be between "+Defination.MOBILE_MIN_LENGTH+" to "+Defination.MOBILE_MAX_LENGTH;
        this.phone.nativeElement.focus();
        this.isValidForm=false;   
    }
    else if(this.sellerContactForm.value.city==='')
    {      
        this.showMessage="Please enter city";
        this.city.nativeElement.focus();
        this.isValidForm=false;   
    }        
    else if(this.sellerContactForm.value.message==='')
    {
      this.showMessage="Please message ";
      this.message.nativeElement.focus();
      this.isValidForm=false;
    }
    else
    {
      this.showMessage=''; 
      this.isValidForm=true;
    }
    

  }

  onPress(event: any) {
    const inputValue = event.target.value;
    if (inputValue.length >= 14) {      
      event.preventDefault(); 
    }
  }


}
