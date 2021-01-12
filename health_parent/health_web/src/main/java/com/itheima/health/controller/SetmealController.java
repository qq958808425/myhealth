package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import com.itheima.health.utils.QiNiuUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 包名:com.itheima.health.controller
 *
 * @author Zhang Baoxian
 * 日期:2021-01-10   22:20:12
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    /**
     * 订阅服务
     */
    @Reference
    private SetmealService setmealService;

    /**
     * 定义log
     */
    private static Logger log = LoggerFactory.getLogger(SetmealController.class);

    /**
     * 上传图片【套餐】
     * @param imgFile
     * @return
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile imgFile) {
        //1. 获取源文件名
        String originalFilename = imgFile.getOriginalFilename();
        //2. 截取后缀名
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //3. 生成唯一id,
        String uniqueId = UUID.randomUUID().toString();
        //4. 拼接唯一文件名
        String filename = uniqueId + suffix;
        //5. 调用7牛工具上传图片
        try {
            QiNiuUtils.uploadViaByte(imgFile.getBytes(), filename);
            //6. 构建返回的数据
            /*
             {
                imgName: 图片名 , 补全formData.img
                domain: 七牛的域名 图片回显imageUrl = domain+图片名
            }
             */
            Map<String,String> map = new HashMap<String,String>(2);
            map.put("imgName", filename);
            map.put("domain",QiNiuUtils.DOMAIN);
            //7. 放到result里，再返回给页面
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,map);
        } catch (IOException e) {
            log.error("上传文件失败了",e);
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    /**
     * 添加【套餐】
     * @param setmeal
     * @param checkgroupIds
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        // 调用服务层【接口方法】添加套餐
        setmealService.add(setmeal, checkgroupIds);

        // 返回结果
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    /**
     * 分布查询【套餐】
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        // 调用服务层【接口方法】添加套餐
        PageResult<Setmeal> pageResult = setmealService.findPage(queryPageBean);

        // 返回结果
        return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS, pageResult);

    }

    /**
     * 根据ID查询【套餐】
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public Result findById(int id) {
        // 调用服务层【接口方法】查询套餐
        Setmeal setmeal = setmealService.findById(id);
        //返回结果【存入Map<String, Object】{"setmeal" : setmeal, "domain" : QiNiuUtils.DOMAIN}
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("setmeal", setmeal);
        map.put("domain", QiNiuUtils.DOMAIN);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, map);
    }

    /**
     * 根据餐组ID查询勾选检查组
     * @param id
     * @return
     */
    @GetMapping("/findSetmealCheckGroupById")
    public Result findSetmealCheckGroupById(int id) {
        // 调用服务层【接口方法】根据餐组ID查询勾选检查组
        List<Integer> checkGroupIds = setmealService.findSetmealCheckGroupById(id);
        //返回结果
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, checkGroupIds);
    }

    /**
     * 编辑【套餐】
     * @param setmeal
     * @param checkgroupIds
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        // 调用服务层【接口方法】
        setmealService.update(setmeal, checkgroupIds);
        //返回结果
        return new Result(true, MessageConstant.UPDATE_SETMEAL_SUCCESS);
    }

    /**
     * 删除【套餐】
     * @param id
     * @return
     */
    @PostMapping("/deleteById")
    public Result delete(int id) {
        //调用服务层【接口方法】删除检查组
        setmealService.delete(id);
        //响应查找结果
        return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }
}
