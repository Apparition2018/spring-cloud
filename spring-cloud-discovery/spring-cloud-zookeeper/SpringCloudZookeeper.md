# Spring Cloud Zookeeper

---
## 参考网站
1. [Spring Cloud Zookeeper](https://spring.io/projects/spring-cloud-zookeeper)
2. [Spring Cloud Zookeeper | Github](https://github.com/spring-cloud/spring-cloud-zookeeper)
3. [An Intro to Spring Cloud Zookeeper | Baeldung](https://www.baeldung.com/spring-cloud-zookeeper)
---
## 安装 Zookeeper
- [Apache ZooKeeper™ Releases](https://zookeeper.apache.org/releases.html)
- ${Zookeeper_Home}/conf/zoo.cfg
```
dataDir=C:\\Users\\Administrator\\Desktop\\data
dataLogDir=C:\\Users\\Administrator\\Desktop\\log
audit.enable=true
```
---
## ZooInspector
- [Download](https://issues.apache.org/jira/secure/attachment/12436620/ZooInspect)
- ${ZooInspector_Home}/build: `java -Dfile.encoding=UTF-8 -jar zookeeper-dev-ZooInspector.jar`
- 左上角绿色箭头 → Connect String → OK
---
## 使用
1. 依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
</dependency>
```
2. `@EnableDiscoveryClient`
3. application.properties
```properties
spring.application.name=zookeeper
spring.cloud.zookeeper.connect-string=localhost:2181
spring.cloud.zookeeper.discovery.enabled=true
```
---
