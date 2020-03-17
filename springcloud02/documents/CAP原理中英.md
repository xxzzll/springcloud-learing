





# 一明哥和表妹

# CAP原理

# Part 1



## An Illustrated Proof of the CAP Theorem

```
一个关于CAP定理的图示证明
Illustrated = 带图的 有图有真相
Proof = 证明
Theorem = 定理
```





The [CAP Theorem](http://en.wikipedia.org/wiki/CAP_theorem) is a fundamental theorem in distributed systems that states any distributed system can have at most two of the following three properties.

```
CAP定理是分布式系统中的一个基本定理，它指出任何分布式系统最多可以具有以下三个属性中的两个。

fundamental theorem = 基本定理
distributed = 分布式
systems = 系统 
properties = 属性
that states = 它指出
most two of the following three properties =最多可以有3个中的两个属性
```





- **C**onsistency

  ```
  Consistency = 一致性
  ```

  

- **A**vailability

```
Availability = 可用性
```



- **P**artition tolerance

```
Partition tolerance分区容错性
Partition = 分区
tolerance = 容错
```



This guide will summarize [Gilbert and Lynch's specification and proof of the CAP Theorem](http://lpd.epfl.ch/sgilbert/pubs/BrewersConjecture-SigAct.pdf) with pictures!

```
本指南将用图片总结[吉尔伯特和林奇的规范和证明的CAP定理]!
guide = 指南
summarize = 概述
specification = 规范
```



今日总结

```
Illustrated = 带图的 有图有真相
Proof = 证明
Theorem = 定理
fundamental theorem = 基本定理

distributed = 分布式
systems = 系统 
properties = 属性
that states = 它指出
most two of the following three properties =最多可以有3个中的两个属性

Consistency = 一致性
Availability = 可用性
Partition tolerance分区容错性
Partition = 分区
tolerance = 容错
guide = 指南
summarize = 概述
specification = 规范
```





---







# Part 2



## What is the CAP Theorem?

```
什么是CAP定理?
```



The CAP theorem states that a distributed system cannot simultaneously be consistent, available, and partition tolerant. Sounds simple enough, but what does it mean to be consistent? available? partition tolerant? Heck, what exactly do you even mean by a distributed system?

```
CAP定理指出，分布式系统不可能同时具有一致性、可用性和可分区性。听起来很简单，但是保持一致意味着什么呢?可用吗?分区宽容吗?见鬼，你所说的分布式系统到底是什么意思?

states  = 提出、指出
simultaneously = 同时的
```



In this section, we'll introduce a simple distributed system and explain what it means for that system to be available, consistent, and partition tolerant. For a formal description of the system and the three properties, please refer to [Gilbert and Lynch's paper](http://lpd.epfl.ch/sgilbert/pubs/BrewersConjecture-SigAct.pdf).

```
在本节中，我们将介绍一个简单的分布式系统，并解释该系统可用性、一致性和分区容忍性的含义。关于系统和三个属性的正式描述，请参考[Gilbert和Lynch的论文]。

introduce = 介绍
simple = 简单的
explain = 解释
formal = 正式的
description = 描述
refer = 参考
```



## A Distributed System

```
一个分布式系统
```



Let's consider a very simple distributed system. Our system is composed of two servers,` G1` and` G2`. Both of these servers are keeping track of the same variable `v`, whose value is initially `v0`. `G1`and` G2` can communicate with each other and can also communicate with external clients. Here's what our system looks like.

```
让我们想象一个非常简单的分布式系统。我们的系统由两个服务器G1和G2组成。这两个服务器都记录相同的变量v，其值最初是v0。g1和G2可以相互通信，也可以与外部客户端通信。这是我们的系统。

consider = 考虑，想象
composed = 组成
keeping track = 记录，跟踪
value = 值
initially = 最初
communicate = 通信
external = 外部
clients = 客户端
```



![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap1.svg)

A client can request to write and read from any server. When a server receives a request, it performs any computations it wants and then responds to the client. For example, here is what a write looks like.

```
这里有一个客户端，可以发送读和写的请求向任意服务器。当服务器接收到一个请求时，它执行它想要的任何计算，然后响应客户机。例如，写是这样的。
request = 请求
receives = 接受
performs = 执行
computations = 计算
responds = 响应
example = 例子
```



![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap2.svg) ![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap3.svg) ![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap4.svg)

And here is what a read looks like.

```
这是读取流程。
```



![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap5.svg) ![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap6.svg)

Now that we've gotten our system established, let's go over what it means for the system to be consistent, available, and partition tolerant.

```
既然我们已经建立了我们的系统，让我们来看看系统中的一致性，可用性，和分区容错意具体是什么意思。
established = 构建
```



# Part3

## Consistency

```
一致性
```



Here's how Gilbert and Lynch describe consistency.

```
吉尔伯特和林奇是这样描述一致性的。
```



> any read operation that begins after a write operation completes must return that value, or the result of a later write operation

```
任何在写操作完成后开始的读操作都必须返回该值，或者后面的写操作的结果
```





In a consistent system, once a client writes a value to any server and gets a response, it expects to get that value (or a fresher value) back from any server it reads from.

```
在一致的系统中，一旦客户机向任何服务器写入一个值并获得响应，它就期望从它读取的任何服务器获取该值(或更新的值)。
```



Here is an example of an **inconsistent** system.

```
下面是一个**不一致**系统的例子。
```



![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap7.svg) ![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap8.svg) ![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap9.svg) ![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap10.svg)![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap11.svg)

Our client writes `v1` to `G1` and `G1` acknowledges, but when it reads from `G2`, it gets stale data: v0`.

On the other hand, here is an example of a **consistent** system.

```
我们的客户端写' v1 '到' G1 '， ' G1 '承认，但当它从' G2 '读，它得到陈旧的数据:v0 '。
另一方面，下面是一个**一致性**系统的例子。
```



![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap12.svg) ![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap13.svg) ![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap14.svg) ![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap15.svg)![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap16.svg) ![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap17.svg) ![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap18.svg) ![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap19.svg)

In this system, `G1` replicates its value to `G2` before sending an acknowledgement to the client. Thus, when the client reads from `G2`, it gets the most up to date value of `v`: `v1`.

```
在这个系统中，' G1 '在向客户端发送确认之前将其值复制到' G2 '。因此，当客户端读取' G2 '时，它获得' v '的最新值:' v1 '。
```



## Availability

```
可用性
```



Here's how Gilbert and Lynch describe availability.

```
Gilbert和Lynch是这样描述可用性的。
```



> every request received by a non-failing node in the system must result in a response

```
系统中的非故障节点接收到的每个请求都必须产生一个响应
```



In an available system, if our client sends a request to a server and the server has not crashed, then the server must eventually respond to the client. The server is not allowed to ignore the client's requests.

```
在可用的系统中，如果我们的客户端向服务器发送请求，而服务器没有崩溃，那么服务器最终必须响应客户端。不允许服务器忽略客户机的请求。
```



## Partition Tolerance

```
分区容错性
```



Here's how Gilbert and Lynch describe partitions.

```
吉尔伯特和林奇是这样描述分区的。
```



> the network will be allowed to lose arbitrarily many messages sent from one node to another

```
网络将被允许丢失任意多个从一个节点发送到另一个节点的消息
```



This means that any messages G1G1 and G2G2 send to one another can be dropped. If all the messages were being dropped, then our system would look like this.

```
这意味着可以删除相互发送的任何消息G1G1和G2G2。如果所有的消息都被删除，那么我们的系统将是这样的。
```



![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap20.svg)

Our system has to be able to function correctly despite arbitrary network partitions in order to be partition tolerant.

```
我们的系统必须能够在任意网络分区的情况下正常工作，以便能够容忍分区。
```



## The Proof

```
证明
```



Now that we've acquainted ourselves with the notion of consistency, availability, and partition tolerance, we can prove that a system cannot simultaneously have all three.

Assume for contradiction that there does exist a system that is consistent, available, and partition tolerant. The first thing we do is partition our system. It looks like this.

```
既然我们已经熟悉了一致性、可用性和分区容限的概念，我们就可以证明一个系统不可能同时拥有这三者。
假设存在一个一致的、可用的、分区容忍的系统。我们要做的第一件事是划分系统。它是这样的。
```



![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap21.svg)

Next, we have our client request that v1v1 be written to G1G1. Since our system is available, G1G1 must respond. Since the network is partitioned, however, G1G1 cannot replicate its data to G2G2. Gilbert and Lynch call this phase of execution α1α1.

```
接下来，我们的客户端请求将v1v1写入G1G1。由于我们的系统可用，G1G1必须响应。但是，由于网络是分区的，G1G1不能将其数据复制到G2G2。吉尔伯特和林奇α1α1称这个阶段执行。
```



![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap22.svg) ![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap23.svg) ![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap24.svg)

Next, we have our client issue a read request to G2G2. Again, since our system is available, G2G2 must respond. And since the network is partitioned, G2G2 cannot update its value from G1G1. It returns v0v0. Gilbert and Lynch call this phase of execution α2α2.

```
接下来，我们让客户机向G2G2发出读请求。同样，由于我们的系统可用，G2G2必须响应。由于网络是分区的，G2G2无法从G1G1更新其值。它返回v0v0。吉尔伯特和林奇α2α2称这个阶段执行。
```



![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap25.svg) ![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap26.svg)

G2G2 returns v0v0 to our client after the client had already written v1v1 to G1G1. This is inconsistent.

We assumed a consistent, available, partition tolerant system existed, but we just showed that there exists an execution for any such system in which the system acts inconsistently. Thus, no such system exists.

```
在客户端已经将v1v1写入G1G1之后，G2G2将v0v0返回给客户端。这是不一致的。
我们假设存在一个一致的、可用的、分区容忍的系统，但是我们只是证明了对于任何这样的系统都存在一个执行，在这个执行中系统的行为是不一致的。因此，不存在这样的系统。
```

