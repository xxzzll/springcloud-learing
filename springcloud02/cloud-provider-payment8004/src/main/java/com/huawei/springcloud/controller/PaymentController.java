package com.huawei.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * @author xixi
 * @Description：
 * @create 2020/3/9
 * @since 1.0.0
 */
@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("zk")
    public String paymentZk(){
        return "SpringCloud with Zookeeper:" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
