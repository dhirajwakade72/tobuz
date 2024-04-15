import { Component } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-privacy-policy',
  templateUrl: './privacy-policy.component.html',
  styleUrls: ['./privacy-policy.component.css']
})
export class PrivacyPolicyComponent {
  constructor(private meta: Meta, private title: Title,private dataService:DataService) {

  }
  ngOnInit() {
    this.updateSeoMetaTag();
  }
  updateSeoMetaTag() {
    this.title.setTitle('Tobuz.com | Privacy Policies');
    this.meta.updateTag({ name: 'description', content: 'Tobuz.com | Privacy Policies' });
    this.meta.updateTag({ name: 'title', content: 'Tobuz.com | Privacy Policies' });
    this.dataService.addCommanMeta(this.title,this.meta);
  }
}
