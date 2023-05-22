# Nacos

---
## Reference
1. [Nacos](https://nacos.io/zh-cn/docs/quick-start.html)
2. [Nacos | Github](https://github.com/alibaba/nacos)
3. [Nacos Group | GitHub](https://github.com/nacos-group)
4. [Nacos 视频教程](https://www.bilibili.com/video/BV1WZ4y1w7ww)
---
## [Features](https://nacos.io/zh-cn/docs/feature-list.html)
1. 服务发现：服务注册与发现、健康检查、路由策略
2. 配置管理：配置管理、灰度配置、加密配置
3. 元数据管理：对接第三方 CMDB
4. 地址服务器：支持 Nacos 寻址
5. 安全与稳定性：metrics 收集
6. 客户端
7. Nacos-Docker：Docker 部署 Nacos Server
8. Nacos-Sync：与 Nacos、Zookeeper、Eureka、Consul 同步
---
## 安装
1. [Releases](https://github.com/alibaba/nacos/releases)
2. 自定义数据库：默认为嵌入式数据库 derby
    - 修改 nacos/conf/application.properties
        ```properties
        spring.datasource.platform=mysql
        db.num=1
        db.url.0=jdbc:mysql://127.0.0.1:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
        db.user.0=root
        db.password.0=root
        ```
    - 新建数据库 nacos，执行 nacos/conf/mysql-schema.sql
3. 启动服务器：nacos/bin/startup
    - 单体部署：修改 nacos/bin/startup `set MODE="standalone"`
    - [集群部署](https://nacos.io/zh-cn/docs/cluster-mode-quick-start.html)
        - 修改 nacos/bin/startup `set MODE="cluster"`
        - 修改 {nacos1,nacos2,nacos3}/conf/application.properties
            ```properties
            server.port=8848
            # server.port=8849
            # server.port=8850
            nacos.inetutils.ip-address=127.0.0.1
            # 配置多个数据源
            db.num=2
            # ……
            ```
        - nacos/conf/cluster.conf
            ```
            127.0.0.1:8848
            127.0.0.1:8849
            127.0.0.1:8850
            ```
4. Console：`http://localhost:8848/nacos/index.html` with `nacos/nacos`
---
## 数据模型
- Nacos 数据模型 Key 由三元组唯一确定
    1. Namespace：默认是空串，公共命名空间 public
    2. Group：默认 DEFAULT_GROUP
    3. Service / Data ID
---
## [配置中心](https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/zh-cn/index.html#_spring_cloud_alibaba_nacos_config)
1. 依赖
    ```xml
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
    </dependency>
    ```
2. Console
    1. 新命名空间：命名空间 → 新建命名空间 → dev:开发环境
    2. 新建配置：配置管理 → 配置列表 → 选择命名空间 → 创建配置 → 发布
        - Data ID: nacos_config
        - Group: GROUP_1
        - 配置内容
            ```properties
            ljh.name=ljh
            ljh.age=21
            ```
3. [bootstrap.properties](nacos-config/src/main/resources/bootstrap.properties)
    - 单配置集
        ```properties
        spring.cloud.nacos.config.server-addr=127.0.0.1:8848
        spring.cloud.nacos.config.group=GROUP_1
        spring.cloud.nacos.config.name=nacos_config
        spring.cloud.nacos.config.file-extension=properties
        spring.cloud.nacos.config.refresh-enabled=true
        ```
    - 多配置集
        ```properties
        spring.cloud.nacos.config.server-addr=127.0.0.1:8848,127.0.0.1:8849,127.0.0.1:8850
        # 命名空间 ID
        spring.cloud.nacos.config.namespace=89dc3a33-a0d7-4970-8589-7e37a6e3302d
        spring.cloud.nacos.config.extension-configs[0].data-id=mysql_common
        spring.cloud.nacos.config.extension-configs[0].group=DEFAULT_GROUP
        spring.cloud.nacos.config.extension-configs[0].refresh=true
        spring.cloud.nacos.config.extension-configs[2].data-id=crm_config
        spring.cloud.nacos.config.extension-configs[2].group=CRM
        spring.cloud.nacos.config.extension-configs[2].refresh=true
        ```
4. 使用
    ```java
    public class Controller {
        @Value("${ljh.name}")
        private String name;
    }
    ```
---
## [服务注册与发现](https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/zh-cn/index.html#_spring_cloud_alibaba_nacos_discovery)
- 服务实体关系模型  
<img alt="Service Entity Relationship Model" src="https://cdn.nlark.com/yuque/0/2019/jpeg/338441/1561217924697-ba504a35-129f-4fc6-b0df-1130b995375a.jpeg" width="600">
1. 依赖
    ```xml
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    </dependency>
    ```
2. `@EnableDiscoveryClient`
3. application.properties
    ```properties
    spring.application.name=order-service
    spring.cloud.nacos.discovery.server-addr=127.0.0.1:8848,127.0.0.1:8849,127.0.0.1:8850
    spring.cloud.nacos.discovery.namespace=89dc3a33-a0d7-4970-8589-7e37a6e3302d
    ```
    ```properties
    spring.application.name=stock-service
    spring.cloud.nacos.discovery.server-addr=127.0.0.1:8848,127.0.0.1:8849,127.0.0.1:8850
    spring.cloud.nacos.discovery.namespace=89dc3a33-a0d7-4970-8589-7e37a6e3302d
    spring.cloud.nacos.discovery.cluster-name=stock
    ```
4. Feign
    - `@EnableFeignClients("com.ljh.feign")`
    - com.ljh.feign.FeignService
        ```java
        @FeignClient("stock-service")
        public interface FeignService {
            @GetMapping("/stock/test")
            String test(@RequestParam("info") String info);
        }
        ```
---
## [概念](https://nacos.io/zh-cn/docs/concepts.html)
| 英                        | 中      | 说明                                                                  |
|:-------------------------|:-------|:--------------------------------------------------------------------|
| Region                   | 区域     | 物理数据中心，资源创建后不能更改                                                    |
| Available Zone           | 可用区    ||
| Endpoint                 | 端点     |                                                                     |
| Namespace                | 命名空间   | 隔离租户配置。常见场景：区分开发、测试、生产环境                                            |
| Configuration            | 配置     ||
| Configuration Management | 配置管理   | 编辑、存储、分发、修改管理、发布版本管理、修改审计                                           |
| Configuration Item       | 配置项    ||
| Configuration Set        | 配置集    ||
| Data ID                  | 数据 ID  | 配置集 ID，通常使用 Java 包的命名规则来确保全局唯一                                      |
| Group                    | 分组     | 一组配置，名称默认 DEFAULT_GROUP                                             |
| Configuration Snapshot   | 配置快照   ||
| Service                  | 服务     | 一些预定义的接口，通过网络访问，提供给客户端的软件功能                                         |
| Service Name             | 服务名    | 服务标识符                                                               |
| Service Registry         | 服务注册中心 | 服务实例数据库，服务负载均衡策略                                                    |
| Service Discovery        |        |                                                                     |
| Metadata                 | 元数据    | 自定义配置信息，如：容灾策略、负载均衡策略、认证配置、标签等；<br/>从作用范围来看，分为服务级别元信息、虚拟集群元信息、实例元信息 |
| Naming Service           | 命名服务   | 将分布式系统中所有对象和实体的"names"映射到相关的元数据；<br/>两大场景：服务发现、DNS                  |
| Configuration Service    | 配置服务   | 为其它服务喝应用提供动态配置、服务元数据、配置管理                                           |
| Application              | 应用     ||
| Service Group            | 服务分组   ||
| Virtual Cluster          | 虚拟集群   ||
| Instance                 | 实例     ||
| Weight                   | 权重     ||
| Health Check             | 健康检查   ||
| Protect Threshold        | 保护阈值   ||
---
