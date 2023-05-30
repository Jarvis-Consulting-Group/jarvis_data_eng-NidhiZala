import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Quote } from './quote';

@Injectable({
  providedIn: 'root'
})
export class QuotesService {
  private quotesUrl = 'assets/quotes-list-data.json'; // JSON file URL

  constructor(private http: HttpClient) {}

  getDataSource(): Observable<Quote[]> {
    return this.http.get<Quote[]>(this.quotesUrl);
  }
}
