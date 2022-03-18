package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskController {

    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/add-task")
    public String addTask(Model model) {
        model.addAttribute("task", Task.builder().build());
        model.addAttribute("current_operation", "Adding new task");
        return "books/add-edit";
    }

//    @PostMapping("/task-save")
//    public String saveBook(@Valid Task task, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("task", task);
//            List<ObjectError> allErrors = bindingResult.getAllErrors();
//            for (ObjectError allError : allErrors) {
//                System.out.println(allError.getDefaultMessage());
//            }
//            log.warn("task is not valid");
//            return "books/add-edit";
//        }
//        Task saved = service.saveTask(task);
//        return "redirect:/books/" + saved.getId();
//
//    }
}
