import { TestBed } from '@angular/core/testing';

import { AdminMessagesService } from './admin-messages.service';

describe('AdminMessagesService', () => {
  let service: AdminMessagesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminMessagesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
