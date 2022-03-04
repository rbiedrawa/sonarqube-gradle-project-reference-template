package com.rbiedrawa.app.todos;

import com.rbiedrawa.app.IntegrationTestWithWireMockServer;
import com.rbiedrawa.app.utils.TestFileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
                .andExpect(content().json(TestFileUtils.readAsString("wiremock/__files/todos-response.json")))
                .andExpect(jsonPath("$").isArray())
                // Example how to check single value
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].userId").value(1L))
                .andExpect(jsonPath("$[0].title").value("delectus aut autem"))
                .andExpect(jsonPath("$[0].completed").value(false));
    }
}