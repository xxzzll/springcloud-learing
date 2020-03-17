package com.huawei.springcloud.service;

import com.huawei.springcloud.common.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xixi
 * @Description：
 * @create 2020/3/17
 * @since 1.0.0
 */
@Component
@FeignClient(value = "cloud-payment-service")
public interface PaymentService {

    @GetMapping("payment/query/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);
}
