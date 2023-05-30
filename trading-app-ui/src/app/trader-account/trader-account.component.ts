import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TraderListService } from '../trader-list.service';
import { Trader } from '../trader';
import { MatDialog } from '@angular/material/dialog';
import { TraderAccountDepositDialogComponent } from '../trader-account-deposit-dialog/trader-account-deposit-dialog.component';
import { TraderAccountWithdrawDialogComponent } from '../trader-account-withdraw-dialog/trader-account-withdraw-dialog.component';

@Component({
  selector: 'app-trader-account',
  templateUrl: './trader-account.component.html',
  styleUrls: ['./trader-account.component.css']
})
export class TraderAccountComponent implements OnInit {
  trader: Trader | undefined;
  
  constructor(
    private activatedRoute: ActivatedRoute,
    private traderListService: TraderListService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    if (id) {
      this.traderListService.getTrader(Number(id)).subscribe(trader => {
        this.trader = trader;
        this.traderListService.setTraderId(Number(id));
      });
    }
  }

  openDepositDialog() {
    const traderId = this.trader?.id;
    if (traderId) {
      const dialogRef = this.dialog.open(TraderAccountDepositDialogComponent, {
        data: {
          traderId: traderId
        }
      });
  
      dialogRef.afterClosed().subscribe((result: number | undefined) => {
        console.log('Deposit dialog closed');
        if (result !== undefined) { // Check if a valid result is received
          this.traderListService.depositFunds(traderId, result).subscribe(updatedTrader => {
            console.log(`Successfully deposited ${result} funds for trader ${this.trader?.firstName} ${this.trader?.lastName}`);
          });
        }
      });
    }
  }
  
  openWithdrawDialog() {
    const traderId = this.trader?.id;
    if (traderId) {
      const dialogRef = this.dialog.open(TraderAccountWithdrawDialogComponent, {
        data: {
          traderId: traderId
        }
      });
  
      dialogRef.afterClosed().subscribe((result: number | undefined) => {
        console.log('Withdraw dialog closed');
        if (result !== undefined) { // Check if a valid result is received
          this.traderListService.withdrawFunds(traderId, result).subscribe(updatedTrader => {
            this.trader = updatedTrader;
            console.log(`Successfully withdrew ${result} funds for trader ${updatedTrader.firstName} ${updatedTrader.lastName}`);
          });
        }
      });
    }
  }
}
