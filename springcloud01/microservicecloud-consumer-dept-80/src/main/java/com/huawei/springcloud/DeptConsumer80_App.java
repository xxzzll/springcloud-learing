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

import com.huawei.myrule.MyRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

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
//  在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置生效
@RibbonClient(name = "MICROSERVICECLOUD-DEPT", configuration = MyRuleConfig.class)
public class DeptConsumer80_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_App.class, args);
    }
}
