package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.model.Activity;
import cn.stylefeng.guns.modular.system.model.Party;
import cn.stylefeng.guns.modular.system.service.IActivityService;
import cn.stylefeng.guns.modular.system.service.IPartyService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 报名控制器
 *
 * @author fengshuonan
 * @Date 2019-01-09 11:21:57
 */
@Controller
@RequestMapping("/gunsApi")

public class ApiPartyController extends BaseController {

    @Autowired
    private IPartyService partyService;
    @Autowired
    private IActivityService activityService;

    /**
     * 获取报名列表
     */
    @RequestMapping(value = "/party", method = RequestMethod.GET)
    @ResponseBody
    public Object list(String condition) {
        return partyService.selectList(null);
    }

    /**
     * 新增报名
     */
    @RequestMapping(value = "/party", method = RequestMethod.POST)
    @ResponseBody
    public Object add(Party party) {
        party.setCreatetime(new Date());
        EntityWrapper<Party> ew_party = new EntityWrapper<Party>();
        ew_party.eq("openid", party.getOpenid());
        ew_party.eq("activityid", party.getActivityid());
        Integer partyCount = partyService.selectCount(ew_party);

        EntityWrapper<Activity> ew_activity = new EntityWrapper<Activity>();
        ew_activity.eq("id", party.getActivityid());
        ew_activity.eq("status", 1);
        Integer activityCount = activityService.selectCount(ew_activity);
        if (activityCount > 0) {
            if (partyCount > 0) {
                return new ErrorResponseData(500, "您已经报名过了！");
            } else {
                partyService.insert(party);
                return new SuccessResponseData(200, "报名成功！", null);
            }
        } else {
            return new ErrorResponseData(500, "活动已经结束！");
        }
    }

    /**
     * 删除报名
     */
    @RequestMapping(value = "/party", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@RequestParam Integer partyId) {
        partyService.deleteById(partyId);
        return SUCCESS_TIP;
    }

    /**
     * 修改报名
     */
    @RequestMapping(value = "/party", method = RequestMethod.PUT)
    @ResponseBody
    public Object update(Party party) {
        partyService.updateById(party);
        return SUCCESS_TIP;
    }

    /**
     * 报名详情
     */
    @RequestMapping(value = "/party/{partyId}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable("partyId") Integer partyId) {
        return partyService.selectById(partyId);
    }


    /**
     * 统计人数party
     */
    @RequestMapping(value = "/party/count/{activityid}", method = RequestMethod.GET)
    @ResponseBody
    public Object count(@PathVariable("activityid") String activityid) {
        EntityWrapper<Party> ew_party = new EntityWrapper<Party>();
        ew_party.eq("activityid", activityid);
        Integer partyCount = partyService.selectCount(ew_party);
        return new SuccessResponseData(200, partyCount + "", null);
    }

    /**
     * 查询party
     */
    @RequestMapping(value = "/party/search/{openid}", method = RequestMethod.GET)
    @ResponseBody
    public Object search(@PathVariable("openid") String openid) {
        EntityWrapper<Party> ew = new EntityWrapper<Party>();
        ew.eq("openid", openid);
        List<Party> parties = partyService.selectList(ew);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < parties.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            EntityWrapper<Activity> ew_activity = new EntityWrapper<Activity>();
            ew_activity.in("id", parties.get(i).getActivityid()+"");
           Activity activity = activityService.selectOne(ew_activity);
            jsonObject.put("party",parties.get(i));
            jsonObject.put("activity",activity);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
