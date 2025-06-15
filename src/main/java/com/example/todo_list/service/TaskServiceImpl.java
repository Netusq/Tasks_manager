package com.example.todo_list.service;

import com.example.todo_list.entity.Task;
import com.example.todo_list.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor

public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        if (task.getTitle() == null || task.getDescription() == null || task.getStatus() == null) {
            throw new IllegalArgumentException("Task title or description or status is null");
        }
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @Override
    @Transactional
    public Task getById(long id) {
        return taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task with id " + id + " not found"));
    }



    @Override
    public Task updateTask(Task task) {
        Task existingTask = getById(task.getId());

        if (task.getTitle() != null && !task.getTitle().isBlank()) {
            existingTask.setTitle(task.getTitle());
        }
        if (task.getDescription() != null) {
            existingTask.setDescription(task.getDescription());
        }
        if (task.getStatus() != null) {
            existingTask.setStatus(task.getStatus());
        }

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(long id) {
        if (!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete - task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }


}
