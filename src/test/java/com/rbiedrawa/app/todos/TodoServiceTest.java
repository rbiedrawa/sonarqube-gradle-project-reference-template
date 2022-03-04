package com.rbiedrawa.app.todos;

import com.rbiedrawa.app.todos.properties.TodoServiceProperties;
import com.rbiedrawa.app.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest({TodoService.class, TodoServiceProperties.class})
class TodoServiceTest {

    @Autowired
    private TodoService cut;

    @Autowired
    private MockRestServiceServer mockServer;

    @Value("classpath:data/todos-response.json")
    private Resource todosSuccessResponse;

    @Test
    void should_return_all_todos() {
        // Given
        final var todosJson = JsonUtils.readAsJsonString(todosSuccessResponse);
        final var expectedTodos = JsonUtils.readValueAsList(todosJson, Todo.class);

        mockServer.expect(once(), requestTo(TodoService.BASE_PATH))
                .andRespond(withSuccess()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(todosSuccessResponse));


        // When
        final var actualTodos = cut.findAll();

        // Then
        mockServer.verify();
        assertThat(actualTodos).isNotNull()
                .containsExactlyInAnyOrderElementsOf(expectedTodos);
    }
}