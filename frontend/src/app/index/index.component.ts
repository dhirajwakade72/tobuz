import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../services/data.service';
import { Meta, Title } from '@angular/platform-browser';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent {
  constructor(private dataService:DataService,private meta: Meta, private title: Title){
  }
  ngOnInit() {
    this.dataService.updateHeaderActiveMenu("Home");    
  }

  updateMeta() {
    this.title.setTitle("Tobuz.com | Home");
    this.meta.updateTag({ name: 'title', content: "Business for Sale, Investment Opportunities- Buy, Sell, Transform Your Business | Tobuz" });
    this.meta.updateTag({ name: 'description', content: "Tobuz is a Global Marketplace for Buying, Franchising, Selling, Funding, or Transforming your business. Search the extensive database of 36940 Businesses, Consultants and Advisors." });
    this.meta.updateTag({ name: 'keywords', content: 'Tobuz, Business, Commercial, Brokers, Buyers, Investors, Franchisors, Distress, Sales, Services, Adverts, Listing' });
    this.meta.updateTag({ name: 'author', content: 'Tobuz' });
    this.meta.updateTag({ name: 'updatedOn', content: new Date().toDateString() });
  }
}
