package com.huawei.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author xixi
 * @Description：
 * @create 2020/3/9
 * @since 1.0.0
 */
@Configuration
public class ApplicationConfig {

    /**
     * 异常：I/O error on GET request for \"http://CLOUD-PAYMENT-SERVICE/payment/query/31\": CLOUD-PAYMENT-SERVICE; nested exception is java.net.UnknownHostException: CLOUD-PAYMENT-SERVICE
     * 解决添加 @LoadBalanced 注解
     * @return
     */
    @Bean
    @LoadBalanced // 添加该注解，使得RestTemplate作为负载均衡客户端使用
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
