package com.ljh;

import com.ljh.filter.ErrorFilter;
import com.ljh.filter.PostFilter;
import com.ljh.filter.PreFilter;
import com.ljh.filter.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * zuul 1.x
 *
 * @author Arsenal
 * created on 2020/11/29 19:18
 */
@EnableZuulProxy
@SpringBootApplication
public class ZuulApp {

    /**
     * http://localhost:8080/student/echoStudentName/david
     * http://localhost:8080/student/getStudentDetail/david
     */
    public static void main(String[] args) {
        SpringApplication.run(ZuulApp.class, args);
    }

    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }

    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }

    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }

}
