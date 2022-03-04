package com.rbiedrawa.app.todos.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.apis.todo-service")
public class TodoServiceProperties {
	private String baseUrl;
}