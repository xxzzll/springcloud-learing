package com.huawei.springcloud.controller;

import com.huawei.springcloud.service.HystrixPaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xixi
 * @Description：
 * @create 2020/3/17
 * @since 1.0.0
 */
@RestController
public class HystrixOrderController {

    @Resource
    private HystrixPaymentService hystrixPaymentService;

    @GetMapping("/customer/hystrixPayment/hystrixPayment_ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = hystrixPaymentService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/customer/hystrixPayment/hystrixPayment_Timeout/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result = hystrixPaymentService.paymentInfo_TimeOut(id);
        return result;
    }
}
