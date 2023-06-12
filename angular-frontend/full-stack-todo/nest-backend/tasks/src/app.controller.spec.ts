import { Test, TestingModule } from '@nestjs/testing';
import { HttpStatus, INestApplication } from '@nestjs/common';
import * as request from 'supertest';
import { AppController } from './app.controller';
import { AppService, Tasks } from './app.service';

describe('AppController', () => {
  let app: INestApplication;

  // Mock the AppService to isolate the controller during testing
  const mockAppService = {
    getTasks: jest.fn(() => []),
    createTasks: jest.fn((name: string) => []),
    deleteTasks: jest.fn((id: number) => []),
  };

  beforeAll(async () => {
    const moduleFixture: TestingModule = await Test.createTestingModule({
      controllers: [AppController],
      providers: [{ provide: AppService, useValue: mockAppService }],
    }).compile();

    app = moduleFixture.createNestApplication();
    await app.init();
  });

  beforeEach(() => {
    // Reset the mock function's state before each test
    jest.clearAllMocks();
  });

  afterAll(async () => {
    await app.close();
  });

  describe('GET /', () => {
    it('should return an array of tasks', () => {
      const expectedResult: Tasks[] = [
        { id: 1, name: 'Task 1', completed: false },
      ];
      mockAppService.getTasks.mockReturnValue(expectedResult);

      return request(app.getHttpServer())
        .get('/')
        .expect(HttpStatus.OK)
        .expect(expectedResult);
    });
  });

  describe('POST /', () => {
    it('should create a new task', () => {
      const newTask: Tasks = { id: 2, name: 'Task 2', completed: false };
      mockAppService.createTasks.mockReturnValue([newTask]);
      return request(app.getHttpServer())
        .post('/')
        .send({ name: newTask.name })
        .expect(HttpStatus.CREATED) // Update the assertion to expect HttpStatus.CREATED
        .expect([newTask]);
    });
  });
  describe('DELETE /:id', () => {
    it('should delete a task by ID', () => {
      const taskId = 1;
      mockAppService.deleteTasks.mockReturnValue([]);

      return request(app.getHttpServer())
        .delete(`/${taskId}`)
        .expect(HttpStatus.OK)
        .expect([]);
    });
  });
});
