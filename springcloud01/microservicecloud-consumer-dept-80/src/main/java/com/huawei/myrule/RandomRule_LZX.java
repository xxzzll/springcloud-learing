/**
 * FileName: RandomRule_LZX
 * Author:   xixi
 * Date:     19-7-25 上午9:41
 * Description: 自定义负载均衡算法
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huawei.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *  <br>
 * 〈自定义负载均衡算法〉
 *
 * @author xixi
 * @create 19-7-25
 * @since 1.0.0
 */
public class RandomRule_LZX extends AbstractLoadBalancerRule {

    private Integer total = 0; // 统计每个服务执行的次数，最多可执行5次
    private Integer currentIndex = 0; // 记录当前访问的服务

    /**
     * Randomly choose from all living servers
     */
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }

            if(total < 5){
                server = upList.get(currentIndex);
                total ++;
            }else{
                total = 0;
                if (currentIndex >= upList.size() - 1) {
                    currentIndex = 0;
                } else {
                    currentIndex++;
                }
            }

            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}