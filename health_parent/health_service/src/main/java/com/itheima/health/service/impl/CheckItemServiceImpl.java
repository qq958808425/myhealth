package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.dao.CheckItemDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 包名:com.itheima.health.service.impl
 * interfaceClass 指定发布接口：CheckItemService.class
 * @author Zhang Baoxian
 * 日期:2021-01-06   20:25:17
 */

@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {
    /**
     * 注入Dao接口
     */
    @Autowired
    private CheckItemDao checkItemDao;

    /**
     * 查找所有【检查项目】
     * @return
     */
    @Override
    public List<CheckItem> findCheckItem() {
        return checkItemDao.findCheckItem();
    }

    /**
     * 添加【检查项目】
     * @param checkItem
     */
    @Override
    public void addCheckItem(CheckItem checkItem) {
        checkItemDao.addCheckItem(checkItem);
    }

    /**
     * 分页查询【检查项目】
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        //使用分布插件
        //PageHelper.startPage() 方法传入参数，底层判断PageSize>0时，则计算总条数并封装进Page
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        //判断查询条件
        if (StringUtil.isNotEmpty(queryPageBean.getQueryString())) {
            //不为空则按查询条件 设置模糊查询
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }

        //按条件 查询分页集合，用page接收  (page继承ArrayList)
        Page<CheckItem> page = checkItemDao.findPage(queryPageBean.getQueryString());

        //page.getResult  相当于Page调用get的方法，返回List  (public List<E> getResult(return this); )
        //将page.getTotal、page.getResult 封装进PageResult, 返回结果
        PageResult<CheckItem> pageResult = new PageResult<>(page.getTotal(), page.getResult());
        return pageResult;
    }

    /**
     * 根据ID查询【检查项目】
     * @param id
     * @return
     */
    @Override
    public CheckItem findById(int id) {
        return checkItemDao.findById(id);
    }

    /**
     * 修改【检查项目】
     * @param checkItem
     */
    @Override
    public void update(CheckItem checkItem) {
        checkItemDao.update(checkItem);
    }

    /**
     * 根据ID删除【检查项目】
     * @param id
     */
    @Override
    public void deleteById(int id) {
        //判断删除的检查项目是否有关联表(有则不能删)，并根据id计算关联表总数,count>0 抛出业务异常
        int count = checkItemDao.findCheckItemByCheckGroupId(id);
        if (count > 0) {
            //检查项目被使用，
            throw new MyException(MessageConstant.DELETE_CHECKITEM_CHECKGROUP_BYID);
        }

        //无关联表则删除
        checkItemDao.deleteById(id);

    }
}
