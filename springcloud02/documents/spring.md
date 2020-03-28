# Spring知识点

参考：https://blog.csdn.net/a745233700/article/details/80959716

## 1、Spring是什么？

Spring是一个轻量级的IOC和AOP容器;为java应用提供基础性服务的一套架构;

### 1.1、常见的配置？

有三种方式，

​	基于XML的配置

​	基于注解的配置

​	基于Java的配置

### 1.2、组成模块

​	Spring Core：核心类库，提供IOC服务;

​	Spring Context：提供框架式的Bean访问方式，以及企业级功能（JNDI、定时任务等）;

Spring AOP：AOP服务;

Spring DAO：对JDBC的抽象，简化了数据访问异常的处理;

Spring ORM：对现有ORM框架的支持;

Spring WEB：提供了基本的web的综合特性，例如多方文件上传;

Spring MVC：提供了面向WEB应用的Model-View-Controller实现;

## 2、Spring AOP理解

OOP面向对象，允许开发者定义纵向的关系，但并不适用定义横向的关系，导致大量代码的重复，不利于各个模块的重用;



AOP，成为面向切面，是对面向对像的一种补充;

将那些与业务无关，但却对多个对象产生影响的公共行为和逻辑，抽取并封装为一个高可用的模块，这个模块被命名为"切面"（Aspect）;

应用场景：用于权限认证、日志、事务处理;



==AOP实现的关键代理模式;==

### 2.1、AOP代理分静态代理和动态代理;

​	静态代理的代表为AspectJ;

​	动态代理则以Spring AOP为代表;

1）AspectJ是静态代理的增强，所谓静态代理，就是AOP框架会在编译阶段生成AOP代理类，是在编译时将切面织入到java字节码中;

2）Spring AOP使用的动态代理就是说AOP框架不会去修改字节码，而是每次运行时在内存中临时为方法生成一个AOP对象，这个AOP对象包含了目标对象的全部方法，并且在特定的切点做了增强处理，并回调原对象的方法;

### 2.2、动态代理的两种方式

1）JDK动态代理

![1565171457742](/home/xixi/.config/Typora/typora-user-images/1565171457742.png)

==说明：给目标对像提供一个代理对象，并由代理对象控制目标对象的引用;==

jdk动态代理只提供接口的代理，不支持类的代理。

​	核心类：InvocationHandler、Proxy

InvocationHandler通过调用invoker()方法反射来调用目标类中的方法，动态地将横切面逻辑和业务逻辑编织在一起;

接着Proxy利用InvocationHandler动态地创建符合某一接口的实例，生成目标类的代理对象;

```java
// jdk的动态代理生产代理实例需要传入被代理接口
public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) throws IllegalArgumentException
```

- 基于接口实现的静态代理实例：

```java
// 基于接口实现的静态代理，代理类在程序运行前已经存在;

// 接口
public interface Fruit {
    void eat();
}
 
// 实现类
public class Apple implements Fruit {
    public void eat() {
        System.out.println("吃苹果)；
    }
}
 
// 代理类
public class AppleProxy implements Fruit {
    private Apple apple;
    public AppleProxy (Apple apple) {
        this.apple = apple;
    }
 
    public void eat() {
        System.out.println("吃前先洗手");
        apple.eat();
        System.out.println("吃后也洗手");
    }
 
    public static void main(String[] args) {
        AppleProxy appleProxy = new AppleProxy(new Apple());
        appleProxy.eat();
        /*
         * 输出：
         * 吃前先洗手
		 * 吃苹果
		 * 吃后也洗手
         *
         */
    }
}
```



- 基于接口实现的动态代理实例

  InvocationHandler类：

  ```java
  public interface InvocationHandler {
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
  }
  ```

  参数说明：

  - Object proxy：指被代理的对象

  - Method method：要调用的方法

  - Object[] args：方法调用时所需要的参数

    

  Proxy类：

  ```java
  public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h);
  ```

  参数说明：

  - ClassLoader loader：类加载器

  - Class<?>[] interfaces：得到全部的接口

  - InvocationHandler h：得到InvocationHandler接口的子类实例

    

```java
// 实现类
public class Apple implements Fruit {
    @Override
    public void eat() {
        System.out.println("吃苹果");
    }
}
 
// 代理类
public class AppleProxy implements InvocationHandler {
    public Object object;
 
    public Object bind(Object object) {
        this.object = object;
        // 
        return Proxy.newInstance(object.getClass().getClassLoader(),     
                                 object.getClass().getInterfaces(), 
                                 this);
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("执行方法之前");
        result = method.invoke(object, args);
        System.out.println("执行方法之后");
        return result;
    }
 
    public static void main(String[] args) {
        AppleProxy appleProxy = new AppleProxy();
        Fruit fruitProxy = (Fruit) appleProxy.bind(new Apple());
        fruitProxy.eat();
         /*
         * 输出：
         * 吃前先洗手
		 * 吃苹果
		 * 吃后也洗手
         *
         */
    }
}
```



2）CGLIB动态代理

代理类没有实现InvocationHandler接口，Spring AOP会选择CGLIB动态代理目标对象;

