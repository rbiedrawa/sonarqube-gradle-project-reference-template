package com.rbiedrawa.app.todos;

import lombok.Value;

@Value
public class Todo {
    Long id;
    Long userId;
    String title;
    Boolean completed;
}
