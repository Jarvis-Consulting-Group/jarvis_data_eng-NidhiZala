// import { Injectable } from '@angular/core';
// import { Trader } from './trader';
// import { HttpClient } from '@angular/common/http';
// import { Observable, of } from 'rxjs';
// import { tap } from 'rxjs/operators';
// @Injectable({
//   providedIn: 'root'
// })
// export class TraderListService {
//   private dataUrl = 'assets/data.json'; // Update the path to your JSON file
//   private traderList: Trader[] = [];
//   private selectedTraderId: number | undefined;
//   constructor(private http: HttpClient) { }

//   getDataSource(): Observable<Trader[]> {
//     return this.http.get<Trader[]>(this.dataUrl)
//       .pipe(
//         tap(data => {
//           this.traderList = data;
//         })
//       );
//   }

//   getColumns(): string[] {
//     return ['firstName', 'lastName', 'email', 'dob', 'country', 'actions'];
//   }

//   deleteTrader(id: number): Observable<Trader[]> {
//     const index = this.traderList.findIndex(trader => trader.id === id);
//     if (index !== -1) {
//       this.traderList.splice(index, 1);
//     }
//     return of(this.traderList);
//   }  
//   addTrader(trader: Trader): Observable<Trader[]> {
//     // Generate a unique ID for the new trader
//     const newId = this.generateUniqueId();

//     // Assign the new ID to the trader object
//     const newTrader = { ...trader, id: newId };

//     // Add the new trader to the traderList
//     this.traderList.push(newTrader);

//     // Return the updated traderList
//     return of(this.traderList);
//   }
  
//   getTrader(id: number): Trader | undefined {

//     const trader = this.traderList.find(trader => trader.id === id);
//     console.log('Retrieved Trader:', trader);
//     return trader;
//   }
  
//   setTraderId(traderId: number): void {
//     this.selectedTraderId = traderId;
//   }

//   getSelectedTraderId(): number | undefined {
//     return this.selectedTraderId;
//   }
//   depositFunds(traderId: number, amount: number): Observable<Trader[]> {
//     const trader = this.traderList.find(t => t.id === traderId);
//     if (trader) {
//       if (isNaN(trader.funds)) {
//         trader.funds = 0; // Set initial funds value to 0 if it's NaN
//       }
//       trader.funds += amount;
//     }
//     return of(this.traderList);
//   }
  
  
//   withdrawFunds(traderId: number, amount: number): Observable<Trader[]> {
//     const trader = this.traderList.find(t => t.id === traderId);
//     if (trader) {
//       if (trader.funds >= amount) {
//         console.log(amount)
//         trader.funds -= amount;
//       } else {
//         // Handle insufficient funds error
//         console.log('Insufficient funds');
//       }
//     }
//     // Return the updated traderList
//     return of(this.traderList);
//   }
  
  
  
//   generateUniqueId(): number {
//     const existingIds = this.traderList.map(trader => trader.id);
//     let newId = Math.floor(Math.random() * 1000) + 1;
  
//     // Generate a new ID until it becomes unique
//     while (existingIds.includes(newId)) {
//       newId = Math.floor(Math.random() * 1000) + 1;
//     }
  
//     return newId;
//   }
  

// }
import { Injectable } from '@angular/core';
import { Trader } from './trader';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TraderListService {
  private apiUrl = 'http://localhost:3000/api/traders'; // Update with your Express server's API endpoint
  private selectedTraderId: number | undefined;

  constructor(private http: HttpClient) { }

  getDataSource(): Observable<Trader[]> {
    return this.http.get<Trader[]>(this.apiUrl);
  }

  getColumns(): string[] {
    return ['firstName', 'lastName', 'email', 'dob', 'country', 'actions'];
  }

  deleteTrader(id: number): Observable<Trader[]> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<Trader[]>(url);
  }

  addTrader(trader: Trader): Observable<Trader> {
    return this.http.post<Trader>(this.apiUrl, trader);
  }

  getTrader(id: number): Observable<Trader> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Trader>(url);
  }

  updateTrader(trader: Trader): Observable<Trader> {
    const url = `${this.apiUrl}/${trader.id}`;
    return this.http.put<Trader>(url, trader);
  }

  depositFunds(traderId: number, amount: number): Observable<Trader> {
    const url = `${this.apiUrl}/${traderId}/deposit`;
    return this.http.patch<Trader>(url, { amount });
  }

  withdrawFunds(traderId: number, amount: number): Observable<Trader> {
    const url = `${this.apiUrl}/${traderId}/withdraw`;
    return this.http.patch<Trader>(url, { amount });
  }
  generateUniqueId(): number {
    // Generate a random number between 1 and 1000
    const newId = Math.floor(Math.random() * 1000) + 1;
    return newId;
  }
  setTraderId(traderId: number): void {
    this.selectedTraderId = traderId;
  }

  getSelectedTraderId(): number | undefined {
    return this.selectedTraderId;
  }
}
