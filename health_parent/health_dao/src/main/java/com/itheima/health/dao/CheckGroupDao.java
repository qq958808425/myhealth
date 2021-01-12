package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 包名:com.itheima.health.dao
 *
 * @author Zhang Baoxian
 * 日期:2021-01-09   18:19:00
 */
public interface CheckGroupDao {
    /**
     * 添加【检查组】
     * @param checkGroup
     */
    void add(CheckGroup checkGroup);

    /**
     * 添加检查组与检查项的关系
     * @param checkGroupId
     * @param checkitemId
     */
    void addCheckGroupCheckItemById(@Param("checkGroupId") Integer checkGroupId, @Param("checkitemId") Integer checkitemId);

    /**
     * 分页查询【检查组】
     * @param queryString
     * @return
     */
    Page<CheckGroup> findPage(String queryString);

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
     * 更新【检查组】
     * @param checkGroup
     */
    void updateCheckGroup(CheckGroup checkGroup);

    /**
     * 删除【检查组与检查项】旧关系
     * @param checkGroupId
     */
    void deleteCheckGroupCheckItemById(Integer checkGroupId);

    /**
     * 统计【检查组与套餐】关联总数
     * @param id
     * @return
     */
    int findSetmealCheckGroupById(int id);

    /**
     * 删除【检查组】
     * @param id
     */
    void deleteCheckGroup(int id);

    /**
     * 查询所有【检查组】
     * @return
     */
    List<CheckGroup> findAll();

}
