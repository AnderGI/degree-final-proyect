import { TestBed } from '@angular/core/testing';

import { GetAllCarBrandsHttpClientService } from './get-all-car-brands-http-client.service';

describe('GetAllCarBrandsHttpClientService', () => {
  let service: GetAllCarBrandsHttpClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetAllCarBrandsHttpClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
