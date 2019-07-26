# SpringCloud

## 一、没有引入eureka之前的微服务搭建

### 1、父子microservicecloud2019工程pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.huawei.springcloud</groupId>
    <artifactId>microservicecloud2019</artifactId>
    <version>1.0-SNAPSHOT</version>
    <modules>
        <module>microservicecloud-api</module>
        <module>microservicecloud-provider-dept-8001</module>
        <module>microservicecloud-consumer-dept-80</module>
    </modules>
    <packaging>pom</packaging>


    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <junit.version>4.12</junit.version>
        <log4j.version>1.2.17</log4j.version>
        <lombok.version>1.16.18</lombok.version>
    </properties>
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Dalston.SR1</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>1.5.9.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>5.1.46</version>
            </dependency>
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>1.0.31</version>
            </dependency>
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>1.3.0</version>
            </dependency>
            <dependency>
                <groupId>ch.qos.logback</groupId>
                <artifactId>logback-core</artifactId>
                <version>1.2.3</version>
            </dependency>
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>${junit.version}</version>
                <scope>test</scope>
            </dependency>
            <dependency>
                <groupId>log4j</groupId>
                <artifactId>log4j</artifactId>
                <version>${log4j.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>
```

### 2、microservicecloud-api存储本地jar

- 使用lombok注解

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>microservicecloud2019</artifactId>
        <groupId>com.huawei.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>microservicecloud-api</artifactId>

    <dependencies>
        <!-- 当前Module需要用到的jar包，按自己需求添加，如果父类已经包含了，可以不用写版本号 -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
    </dependencies>

</project>
```

### 3、microservicecloud-provider-dept-8001，服务提供者

#### 3.1、pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>microservicecloud2019</artifactId>
        <groupId>com.huawei.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>microservicecloud-provider-dept-8001</artifactId>
    <description>部门服务提供者</description>

    <dependencies>
        <dependency>
            <!-- 引入自己定义的api通用包，可以使用Dept部门Entity -->
            <groupId>com.huawei.springcloud</groupId>
            <artifactId>microservicecloud-api</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jetty</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>   <!-- 修改后立即生效，热部署 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>springloaded</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>

    </dependencies>

</project>
```

#### 3.2、application.yml

```yaml
server:
  port: 8001
mybatis:
  config-location: classpath:mybatis/mybatis.cfg.xml        # mybatis配置文件所在路径
  type-aliases-package: com.huawei.springcloud.entities     # 所有Entity别名类所在包
  mapper-locations:
    - classpath:mybatis/mapper/**/*.xml                     # mapper映射文件
spring:
  application:
    name: microservicecloud-dept
  datasource:
      type: com.alibaba.druid.pool.DruidDataSource          # 当前数据源操作类型
      driver-class-name: org.gjt.mm.mysql.Driver            # mysql驱动包
      url:  jdbc:mysql://localhost:3306/cloudDB01           # 数据库名称
      username: root
      password: Lzx@123456
      dbcp2:
        min-idle: 5                             # 数据库连接池的最小维持连接数
        initial-size: 5                         # 初始化连接数
        max-total: 5                            # 最大连接数
        max-wait-millis: 200                    # 等待连接获取的最大超时时间
```

#### 3.3、主启动类DeptProvider8001_App

```java
/**
 * FileName: DeptProvider8001_App
 * Author:   xixi
 * Date:     19-7-24 下午1:54
 * Description: 部门8001微服务主启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huawei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *  <br>
 * 〈部门8001微服务主启动类〉
 *
 * @author xixi
 * @create 19-7-24
 * @since 1.0.0
 */
@SpringBootApplication
public class DeptProvider8001_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8001_App.class, args);
    }
}
```

#### 3.4、Rest风格的控制器DeptController

```java
/**
 * FileName: DeptController
 * Author:   xixi
 * Date:     19-7-24 下午1:49
 * Description: 部门控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huawei.springcloud.controller;

import com.huawei.springcloud.entities.Dept;
import com.huawei.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  <br>
 * 〈部门控制器〉
 *
 * @author xixi
 * @create 19-7-24
 * @since 1.0.0
 */
@RestController // 前后端分离，restful风格
public class DeptController {

