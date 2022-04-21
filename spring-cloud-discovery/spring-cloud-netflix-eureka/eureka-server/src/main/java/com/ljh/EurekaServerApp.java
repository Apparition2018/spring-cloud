package com.ljh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka Server
 *
 * @author Arsenal
 * created on 2020/11/29 22:26
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApp {

    /**
     * http://localhost:9201
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApp.class, args);
    }
}
