import { TestBed } from '@angular/core/testing';

import { GetAllCarServiceHttpClientService } from './get-all-car-service-http-client.service';

describe('GetAllCarServiceHttpClientService', () => {
  let service: GetAllCarServiceHttpClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetAllCarServiceHttpClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
