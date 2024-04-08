import { Component } from '@angular/core';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-business-valuation',
  templateUrl: './business-valuation.component.html',
  styleUrls: ['./business-valuation.component.css']
})
export class BusinessValuationComponent {
  constructor(private dataService:DataService){
  }
  ngOnInit() {
    this.dataService.updateHeaderActiveMenu("Investor");
  }
}
