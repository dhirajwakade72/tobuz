import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessServiceDetailsComponent } from './business-service-details.component';

describe('BusinessServiceDetailsComponent', () => {
  let component: BusinessServiceDetailsComponent;
  let fixture: ComponentFixture<BusinessServiceDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BusinessServiceDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BusinessServiceDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
