package com.example.taskmanagement.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Builder
@NoArgsConstructor
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Task need to have a title")
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deadline;
    @ManyToMany
    @JoinTable(
            name = "task_user",
            joinColumns = @JoinColumn(name="task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();

    public Task(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Task(long id, String title, String description, Status status, LocalDateTime deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
    }

    public Task(long id, String title, String description, Status status, LocalDateTime deadline, List<User> users) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}
