import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessValuationComponent } from './business-valuation.component';

describe('BusinessValuationComponent', () => {
  let component: BusinessValuationComponent;
  let fixture: ComponentFixture<BusinessValuationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BusinessValuationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BusinessValuationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
