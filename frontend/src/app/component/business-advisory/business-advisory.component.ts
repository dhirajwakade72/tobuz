import { Component } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { Defination } from 'src/app/definition/defination';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-business-advisory',
  templateUrl: './business-advisory.component.html',
  styleUrls: ['./business-advisory.component.css']
})
export class BusinessAdvisoryComponent {
  constructor(private meta: Meta, private title: Title,private dataService:DataService){
  }
  ngOnInit() {
    this.dataService.updateHeaderActiveMenu("Investor");
    this.updateMeta();
  }
  updateMeta()
  {
    this.title.setTitle("Tobuz.com | Business Advisory");
    this.meta.updateTag({ name: 'title', content: 'Tobuz.com | Business Advisory'});
    this.meta.updateTag({ name: Defination.META_NAME, content: "Businesses are always in need of professional guidance for resolving financial, marketing, and valuation issues. And, that is precisely what Tobuz focuses on – developing a comprehensive understanding and business insights to help you survive!"});
    this.dataService.addCommanMeta(this.title,this.meta);
  }
}
