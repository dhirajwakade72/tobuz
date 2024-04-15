import { Component } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { Defination } from 'src/app/definition/defination';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.css']
})
export class AboutUsComponent {
  constructor(private dataService:DataService,private meta: Meta, private title: Title){
  }
  ngOnInit() {
    this.dataService.updateHeaderActiveMenu("About-Us");
    this.updateMeta();
  }

  updateMeta()
  {    
    this.meta.updateTag({ name: Defination.META_NAME, content: "Tobuz is a virtual commercial online platform that brings Buyers, Sellers, Franchisers, Business and Commercial Property brokers together, thereby transforming and simplifying the way Businesses and Commercial Properties are bought, sold and leased"});
    this.title.setTitle('Tobuz.com | About Us');    
    this.meta.updateTag({ name: 'title', content: 'Tobuz.com | About Us' });
    this.dataService.addCommanMeta(this.title,this.meta);
  }
}
