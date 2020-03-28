# SpringMVC
Description:
>
SpringMVC 是基于 Servlet API 构建的原始Web框架。
## DispatcherServlet

说明：处理springMVC的请求的总调度器;

Description:
>
DispatcherServlet 与任何 Servlet 一样，需要使用 Java 配置或在 web.xml 中根据 Servlet 规范进行声明和映射，反过来，DispatcherServlet 需要 Spring 配置来发现请求映射，视图解析，异常处理等所需要的委托组件。
#### 1.1.Context Hierarchy

说明：MVC上下文层次结构;

图1： MVC Context 层次表：

![avatar](/home/xixi/Pictures/spring/mvc-context-hierarchy.png)

配置一个有层次的 WebApplicationContext 例子如下:

>    public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
>        @Override
>        protected Class<?>[] getRootConfigClasses() {
>            return new Class<?>[] { RootConfig.class };
>        }
>        @Override
>        protected Class<?>[] getServletConfigClasses() {
>            return new Class<?>[] { App1Config.class };
>        }
>        @Override
>        protected String[] getServletMappings() {
>            return new String[] { "/app1/*" };
>        }
>    }
>    /* 
>     * 如果不需要应用程序上下文层次结构，则应用程序可以通过 getRootConfigClasses() 返回所有
>     * 的配置和 getServletConfigClasses() 返回null。
>     */
>**等同于 web.xml 中的一下配置：**
>
>```xml
><web-app>
>    <listener>
>        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
>    </listener>
>    <!-- contextLoaderListener初始化时设置以下参数值 -->
>    <context-param>
>        <!--
>    		Name of servlet context parameter (i.e.,@value) that can specify the
>	    config location for the root context
>		-->
>        <param-name>contextConfigLocation</param-name>
>        <param-value>/WEB-INF/root-context.xml</param-value>
>    </context-param>
>    
>    <!--
>	因为 ContextLoaderListener 本质上是创建了一个 WebApplicationContext ，所以你的项目里面，如果不使用 WebApplicationContext 就可以不配置该节点。
>你的应用程序是无法使用WebApplicationContext，高级实现的IOC容器;
>	-->
>    
>    <servlet>
>        <servlet-name>app1</servlet-name>
>        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
>        <init-param>
>            <param-name>contextConfigLocation</param-name>
>            <param-value>/WEB-INF/app1-context.xml</param-value>
>        </init-param>
>        <load-on-startup>1</load-on-startup>
>    </servlet>
>    <servlet-mapping>
>        <servlet-name>app1</servlet-name>
>        <url-pattern>/app1/*</url-pattern>
>    </servlet-mapping>
>    <!--
>	同上:
> 		If an application context hierarchy is not required, applications may 
> 	configure a “root” context only and leave the contextConfigLocation 
> 	Servlet parameter empty.
>	-->
></web-app> 
>```
>
#### 1.2.Special Bean Types
Description:
>
DispatcherServlet 委托 special beans 来处理请求并呈现适当的响应；"special beans" 是指实现架构契约的 Spring 管理的 Object 实例。

下面列出了 DispatcherServlet 检测到 "special beans":<br/>

|   Bean type   |   Explanation  |
|-----------|-----------|
|     HandlerMapping      |     将request映射给handler,以及用于预处理和后处理的拦截器interceptors列表;映射是基于一些标准，具体看HandlerMapping具体实现.两个HandlerMapping主要实现：<br/>$\color{blue}{RequestMappingHandlerMapping}$ (which supports @RequestMapping annotated methods)<br/>$\color{blue}{SimpleUrlHandlerMapping}$ (which maintains explicit registrations of URI path patterns to handlers).|
|      HandlerAdapter     |      帮助DispatcherServlet调用映射到request的handler     |
|      ViewResolver       |        Resolve logical String-based view names returned from a handler to an actual View with which to render to the response        |
|       LocaleResolver, LocaleContextResolver        |         Resolve the Locale a client is using and possibly their time zone, in order to be able to offer internationalized views.     |
|       MultipartResolver        |        Abstraction for parsing a multi-part request (for example, browser form file upload) with the help of some multipart parsing library.        |
|       HandlerExceptionResolver        |        Strategy to resolve exceptions, possibly mapping them to handlers, to HTML error views, or other targets.        |
|       ThemeResolver        |        Resolve themes your web application can use — for example, to offer personalized layouts.        |

#### 1.3.Web MVC Config
Description:
>
Applications can declare the infrastructure beans listed in Special Bean Types that are required to process requests. <br/>
The DispatcherServlet checks the WebApplicationContext for each special bean.<br/>
If there are no matching bean types, it falls back on the default types listed in DispatcherServlet.properties.
<br/>
In most cases, the MVC Config is the best starting point. It declares the required beans in either Java or XML and provides a higher-level configuration callback API to customize it.

#### 1.4.Servlet Config
Description：
>
In a Servlet 3.0+ environment, you have the option of configuring the Servlet container programmatically as an alternative or in combination with a web.xml file. 

The following example registers a DispatcherServlet:

## 原理

### 1、HandlerMapping：

说明：处理器映射器;

DispatcherServlet.handlerMappings 	size = 3

​	BeanNameUrlHandlerMapping	  ---使用实现Controller接口，继承AbstractController抽象类的处理器映射器

​	RequestMappingHandlerMapping ---使用@Controller注解+扫描的方式

​	RouterFunctionMapping 

### 2、HandlerAdapter

说明：处理器适配器;

DispatcherServlet.handlerAdapters 	size = 4

​	HttpRequestHandlerAdapter 

​	SimpleControllerHandlerAdapter ---使用实现Controller接口，或继承AbstractController抽象类

​	RequestMappingHandlerAdapter  ---使用@Controller注解+扫描的方式

​	HandlerFunctionAdapter

## ContextLoaderListener

### 1、作用



### 2、加载过程

**在web.xml中，配置ContextLoaderListener**

```xml
<!-- 配置Listener，用来创建Spring容器 -->
 <listener>
   <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
 </listener>
 
 <context-param>
   <!-- 配置Listener参数：告诉它Spring的配置文件位置，它好去创建容器 -->
   <param-name>contextConfigLocation</param-name>
   <param-value>classpath:application-context*.xml</param-value>
 </context-param>
```



## ServletContextListener

```java
   /**
     * Receives notification that the web application initialization
     * process is starting.
     *
     * <p>All ServletContextListeners are notified of context
     * initialization before any filters or servlets in the web
     * application are initialized.
     *
     * @param sce the ServletContextEvent containing the ServletContext
     * that is being initialized
     *
     * @implSpec
     * The default implementation takes no action.
     */
	default public void contextInitialized(ServletContextEvent sce) {}

    /**
     * Receives notification that the ServletContext is about to be
     * shut down.
     *
     * <p>All servlets and filters will have been destroyed before any
     * ServletContextListeners are notified of context
     * destruction.
     *
     * @param sce the ServletContextEvent containing the ServletContext
     * that is being destroyed
     *
     * @implSpec
     * The default implementation takes no action.
     */
    default public void contextDestroyed(ServletContextEvent sce) {}
```



### 1、作用

监听servlet容器（Tomcat、Jetty）启动和关闭;

In order to receive these notification events, the implementation class must be either declared in the deployment descriptor of the web application, annotated with {@link javax.servlet.annotation.WebListener}, or registered via one of the addListener methods defined on {@link ServletContext}.



