package com.example.taskmanagement.repository;

import com.example.taskmanagement.model.Status;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUsersOrderByDeadlineAsc(User user);
    Task findByTitle(String title);
    Task findByStatus(String status);
}
