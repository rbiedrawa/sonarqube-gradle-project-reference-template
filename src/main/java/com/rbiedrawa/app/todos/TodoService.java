package com.rbiedrawa.app.todos;

import com.rbiedrawa.app.todos.properties.TodoServiceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class TodoService {
    static final String BASE_PATH = "/todos";
    private final RestTemplate restTemplate;

    public TodoService(final RestTemplateBuilder restTemplateBuilder, final TodoServiceProperties properties) {
        this.restTemplate = restTemplateBuilder
                .rootUri(properties.getBaseUrl())
                .build();
    }

    public List<Todo> findAll() {
        return restTemplate.exchange(
                BASE_PATH,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Todo>>() {
                }
        ).getBody();
    }

}
