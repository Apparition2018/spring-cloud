# Spring Cloud

---
## 问题
1. [微服务与 SOA 之间差了一个ESB](https://blog.csdn.net/jdk2006/article/details/51695416)
---
## 微服务架构的发展历史
1. 巨石应用：Monolith Application
    - 一个统一的数据库，一个统一的应用
2. 面向服务架构：Service-Oriented Architecture (SOA)
    - 共同的治理和标准
    - 专注于业务功能重用
    - 通信使用企业服务总线 ESB
3. 微服务架构：Microservice Architecture
    - 模块化
    - 独立部署
    - 异构化
4. 服务网格：Service Mesh
---
## Spring Cloud 生态
1. 服务注册与发现
    1. eureka
    2. consul
    3. zookeeper
2. 网关路由
    1. zuul 1.x
    2. zuul 2.x
    3. gateway
3. 服务调用
    1. Ribbon
    2. Feign
    3. Hystrix
4. 链路监控中心
    - 监控
        1. actuator
        2. admin
    - 链路监控
        1. sleuth
        2. zipkin
5. 消息中心
    1. stream
    2. bus
6. 配置中心
    1. git
    2. svn
    3. zookeeper
7. 认证授权中心
    1. Security
    2. oauth
    3. jwt
8. 缓存中心
9. 文档中心：swagger
---
## API 网关路由
- 使用场景：认证登录，授权，限流，日志，监控，减少客户端与服务的交互次数
- 使用方案：
    1. nginx: KONG，API Umbrella
    2. Zuul
    3. Spring Cloud gateway
    4. Linkerd
    5. 自研
- Spring Cloud 网关路由
    1. spring-cloud-starter-netflix-zuul: 同步阻塞网关
        - 1.cpu密集型任务
        - 2.简单操作的需求
        - 3.开发简单的需求
        - 4.实时请求高的
    2. spring-cloud-starter-gateway: 异步非阻塞网关
        - 1.io密集的任务
        - 2.大请求或者大文件
        - 3.队列的流式数据
        - 4.超大量的连接
---
## 服务发现
- 作用：提供服务注册、目录和查找三大关键特性，和提供健康监控、多种查询、实时更新和高可用性等
- 使用方案：
    1. eureka
    2. consul
    3. zookeeper
    4. etcd
    5. spotify
    6. serf
    7. appollo
- Spring Cloud 服务发现
    1. spring-cloud-starter-netflix-eureka-server，spring-cloud-starter-netflix-eureka-client (Java)
    2. spring-cloud-starter-zookeeper-discovery (Java)
        1. 启动 zookeeper 
            - 下载 zookeeper
            - 在 config/复制 zoo_sample.cfg 并重命名为 zoo.cfg
                1. 修改 dataDir=D:\\git\\spring-cloud\\spring-cloud-discovery\\spring-cloud-zookeeper\\apache-zookeeper-3.6.2\\data
                2. 添加 dataLogDir=D:\\git\\spring-cloud\\spring-cloud-discovery\\spring-cloud-zookeeper\\apache-zookeeper-3.6.2\\log
                3. 添加 audit.enable=true
            - 双击 bin/zkServer.cmd 启动
        2. zookeeper 图形化界面 ZooInspector
            - 下载 ZooInspector
            - 在 bin 下 cmd 输入 java -jar -Dfile.encoding-UTF-8 zookeeper-dev-ZooInspector.jar
    3. spring-cloud-starter-consul-discovery (Go)
        - 下载 consul.exe，在 consul.exe 所在位置打开 cmd 输入启动命令 consul agent -dev
        - 网址输入 localhost:8500 打开 consul 客户端