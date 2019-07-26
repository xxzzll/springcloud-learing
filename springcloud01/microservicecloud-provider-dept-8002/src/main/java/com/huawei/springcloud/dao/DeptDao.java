/**
 * FileName: DeptDao
 * Author:   xixi
 * Date:     19-7-24 下午1:18
 * Description: 部门dao
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huawei.springcloud.dao;

import com.huawei.springcloud.entities.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *  <br>
 * 〈部门dao〉
 *
 * @author xixi
 * @create 19-7-24
 * @since 1.0.0
 */
@Mapper
public interface DeptDao {

    public boolean addDept(Dept dept);
    public Dept findById(Long id);
    public List<Dept> findAll();
}
