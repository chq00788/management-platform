package com.chq.project.management.system.model;

import io.swagger.annotations.ApiModelProperty;

        import com.fasterxml.jackson.annotation.JsonFormat;
        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;
        import java.util.Date;

/**
* 描述：用户管理实体类
* @author CHQ
* @date 2019-05-24
*/
public class UserModel {


    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "状态(0:正常1:禁用)")
    private String status;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:dd")
    private Date createTime;

    @ApiModelProperty(value = "最后登录时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:dd")
    private Date lastLoginTime;

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

    public String getUsername() {
    return username;
    }

    public void setUsername(String username) {
    this.username = username;
    }

    public String getPassword() {
    return password;
    }

    public void setPassword(String password) {
    this.password = password;
    }

    public String getRealName() {
    return realName;
    }

    public void setRealName(String realName) {
    this.realName = realName;
    }

    public String getPhone() {
    return phone;
    }

    public void setPhone(String phone) {
    this.phone = phone;
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

    public Date getLastLoginTime() {
    return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
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