    @Autowired
    private DeptService deptService;


    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list(){
        return deptService.list();
    }

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id){
        return deptService.get(id);
    }

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept){
        return deptService.add(dept);
    }
}
```

### 4、microservicecloud-consumer-dept-80，服务消费者

#### 4.1、pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>microservicecloud2019</artifactId>
        <groupId>com.huawei.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>microservicecloud-consumer-dept-80</artifactId>
    <description>部门微服务消费者</description>

    <dependencies>
        <dependency>
            <!-- 自己定义的api -->
            <groupId>com.huawei.springcloud</groupId>
            <artifactId>microservicecloud-api</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- 修改后立即生效，热部署 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>springloaded</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
    </dependencies>

</project>
```

#### 4.2、application.yml

```yaml
server:
  port: 1025 # linux 非root用户不能使用1024端口之内
```

#### 4.3、springboot对application.xml抽象的配置类

```java
/**
 * FileName: ConfigBean
 * Author:   xixi
 * Date:     19-7-24 下午4:09
 * Description: springboot对spring的配置文件applicationContext.xml的类定义
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huawei.springcloud.cfgbeans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *  <br>
 * 〈springboot对spring的配置文件applicationContext.xml的类定义〉
 *
 * @author xixi
 * @create 19-7-24
 * @since 1.0.0
 */
@Configuration
public class ConfigBean {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

```

#### 4.4、控制器DeptController

```java
/**
 * FileName: DeptController
 * Author:   xixi
 * Date:     19-7-24 下午4:11
 * Description: 消费端部门控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huawei.springcloud.controller;

import com.huawei.springcloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * <br>
 * 〈消费端部门控制器〉
 *
 * @author xixi
 * @create 19-7-24
 * @since 1.0.0
 */
@RestController
public class DeptController {

    private static final String REST_URL_PREFIX = "http://localhost:8001";

    /**
     * 使用
     * 使用RestTemplate访问restful接口非常简单粗暴无闹
     * (url, requestMap, ResponseBean.class) 这三个参数分别代表
     * Rest请求地址、请请求参数、HTTP响应转换被转换成的对象类型;
     */
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/dept/add")
    public boolean add(Dept dept) {
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
    }

    @RequestMapping(value = "/consumer/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
    }

    @RequestMapping(value = "/consumer/dept/list", method = RequestMethod.GET)
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }
}
```

#### 4.5、主启动类DeptConsumer80_App

```java
/**
 * FileName: DeptConsumer80_App
 * Author:   xixi
 * Date:     19-7-24 下午4:19
 * Description: 部门消费端主启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huawei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  <br>
 * 〈部门消费端主启动类〉
 *
 * @author xixi
 * @create 19-7-24
 * @since 1.0.0
 */
@SpringBootApplication
public class DeptConsumer80_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_App.class, args);
    }
}
```

### 5、效果

```reStructuredText
访问消费端路径:
	http://localhost:1025/customer/emp/list
	http://localhost:1025/customer/emp/get/1
	http://localhost:1025/customer/emp/add?dname=test
都可以通过RestTemplate的HTPP请求，访问到服务提供方提供的服务;	
```

## 二、引入eureka服务注册中心

![eureka架构图](/home/xixi/Documents/markdown-workspace/springcloud_images/eureka注册中心.png)

### 1、创建eureka服务注册中心服务端microservicecloud-eureka-7001

#### 1.1、pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>microservicecloud2019</artifactId>
        <groupId>com.huawei.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>microservicecloud-eureka-7001</artifactId>
    <description>eureka服务注册中心7001</description>

    <dependencies>
        <!--eureka-server服务端 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka-server</artifactId>
        </dependency>
        <!-- 修改后立即生效，热部署 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>springloaded</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
    </dependencies>

</project>
```

#### 1.2、application.yml

```yaml
server:
  port: 7001
eureka:
  instance:
    hostname: localhost #eureka服务端的实例名称
  client:
    register-with-eureka: false #false表示不向注册中心注册自己。
    fetch-registry: false #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/        #设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个地址。
