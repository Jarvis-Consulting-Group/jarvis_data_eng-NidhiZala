import { Component } from '@angular/core';
import { TasksService } from './tasks.service';

interface Task {
  id: number;
  name: string;
  completed: boolean;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  tasks: Task[];
  task: string;

  constructor(private taskService: TasksService) {
    this.tasks = [];
    this.task = '';
  }

  title = 'task-ui';

  ngOnInit() {
    this.taskService.getTasks().subscribe((data: Object) => {
      console.log(data);
      this.tasks = data as Task[];
    });
  }

  addTask(task: string) {
    this.taskService.createTasks(task).subscribe(() => {
      this.task = '';
      this.taskService.getTasks().subscribe((data: Object) => {
        console.log(data);
        this.tasks = data as Task[];
      });
    });
  }

  deleteTask(id: number) {
    this.taskService.deleteTasks(id).subscribe(() => {
      console.log(`Task with ID ${id} deleted.`);
      this.taskService.getTasks().subscribe((data: Object) => {
        console.log(data);
        this.tasks = data as Task[];
      });
    });
  }
}
