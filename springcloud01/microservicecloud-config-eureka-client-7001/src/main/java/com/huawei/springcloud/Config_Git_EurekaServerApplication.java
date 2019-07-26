/**
 * FileName: Config_Git_EurekaServerApplication
 * Author:   xixi
 * Date:     19-7-25 下午5:33
 * Description: 主启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huawei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *  <br>
 * 〈主启动类〉
 *
 * @author xixi
 * @create 19-7-25
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaServer
public class Config_Git_EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(Config_Git_EurekaServerApplication.class, args);
    }
}
