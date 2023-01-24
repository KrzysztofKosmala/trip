import { TestBed } from '@angular/core/testing';

import { AdminImageServiceService } from './admin-image-service';

describe('AdminImageServiceService', () => {
  let service: AdminImageServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminImageServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