```

#### 1.3、主启动类EurekaServer7001_App，启动服务注册功能

```java
/**
 * FileName: EurekaServer7001_App
 * Author:   xixi
 * Date:     19-7-24 下午5:12
 * Description: eureka注册中心主启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huawei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *  <br>
 * 〈eureka注册中心主启动类〉
 *
 * @author xixi
 * @create 19-7-24
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaServer // 启动eureka服务注册中心
public class EurekaServer7001_App {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer7001_App.class, args);
    }
}
```

#### 1.4、修改服务提供端microservicecloud-provider-dept-8001

##### 1.4.1、附加application.yml，eureka功能配置

```yaml
## 服务提供方接入eureka注册中心，对外提供服务
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka
  instance:
    instance-id: microservicecloud-dept8001 # 服务名称修改
    prefer-ip-address: true #访问ip信息显示
```

##### 1.4.2、修改主启动类，添加Eureka的@EnableEurekaClient注解

```java
@SpringBootApplication
@EnableEurekaClient // 本服务启动后会自动注册到eureka服务注册中心
public class DeptProvider8001_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8001_App.class, args);
    }
}
```

## 三、actuator与注册微服务信息完善

### 1、超链接点击服务报告ErrorPage

- 在服务提供方microservicecloud-provider-dept-8001，pom添加actuator依赖

```xml
<!-- actuator与注册微服务信息完善 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

### 2、微服务info内容详细信息

- 总的父工程microservicecloud2019修改pom.xml添加构建build信息

```xml
<build>
     <finalName>microservicecloud</finalName>
     <resources>
         <resource>
             <directory>src/main/resources</directory>
             <filtering>true</filtering>
         </resource>
     </resources>
     <plugins>
         <plugin>
             <groupId>org.apache.maven.plugins</groupId>
             <artifactId>maven-resources-plugin</artifactId>
             <configuration>
                 <delimiters>
                     <delimit>$</delimit>
                 </delimiters>
             </configuration>
         </plugin>
     </plugins>
</build>
```

### 3、服务名称鼠标放在上面可见IP信息

- 修改microservicecloud-provider-dept-8001，application.yml附加

```yaml
## 服务提供方接入eureka注册中心，对外提供服务
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka
  instance:
    instance-id: microservicecloud-dept8001 # 服务名称修改
    prefer-ip-address: true #访问ip信息显示
```

## 四、EUREKA自我保护机制

![1563964602984](/home/xixi/.config/Typora/typora-user-images/1563964602984.png)



## 五、microservicecloud-provider-dept-8001服务发现Discovery

### 1、在服务控制器上添加查询本服务发现的方法

```java
@RestController
public class DeptController {
	@Autowired
    private DiscoveryClient client;

	@RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
    public Object discovery() {
        List<String> list = client.getServices();
        System.out.println("**********" + list);
        List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance element : srvList) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri());
        }
        return this.client;
    }
    
 	// ...
    
}    
```

### 2、修改主启动类，添加@EnableDiscoveryClient注解

```java
@SpringBootApplication
@EnableEurekaClient // 本服务启动后会自动注册到eureka服务注册中心
@EnableDiscoveryClient // 启动服务发现
public class DeptProvider8001_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8001_App.class, args);
    }
}
```

### 3、访问测试

```reStructuredText
http://localhost:8001//dept/discovery
--结果：
{
"services": [
"microservicecloud-dept"
],
"localServiceInstance": {
"serviceId": "microservicecloud-dept",
"metadata": {},
"uri": "http://192.168.0.112:8001",
"secure": false,
"host": "192.168.0.112",
"port": 8001
}
}
```

## 六、EUREKA集群

## 七、eureka与zookeeper区别

### CAP理论

![CAP理论](/home/xixi/Documents/markdown-workspace/springcloud_images/CAP理论.png)

### 1、eureka遵守AP和zookeeper遵守CP

### 2、eureka各个节点都是平等的;zookeeper主从

## 八、Ribbon客户端负载均衡

### 1、客户端microservicecloud-consumer-dept-80引入ribbon

```xml
 <!-- Ribbon相关 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-ribbon</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
```

### 2、标记RestTemplate为负载均衡的客户端

```java
@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced // 把RestTemplate标记负载均衡客户端
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
```

### 3、修改主启动类

```java
@SpringBootApplication
@EnableEurekaClient // 在客户端订阅eureka注册中心提供的服务
public class DeptConsumer80_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_App.class, args);
    }
}
```

#### 4、修改application.yaml

```yaml
#  追加订阅eureka服务注册地址
eureka:
  client:
    register-with-eureka: false
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
```

### 5、总结：

​	Ribbon和Eureka整合后Consumer可以直接调用服务而不用再关心地址和端口号

### 6、Ribbon核心组建IRule

#### 6.1、RoundRobinRule轮询

#### 6.2、RandomRule随机

#### 6.3、RetryRule先按照轮询的策略获取服务，如果获取服务失败则在指定时间内进行重试，获取可用的服务

### 7、Ribbon自定义

#### 7.1、修改microservicecloud-consumer-dept-80

- 主启动类添加@RibbonClient

  ```java
  @SpringBootApplication
  @EnableEurekaClient // 在客户端订阅eureka注册中心提供的服务
  //  在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置生效
  @RibbonClient(name = "MICROSERVICECLOUD-DEPT", configuration = MyRuleConfig.class)
  public class DeptConsumer80_App {
      public static void main(String[] args) {
          SpringApplication.run(DeptConsumer80_App.class, args);
      }
  }
  ```

  

- 添加Ribbon负载均衡配置规则

  ```java
  /**
   *  <br>
   * 〈自定义Ribbon负载均衡算法配置在主启动类启动时被加载〉
   *	官方文档明确给出了警告：
   *   这个自定义配置类不能放在@ComponentScan所扫描的当前包下以及子包下，
   *   否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享，也就是说我们达不到特殊化定制的目的了。  
   * @author xixi
   * @create 19-7-25
   * @since 1.0.0
   */
  @Configuration
  public class MyRuleConfig {
  
      @Bean
      public IRule getIRule(){
          return new RandomRule(); // Ribbon默认为轮询，我自定义随机算法
      }
  }
  
  ```

  

- 自定义配置类不能放在@ComponentScan所扫描的当前包下以及子包下，否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享;

## 九、Feign负载均衡

### 1、Feign简介

> [Feign](https://github.com/Netflix/feign) is a declarative web service client. It makes writing web service clients easier. To use Feign create an interface and annotate it

### 2、Feign负载均衡的实现

#### 2.1、新建microservicecloud-consumer-dept-feign项目

#### 2.2、复制microservicecloud-consumer-dept-80pom中依赖并添加feign依赖

```xml
<!-- feign 相关支持-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-feign</artifactId>
</dependency>
```

#### 2.3、定义客户端可直接调用的接口DeptClientService

```java
/**
 *  <br>
 * 〈定义部门服务接口〉
 *  在Feign的实现下，我们只需创建一个接口并使用注解的方式来配置它(以前是Dao接口上面标注Mapper注解,现在是一个微服务接口上面标注一个Feign注解即可)，即可完成对服务提供方的接口绑定，简化了使用Spring cloud Ribbon时，自动封装服务调用客户端的开发量。
 *  Feign集成了Ribbon利用Ribbon维护了MicroServiceCloud-Dept的服务列表信息，并且通过轮询实现了客户端的负载均衡。
 *  而与Ribbon不同的是，通过feign只需要定义服务绑定接口且以声明式的方法，优雅而简单的实现了服务调用
 * @author xixi
 * @create 19-7-25
 * @since 1.0.0
 */
