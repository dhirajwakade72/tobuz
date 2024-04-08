import { TestBed } from '@angular/core/testing';

import { BusinessListingService } from './business-listing.service';

describe('BusinessListingService', () => {
  let service: BusinessListingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BusinessListingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
