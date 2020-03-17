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
@RequestMapping("consulOrder")
public class OrderConsulController {
    private static final String INVOKE_URL = "http://cloud-consul-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("getConsulInfo")
    public String getConsulInfo(){
        String object = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        return object;
    }
}
