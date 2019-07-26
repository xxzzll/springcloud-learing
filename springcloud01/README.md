# springcloud 微服务架构
## 1、架构的演变
![阶段1_单机集中构建网站.jpg](/home/xixi/IdeaProjects/springcloud01/images/阶段1_单机集中构建网站.jpg)
![阶段2_0_应用服务器配置集群.jpg](/home/xixi/IdeaProjects/springcloud01/images/阶段2_应用服务器配置集群.jpg)
![阶段2_1_nginx+应用服务器配置集群.jpg](/home/xixi/IdeaProjects/springcloud01/images/阶段2_nginx+应用服务器配置集群.jpg)
![阶段2_2_nginx+应用服务器配置集群+HA.jpg](/home/xixi/IdeaProjects/springcloud01/images/阶段2_nginx+应用服务器配置集群+HA.jpg)
![阶段3_负载均衡服务配置集群.jpg](/home/xixi/IdeaProjects/springcloud01/images/阶段3_负载均衡服务配置集群.jpg)
![阶段4_CDN+Varnish服务配置集群.jpg](/home/xixi/IdeaProjects/springcloud01/images/阶段4_CDN+Varnish服务配置集群.jpg)
![阶段5_数据库读写分离.jpg](/home/xixi/IdeaProjects/springcloud01/images/阶段5_数据库读写分离.jpg)
![阶段6_NOSQL+分布式搜索引擎.jpg](/home/xixi/IdeaProjects/springcloud01/images/阶段6_NOSQL+分布式搜索引擎.jpg)
![阶段7_NOSQL(HA)+分库分表+MyCat.jpg](/home/xixi/IdeaProjects/springcloud01/images/阶段7_NOSQL(HA)+分库分表+MyCat.jpg)
![阶段8_分布式文件系统.jpg](/home/xixi/IdeaProjects/springcloud01/images/阶段8_分布式文件系统.jpg)
![京东2017618交易平台目前的架构体系.jpg](/home/xixi/IdeaProjects/springcloud01/images/京东2017618交易平台目前的架构体系.jpg)
![阶段9_应用服务化拆分+消息中间件.jpg](/home/xixi/IdeaProjects/springcloud01/images/阶段9_应用服务化拆分+消息中间件.jpg)
![阶段10_微服务架构.jpg](/home/xixi/IdeaProjects/springcloud01/images/阶段10_微服务架构.jpg)
## 2、内容技术梳理与架构
    2.1、整套开发技术栈以SpringCloud为主，单个微服务模块以SpringMVC+SpringBoot/Spring+MyBatis组合进行开发
    2.2、前端层，页面H5+thymeleaf/样式CSS3+Bootstrap/前端框架JQuery+Node|Vue等
    2.3、负载层，前端访问通过Http或Https协议到达服务端的LB，可以是F5等硬件做负载均衡，还可以自行部署LVS+Keepalived等（前期量小可以直接使用Nginx） 
    2.4、网关层，请求通过LB后，会到达整个微服务体系的网关层Zuul（Gateway），内嵌Ribbon做客户端负载均衡，Hystrix做熔断降级等
    2.5、服务注册，采用Eureka来做服务治理，Zuul会从Eureka集群获取已发布的微服务访问地址，然后根据配置把请求代理到相应的微服务去
    2.6、docker容器，所有的微服务模块都部署在Docker容器里面，而且前后端的服务完全分开，各自独立部署后前端微服务调用后端微服务，后端微服务之间会有相互调用
    2.7、服务调用，微服务模块间调用都采用标准的Http/Https+REST+JSON的方式，调用技术采用Feign+HttpClient+Ribbon+Hystrix
    2.8、统一配置，每个微服务模块会跟Eureka集群、配置中心（SpringCloudConfig）等进行交互
    2.9、第3方框架，每个微服务模块根据实现的需要，通常还需要使用一些第三发框架，比如常见的有：缓存服务（Redis）、图片服务（FastDFS）、搜索服务（ElasticSearch）、安全管理（Shiro）等等
    2.10、Mysql数据库，可以按照微服务模块进行拆分，统一访问公共库或者单独自己库，可以单独构建MySQL集群或者分库分表MyCat等
    