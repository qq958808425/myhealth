package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

/**
 * 包名:com.itheima.health.dao
 *
 * @author Zhang Baoxian
 * 日期:2021-01-06   20:36:26
 */
public interface CheckItemDao {
    /**
     * 查找所有【检查项目】
     * @return
     */
    List<CheckItem> findCheckItem();

    /**
     * 添加【检查项目】
     * @param checkItem
     */
    void addCheckItem(CheckItem checkItem);

    /**
     * 分页查询【检查项目】
     * 接收参数是【String类型】搜索条件
     * @param queryString
     * @return
     */
    Page<CheckItem> findPage(String queryString);

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
     * 根据ID计算【检查项目】关联的检查组
     * @param id
     * @return
     */
    int findCheckItemByCheckGroupId(int id);

    /**
     * 根据ID删除【检查项目】
     * @param id
     */
    void deleteById(int id);
}
