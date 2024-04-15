import { Component } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-testmonal',
  templateUrl: './testmonal.component.html',
  styleUrls: ['./testmonal.component.css']
})
export class TestmonalComponent {
  constructor(private meta: Meta, private title: Title,private dataService:DataService) {

  }

  ngOnInit() {

    this.updateMeta();
  }


  updateMeta() {
    this.title.setTitle("Tobuz terms and condition");
    this.meta.updateTag({ name:'description', content: "Tobuz.com Register" });
    this.dataService.addCommanMeta(this.title,this.meta);
  }

}
