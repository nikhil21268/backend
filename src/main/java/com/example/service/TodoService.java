package com.example.service;

import com.example.entity.Project;
import com.example.entity.Todo;
import com.example.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ProjectService projectService;

    public Todo addTodo(Long projectId, String description) {
        Project project = projectService.getProject(projectId);
        if (project != null) {
            Todo todo = new Todo();
            todo.setDescription(description);
            todo.setStatus(false);
            todo.setCreatedDate(LocalDateTime.now());
            todo.setProject(project);
            return todoRepository.save(todo);
        }
        return null;
    }

    public List<Todo> getTodosByProject(Long projectId) {
        Project project = projectService.getProject(projectId);
        if (project != null) {
            return project.getTodos();
        }
        return null;
    }

    public Todo updateTodoStatus(Long todoId, boolean status) {
        Todo todo = todoRepository.findById(todoId).orElse(null);
        if (todo != null) {
            todo.setStatus(status);
            todo.setUpdatedDate(LocalDateTime.now());
            return todoRepository.save(todo);
        }
        return null;
    }

    public void deleteTodo(Long todoId) {
        todoRepository.deleteById(todoId);
    }
}
