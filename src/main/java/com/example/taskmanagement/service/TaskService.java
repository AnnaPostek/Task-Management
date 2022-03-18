package com.example.taskmanagement.service;

import com.example.taskmanagement.exception.TaskNotFoundException;
import com.example.taskmanagement.model.Status;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.User;
import com.example.taskmanagement.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTaskById(Long id) {
        return findTaskById(id);
    }

    public Task updateTask(Long id, Task toUpdate) {
        Task task = findTaskById(id);
        task.setTitle(task.getTitle());
        task.setDescription(task.getDescription());
        task.setDeadline(task.getDeadline());
        task.setStatus(task.getStatus());
        return repository.save(task);
    }

    public void deleteTask(long id) {
        repository.deleteById(id);
    }

    public void changeStatusForCancel(long id) {
        Task task = findTaskById(id);
        task.setStatus(Status.CANCEL);
        repository.save(task);
    }

    public void changeStatusForDone(long id) {
        Task task = findTaskById(id);
        task.setStatus(Status.DONE);
        repository.save(task);
    }
    public void changeStatusForInProgress(long id) {
        Task task = findTaskById(id);
        task.setStatus(Status.IN_PROGRESS);
        repository.save(task);
    }
    @Transactional
    public Task saveTask(Task task) {
        return repository.save(task);
    }

    private Task findTaskById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    throw new TaskNotFoundException((String.format("Task with this id = %d not found", id)));
                });
    }

    public void userAssign(Task task, User user) {
        task.addUser(user);
        repository.save(task);
    }

}
