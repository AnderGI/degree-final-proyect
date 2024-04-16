import { TestBed } from '@angular/core/testing';

import { GetCarServiceHttpClientService } from './get-car-service-http-client.service';

describe('GetCarServiceHttpClientService', () => {
  let service: GetCarServiceHttpClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetCarServiceHttpClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
