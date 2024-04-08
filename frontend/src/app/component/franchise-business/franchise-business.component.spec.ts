import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FranchiseBusinessComponent } from './franchise-business.component';

describe('FranchiseBusinessComponent', () => {
  let component: FranchiseBusinessComponent;
  let fixture: ComponentFixture<FranchiseBusinessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FranchiseBusinessComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FranchiseBusinessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
