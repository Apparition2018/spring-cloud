# Spring Cloud Config

---
## 参考网站
1. [Spring Cloud Config](https://cloud.spring.io/spring-cloud-config/reference/html/)
2. [Spring Cloud Config | Github](https://github.com/spring-cloud/spring-cloud-config)
3. [Spring Cloud Config | Baeldung](https://www.baeldung.com/?s=Spring+Cloud+Config)
4. [Spring Cloud Config | 风的姿态](https://www.cnblogs.com/fengzheng/p/11242128.html)
5. [应用SpringCloud完成自定义配置中心开发实践](https://www.imooc.com/learn/1135)
---
## 配置服务器
1. 依赖
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
    server.port=9100
    spring.application.name=config-server
    spring.profiles.active=native
    spring.cloud.config.server.native.search-locations=D:/Liang/git/spring-cloud/spring-cloud-config/config
    ```
    - git
    ```properties
    server.port=9100
    spring.application.name=config-server
    spring.cloud.config.server.git.uri=https://gitee.com/Apparition2018/file-server
    spring.cloud.config.server.git.clone-on-start=true
    spring.cloud.config.server.git.default-label=master
    spring.cloud.config.server.git.search-paths=config
    spring.cloud.config.server.git.username=
    spring.cloud.config.server.git.passphrase=
    ```
4. 查询
```
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```
---
## 配置客户端
1. 依赖
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
spring.cloud.config.uri=http://localhost:9100
# git
spring.cloud.config.label=master
```
--- 
