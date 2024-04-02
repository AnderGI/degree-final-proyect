import { TestBed } from '@angular/core/testing';

import { ApiCarService } from './api-car.service';

describe('ApiCarService', () => {
  let service: ApiCarService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiCarService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
