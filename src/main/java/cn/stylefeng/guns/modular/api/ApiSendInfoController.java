package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.model.SendInfo;
import cn.stylefeng.guns.modular.system.service.ISendInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

/**
 * 信息控制器
 *
 * @author fengshuonan
 * @Date 2019-01-07 17:27:12
 */
@RestController
@RequestMapping("/gunsApi/sendInfo")
public class ApiSendInfoController extends BaseController {

    @Autowired
    private ISendInfoService sendInfoService;

    /**
     * 获取信息列表
     */
    @RequestMapping(value = "/data",method = RequestMethod.GET)
    @ResponseBody
    public Object list(String condition) {
        return sendInfoService.selectList(null);
    }

    /**
     * 新增信息
     */
    @RequestMapping(value = "/data",method = RequestMethod.POST)
    @ResponseBody
    public Object add(SendInfo sendInfo) {
        sendInfo.setCreatetime(new Date());
        sendInfo.setUuid(UUID.randomUUID().toString());
        sendInfoService.insert(sendInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除信息
     */
    @RequestMapping(value = "/data",method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@RequestParam Integer id) {
        sendInfoService.deleteById(id);
        return SUCCESS_TIP;
    }

    /**
     * 修改信息
     */
    @RequestMapping(value = "/data",method = RequestMethod.PUT)
    @ResponseBody
    public Object update(SendInfo sendInfo) {
        sendInfoService.updateById(sendInfo);
        return SUCCESS_TIP;
    }

    /**
     * 信息详情
     */
    @RequestMapping(value = "/data/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable("id") Integer id) {
        return sendInfoService.selectById(id);
    }
}
