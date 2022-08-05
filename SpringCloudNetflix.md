# Spring Cloud Netflix

---
## 参考网站
1. [Spring Cloud Netflix](https://cloud.spring.io/spring-cloud-netflix/reference/html/)
2. [Spring Cloud Netflix | Github](https://github.com/spring-cloud/spring-cloud-netflix)
---
## Features
1. 服务发现：Eureka
2. 断路器：Hystrix
3. 智能路由：Zuul
4. 客户端负载均衡 ：Ribbon
---
## Eureka
### 服务端
1. 依赖
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```
2. `@EnableEurekaServer`
3. application.properties
```properties
server.port=9201
eureka.instance.hostname=localhost
eureka.server.wait-time-in-ms-when-sync-empty=0
# 注册中心，不注册自己
eureka.client.register-with-eureka=false
# 注册中心，维护服务实例，不需要区检索服务
eureka.client.fetch-registry=false
# 默认服务注册中心地址，多个用","隔开
eureka.client.service-url.default-zone=http://${eureka.instance.hostname}:9201/eureka
```
4. GUI：`http://localhost:9201`
### 客户端
1. 依赖
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
2. `@EnableEurekaClient`
3. application.properties
```properties
eureka.instance.hostname=localhost
# 服务续约任务调用间隔时间，默认30秒
# client 每隔30秒向 server 上报自己状态，避免被 server 剔除
eureka.instance.lease-renewal-interval-in-seconds=5
# 服务时效时间，默认90秒
# 当 server 90秒内没有收到 client 的注册信息时，会将该节点剔除
eureka.instance.lease-expiration-duration-in-seconds=10
# 默认服务注册中心地址，多个用","隔开
eureka.client.service-url.default-zone=http://${eureka.instance.hostname}:9201/eureka
# 需要 actuator
eureka.client.healthcheck.enabled=true
```
---
## Zuul
1. 依赖
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
</dependency>
```
2. `@EnableZuulProxy`
3. application.properties
```properties
spring.application.name=zuul
zuul.routes.student.url=http://localhost:9300
```
3. 调用其它服务：`http://localhost:8080/student/echoStudentName/david`
---
