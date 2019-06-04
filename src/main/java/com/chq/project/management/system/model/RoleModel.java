package com.chq.project.management.system.model;

import io.swagger.annotations.ApiModelProperty;

        import com.fasterxml.jackson.annotation.JsonFormat;
        import java.util.Date;

/**
* 描述：角色管理实体类
* @author CHQ
* @date 2019-05-24
*/
public class RoleModel {


    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色标志")
    private String roleCode;

    @ApiModelProperty(value = "状态(0:正常1:禁用)")
    private String status;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:dd")
    private Date createTime;

    @ApiModelProperty(value = "是否删除(0:否1:是)")
    private Integer deleted;

    @ApiModelProperty(value = "是否选中(0:否1:是)")
    private Integer checked;
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

    public String getRoleName() {
    return roleName;
    }

    public void setRoleName(String roleName) {
    this.roleName = roleName;
    }

    public String getRoleCode() {
    return roleCode;
    }

    public void setRoleCode(String roleCode) {
    this.roleCode = roleCode;
    }

    public String getStatus() {
    return status;
    }

    public void setStatus(String status) {
    this.status = status;
    }

    public String getDescription() {
    return description;
    }

    public void setDescription(String description) {
    this.description = description;
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

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }
}