import { Component } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { Defination } from 'src/app/definition/defination';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.css']
})
export class FaqComponent {

  constructor(private meta: Meta, private title: Title,private dataService:DataService){
  }
  ngOnInit() {  
    this.updateMeta();
  }
  updateMeta()
  {
    this.title.setTitle("Tobuz FAQ");
    this.meta.updateTag({ name: Defination.META_NAME, content: "To get detailed information on a business for sale listed on our site, feel free to contact the seller or the broker directly using the contact seller links provided with each listing.Kindly note that we are not a business broker or an agent. We are simply an advertising service that you, the seller and the broker can use to express interest in buying and selling a business.Once you contact a seller, it is possible that the sellers may want to know more about you before they disclose any financial or other information about their business. So please be prepared for the same.Some Sellers/ Brokers may also insist that you sign an NDA or Non Disclosure Agreement to protect their business information from their competitors or the media."});
    this.dataService.addCommanMeta(this.title,this.meta);
  }
}
