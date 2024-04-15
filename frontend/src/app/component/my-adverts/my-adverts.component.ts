import { Component } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { Defination } from 'src/app/definition/defination';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-my-adverts',
  templateUrl: './my-adverts.component.html',
  styleUrls: ['./my-adverts.component.css']
})
export class MyAdvertsComponent {
  constructor(private meta: Meta, private title: Title,private dataService:DataService){
  }
  ngOnInit() {  
    this.updateMeta();
  }
  updateMeta()
  {
    this.title.setTitle("Tobuz.com | My Adverts");
    this.meta.updateTag({ name: Defination.META_NAME, content: "Tobuz.com |  My Adverts"});
    this.dataService.addCommanMeta(this.title,this.meta);
  }
}
