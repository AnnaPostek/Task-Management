package com.example.taskmanagement.service;

import com.example.taskmanagement.exception.UserNotFoundException;
import com.example.taskmanagement.model.User;
import com.example.taskmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(long id) {
        return repository.findById(id)
                .orElseThrow(()-> {
                    throw new UserNotFoundException(String.format("User with this id = %d not found", id));
                });
    }

    public User updateUser(long id, User toUpdate) {
        User user = repository.findById(id)
                .orElseThrow(() -> {
                    throw new UserNotFoundException(String.format("User with this id = %d not found", id));
                });
        user.setName(toUpdate.getName());
        user.setSurname(toUpdate.getSurname());
        user.setEmail(toUpdate.getEmail());
        return repository.save(user);
    }

    public void deleteUser(long id) {
        repository.deleteById(id);
    }
}