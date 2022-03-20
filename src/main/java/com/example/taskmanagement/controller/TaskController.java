package com.example.taskmanagement.controller;

import com.example.taskmanagement.exception.TaskNotFoundException;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.User;
import com.example.taskmanagement.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TaskController {

    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping("/task")
    public ResponseEntity<Task> addTask(@RequestBody @Valid Task task) {
        Task savedTask = service.saveTask(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getList() {
        List<Task> allTasks = service.getAllTasks();
        return new ResponseEntity<>(allTasks, HttpStatus.OK);
    }

    @PostMapping("/task/user/{taskId}")
    public ResponseEntity<Task> addUser(@PathVariable Long taskId, @RequestBody @Valid User user) {
        Task task = service.addUserToTask(taskId, user);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        Task taskById = service.getTaskById(id);
        return new ResponseEntity<>(taskById, HttpStatus.OK);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task toUpdate) {
        Task task = service.updateTask(id, toUpdate);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/task/status/{id}")
    public ResponseEntity<Task> changeStatus(@PathVariable Long id, @RequestBody @Valid Task task) {
        Task taskStatus = service.changeStatus(id, task.getStatus());
        return new ResponseEntity<>(taskStatus, HttpStatus.OK);
    }

    @GetMapping("/tasks/{userId}")
    public ResponseEntity<List<Task>> allTaskByUser(@PathVariable Long userId) {
        List<Task> taskListOrdered = service.findTaskListForUserByDateAsc(userId);
        return new ResponseEntity<>(taskListOrdered, HttpStatus.OK);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        if (service.taskExistsById(id)) {
            service.deleteTask(id);
            return new ResponseEntity<>(String.format("Task with id = %d is deleted successfully", id), HttpStatus.OK);
        } else {
            throw new TaskNotFoundException();
        }
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = TaskNotFoundException.class)
    public ResponseEntity<Object> exception(TaskNotFoundException exception) {
        return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
    }
}


