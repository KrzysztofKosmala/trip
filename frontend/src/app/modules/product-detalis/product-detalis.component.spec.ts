import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductDetalisComponent } from './product-detalis.component';

describe('ProductDetalisComponent', () => {
  let component: ProductDetalisComponent;
  let fixture: ComponentFixture<ProductDetalisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductDetalisComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductDetalisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
