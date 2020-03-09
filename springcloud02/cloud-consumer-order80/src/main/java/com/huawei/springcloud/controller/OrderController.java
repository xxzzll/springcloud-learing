package com.huawei.springcloud.controller;

import com.huawei.springcloud.common.CommonResult;
import com.huawei.springcloud.entities.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author xixi
 * @Description：
 * @create 2020/3/9
 * @since 1.0.0
 */
@RestController
@RequestMapping("order")
public class OrderController {

    private static final String PAYMENT_URL = "http://localhost:8001/";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "payment/create", payment, CommonResult.class);
    }

    @RequestMapping("query/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "payment/query/" + id, CommonResult.class, id);
    }

}
