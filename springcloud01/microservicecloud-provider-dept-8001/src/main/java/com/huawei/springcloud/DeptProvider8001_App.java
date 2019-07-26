/**
 * FileName: DeptProvider8001_App
 * Author:   xixi
 * Date:     19-7-24 下午1:54
 * Description: 部门8001微服务主启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huawei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *  <br>
 * 〈部门8001微服务主启动类〉
 *
 * @author xixi
 * @create 19-7-24
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient // 本服务启动后会自动注册到eureka服务注册中心
@EnableDiscoveryClient // 启动服务发现
public class DeptProvider8001_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8001_App.class, args);
    }
}
