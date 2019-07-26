/**
 * FileName: DeptService
 * Author:   xixi
 * Date:     19-7-24 下午1:47
 * Description: 部门服务
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huawei.springcloud.service;

import com.huawei.springcloud.entities.Dept;

import java.util.List;

/**
 *  <br>
 * 〈部门服务〉
 *
 * @author xixi
 * @create 19-7-24
 * @since 1.0.0
 */
public interface DeptService {
    public boolean add(Dept dept);

    public Dept get(Long id);

    public List<Dept> list();
}
