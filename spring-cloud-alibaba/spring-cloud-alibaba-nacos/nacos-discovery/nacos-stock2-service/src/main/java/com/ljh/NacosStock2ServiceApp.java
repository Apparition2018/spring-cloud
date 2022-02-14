package com.ljh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Nacos Stock2 Service
 *
 * @author ljh
 * created on 2022/2/14 0:24
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosStock2ServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(NacosStock2ServiceApp.class);
    }
}
