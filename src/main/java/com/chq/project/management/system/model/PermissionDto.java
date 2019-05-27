package com.chq.project.management.system.model;

import java.util.List;

/**
 * @author CHQ
 * @Description
 * @date 2019/5/9
 */
public class PermissionDto {

    /**
     * url
     */
    private String permissionUrl;

    /**
     * 角色名称
     */
    private List<String> roleNames;



    public PermissionDto() {
    }

    public PermissionDto(String permissionUrl, List<String> roleNames) {
        this.permissionUrl = permissionUrl;
        this.roleNames = roleNames;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }
}
