package com.huawei.springcloud.service;

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
@FeignClient(value = "CLOUD-HYSTRIX-PAYMENT")
public interface HystrixPaymentService {

    /**
     * 正常访问
     * @param id
     * @return
     */
    @GetMapping("/hystrixPayment/hystrixPayment_ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    /**
     * 超时访问
     *
     * @param id
     * @return
     */
    @GetMapping("/hystrixPayment/hystrixPayment_Timeout/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