@FeignClient(value = "MICROSERVICECLOUD-DEPT")
public interface DeptClientService {

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") long id);

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list();

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(Dept dept);
}
```

#### 2.4、修改主启动类

- 把以上接口路径作为Feign扫描的基本路径添加的主启动配置中

```java
@SpringBootApplication
@EnableEurekaClient // 在客户端订阅eureka注册中心提供的服务
@EnableFeignClients(basePackages = "com.huawei.springcloud")
@ComponentScan("com.huawei.springcloud") // 扫描@Configuration注解的类路径
public class DeptConsumer80_Feign_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_Feign_App.class, args);
    }
}
```

### 3、总结

Feign通过接口的方法调用Rest服务（之前是Ribbon+RestTemplate），该请求发送给Eureka服务器（http://MICROSERVICECLOUD-DEPT/dept/list）,通过Feign直接找到服务接口，由于在进行服务调用的时候融合了Ribbon技术，所以也支持负载均衡作用。     

## 十、Hystrix断路器

### 1、服务容断

> ​	一般是某个服务故障或者异常引起，类似现实世界中的"保险丝"，但某个异常条件被触发时，直接容断整个服务，而不是一直等待此服务超时;

#### 1.1、新建同microservicecloud-provider-dept-8001相同名称为microservicecloud-provider-dept-hystrix-8001的模块

#### 1.2、复制pom并添加与Hystrix相关依赖

```xml
<!-- hystrix 相关的-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-hystrix</artifactId>
</dependency>
```

#### 1.3、修改控制器

- 添加对异常的处理

```java
@RestController // 前后端分离，restful风格
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "processHystrix_GET")
    public Dept get(@PathVariable("id") Long id){
        Dept dept = deptService.get(id);
        if(dept == null){
            throw new RuntimeException("该ID：" + id + "没有对应的部门信息！");
        }
        return dept;
    }
	// Hystrix对服务异常的处理
    public Dept processHystrix_GET(@PathVariable("id") Long id){
        return new Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,null--@HystrixCommand").setDb_source("no this database in MySQL");
    }
}
```

#### 1.4、修改主启动类

- 在启动类中添加启用断路器注解

```java
@SpringBootApplication
@EnableEurekaClient // 本服务启动后会自动注册到eureka服务注册中心
@EnableDiscoveryClient // 启动服务发现
@EnableCircuitBreaker // 启动断路器
public class DeptProvider8001_Hystrix_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8001_Hystrix_App.class, args);
    }
}
```

### 2、服务降级

> 所谓降级，一般是从整体负荷考虑。就是当某个服务容断之后，服务器将不再被调用，此时客户端可以自己准备一个本地的fallback回调，返回一个缺省值;
>
> 这样做，虽然服务水平下降，但好歹可用，比直接挂掉要强;

整体资源快不够了，忍痛将某些服务先关掉，待渡过难关，再开启回来。

服务降级处理是在客户端实现完成的，与服务端没有关系

#### 2.1、添加一个实现FallbackFactory<T>接口的类DeptClientServiceFallbackFactory

```java

