package com.huawei.springcloud.controller;

import com.huawei.springcloud.common.CommonResult;
import com.huawei.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xixi
 * @Description：
 * @create 2020/3/17
 * @since 1.0.0
 */
@RestController
@RequestMapping("openfeignOrder")
public class OpenfeignOrderController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("query/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        return paymentService.getPaymentById(id);
    }

}
