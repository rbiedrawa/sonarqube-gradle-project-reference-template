package com.rbiedrawa.app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@EnableScheduling
public class SchedulingConfiguration {

    @PostConstruct
    void init() {
        log.info("Enabled Scheduling !!!");
    }

}
