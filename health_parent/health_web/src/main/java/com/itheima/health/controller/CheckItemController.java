package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 包名:com.itheima.health.controller
 *
 * @author Zhang Baoxian
 * 日期:2021-01-06   19:53:57
 */
@RestController
@RequestMapping("/checkItem")
public class CheckItemController {

    /**
     * 订阅服务
     */
    @Reference
    private CheckItemService checkItemService;

    /**
     * 查找所有【检查项目】
     * @return
     */
    @GetMapping("/findCheckItem")
    public Result findCheckItem() {
        //调用服务层【接口方法】查找所有检查项目
        List<CheckItem> checkItemList = checkItemService.findCheckItem();
        //响应查找结果
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItemList);
    }

    /**
     * 添加【检查项目】
     * @param checkItem
     * @return
     */
    @PostMapping("/addCheckItem")
    public Result addCheckItem(@RequestBody CheckItem checkItem) {
        //调用服务层【接口方法】添加检查项目
        checkItemService.addCheckItem(checkItem);
        //响应添加结果
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    /**
     * 分页查询【检查项目】
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        //调用服务层【接口方法】按搜索条件分页查询检查项目
        PageResult<CheckItem> pageResult = checkItemService.findPage(queryPageBean);
        //响应查找结果
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, pageResult);
    }

    /**
     * 根据ID查询【检查项目】
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public Result findById(int id) {
        //调用服务层【接口方法】根据ID查询检查项目
        CheckItem checkItem = checkItemService.findById(id);
        //响应查找结果
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItem);
    }

    /**
     * 修改【检查项目】
     * @param checkItem
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody CheckItem checkItem) {
        //调用服务层【接口方法】修改检查项目
        checkItemService.update(checkItem);
        //响应添加结果
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    /**
     * 根据ID删除【检查项目】
     * @param id
     * @return
     */
    @PostMapping("/deleteById")
    public Result deleteById(int id) {
        checkItemService.deleteById(id);
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }
}
