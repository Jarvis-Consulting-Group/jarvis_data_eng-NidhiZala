import { AppService } from './app.service';
import { Tasks } from './app.service';
export declare class AppController {
    private readonly service;
    constructor(service: AppService);
    getTodos(): Tasks[];
    createTodo({ name }: Tasks): Tasks[];
    deleteTodo(id: number): Tasks[];
}
