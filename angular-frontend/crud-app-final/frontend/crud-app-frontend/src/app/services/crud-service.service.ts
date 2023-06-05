import { Injectable } from '@angular/core';
// we need http client to connect to backend
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CrudItem } from '../models/crud-item.model'; // interface for an item

@Injectable({
  providedIn: 'root'
})
export class CrudServiceService {
  private apiURL="http://localhost:3000/api/crud";
  constructor(private http: HttpClient) { 
  }
  getCrudItem(): Observable<CrudItem[]>{
    return this.http.get<CrudItem[]>(this.apiURL);
  }
  createCrudItem(item: CrudItem): Observable<CrudItem[]> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<CrudItem[]>(this.apiURL, item, { headers });
  }

  // updateCrudItem(item: CrudItem): Observable<any> {
  //   const url = `${this.apiURL}/${item.id}`;
  //   const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  //   return this.http.put<any>(url, item, { headers });
  // }
  updateCrudItem(item: CrudItem): Observable<any> {
    const url = `${this.apiURL}`;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = JSON.stringify(item);
    
    return this.http.put<any>(url, body, { headers });
  }
  
  
  deleteCrudItem(id: number): Observable<any> {
    const url = `${this.apiURL}?id=${id}`;
    console.log(url);
    return this.http.delete<any>(url);
  }  

}
