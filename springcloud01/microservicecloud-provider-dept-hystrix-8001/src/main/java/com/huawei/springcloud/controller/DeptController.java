/**
 * FileName: DeptController
 * Author:   xixi
 * Date:     19-7-24 下午1:49
 * Description: 部门控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huawei.springcloud.controller;

import com.huawei.springcloud.entities.Dept;
import com.huawei.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  <br>
 * 〈部门控制器〉
 *
 * @author xixi
 * @create 19-7-24
 * @since 1.0.0
 */
@RestController // 前后端分离，restful风格
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "processHystrix_GET")
    public Dept get(@PathVariable("id") Long id){
        Dept dept = deptService.get(id);
        if(dept == null){
            throw new RuntimeException("该ID：" + id + "没有对应的部门信息！");
        }
        return dept;
    }

    public Dept processHystrix_GET(@PathVariable("id") Long id){
        return new Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,null--@HystrixCommand").setDb_source("no this database in MySQL");
    }
}
