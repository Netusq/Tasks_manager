package com.example.todo_list.controller;

import com.example.todo_list.entity.Task;
import com.example.todo_list.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Управление задачами", description = "CRUD для задач")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
        System.out.println("TaskController created! Service: " + taskService);
    }

    @Operation(summary = "Создать новую задачу")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Задача создана",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Task.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Невалидные входные данные"
            )
    })
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @Operation(
            summary = "Получить все задачи",
            description = "Возвращает список всех задач в системе"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение списка задач",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Task.class))
            )
    )
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @Operation(
            summary = "Получить задачу по ID",
            description = "Возвращает задачу с указанным идентификатором"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Задача найдена",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Task.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Задача не найдена",
                    content = @Content(schema = @Schema(hidden = true))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(
            @Parameter(description = "ID задачи", example = "1")
            @PathVariable long id) {
        return ResponseEntity.ok(taskService.getById(id));
    }

    @Operation(
            summary = "Обновить задачу",
            description = "Обновляет данные существующей задачи"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Задача успешно обновлена",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Task.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неверные входные данные"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Задача не найдена"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @Parameter(description = "ID задачи для обновления", example = "1")
            @PathVariable long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Обновленные данные задачи",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Task.class)
                    ))
            @RequestBody Task task) {
        task.setId(id);
        Task updatedTask = taskService.updateTask(task);
        return ResponseEntity.ok(updatedTask);
    }

    @Operation(
            summary = "Удалить задачу",
            description = "Удаляет задачу с указанным идентификатором"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Задача успешно удалена"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Задача не найдена"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @Parameter(description = "ID задачи для удаления", example = "1")
            @PathVariable long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}