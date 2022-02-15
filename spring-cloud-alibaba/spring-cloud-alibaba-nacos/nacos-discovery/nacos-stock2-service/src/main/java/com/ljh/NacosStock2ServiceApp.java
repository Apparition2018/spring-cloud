package com.ljh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Nacos Stock2 Service
 *
 * @author ljh
 * created on 2022/2/14 0:24
 */
@RestController
@RequestMapping("stock")
@SpringBootApplication
@EnableDiscoveryClient
public class NacosStock2ServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(NacosStock2ServiceApp.class);
    }

    @GetMapping("/test")
    public String test(String info) {
        return "Stock2 模块接收到的信息 " + info;
    }
}
