/**
 * FileName: ConfigBean
 * Author:   xixi
 * Date:     19-7-24 下午4:09
 * Description: springboot对spring的配置文件applicationContext.xml的类定义
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huawei.springcloud.cfgbeans;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *  <br>
 * 〈springboot对spring的配置文件applicationContext.xml的类定义〉
 *
 * @author xixi
 * @create 19-7-24
 * @since 1.0.0
 */
@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced // 把RestTemplate标记负载均衡客户端
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public IRule getIRule(){
//        return new RoundRobinRule(); // 轮询算法
//        return new RandomRule(); // 随机算法
        return new RetryRule(); // 首先轮询，失败重试
    }

}