/**
 *  <br>
 * 〈实现FallbackFactory接口的类〉
 *  AOP实现：异常通知
 * @author xixi
 * @create 19-7-25
 * @since 1.0.0
 */
@Component//不要忘记添加，不要忘记添加
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {

    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public Dept get(long id) {
                return new Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭").setDb_source("no this database in MySQL");
            }

            @Override
            public List<Dept> list() {
                return null;
            }

            @Override
            public boolean add(Dept dept) {
                return false;
            }
        };
    }

}
```

#### 2.2、在microservicecloud-consumer-dept-feign客户端application.yml附加对服务降级的配置

```yaml
## 添加服务降级配置!!!
feign:
  hystrix:
    enabled: true
```

#### 2.3、效果

​	在服务端发生容断后，再次有客户访问该服务时，就会响应fallback缺省值信息;

## 3、容断器面板hystrix-dashboard

#### 3.1、新建模块microservicecloud-provider-dept-hystrix-dashboard

#### 3.2、pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>microservicecloud2019</artifactId>
        <groupId>com.huawei.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>microservicecloud-provider-dept-hystrix-dashboard</artifactId>
    <description>断路器监控面板</description>
    <dependencies>
        <dependency>
            <!-- 引入自己定义的api通用包，可以使用Dept部门Entity -->
            <groupId>com.huawei.springcloud</groupId>
            <artifactId>microservicecloud-api</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- 修改后立即生效，热部署 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>springloaded</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>

        <!-- hystrix 相关的-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
            <version>2.0.0.RELEASE</version>
        </dependency>
    </dependencies>

</project>
```

#### 3.3、application.yml

```yaml
server:
  port: 9001
```

#### 3.4、主启动类DeptConsumer_DashBoard_App

```java
/**
 *  <br>
 * 〈主启动类〉
 *
 * @author xixi
 * @create 19-7-25
 * @since 1.0.0
 */
@SpringBootApplication
@EnableHystrixDashboard
public class DeptConsumer_DashBoard_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_DashBoard_App.class, args);
    }
}
```

#### 3.5、监控

- 启动microservicecloud-provider-dept-hystrix-8001服务

- 启动microservicecloud-provider-dept-hystrix-dashboard监控

