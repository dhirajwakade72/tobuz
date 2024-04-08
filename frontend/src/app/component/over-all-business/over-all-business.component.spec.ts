import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OverAllBusinessComponent } from './over-all-business.component';

describe('OverAllBusinessComponent', () => {
  let component: OverAllBusinessComponent;
  let fixture: ComponentFixture<OverAllBusinessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OverAllBusinessComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OverAllBusinessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
