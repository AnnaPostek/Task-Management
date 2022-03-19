package com.example.taskmanagement.service;

import com.example.taskmanagement.exception.TaskNotFoundException;
import com.example.taskmanagement.exception.UserNotFoundException;
import com.example.taskmanagement.model.Status;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.User;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class TaskService {

    private TaskRepository repository;
    private UserRepository userRepository;

    public TaskService(TaskRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTaskById(Long id) {
        return findTaskById(id);
    }

    public Task updateTask(Long id, Task toUpdate) {
        Task task = findTaskById(id);
        task.setTitle(toUpdate.getTitle());
        task.setDescription(toUpdate.getDescription());
        task.setDeadline(toUpdate.getDeadline());
        task.setStatus(toUpdate.getStatus());
        task.setUsers(toUpdate.getUsers());
        return repository.save(task);
    }

    public void deleteTask(long id) {
        repository.deleteById(id);
    }

    public Task changeStatus(long id, Status status) {
        Task task = findTaskById(id);
        task.setStatus(status);
      return  repository.save(task);
    }

    @Transactional
    public Task saveTask(Task task) {
        return repository.save(task);
    }

    @Transactional
    public Task addUserToTask(Long taskId, User user) {
        Task task = findTaskById(taskId);
        task.addUser(user);
        return repository.save(task);
    }

    private Task findTaskById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    throw new TaskNotFoundException((String.format("Task with this id = %d not found", id)));
                });
    }


    public List<Task> findTaskListForUserByDateAsc(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException(String.format("User with this id = %d not found", userId));
        });
        return repository.findByUsersOrderByDeadlineAsc(user);
    }

}
