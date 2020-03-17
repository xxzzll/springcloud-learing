package com.huawei.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author xixi
 * @Description：
 * @create 2020/3/17
 * @since 1.0.0
 */
@RestController
@RequestMapping("payment")
public class ConsulPaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("consul")
    public String paymentConsul(){
        return "SpringCloud with Consul:" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
