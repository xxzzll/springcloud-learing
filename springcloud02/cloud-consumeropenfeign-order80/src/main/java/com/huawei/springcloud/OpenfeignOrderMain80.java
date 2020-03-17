package com.huawei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xixi
 * @Description：
 * @create 2020/3/17
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class OpenfeignOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OpenfeignOrderMain80.class, args);
    }
}
