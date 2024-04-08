import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddBusinessListingComponent } from './add-business-listing.component';

describe('AddBusinessListingComponent', () => {
  let component: AddBusinessListingComponent;
  let fixture: ComponentFixture<AddBusinessListingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddBusinessListingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddBusinessListingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
