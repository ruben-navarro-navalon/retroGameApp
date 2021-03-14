import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SellerOrBuyerComponent } from './seller-or-buyer.component';

describe('SellerOrBuyerComponent', () => {
  let component: SellerOrBuyerComponent;
  let fixture: ComponentFixture<SellerOrBuyerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SellerOrBuyerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SellerOrBuyerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
