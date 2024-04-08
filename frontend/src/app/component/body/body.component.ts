import { Component,OnInit } from '@angular/core';
declare function test():void;
@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit{

  ngOnInit() {
    test();
  }
 
}
