# Sentinel
![主要特征](https://user-images.githubusercontent.com/9434884/50505538-2c484880-0aaf-11e9-9ffc-cbaaef20be2b.png)

---
## 参考网站
1. [Sentinel](https://sentinelguard.io/zh-cn/docs/quick-start.html)
2. [Sentinel | Github](https://github.com/alibaba/Sentinel)
3. [Introduction to Alibaba Sentinel | Baeldung](https://www.baeldung.com/java-sentinel-intro)
4. [Sentinel 视频教程](https://www.bilibili.com/video/BV19Q4y1d7wA)
---
## Dashboard
1. [Sentinel Releases](https://github.com/alibaba/Sentinel/releases)
2. `java -Dserver.port=8858 -Dcsp.sentinel.dashboard.server=localhost:8858 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.3.jar`
3. GUI：`http://localhost:8858`，`sentinel/sentinel`
4. 依赖：
```xml
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-transport-simple-http</artifactId>
</dependency>
```
5. Edit Configurations... → Configuration → Environment
    - VM options: `-Dcsp.sentinel.dashboard.server=localhost:8858 -Dproject.name=sentinel`
---
## 基本使用
- 依赖
```xml
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-core</artifactId>
</dependency>
```
### 定义资源
1. [主流框架默认适配](https://sentinelguard.io/zh-cn/docs/open-source-framework-integrations.html)
2. 抛出异常方式：`Entry SphU.entry(String name)`
```
try (Entry entry = SphU.entry("resourceName")) {
    // 业务逻辑
} catch (BlockException e) {
}
```
3. 返回布尔值方式：`boolean SphO.entry(String name)`
```
if (SphO.entry("resourceName")) {
    try {
        // 业务逻辑
    } finally {
        SphO.exit();
    }
} else {
}
```
4. 注解方式：`@SentinelResource`
```xml
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-annotation-aspectj</artifactId>
</dependency>
```
```java
@RestController
@Configuration
public class SentinelConfig {
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
    @GetMapping("hello")
    @SentinelResource(value = "resourceName", blockHandler = "blockHandler")
    public void hello() {
        // ...
    }
    public String helloBlockHandler(BlockException e) {
        // ...
    }
}
```
5. [异步调用：](https://sentinelguard.io/zh-cn/docs/basic-api-resource-rule.html)
    - `AsyncEntry SphU.asyncEntry(String name)`
    - `void runOnContext(Context context, Runnable f)`
### 定义规则
1. [FlowRule](https://sentinelguard.io/zh-cn/docs/flow-control.html) ：流量控制规则

| Field           | 说明                                                                           | 默认值     |
|:----------------|:-----------------------------------------------------------------------------|:--------|
| resource        | 资源名                                                                          ||
| grade           | 类型：QPS, Thread(并发线程数)                                                        | QPS     |
| count           | 阈值                                                                           ||
| limitApp        | 调用来源：<br/>default：不区分调用来源<br/>other：其它来源                                     | default |
| strategy        | 调用关系策略：<br/>Direct：直接，资源本身<br/>Relate：关联<br/>Chain：链路                        | Direct  |
| controlBehavior | 控制表现：<br/>Default：直接拒绝，系统最高水平下<br/>Warn Up：冷启动，逐渐增加到阈值<br/>Rate Limiter：匀速通过 | Default |
| cluster         | 是否集群限流                                                                       | false   |
2. [DegradeRule](https://sentinelguard.io/zh-cn/docs/circuit-breaking.html) ：熔断降级规则

| Field              | 说明                                                                                            | 默认值                |
|:-------------------|:----------------------------------------------------------------------------------------------|:-------------------|
| resource           | 资源名                                                                                           ||
| grade              | CircuitBreakerStrategy：<br/>Slow Request Ratio：慢调用比例<br/>Error Ratio：异常比例<br/>Error Count：异常数 | Slow Request Ratio |
| slowRatioThreshold | 慢调用比例阈值                                                                                       ||
| count              | 阈值                                                                                            ||
| timeWindow         | 熔断时长（秒）                                                                                       ||
| minRequestAmount   | 触发熔断所需最少请求数                                                                                   | 5                  |
| statIntervalMs     | 统计间隔（ms）                                                                                      ||
3. [SystemRule](https://sentinelguard.io/zh-cn/docs/system-adaptive-protection.html) ：系统保护规则

| Field             | 说明                      | 默认值 |
|:------------------|:------------------------|:----|
| highestSystemLoad | 系统负载阈值，且 maxQps * minRt | -1  |
| avgRt             | 平均响应时间                  | -1  |
| maxThread         | 并发线程数                   | -1  |
| qps               | QPS                     | -1  |
| highestCpuUsage   | CPU 使用率阈值               | -1  |
4. [AuthorityRule](https://sentinelguard.io/zh-cn/docs/origin-authority-control.html) ：访问权限规则
- 通过 `ContextUtil.enter(resourceName, origin)` 传入 origin

| Field    | 说明              | 默认值   |
|:---------|:----------------|:------|
| resource | 资源名             ||
| strategy | 策略：White，Black  | White |
| limitApp | 不同 origin 用逗号分隔 ||
5. [ParamFlowRule](https://sentinelguard.io/zh-cn/docs/parameter-flow-control.html) ：热点参数规则
- 依赖
```xml
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-parameter-flow-control</artifactId>
</dependency>
```
- 通过 `Entry entry(String name, EntryType type, int count, Object... args)` 传入参数

| Field             | 说明                                           | 默认值     |
|:------------------|:---------------------------------------------|:--------|
| resource          | 资源名                                          ||
| count             | 阈值                                           ||
| grade             | 类型                                           | QPS     |
| durationInSec     | 期间（秒）                                        | 1s      |
| controlBehavior   | 控制表现：<br/>Default：直接拒绝<br/>Rate Limiter：匀速通过 | Default |
| maxQueueingTimeMs | 最大队列时间（ms）                                   | 0ms     |
| paramIdx          | 热点参数索引                                       |
| paramFlowItemList | 针对指定参数单独设置限流阈值，<br/>仅支持 primitive 和 String   ||
| cluster           | 是否集群限流                                       | false   |
| clusterConfig     | 集群限流相关配置                                     ||
---
## 整合 Nacos
1. 依赖
```xml
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-datasource-nacos</artifactId>
</dependency>
```
---
2. Nacos 新建配置
```json
[
  {
    "resource": "Hello",
    "grade": 1,
    "count": 2,
    "limitApp": "default",
    "strategy": 0,
    "controlBehavior": 0,
    "clusterMode": false
  }
]
```
3. application.properties
```properties
spring.cloud.sentinel.datasource.ds.nacos.server-addr=localhost:8848
spring.cloud.sentinel.datasource.ds.nacos.group-id=DEFAULT_GROUP
spring.cloud.sentinel.datasource.ds.nacos.data-id=sentinel
spring.cloud.sentinel.datasource.ds.nacos.data-type=json
spring.cloud.sentinel.datasource.ds.nacos.rule-type=flow
```
## 整合 Spring Cloud
1. 依赖
```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
</dependency>
```
2. application.properties
```properties
spring.application.name=sentinel
spring.cloud.sentinel.transport.dashboard=localhost:8858
spring.cloud.sentinel.log.dir=logs/csp
```
---
