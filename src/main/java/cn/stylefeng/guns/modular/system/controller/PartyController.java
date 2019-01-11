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
import cn.stylefeng.guns.modular.system.model.Party;
import cn.stylefeng.guns.modular.system.service.IPartyService;

/**
 * 报名控制器
 *
 * @author fengshuonan
 * @Date 2019-01-10 17:10:50
 */
@Controller
@RequestMapping("/party")
public class PartyController extends BaseController {

    private String PREFIX = "/system/party/";

    @Autowired
    private IPartyService partyService;

    /**
     * 跳转到报名首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "party.html";
    }

    /**
     * 跳转到添加报名
     */
    @RequestMapping("/party_add")
    public String partyAdd() {
        return PREFIX + "party_add.html";
    }

    /**
     * 跳转到修改报名
     */
    @RequestMapping("/party_update/{partyId}")
    public String partyUpdate(@PathVariable Integer partyId, Model model) {
        Party party = partyService.selectById(partyId);
        model.addAttribute("item",party);
        LogObjectHolder.me().set(party);
        return PREFIX + "party_edit.html";
    }

    /**
     * 获取报名列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return partyService.selectList(null);
    }

    /**
     * 新增报名
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Party party) {
        partyService.insert(party);
        return SUCCESS_TIP;
    }

    /**
     * 删除报名
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer partyId) {
        partyService.deleteById(partyId);
        return SUCCESS_TIP;
    }

    /**
     * 修改报名
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Party party) {
        partyService.updateById(party);
        return SUCCESS_TIP;
    }

    /**
     * 报名详情
     */
    @RequestMapping(value = "/detail/{partyId}")
    @ResponseBody
    public Object detail(@PathVariable("partyId") Integer partyId) {
        return partyService.selectById(partyId);
    }
}
