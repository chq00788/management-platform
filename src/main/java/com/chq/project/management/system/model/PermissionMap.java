package com.chq.project.management.system.model;

import org.springframework.security.access.ConfigAttribute;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @author CHQ
 * @Description
 * @date 2019/5/9
 */
public class PermissionMap {

    public static HashMap<String, Collection<ConfigAttribute>> map;

    public static List<PermissionDto> list;

    public static HashMap<String, Collection<ConfigAttribute>> getMap() {
        return map;
    }

    public static void setMap(HashMap<String, Collection<ConfigAttribute>> map) {
        PermissionMap.map = map;
    }

    public static List<PermissionDto> getList() {
        return list;
    }

    public static void setList(List<PermissionDto> list) {
        PermissionMap.list = list;
    }
}
