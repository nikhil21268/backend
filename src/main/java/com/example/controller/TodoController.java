package com.example.controller;

import com.example.entity.Todo;
import com.example.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping
    public Todo addTodo(@RequestParam Long projectId, @RequestParam String description) {
        return todoService.addTodo(projectId, description);
    }

    @GetMapping("/project/{projectId}")
    public List<Todo> getTodosByProject(@PathVariable Long projectId) {
        return todoService.getTodosByProject(projectId);
    }

    @PutMapping("/{id}/status")
    public Todo updateTodoStatus(@PathVariable Long id, @RequestParam boolean status) {
        return todoService.updateTodoStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}
