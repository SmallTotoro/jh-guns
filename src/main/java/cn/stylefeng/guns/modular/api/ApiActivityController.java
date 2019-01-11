package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.model.Activity;
import cn.stylefeng.guns.modular.system.service.IActivityService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 活动控制器
 *
 * @author fengshuonan
 * @Date 2019-01-09 15:09:56
 */
@Controller
@RequestMapping("/gunsApi")
public class ApiActivityController extends BaseController {


    @Autowired
    private IActivityService activityService;

    /**
     * 获取活动列表
     */
    @RequestMapping(value = "/activity",method = RequestMethod.GET)
    @ResponseBody
    public Object list(String condition) {
        return activityService.selectList(null);
    }

    /**
     * 新增活动
     */
    @RequestMapping(value = "/activity",method = RequestMethod.POST)
    @ResponseBody
    public Object add(Activity activity) {
        activityService.insert(activity);
        return SUCCESS_TIP;
    }

    /**
     * 删除活动
     */
    @RequestMapping(value = "/activity",method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@RequestParam Integer activityId) {
        activityService.deleteById(activityId);
        return SUCCESS_TIP;
    }

    /**
     * 修改活动
     */
    @RequestMapping(value = "/activity",method = RequestMethod.PUT)
    @ResponseBody
    public Object update(Activity activity) {
        activityService.updateById(activity);
        return SUCCESS_TIP;
    }

    /**
     * 活动详情
     */
    @RequestMapping(value = "/activity/{activityId}",method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable("activityId") Integer activityId) {
        return activityService.selectById(activityId);
    }
}
