package com.ljh;

import com.ljh.feign.StockFeignService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Nacos Order Service
 *
 * @author ljh
 * created on 2022/2/14 0:24
 */
@RestController
@RequestMapping("order")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.ljh.feign")
public class NacosOrderServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(NacosOrderServiceApp.class);
    }

    private final StockFeignService stockFeignService;

    public NacosOrderServiceApp(StockFeignService stockFeignService) {
        this.stockFeignService = stockFeignService;
    }

    @GetMapping("/test")
    public String test() {
        return stockFeignService.test("stock info");
    }
}
