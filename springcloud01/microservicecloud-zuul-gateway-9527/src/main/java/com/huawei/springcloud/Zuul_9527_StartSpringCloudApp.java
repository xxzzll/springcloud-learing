/**
 * FileName: Zuul_9527_StartSpringCloudApp
 * Author:   xixi
 * Date:     19-7-25 下午1:41
 * Description: 主启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huawei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 *  <br>
 * 〈主启动类〉
 *
 * @author xixi
 * @create 19-7-25
 * @since 1.0.0
 */
@SpringBootApplication
@EnableZuulProxy
public class Zuul_9527_StartSpringCloudApp {
    public static void main(String[] args) {
        SpringApplication.run(Zuul_9527_StartSpringCloudApp.class, args);
    }
}
