import { TestBed } from '@angular/core/testing';

import { UserCatalogService } from './user-catalog.service';

describe('UserCatalogService', () => {
  let service: UserCatalogService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserCatalogService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
