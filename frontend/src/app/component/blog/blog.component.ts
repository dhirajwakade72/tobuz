import { Component } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { Defination } from 'src/app/definition/defination';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.css']
})
export class BlogComponent {
  constructor(private meta: Meta, private title: Title, private dataService: DataService) {
  }
  ngOnInit() {
    const url = window.location.href;
    if (url.includes("blog")) {
      this.dataService.updateHeaderActiveMenu("Blog");
      this.title.setTitle("tobuz.com | Blog");
      this.meta.updateTag({ name: Defination.META_NAME, content: "Tobuz is a virtual commercial online platform that brings Buyers, Sellers, Franchisers, Business and Commercial Property brokers together, thereby transforming and simplifying the way Businesses and Commercial Properties are bought, sold and leased" });
    } else {
      this.dataService.updateHeaderActiveMenu("Home");

      this.title.setTitle("Tobuz.com | Home");
      this.meta.updateTag({ name: 'title', content: "Business for Sale, Investment Opportunities- Buy, Sell, Transform Your Business | Tobuz" });
      this.meta.updateTag({ name: 'description', content: "Tobuz is a Global Marketplace for Buying, Franchising, Selling, Funding, or Transforming your business. Search the extensive database of 36940 Businesses, Consultants and Advisors." });
      

    }
    this.dataService.addCommanMeta(this.title,this.meta);

  }
}
