package com.huawei.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author xixi
 * @Description：
 * @create 2020/3/17
 * @since 1.0.0
 */
@RestController
@RequestMapping("zkOrder")
public class OrderZkController {
    private static final String INVOKE_URL = "http://cloud-payment-server";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("getZkInfo")
    public String getZkInfo(){
        String object = restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
        return object;
    }
}
