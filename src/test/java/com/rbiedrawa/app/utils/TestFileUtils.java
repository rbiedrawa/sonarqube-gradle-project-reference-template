package com.rbiedrawa.app.utils;

import lombok.SneakyThrows;
import wiremock.org.apache.commons.io.IOUtils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public final class TestFileUtils {
    private TestFileUtils() {
    }

    @SneakyThrows
    public static String readAsString(String classpathFile) {
        var classLoader = TestFileUtils.class.getClassLoader();
        final var resourceUrl = Objects.requireNonNull(classLoader.getResource(classpathFile));
        return IOUtils.toString(resourceUrl.openStream(), StandardCharsets.UTF_8.toString());
    }
}
