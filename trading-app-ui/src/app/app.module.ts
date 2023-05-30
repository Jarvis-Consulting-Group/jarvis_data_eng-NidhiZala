import { NgModule,APP_INITIALIZER } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NavbarComponent } from './navbar/navbar.component';
import { TraderFormComponent } from './trader-form/trader-form.component';
import { TraderListComponent } from './trader-list/trader-list.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTableModule } from '@angular/material/table';
import { HttpClientModule } from '@angular/common/http';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field'; // Add this import
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { TraderAccountComponent } from './trader-account/trader-account.component';
import { TraderAccountDepositDialogComponent } from './trader-account-deposit-dialog/trader-account-deposit-dialog.component';
import { TraderAccountWithdrawDialogComponent } from './trader-account-withdraw-dialog/trader-account-withdraw-dialog.component';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { TraderListService } from './trader-list.service';
import { QuotesListComponent } from './quotes-list/quotes-list.component';
import { EditTraderDialogComponent } from './edit-trader-dialog/edit-trader-dialog.component';

export function initializeApp(traderListService: TraderListService) {
  return () => traderListService.getDataSource().toPromise();
}


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    NavbarComponent,
    TraderFormComponent,
    TraderListComponent,
    TraderAccountComponent,
    TraderAccountDepositDialogComponent,
    TraderAccountWithdrawDialogComponent,
    QuotesListComponent,
    EditTraderDialogComponent
  ],
  imports: [
    BrowserModule, 
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTableModule,
    HttpClientModule,
    MatButtonModule,
    FormsModule,
    MatDialogModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatIconModule,
    MatCardModule
  ],
  providers: [
    TraderListService,
    {
      provide: APP_INITIALIZER,
      useFactory: initializeApp,
      deps: [TraderListService],
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
