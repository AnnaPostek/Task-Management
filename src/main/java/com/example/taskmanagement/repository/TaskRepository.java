package com.example.taskmanagement.repository;

import com.example.taskmanagement.model.Status;
import com.example.taskmanagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

//    List<Task> findByTitle(String title);
//    List<Task> findByTitleAndDeadlineIsNear(String title);
//    List<Task> findByStatus(Status status);
//    List<Task> findByStatusAndDeadlineIsNear(Status status);
//    List<Task> findByDeadlineIsNear();
}
