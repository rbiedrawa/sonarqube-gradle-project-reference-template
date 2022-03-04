package com.rbiedrawa.app;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;


import org.junit.jupiter.api.Tag;

import java.lang.annotation.*;

@Tag(IntegrationTestWithWireMockServer.INTEGRATION_TEST)
@ActiveProfiles(IntegrationTestWithWireMockServer.INTEGRATION_TEST)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = IntegrationTestWithWireMockServer.RANDOM_PORT)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface IntegrationTestWithWireMockServer {
    String INTEGRATION_TEST = "integrationTest";
    int RANDOM_PORT = 0;

}