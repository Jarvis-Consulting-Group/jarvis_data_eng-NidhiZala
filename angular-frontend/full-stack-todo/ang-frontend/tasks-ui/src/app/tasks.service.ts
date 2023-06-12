import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class TasksService {
  host="http://localhost:3000/";
  // eslint-disable-next-line @typescript-eslint/no-empty-function
  constructor(private http: HttpClient) { }
  getTasks(){
    return this.http.get(this.host).pipe(map((res) => res));
  }
  createTasks(name: string){
    return this.http.post('http://localhost:3000/',{
      name : name,
      completed: true,
    });

  }
  deleteTasks(id: number){   
      return this.http.delete(`http://localhost:3000/${id}`);
  }
}
