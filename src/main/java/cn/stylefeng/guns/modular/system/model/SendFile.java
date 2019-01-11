package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 附件
 * </p>
 *
 * @author stylefeng
 * @since 2019-01-07
 */
@TableName("send_file")
public class SendFile extends Model<SendFile> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 归属id
     */
    private String owenid;
    /**
     * 文件名称
     */
    private String filename;
    /**
     * 文件后缀
     */
    private String filesuffix;
    /**
     * 文件地址
     */
    private String fileurl;
    /**
     * 文件类型
     */
    private Integer filetype;
    /**
     * 描述
     */
    private String description;
    /**
     * 文件来源
     */
    private Integer filesource;
    private String uuid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwenid() {
        return owenid;
    }

    public void setOwenid(String owenid) {
        this.owenid = owenid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilesuffix() {
        return filesuffix;
    }

    public void setFilesuffix(String filesuffix) {
        this.filesuffix = filesuffix;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public Integer getFiletype() {
        return filetype;
    }

    public void setFiletype(Integer filetype) {
        this.filetype = filetype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFilesource() {
        return filesource;
    }

    public void setFilesource(Integer filesource) {
        this.filesource = filesource;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SendFile{" +
        ", id=" + id +
        ", owenid=" + owenid +
        ", filename=" + filename +
        ", filesuffix=" + filesuffix +
        ", fileurl=" + fileurl +
        ", filetype=" + filetype +
        ", description=" + description +
        ", filesource=" + filesource +
        ", uuid=" + uuid +
        "}";
    }
}
