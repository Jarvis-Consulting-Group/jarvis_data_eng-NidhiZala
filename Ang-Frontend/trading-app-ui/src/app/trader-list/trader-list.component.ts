import { Component, OnInit } from '@angular/core';
import { Trader } from '../trader';
import { MatTableDataSource } from '@angular/material/table';
import { TraderListService } from '../trader-list.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { TraderFormComponent } from '../trader-form/trader-form.component';
import { Router } from '@angular/router';
import { EditTraderDialogComponent } from '../edit-trader-dialog/edit-trader-dialog.component';
@Component({
  selector: 'app-trader-list',
  templateUrl: './trader-list.component.html',
  styleUrls: ['./trader-list.component.css']
})
export class TraderListComponent implements OnInit {
  traderList: MatTableDataSource<Trader>;
  columns: string[] = ['firstName', 'lastName', 'email', 'country', 'dob', 'actions'];

  constructor(private traderListService: TraderListService, private dialog: MatDialog, private router: Router) {
    this.traderList = new MatTableDataSource<Trader>([]);
  }

  ngOnInit(): void {
    this.traderListService.getDataSource().subscribe((data: Trader[]) => {
      this.traderList.data = data;
      console.log('Trader List:', data);
    });
  }

  getColumns(): string[] {
    return this.columns;
  }

  getColumnDisplayName(column: string): string {
    switch (column) {
      case 'firstName':
        return 'First Name';
      case 'lastName':
        return 'Last Name';
      case 'email':
        return 'Email Address';
      case 'dob':
        return 'Date of Birth';
      case 'country':
        return 'Country of Origin';
      case 'actions':
        return 'Actions';
      default:
        return column;
    }
  }

  deleteTrader(id: number): void {
    this.traderListService.deleteTrader(id).subscribe(() => {
      this.traderList.data = this.traderList.data.filter(trader => trader.id !== id);
    });
  }

  viewTraderAccount(trader: Trader): void {
    const traderId = trader.id;
    this.router.navigate(['/account', traderId]);
  }  
  openEditTraderDialog(trader: Trader): void {
    const dialogRef = this.dialog.open(EditTraderDialogComponent, {
      width: '400px',
      data: { trader: trader }
    });
  
    dialogRef.afterClosed().subscribe(result => {
      if (result && result !== 'cancel') {
        console.log('Dialog closed with result:', result);
        this.traderListService.updateTrader(result).subscribe(updatedTrader => {
          const index = this.traderList.data.findIndex(t => t.id === updatedTrader.id);
          if (index !== -1) {
            this.traderList.data[index] = updatedTrader;
          }
        });
      }
    });
  }
  openTraderFormDialog(): void {
    const dialogRef = this.dialog.open(TraderFormComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log('Dialog closed with result:', result);
        this.traderListService.getDataSource().subscribe((data: Trader[]) => {
          this.traderList.data = data;
        });
      }
    });
  }
}
