# Spring Cloud Config

---
## 参考网站
1. [Spring Cloud Config](https://cloud.spring.io/spring-cloud-config/reference/html/)
2. [Quick Intro to Spring Cloud Configuration | Baeldung](https://www.baeldung.com/spring-cloud-configuration)
3. [Spring Cloud Config | 风的姿态](https://www.cnblogs.com/fengzheng/p/11242128.html)
4. [应用SpringCloud完成自定义配置中心开发实践](https://www.imooc.com/learn/1135)
---
## 配置服务器
1. 依赖：
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```
2. `@EnableConfigServer`
3. application.properties
    - 本地
    ```properties
    server.port=8081
    spring.application.name=config-server
    # 本地读取配置
    spring.profiles.active=native
    spring.cloud.config.server.native.search-locations=D:/Liang/git/spring-cloud/spring-cloud-config/config
    ```
---
## 配置客户端
1. 依赖：
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>

```
2. bootstrap.properties
```properties
# 配置文件名称=${spring.application.name}-${spring.cloud.config.profile}，即：config-client-dev
spring.application.name=config-client
spring.cloud.config.profile=dev
spring.cloud.config.uri=http://localhost:8081
```
--- 