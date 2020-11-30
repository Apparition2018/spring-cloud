package com.ljh;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java 配置 Routes
 *
 * @author Arsenal
 * created on 2020/11/29 19:53
 */
// @Configuration
public class GatewayRoutes {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/student/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8090/")
                )
                .build();
    }
}
