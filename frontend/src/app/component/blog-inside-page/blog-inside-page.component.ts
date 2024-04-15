import { Component } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-blog-inside-page',
  templateUrl: './blog-inside-page.component.html',
  styleUrls: ['./blog-inside-page.component.css']
})
export class BlogInsidePageComponent {
  constructor(private meta: Meta, private title: Title,private dataService:DataService) {

  }

  ngOnInit() {

    this.updateSeoMetaTag();
  }
  updateSeoMetaTag() {

    this.title.setTitle('Tobuz.com | blog inside page');
    this.meta.updateTag({ name: 'description', content: 'Running a business is not at all easy or simple, as situations keep on changing. Sometimes the situations do a business, and sometimes situations break a business. Sometimes a business is running successfully, but some circumstances compel the owner to sell that business. Few entrepreneurs sell their business because they want to move into another business or field. Despite the reason behind selling a business, the business must be sold at a good price. Selling a business is like a marathon that requires proper planning and guidance. If you are looking to sell your business in the UAE, then you have come to the right place; keep on scrolling!' });
    this.meta.updateTag({ name: 'title', content: 'Tobuz.com | blog inside page' });
    this.dataService.addCommanMeta(this.title,this.meta);

  }
}
