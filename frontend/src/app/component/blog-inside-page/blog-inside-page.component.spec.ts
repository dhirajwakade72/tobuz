import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BlogInsidePageComponent } from './blog-inside-page.component';

describe('BlogInsidePageComponent', () => {
  let component: BlogInsidePageComponent;
  let fixture: ComponentFixture<BlogInsidePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BlogInsidePageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BlogInsidePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
