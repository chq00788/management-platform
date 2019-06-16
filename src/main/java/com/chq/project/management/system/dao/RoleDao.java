package com.chq.project.management.system.dao;

import com.chq.project.management.system.model.RoleModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 描述：角色管理 Dao接口
 *
 * @author CHQ
 * @date 2019-05-24
 */
public interface RoleDao {


    /**
     * 查询数据信息
     *
     * @param searchMap
     * @return
     */
    List<RoleModel> selectList(Map<String, Object> searchMap);

    /**
     * 新增
     *
     * @param model
     * @return
     */
    Integer insert(RoleModel model);

    /**
     * 更新
     *
     * @param model
     * @return
     */
    Integer update(RoleModel model);

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
    RoleModel getById(Integer id);

    /**
     * 根据用户名查询用户角色信息
     *
     * @param username
     * @return
     */
    List<RoleModel> selectRoleByUsername(@Param(value = "username") String username);


    /**
     * 保存角色权限信息
     *
     * @param list
     * @return
     */
    Integer savePerm(List list);

    /**
     * 删除角色权限信息
     *
     * @param roleId
     * @return
     */
    Integer deletePerm(@Param(value = "roleId") Integer roleId);

    /**
     * 根据用户查询角色信息
     *
     * @param userId
     * @return
     */
    List<RoleModel> selectListByUserId(@Param(value = "userId") Integer userId);

    /**
     * 查询角色权限信息
     *
     * @param roleId
     * @return
     */
    List<Integer> getPermsByRoleId(@Param(value = "roleId") Integer roleId);
}