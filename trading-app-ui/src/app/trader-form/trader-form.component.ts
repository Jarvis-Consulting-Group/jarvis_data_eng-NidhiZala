import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Trader } from '../trader';
import { TraderListService } from '../trader-list.service';

@Component({
  selector: 'app-trader-form',
  templateUrl: './trader-form.component.html',
  styleUrls: ['./trader-form.component.css']
})
export class TraderFormComponent implements OnInit {
  traderForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private traderListService: TraderListService,
    private dialogRef: MatDialogRef<TraderFormComponent>
  ) {
    this.traderForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: [''],
      dob: [''],
      country: [''],
      email: ['']
    });
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    if (this.traderForm.valid) {
      const newTrader: Trader = {
        key: '', // Generate key using your logic or remove this property if not needed
        id: this.traderListService.generateUniqueId(),
        firstName: this.traderForm.value.firstName,
        lastName: this.traderForm.value.lastName,
        dob: this.traderForm.value.dob ? this.traderForm.value.dob.toISOString() : '', // Convert date to ISO string if it exists
        country: this.traderForm.value.country,
        email: this.traderForm.value.email,
        funds: 0
      };
      this.traderListService.addTrader(newTrader).subscribe(() => {
        this.dialogRef.close();
      });
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}
