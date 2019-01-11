package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 报名表
 * </p>
 *
 * @author wjh
 * @since 2019-01-10
 */
@TableName("party")
public class Party extends Model<Party> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private Long phone;
    /**
     * 内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 微信昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 微信头像
     */
    @TableField("avatar_url")
    private String avatarUrl;
    /**
     * 微信性别
     */
    private Integer gender;
    /**
     * 活动id
     */
    private Integer activityid;
    /**
     * 微信id
     */
    private String openid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getActivityid() {
        return activityid;
    }

    public void setActivityid(Integer activityid) {
        this.activityid = activityid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Party{" +
        ", id=" + id +
        ", name=" + name +
        ", phone=" + phone +
        ", content=" + content +
        ", createtime=" + createtime +
        ", nickName=" + nickName +
        ", avatarUrl=" + avatarUrl +
        ", gender=" + gender +
        ", activityid=" + activityid +
        ", openid=" + openid +
        "}";
    }
}
