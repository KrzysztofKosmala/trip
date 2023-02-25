import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminProductPopupImagesListComponent } from './admin-product-popup-images-list.component';

describe('AdminProductPopupImagesListComponent', () => {
  let component: AdminProductPopupImagesListComponent;
  let fixture: ComponentFixture<AdminProductPopupImagesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminProductPopupImagesListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminProductPopupImagesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
