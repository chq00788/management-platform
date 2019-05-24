package com.chq.project.management.system.model;

import io.swagger.annotations.ApiModelProperty;

        import com.fasterxml.jackson.annotation.JsonFormat;
        import java.util.Date;

/**
* 描述：权限管理实体类
* @author CHQ
* @date 2019-05-24
*/
public class PermModel {


    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String permName;

    @ApiModelProperty(value = "权限标识")
    private String permCode;

    @ApiModelProperty(value = "类型(1:目录2:菜单3:按钮)")
    private Integer permType;

    @ApiModelProperty(value = "菜单路径")
    private String permUrl;

    @ApiModelProperty(value = "排序")
    private Integer permSort;

    @ApiModelProperty(value = "父ID")
    private Integer pid;

    @ApiModelProperty(value = "图标")
    private String permIcon;

    @ApiModelProperty(value = "状态(0:正常1:禁用)")
    private String status;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:dd")
    private Date createTime;

    @ApiModelProperty(value = "是否删除(0:否1:是)")
    private Integer deleted;

    /**
    * 排序字段默认为id
    */
    private String sortCode = "id";

    /**
    * 排序规则默认为降序排列(DESC/ASC)
    */
    private String sortRole = "DESC";


    public Integer getId() {
    return id;
    }

    public void setId(Integer id) {
    this.id = id;
    }

    public String getPermName() {
    return permName;
    }

    public void setPermName(String permName) {
    this.permName = permName;
    }

    public String getPermCode() {
    return permCode;
    }

    public void setPermCode(String permCode) {
    this.permCode = permCode;
    }

    public Integer getPermType() {
    return permType;
    }

    public void setPermType(Integer permType) {
    this.permType = permType;
    }

    public String getPermUrl() {
    return permUrl;
    }

    public void setPermUrl(String permUrl) {
    this.permUrl = permUrl;
    }

    public Integer getPermSort() {
    return permSort;
    }

    public void setPermSort(Integer permSort) {
    this.permSort = permSort;
    }

    public Integer getPid() {
    return pid;
    }

    public void setPid(Integer pid) {
    this.pid = pid;
    }

    public String getPermIcon() {
    return permIcon;
    }

    public void setPermIcon(String permIcon) {
    this.permIcon = permIcon;
    }

    public String getStatus() {
    return status;
    }

    public void setStatus(String status) {
    this.status = status;
    }

    public Date getCreateTime() {
    return createTime;
    }

    public void setCreateTime(Date createTime) {
    this.createTime = createTime;
    }

    public Integer getDeleted() {
    return deleted;
    }

    public void setDeleted(Integer deleted) {
    this.deleted = deleted;
    }
    public String getSortRole() {
    return sortRole;
    }

    public void setSortRole(String sortRole) {
    this.sortRole = sortRole;
    }

    public String getSortCode() {
    return sortCode;
    }

    public void setSortCode(String sortCode) {
    this.sortCode = sortCode;
    }
}