import { Component } from '@angular/core';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.css']
})
export class BlogComponent 
{
  constructor(private dataService:DataService){
  }
  ngOnInit() {
    const url=window.location.href;
    if (url.includes("blog")) {
      this.dataService.updateHeaderActiveMenu("Blog");
  } else {
    this.dataService.updateHeaderActiveMenu("Home");
  }
    
    
  }
}
