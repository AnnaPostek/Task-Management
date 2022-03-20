package com.example.taskmanagement.controller;

import com.example.taskmanagement.exception.TaskNotFoundException;
import com.example.taskmanagement.exception.UserNotFoundException;
import com.example.taskmanagement.model.User;
import com.example.taskmanagement.service.UserService;
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
public class UserController {
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getList() {
        List<User> allUsers = service.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody @Valid User user) {
        User addUser = service.addUser(user);
        return new ResponseEntity<>(addUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

        if (service.userExistById(id)) {
            service.deleteUser(id);
            return new ResponseEntity<>(String.format("User with id = %d is deleted successfully", id), HttpStatus.OK);
        } else {
            throw new UserNotFoundException();
        }
    }

    @GetMapping("/user/{name}")
    public ResponseEntity<List<User>> list(@PathVariable String name) {
        List<User> userList = service.getUserListByNameOrdered(name);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> exception(UserNotFoundException exception) {
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
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
}
