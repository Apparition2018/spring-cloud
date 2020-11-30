package com.ljh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * eureka
 *
 * @author Arsenal
 * created on 2020/11/30 01:15
 */
@EnableEurekaClient
@SpringBootApplication
public class EurekaClientStudentServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientStudentServiceApp.class, args);
    }
}
