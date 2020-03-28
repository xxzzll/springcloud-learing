# redis

1、redis概念

> 1. 开源的(BSD协议)，使用ANSI C编写，基于内存的且支持持久化，高性能的Key-Value的NoSQL数据库;
> 2. 支持数据结构类型丰富，有如字符串(strings)、散列(hashes)、列表(lists)、集合(sets)、有序集合(sorted sets) 与范围查询，bitmaps，hyperloglogs 和 地理空间(geospatial) 索引半径查询;
> 3. 丰富的支持主流语言的客户端，C、C++、Python、Erlang、R、C#、Java、PHP、Objective-C、Pert、Ruby、Scala、Go、JavaScript;
> 4. 用途：缓存(StackOverFlow)、数据库(微博)、消息中间件(微博)
> 5. 官方网站：http://www.redis.io
> 6. 源语：相同的key为一组，这一组数据调用一次reduce方法;
> 7. a 1 ;  （1,a,3,x)   x=1 y=4



2、redis单节点安装

- 编译安装

  - yum -y install gcc tcl -y
  - tar xf redis-2.8.18.tar.gz
  - make
  - make PREFIX=/opt/redis install
  - export REDIS_HOME=/opt/redis
  - export PATH=$PATH:$REDIS_HOME/bin

- utils目录

  - 安装服务

    > [root@192 utils]# ./install_server.sh 
    > Welcome to the redis service installer
    > This script will help you easily set up a running redis server
    >
    > Please select the redis port for this instance: [6379] 
    > Selecting default: 6379
    > Please select the redis config file name [/etc/redis/6379.conf] 
    > Selected default - /etc/redis/6379.conf
    > Please select the redis log file name [/var/log/redis_6379.log] 
    > Selected default - /var/log/redis_6379.log
    > Please select the data directory for this instance [/var/lib/redis/6379] 
    > Selected default - /var/lib/redis/6379
    > Please select the redis executable path [/opt/redis/bin/redis-server] 
    > Selected config:
    > Port           : 6379
    > Config file    : /etc/redis/6379.conf
    > Log file       : /var/log/redis_6379.log
    > Data dir       : /var/lib/redis/6379
    > Executable     : /opt/redis/bin/redis-server
    > Cli Executable : /opt/redis/bin/redis-cli
    > Is this ok? Then press ENTER to go on or Ctrl-C to abort.
    > Copied /tmp/6379.conf => /etc/init.d/redis_6379
    > Installing service...
    > Successfully added to chkconfig!
    > Successfully added to runlevels 345!
    > Starting Redis server...
    > Installation successful!

- 启动

3、redis数据模型

![1571400567286](/home/xixi/.config/Typora/typora-user-images/1571400567286.png)

4、键Key	redis	KEY VALUE

- Redis key 值是**二进制安全的**，这意味着可以用任何**二进制序列**作为key值，从形如"foo"的简单字符串到一个JPEG文件的内容都可以。空字符串也是有效key值;
- Key取值原则
  - 键值不需要太长，消耗内存，且在数据中查找这类键值的计算成本较高
  - 键值不直过短，可读性较差

5、常用命令

​	命令String

