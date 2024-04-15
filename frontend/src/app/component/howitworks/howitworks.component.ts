import { Component } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { Defination } from 'src/app/definition/defination';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-howitworks',
  templateUrl: './howitworks.component.html',
  styleUrls: ['./howitworks.component.css']
})
export class HowitworksComponent {

  constructor(private meta: Meta, private title: Title,private dataService:DataService){
  }
  ngOnInit() {  
    this.updateMeta();
  }
  updateMeta()
  {
    this.title.setTitle("Tobuz.com | How its works");
    this.meta.updateTag({ name: Defination.META_NAME, content: "Tobuz.com | How its works"});
    this.dataService.addCommanMeta(this.title,this.meta);
  }
}
