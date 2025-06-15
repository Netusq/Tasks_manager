package com.example.todo_list.entity;

import com.example.todo_list.model.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "tasks")
public class Task {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false,length = 100,unique = true)
    private String title;

    @Setter
    @Getter
    @Column(length = 1000)
    private String description;

    @Setter
    @Getter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

}

