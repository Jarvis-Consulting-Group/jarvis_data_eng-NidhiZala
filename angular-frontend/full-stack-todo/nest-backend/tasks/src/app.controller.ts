import {
  Controller,
  Get,
  Post,
  Param,
  Delete,
  HttpException,
  HttpStatus,
  Body,
} from '@nestjs/common';
import { AppService } from './app.service';
import { Tasks } from './app.service';
@Controller()
export class AppController {
  constructor(private readonly service: AppService) {}

  @Get()
  getTodos(): Tasks[] {
    try {
      return this.service.getTasks();
    } catch (error) {
      throw new HttpException(error.message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Post()
  createTodo(@Body() { name }: Tasks): Tasks[] {
    try {
      return this.service.createTasks(name);
    } catch (error) {
      throw new HttpException(error.message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @Delete(':id')
  deleteTodo(@Param('id') id: number): Tasks[] {
    try {
      return this.service.deleteTasks(id);
    } catch (error) {
      throw new HttpException(error.message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
