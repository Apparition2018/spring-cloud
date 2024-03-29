# Spring Cloud

---
## 参考网站
1. [Spring Cloud](https://spring.io/projects/spring-cloud)
2. [Spring Cloud | Github](https://github.com/spring-cloud)
3. [Spring Cloud | Baeldung](https://www.baeldung.com/category/spring/spring-cloud/)
4. [极速入门SpringCloud之API网关与服务发现](https://www.imooc.com/learn/1134)
---
## 问题
1. [微服务与 SOA 之间差了一个 ESB](https://blog.csdn.net/jdk2006/article/details/51695416)
2. [分布式与集群的区别是什么？](https://www.zhihu.com/question/20004877)
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
## Spring Cloud
- Spring Cloud 为开发者提供了在分布式系统中快速构建的工具，使用 Spring Cloud 的开发人员可以快速的启动服务或构建应用、同时能够快速和云平台资源进行对接
- 五大功能：
    1. 服务发现：Netflix Eureka
    2. 客户端负载均衡：Netflix Ribbon
    3. 熔断器：Netflix Hystrix
    4. 网关路由：Netflix Zuul
    5. 分布式配置中心：Spring Cloud Config
- 其它功能：
    - 链路监控中心
        - 监控：actuator，admin
        - 链路监控：sleuth，zipkin
    - 消息中心：stream，bus
    - 认证授权中心：security
    - 缓存，熔断，代理，控制总线，全局锁，leader 选举，分布式 session，集群状态，文档 ...
---
## Spring Cloud 启动流程分析
<img alt="Spring Cloud 启动流程分析" src="https://img1.mukewang.com/5fc6704800015dd319201080.jpg" width="600"/>

1. SpringApplication 初始化
2. Initializer: 设置 Spring 所有 Initializer，搜索 jar 包下面所有的 META-INF/spring.factories 并加载 Initializer
3. prepareContext: 将 environment 和 context 关联
4. refreshContext:
    - postProcessBeanFactory: 加载 auto configure 的地方
5. afterRefresh:
---
## 网关路由
- 应用场景：认证登录，授权，限流，日志，监控，减少客户端与服务的交互次数
- 使用方案：nginx(KONG, API Umbrella)、Zuul、Spring Cloud gateway、Linkerd
- Spring Cloud 网关路由
    1. spring-cloud-starter-netflix-zuul: zuul 1，同步阻塞网关
        - cpu密集型任务
        - 简单操作的需求
        - 开发简单的需求
        - 实时请求高的
    2. spring-cloud-starter-gateway: zuul 2，异步非阻塞网关
        - io密集的任务
        - 大请求或者大文件
        - 队列的流式数据
        - 超大量的连接
---
## 服务注册与发现
- 作用：提供服务注册、目录和查找三大关键特性，和提供健康监控、多种查询、实时更新和高可用性等
- 使用方案：eureka、consul、zookeeper、etcd、spotify、serf、apollo
- CAP 原则：指的是在一个分布式系统中，一致性(Consistency)、可用性(Availability)、分区容错性(Partition tolerance)，最多只能同时实现两点，不可能三者兼顾。
- Spring Cloud 服务发现
    1. spring-cloud-netflix-eureka (Java，侧重 Availability)
    2. spring-cloud-zookeeper (Java，侧重 Consistency)
    3. spring-cloud-consul (Go)
---