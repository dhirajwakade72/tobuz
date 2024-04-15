import { Component } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { Defination } from 'src/app/definition/defination';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-business-valuation',
  templateUrl: './business-valuation.component.html',
  styleUrls: ['./business-valuation.component.css']
})
export class BusinessValuationComponent {
  constructor(private meta: Meta, private title: Title,private dataService:DataService){
  }
  ngOnInit() {
    this.dataService.updateHeaderActiveMenu("Investor");
    this.updateMeta();
  }

  updateMeta()
  {
    this.title.setTitle("Tobuz.com | Business Valuation");
    this.meta.updateTag({ name: Defination.META_NAME, content: "Find the best Brokers providers at Tobuz. We offer 1056+ broker Business Consultants, Accountants, and Legal Advisors."});
    this.meta.updateTag({ name:'title', content: "tobuz.com |Find the best Brokers providers at Tobuz. We offer 1056+ broker Business Consultants, Accountants, and Legal Advisors."});
    this.dataService.addCommanMeta(this.title,this.meta);
  }
}
