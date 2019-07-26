/**
 * FileName: DeptServiceImpl
 * Author:   xixi
 * Date:     19-7-24 下午1:48
 * Description: 部门实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huawei.springcloud.service.impl;

import com.huawei.springcloud.dao.DeptDao;
import com.huawei.springcloud.entities.Dept;
import com.huawei.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  <br>
 * 〈部门实现类〉
 *
 * @author xixi
 * @create 19-7-24
 * @since 1.0.0
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public boolean add(Dept dept) {
        return deptDao.addDept(dept);
    }

    @Override
    public Dept get(Long id) {
        return deptDao.findById(id);
    }

    @Override
    public List<Dept> list() {
        return deptDao.findAll();
    }
}
