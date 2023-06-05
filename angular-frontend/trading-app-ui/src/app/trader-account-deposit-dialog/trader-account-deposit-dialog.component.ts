import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TraderListService } from '../trader-list.service';
import { Trader } from '../trader';

@Component({
  selector: 'app-trader-account-deposit-dialog',
  templateUrl: './trader-account-deposit-dialog.component.html',
  styleUrls: ['./trader-account-deposit-dialog.component.css']
})
export class TraderAccountDepositDialogComponent {
  amount: number = 0; // Initialize amount with a default value of 0

  constructor(
    public dialogRef: MatDialogRef<TraderAccountDepositDialogComponent>,
    private traderListService: TraderListService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  cancel() {
    this.dialogRef.close();
  }
  parseAmount() {
    if (typeof this.amount === 'string') {
      this.amount = parseFloat(this.amount);
    }
  }
  
  submit() {
    console.log('Amount:', this.amount);
    console.log('Data:', this.data); // Add this line to log the data object
    if (this.amount > 0) { // Check if the amount is greater than 0
      this.dialogRef.close(this.amount);
    }
  }
}
