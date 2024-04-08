import { Component } from '@angular/core';

@Component({
  selector: 'app-testmonal',
  templateUrl: './testmonal.component.html',
  styleUrls: ['./testmonal.component.css']
})
export class TestmonalComponent {
  moreBoxes = [
    { isHidden: false },
    { isHidden: false },
    { isHidden: false },    
    
  ];
  visibleBoxes = 3;


  loadMore() {
    this.visibleBoxes += 6; // Increase the number of visible boxes by 6
    if (this.visibleBoxes >= this.moreBoxes.length) {
      this.visibleBoxes = this.moreBoxes.length; 
    }
  }

}
