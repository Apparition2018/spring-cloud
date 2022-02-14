package com.ljh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Zookeeper
 *
 * @author Arsenal
 * created on 2020/12/01 00:09
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ZookeeperApp {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperApp.class, args);
    }
}
