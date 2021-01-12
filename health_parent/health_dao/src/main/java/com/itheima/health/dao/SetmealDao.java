package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 包名:com.itheima.health.dao
 *
 * @author Zhang Baoxian
 * 日期:2021-01-11   11:23:48
 */
public interface SetmealDao {
    /**
     * 添加【套餐】
     * @param setmeal
     */
    void addSetmeal(Setmeal setmeal);

    /**
     * 添加套餐与检查组的关系
     * @param checkgroupId
     */
    void addSetmealCheckGroup(@Param("setmealId") Integer setmealId, @Param("checkgroupId") Integer checkgroupId);

    /**
     * 分布查询【套餐】
     * @param queryString
     * @return
     */
    Page<Setmeal> findPage(String queryString);

    /**
     * 根据ID查询【套餐】
     * @param id
     * @return
     */
    Setmeal findById(int id);

    /**
     * 根据餐组ID查询勾选检查组
     * @param id
     * @return
     */
    List<Integer> findSetmealCheckGroupById(int id);

    /**
     * 更新套餐组
     * @param setmeal
     */
    void updateSetmeal(Setmeal setmeal);

    /**
     * 删除【套餐与检查组】旧关系
     * @param setmealId
     */
    void deleteSetmealCheckGroup(Integer setmealId);

    /**
     * 统计套餐与的订单
     * @param id
     * @return
     */
    int findSetmealOrderById(int id);

    /**
     *  删除【套餐】
     * @param id
     */
    void deleteSetmeal(int id);
}
