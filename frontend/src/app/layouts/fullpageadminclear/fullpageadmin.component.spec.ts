import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FullpageadminclearComponent } from './fullpageadminclear.component';

describe('FullpageadminComponent', () => {
  let component: FullpageadminclearComponent;
  let fixture: ComponentFixture<FullpageadminclearComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FullpageadminclearComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FullpageadminclearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
