import { Component, ElementRef, Renderer2 } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Meta, Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Defination } from 'src/app/definition/defination';
import { BusinessListingService } from 'src/app/services/business-listing.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {

  email:string='';
  constructor(private meta: Meta, private title: Title,private formBuilder: FormBuilder,private router: Router,private el: ElementRef,private renderer: Renderer2,private businessListingService: BusinessListingService)
  {     
      
  }

  ngOnInit() {
    this.updateMeta();
  }

  updateMeta()
  {
    this.title.setTitle("Tobuz Footer");
    this.meta.updateTag({ name: Defination.META_NAME, content: ""});
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
