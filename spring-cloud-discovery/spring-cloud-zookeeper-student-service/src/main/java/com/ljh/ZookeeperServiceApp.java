package com.ljh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * zookeeper
 *
 * @author Arsenal
 * created on 2020/12/01 00:09
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ZookeeperServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ZookeeperServiceApp.class, args);
    }
}
