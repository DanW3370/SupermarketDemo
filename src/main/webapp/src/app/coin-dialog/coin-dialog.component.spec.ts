import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CoinDialogComponent} from './coin-dialog.component';

describe('CoinDialogComponent', () => {
  let component: CoinDialogComponent;
  let fixture: ComponentFixture<CoinDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CoinDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CoinDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
