# Spring Cloud Gateway

---
## 参考网站
1. [Spring Cloud Gateway](https://cloud.spring.io/spring-cloud-gateway/reference/html/)
2. [Spring Cloud Gateway | Github](https://github.com/spring-cloud/spring-cloud-gateway)
3. [Spring Cloud Gateway | Baeldung](https://www.baeldung.com/?s=Spring+Cloud+Gateway)
---
## 使用
1. 依赖：注意与 org.springframework.boot:spring-boot-starter-web 冲突
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```
2. 配置
    1. application.yml
    ```yaml
    spring:
      cloud:
        gateway:
          routes:
            - predicates:
                - Path=/student/**
              filters:
                - StripPrefix=1
              uri: "http://localhost:9300/"
    ```
    2. ConfigBean
    ```java
    public class Routes {
        @Bean
        public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/student/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:9300/")
                )
                .build();
        }
    }
    ```
3. 调用其它服务：`http://localhost:8080/student/echoStudentName/david`
