package com.example.taskmanagement.service;

import com.example.taskmanagement.exception.UserNotFoundException;
import com.example.taskmanagement.model.User;
import com.example.taskmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public User addUser(User user) {
        return repository.save(user);
    }
    public User getUserById(long id) {
        return repository.findById(id)
                .orElseThrow(()-> {
                    throw new UserNotFoundException(String.format("User with this id = %d not found", id));
                });
    }

    public boolean userExistById(Long id) {
        return repository.existsById(id);
    }

    public void deleteUser(long id) {
        repository.deleteById(id);
    }

    public List<User> getUserListByNameOrdered(String name) {
       return repository.findAllByNameOrderBySurname(name);
    }
}
