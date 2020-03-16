package com.huawei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xixi
 * @Description： 支付的主启动类
 * @create 2020/3/9
 * @since 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用zookeeper或者consul作为注册中心时注册服务
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class, args);
    }
}
