package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.SendInfo;
import cn.stylefeng.guns.modular.system.service.ISendInfoService;

/**
 * 信息控制器
 *
 * @author fengshuonan
 * @Date 2019-01-07 17:59:27
 */
@Controller
@RequestMapping("/sendInfo")
public class SendInfoController extends BaseController {

    private String PREFIX = "/system/sendInfo/";

    @Autowired
    private ISendInfoService sendInfoService;

    /**
     * 跳转到信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "sendInfo.html";
    }

    /**
     * 跳转到添加信息
     */
    @RequestMapping("/sendInfo_add")
    public String sendInfoAdd() {
        return PREFIX + "sendInfo_add.html";
    }

    /**
     * 跳转到修改信息
     */
    @RequestMapping("/sendInfo_update/{sendInfoId}")
    public String sendInfoUpdate(@PathVariable Integer sendInfoId, Model model) {
        SendInfo sendInfo = sendInfoService.selectById(sendInfoId);
        model.addAttribute("item",sendInfo);
        LogObjectHolder.me().set(sendInfo);
        return PREFIX + "sendInfo_edit.html";
    }

    /**
     * 获取信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return sendInfoService.selectList(null);
    }

    /**
     * 新增信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(SendInfo sendInfo) {
        sendInfoService.insert(sendInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer sendInfoId) {
        sendInfoService.deleteById(sendInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(SendInfo sendInfo) {
        sendInfoService.updateById(sendInfo);
        return SUCCESS_TIP;
    }

    /**
     * 信息详情
     */
    @RequestMapping(value = "/detail/{sendInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("sendInfoId") Integer sendInfoId) {
        return sendInfoService.selectById(sendInfoId);
    }
}
