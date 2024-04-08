import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessListingDetailsComponent } from './business-listing-details.component';

describe('BusinessListingDetailsComponent', () => {
  let component: BusinessListingDetailsComponent;
  let fixture: ComponentFixture<BusinessListingDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BusinessListingDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BusinessListingDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
