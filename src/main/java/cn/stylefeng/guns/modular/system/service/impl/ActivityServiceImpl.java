package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.Activity;
import cn.stylefeng.guns.modular.system.dao.ActivityMapper;
import cn.stylefeng.guns.modular.system.service.IActivityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动 服务实现类
 * </p>
 *
 * @author wjh
 * @since 2019-01-09
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

}
