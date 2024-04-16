import { TestBed } from '@angular/core/testing';

import { GetAllCarsMatchingCriteriaHttpClientService } from './get-all-cars-matching-criteria-http-client.service';

describe('GetAllCarsMatchingCriteriaHttpClientService', () => {
  let service: GetAllCarsMatchingCriteriaHttpClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetAllCarsMatchingCriteriaHttpClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
