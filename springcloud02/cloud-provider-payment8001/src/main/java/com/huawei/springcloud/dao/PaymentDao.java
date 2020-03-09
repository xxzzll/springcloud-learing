package com.huawei.springcloud.dao;

import com.huawei.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xixi
 * @Description：
 * @create 2020/3/9
 * @since 1.0.0
 */
@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
