package com.ljh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * gateway
 *
 * @author Arsenal
 * created on 2020/11/29 19:18
 */
@SpringBootApplication
public class GatewayApp {

    /**
     * http://localhost:8080/student/echoStudentName/david
     * http://localhost:8080/student/getStudentDetail/david
     */
    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class, args);
    }
}
