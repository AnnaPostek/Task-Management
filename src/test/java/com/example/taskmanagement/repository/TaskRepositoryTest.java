package com.example.taskmanagement.repository;

import com.example.taskmanagement.exception.TaskNotFoundException;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class TaskRepositoryTest {
    @Autowired
    private TaskRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateSizeOfNewTask() {
        List<Task> listTasks = repository.findAll();

        assertThat(listTasks.size()).isEqualTo(3);
    }

    @Test
    public void testCreateNewTask() {

        Task task = repository.findById(4L)
                .orElseThrow(() -> {
                    throw new TaskNotFoundException((String.format("Task with this id = %d not found", id)));
                });
        Task save = repository.save(task);
        Task existTask = entityManager.find(Task.class, save.getId());
        assertThat(task.getTitle()).isEqualTo(existTask.getTitle());
    }
}