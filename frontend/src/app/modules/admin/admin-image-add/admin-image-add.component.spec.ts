import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminImageAddComponent } from './admin-image-add.component';

describe('AdminImageAddComponent', () => {
  let component: AdminImageAddComponent;
  let fixture: ComponentFixture<AdminImageAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminImageAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminImageAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