CGLIB（Code Generation Library），是一个代码生产的类库，可以在运行时动态生产指定类的一个子类，并覆盖特定的方法并添加增强代码，从而实现AOP; 

Tip：标记final的类不能实现;

实例：

```java
// 接口
public interface Fruit {
    void eat();
}
 
// 实现类
public class Apple implements Fruit {
 
    public void eat() {
        System.out.println("吃苹果");
    }
}
 
// 代理类
public class AppleProxy implements MethodInterceptor {
    private Object object;
 
    public Object getInstance(Object object) {
        this.object = object;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.object.getClass());
        //回调方法
        enhancer.setCallback(this);
        //创建代理对象
        return enhancer.create();
    }
 
 	// 在生产的代理方法中调用原目标方法，并在调用前后添加增强的代码
    public Object intercept(Object object, Method method, 
                            Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("执行方法之前");
        methodProxy.invokeSuper(object, args);
        System.out.println("执行方法之后");
        return null;
    }
 
    public static void main(String[] args) {
        AppleProxy appleProxy = new AppleProxy();
        Apple apple = (Apple) appleProxy.getInstance(new Apple());
        apple.eat();
    }
} 
```

## 3、Spring IOC理解

### 3.1、解释

> IOC就是控制反转，指创建对象控制权的转移，以前创建对象的主动权和时间由自己把握，现在这种权限转移给Spring容器;
>
> 由Spring容器根据配置文件或者配置类去创建实例和管理各实例之间的关系，对象和对象之间松散耦合;
>
> DI依赖注入，和控制反转是同一概念不同角度的描述，即应用程序在运行时依赖IOC容器来动态地注入对象所需要的外部资源;



## 4、BeanFactory和ApplicationContext区别？

```java
public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory, HierarchicalBeanFactory,
		MessageSource, ApplicationEventPublisher, ResourcePatternResolver {
```

区别1：

Spring核心接口，都可作为Spring容器使用;

ApplicationContext是BeanFactory的字节口;

BeanFactory，是Spring里面最底层的接口，包含了各种Bean的定义，读取Bean配置文档，管理Bean的加载，实例化Bean，控制Bean的生命周期，维护Bean之间的依赖关系。

ApplicationContext接口作为BeanFactory的派生，除了提供BeanFactory功能外，还提供完整的框架功能;

​	1）集成MessageSource，支持国际化;

​	2）统一资源文件访问方式;

​	3）同时加载多个配置文件;

区别2：

BeanFactory采用的是延迟加载形式注入Bean;

ApplicationContext，它是在容器创建时，一次性创建所有的Bean;



## 5、Spring Bean的生命周期

首先说一下ServletBean的生命周期，实例化，初始化init，接收请求Service，销毁destroy。

==1）实例化Bean==

对于BeanFacotry，当客户向容器请求一个尚未初始化，或者初始化Bean的时候需要注入另一个尚未初始化的依赖时，容器会实例化Bean;

ApplicationContext容器，当容器启动后，获取BeanDefinition对象中的信息，实例化所有的Bean;

==2）设置对象属性（依赖注入）==

实例化后的对象被封装到BeanWrapper对象中，Spring通过BeanDefinition中的信息，并通过BeanWrapper属性对象完成注入;

==3）处理Aware接口==

Spring检查该对象是否实现了XxxAware接口，并将相应的XxxAware实例注入给Bean;

​	接口实现BeanNameAware，调用setBeanName方法，此处传递的是Bean的ID;

​	接口实现BeanFactoryAware，调用setBeanFactory方法，传递的是Bean工厂本身;

​	接口实现ApplicationContextAware，调用setApplicationContext方法，传入Spring上下文;

4）BeanPostProcessor

​	Bean初始化结束时调用Bean的后置处理器;

5）DisposableBean

​	如果Bean实现了DisposableBean，就会调用destroy方法

## 6、Spring支持的几种作用域

1）singleton：默认，每个容器只有一个bean的实例，单例模式由BeanFactory单独维护;

2）prototype：为每个Bean请求提供一个实例;

3）request：为网络请求创建一个实例，请求完成后bean会失效;

4）session：与request类似;

5）global：全局作用域;

## 7、Spring框架中单例Beans是线程安全？

单例Bean没有封装对多线程的支持;

无态Bean，没有线程安全之说;

多态Bean，将 singleton 该成prototype;

## 8、Spring如何处理多线程并发问题？

ThreadLocal和线程同步机制都是为了解决多线程访问同一变量冲突的问题;

ThreadLocal是空间换时间，通过为每一个线程提供一个独立的变量副本，从而隔离各个线程对数据访问的冲突;

## 9、Spring基于xml注入bean的几种方式

1）set方法注入

2）构造器注入

3）静态工厂注入

## 10、Spring自动装配

在Spring中，对象无需查询或创建与自己关联的其他对象，容器负责把需要相互协调的对象引用赋值给各对象，使用autowire来配置自动装配模式;

