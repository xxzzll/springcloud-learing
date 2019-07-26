/**
 * FileName: EurekaServer7001_App
 * Author:   xixi
 * Date:     19-7-24 下午5:12
 * Description: eureka注册中心主启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huawei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *  <br>
 * 〈eureka注册中心主启动类〉
 *
 * @author xixi
 * @create 19-7-24
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer7001_App {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer7001_App.class, args);
    }
}
