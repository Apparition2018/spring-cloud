package com.ljh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Consul
 *
 * @author Arsenal
 * created on 2020/11/30 20:15
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulApp {

    public static void main(String[] args) {
        SpringApplication.run(ConsulApp.class, args);
    }
}
