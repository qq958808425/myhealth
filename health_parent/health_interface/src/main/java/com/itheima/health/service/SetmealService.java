package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.Setmeal;

import java.util.List;

/**
 * 包名:com.itheima.health.service
 *
 * @author Zhang Baoxian
 * 日期:2021-01-11   11:16:19
 */
public interface SetmealService {
    /**
     * 添加【套餐】
     * @param setmeal
     * @param checkgroupIds
     */
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 分布查询【套餐】
     * @param queryPageBean
     * @return
     */
    PageResult<Setmeal> findPage(QueryPageBean queryPageBean);

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
     * 编辑【套餐】
     * @param setmeal
     * @param checkgroupIds
     */
    void update(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 删除【套餐】
     * @param id
     */
    void delete(int id) throws MyException;
}