```text
@Autowired可用于：构造函数、成员变量、Setter方法

注：@Autowired和@Resource之间的区别

(1) @Autowired默认是按照类型装配注入的，默认情况下它要求依赖对象必须存在（可以设置它required属性为false）。

(2) @Resource默认是按照名称来装配注入的，只有当找不到与名称匹配的bean才会按照类型来装配注入。
```

## 11、Spring框架都用过哪些设计模式

1）工厂模式，BeanFactory获取Bean实例;

2）单例模式，Bean模式为单例;

3）代理模式，Spring AOP用到JDK动态代理和CGLIB字节码生产技术;

4）模板方法，RestTemplate，JpaTemplate，...;

5）观察者模式，ApplicationListener，ApplicationContext，ApplicationEvent;

## 12、Spring实现方式和实现原理

Spring事务本质上就是数据库对事务的支持，没有数据库的事务支持，Spring是无法提供事务功能的;

### 12.1、Spring事务种类

1）编程式事务管理

​	编程式事务管理使用TranslationTemplate;

2）声明式事务管理

​	声明试事务是建立在AOP基础上;本质是通过AOP功能，对方法前后进行拦截，将事务处理的功能编织到拦截的方法中，也即是在目标方法执行前加入一个事务，再在执行完目标方法后根据执行情况提交和回滚;

声明式事务只需要在配置文件中做相关事务规则声明或通过@Transaction注解的方式，将事务规则应用到业务逻辑中;

Tip：声明事务最细粒度只能应用到方法级别，无法像编程式事务可以应用到代码块级别;

### 12.2、Spring事务传播行为

什么是事务传播行为？

既然是传播，那么至少是两个东西，才可以发生传播;

事务的传播行为，是指当一个事务的方法去调用另外一个事务的方法时，这个事务方法该如何行为？

示例：

methodA事务方法调用methodB事务方法时，methodB是继续在methodA事务中运行呢，还是开启自己的一个新事务，这就是methodB的事务传播行为决定的;

==7种事务传播机制;==


### 12.3、Spring事务隔离级别

1）ISOLATION_DEFAULT，默认隔离级别，使用数据库的隔离级别;

2）READ_UNCOMMITTED，读未提交，允许另外一个事务可以看到未提交的数据;

3）READ_COMMITTED，读已提交，保证事务提交后才能被其他事务读取，能看到该事务对已有记录的更新;

4）REPEATABLE_READ，可重复读，保证事务提交后才能被其他事务读取，不能看到该事务对已有记录的更新;

5）SERIALIZABLE，串行，一个事务执行过程中完全看不到其他事务对数据所做的更新;

### 12.4、事务的属性可串化（Serializable）

 可串行化是一个调度，即多个事务之间的执行方式；而多个事务之间的执行有个先后顺序，如果事务之间没有共同的操作对象（读或写操作），则事务之间的执行顺序前后置换是没有关系的；但是如果事物间存在共同的操作对象，则事务间先后执行的顺序则需要区分；对于存在共同操作对象的多个并发执行的事务，如果其执行结果“等价”于某个“串行化调度”，则这个调度才是“可串行化的调度”。满足“可串行化的调度”则具有了可串行化（Serializability）属性。所以，可串行化（Serializability）属性保证的是多个事务并发时的执行顺序要对数据的一致性没有影响。

### 12.5、事务的特性

​	**原子性（atomicity）**，强调事务的不可分割;

​	**一致性（consistency）**，事务执行前后的数据完整性保持一致;

​	**隔离性（isolation）**，一个事务的执行过程不受其它事务的干扰;

​	**持久性（durability）**，事务一旦结束，数据就持久到数据库;

## 13、Spring框架有哪些不同类型的事件？

Spring提供5种标准事件

1）上下文更新事件（ContextRefreshedEvent），调用ConfigurableApplicationContext.refresh()方法时触发;

2）上下文开始事件（ContextStartedEvent），调用ConfigurableApplicationContext.start()方法时触发;

3）上下文停止事件（ContextStoppedEvent），调用ConfigurableApplicationContext.stop()方法时触发;

4）上下文关闭事件（ContextClosedEvent），当ApplicationContext被关闭时触发;

5）请求处理事件（RequestHandledEvent），web应用，请求处理结束时触发;

## 14、解释Spring AOP几个名词

1）**切面（Aspect）**，被抽取的公共模块

2）**连接点（Join Point）**，指方法，一个连接点代表一个方法的执行;

3）**通知（Advice）**，在切面的某个特定连接点上执行的动作。

4）**目标对象（Target Object）**，被一个或多个切面（aspect）通知（advise）的对象，即被代理对象;

5）**织入（Weaving）**，把增强应用到目标对象的过程;

## 15、Spring通知的类型？

1）**前置通知（Before advice）**，在某个连接点执行之前的通知，但不能阻止继续执行;

2）**返回后通知（After returning advice）**，一个连接点正常完成后的通知;

3）**抛出异常后通知（After throwing advice）**，方法抛出异常推出后的通知;

4）**后置通知（After(finally) advice）**，连接点退出时的通知;

5） **环绕通知（Around advice）**，包围一个连接点的通知，它会选择是否继续执行连接点或直接返回它们的结果或者抛出异常结束执行;


