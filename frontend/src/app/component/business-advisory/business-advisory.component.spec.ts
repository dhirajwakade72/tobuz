import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessAdvisoryComponent } from './business-advisory.component';

describe('BusinessAdvisoryComponent', () => {
  let component: BusinessAdvisoryComponent;
  let fixture: ComponentFixture<BusinessAdvisoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BusinessAdvisoryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BusinessAdvisoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
