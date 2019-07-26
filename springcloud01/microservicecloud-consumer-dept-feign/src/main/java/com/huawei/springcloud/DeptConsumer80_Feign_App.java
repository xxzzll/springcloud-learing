/**
 * FileName: DeptConsumer80_App
 * Author:   xixi
 * Date:     19-7-24 下午4:19
 * Description: 部门消费端主启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huawei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 *  <br>
 * 〈部门消费端主启动类〉
 *
 * @author xixi
 * @create 19-7-24
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient // 在客户端订阅eureka注册中心提供的服务
@EnableFeignClients(basePackages = "com.huawei.springcloud")
@ComponentScan("com.huawei.springcloud") // 扫描@Configuration注解的类路径
public class DeptConsumer80_Feign_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_Feign_App.class, args);
    }
}
