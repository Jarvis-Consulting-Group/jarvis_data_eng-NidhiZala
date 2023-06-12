export interface Tasks {
    id: number;
    name: string;
    completed: boolean;
}
export declare class AppService {
    private tasks;
    constructor();
    getTasks(): Tasks[];
    createTasks(name: string): Tasks[];
    deleteTasks(id: number): Tasks[];
}
