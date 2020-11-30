package com.ljh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Arsenal
 * created on 2020/12/1 2:04
 */
@RestController
@SpringBootApplication
public class ConfigClientApp {

    @Value("${guestname}")
    String name;

    /**
     * localhost:8030
     */
    @RequestMapping("/")
    public String sayHello() {
        return "Hello, " + name;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApp.class, args);
    }
}