- 访问服务http://localhost:8001/get/1

- 访问http://localhost:8001/hystrix.stream

- 配置dashboard

  ![1564031872205](/home/xixi/.config/Typora/typora-user-images/1564031872205.png)

- 启动监控后的页面如下

  ![1564031958017](/home/xixi/.config/Typora/typora-user-images/1564031958017.png)

## 十一、Zuul路由

### 1、配置Zuul模块

#### 1.1、新建模块microservicecloud-zuul-gateway-9527

#### 1.2、pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>microservicecloud2019</artifactId>
        <groupId>com.huawei.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>microservicecloud-zuul-gateway-9527</artifactId>
    <description>网关zuul：提供=代理+路由+过滤三大功能</description>

    <dependencies>
        <!-- zuul路由网关 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zuul</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka</artifactId>
        </dependency>
        <!-- actuator监控 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--  hystrix容错-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        <!-- 日常标配 -->
        <dependency>
            <groupId>com.huawei.springcloud</groupId>
            <artifactId>microservicecloud-api</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jetty</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>   <!-- 热部署插件 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>springloaded</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
    </dependencies>

</project>
```

#### 1.3、application.yml

```yaml
server:
  port: 9527
spring:
  application:
    name: microservicecloud-zuul-gateway
eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka,http://eureka7003.com:7003/eureka
      instance:
        instance-id: gateway-9527.com
        prefer-ip-address: true
info:
  app.name: xixi-microcloud
  company.name: www.xixi.com
  build.artifactId: $project.artifactId$
  build.version: $project.version$

```

#### 1.4、主启动类

```java
@SpringBootApplication
@EnableZuulProxy
public class Zuul_9527_StartSpringCloudApp {
    public static void main(String[] args) {
        SpringApplication.run(Zuul_9527_StartSpringCloudApp.class, args);
    }
}
```

#### 1.5、添加hosts配置

```text
127.0.0.1		myzuul.com
```

#### 1.6、效果

```text
1.启动三个eureka集群
2.启动一个服务microservicecloud-provider-dept-8001
3.启动路由microservicecloud-zuul-gateway-9527
4.直接访问服务地址：http://localhost:8001/dept/get/2
5.通过路由访问服务地址：http://myzuul.com:9527/microservicecloud-dept/dept/get/2
```

### 2、zuul路由访问映射规则

- 配置规则

```yaml
zuul:
  routes:
    mydept.serviceId: microservicecloud-dept
    mydept.path: /mydept/**                   # 添加映射
  ignored-services:  "*"   # 屏蔽原服务名称的访问，单个指定具体microservicecloud-dept，多个使用"*"
  prefix: /xixi            # 添加访问服务的路由前缀
```

## 十二、Config服务端配置

### 1、config服务端配置

#### 1.1、新建模块microservicecloud-config-3344

#### 1.2、pom文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>microservicecloud2019</artifactId>
        <groupId>com.huawei.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>microservicecloud-config-3344</artifactId>
    <description>SpringCloud Config服务端配置</description>

    <dependencies>
        <!-- springCloud Config -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>
        <!-- 图形化监控 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!-- 熔断 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jetty</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>
        <!-- 热部署插件 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>springloaded</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
    </dependencies>
</project>
```

#### 1.3、application.yml配置

```yaml
server:
  port: 3344
spring:
  application:
    name: microservicecloud-config
  cloud:
    config:
      server:
        git:
          uri: git@github.com:xxzzll/springcloud-config.git #GitHub上面的git仓库名字
```

#### 1.4、主启动类

```java
/**
 *  <br>
 * 〈主启动类〉
 *
 * @author xixi
 * @create 19-7-25
 * @since 1.0.0
 */
@SpringBootApplication
@EnableConfigServer
public class Config_3344_StartSpringCloudApp {
    public static void main(String[] args) {
        SpringApplication.run(Config_3344_StartSpringCloudApp.class, args);
    }
}
```

#### 1.5、效果

```text
1.访问地址：
http://config-3344.com:3344/application-dev.yml
http://config-3344.com:3344/application-test.yml
http://config-3344.com:3344/application-xx.yml

http://config-3344.com:3344/application/dev/master
http://config-3344.com:3344/application/test/master
http://config-3344.com:3344/application/xx/master

