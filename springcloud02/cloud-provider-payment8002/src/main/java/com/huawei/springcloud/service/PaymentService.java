package com.huawei.springcloud.service;

import com.huawei.springcloud.entities.Payment;

/**
 * @author xixi
 * @Description：
 * @create 2020/3/9
 * @since 1.0.0
 */
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
