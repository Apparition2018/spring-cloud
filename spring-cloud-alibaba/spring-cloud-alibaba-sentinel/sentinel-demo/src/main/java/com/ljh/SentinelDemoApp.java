package com.ljh;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Sentinel Demo
 *
 * @author ljh
 * created on 2022/2/15 11:44
 */
@EnableAsync
@SpringBootApplication
public class SentinelDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(SentinelDemoApp.class);
    }

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