http://config-3344.com:3344/master/application-dev.yml
http://config-3344.com:3344/master/application-test.yml
http://config-3344.com:3344/master/application-xx.yml

根据读取配置规则：
/{application}-{profile}.yml
/{application}/{profile}[/{label}]
/{label}/{application}-{profile}.yml
通过访问可以响应回来远程github上的config配置文件内容
```

```yaml
spring:
  application:
    name: microservicecloud-config-xixi-dev
  profiles:
    active:
    - dev
```

```
spring:
  application:
    name: microservicecloud-config-xixi-test
  profiles:
    active:
    - dev
```

### 2、config客户端配置

#### 2.1、向远程github仓库添加yml配置文件

- **microservicecloud-config-client.yml**

```yaml
spring:
  profiles:
    active:
      - dev
---
server:
  port: 8201
spring:
  profiles: dev
  application:
    name: microservicecloud-config-client
eureka:
  client:
    service-url:
      defaultZone: http://eureka-dev.com:7001/eureka/
---
server:
  port: 8202
spring:
  profiles: test
  application:
    name: microservicecloud-config-client
eureka:
  client:
    service-url:
      defaultZone: http://eureka-test.com:7001/eureka/
```

#### 2.2、新建模块microservicecloud-config-client-3355

#### 2.3、pom文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>microservicecloud2019</artifactId>
        <groupId>com.huawei.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>microservicecloud-config-client-3355</artifactId>
    <description>SpringCloud config client</description>
    
    <dependencies>
        <!-- springCloud Config client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config</artifactId>
        </dependency>
        <!-- 图形化监控 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!-- 熔断 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jetty</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>
        <!-- 热部署插件 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>springloaded</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
    </dependencies>
</project>
```

#### 2.4、添加bootstrap.yml和application.yml文件

> ​	applicaiton.yml是用户级的资源配置项bootstrap.yml是系统级的，优先级更加高;
>
> Spring Cloud会创建一个`Bootstrap Context`，作为Spring应用的`Application Context`的父上下文。
>
> 初始化的时候，`Bootstrap Context`负责从外部源加载配置属性并解析配置。
>
> 这两个上下文共享一个从外部获取的`Environment`。
>
> `Bootstrap`属性有高优先级，默认情况下，它们不会被本地配置覆盖。
>
> `Bootstrap context`和`Application Context`有着不同的约定，所以新增了一个`bootstrap.yml`文件，
>
> 保证`Bootstrap Context`和`Application Context`配置的分离。

- **bootstrap.yml**

  ```yaml
  spring:
    cloud:
      config:
        name: microservicecloud-config-client #需要从github上读取的资源名称，注意没有yml后缀名
        profile: dev   #本次访问的配置项
        label: master
        uri: http://config-3344.com:3344  #本微服务启动后先去找3344号服务，通过SpringCloudConfig获取GitHub的服务地址
  ```

- **application.yml**

  ```yaml
  spring:
    application:
      name: microservicecloud-config-client
  ```



#### 2.5、新建Rest风格的控制器类

```java
@RestController
public class ConfigClientRest {
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaServers;
    @Value("${server.port}")
    private String port;

    @RequestMapping("/config")
    public String getConfig() {
        String str = "applicationName: " + applicationName + "\t eurekaServers:" + eurekaServers + "\t port: " + port;
        System.out.println("******str: " + str);
        return "applicationName: " + applicationName + "\t eurekaServers:" + eurekaServers + "\t port: " + port;
    }
}
```

#### 2.6、新建主启动类

```java
/**
 *  <br>
 * 〈主启动类〉
 *
 * @author xixi
 * @create 19-7-25
 * @since 1.0.0
 */
@SpringBootApplication
public class ConfigClient_3355_StartSpringCloudApp {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClient_3355_StartSpringCloudApp.class, args);
    }
}
```

#### 2.7、效果

```text
1.访问地址：
	http://client-config.com:8201/config
	http://client-config.com:8202/config
	
2.当microservicecloud-config-client-3355模块的bootstrap.yml的profile=dev时，
访问http://client-config.com:8201/config可以带出信息如下：

applicationName: microservicecloud-config-client	eurekaServers:http://eureka-dev.com:7001/eureka/	port: 8201

3.当microservicecloud-config-client-3355模块的bootstrap.yml的profile=test时，
访问http://client-config.com:8202/config可以带出信息如下：

applicationName: microservicecloud-config-client	eurekaServers:http://eureka-test.com:7001/eureka/	port: 8202
```

