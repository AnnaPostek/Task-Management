package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.User;
import com.example.taskmanagement.service.TaskService;
import com.example.taskmanagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
    public List<Task> getList(){
        return service.getAllTasks();
    }

    @PostMapping("/tasks/addUser/{taskId}")
    public Task addUser(@PathVariable Long taskId, @RequestBody User user) {
       return service.addUserToTask(taskId, user);
    }

    @GetMapping("/getTask/{id}")
    public Task getTask(@PathVariable Long id) {
        return service.getTaskById(id);
    }

    @GetMapping("/taskList/{userId}")
    public List<Task> allTaskByUser(@PathVariable Long userId) {
        return service.findTaskListForUserByDateAsc(userId);
    }
}


