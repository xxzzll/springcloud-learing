package com.huawei.springcloud.controller;

import com.huawei.springcloud.common.CommonResult;
import com.huawei.springcloud.entities.Payment;
import com.huawei.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xixi
 * @Description：
 * @create 2020/3/9
 * @since 1.0.0
 */
@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("create")
    public CommonResult create(Payment payment) {
        int result = paymentService.create(payment);
        log.info("查询结果：" + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据成功", result);
        }else{
            return new CommonResult(500, "插入失败");
        }
    }

    @GetMapping("query/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果：" + payment);

        if(payment != null){
            return new CommonResult(200, "查询成功", payment);
        }else{
            return new CommonResult(444, "查询失败");
        }
    }
}
