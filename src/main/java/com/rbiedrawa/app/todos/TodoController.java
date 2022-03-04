package com.rbiedrawa.app.todos;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(TodoController.BASE_PATH)
@RequiredArgsConstructor
public class TodoController {
    public static final String BASE_PATH = "/api/v1/todos";

    private final TodoService todoService;

    @GetMapping
    ResponseEntity<List<Todo>> findAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

}
