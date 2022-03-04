package com.rbiedrawa.app.utils;

import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rbiedrawa.app.todos.Todo;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import wiremock.org.apache.commons.io.IOUtils;

public enum JsonUtils {
    ;

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
    }

    @SneakyThrows
    public static String writeValueAsString(final Object jsonObject) {
        return OBJECT_MAPPER.writeValueAsString(jsonObject);
    }

    @SneakyThrows
    public static <T> T readValue(final String json, final Class<T> clazz) {
        return OBJECT_MAPPER.readValue(json, clazz);
    }

    @SneakyThrows
    public static <T> List<T> readValueAsList(final String json, final Class<T> clazz) {
        return OBJECT_MAPPER.readValue(json, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    @SneakyThrows
    public static <T> T readValue(final MvcResult mvcResult, final Class<T> clazz) {
        return OBJECT_MAPPER.readValue(mvcResult.getResponse().getContentAsString(), clazz);
    }

    @SneakyThrows
    public static String readAsJsonString(final Resource resource) {
        return IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8.toString());
    }
}