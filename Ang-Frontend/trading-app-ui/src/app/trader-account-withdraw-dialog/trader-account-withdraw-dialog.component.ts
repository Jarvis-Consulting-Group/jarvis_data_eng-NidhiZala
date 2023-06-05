import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-trader-account-withdraw-dialog',
  templateUrl: './trader-account-withdraw-dialog.component.html',
  styleUrls: ['./trader-account-withdraw-dialog.component.css']
})
export class TraderAccountWithdrawDialogComponent {
  amount: number | undefined;

  constructor(public dialogRef: MatDialogRef<TraderAccountWithdrawDialogComponent>) {}

  cancel() {
    this.dialogRef.close();
  }

  submit() {
    // Perform any necessary actions with the withdrawn amount
    this.dialogRef.close(this.amount);
  }
}
