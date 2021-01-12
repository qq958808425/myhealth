package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.dao.SetmealDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 包名:com.itheima.health.service.impl
 * 发布指定接口 @Service(interfaceClass = SetmealService.class)
 * @author Zhang Baoxian
 * 日期:2021-01-11   11:16:50
 */
@Service(interfaceClass = SetmealService.class)
public class SetmealServiceImpl implements SetmealService {
    /**
     * 注入Dao
     */
    @Autowired
    private SetmealDao setmealDao;

    /**
     * 添加【套餐】
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    @Transactional
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        //调用Dao层方法  *添加套餐；
        setmealDao.addSetmeal(setmeal);
        //获取套餐ID【id来自回显数据】
        Integer setmealId = setmeal.getId();
        //判断checkgroupIds是否为空，不为空则进行遍历
        if (checkgroupIds != null) {
            for (Integer checkgroupId : checkgroupIds) {
                //添加套餐与检查组的关系(一对多)
                setmealDao.addSetmealCheckGroup(setmealId,checkgroupId);
            }
        }

    }

    /**
     * 分布查询【套餐】
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<Setmeal> findPage(QueryPageBean queryPageBean) {
        //使用分布插件
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        //判断查询条件,不为空则按查询条件 设置模糊查询
        if (queryPageBean.getQueryString() != null) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }

        //调用Dao层方法  *查询套餐；【按条件 查询分页集合，用page接收】
        Page<Setmeal> page = setmealDao.findPage(queryPageBean.getQueryString());

        //将page.getTotal、page.getResult 封装进PageResult, 返回结果
        PageResult<Setmeal> pageResult = new PageResult<>(page.getTotal(), page.getResult());
        return pageResult;
    }

    /**
     * 根据ID查询【套餐】
     * @param id
     * @return
     */
    @Override
    public Setmeal findById(int id) {
        return setmealDao.findById(id);
    }

    /**
     * 根据餐组ID查询勾选检查组
     * @param id
     * @return
     */
    @Override
    public List<Integer> findSetmealCheckGroupById(int id) {
        return setmealDao.findSetmealCheckGroupById(id);
    }

    /**
     * 编辑【套餐】
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    @Transactional
    public void update(Setmeal setmeal, Integer[] checkgroupIds) {
        //调用Dao层方法,更新套餐组
        setmealDao.updateSetmeal(setmeal);
        //获取套餐ID
        Integer setmealId = setmeal.getId();
        //【根据套餐ID】删除旧关系
        setmealDao.deleteSetmealCheckGroup(setmealId);
        //添加套餐与检查组的关系
        if (checkgroupIds != null) {
            for (Integer checkgroupId : checkgroupIds) {
                setmealDao.addSetmealCheckGroup(setmealId,checkgroupId);
            }
        }
    }

    /**
     * 删除【套餐】
     * @param id
     * @throws MyException
     */
    @Override
    @Transactional
    public void delete(int id) throws MyException {
        //判断套餐与的订单关系(统计)
        int count = setmealDao.findSetmealOrderById(id);
        if (count > 0) {
            //count>0 有关联表则抛出异常
            throw new MyException(MessageConstant.DELETE_ORDER_SETMEAL_BYID);
        }

        //无关联则先删除旧关系(删除套餐与检查组)
        setmealDao.deleteSetmealCheckGroup(id);
        //再删套餐
        setmealDao.deleteSetmeal(id);
    }
}
