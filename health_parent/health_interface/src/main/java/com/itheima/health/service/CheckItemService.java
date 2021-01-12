package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

/**
 * 包名:com.itheima.health.service
 *
 * @author Zhang Baoxian
 * 日期:2021-01-06   20:10:13
 */
public interface CheckItemService {
    /**
     * 查找所有【检查项目】
     * @return
     */
    List<CheckItem> findCheckItem();

    /**
     * 添加【检查项目】
     *
     * @param checkItem
     */
    default void addCheckItem(CheckItem checkItem) {

    }

    /**
     * 分页查询【检查项目】
     * @param queryPageBean
     * @return
     */
    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);

    /**
     * 根据ID查询【检查项目】
     * @param id
     * @return
     */
    CheckItem findById(int id);

    /**
     * 修改【检查项目】
     * @param checkItem
     */
    void update(CheckItem checkItem);

    /**
     * 根据ID删除【检查项目】
     * @param id
     * @throws MyException
     */
    void deleteById(int id) throws MyException;
}
