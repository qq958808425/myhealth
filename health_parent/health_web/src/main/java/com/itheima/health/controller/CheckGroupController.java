package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.service.CheckGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 包名:com.itheima.health.controller
 *
 * @author Zhang Baoxian
 * 日期:2021-01-09   17:54:04
 */
@RestController
@RequestMapping("/checkGroup")
public class CheckGroupController {
    //订阅
    @Reference
    private CheckGroupService checkGroupService;

    /**
     * 查询所有【检查组】
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll() {
        List<CheckGroup> checkGroupList = checkGroupService.findAll();
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroupList);
    }

    /**
     * 添加【检查组】
     * @param checkGroup
     * @param checkitemIds
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        //调用服务层【接口方法】添加检查组
        checkGroupService.add(checkGroup, checkitemIds);
        //响应查找结果
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    /**
     * 分页查询【检查组】
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        //调用服务层【接口方法】按搜索条件分页查询检查组
        PageResult<CheckGroup> pageResult = checkGroupService.findPage(queryPageBean);
        //响应查找结果
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, pageResult);
    }

    /**
     * 根据ID查询【检查组】
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public Result findById(int id) {
        //调用服务层【接口方法】根据检查组ID查询检查组
        CheckGroup checkGroup = checkGroupService.findById(id);
        //响应查找结果
        return  new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);
    }

    /**
     * 根据检查组ID查询【勾选检查项】
     * @param id
     * @return
     */
    @GetMapping("/findCheckGroupCheckItemById")
    public Result findCheckGroupCheckItemById(int id) {
        //调用服务层【接口方法】根据检查组ID查询勾选检查项
        List<Integer> checkItemIds = checkGroupService.findCheckGroupCheckItemById(id);
        //响应查找结果
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItemIds);
    }

    /**
     * 编辑【检查组】
     * @param checkGroup
     * @param checkitemIds
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        //调用服务层【接口方法】编辑检查组
        checkGroupService.update(checkGroup, checkitemIds);
        //响应查找结果
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    /**
     * 删除【检查组】
     * @param id
     * @return
     */
    @PostMapping("/deleteById")
    public Result delete(int id) {
        //调用服务层【接口方法】删除检查组
        checkGroupService.delete(id);
        //响应查找结果
        return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }

}
