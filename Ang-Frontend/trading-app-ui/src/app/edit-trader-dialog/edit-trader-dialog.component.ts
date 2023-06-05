import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Trader } from '../trader';

@Component({
  selector: 'app-edit-trader-dialog',
  templateUrl: './edit-trader-dialog.component.html',
  styleUrls: ['./edit-trader-dialog.component.css']
})
export class EditTraderDialogComponent {
  trader: Trader;

  constructor(
    public dialogRef: MatDialogRef<EditTraderDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { trader: Trader }
  ) {
    this.trader = { ...data.trader }; // Make a copy of the trader object to avoid modifying the original data
  }

  onSubmit(): void {
    this.dialogRef.close(this.trader); // Pass the updated trader object back to the parent component
  }

  cancel(): void {
    this.dialogRef.close('cancel'); // Close the dialog without saving changes
  }
}
