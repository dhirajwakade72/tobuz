import { Component, ElementRef, Renderer2 } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { BusinessListingService } from 'src/app/services/business-listing.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {

  email:string='';
  constructor(private formBuilder: FormBuilder,private router: Router,private el: ElementRef,private renderer: Renderer2,private businessListingService: BusinessListingService)
  {     
      
  }

  ngOnInit() {
    
  }

  onSubmit()
  {
    if(!this.validation())
    {
      alert("Succssully Submitted");
      this.email='';
    }
  }

  validation():boolean
  {     
    if(this.email==='')
    {
     alert("Please enter email");
      return true;
    }
    else if (!/\S+@\S+\.\S+/.test(this.email)) {
      alert("Please enter  valid email");
      
      return true;
    } 
    return false;
  }

}
