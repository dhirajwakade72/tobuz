import { Component } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { Defination } from 'src/app/definition/defination';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-business-setup',
  templateUrl: './business-setup.component.html',
  styleUrls: ['./business-setup.component.css']
})
export class BusinessSetupComponent {

  constructor(private meta: Meta, private title: Title,private dataService:DataService){
  }
  ngOnInit() {
    this.dataService.updateHeaderActiveMenu("Investor");
    this.updateSeoMetaTag();
  }

  updateSeoMetaTag()
  {
    this.title.setTitle("Tobuz.com | Business Setup");
    this.meta.updateTag({ name: Defination.META_NAME, content: "Find the best Brokers providers at Tobuz. We offer 1056+ broker Business Consultants, Accountants, and Legal Advisors."});    
    this.meta.updateTag({ name: 'title', content: "Find the best Brokers providers at Tobuz. We offer 1056+ broker Business Consultants, Accountants, and Legal Advisors."});    
    this.dataService.addCommanMeta(this.title,this.meta);
  }

}
