package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.CheckGroup;

import java.util.List;

/**
 * 包名:com.itheima.health.service
 *
 * @author Zhang Baoxian
 * 日期:2021-01-09   18:01:46
 */
public interface CheckGroupService {
    /**
     * 添加【检查组】
     * @param checkGroup
     * @param checkitemIds
     */
    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 分页查询【检查组】
     * @param queryPageBean
     * @return
     */
    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);

    /**
     * 根据ID查询【检查组】
     * @param id
     * @return
     */
    CheckGroup findById(int id);

    /**
     * 根据检查组ID查询【勾选检查项】
     * @param id
     * @return
     */
    List<Integer> findCheckGroupCheckItemById(int id);

    /**
     * 编辑【检查组】
     * @param checkGroup
     * @param checkitemIds
     */
    void update(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 删除【检查组】
     * @param id
     * @throws MyException
     */
    void delete(int id) throws MyException;

    /**
     * 查询所有【检查组】
     * @return
     */
    List<CheckGroup> findAll();

}
