import { Component } from '@angular/core';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-business-setup',
  templateUrl: './business-setup.component.html',
  styleUrls: ['./business-setup.component.css']
})
export class BusinessSetupComponent {

  constructor(private dataService:DataService){
  }
  ngOnInit() {
    this.dataService.updateHeaderActiveMenu("Investor");
  }

}
