# Spring Cloud Consul

---
## 参考网站
1. [Spring Cloud Consul](https://cloud.spring.io/spring-cloud-consul/reference/html/)
2. [Spring Cloud Consul | Github](https://github.com/spring-cloud/spring-cloud-consul)
3. [A Quick Guide to Spring Cloud Consul | Baeldung](https://www.baeldung.com/spring-cloud-consul)
---
## Consul.exe
- [Download Consul](https://www.consul.io/downloads)
- 命令航启动：`consul agent -dev`
- GUI：`http://localhost:8500`
---
1. 依赖
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
2. `@EnableDiscoveryClient`
3. application.properties
```properties
spring.application.name=consul
spring.cloud.consul.host=localhost
spring.cloud.consul.port=8500
spring.cloud.consul.discovery.instance-id=${spring.application.name}:${vcap.application.instance_id:${spring.application.instance_id:${random.value}}}
```
---