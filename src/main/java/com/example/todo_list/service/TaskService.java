package com.example.todo_list.service;

import com.example.todo_list.entity.Task;

import java.util.List;
public interface TaskService {

    Task createTask(Task task);

    List<Task> getAllTasks();

    Task getById(long id);

    Task updateTask(Task task);

    void deleteTask(long id);

}
