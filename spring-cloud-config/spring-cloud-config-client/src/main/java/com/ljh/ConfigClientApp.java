package com.ljh;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Config Client
 *
 * @author Arsenal
 * created on 2020/12/1 2:04
 */
@RestController
@SpringBootApplication
public class ConfigClientApp {

    @Value("${guest.name}")
    private String name;

    @Resource
    private Guest guest;

    @GetMapping("hello")
    public String hello() {
        return "Hello, " + name;
    }

    @GetMapping("hello2")
    public String hello2() {
        return "Hello, " + guest;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApp.class, args);
    }

    @Data
    @Component
    @ConfigurationProperties(prefix = "guest")
    private static class Guest {
        private String name;
        private Integer age;
    }
}
