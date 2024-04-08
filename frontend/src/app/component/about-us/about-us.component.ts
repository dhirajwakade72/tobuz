import { Component } from '@angular/core';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.css']
})
export class AboutUsComponent {
  constructor(private dataService:DataService){
  }
  ngOnInit() {
    this.dataService.updateHeaderActiveMenu("About-Us");
  }
}
