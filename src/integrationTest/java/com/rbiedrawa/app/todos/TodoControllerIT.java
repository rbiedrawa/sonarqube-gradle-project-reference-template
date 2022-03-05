package com.rbiedrawa.app.todos;

import com.rbiedrawa.app.IntegrationTestWithWireMockServer;
import com.rbiedrawa.app.utils.TestFileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@IntegrationTestWithWireMockServer
class TodoControllerIT {

    @Autowired
    private MockMvc mvc;

    @Test
    void should_return_all_todos() throws Exception {
        // When
        var result = mvc.perform(get(TodoController.BASE_PATH)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        // Then
        result.andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(TestFileUtils.readAsString("wiremock/__files/get-all-todos-200-response.json")))
                .andExpect(jsonPath("$").isArray())
                // Example how to check single value
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].userId").value(1L))
                .andExpect(jsonPath("$[0].title").value("delectus aut autem"))
                .andExpect(jsonPath("$[0].completed").value(false));
    }

    @Test
    void should_return_todo_by_id() throws Exception {
        // Given
        final var todoId = new Random().nextLong();

        // When
        var result = mvc.perform(get(String.format("%s/%d", TodoController.BASE_PATH, todoId))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        // Then
        result.andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(todoId))
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.completed").exists());
    }
}