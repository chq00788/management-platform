package com.chq.project.management.system.dao;

import com.chq.project.management.system.model.PermModel;
import com.chq.project.management.system.model.PermissionDto;

import java.util.List;
import java.util.Map;

/**
 * 描述：权限管理 Dao接口
 *
 * @author CHQ
 * @date 2019-05-24
 */
public interface PermDao {


    /**
     * 查询数据信息
     *
     * @param searchMap
     * @return
     */
    List<PermModel> selectList(Map<String, Object> searchMap);

    /**
     * 新增
     *
     * @param model
     * @return
     */
    Integer insert(PermModel model);

    /**
     * 更新
     *
     * @param model
     * @return
     */
    Integer update(PermModel model);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    Integer delete(Integer id);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    PermModel getById(Integer id);

    /**
     * 查询权限对应的角色信息
     *
     * @return
     */
    List<PermissionDto> selectPermWithRole();
}