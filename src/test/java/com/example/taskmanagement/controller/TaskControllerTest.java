package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.Status;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TaskService service;

    @Test
    public void shouldReturnCreateStatus_whenTaskIsSaved() throws Exception {
        Task task = getBasicTask();
        service.saveTask(task);
        this.mockMvc
                .perform(post("/task")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isCreated());
        Task taskById = service.getTaskById(task.getId());
        assertEquals(taskById.getTitle(), "learn");
    }

    @Test
    public void shouldReturnOkStatus_whenDisplayListOfTasks() throws Exception {
        Task task1 = new Task(1, "java", "learn", Status.IN_PROGRESS, LocalDateTime.now().plusDays(4));
        Task task2 = new Task(2, "book", "read", Status.DONE, LocalDateTime.now().minusDays(3));
        Task task3 = new Task(3, "food", "eat", Status.CANCEL, LocalDateTime.now().minusDays(1));
        List<Task> taskList = List.of(task1, task2, task3);
        this.mockMvc
                .perform(get("/tasks")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskList.toString())))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOkStatus_whenDeleteTaskById() throws Exception {
        Task basicTask = getBasicTask();
        service.saveTask(basicTask);
        Long id = service.getAllTasks().get(0).getId();
        this.mockMvc
                .perform(delete("/task/{id}", String.valueOf(id))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void shouldReturnOkStatus_whenDisplayTaskById() throws Exception {
        Task basicTask = getBasicTask();
        service.saveTask(basicTask);
        Long id = service.getAllTasks().get(0).getId();
        this.mockMvc
                .perform(get("/task/{id}", String.valueOf(id))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOkStatus_whenUpdateTask() throws Exception {
        Task basicTask = getBasicTask();
        service.saveTask(basicTask);
        Long id = service.getAllTasks().get(0).getId();
        String title = "title";
        String description = "description";
        this.mockMvc
                .perform(get("/task/{id}", String.valueOf(id))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(title))
                        .content(objectMapper.writeValueAsString(description)))
                .andExpect(status().isOk());
    }

    private Task getBasicTask() {
        Task task = new Task();
        task.setTitle("learn");
        task.setDescription("test");
        task.setStatus(Status.IN_PROGRESS);
        task.setDeadline(LocalDateTime.now().plusDays(5));
        return task;
    }
}