import { Component} from '@angular/core';

interface Task {
  name: string;
  schedule: Date;
  description: string;
}
@Component({
  selector: 'app-to-do-page',
  templateUrl: './to-do-page.component.html',
  styleUrls: ['./to-do-page.component.css']
})
export class ToDoPageComponent {
  tasks: Task[] = [];
  newTask: Task = {
    name: '',
    schedule: new Date(),
    description: ''
  };
  
  taskAdded: boolean = false;
  addTask(){
    this.tasks.push(this.newTask);
    this.newTask = {
        name: '',
        schedule: new Date(),
        description: ''
      };
    this.taskAdded=true;
  };

  deleteTask(index: number)
  {
    this.tasks.splice(index, 1);
  }
}
