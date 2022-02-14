package com.ljh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * Gateway
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

    // 配置 routes
    // @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/student/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8090/")
                )
                .build();
    }
}
