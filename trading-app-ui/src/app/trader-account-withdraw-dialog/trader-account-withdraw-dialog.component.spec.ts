import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TraderAccountWithdrawDialogComponent } from './trader-account-withdraw-dialog.component';

describe('TraderAccountWithdrawDialogComponent', () => {
  let component: TraderAccountWithdrawDialogComponent;
  let fixture: ComponentFixture<TraderAccountWithdrawDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TraderAccountWithdrawDialogComponent]
    });
    fixture = TestBed.createComponent(TraderAccountWithdrawDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
