package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.User;
import com.example.taskmanagement.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskController {

    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping("/addTask")
    public Task addTask(@RequestBody @Valid Task task) {
        return service.saveTask(task);
    }

    @GetMapping("/allTasks")
    public List<Task> getList() {
        return service.getAllTasks();
    }

    @PostMapping("/task/addUser/{taskId}")
    public Task addUser(@PathVariable Long taskId, @RequestBody User user) {
        return service.addUserToTask(taskId, user);
    }

    @GetMapping("/getTask/{id}")
    public Task getTask(@PathVariable Long id) {
        return service.getTaskById(id);
    }

    @PutMapping("/updateTask/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task toUpdate) {
        return service.updateTask(id, toUpdate);
    }

    @PutMapping("/task/changeStatus/{id}")
    public Task changeStatus(@PathVariable Long id, @RequestBody Task task) {
        return service.changeStatus(id, task.getStatus());
    }

    @GetMapping("/taskList/{userId}")
    public List<Task> allTaskByUser(@PathVariable Long userId) {
        return service.findTaskListForUserByDateAsc(userId);
    }

    @DeleteMapping("/deleteTask/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
    }
}


