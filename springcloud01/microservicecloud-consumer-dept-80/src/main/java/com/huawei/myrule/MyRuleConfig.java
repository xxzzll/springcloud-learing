/**
 * FileName: MyRuleConfig
 * Author:   xixi
 * Date:     19-7-25 上午9:09
 * Description: 自定义Ribbon负载均衡算法配置在主启动类启动时被加载
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huawei.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  <br>
 * 〈自定义Ribbon负载均衡算法配置在主启动类启动时被加载〉
 *   官方文档明确给出了警告：
 *   这个自定义配置类不能放在@ComponentScan所扫描的当前包下以及子包下，
 *   否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享，也就是说我们达不到特殊化定制的目的了。
 * @author xixi
 * @create 19-7-25
 * @since 1.0.0
 */
@Configuration
public class MyRuleConfig {

    @Bean
    public IRule getIRule(){
//        return new RandomRule(); // Ribbon默认为轮询，我自定义随机算法
        return new RandomRule_LZX(); // Ribbon默认为轮询，我自定义随机算法
    }
}
