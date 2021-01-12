package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.dao.CheckGroupDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 包名:com.itheima.health.service.impl
 *  nterfaceClass 指定发布接口：CheckGroupService.class
 * @author Zhang Baoxian
 * 日期:2021-01-09   18:05:34
 */

@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {
    // 注入Dao
    @Autowired
    private CheckGroupDao checkGroupDao;

    /**
     * 添加【检查组】
     * 添加检查组与检查项的关系
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    @Transactional
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        // 添加检查组
        checkGroupDao.add(checkGroup);
        // 获取检查组ID
        Integer checkGroupId = checkGroup.getId();
        // 判断checkitemIds是否为空，不为空则进行遍历
        if (checkitemIds != null) {
            for (Integer checkitemId : checkitemIds) {
                // 添加检查组与检查项的关系(一对多)
                checkGroupDao.addCheckGroupCheckItemById(checkGroupId, checkitemId);
            }
        }
    }

    /**
     * 分页查询【检查组】
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        //使用分布插件
        //PageHelper.startPage() 方法传入参数，底层判断PageSize>0时，则计算总条数并封装进Page
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        //判断查询条件
        if (StringUtil.isNotEmpty(queryPageBean.getQueryString())) {
            //不为空则按查询条件 设置模糊查询
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }

        //按条件 查询分页集合，用page接收  (page继承ArrayList)
        Page<CheckGroup> page = checkGroupDao.findPage(queryPageBean.getQueryString());

        //page.getResult  相当于Page调用get的方法，返回List  (public List<E> getResult(return this); )
        //将page.getTotal、page.getResult 封装进PageResult, 返回结果
        PageResult<CheckGroup> pageResult = new PageResult<>(page.getTotal(), page.getResult());
        return pageResult;
    }

    /**
     * 根据ID查询【检查组】
     * @param id
     * @return
     */
    @Override
    public CheckGroup findById(int id) {
        return checkGroupDao.findById(id);
    }

    /**
     * 根据检查组ID查询【勾选检查项】
     * @param id
     * @return
     */
    @Override
    public List<Integer> findCheckGroupCheckItemById(int id) {
        return checkGroupDao.findCheckGroupCheckItemById(id);
    }

    /**
     * 编辑【检查组】
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    @Transactional
    public void update(CheckGroup checkGroup, Integer[] checkitemIds) {
        //更新检查组
        checkGroupDao.updateCheckGroup(checkGroup);
        //获取检查组ID
        Integer checkGroupId = checkGroup.getId();
        //删除旧关系
        checkGroupDao.deleteCheckGroupCheckItemById(checkGroupId);
        //添加检查组与检查项的关系
        if (checkitemIds != null) {
            for (Integer checkitemId : checkitemIds) {
                checkGroupDao.addCheckGroupCheckItemById(checkGroupId, checkitemId);
            }
        }
    }

    @Override
    @Transactional
    public void delete(int id) throws MyException {
        //判断检查组与套餐的关系(统计)
        int count = checkGroupDao.findSetmealCheckGroupById(id);
        if (count > 0) {
        //count>0 有关联表则抛出异常
            throw new MyException(MessageConstant.DELETE_SETMEAL_CHECKGROUP_BYID);
        }

        //无关联则先删除旧关系(删除检查组与检查项)
        checkGroupDao.deleteCheckGroupCheckItemById(id);
        //再删检查组
        checkGroupDao.deleteCheckGroup(id);
    }

    /**
     * 查询所有【检查组】
     * @return
     */
    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }

}
