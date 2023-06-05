import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { TraderListComponent } from './trader-list/trader-list.component';
import { TraderAccountComponent } from './trader-account/trader-account.component';
import { QuotesListComponent } from './quotes-list/quotes-list.component';


const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'traderslist', component: TraderListComponent },
  { path: 'account', component: TraderAccountComponent },
  { path: 'account/:id', component: TraderAccountComponent },
  { path: 'quotes', component: QuotesListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
