package com.huawei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author xixi
 * @Description：
 * @create 2020/3/16
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaServer // 启用eureka服务注册中心服务
public class EurekaMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class, args);
    }
}