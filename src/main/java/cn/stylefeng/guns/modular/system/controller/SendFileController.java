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
import cn.stylefeng.guns.modular.system.model.SendFile;
import cn.stylefeng.guns.modular.system.service.ISendFileService;

/**
 * 附件控制器
 *
 * @author fengshuonan
 * @Date 2019-01-07 17:59:14
 */
@Controller
@RequestMapping("/sendFile")
public class SendFileController extends BaseController {

    private String PREFIX = "/system/sendFile/";

    @Autowired
    private ISendFileService sendFileService;

    /**
     * 跳转到附件首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "sendFile.html";
    }

    /**
     * 跳转到添加附件
     */
    @RequestMapping("/sendFile_add")
    public String sendFileAdd() {
        return PREFIX + "sendFile_add.html";
    }

    /**
     * 跳转到修改附件
     */
    @RequestMapping("/sendFile_update/{sendFileId}")
    public String sendFileUpdate(@PathVariable Integer sendFileId, Model model) {
        SendFile sendFile = sendFileService.selectById(sendFileId);
        model.addAttribute("item",sendFile);
        LogObjectHolder.me().set(sendFile);
        return PREFIX + "sendFile_edit.html";
    }

    /**
     * 获取附件列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return sendFileService.selectList(null);
    }

    /**
     * 新增附件
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(SendFile sendFile) {
        sendFileService.insert(sendFile);
        return SUCCESS_TIP;
    }

    /**
     * 删除附件
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer sendFileId) {
        sendFileService.deleteById(sendFileId);
        return SUCCESS_TIP;
    }

    /**
     * 修改附件
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(SendFile sendFile) {
        sendFileService.updateById(sendFile);
        return SUCCESS_TIP;
    }

    /**
     * 附件详情
     */
    @RequestMapping(value = "/detail/{sendFileId}")
    @ResponseBody
    public Object detail(@PathVariable("sendFileId") Integer sendFileId) {
        return sendFileService.selectById(sendFileId);
    }
}
