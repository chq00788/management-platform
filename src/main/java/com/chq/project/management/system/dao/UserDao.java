package com.chq.project.management.system.dao;

import com.chq.project.management.system.model.UserModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 描述：用户管理 Dao接口
 *
 * @author CHQ
 * @date 2019-05-24
 */
public interface UserDao {


    /**
     * 查询数据信息
     *
     * @param searchMap
     * @return
     */
    List<UserModel> selectList(Map<String, Object> searchMap);

    /**
     * 新增
     *
     * @param model
     * @return
     */
    Integer insert(UserModel model);

    /**
     * 更新
     *
     * @param model
     * @return
     */
    Integer update(UserModel model);

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
    UserModel getById(Integer id);

    /**
     * 保存用户角色信息
     *
     * @param list
     * @return
     */
    Integer saveRole(List list);

    /**
     * 删除用户角色信息
     *
     * @param userId
     * @return
     */
    Integer deleteRole(@Param(value = "userId") Integer userId);
}