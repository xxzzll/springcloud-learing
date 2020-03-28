一、Spring Boot 入门

## 1、Spring Boot 简介

> 简化Spring应用开发的框架；
>
> 整个Spring技术栈的一个大整合；
>
> J2EE开发的一站式解决方案；

## 2、微服务

2014,martin fowler

微服务：架构风格（服务微化）

一个应用应该是一组小型服务；可以通过http的方式进行互通。

单体应用：ALL IN ONE

每个功能元素最终都可以独立替换和独立升级的软件单元；

[详细参照微服务文档：](https://martinfowler.com/articles/microservices.html)

![Selection_048](/home/xixi/Pictures/Selection_048.jpg)

![Selection_049](/home/xixi/Pictures/Selection_049.jpg)

针对大型分布式应用Spring提供的解决方案：

Spring 为我们提供了从微服务的构建（Spring Boot）到众多微服务组成的网之间的互联互调（Spring Cloud）以及包括分布式中间进行的流数据计算和批处理（Spring Cloud Data Flow）

![Selection_050](/home/xixi/Pictures/Selection_050.jpg)

## 3、环境约束：

-jdk：**jdk1.8.0_192**

-maven：**Apache Maven 3.3.9**

-idea：**IntelliJ IDEA 2019.1.2 (Ultimate Edition)
Build #IU-191.7141.44, built on May 7, 2019
Licensed to jetbrains js
Subscription is active until November 27, 2019
JRE: 1.8.0_202-release-1483-b49 amd64
JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
Linux 4.15.0-54-generic**

-Spring Boot：**2.1.6.RELEASE**

## 4、Spring Boot HelloWorld

一个功能

浏览器发送一个hello，服务器接收请求并处理，响应Hello World字符串；

### 1、创建一个maven工程（jar）

### 2、导入spring boot 相关依赖

```xml
	<!--引入一个父级项目和一个web相关的maven依赖-->
	<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.6.RELEASE</version>
    </parent>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
```



### 3、编写一个主程序：启动Spring Boot 应用

```java
/**
 *  <br>
 * 〈Spring Boot 主程序〉
 *@SpringBootApplication 来标注一个主程序类，说明一个Spring Boot 应用
 * @author xixi
 * @create 19-7-6
 * @since 1.0.0
 */
@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {
        // 让Spring Boot 主程序启动起来
        SpringApplication.run(MainApp.class, args);
    }
}
```



### 4、编写相关的Controller

```java
/**
 *  <br>
 * 〈〉
 *
 * @author xixi
 * @create 19-7-6
 * @since 1.0.0
 */
@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("hello")
    public String hello(){
        return "Hello World!";
    }
}
```



### 5、运行主程序测试

### 6、简化部署

```xml
   <!-- maven插件：将应用打包成可执行的jar-->
	<build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```



将这个应用打成jar包，直接使用java -jar 命令进行执行；

## 5、Hello World 探究

### 1、POM文件

#### 1、仲裁中心（父项目）

```xml
<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.6.RELEASE</version>
    </parent>
    // 他的父项目：
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>2.1.6.RELEASE</version>
        <relativePath>../../spring-boot-dependencies</relativePath>
     </parent>
	// 他是真正管理Spring Boot应用里面的所有依赖版本；
```



Spring Boot 的版本仲裁中心：

以后我们导入依赖默认不需要写版本；（没有在dependencies管理的依赖，需要声明版本号）

#### 2、启动器（导入的依赖）

```xml
 	<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
```



**spring-boot-starter**-==web==:

​	spring-boot-starter：spring-boot场景启动器；帮我们导入web模块正常运行所依赖的组件；



Spring Boot 将所有的功能场景都抽取出来，做成一个个starters（启动器），只需要在项目里面引入这些starter相关场景的所有依赖都会导入进来。要用什么功能就导入什么场景的启动器。

### 2、主程序类，主入口类

```java
/**
 *  <br>
 * 〈Spring Boot 主程序〉
 *@SpringBootApplication 来标注一个主程序类，说明一个Spring Boot 应用
 * @author xixi
 * @create 19-7-6
 * @since 1.0.0
 */
//@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {
        // 让Spring Boot 主程序启动起来
        SpringApplication.run(MainApp.class, args);
    }
}
```

@SpringBootApplication Spring Boot应用标注在某个类是SpringBoot的主配置类，SpringBoot就应该运行这个类的main方法来启动SpringBoot应用。

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
public @interface SpringBootApplication {
```

**@SpringBootConfiguration**：SpringBoot的配置类；（SpringBoot定义的注解）

​	标注在某个类上，表示这是一个SpringBoot的配置类；

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
public @interface SpringBootConfiguration {
}
```

​	**@Configuration**：配置类上标注这个注解；(Spring定义的注解)

​		配置类	<===> 	配置文件; 配置也是容器中的一个组件：@Component;

**@EnableAutoConfiguration**：启用自动配置功能；

​	以前我们需要配置的东西，SpringBoot帮我们自动配置；@EnableAutoConfiguration告诉SpringBoot开启自动配置功能；这样自动配置功能才能生效；

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import({AutoConfigurationImportSelector.class})
public @interface EnableAutoConfiguration {
```

​	自动配置的原理：

**@AutoConfigurationPackage**：自动配置包；

​	**@Import**({Registrar.class})

​		Spring的底层注解：@Import，给容器中导入一个组件；导入的组件由org.springframework.boot.autoconfigure.AutoConfigurationPackages.Registrar.class 类决定；

​	==将主配置类（@SpringBootApplication标注的类）的所在包及下面所有子包里面的所有组件扫描到Spring容器；==

图-自动扫描包

![1562428024826](/home/xixi/.config/Typora/typora-user-images/1562428024826.png)

**@Import**({AutoConfigurationImportSelector.class})

​	给容器中导入一些组件？

​	导入什么组件？就是后面这个类AutoConfigurationImportSelector.class告诉你的。

​	AutoConfigurationImportSelector：导入哪些组件的选择器；

​	将所有导入的组件以全类名的方式；这些组件就会添加到容器中；

​	会给容器中导入非常多的自动配置类（XxxAutoConfiguiration）：就是给容器中导入这个场景需要的所有组件，并配置好这些组件；

图-自动配置类：

![screenshot](/tmp/screenshot.png)

有了自动配置类，免去了我们手动编写配置注入功能组件等工作；

​	SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class,classLoader);

​	==Spring Boot在启动的时候，从类路径下的META-INF/spring.factories中获取EnableAutoConfiguration指定的值，将这些值作为自动配置类导入到容器中，自动配置类就生效，帮我们进行自动配置工作；以前我们需要自己配置的东西，自动配置类帮我们配置啦；==

J2EE的整体整合解决方案和自动配置都在spring-boot-autoconfigure-2.1.6.RELEASE.jar包中；

## 6、使用Spring Initializer 快速创建Spring Boot项目

ide都支持使用Spring的项目创建向导快速创建一个Spring Boot项目;

选择我们需要的模块；向导会联网创建Spring Boot项目；

默认生成的Spring Boot项目：

- 主程序已经生成好了，我们只需要我们自己的逻辑
- resources文件夹中目录结构
  - static：保存所有的静态资源；js、css、images
  - templates：保存所有的模板页面；（Spring Boot默认jar包使用嵌入式的Tomcat，默认不支持JSP页面）；可以使用模板引擎（freemarker、thymeleaf）；
  - application.properties：Spring Boot应用的配置文件；可以修改一些默认设置；

# 二、配置文件

## 1、配置文件

SpringBoot使用一个全局的配置文件，配置文件名是固定的；

- application.properties
- application.yml

配置文件的作用：修改SpringBoot自动配置的默认值；

SpringBoot在底层将所有的东西都给我们呢自动配置好了；

YAML(YAML Ais't Markup Language)

​	YAML A Markup Language

​	YAML isn't Markup Language

标记语言：

​	以前的配置语言：大多数使用xx.xml文件

​	YAML：以数据为中心，比json、xml等更适合做配置文件；

​	YAML：

```yaml
server:
  port: 8081
```

​	XML:

```xml
<server>
	<port>8081</port>
</server>
```

## 2、YAML语法：

### 1、基本语法

k:(空格)v：表示一对键值对（空格必须有）;

以**空格**的缩进来控制层级关系;只要是左对齐的一列数据，都是同一个层级的;

```yaml
server: 
	port: 8081
	path: /hello
```

属性和值也是大小写敏感;

### 2、值的写法

字面量：普通的值（数字，字符串，布尔）

​	k: v：字面直接来写;

​		字符串默认不用加上引号或者双引号;

​		"":双引号;不会转义字符串里面的特殊字符；特殊字符会作为本身想表示的意思

​				name: "zhangsan \n lisi":输出：zhangsan 换行 lisi

​		'':单引号;会转义特殊字符，特殊字符最终只是一个普通的字符串数据

​				name: 'zhangsan \n lisi':输出：zhangsan \n lisi

对象（属性和值）（键值对）：

​	k: v：在下一行写对象的属性和值的关系;注意缩进

​	对象还是k: v的方式

```yaml
friends: 
	lastName: zhangsan
	age: 20
```

行内写法：

```yaml
friends: {lastName: zhangsan,age: 20}
```

数组（集合List,Set）:

```yaml
pets:
	-	cat
	-	dog
	-	pig
```

行内写法：

```yaml
pets: [cat,dog,pig]
```

## 3、配置文件值注入

配置文件

```yaml
server:
  port: 8081
persion:
  lastName: hello
  age: 20
  birthday: 2018/12/12
  maps: {k1: v1, k2: v2}
  lists:
    - lisi
    - tianwu
    - zhaoliu
  dog:
    name: dog
    age: 2
```

### 1、properties配置文件在idea中默认utf-8可能会乱码

解决方案如下图：勾选Transparent native-toasscii conversion

![1562472223057](/home/xixi/.config/Typora/typora-user-images/1562472223057.png)

### 2、@Value与@ConfigurationProperties获取值的比较

|                      | @ConfigurationProperties | @Value          |
| -------------------- | ------------------------ | --------------- |
| 功能                 | 批量注入配置文件中的属性 | 一个个指定      |
| 松散绑定（松散语法） | 支持                     | 不支持          |
| SpEL                 | 不支持                   | 支持（#{12×1}） |
| JSR303数据校验       | 支持                     | 不支持          |

配置文件yml和properties都能获取到值;

```java
/**
 *  <br>
 * 将配置文件中配置的每一个属性的值，映射到这个组件中;
 * @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定;
 *      prefix = "persion"：配置文件中哪个下面的所有属性进行一一映射
 *
 * 只有这个组件是容器中的组件，容器才能提供的ConfigurationProperties功能;
 * @ConfigurationProperties(prefix = "persion")默认从全局配置文件中获取值;
 *
 * @author xixi
 * @create 19-7-7
 * @since 1.0.0
 */
@Component // 交给spring容器管理
@ConfigurationProperties(prefix = "persion")
@Validated // JSR303数据校验，和@ConfigurationProperties注解一起使用，来验证属性的值是否符合指定类型规范，如：@Email-邮件地址类型
public class Persion {

    @Email // 必须是邮箱格式的数据
    private String lastName;
    private Integer age;
    private Date birthday;
    private Map<String,Object> maps;
    private List<Object> list;
    private Dog dog;
}

/**
 *  <br>
 * 〈@Value注解的使用，用来给某个特定属性从配置文件中设置值〉
 *
 * @author xixi
 * @create 19-7-7
 * @since 1.0.0
 */
@Component
public class Dog {
    /**
     * <bean class="Dog">
     *      <property name="name" value="？=字面量|${key}环境变量、配置文件中获取|#{SpeL}"></property>
     * </bean>
     */

    @Value("${dog.name}")
    private String name;
    @Value("#{0.2*10}")
    private Integer age;
}    
```

### 4、@PropertySource 与 @ImportResource

​	@PropertySource：加载指定的配置文件;

```java
@PropertySource(value = "classpath:persion.properties")
@Component // 交给spring容器管理
@ConfigurationProperties(prefix = "persion")
@Validated // JSR303数据校验，和@ConfigurationProperties注解一起使用，来验证属性的值是否符合指定类型规范，如：@Email-邮件地址类型
public class Persion {

//    @Email // 必须是邮箱格式的数据
    private String lastName;
    private Integer age;
    private Date birthday;
    private Map<String,Object> maps;
    private List<Object> list;
    private Dog dog;
```

​	@**ImportResource**：导入Spring的配置文件，让配置文件里面的内容生效

​	Spring Boot里面没有Spring的配置文件，我们自己编写的配置文件，也不能自动识别;

​	想让Spring配置文件生效，加载进来; @**ImportResource**标注在一个配置类上

不来编写Spring bean配置文件：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="helloService" class="com.cn.huawei.springboot02config.service.HelloService"></bean>
</beans>
```





```java
// 导入Spring的配置文件让其生效
@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class Springboot02ConfigApplication {
```

SpringBoot推荐给容器中添加组件的方式：推荐使用全注解

1、配置类	====	Spring配置文件

2、使用@Bean给容器添加组件

```java
/**
 *  <br>
 * @Configuration:指明当前类是个配置类，替代Spring配置文件
 * 在配置文件用<bean></bean>标签添加组件
 *
 * @author xixi
 * @create 19-7-7
 * @since 1.0.0
 */

@Configuration
public class MyAppConfig {

    // 将方法的返回值添加到容器中，容器中组件id默认为方法名
    @Bean
    public HelloService helloService01(){
        System.out.println("配置类@Bean给容器添加组件了....");
        return new HelloService();
    }
}
```

## 4、配置文件占位符

### 1、随机数

```java
${random.value}${random.int}${random.long}
${random.int(10)${random.int[1024,65536])
```



### 2、占位符获取之前配置的值，如果没有可以用":"指定默认值;

```java
persion.dog.name=${persion.last-name}_dog
persion.dog.age=2
persion.list=1,2,3
persion.maps.k1=v1
persion.maps.k2=v2
dog.name=${persion.hello:hello}_小黄
```

## 5、Profile

### 1、多Profile文件

我们在主配置文件编写的时候，文件名可以是applicaiton-{profile}.properties/yml

默认使用application.properties的配置;

### 2、yml支持多文档块方式

```yaml

server:
  port: 8081
spring:
  profiles:
    active: product
---
server:
  port: 8082
spring:
  profiles: dev
---
server:
  port: 8083
spring:
  profiles: product
```



### 3、激活制定profile

1、在配置文件中指定： spring.profiles.active=dev

2、命令行的方式：--spring.profiles.active=dev

​	2.1、在idea主启动配置环境中的Program aruments:后添加--spring.profiles.active=dev

![1562478820068](/home/xixi/.config/Typora/typora-user-images/1562478820068.png)

​		2.2、 /usr/local/jdk1.8.0_192/bin/java -jar springboot02-config-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev

​			可以直接在测试的时候，配置传入命令行参数

3、虚拟机参数：

​	-Dspring.profiles.active=dev

![1562480050316](/home/xixi/.config/Typora/typora-user-images/1562480050316.png)

## 6、配置文件的加载位置

SpringBoot 启动会扫描以下位置的application.properties或者application.yml文件作为SpringBoot的默认配置文件

—file:./config/

—file:./

—classpath:/config/

—classpath:/

以上是按照==优先级从高到低==的顺序，所有位置的文件都会被加载，**高优先级配置**内容会**覆盖低优先级配置**内容;

SpringBoot会从这四个位置全部加载主配置文件;形成互补配置;

==我们也可以通过配置spring.config.location来改变默认配置;==

项目打包好后，我们可以使用命令行参数的形式，启动项目时来指定配置文件的新位置（--spring.config.location=/xxx/xxx/.application.properties）;指定的配置文件和默认记载的配置文件共同起作用，形成互补配置;

## 7、外部配置的加载顺序

==Spring Boot 也可以从以下位置加载配置;优先级从高到低;高优先级的配置覆盖低优先级的配置，所有配置会形成互补配置;==

这些方式优先级如下：

1. 命令行参数

   eg:/usr/local/jdk1.8.0_192/bin/java -jar ./springboot02-config-02-0.0.1-SNAPSHOT.jar --server.port=8088  --server.servlet.context-path=/boot

   多个配置用空格分开; --配置项=值

2. 来自java：comp/env的JNDI属性

3. Java系统属性（System.getProperties()）

4. 操作系统环境变量

5. RandomValuePropertySource配置的random.*属性值

   ==**由jar包外向jar包内进行寻找**==

   ==**优先加载带profile**==

6. jar包外部的application-{profile}.properties或application.yml(带spring.profile)配置文件

7. jar包内部的application-{profile}.properties或application.yml(带spring.profile)配置文件

   **==再来加载不带profile==**

8. jar包外部的application-{profile}.properties或application.yml(不带spring.profile)配置文件

9. jar包内部的application-{profile}.properties或application.yml(不带spring.profile)配置文件

10. @Configuration注解类上的@PropertySource

11. 通过SpringApplication.setDefaultProperties指定的默认属性

所有支持的配置加载来源：

[参考官方文档](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-external-config)

## 8、自动配置的原理

配置文件到底能写什么？怎么写？自动配置原理？

[配置文件的配置属性参考](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/common-application-properties.html)

### 1、自动配置原理：

1）、SpringBoot启动的时候加载主配置类，开启自动配置功能**@EnableAutoConfiguration**;

2）、@EnableAutoConfiguration作用;

> 组合注解，是由@AutoConfigurationPackage和@Import(AutoConfigurationImportSelector.class)组成;

```java
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {

```



组合注解之一：@AutoConfigurationPackage注解的作用就是将主配置类所在的包下面所有的组件都扫描到Spring容器中。

```java
// 导入组件AutoConfigurationPackages类下内部类Registrar.class
@Import(AutoConfigurationPackages.Registrar.class)
public @interface AutoConfigurationPackage {}
```

@AutoConfigurationPackage注解下导入的组件Registrar.class，作用：存储从导入的主配置中存储基本扫描的包路径;

```java
/**
  * {@link ImportBeanDefinitionRegistrar} to store the base package from the importing
  * configuration.
  */
static class Registrar implements ImportBeanDefinitionRegistrar, DeterminableImports {
    // 从带@SpringBootApplication注解的类获取包路径;
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        // registry type of DefaultListableBeanFactory
        register(registry, new PackageImport(metadata).getPackageName());
    }

    @Override
    public Set<Object> determineImports(AnnotationMetadata metadata) {
        return Collections.singleton(new PackageImport(metadata));
    }
}

```



组合注解之二：@Import(AutoConfigurationImportSelector.class)注解，作用从META-INF/spring.factories文件中获取默认定义好的各种场景使用的自动配置类;

```java
// 扫描所有jar包类路径下 META-INF/spring.factories文件获取候选配置类
protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
    List<String> configurations = SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass(),
                                                                         getBeanClassLoader());
    Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you "
                    + "are using a custom packaging, make sure that file is correct.");
    return configurations;
}
```



将类路径下META-INFO/spring.factories文件里面配置的所有EnableAutoConfiguration的值加入到容器中;

```properties
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\
org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration,\
org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration,\
org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration,\
org.springframework.boot.autoconfigure.cloud.CloudServiceConnectorsAutoConfiguration,\
org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration,\
org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration,\
org.springframework.boot.autoconfigure.couchbase.CouchbaseAutoConfiguration,\
org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseReactiveDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseReactiveRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.ldap.LdapRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoReactiveRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.neo4j.Neo4jRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.solr.SolrRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration,\
org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration,\
org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration,\
org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration,\
org.springframework.boot.autoconfigure.elasticsearch.jest.JestAutoConfiguration,\
org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientAutoConfiguration,\
org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration,\
org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration,\
org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration,\
org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration,\
org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration,\
org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration,\
org.springframework.boot.autoconfigure.hazelcast.HazelcastJpaDependencyAutoConfiguration,\
org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration,\
org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration,\
org.springframework.boot.autoconfigure.influx.InfluxDbAutoConfiguration,\
org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration,\
org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration,\
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration,\
org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration,\
org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration,\
org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration,\
org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration,\
org.springframework.boot.autoconfigure.jsonb.JsonbAutoConfiguration,\
org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration,\
org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration,\
org.springframework.boot.autoconfigure.ldap.LdapAutoConfiguration,\
org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration,\
org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration,\
org.springframework.boot.autoconfigure.mail.MailSenderValidatorAutoConfiguration,\
org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration,\
org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration,\
org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration,\
org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration,\
org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration,\
org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration,\
org.springframework.boot.autoconfigure.reactor.core.ReactorCoreAutoConfiguration,\
org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration,\
org.springframework.boot.autoconfigure.security.servlet.SecurityRequestMatcherProviderAutoConfiguration,\
org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration,\
org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration,\
org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration,\
org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration,\
org.springframework.boot.autoconfigure.sendgrid.SendGridAutoConfiguration,\
org.springframework.boot.autoconfigure.session.SessionAutoConfiguration,\
org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration,\
org.springframework.boot.autoconfigure.security.oauth2.client.reactive.ReactiveOAuth2ClientAutoConfiguration,\
org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration,\
org.springframework.boot.autoconfigure.security.oauth2.resource.reactive.ReactiveOAuth2ResourceServerAutoConfiguration,\
org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration,\
org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration,\
org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration,\
org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration,\
org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration,\
org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration,\
org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration,\
org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration,\
org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.ReactiveWebServerFactoryAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.function.client.ClientHttpConnectorAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration,\
org.springframework.boot.autoconfigure.websocket.reactive.WebSocketReactiveAutoConfiguration,\
org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration,\
org.springframework.boot.autoconfigure.websocket.servlet.WebSocketMessagingAutoConfiguration,\
org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration,\
org.springframework.boot.autoconfigure.webservices.client.WebServiceTemplateAutoConfiguration
```

每一个这样的 XxxAutoConfiguration类都是容器中的一个组件，都被加入到容器中，用他们来做自动配置;

3）、每一个自动配置类进行自动配置功能;

4）、以**HttpEncodingAutoConfiguration(Http编码自动配置)**为例解释自动配置原理;

```java
@Configuration // 表示这是一个配置类，以前编写的配置文件一样，也可以给容器中添加组件
@EnableConfigurationProperties({HttpProperties.class}) // 启用ConfigurationProperties功能;将配置文件中对应的值和HttpProperties绑定起来；并把HttpProperties加入IOC容器中;
@ConditionalOnWebApplication(// Spring底层@Conditional注解（Spring注解版）：根据不同的条件，如果满足指定的条件，整个配置里面的配置才会生效;	判断当前应用是否为web应用，如果是，当前配置类生效;
    type = Type.SERVLET
)
@ConditionalOnClass({CharacterEncodingFilter.class})// 判断当前项目有没有这个类：CharacterEncodingFilter：SpringMVC中解决乱码的过滤器;
@ConditionalOnProperty(// 判断配置文件中是否存在某个配置spring.http.encoding.enabled; 如果不存在，判断也是成立的;
// 即使我们配置文件中不配置spring.http.encoding.enabled=true/false,也是生效的;
    prefix = "spring.http.encoding",
    value = {"enabled"},
    matchIfMissing = true
) 
public class HttpEncodingAutoConfiguration {
```

```java
@Configuration
@EnableConfigurationProperties({HttpProperties.class})
@ConditionalOnWebApplication(
    type = Type.SERVLET
)
@ConditionalOnClass({CharacterEncodingFilter.class})
@ConditionalOnProperty(
    prefix = "spring.http.encoding",
    value = {"enabled"},
    matchIfMissing = true
)
public class HttpEncodingAutoConfiguration {
    // 由于HttpProperties已经与SpringBoot配置文件映射了，而properties是HttpProperties内部类，也即properties跟SpringBoot配置文件绑定了;
    private final Encoding properties;

    // 只有一个构造器的情况下，参数的之就会从容器中拿
    public HttpEncodingAutoConfiguration(HttpProperties properties) {
        this.properties = properties.getEncoding();
    }

    @Bean // 给容器中添加一个组件，这个组件的某些值需要从properties中获取
    @ConditionalOnMissingBean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
        filter.setEncoding(this.properties.getCharset().name());
        filter.setForceRequestEncoding(this.properties.shouldForce(org.springframework.boot.autoconfigure.http.HttpProperties.Encoding.Type.REQUEST));
        filter.setForceResponseEncoding(this.properties.shouldForce(org.springframework.boot.autoconfigure.http.HttpProperties.Encoding.Type.RESPONSE));
        return filter;
    }
```

根据当前不同的条件判断，决定这个配置类是否生效？

一但这个配置类生效，这个配置类就会给容器中添加各种组件;这些组件的属性是从对应的XxxProperties类中获取的,这些类里面的每个属性又是和配置文件绑定的;



5）、所有在配置文件中能配置的属性都是在XxxProperties类中封装着;配置文件能配置什么就可以参照某个功能对应的这个属性类;

```java
@ConfigurationProperties(// 从配置文件中获取指定的值和bean的属性绑定
    prefix = "spring.http"
)
public class HttpProperties {
```



精髓：

​	1）、SpringBoot启动时会加载大量的自动配置类;

​	2）、我们看我们需要的功能有没有SpringBoot默认写好的自动配置类;

​	3）、我们再来看这个自动配置类中到底配置了哪些组件;（只要我们要用的组件有，我们就不需要再来配置了）

​	4）、给容器中自动配置类添加组件的时候，会从properties类中获取某些属性，我们就可以在配置文件.properties/...中指定这些属性的值;

XxxAutoConfiguration：自动配置类;给容器中添加组件;

XxxProperties：封装配置文件中相关属性;

xxx.properties：绑定与XxxProperties类中属性的值;

### 2、细节

#### 1、@Conditional派生注解（Spring注解版原生的@Conditional作用）

作用：必须是@Conditional指定的条件成立，才给容器中添加组件，配置里面的所有内容生效;

| @Conditional扩展                | 作用(判断是否满足当前指定条件)                   |
| ------------------------------- | ------------------------------------------------ |
| @ConditionalOnJava              | 系统的java版本是否符合要求                       |
| @ConditionalOnBean              | 容器中存在指定Bean                               |
| @ConditionalOnMissingBean       | 容器中不存在指定Bean                             |
| @ConditionalOnExpression        | 满足SpEL表达式指定                               |
| @ConditionalOnClass             | 系统中有指定类                                   |
| @ConditionalOnMissiongClass     | 系统中没有指定类                                 |
| @ConditionalOnSingleCandidate   | 容器中只有一个指定的Bean，或者这个Bean是首选Bean |
| @ConditionalOnProperty          | 系统中指定的属性是否有指定的值                   |
| @ConditionalOnResource          | 类路径下是否存在指定资源文件                     |
| @ConditionalOnWebApplication    | 当前是web环境                                    |
| @ConditionalOnNotWebApplication | 当前不是web环境                                  |
| @ConditionalOnJndi              | JNDI存在指定项                                   |

**自动配置类必须在一定的条件下才能生效;**

我们怎么知道哪些自动配置类生效了？

我们可以通过启用 debug=true属性;来让控制台打印自动配置报告，这样我们就可以很方便地知道哪些自动配置类生效;

```java
============================
CONDITIONS EVALUATION REPORT
============================


Positive matches:(自动配置类启用的)
-----------------

   CodecsAutoConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.http.codec.CodecConfigurer' (OnClassCondition)

   CodecsAutoConfiguration.JacksonCodecConfiguration matched:
      - @ConditionalOnClass found required class 'com.fasterxml.jackson.databind.ObjectMapper' (OnClassCondition)

   CodecsAutoConfiguration.JacksonCodecConfiguration#jacksonCodecCustomizer matched:
      - @ConditionalOnBean (types: com.fasterxml.jackson.databind.ObjectMapper; SearchStrategy: all) found bean 'jacksonObjectMapper' (OnBeanCondition)
......
Negative matches:(没有自动配置启动的)
-----------------

   ActiveMQAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'javax.jms.ConnectionFactory' (OnClassCondition)

   AopAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.aspectj.lang.annotation.Aspect' (OnClassCondition)          
```

# 三、日志

## 1、日志框架

小张：开发一个大型系统;

​	1、System.out.println(""); 将关键数据打印在控制台;去掉？写在一个文件？

​	2、框架来记录系统的一些运行时信息;日志框架; zhanglogging.jar;

​	3、添加：高大上的几个功能？异步模式？自动归档？xxxx？zhanglogging-good.jar;

​	4、将以前框架卸下来？换上新的框架，重新修改之前相关API;zhanglogging-prefect.jar;

​	5、JDBC-----数据库驱动;

​			写了一个统一的接口层; 日志门面（日志的一个抽象层）; logging-abstract.jar;

​			给项目中导入具体的日志实现就可以了;我们之前的日志框架都是现实的抽象层;

市面上的日志框架：

JUL、JCL、Jboss-logging、logback、log4j、log4j2、slf4j......

**门市上的日志框架：**

| 日志门面(日志的抽象层)                                       | 日志实现                                           |
| ------------------------------------------------------------ | -------------------------------------------------- |
| ~~JCL(Jakarta Commons Logging)~~、SLF4J(Simple Logging Facade for java)、**~~jboss-logging~~** | Log4j、JUL(java.util.logging)、Log4j2、**Logback** |

左边选一个门面(抽象层)、右边选一个实现;

日志门面：SLF4J;

日志实现：Logback;

SpringBoot： 底层是Spring框架，Spring框架默认是用JCL;

​	SpringBoot选用SLF4J和Logback

## 2、SLF4J使用

### 1、如何在系统中使用SLF4J

以后在开发的时候，日志记录方法的调用，不应该直接调用日志的实现类，而是调用日志抽象层里面的方法;

给系统里面导入slf4j.jar和实现logback.jar

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    logger.info("Hello World");
  }
}
```

![concrete-bindings.png (1152Ã636)](https://www.slf4j.org/images/concrete-bindings.png)

每一个日志的实现框架都有自己的配置文件;使用了sf4j以后，配置文件还是做成日志实现框架的配置文件;

### 2、遗留问题

a(slf4j+logback)：Spring(common-logging)、Hibernate(jboss-logging)、MyBatis、xxx

统一日志记录，即使是别的框架和我一起使用slf4j+logback组合进行输出;

![legacy.png (1587Ã1123)](https://www.slf4j.org/images/legacy.png)

**如何让系统中所有的日志都统一到slf4j？**
==1、将系统中其他日志框架先排除出去;==

==2、用中间包来替换原有日志框架;==

==3、我们导入slf4j其他的现实(对其他日志框架的重新封装)==

## 3、SpringBoot日志关系

SpringBoot最基本的场景启动器

```xml
 	<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
      <version>2.1.6.RELEASE</version>
      <scope>compile</scope>
    </dependency>
```



SpringBoot使用它来做日志功能：

```xml
	<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-logging</artifactId>
      <version>2.1.6.RELEASE</version>
      <scope>compile</scope>
    </dependency>
```

图-SpringBoot日志关系图

![1562508871430](/home/xixi/.config/Typora/typora-user-images/1562508871430.png)

总结：

​	1）、SpringBoot底层也是使用slf4j-logback的方式进行日志记录;

​	2）、SpringBoot也罢其他的日志都替换成slf4j;

​	3）、中间替换包？

​	4）、如果我们要引入其他框架？一定要把这个框架的默认日志依赖移除掉？

​			Spring框架使用的commons-logging

==SpringBoot能自动适配所有的日志，而且底层使用slf4j+logback,要引入其他框架，首先要移除原框架的logging框架即可;==

## 4、日志使用

### 1、默认配置

SpringBoot默认帮我们配置好了日志;

```java
    Logger logger = LoggerFactory.getLogger(getClass());

    // 日志级别
    // 由低到高：trace<debug<info<warn<error
    // 可以调整输出的日志级别;日志就会在这个级别以上做出信息输出
    @Test
    public void contextLoads() {
//        System.out.println("");

        logger.trace("这个是trace日志...");
        logger.debug("这个是debug日志...");
        // SpringBoot默认是info级别的日志记录，没有指出级别就用springboot默认给出的级别：root级别
        logger.info("这个是info日志...");
        logger.warn("这个是warn日志...");
        logger.error("这个是error日志...");


    }

```

日志等级的设定：

```properties
# 设置com.cn.huawei路径下日志级别为trace
logging.level.com.cn.huawei=trace
```

| logging.file | logging.path | Example  | Description                      |
| ------------ | ------------ | -------- | -------------------------------- |
| (none)       | (none)       |          | 只在控制台输出                   |
| 指定文件名   | (none)       | my.log   | 输出日志到my.log文件             |
| （none）     | 指定目录     | /var/log | 输出到制定目录的spring.log文件中 |

### 2、指定配置

给类路径下放上每个日志框架自己的配置文件即可;SpringBoot就不使用默认配置的;

| Logging System          | Customization                                                |
| ----------------------- | ------------------------------------------------------------ |
| Logback                 | `logback-spring.xml`, `logback-spring.groovy`, `logback.xml`, or `logback.groovy` |
| Log4j2                  | `log4j2-spring.xml` or `log4j2.xml`                          |
| JDK (Java Util Logging) | `logging.properties`                                         |

logback.xml：直接被日志框架识别了;

logback-spring.xml：日志框架就不直接加载日志的配置项，由SpringBoot解析日志配置，可以使用SpringBoot的高级Profile功能;

```xml
<!--  可以指定某段配置只在某个环境下生效	-->
<springProfile name="staging">
	<!-- configuration to be enabled when the "staging" profile is active -->
</springProfile>
<springProfile name="dev | staging">
	<!-- configuration to be enabled when the "dev" or "staging" profiles are active -->
</springProfile>

<springProfile name="!production">
	<!-- configuration to be enabled when the "production" profile is not active -->
</springProfile>
```

否则：

如果使用logback.xml作为日志配置文件，还要使用profile功能，会有以下错误

```
no application action for [springProfile]
```

## 5、切换日志框架

可以按照slf4的日志适配图，进行相关的切换;

切换为log4j2

```xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <artifactId>spring-boot-starter-logging</artifactId>
                    <groupId>org.springframework.boot</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
        </dependency>
```

# 四、Spring Boot 与 web开发

## 1、使用SpringBoot：

**1）、创建SpringBoot**

**2）、SpringBoot已经默认将这些场景配置好了，只需要在配置文件中指定少量配置就可以运行起来**

**3）、自己编写业务代码;**

**自动配置原理？**

这个场景SpringBoot帮我们配置了什么？能不能修改？能修改哪些配置？能不能扩展？...

```
XxxAutoConfiguration：帮我们向容器中自动配置组件;
XxxProperties：配置类类封装配置文件的内容;
```

## 2、SpringBoot对静态资源的映射规则;

对静态资源定位有关参数配置类ResourceProperties：

- classpath:/META-INF/resources/
- classpath:/resources/
- classpath:/static/
- classpath:/public/

```java
@ConfigurationProperties(prefix = "spring.resources", ignoreUnknownFields = false)
public class ResourceProperties {

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
			"classpath:/resources/", "classpath:/static/", "classpath:/public/" };

	/**
	 * Locations of static resources. Defaults to classpath:[/META-INF/resources/,
	 * /resources/, /static/, /public/].
	 */
	private String[] staticLocations = CLASSPATH_RESOURCE_LOCATIONS;
```



SpringBoot 使用WebJars统一管理静态资源

```java
 public void addResourceHandlers(ResourceHandlerRegistry registry) {
            if (!this.resourceProperties.isAddMappings()) {
                logger.debug("Default resource handling disabled");
            } else {
                Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
                CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
                if (!registry.hasMappingForPattern("/webjars/**")) {
                    this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{"/webjars/**"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"}).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
                }

                String staticPathPattern = this.mvcProperties.getStaticPathPattern();
                // 静态资源文件映射
                if (!registry.hasMappingForPattern(staticPathPattern)) {
                    this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{staticPathPattern}).addResourceLocations(getResourceLocations(this.resourceProperties.getStaticLocations())).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
                }

            }
        }
// 欢迎页面映射配置
@Bean
public WelcomePageHandlerMapping welcomePageHandlerMapping(ApplicationContext applicationContext) {
			return new WelcomePageHandlerMapping(new TemplateAvailabilityProviders(applicationContext),
					applicationContext, getWelcomePage(), this.mvcProperties.getStaticPathPattern());
}
// 喜欢图标的配置
@Configuration
@ConditionalOnProperty(value = "spring.mvc.favicon.enabled", matchIfMissing = true)
public static class FaviconConfiguration implements ResourceLoaderAware {

			private final ResourceProperties resourceProperties;

			private ResourceLoader resourceLoader;

			public FaviconConfiguration(ResourceProperties resourceProperties) {
				this.resourceProperties = resourceProperties;
			}

			@Override
			public void setResourceLoader(ResourceLoader resourceLoader) {
				this.resourceLoader = resourceLoader;
			}

			@Bean
			public SimpleUrlHandlerMapping faviconHandlerMapping() {
				SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
				mapping.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
				mapping.setUrlMap(Collections.singletonMap("**/favicon.ico", faviconRequestHandler()));
				return mapping;
			}

			@Bean
			public ResourceHttpRequestHandler faviconRequestHandler() {
				ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
				requestHandler.setLocations(resolveFaviconLocations());
				return requestHandler;
			}

			private List<Resource> resolveFaviconLocations() {
				String[] staticLocations = getResourceLocations(this.resourceProperties.getStaticLocations());
				List<Resource> locations = new ArrayList<>(staticLocations.length + 1);
				Arrays.stream(staticLocations).map(this.resourceLoader::getResource).forEach(locations::add);
				locations.add(new ClassPathResource("/"));
				return Collections.unmodifiableList(locations);
			}

}


```

==1）、所有/webjars/**,都去classpath:/META-INF/resources/webjars/找资源;==

​	webjars：以jar的方式引入静态资源;

​	webjars官网：https://www.webjars.org/

![1562769888015](/home/xixi/.config/Typora/typora-user-images/1562769888015.png)

​		访问：localhost:8080/webjars/jquery/3.3.1/jquery.js

​		返回：jquery.js

==2）、"/**"：访问当前项目的任何资源，都去(静态资源的文件夹)找映射;==

```text
"classpath:/META-INF/resources/", 
"classpath:/resources/",
"classpath:/static/", 
"classpath:/public/" 
"/"：当前项目的根路径
```

localhost:8080/abc ：去静态资源文件夹里找abc;

==3）、欢迎页：静态资源文件下的所有index.html页面;被"/**"映射;==

localhost:8080/ ：找index.html页面

==4）、所有的**/favicon.ico 都是在静态资源文件夹下找;==

## 3、默认引擎

JSP、Velocity、Freemarker、Thymeleaf

![模板引擎的原理图](/home/xixi/Pictures/springMVC/thymeleaf模板引擎的原理图.png)

SpringBoot推荐的Thymeleaf：

​	语法更简单、功能更强大;

### 1、引入thymeleaf;

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
    // 默认是2.1.6版本
</dependency>
```

切换高版本的thyeleaf3.xx

```xml
// 在properties标签下控制版本
<properties>
        <java.version>1.8</java.version>
        <thymeleaf.version>3.0.11.RELEASE</thymeleaf.version>
        <thymeleaf-layout-dialect.version>2.4.1</thymeleaf-layout-dialect.version>
    </properties>
```

### 2、Thymeleaf使用&语法

```java
@ConfigurationProperties(
    prefix = "spring.thymeleaf"
)
public class ThymeleafProperties {
    private static final Charset DEFAULT_ENCODING;
    public static final String DEFAULT_PREFIX = "classpath:/templates/";
    public static final String DEFAULT_SUFFIX = ".html";
    private boolean checkTemplate = true;
    private boolean checkTemplateLocation = true;
    private String prefix = "classpath:/templates/";
    private String suffix = ".html";
```

只要我们把HTML页面放在classpath:/templates/，thymeleaf就能自动渲染;

使用：

1、HTML标签上导入 Thymeleaf 命名空间

```html
<html lang="en" xmlns:th="http://www.thymeleaf.org">
```

2、使用Thymeleaf语法：

```html
<!DOCTYPE html>
<html lang="en"  xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>SUCCESS!</h1>

    <!-- 使用默认引擎的${}：变量表达式语法，给h4标签里面的内容设置值 -->
    <h4 th:text="${hello}">这是成功页面</h4>
</body>
</html>
```

### 3、Thymeleaf语法规则

1）、th:text：改变当前元素里面的文本内容;

​	注：th:任何html属性：来替换原生属性的值;

| 顺序 | 特征                            | 属性                                            | 描述                 |
| ---- | ------------------------------- | ----------------------------------------------- | -------------------- |
| 1    | Fragment inclusion              | th:insert、th:replace                           | 片段包含:jsp:include |
| 2    | Frament iteration               | th:each                                         | 遍历:c:forEach       |
| 3    | Conditional evaluation          | th:if、th:unless、th:switch、th:case            | 条件判断:c:if        |
| 4    | Local variable definition       | th:object、th:with                              | 声明变量:c:set       |
| 5    | General attribute modification  | th:attr、th:attrprepend、th:attrappend          | 任意属性修改         |
| 6    | Specific attribute modification | th:value、th:href、th:src、...                  | 修改指定属性默认值   |
| 7    | Text(tag body modification)     | th:text(转义特殊字符)、th:utext(不转义特殊字符) | 修改标签体内容       |
| 8    | Fragment specification          | th：fragment                                    | 声明片段             |
| 9    | Fragment removal                | th:remove                                       |                      |

2）、表达式？

​	2.1）Simple expressions(简单表达式)：

```text
Variable Expressions: ${...}：获取变量值；OGNL；
    1）、获取对象的属性、调用方法
    2）、使用内置的基本对象：
    #ctx : the context object.
    #vars: the context variables.
    #locale : the context locale.
    #request : (only in Web Contexts) the HttpServletRequest object.
    #response : (only in Web Contexts) the HttpServletResponse object.
    #session : (only in Web Contexts) the HttpSession object.
    #servletContext : (only in Web Contexts) the ServletContext object.
    3）、内置的一些工具对象：
    #execInfo : information about the template being processed.
    #messages : methods for obtaining externalized messages inside variables expressions, in the same way as they would be obtained using #{…} syntax.
    #uris : methods for escaping parts of URLs/URIs
    #conversions : methods for executing the configured conversion service (if any).
	#dates : methods for java.util.Date objects: formatting, component extraction, etc.
	#calendars : analogous to #dates , but for java.util.Calendar objects.
    #numbers : methods for formatting numeric objects.
    #strings : methods for String objects: contains, startsWith, prepending/appending, etc.
    #objects : methods for objects in general.
    #bools : methods for boolean evaluation.
    #arrays : methods for arrays.
    #lists : methods for lists.
    #sets : methods for sets.
    #maps : methods for maps.
    #aggregates : methods for creating aggregates on arrays or collections.
	#ids : methods for dealing with id attributes that might be repeated (for example, as a result of an iteration).
```

​	2.2）Selection Variable Expressions(选择表达式)：*{...},和${...}在功能上是一样的;

```text
补充：配合 th:object="${session.user}：
<div th:object="${session.user}">
  <p>Name: 
  	<span th:text="*{firstName}">Sebastian</span>.
  </p>
  <p>Surname: 
  	<span th:text="*{lastName}">Pepper</span>.
  </p>
  <p>Nationality: 
  	<span th:text="*{nationality}">Saturn</span>.	   
  </p>
</div>
```

​	2.3）Message Expressions(获取国际化内容)：

```text
#{...}
```

​	2.4）Link URL Expressions（定义URL）：

```text
@{...}
// 如：<a @{/order/process(execId=${execId},execType='FAST')}
```

​	2.5）Fragment Expressions（片段引用表达式）

```text
~{...}
// 如：<div th:insert="~{commons::main}">...</div>
```

​	2.6）Literals（字面量）

```text
    Text literals: 'one text' , 'Another one!' ,…
    Number literals: 0 , 34 , 3.0 , 12.3 ,…
    Boolean literals: true , false
    Null literal: null
    Literal tokens: one , sometext , main ,…
    Text operations:（文本操作）
    String concatenation: +
    Literal substitutions: |The name is ${name}|
    Arithmetic operations:（数学运算）
    Binary operators: + , - , * , / , %
    Minus sign (unary operator): -
    Boolean operations:（布尔运算）
    Binary operators: and , or
    Boolean negation (unary operator): ! , not
    Comparisons and equality:（比较运算）
    Comparators: > , < , >= , <= ( gt , lt , ge , le )
    Equality operators: == , != ( eq , ne )
    Conditional operators:条件运算（三元运算符）
    If-then: (if) ? (then)
    If-then-else: (if) ? (then) : (else)
    Default: (value) ?: (defaultvalue)
    Special tokens:
    		No-Operation: _ 
```



### 4、SpringMVC自动配置

1、SpringMVC处理流程：

![springMVC运行流程](/home/xixi/Pictures/springMVC/springMVC运行流程.png)

2、RequestMappingHandlerAdapter解析流程

![RequestMappingHandlerAdapter解析流程](/home/xixi/Pictures/springMVC/RequestMappingHandlerAdapter解析流程.jpg)

#### 1、SpringMVC auto-configuration

https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/using-boot-auto-configuration.html

Spring Boot 自动配置了SpringMVC.

以下是SpringBoot对SpringMVC的默认配置:

- Inclusion of `ContentNegotiatingViewResolver` and `BeanNameViewResolver` beans.

  - 自动配置了ViewResolver(视图解析器：根据方法的返回值得到视图对象(View)，视图对象决定如何渲染(转发？重定向？))

  - ContentNegotiatingViewResolver**：

    - ContentNegotiatingViewResolver组合所有视图解析器;

    - 如何定制？我们可以自己给容器中添加一个视图解析器;自动的将其组合进来;

    - ContentNegotiatingViewResolver不是自己处理view,而是代理给不同的ViewResolver来处理不同的view;

    - ContentNegotiatingViewResolver使用request的媒体类型,根据扩展名选择不同的view输出不同的格式;

    - ContentNegotiatingViewResolver是ViewResolver的一个实现;

      ```java
      public class ContentNegotiatingViewResolver extends WebApplicationObjectSupport implements ViewResolver, Ordered, InitializingBean {
      ```

      

    - ContentNegotiatingViewResolver支持在Spring MVC下输出不同的格式;

    ```java
    	@RequestMapping(value={"/user/{id}","/user/{id}.json"})  
        public ModelAndView show(@PathVariable(value="id")String id){  
             User user=userService.get(Long.valueOf(id));  
             ModelAndView mav=**new** ModelAndView("show");  
             mav.addObject(user);  
             return mav;  
        }  
    ```

    需求：

    当我请求/user/1时，便会回应show的视图，当我请求/user/{id}.json时，便会回应json格式的数据。

    而spring mvc中ContentNegotiatingViewResolver便能满足这个需求，除了json，还有xml/rss。

    其作用的配置文件如下：

    ```xml
    <!-- REST -->  
    <bean class="org.springframework.web.servlet.view.ContentNegotiatingViewResolver">  
            <property name="order" value="1" />  
            <property name="contentNegotiationManager">  
                <bean class="org.springframework.web.accept.ContentNegotiationManager">  
                    <constructor-arg>  
                        <bean class="org.springframework.web.accept.PathExtensionContentNegotiationStrategy">  
                            <constructor-arg>  
                                <map>  
                                    <entry key="json" value="application/json"/>  
                                    <entry key="xml" value="application/xml"/>  
                                </map>  
                            </constructor-arg>  
                        </bean>  
                    </constructor-arg>  
                </bean>  
            </property>  
      
      
            <property name="defaultViews">  
                <list>  
                    <!-- JSON View -->  
                    <bean class="org.springframework.web.servlet.view.json.MappingJacksonJsonView" />  
      
                    <!-- XML View -->  
                    <bean class="org.springframework.web.servlet.view.xml.MarshallingView">  
                        <constructor-arg>  
                            <bean class="org.springframework.oxm.jaxb.Jaxb2Marshaller">  
                                <property name="packagesToScan">  
                                    <list>  
                                        <value>documentLoader.domain</value>  
                                    </list>  
                                </property>  
                            </bean>  
                        </constructor-arg>  
                    </bean>  
                </list>  
            </property>  
    </bean>  
      
        <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">  
            <property name="viewClass" value="org.springframework.web.servlet.view.JstlView" />  
            <property name="prefix" value="/WEB-INF/views/" />  
            <property name="suffix" value=".jsp" />  
        </bean>  
    ```

    

  - BeanNameViewResolver

    Simple implementation of ViewResolver that interprets a view name as bean name in the current application context;（在spring上下文将视图名称解析成配置中的Bean;其中Bean.ID=viewName）

  - **InternalResourceViewResolver**

    Convenient subclass of [`UrlBasedViewResolver`](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/servlet/view/UrlBasedViewResolver.html) that supports [`InternalResourceView`](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/servlet/view/InternalResourceView.html) (i.e. Servlets and JSPs) and subclasses such as [`JstlView`](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/servlet/view/JstlView.html).

    将视图解析成web应用的内部资源(一般为JSP)

    ```xml
    <bean id="defaultViewResolver"
    		class="org.springframework.web.servlet.view.InternalResourceViewResolver"
    		p:order="3">
    		<property name="viewClass"
    			value="org.springframework.web.servlet.view.JstlView" />
    		<property name="contentType" value="text/html" />
    		<property name="prefix" value="/webpage/" />
    		<property name="suffix" value=".jsp" />
    		<property name="redirectHttp10Compatible" value="false" />
    	</bean>
    ```

    

  - FreeMarkerViewResolver

    A `ViewResolver` for resolving [`FreeMarkerView`](https://docs.spring.io/spring-framework/docs/5.0.2.RELEASE/javadoc-api/org/springframework/web/reactive/result/view/freemarker/FreeMarkerView.html) instances, i.e. FreeMarker templates and custom subclasses of it.

    将视图解析成freemarker模板

- Support for serving static resources, including support for WebJars (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-developing-web-applications.html#boot-features-spring-mvc-static-content))).

  - **静态资源**、**webjars**

    ```java
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
                if (!this.resourceProperties.isAddMappings()) {
                    logger.debug("Default resource handling disabled");
                } else {
                    Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
                    CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
                    if (!registry.hasMappingForPattern("/webjars/**")) {
                        this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{"/webjars/**"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"}).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
                    }
    
                    String staticPathPattern = this.mvcProperties.getStaticPathPattern();
                    if (!registry.hasMappingForPattern(staticPathPattern)) {
                        this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{staticPathPattern}).addResourceLocations(getResourceLocations(this.resourceProperties.getStaticLocations())).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
                    }
    
                }
            }
    ```

    

- 自动注册 `Converter`, `GenericConverter`, and `Formatter` beans.

  - 转换器：请求的字符串转成Bean属性的int、long、boolean、...

    ![自定义转换器处理特殊数据](/home/xixi/Pictures/springMVC/Converter1.png)

    ![Converter配置](/home/xixi/Pictures/springMVC/Converter配置.png)格式化器：用法:与Converter用法类似，只是源类型必须为String

    ​		eg: 2019-07-09====>Date 格式化为指定格式的日期对象
    
    

- Support for `HttpMessageConverters` (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-developing-web-applications.html#boot-features-spring-mvc-message-converters)).

  - 将添加了@RequestBody、@ResponseBody注解的请求报文和响应报文解析成json格式;

    ![HttpMessageConverter运行原理](/home/xixi/Pictures/springMVC/HttpMessageConverter运行原理.png)

- Automatic registration of `MessageCodesResolver` (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-developing-web-applications.html#boot-features-spring-message-codes)).

- Static `index.html` support. 

  - 支持静态首页

    ```java
    @Bean
    public WelcomePageHandlerMapping welcomePageHandlerMapping(ApplicationContext applicationContext) {
                return new WelcomePageHandlerMapping(new TemplateAvailabilityProviders(applicationContext), applicationContext, this.getWelcomePage(), this.mvcProperties.getStaticPathPattern());
    }
    ```

    

- Custom `Favicon` support (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-developing-web-applications.html#boot-features-spring-mvc-favicon)).

  - 定制喜欢的图标

    ```java
     	@Configuration
        @ConditionalOnProperty(
            value = {"spring.mvc.favicon.enabled"},
            matchIfMissing = true
        )
        public static class FaviconConfiguration implements ResourceLoaderAware {
    ```

    

- Automatic use of a `ConfigurableWebBindingInitializer` bean (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-developing-web-applications.html#boot-features-spring-mvc-web-binding-initializer)).

  - 主要作用就是 初始化WebDataBinder；将请求的参数转化为对应的JavaBean，并且会结合上面的类型、格式转换一起使用。

    ```java
    // WebMvcAutoConfiguration MVC自动配置类中的方法
    protected ConfigurableWebBindingInitializer getConfigurableWebBindingInitializer() {
                try {
                    return (ConfigurableWebBindingInitializer)this.beanFactory.getBean(ConfigurableWebBindingInitializer.class);
                } catch (NoSuchBeanDefinitionException var2) {
                    return super.getConfigurableWebBindingInitializer();
                }
            }
    ```

    可以发现`ConfigurableWebBindingInitializer`是从容器（`beanFactory`）中获取到的，所以我们可以配置一个ConfigurableWebBindingInitializer来替换默认的，只需要在容器中添加一个我们自定义的转换器即可。****

**org.springframework.boot.autoconfigure.web**：web所有自动场景;

If you want to keep Spring Boot MVC features and you want to add additional [MVC configuration](https://docs.spring.io/spring/docs/5.1.8.RELEASE/spring-framework-reference/web.html#mvc) (interceptors, formatters, view controllers, and other features), you can add your own `@Configuration` class of type `WebMvcConfigurer` but **without** `@EnableWebMvc`.



If you wish to provide custom instances of `RequestMappingHandlerMapping`, `RequestMappingHandlerAdapter`, or `ExceptionHandlerExceptionResolver`, you can declare a `WebMvcRegistrationsAdapter` instance to provide such components.



If you want to take complete control of Spring MVC, you can add your own `@Configuration` annotated with `@EnableWebMvc`.

#### 2、扩展SpringMVC

```xml
  <mvc:view-controller path="/hello" view-name="success"/>
  <mvc:interceptors>
     <mvc:interceptor>
     	<mvc:mapping path="/hello"/>
        <bean></bean>
     </mvc:interceptor>
  </mvc:interceptors>
```

**编写一个配置类(@Configuration ) ，而且是WebMvcConfigurer类型;不能标注@EnableWebMvc**

```java
/**
 *  <br>
 * 〈自定义MVC的Configuiration〉
 * 使用WebMvcConfigurer可以扩展SpringMVC的功能
 * @author xixi
 * @create 19-7-10
 * @since 1.0.0
 */
@Configuration
public class MyWebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 浏览器发送 /test 请求来到 succes
        registry.addViewController("/test").setViewName("success");
    }
}
```

原理：

​	1）、WebMvcAutoConfiguration是SpringMVC的自动配置类;

​	2）、在做其他自动配置时会导入：@Import(**EnableWebMvcConfiguration**.class)

```java
@Configuration
public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration {
    // 继承父类DelegatingWebMvcConfiguration属性和方法
    private final WebMvcConfigurerComposite configurers = 
         new WebMvcConfigurerComposite();
	// 从容器中获取所有的WebMvcConfigurer
    @Autowired(required = false)
    public void setConfigurers(List<WebMvcConfigurer> configurers) {
        if (!CollectionUtils.isEmpty(configurers)) {
            this.configurers.addWebMvcConfigurers(configurers);
            	// 其中视图映射的具体实现
              	//protected void addViewControllers(ViewControllerRegistry registry) {
                //    this.configurers.addViewControllers(registry);
                //}
        }
    }
    
    // ...
}    
```

​	3）、容器中所有WebMvcConfigurer都会一起起作用;

​	4）、我们的配置类也会被调用;

​	效果：SpringMVC的自动配置和我们的扩展配置都会起作用;

#### 3、全面接管SpringMVC

SpringBoot对SpringMVC的自动配置不需要了，所有都是我们自己配; SpringBoot默认配置全部失效;

**我们只需要在配置类中添加@EnableWebMvc注解即可;**

```java
/**
 *  <br>
 * 〈自定义MVC的Configuiration〉
 * 使用WebMvcConfigurer可以扩展SpringMVC的功能
 * @author xixi
 * @create 19-7-10
 * @since 1.0.0
 */
@EnableWebMvc
@Configuration
public class MyWebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 浏览器发送 /test 请求来到 succes
        registry.addViewController("/test").setViewName("success");
    }
}
```

原理：

​	为什么添加@EnableWebMvc 自动配置就失效？

​	1）、@EnableWebMvc核心

```java
--签名
@Import({DelegatingWebMvcConfiguration.class})
public @interface EnableWebMvc {
}
```

​	2）、需要导入的类DelegatingWebMvcConfiguration

```java
@Configuration
public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {
```

​	3）、SpringMVC自动配置类

```java
@Configuration
@ConditionalOnWebApplication(type = Type.SERVLET)
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class })
// 容器中没有这个组件时，这个自动配置类才生效
@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
@AutoConfigureAfter({ DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class,
		ValidationAutoConfiguration.class })
public class WebMvcAutoConfiguration {
```

​	4）、@EnableWebMvc将WebMvcConfigurationSupport导入进来了;

​	5）、导入的WebMvcConfigurationSupport只是SpringMVC最基本组件;

### 5、如何修改SpringBoot的默认配置	

模式：

​	1）、SpringBoot在自动配置很多组件的时候，先看容器中有没有用户自己配置的(@Bean、@Component)如果有就用用户配置的，如果没有，才自动配置;如果有些组件可以有多个(ViewResolver)将用户配置的和自己默认的组合起来;

​	2）、在SpringBoot会有非常的xxAutoConfiguration帮助我们扩展各种功能的;

​	3）、在SpringBoot中会有很多的xxCustomizer帮助我们进行定制配置;

### 6、RestFulCRUD

#### 1）、默认访问首页

- 配置视图映射，让用户默认访问login.html登录页面;
- 替换themreaf模板引擎能渲染的标签:th:xx
  - 好处：可以给路径自动添加项目context-path：[/crud/asserts/css/signin.css](http://localhost:8080/crud/asserts/css/signin.css)

#### 2）、国际化

以前操作：

​	1）、编写国际化配置文件;

​	2）、使用ResourceBundleMessageSource管理国际化资源文件;

​	3）、在页面使用fmt:message取出国际化内容;

SpringBoot实现步骤：

​	1）、编写国际化配置文件，抽取页面需要显示的国际化消息

​	![1563709365774](/home/xixi/.config/Typora/typora-user-images/1563709365774.png)

​	2）、SpringBoot自动配置好了管理国际资源文件组件;MessageSourceAutoConfiguration

```java
@Configuration
@ConditionalOnMissingBean(value = MessageSource.class, search = SearchStrategy.CURRENT)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Conditional(ResourceBundleCondition.class)
@EnableConfigurationProperties
public class MessageSourceAutoConfiguration {
    // String basename = context.getEnvironment().getProperty("spring.messages.basename", "messages");
    
    @Bean
	public MessageSource messageSource(MessageSourceProperties properties) {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		if (StringUtils.hasText(properties.getBasename())) {
            // 设置国际化资源文件的基础名(去掉语言国家代码的)
			messageSource.setBasenames(StringUtils
					.commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(properties.getBasename())));
		}
		if (properties.getEncoding() != null) {
			messageSource.setDefaultEncoding(properties.getEncoding().name());
		}
		messageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
		Duration cacheDuration = properties.getCacheDuration();
		if (cacheDuration != null) {
			messageSource.setCacheMillis(cacheDuration.toMillis());
		}
		messageSource.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
		messageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());
		return messageSource;
	}
 
    // ...
}    
```

​	3）、去页面获取国际化的值;

​		A、解决properties文件乱码问题：

![1563718676480](/home/xixi/.config/Typora/typora-user-images/1563718676480.png)

​		B、国际化实现实例：登录页面的国际化

```html
<!--
	themreaf:标签语法
	1）凡是所有标签都能使用th:xx-替换;
	2）国际化的标签使用#{}来从配置文件中取值;
	3）超链接取值使用@{/-从项目根目录下找};
-->
<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
	<meta name="generator" content="Jekyll v3.8.5">
	<title>Signin Template · Bootstrap</title>

	<link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/sign-in/">

	<!-- Bootstrap core CSS -->
	<link href="/docs/4.3/dist/css/bootstrap.min.css" th:href="@{/webjars/bootstrap/4.3.1/css/bootstrap.css}" rel="stylesheet">


	<style>
		.bd-placeholder-img {
			font-size: 1.125rem;
			text-anchor: middle;
			-webkit-user-select: none;
			-moz-user-select: none;
			-ms-user-select: none;
			user-select: none;
		}

		@media (min-width: 768px) {
			.bd-placeholder-img-lg {
				font-size: 3.5rem;
			}
		}
	</style>
	<!-- Custom styles for this template -->
	<link href="signin.css" th:href="@{/asserts/css/signin.css}" rel="stylesheet">
</head>
<body class="text-center">
<form class="form-signin">
	<img class="mb-4" src="/docs/4.3/assets/brand/bootstrap-solid.svg" th:src="@{/asserts/img/bootstrap-solid.svg}" alt="" width="72" height="72">
	<h1 class="h3 mb-3 font-weight-normal" th:text="#{login.tip}">Please sign in</h1>
	<label for="inputEmail" class="sr-only" th:text="#{login.email}">Email address</label>
	<input type="email" id="inputEmail" class="form-control" placeholder="Email address" th:placeholder="#{login.email}" required autofocus>
	<label for="inputPassword" class="sr-only" th:text="#{login.password}">Password</label>
	<input type="password" id="inputPassword" class="form-control" placeholder="Password" th:placeholder="#{login.password}" required>
	<div class="checkbox mb-3">
		<label>
			<input type="checkbox" value="remember-me"> [[#{login.remember}]]
		</label>
	</div>
	<button class="btn btn-lg btn-primary btn-block" type="submit" th:text="#{login.btn}">Sign in</button>
	<p class="mt-5 mb-3 text-muted">&copy; 2017-2019</p>
	<a class="btn btn-sm">中文</a>
	<a class="btn btn-sm">English</a>
</form>
</body>
</html>
```

效果：根据浏览器语言设置的信息切换国际化;

![1563719282369](/home/xixi/.config/Typora/typora-user-images/1563719282369.png)

4）、需求通过外部按钮来实现中英文的切换;

- 原理：

​		国际化Locale（区域信息对象）;LocaleResolver（获取区域信息对象）;

```java
// 区域信息解析器
@Bean
@ConditionalOnMissingBean
@ConditionalOnProperty(prefix = "spring.mvc", name = "locale")
public LocaleResolver localeResolver() {
    if (this.mvcProperties.getLocaleResolver() == WebMvcProperties.LocaleResolver.FIXED) 
    {
				return new FixedLocaleResolver(this.mvcProperties.getLocale());
	}
	AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
	localeResolver.setDefaultLocale(this.mvcProperties.getLocale());
	return localeResolver;
}
// 默认根据请求头带来的区域信息获取Locale进行国际化;
```

- 浏览器请求头信息

![1563719945305](/home/xixi/.config/Typora/typora-user-images/1563719945305.png)

- 解析方法：

  ```java
  public class AcceptHeaderLocaleResolver implements LocaleResolver {
      @Nullable
      private Locale defaultLocale;
      // 解析Locale区域信息对象
      public Locale resolveLocale(HttpServletRequest request) {
              Locale defaultLocale = this.getDefaultLocale();
              if (defaultLocale != null && request.getHeader("Accept-Language") == null) {
                  return defaultLocale;
              } else {
                  Locale requestLocale = request.getLocale();
                  List<Locale> supportedLocales = this.getSupportedLocales();
                  if (!supportedLocales.isEmpty() && !supportedLocales.contains(requestLocale)) {
                      Locale supportedLocale = this.findSupportedLocale(request, supportedLocales);
                      if (supportedLocale != null) {
                          return supportedLocale;
                      } else {
                          return defaultLocale != null ? defaultLocale : requestLocale;
                      }
                  } else {
                      return requestLocale;
                  }
              }
          }
      
      // ...
  }    
  ```


#### 3）、登录

​		3.1）开发期间模板引擎页面修改以后，要实时生效

​		A、禁用模板引擎的缓存

```properties
# 禁止thymeleaf模板引擎缓存机制
spring.thymeleaf.cache=false
```

​		B、页面修改完成以后需重新编译;

​	3.2）登录错误消息提示

```html
<p style="color: red;" th:text="${msg}" th:if="${not #strings.isEmpty(msg)}"></p>
```

​	3.3）SpringBoot2.xx版本对应Spring5.xx版本，静态资源也会执行自定义的拦截器，因此在配置拦截器时候需指定排除静态资源的访问路径，配置如下;

```java
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);
    
    @Bean // 将组件注册到容器中
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                logger.info("add view mapping");
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                logger.info("add interceptors");

                // 添加拦截器拦截路径模式
                // 静态资源: .css .js .png ... SpringBoot 1.xx版本已经做好了静态资源映射;
                // SpringBoot 2.xx需要自己配置排除静态资源规则
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html", "/", "/user/login", "/asserts/**", "/webjars/**");
            }
        };
        return webMvcConfigurer;
    }
    
  // ...
  
}
```

- 本文中使用的具体版本

  - spring-boot-2.1.6.RELEASE（相对应的spring版本是spring-webmvc-5.1.8.RELEASE）

- 关于配置类，在spring boot 2.x已经改为最低支持jdk8版本，而jdk8中的接口允许有默认实现，所以已经废弃掉WebMvcConfigurerAdapter适配类，而改为直接实现WebMvcConfigurer接口;

- 拦截器拦截静态资源的原因;

  1）、静态资源路径的注册

  ```java
  // springMVC自动配置类：WebMvcAutoConfiguration，它里面的内部类WebMvcAutoConfigurationAdapter：
  
  // 定义为嵌套配置，以确保在不在类路径上时不读取WebMvcConfigurer
  @Configuration
  // 引入EnableWebMvcConfiguration对象到容器中
  @Import(EnableWebMvcConfiguration.class)
  @EnableConfigurationProperties({ WebMvcProperties.class, ResourceProperties.class })
  @Order(0)
  public static class WebMvcAutoConfigurationAdapter implements WebMvcConfigurer, ResourceLoaderAware {
      @Override
      public void addResourceHandlers(ResourceHandlerRegistry registry) {
          if (!this.resourceProperties.isAddMappings()) {
              logger.debug("Default resource handling disabled");
              return;
          }
          Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
          CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
          // 是够有注册/webjars/**
          if (!registry.hasMappingForPattern("/webjars/**")) {
              customizeResourceHandlerRegistration(registry.addResourceHandler("/webjars/**")
                      .addResourceLocations("classpath:/META-INF/resources/webjars/")
                      .setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl));
          }
          String staticPathPattern = this.mvcProperties.getStaticPathPattern();
          // 是否有注册静态资源路径
          if (!registry.hasMappingForPattern(staticPathPattern)) {
              customizeResourceHandlerRegistration(registry.addResourceHandler(staticPathPattern)
                      .addResourceLocations(getResourceLocations(this.resourceProperties.getStaticLocations()))
                      .setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl));
          }
      }
  ```

  首先，它将`/webjars/**`注册，其次将默认的静态资源路径注册;在代码第24行追踪到staticPathPattern就等于/**，再看第28行，追踪对应的代码如下：

  ```java
  @ConfigurationProperties(prefix = "spring.mvc")
  public class WebMvcProperties {
     /**
  	 * Path pattern used for static resources.
  	 */
  	private String staticPathPattern = "/**";
  ```

  

  ```java
  @ConfigurationProperties(prefix = "spring.resources", ignoreUnknownFields = false)
  public class ResourceProperties {
  
  	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
  			"classpath:/resources/", "classpath:/static/", "classpath:/public/" };
     /**
  	 * Locations of static resources. Defaults to classpath:[/META-INF/resources/,
  	 * /resources/, /static/, /public/].
  	 */
  	private String[] staticLocations = CLASSPATH_RESOURCE_LOCATIONS;
  
  	public String[] getStaticLocations() {
  		return this.staticLocations;
  	}
  ```

  2）、静态资源的处理器映射器

  上面讲到WebMvcAutoConfigurationAdapter 类，它使用Import注解引入EnableWebMvcConfiguration对象到容器中;

  EnableWebMvcConfiguration类也是WebMvcAutoConfiguration的一个内部类;

  ```java
  /**
    * Configuration equivalent to {@code @EnableWebMvc}.
    */
  @Configuration
  public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration { 
  ```

  ```java
  @Configuration
  public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {
  ```

  直接查看EnableWebMvcConfiguration类的间接父类：WebMvcConfigurationSupport

  ```java
  /**
    * This is the main class providing the configuration behind the MVC Java config.
    * This class registers the following {@link HandlerMapping HandlerMappings}
    *		{@link RequestMappingHandlerMapping}
    * 	ordered at 0 for mapping requests to annotated controller methods.
    *		{@link HandlerMapping}
    *		ordered at 1 to map URL paths directly to view names.
    *		{@link BeanNameUrlHandlerMapping}
    *		ordered at 2 to map URL paths to controller bean names.
    *		{@link HandlerMapping}
    *		ordered at {@code Integer.MAX_VALUE-1} to serve static resource requests.
    *		{@link HandlerMapping}
    *		ordered at {@code Integer.MAX_VALUE} to forward requests to the 
    *		default servlet.
    * Registers these {@link HandlerAdapter HandlerAdapters}:
    *  	{@link RequestMappingHandlerAdapter}
    *  	processing requests with annotated controller methods.
    *  	{@link HttpRequestHandlerAdapter}
    *  	processing requests with {@link HttpRequestHandler HttpRequestHandlers}.
    *  	{@link SimpleControllerHandlerAdapter}
    *  	processing requests with interface-based {@link Controller Controllers}.
    */
  public class WebMvcConfigurationSupport implements ApplicationContextAware, ServletContextAware {
      /**
  	 * Return a handler mapping ordered at Integer.MAX_VALUE-1 with mapped
  	 * resource handlers. To configure resource handling, override
  	 * {@link #addResourceHandlers}.
  	 * 返回一个优先级为Integer.MAX_VALUE-1，映射静态资源处理器的HandlerMapping
  	 */
  	@Bean
  	@Nullable
  	public HandlerMapping resourceHandlerMapping() {
  		Assert.state(this.applicationContext != null, "No ApplicationContext set");
  		Assert.state(this.servletContext != null, "No ServletContext set");
  
  		ResourceHandlerRegistry registry = new ResourceHandlerRegistry(this.applicationContext,
  				this.servletContext, mvcContentNegotiationManager(), mvcUrlPathHelper());
          // 对静态资源路径进行注册
  		addResourceHandlers(registry);
          // 返回映射有静态资源路径的HandlerMapping
  		AbstractHandlerMapping handlerMapping = registry.getHandlerMapping();
  		if (handlerMapping == null) {
  			return null;
  		}
  		handlerMapping.setPathMatcher(mvcPathMatcher());
  		handlerMapping.setUrlPathHelper(mvcUrlPathHelper());
          // 添加所有拦截器，包括用户自定义的;
  		handlerMapping.setInterceptors(getInterceptors());
  		handlerMapping.setCorsConfigurations(getCorsConfigurations());
  		return handlerMapping;
  	}
  ```

  分析：类中有一个方法resourceHandlerMapping()，它返回一个专门处理静态资源路径的处理器映射器;

  ​	见39行代码，其实最终它就是调用类WebMvcAutoConfigurationAdapter中addResourceHandlers方法，将资源路径注册;

  ​	见41行代码，查看该方法发现其将注册的资源路径形成映射放在HandlerMapping中，并返回;

  ​	见48行代码，将所有拦截器(包括我们自定义的拦截器)添加到HandlerMapping，这就是为什么我们自定义拦截器会拦截静态资源;

  ​	SpringBoot1.xx版本中的处理是这样的代码：

  ```java
  handlerMapping.setInterceptors(new ResourceUrlProviderExposingInterceptor(mvcResourceUrlProvider()));
  ```

  并没有添加所有拦截器到该`handlerMapping`，这也是springboot1.xx不拦截静态资源的原因。

  3）、解决方法

  ​	设置拦截路径时设置excludePathPatterns，例如：

  ```java
  registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                          .excludePathPatterns("/index.html", "/", "/user/login", "/asserts/**", "/webjars/**");
  ```

  

#### 4）、拦截器进行登录检查

- 自定义拦截器

  ```java
  /**
   *  <br>
   * 〈自定义拦截器〉
   * 目的：检查登录请求
   * @author xixi
   * @create 19-7-22
   * @since 1.0.0
   */
  public class LoginHandlerInterceptor implements HandlerInterceptor {
  
      private static final Logger logger = LoggerFactory.getLogger(LoginHandlerInterceptor.class);
  
      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
  
          Object loginUser = request.getSession().getAttribute("loginUser");
          if(StringUtils.isEmpty(loginUser)){
              logger.info("拦截的请求URI：" + request.getRequestURI());
              // 没有登录,重定向的登录页面
              request.setAttribute("msg", "没有权限访问");
              request.getRequestDispatcher("/index.html").forward(request, response);
              return false;
          }else{
              return true;
          }
      }
  }
  ```

  

- 把自定义的拦截器添加的MVC自动配置中，并设置拦截规则

  ```java
   @Override
              public void addInterceptors(InterceptorRegistry registry) {
                  logger.info("add interceptors");
  
                  // 添加拦截器拦截路径模式
                  // 静态资源: .css .js .png ... SpringBoot 1.xx版本已经做好了静态资源映射;
                  // SpringBoot 2.xx需要自己配置排除静态资源规则
                  registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                          .excludePathPatterns("/index.html", "/", "/user/login", "/asserts/**", "/webjars/**");
              }
  ```

#### 5）、CRUD-员工列表

实验要求：

1）、RestfulCRUD：CRUD满足Rest风格;

URI：/资源名称/资源标识	HTTP请求方式区分对资源CRUD操作

|      | 普通CRUD(uri来区分操作) | RestfulCRUD  |
| ---- | ----------------------- | ------------ |
| 查询 | getEmp                  | emp---GET    |
| 添加 | addEmp?xx               | emp---POST   |
| 修改 | updateEmp?id=xx         | emp---PUT    |
| 删除 | deleteEmp?id=xx         | emp---DELETE |

2）、实验的请求架构;

|                            | 请求URI  | 请求方式 |
| -------------------------- | -------- | -------- |
| 查询所有员工               | emps     | GET      |
| 查询某个员工(来到修改页面) | emp/{id} | GET      |
| 来到添加页面               | emp      | GET      |
| 添加员工                   | emp      | POST     |
| 修改员工                   | emp      | PUT      |
| 删除员工                   | emp      | DELETE   |

3）、员工列表

**thymeleaf公共页面元素抽取 th:insert and th:replace (and th:include)区别**

```html
1.抽取的片段
<footer th:fragment="copy">
&copy; 2011 The Good Thymes Virtual Grocery
</footer>

2.抽取
<div th:insert="footer :: copy"></div>
<div th:replace="footer :: copy"></div>
<div th:include="footer :: copy"></div>

3.效果
<div>
    <footer>
    &copy; 2011 The Good Thymes Virtual Grocery
    </footer>
</div>
<footer>
&copy; 2011 The Good Thymes Virtual Grocery
</footer>
<div>
&copy; 2011 The Good Thymes Virtual Grocery
</div>
```

总结：

- th:insert ：将公共片段插入到指定元素内;
- th:replace：将声明引入的元素替换为公共片段;
- th:include：将被引入的片段的内容包含进这个标签中;
- 当使用th:insert 等属性进行引入，可以不用写～{};
- 行内写法可以加上：[[~{}]]、[(~{})];

#### 6）、总结，thymeleaf用法

6.1）、公共片段的抽取和插入，th:replace，插入公共片段，并且支持thymeleaf的渲染规则，要添加相对于template的相对路径

```html
<!--thymeleaf 公共片段抽取并插入-->
<div th:replace="commons/bar::topbar"></div>
// 从template目录找/template/commmons/bar.html模板，查询th:fragment="topbar"的片段
```

6.2）、可参数化的片段签名，

```html
<!-- list.html 下抽取的插入的公共片段如下 -->
<div th:replace="commons/bar::#sidebar(activeUri='emps')"></div>
<!-- Dashboard.html下抽取的公共片段如下	-->
<div th:replace="commons/bar::#sidebar(activeUri='main.html')"></div>

<!--	如下是/template/commons/bar.html下部分代码	-->
<ul class="nav flex-column">
    <li class="nav-item">
        <a class="nav-link active"
           th:class="${activeUri == 'main.html' ? 'nav-link active' : 'nav-link'}"
           href="#" th:href="@{/main.html}" >
            <span data-feather="home"></span>
            Dashboard <span class="sr-only">(current)</span>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link"
           th:class="${activeUri == 'emps' ? 'nav-link active' : 'nav-link'}"
           href="#" th:href="@{/emps}">
            <span data-feather="users"></span>
            员工管理
        </a>
    </li>
```

6.3）、使用th:attr=""，来设置任何属性的值;以及使用th:value=""，来设置已有属性的值;

```html
// 设置已有属性的值;
<a class="btn btn-sm btn-primary" th:href="@{/emp/} + ${emp.id}">编辑</a>
// 设置任意属性deleteUri的值;
<a class="btn btn-sm btn-danger deleteBtn" th:attr="deleteUri='/emp/' + ${emp.id}">删除</a>
```

### 7、错误处理机制

#### 1）、SpringBoot默认的错误处理机制

默认效果，

​	返回一个默认的错误页面;

​	浏览器上返回的是空白页面如下：

![1564569733305](/home/xixi/.config/Typora/typora-user-images/1564569733305.png)

​	其对应的请求头参数如下：

![1564570280182](/home/xixi/.config/Typora/typora-user-images/1564570280182.png)

其他工具返返回的是Json字符串如下：

![1564569895747](/home/xixi/.config/Typora/typora-user-images/1564569895747.png)

​	其对应的请求头参数如下：

![1564570141296](/home/xixi/.config/Typora/typora-user-images/1564570141296.png)



原理：

​	可以参照ErrorMvcAutoConfiguration，错误处理的自动配置;

​	给容器添加的组件？

​		1、DefaultErrorAttributes，获取错误响应所需属性值;

```java
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DefaultErrorAttributes implements ErrorAttributes, HandlerExceptionResolver, Ordered {
    // 获取错误响应所需的属性值
    @Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
		Map<String, Object> errorAttributes = new LinkedHashMap<>();
		errorAttributes.put("timestamp", new Date());
		addStatus(errorAttributes, webRequest);
		addErrorDetails(errorAttributes, webRequest, includeStackTrace);
		addPath(errorAttributes, webRequest);
		return errorAttributes;
	}
```



​		2、BasicErrorController，默认处理/error请求;

```java
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class BasicErrorController extends AbstractErrorController {
    // 响应错误提示空白页面
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)// "text/html"
	public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
		HttpStatus status = getStatus(request);
		Map<String, Object> model = Collections
				.unmodifiableMap(getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML)));
		response.setStatus(status.value());
		ModelAndView modelAndView = resolveErrorView(request, response, status, model);
		return (modelAndView != null) ? modelAndView : new ModelAndView("error", model);
	}
	// 响应错误提示Json字符串
	@RequestMapping
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
		HttpStatus status = getStatus(request);
		return new ResponseEntity<>(body, status);
	}

}    
```



​		3、ErrorPageCustomizer，当发生错误后制定跳转的请求，调度/error;

```java
private static class ErrorPageCustomizer implements ErrorPageRegistrar, Ordered {
    private final ServerProperties properties;

    private final DispatcherServletPath dispatcherServletPath;

    // 注册错误跳转页面的
    @Override
    public void registerErrorPages(ErrorPageRegistry errorPageRegistry) {
        ErrorPage errorPage = new ErrorPage(
            this.dispatcherServletPath.getRelativePath(this.properties.getError().getPath()));
        errorPageRegistry.addErrorPages(errorPage);
    }
    
    // 其中，getPath();方法如下，默认调度路径为/error，处理/error请求的控制器BasicErrorController
   /**
	 * Path of the error controller.
	 */
	@Value("${error.path:/error}")
	private String path = "/error";
```



​		4、DefaultErrorViewResolver，错误页面视图解析器，解析error视图;

```java
public class DefaultErrorViewResolver implements ErrorViewResolver, Ordered {   
	@Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        ModelAndView modelAndView = resolve(String.valueOf(status.value()), model);
        if (modelAndView == null && SERIES_VIEWS.containsKey(status.series())) {
            modelAndView = resolve(SERIES_VIEWS.get(status.series()), model);
        }
        return modelAndView;
    }
    
	// 解析error视图的具体方法
	private ModelAndView resolve(String viewName, Map<String, Object> model) {
		String errorViewName = "error/" + viewName;
        // 分有模板引擎的解析，和没有模板引擎的解析;
		TemplateAvailabilityProvider provider = this.templateAvailabilityProviders.getProvider(errorViewName,
				this.applicationContext);
		if (provider != null) {
            // 返回（/error + 状态码）视图;
			return new ModelAndView(errorViewName, model);
		}
        // 无模板引擎的解析又分是否存在在静态资源上
		return resolveResource(errorViewName, model);
	}
    
    // 无模板引擎的解析
    private ModelAndView resolveResource(String viewName, Map<String, Object> model) {
		for (String location : this.resourceProperties.getStaticLocations()) {
			try {
				Resource resource = this.applicationContext.getResource(location);
				resource = resource.createRelative(viewName + ".html");
				if (resource.exists()) {
                    // 存在于静态资源上
					return new ModelAndView(new HtmlResourceView(resource), model);
				}
			}
			catch (Exception ex) {
			}
		}
        // 不存在于静态资源上的返回null
		return null;
	}
}    
```

步骤：

​	一但系统出现4xx或者5xx之类的错误，**ErrorPageCustomizer**就会生效（定制一个错误的响应），默认就会发送一个/error请求，这样就会被**BasicErrorController**处理;

1）响应页面，由**DefaultErrorViewResolver**错误视图解析器解析得到，具体方法如下，

```java
// 解析error视图的具体方法
private ModelAndView resolve(String viewName, Map<String, Object> model) {
    String errorViewName = "error/" + viewName;
    // 分有模板引擎的解析，和没有模板引擎的解析;
    TemplateAvailabilityProvider provider = this.templateAvailabilityProviders.getProvider(errorViewName,
                                                                                           this.applicationContext);
    if (provider != null) {
        // 返回（/error + 状态码）视图;
        return new ModelAndView(errorViewName, model);
    }
    // 无模板引擎的解析又分是否存在在静态资源上
    return resolveResource(errorViewName, model);
}

// 无模板引擎的解析
private ModelAndView resolveResource(String viewName, Map<String, Object> model) {
    for (String location : this.resourceProperties.getStaticLocations()) {
        try {
            Resource resource = this.applicationContext.getResource(location);
            resource = resource.createRelative(viewName + ".html");
            if (resource.exists()) {
                // 存在于静态资源上
                return new ModelAndView(new HtmlResourceView(resource), model);
            }
        }
        catch (Exception ex) {
        }
    }
    // 不存在于静态资源上的返回null
    return null;
}
```



#### 2）、如何定制错误响应？

​	2.1）、如何定制错误页面？

​		**1）、有模板引擎的情况下；error/状态码;** 

​		【将错误页面命名为  错误状态码.html 放在模板引擎文件夹里面的 error文件夹下】，发生此状态码的错误就会来到  对应的页面；

 		我们可以使用4xx和5xx作为错误页面的文件名来匹配这种类型的所有错误，精确优先（优先寻找精确的状态码.html）；		

​			页面能获取的信息；

​				timestamp：时间戳

​				status：状态码

​				error：错误提示

​				exception：异常对象

​				message：异常消息

​				errors：JSR303数据校验的错误都在这里

​			2）、没有模板引擎（模板引擎找不到这个错误页面），静态资源文件夹下找；

​			3）、以上都没有错误页面，就是默认来到SpringBoot默认的错误提示页面；

​	2.2）、如何定制错误返回值？

​		1）、自定义异常处理&返回定制json数据；

```java
/**
 *  <br>
 * 〈自定义异常处理器〉
 *
 * @author xixi
 * @create 19-7-31
 * @since 1.0.0
 */
@ControllerAdvice
public class MyExceptionHandler {

    // 1.浏览器和客户端返回的都是json串
    @ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public Map<String, Object> handlerException(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "user not exist");
        map.put("message", e.getMessage());
        return map;
    }
}
// 缺点是不能自适应，浏览器返回页面，客户端返回json串;
```

​	2）出现错误后转发到/error进行自适应响应;

```java
@ExceptionHandler(UserNotExistException.class)
    public String handlerException(Exception e, HttpServletRequest request) {
        // 传入我们自己的状态码，4.xx,5.xx,否则就不会进入错误页面解析流程
        /**
         * Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
         */
        request.setAttribute("javax.servlet.error.status_code", 500);
        Map<String, Object> map = new HashMap<>();
        map.put("code", "user not exist");
        map.put("message", e.getMessage());

        // 转发到/error请求
        return "forward:/error";
    }
```

​	3）、将我们定制的错误数据携带出去;

​		出现错误以后，会来到请求/error处理的控制器BasicErrorController，响应出去可获取的数据是由getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL))方法获得（是BasicErrorController的父类AbstractErrorController的方法）;

​	1、完全编写一个ErrorController的实现类[或者AbstractErrorController的子类]，放在容器中;

​	2、返回页面的数据，获取直接返回的json数据都是通过AbstractErrorController的errorAttributes.getErrorAttributes(webRequest, includeStackTrace);方法获取的;

​		默认使用DefaultErrorAttributes.getErrorAttributes()，获取的;

自定义ErrorAttributes类，

```java

/**
 *  <br>
 * 〈自定义错误属性提供者类〉
 *
 * @author xixi
 * @create 19-7-31
 * @since 1.0.0
 */
@Component // 放入容器中
public class MyErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        // 追加错误提示消息内容
        errorAttributes.put("company", "huawei");
        // 从请求域中获取异常数据
        Map<String,Object> attribute = (Map<String, Object>) webRequest.getAttribute("ext", 0);
        errorAttributes.put("ext", attribute);
        // 放入自定义的异常消息
        return errorAttributes;
    }
}
```

最终效果：响应是自适应的，可以通过定制ErrorAttributes，来改变需要返回的内容;

![1564578760096](/home/xixi/.config/Typora/typora-user-images/1564578760096.png)

### 8、嵌入式Servlet容器

​	SpringBoot默认使用Tomcat作为Servlet的容器;

![1564586842957](/home/xixi/.config/Typora/typora-user-images/1564586842957.png)

#### 1）、如何定制和修改Servlet的相关配置

​	A、修改与Server有关的配置，ServerProperties

```properties
# 配置项目路径访问前缀
server.servlet.context-path=/crud
# 通用的Servlet容器设置
server.port=8081
# 设置tomcat的配置
server.tomcat.uri-encoding=UTF-8
```

​	B、编写一个**EmbeddedServletContainerCustomizer**，嵌入式Servlet容器定制器，来修改Servlet容器配置;

​		[Spring Boot2.0以上版本EmbeddedServletContainerCustomizer被WebServerFactoryCustomizer替代](https://blog.csdn.net/weixin_33739523/article/details/88251899)

```java
// 在@Configuration注解类中注入一下bean
@Bean
public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
    return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
        @Override
        public void customize(ConfigurableWebServerFactory factory) {
            factory.setPort(8083);
        }
    };
}
```

#### 2）、注册Servlet的三大组件（Servlet、Filter、Listener）

​	由于SpringBoot默认以jar的方式启动嵌入式Servlet容器来开启Web应用，没有web.xml文件;

​	注册三大组件用以下方式：	

| 组件         | 注册方式                        |
| ------------ | ------------------------------- |
| 注册Servlet  | ServletRegistrationBean         |
| 注册Filter   | FilterRegistrationBean          |
| 注册Listener | ServletListenerRegistrationBean |

​	分别实现方式：

```java
@Configuration
public class ServletConfig {

    // 定制Servlet容器配置
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(8083);
            }
        };
    }

    // 注册Servlet三大组件，Servlet、Filter、Lisnener
    @Bean
    public ServletRegistrationBean getServlet(){
        ServletRegistrationBean<MyServlet> myServletServletRegistrationBean = new ServletRegistrationBean<>(new MyServlet(), "/myServlet");
        myServletServletRegistrationBean.setLoadOnStartup(1);
        myServletServletRegistrationBean.setName("myServlet");
        return myServletServletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean getFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new MyFilter());
        filterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/hello", "/myServlet"));
        return filterFilterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean getListener(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new MyListener());
        return servletListenerRegistrationBean;
    }
}
```

SpringBoot帮我们自动配置SpingMVC时，SpringMVC的前端控制器DispatcherServlet，DispatcherServletAutoConfiguration中;

```java
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ConditionalOnWebApplication(type = Type.SERVLET)
@ConditionalOnClass(DispatcherServlet.class)
@AutoConfigureAfter(ServletWebServerFactoryAutoConfiguration.class)
public class DispatcherServletAutoConfiguration {
    @Bean(name = DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME)
    @ConditionalOnBean(value = DispatcherServlet.class, name = DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
    public DispatcherServletRegistrationBean dispatcherServletRegistration(DispatcherServlet dispatcherServlet) {
         //默认拦截： /  所有请求；包静态资源，但是不拦截jsp请求；   /*会拦截jsp
         //可以通过spring.mvc.servlet.path来修改SpringMVC前端控制器默认拦截的请求路径
        DispatcherServletRegistrationBean registration = new DispatcherServletRegistrationBean(dispatcherServlet,
                                                                                               this.webMvcProperties.getServlet().getPath());
        registration.setName(DEFAULT_DISPATCHER_SERVLET_BEAN_NAME);
        registration.setLoadOnStartup(this.webMvcProperties.getServlet().getLoadOnStartup());
        if (this.multipartConfig != null) {
            registration.setMultipartConfig(this.multipartConfig);
        }
        return registration;
    }
```

#### 3）、替换Servlet其他容器

![1564591698512](/home/xixi/.config/Typora/typora-user-images/1564591698512.png)

​	默认支持

​	Tomcat

​	Jetty（使用长连接，比如聊天）

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <groupId>org.springframework.boot</groupId>
        </exclusion>
    </exclusions>
</dependency>

<dependency>
    <artifactId>spring-boot-starter-jetty</artifactId>
    <groupId>org.springframework.boot</groupId>
</dependency>
```

​	Undertow（不支持JSP）

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <groupId>org.springframework.boot</groupId>
        </exclusion>
    </exclusions>
</dependency>

<dependency>
    <artifactId>spring-boot-starter-undertow</artifactId>
    <groupId>org.springframework.boot</groupId>
</dependency>
```

#### 4）、嵌入式Servlet自动配置原理（springboot1.5xx）

EmbeddedServletContainerAutoConfiguration：嵌入式Servlet容器自动配置

```java
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ConditionalOnWebApplication
// 向配置中导入BeanPostProcessorsRegistrar，spring注解版，给容器导入一些组件;
// 导入了EmbeddedServletContainerCustomizerBeanPostProcessor，
// xxBeanPostProcessor：后置处理器，作用，bean初始化前后(刚完成对象创建，还没属性赋值)执行初始化工作;
@Import(BeanPostProcessorsRegistrar.class)
public class EmbeddedServletContainerAutoConfiguration {

	/**
	 * Nested configuration if Tomcat is being used.
	 * 1、判断tomcat是否引入
	 * 2、判断当前容器有没有自定义EmbeddedServletContainerFactory(嵌入式Servlet容器工厂)，作用生产嵌入式Servlet容器;
	 */
	@Configuration
	@ConditionalOnClass({ Servlet.class, Tomcat.class })
	@ConditionalOnMissingBean(value = EmbeddedServletContainerFactory.class, search = SearchStrategy.CURRENT)
	public static class EmbeddedTomcat {

		@Bean
		public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory() {
			return new TomcatEmbeddedServletContainerFactory();
		}

	}
    
   /**
	 * Nested configuration if Jetty is being used.
	 */
	@Configuration
	@ConditionalOnClass({ Servlet.class, Server.class, Loader.class,
			WebAppContext.class })
	@ConditionalOnMissingBean(value = EmbeddedServletContainerFactory.class, search = SearchStrategy.CURRENT)
	public static class EmbeddedJetty {

		@Bean
		public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory() {
			return new JettyEmbeddedServletContainerFactory();
		}

	}

   /**
	 * Nested configuration if Undertow is being used.
	 */
	@Configuration
	@ConditionalOnClass({ Servlet.class, Undertow.class, SslClientAuthMode.class })
	@ConditionalOnMissingBean(value = EmbeddedServletContainerFactory.class, search = SearchStrategy.CURRENT)
	public static class EmbeddedUndertow {

		@Bean
		public UndertowEmbeddedServletContainerFactory undertowEmbeddedServletContainerFactory() {
			return new UndertowEmbeddedServletContainerFactory();
		}

	}
```

1）、EmbeddedServletContainerFactory（嵌入式Servlet容器工厂）

```java
public interface EmbeddedServletContainerFactory {
	// 获取嵌入式Servlet容器
	EmbeddedServletContainer getEmbeddedServletContainer(
			ServletContextInitializer... initializers);
}
```

![1564635686187](/home/xixi/.config/Typora/typora-user-images/1564635686187.png)

2）、EmbeddedServletContainer（嵌入式Servlet容器）

![1564635814629](/home/xixi/.config/Typora/typora-user-images/1564635814629.png)



3）、以TomcatEmbeddedServletContainerFactory为例

```java
public class TomcatEmbeddedServletContainerFactory
		extends AbstractEmbeddedServletContainerFactory implements ResourceLoaderAware {
    	@Override
	public EmbeddedServletContainer getEmbeddedServletContainer(
			ServletContextInitializer... initializers) {
        // 创建tomcat实例
		Tomcat tomcat = new Tomcat();
        
        // 配置tomcat基本环境
		File baseDir = (this.baseDirectory != null ? this.baseDirectory
				: createTempDir("tomcat"));
		tomcat.setBaseDir(baseDir.getAbsolutePath());
		Connector connector = new Connector(this.protocol);
		tomcat.getService().addConnector(connector);
		customizeConnector(connector);
		tomcat.setConnector(connector);
		tomcat.getHost().setAutoDeploy(false);
		configureEngine(tomcat.getEngine());
		for (Connector additionalConnector : this.additionalTomcatConnectors) {
			tomcat.getService().addConnector(additionalConnector);
		}
		prepareContext(tomcat.getHost(), initializers);
        
        // 传入tomcat，返回EmbeddedServletContainer，启动tomcat容器
		return getTomcatEmbeddedServletContainer(tomcat);
	}

```

4）、我们对嵌入式容器的配置修改是怎么生效的？

```text
EmbeddedServletContainerCustomizer
ServerProperties（implements EmbeddedServletContainerCustomizer, EnvironmentAware, Ordered ）
```

EmbeddedServletContainerCustomizer：定制器，帮我们修改了Servlet容器的配置？

怎么修改的，原理？

5）、容器中也导入了EmbeddedServletContainerCustomizerBeanPostProcessor

```java
	// 对象初始化前调用后置处理器方法
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean instanceof ConfigurableEmbeddedServletContainer) {
			postProcessBeforeInitialization((ConfigurableEmbeddedServletContainer) bean);
		}
		return bean;
	}

	// 执行后置处理器的处理操作
	private void postProcessBeforeInitialization(
			ConfigurableEmbeddedServletContainer bean) {
        // 获取所有的定制器，调用每个定制器的customize方法，来给Servlet容器属性赋值;
		for (EmbeddedServletContainerCustomizer customizer : getCustomizers()) {
			customizer.customize(bean);
		}
	}

	// 获取所有定制器方法
    private Collection<EmbeddedServletContainerCustomizer> getCustomizers() {
        if (this.customizers == null) {
            // Look up does not include the parent context
            this.customizers = new ArrayList<EmbeddedServletContainerCustomizer>(
                this.beanFactory
                // 从ioc容器中获取类型为EmbeddedServletContainerCustomizer的定制器;
                // 从而得出定制Servlet容器，只需给容器添加EmbeddedServletContainerCustomizer类型的组建即可;
                .getBeansOfType(EmbeddedServletContainerCustomizer.class,
                                false, false)
                .values());
            Collections.sort(this.customizers, AnnotationAwareOrderComparator.INSTANCE);
            this.customizers = Collections.unmodifiableList(this.customizers);
        }
        return this.customizers;
    }
// ServerProperties也是定制器，同样被代入IOC容器中;
```

![1564637754070](/home/xixi/.config/Typora/typora-user-images/1564637754070.png)

步骤：

​	1）、springboot根据导入的依赖情况，给容器中添加相应的EmbeddedServletContainerFactory(嵌入式Servlet容器工厂)[比如TomcatEmbeddedServletContainerFactory]

​	2）、容器中某个组建要创建对象就会惊动后置处理器EmbeddedServletContainerCustomizerBeanPostProcessor;只要类型是嵌入式Servlet容器工厂，后置处理器就继续工作;

​	3）、后置处理器，从容器中获取所有的EmbeddedServletContainerCustomizer，调用每一个的定制方法;



#### 5）、嵌入式Servlet容器启动原理

什么创建嵌入式的Servlet容器工厂？什么时候获取嵌入式的Servlet容器并启动Tomcat？

获取嵌入式的Servlet容器工厂：

​	1）、springboot应用启动运行run方法

​	2）、refreshContext(context); springboot刷新IOC容器[创建IOC容器对象，并初始化容器，创建容器中的每一个组件];如果是web应用创建的IOC=**AnnotationConfigEmbeddedWebApplicationContext**，否则创建IOC=**AnnotationConfigApplicationContext**

​	3）、refresh(context);刷新刚才创建好的IOC容器;

```java
	// 具体执行刷新的方法如下：
	@Override
	public void refresh() throws BeansException, IllegalStateException {
		synchronized (this.startupShutdownMonitor) {
			// Prepare this context for refreshing.
			prepareRefresh();

			// Tell the subclass to refresh the internal bean factory.
			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

			// Prepare the bean factory for use in this context.
			prepareBeanFactory(beanFactory);

			try {
				// Allows post-processing of the bean factory in context subclasses.
				postProcessBeanFactory(beanFactory);

				// Invoke factory processors registered as beans in the context.
				invokeBeanFactoryPostProcessors(beanFactory);

				// Register bean processors that intercept bean creation.
				registerBeanPostProcessors(beanFactory);

				// Initialize message source for this context.
				initMessageSource();

				// Initialize event multicaster for this context.
				initApplicationEventMulticaster();

				// Initialize other special beans in specific context subclasses.
				onRefresh();

				// Check for listener beans and register them.
				registerListeners();

				// Instantiate all remaining (non-lazy-init) singletons.
				finishBeanFactoryInitialization(beanFactory);

				// Last step: publish corresponding event.
				finishRefresh();
			}

			catch (BeansException ex) {
				if (logger.isWarnEnabled()) {
					logger.warn("Exception encountered during context initialization - " +
							"cancelling refresh attempt: " + ex);
				}

				// Destroy already created singletons to avoid dangling resources.
				destroyBeans();

				// Reset 'active' flag.
				cancelRefresh(ex);

				// Propagate exception to caller.
				throw ex;
			}

			finally {
				// Reset common introspection caches in Spring's core, since we
				// might not ever need metadata for singleton beans anymore...
				resetCommonCaches();
			}
		}
	}
```

4）、onRefresh()方法，web的IOC容器中重写了onRefresh()方法;

5）、web的IOC容器会创建嵌入式的Servlet容器;

```java
public class EmbeddedWebApplicationContext extends GenericWebApplicationContext {	
	@Override
	protected void onRefresh() {
		super.onRefresh();
		try {
            // 创建嵌入式Servlet容器
			createEmbeddedServletContainer();
		}
		catch (Throwable ex) {
			throw new ApplicationContextException("Unable to start embedded container",
					ex);
		}
	}
    
    
	private void createEmbeddedServletContainer() {
		EmbeddedServletContainer localContainer = this.embeddedServletContainer;
		ServletContext localServletContext = getServletContext();
		if (localContainer == null && localServletContext == null) {
            // 创建嵌入式Servlet容器工厂
			EmbeddedServletContainerFactory containerFactory = getEmbeddedServletContainerFactory();
			this.embeddedServletContainer = containerFactory
					.getEmbeddedServletContainer(getSelfInitializer());
		}
		else if (localServletContext != null) {
			try {
				getSelfInitializer().onStartup(localServletContext);
			}
			catch (ServletException ex) {
				throw new ApplicationContextException("Cannot initialize servlet context",
						ex);
			}
		}
		initPropertySources();
	}
```

6）、EmbeddedServletContainerFactory containerFactory = getEmbeddedServletContainerFactory(); 创建嵌入式Servlet容器工厂，具体从IOC容器中获取EmbeddedServletContainerFactory组件;

TomcatEmbeddedServletContainerFactory创建嵌入式Servlet容器对象，触发后置处理器，获取所有的嵌入式Servlet容器定制器，来设置Servlet容器的一些属性值，完成Servlet容器配置;

```java
protected EmbeddedServletContainerFactory getEmbeddedServletContainerFactory() {
		// Use bean names so that we don't consider the hierarchy
		String[] beanNames = getBeanFactory()
				.getBeanNamesForType(EmbeddedServletContainerFactory.class);
		if (beanNames.length == 0) {
			throw new ApplicationContextException(
					"Unable to start EmbeddedWebApplicationContext due to missing "
							+ "EmbeddedServletContainerFactory bean.");
		}
		if (beanNames.length > 1) {
			throw new ApplicationContextException(
					"Unable to start EmbeddedWebApplicationContext due to multiple "
							+ "EmbeddedServletContainerFactory beans : "
							+ StringUtils.arrayToCommaDelimitedString(beanNames));
		}
		return getBeanFactory().getBean(beanNames[0],
				EmbeddedServletContainerFactory.class);
	}
```

7）、this.embeddedServletContainer = containerFactory      .getEmbeddedServletContainer(getSelfInitializer());使用容器工厂获取嵌入式Servlet容器，

8）、嵌入式Servlet容器创建对象，并启动Servlet容器; tomcat就被启动起来了;

==IOC容器启动后先创建并启动嵌入式的Servlet容器，再将IOC容器中剩下没有创建出的对象获取出来（单例对象）==



### 9、使用外置的Servlet容器

​	嵌入式Servlet容器：应用打成可执行的jar;

​		优点：简单、便捷;

​		缺点：默认不支持JSP、优化定制比较复杂;

- 使用定制器(ServerProperites，

		-	自定义EmbeddedServletContainerCustomizer，
		-	自己编写嵌入式Servlet容器工厂[EmbeddedServletContainerFactory]))

​	外置的Servlet容器：外面安装一个tomcat----应用war包的方式打包;

​	步骤：

​		1）、必须创建一个war项目，生成项目后使用idea设置如下;

![1564643454734](/home/xixi/.config/Typora/typora-user-images/1564643454734.png)

​		2）、将被内置的tomcat设置成provided，

```xml
<dependency>    
    <groupId>org.springframework.boot</groupId>    
    <artifactId>spring-boot-starter-tomcat</artifactId>    
    <scope>provided</scope>
</dependency>
```

​		3）、编写一个SpringBootServletInitializer类型的实例，调用sources方法;

```java
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {		
        // 传入springboot的主程序类
        return application.sources(Springboot04JspApplication.class);
    }

}
```

​		4）、启动服务器就可以使用啦;

**原理**

​	1、Jar包，执行springboot主类的main方法，启动IOC容器，创建嵌入式Servlet容器;

​	2、War包，启动服务器，**服务器帮我们启动springboot应用[SpringBootServletInitializer]**，并启动IOC容器，...;

​	servlet3.xx（spring注解版）规范：

​	8.2.4 Shared libraries / runtimes pluggability

​	规定：

​		1）服务器启动（web应用启动）会创建当前web应用里面每一个jar包里面**ServletContainerInitializer**的实例;

​		2）ServletContainerInitializer的实现放在jar位置META-INF/services，有一个名为

文件，并指向 ServletContainerInitializer实现类;

​		3）还可以使用@HandlesTypes，在应用启动的时候加载我们感兴趣的类;

​	**流程**

​		1）、启动Tocmat;

​		2）、路径org/springframework/spring-web/4.3.16.RELEASE/spring-web-4.3.16.RELEASE.jar!/META-INF/services/javax.servlet.ServletContainerInitializer下文件内容：org.springframework.web.SpringServletContainerInitializer

​		3）、加载的SpringServletContainerInitializer类将注解@HandlesTypes(WebApplicationInitializer.class)标注感兴趣的类WebApplicationInitializer传入到onStartup方法，onStartup(Set<Class<?>> webAppInitializerClasses, ServletContext servletContext)，为这些传入的类型创建实例;

​		4）、每一个创建实例(webAppInitializerClasses...)的都会调用自己的onStartup方法;

![1564646331810](/home/xixi/.config/Typora/typora-user-images/1564646331810.png)

​		5）、相当于我们的SpringBootServletInitializer的类会被创建对象，并执行onStartup(ServletContext servletContext)方法;

​		6）、SpringBootServletInitializer实例执行onStarup方法时会调用createRootApplicationContext(servletContext);创建容器;

```java
protected WebApplicationContext createRootApplicationContext(
			ServletContext servletContext) {
		SpringApplicationBuilder builder = createSpringApplicationBuilder();
		StandardServletEnvironment environment = new StandardServletEnvironment();
		environment.initPropertySources(servletContext, null);
		builder.environment(environment);
		builder.main(getClass());
		ApplicationContext parent = getExistingRootWebApplicationContext(servletContext);
		if (parent != null) {
			this.logger.info("Root context already created (using as parent).");
			servletContext.setAttribute(
					WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, null);
			builder.initializers(new ParentContextApplicationContextInitializer(parent));
		}
		builder.initializers(
				new ServletContextApplicationContextInitializer(servletContext));
		builder.contextClass(AnnotationConfigEmbeddedWebApplicationContext.class);
    	// 调用configure方法，其实现类，将SpringBoot主程序类传入进来
		builder = configure(builder);
    	// 使用builder创建一个spring应用
		SpringApplication application = builder.build();
		if (application.getSources().isEmpty() && AnnotationUtils
				.findAnnotation(getClass(), Configuration.class) != null) {
			application.getSources().add(getClass());
		}
		Assert.state(!application.getSources().isEmpty(),
				"No SpringApplication sources have been defined. Either override the "
						+ "configure method or add an @Configuration annotation");
		// Ensure error pages are registered
		if (this.registerErrorPageFilter) {
			application.getSources().add(ErrorPageFilterConfiguration.class);
		}
    	// 启动springboot
		return run(application);
	}
```

​		7）、Spring的应用就启动了并且创建IOC容器;

```java
   /**
	 * Run the Spring application, creating and refreshing a new
	 * {@link ApplicationContext}.
	 * @param args the application arguments (usually passed from a Java main method)
	 * @return a running {@link ApplicationContext}
	 */
	public ConfigurableApplicationContext run(String... args) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ConfigurableApplicationContext context = null;
		FailureAnalyzers analyzers = null;
		configureHeadlessProperty();
		SpringApplicationRunListeners listeners = getRunListeners(args);
		listeners.starting();
		try {
			ApplicationArguments applicationArguments = new DefaultApplicationArguments(
					args);
			ConfigurableEnvironment environment = prepareEnvironment(listeners,
					applicationArguments);
			Banner printedBanner = printBanner(environment);
			context = createApplicationContext();
			analyzers = new FailureAnalyzers(context);
			prepareContext(context, environment, listeners, applicationArguments,
					printedBanner);
			refreshContext(context);
			afterRefresh(context, applicationArguments);
			listeners.finished(context, null);
			stopWatch.stop();
			if (this.logStartupInfo) {
				new StartupInfoLogger(this.mainApplicationClass)
						.logStarted(getApplicationLog(), stopWatch);
			}
			return context;
		}
		catch (Throwable ex) {
			handleRunFailure(context, listeners, analyzers, ex);
			throw new IllegalStateException(ex);
		}
	}
```

先启动Servlet容器，再启动SpringBoot应用;
