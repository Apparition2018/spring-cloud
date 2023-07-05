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
## [概念](https://nacos.io/zh-cn/docs/concepts.html)
| 英                        | 中      | 说明                                                  |
|:-------------------------|:-------|:----------------------------------------------------|
| Region                   | 地域     | 物理的数据中心，资源创建成功后不能更换。                                |
| Available Zone           | 可用区    | 同一地域内，电力和网络互相独立的物理区域。同一可用区内，实例的网络延迟较低。              |
| Endpoint                 | 接入点    | 地域的某个服务的入口域名。                                       |
| Namespace                | 命名空间   | 用于进行租户粒度的配置隔离。不同的命名空间下，可以存在相同的 Group 或 Data ID 的配置。 |
| Configuration            | 配置     |                                                     |
| Configuration Management | 配置管理   | 系统配置的编辑、存储、分发、变更管理、历史版本管理、变更审计等所有与配置相关的活动。          |
| Configuration Item       | 配置项    | 一个具体的可配置的参数与其值域，通常以 param-key=param-value 的形式存在。    |
| Configuration Set        | 配置集    | 一组相关或者不相关的配置项的集合称为配置集。                              |
| Data ID                  | 配置集 ID | 某个配置集的 ID。通常采用类 Java 包的命名规则保证全局唯一性。                 |
| Group                    | 配置分组   | 一组配置集。如果未填写配置分组的名称，则配置分组的名称默认采用 DEFAULT_GROUP 。     |
| Configuration Snapshot   | 配置快照   | Nacos 的客户端 SDK 会在本地生成配置的快照。                         |
| Service                  | 服务     | 通过预定义接口网络访问的提供给客户端的软件功能。                            |
| Service Name             | 服务名    | 服务提供的标识。                                            |
| Service Registry         | 服务注册中心 | 存储服务实例和服务负载均衡策略的数据库。                                |
| Service Discovery        | 服务发现   | 在计算机网络上，对服务下的实例的地址和元数据进行探测，并以预先定义的接口提供给客户端进行查询。     |
| Metadata                 | 元信息    | Nacos 数据描述信息。                                       |
| Application              | 应用     | 用于标识服务提供方的服务的属性。                                    |
| Service Group            | 服务分组   | 不同的服务可以归类到同一分组。                                     |
| Virtual Cluster          | 虚拟集群   | 同一个服务下的所有服务实例组成一个默认集群。                              |
| Instance                 | 实例     | 提供一个或多个服务的具有可访问网络地址的进程。                             |
| Weight                   | 权重     | 实例级别的配置。                                            |
| Health Check             | 健康检查   | 以指定方式检查服务下挂载的实例的健康度，从而确认该实例是否能提供服务。                 |
| Protect Threshold        | 健康保护阈值 | 一个 0 到 1 之间的浮点数。                                    |
---
## 数据模型
- Nacos 数据模型 Key 由三元组唯一确定
    1. Namespace：默认是空串，公共命名空间 public
    2. Group：默认 DEFAULT_GROUP
    3. Service / Data ID
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
## [配置中心](https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/zh-cn/index.html#_spring_cloud_alibaba_nacos_config)
1. 依赖
    ```xml
    <dependencies>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bootstrap</artifactId>
        </dependency>
    </dependencies>
    ```
2. Console：配置管理 → 配置列表 → 选择命名空间 → 创建配置 → 发布
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
        # Nacos Spring Cloud 中，dataId 的完整格式：${prefix}-${spring.profiles.active}.${file-extension}
        #   prefix 默认为 spring.application.name 的值，也可以通过配置项 spring.cloud.nacos.config.prefix 来配置
        #   spring.profiles.active 即为当前环境对应的 profile
        spring.cloud.nacos.config.server-addr=127.0.0.1:8848,127.0.0.1:8849,127.0.0.1:8850
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
    @RefreshScope
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