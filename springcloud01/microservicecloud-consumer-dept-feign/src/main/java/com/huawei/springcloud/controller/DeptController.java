/**
 * FileName: DeptController
 * Author:   xixi
 * Date:     19-7-24 下午4:11
 * Description: 消费端部门控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huawei.springcloud.controller;

import com.huawei.springcloud.entities.Dept;
import com.huawei.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * <br>
 * 〈消费端部门控制器〉
 *
 * @author xixi
 * @create 19-7-24
 * @since 1.0.0
 */
@RestController
public class DeptController {

    /**
     * 调用客户端服务并通过feign实现负载均衡
     */
    @Autowired
    private DeptClientService deptClientService;

    @RequestMapping(value = "/consumer/dept/add")
    public boolean add(Dept dept) {
        return deptClientService.add(dept);
    }

    @RequestMapping(value = "/consumer/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id) {
        return deptClientService.get(id);
    }

    @RequestMapping(value = "/consumer/dept/list", method = RequestMethod.GET)
    public List<Dept> list(){
        return deptClientService.list();
    }
}
