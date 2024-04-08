import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertListingDetailsComponent } from './advert-listing-details.component';

describe('AdvertListingDetailsComponent', () => {
  let component: AdvertListingDetailsComponent;
  let fixture: ComponentFixture<AdvertListingDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdvertListingDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvertListingDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
