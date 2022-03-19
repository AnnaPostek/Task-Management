package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.User;
import com.example.taskmanagement.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/allUser")
    public List<User> getList() {
        return service.getAllUsers();
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody @Valid User user) {
        return service.addUser(user);
    }

    @GetMapping("/user/{name}")
    public List<User> list (@PathVariable String name) {
       return service.getUserListbyName(name);
    }
}
