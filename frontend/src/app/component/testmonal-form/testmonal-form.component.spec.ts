import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestmonalFormComponent } from './testmonal-form.component';

describe('TestmonalFormComponent', () => {
  let component: TestmonalFormComponent;
  let fixture: ComponentFixture<TestmonalFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TestmonalFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TestmonalFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
