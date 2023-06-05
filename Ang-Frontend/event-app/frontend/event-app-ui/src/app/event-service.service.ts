import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventServiceService {
  private apiUrl = "http://localhost:3000/api"
  constructor(private http: HttpClient) { }
   getEvents(){
    return this.http.get('http://localhost:3000/api/event');
   }
   createEvent(eventData: Event){
    return this.http.post(`${this.apiUrl}/event`, eventData);
   }
}
