package com.example.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private boolean status; // true for complete, false for pending
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    // Constructors, Getters, and Setters
}
