import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommanContactsComponent } from './comman-contacts.component';

describe('CommanContactsComponent', () => {
  let component: CommanContactsComponent;
  let fixture: ComponentFixture<CommanContactsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommanContactsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CommanContactsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
