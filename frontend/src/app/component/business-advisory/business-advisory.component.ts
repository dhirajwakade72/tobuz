import { Component } from '@angular/core';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-business-advisory',
  templateUrl: './business-advisory.component.html',
  styleUrls: ['./business-advisory.component.css']
})
export class BusinessAdvisoryComponent {
  constructor(private dataService:DataService){
  }
  ngOnInit() {
    this.dataService.updateHeaderActiveMenu("Investor");
  }
}
