package com.huawei.springcloud.service.impl;

import com.huawei.springcloud.dao.PaymentDao;
import com.huawei.springcloud.entities.Payment;
import com.huawei.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xixi
 * @Description：
 * @create 2020/3/9
 * @since 1.0.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