## 十三、SpringCloud Config配置实战 

1）  Config服务端配置配置OK且测试通过，我们可以和config+GitHub进行配置修改并获得内容;

2） 此时我们做一个eureka服务+一个Dept访问的微服务，将两个微服务的配置统一由于github获得实现统一配置分布式管理，完成多环境的变更 ;

### 1、远程github仓库来获取各个微服务中的配置

#### 1.1、

- **microservicecloud-config-eureka-client.yml**

```yaml
spring:   
	profiles:     
		active:     
		- dev
---
server:   
	port: 7001 #注册中心占用7001端口,冒号后面必须要有空格   
spring:   
	profiles: dev  
	application:    
		name: microservicecloud-config-eureka-client    
eureka:   
	instance:     
		hostname: eureka7001.com #冒号后面必须要有空格  
		client:     
			register-with-eureka: false #当前的eureka-server自己不注册进服务列表中
			fetch-registry: false #不通过eureka获取注册信息    
			service-url:       
				defaultZone: http://eureka7001.com:7001/eureka/
---
server:   
	port: 7001 #注册中心占用7001端口,冒号后面必须要有空格   
spring:   
	profiles: test  
	application:    
		name: microservicecloud-config-eureka-client    
eureka:   
	instance:     
		hostname: eureka7001.com #冒号后面必须要有空格  
		client:     
			register-with-eureka: false #当前的eureka-server自己不注册进服务列表中
            fetch-registry: false #不通过eureka获取注册信息    
            service-url:       
            	defaultZone: http://eureka7001.com:7001/eureka/
```

- **microservicecloud-config-dept-client.yml**

```yaml
spring:   
	profiles:    
		active:    
		- dev
--- 
server:  
	port: 8001
spring:    
	profiles: dev   
	application:     
		name: microservicecloud-config-dept-client   
		datasource:    
			type: com.alibaba.druid.pool.DruidDataSource    
			driver-class-name: org.gjt.mm.mysql.Driver    
			url: jdbc:mysql://localhost:3306/cloudDB01    
			username: root    
			password: Lzx@123456    
			dbcp2:      
				min-idle: 5      
				initial-size: 5      
				max-total: 5      
				max-wait-millis: 200 
mybatis:  
	config-location: classpath:mybatis/mybatis.cfg.xml  
	type-aliases-package: com.atguigu.springcloud.entities  
	mapper-locations:  - classpath:mybatis/mapper/**/*.xml
eureka:   
	client: #客户端注册进eureka服务列表内    
	service-url:       
		defaultZone: http://eureka7001.com:7001/eureka  
	instance:    
		instance-id: dept-8001.com    
		prefer-ip-address: true
info:  
	app.name: xixi-microservicecloud-springcloudconfig01  
	company.name: www.xixi.com  
	build.artifactId: $project.artifactId$  
	build.version: $project.version$
---
server:  
	port: 8001
spring:    
	profiles: test   
	application:     
		name: microservicecloud-config-dept-client   
		datasource:    
			type: com.alibaba.druid.pool.DruidDataSource    
			driver-class-name: org.gjt.mm.mysql.Driver    
			url: jdbc:mysql://localhost:3306/cloudDB02    
			username: root    
			password: Lzx@123456    
			dbcp2:      
				min-idle: 5      
				initial-size: 5      
				max-total: 5      
				max-wait-millis: 200      
mybatis:  
	config-location: classpath:mybatis/mybatis.cfg.xml  
	type-aliases-package: com.atguigu.springcloud.entities  
	mapper-locations:  - classpath:mybatis/mapper/**/*.xml
eureka:   
	client: #客户端注册进eureka服务列表内    
	service-url:       
		defaultZone: http://eureka7001.com:7001/eureka  
	instance:    
		instance-id: dept-8001.com    
		prefer-ip-address: true
info:  
	app.name: xixi-microservicecloud-springcloudconfig02  
	company.name: www.xixi.com  
	build.artifactId: $project.artifactId$  
	build.version: $project.version$
```

