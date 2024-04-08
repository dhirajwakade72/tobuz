import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessforsaleComponent } from './businessforsale.component';

describe('BusinessforsaleComponent', () => {
  let component: BusinessforsaleComponent;
  let fixture: ComponentFixture<BusinessforsaleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BusinessforsaleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BusinessforsaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
