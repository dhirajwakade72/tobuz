import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvestmentORBuyersComponent } from './investment-orbuyers.component';

describe('InvestmentORBuyersComponent', () => {
  let component: InvestmentORBuyersComponent;
  let fixture: ComponentFixture<InvestmentORBuyersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InvestmentORBuyersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InvestmentORBuyersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
