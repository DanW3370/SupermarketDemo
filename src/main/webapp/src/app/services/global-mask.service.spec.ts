import {TestBed} from '@angular/core/testing';

import {GlobalMaskService} from './global-mask.service';

describe('GlobalMaskService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GlobalMaskService = TestBed.get(GlobalMaskService);
    expect(service).toBeTruthy();
  });
});
