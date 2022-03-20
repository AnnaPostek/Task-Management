package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.User;
import com.example.taskmanagement.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService service;

    @Test
    public void shouldReturnCreateStatus_whenUserIsSaved() throws Exception {
        User user = getBasicUser();
        service.addUser(user);
        this.mockMvc
                .perform(post("/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
        User userById = service.getUserById(user.getId());
        assertEquals(userById.getSurname(), "Kowalska");
    }

    @Test
    public void shouldReturnOkStatus_whenDisplayListOfUser() throws Exception {
        User user1 = new User(1L, "Anna", "Kowalska", "anna.kowalska@gmail.com");
        User user2 = new User(2L, "Jan", "Nowak", "jan.nowak@gmail.com");
        User user3 = new User(3L, "Stanisław", "Dębski", "stanislaw.debski@gmail.com");
        List<User> taskList = List.of(user1, user2, user3);
        this.mockMvc
                .perform(get("/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskList.toString())))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOkStatus_whenDeleteTaskById() throws Exception {
        User basicUser = getBasicUser();
        service.addUser(basicUser);
        Long id = service.getAllUsers().get(0).getId();
        this.mockMvc
                .perform(delete("/user/{id}", String.valueOf(id))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void shouldReturnOkStatus_whenDisplayUserById() throws Exception {
        User basicUser = getBasicUser();
        service.addUser(basicUser);
        Long id = service.getAllUsers().get(0).getId();
        this.mockMvc
                .perform(get("/user/{id}", String.valueOf(id))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    

    private User getBasicUser(){
        User user = new User();
        user.setName("Anna");
        user.setSurname("Kowalska");
        user.setEmail("anna.kowalska@gmail.com");
        return user;
    }

}