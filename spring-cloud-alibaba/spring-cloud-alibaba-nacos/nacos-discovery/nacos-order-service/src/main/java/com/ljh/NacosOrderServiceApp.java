package com.ljh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Nacos Order Service
 *
 * @author ljh
 * created on 2022/2/14 0:24
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.ljh.feign")
public class NacosOrderServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(NacosOrderServiceApp.class);
    }
}