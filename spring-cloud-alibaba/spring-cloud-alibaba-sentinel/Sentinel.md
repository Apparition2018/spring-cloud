# Sentinel
![主要特征](https://user-images.githubusercontent.com/9434884/50505538-2c484880-0aaf-11e9-9ffc-cbaaef20be2b.png)

---
## 参考网站
1. [Sentinel](https://sentinelguard.io/zh-cn/docs/quick-start.html)
2. [Sentinel | Github](https://github.com/alibaba/Sentinel)
3. [Introduction to Alibaba Sentinel | Baeldung](https://www.baeldung.com/java-sentinel-intro)
---
## Dashboard
1. [Sentinel Releases](https://github.com/alibaba/Sentinel/releases)
2. `java -Dserver.port=8180 -Dcsp.sentinel.dashboard.server=localhost:8180 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.3.jar`
3. GUI：`http://localhost:8180`，`sentinel/sentinel`
4. 依赖：
```
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-transport-simple-http</artifactId>
</dependency>
```
5. Edit Configurations... → Configuration → Environment
    - VM options: `-Dcsp.sentinel.dashboard.server=localhost:8180 -Dproject.name=sentinel-dashboard=demo`
---
## 基本使用
- 依赖
```
<dependency>
   <groupId>com.alibaba.csp</groupId>
   <artifactId>sentinel-core</artifactId>
</dependency>
```
> ### 定义资源
>1. [主流框架默认适配](https://sentinelguard.io/zh-cn/docs/open-source-framework-integrations.html)
>2. 抛出异常方式：`SphU`
>```
>try (Entry entry = SphU.entry("resourceName")) {
>   // 业务逻辑
>} catch (BlockException ex) {
>}
>```
>3. 返回布尔值方式：`SphO`
>```
>if (SphO.entry("resourceName")) {
>   try {
>       // 业务逻辑
>   } finally {
>       SphO.exit();
>   }
>} else {
>}
>```
>4. 注解方式：`@SentinelResource`
>```java
>@RestController
>@Configuration
>public class SentinelConfig {
>   @Bean
>   public SentinelResourceAspect sentinelResourceAspect() {
>       return new SentinelResourceAspect();
>   }
>   @GetMapping("hello")
>   @SentinelResource(value = "resourceName", blockHandler = "blockHandler")
>   public void hello() {
>       // ...
>   }
>   public String helloBlockHandler(BlockException ex) {
>       // ...
>   }
>}
>```
>5. 异步调用
>   1. `@EnableAsync`  
>### 定义规则
---
