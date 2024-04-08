import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestmonalComponent } from './testmonal.component';

describe('TestmonalComponent', () => {
  let component: TestmonalComponent;
  let fixture: ComponentFixture<TestmonalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TestmonalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TestmonalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
