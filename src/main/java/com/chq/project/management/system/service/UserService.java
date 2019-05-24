package com.chq.project.management.system.service;

import com.chq.project.management.system.dao.UserDao;
import com.chq.project.management.system.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 描述：用户管理 服务实现层
 *
 * @author CHQ
 * @date 2019-05-24
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 查询数据
     *
     * @return
     */
    public List<UserModel> selectList(Map<String, Object> searchMap) {
        return userDao.selectList(searchMap);
    }


    /**
     * 新增数据
     *
     * @param model
     */
    public void insert(UserModel model) {
        model.setStatus("0");
        model.setDeleted(0);
        model.setCreateTime(new Date());
        userDao.insert(model);
    }

    /**
     * 更新数据
     *
     * @param model
     */
    public void update(UserModel model) {
        userDao.update(model);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public void delete(Integer id) {
        userDao.delete(id);
    }

    /**
     * 根据ID查询数据
     *
     * @param id
     */
    public UserModel getById(Integer id) {
        return userDao.getById(id);
    }

    /**
     * 保存用户角色信息
     *
     * @param id
     * @param roleIds
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(Integer id, Integer[] roleIds) {
        //删除原来的角色信息
        userDao.deleteRole(id);
        //保存信息角色信息
        List<Map<String, Integer>> list = new ArrayList<>();
        for (Integer roleId : roleIds) {
            Map<String, Integer> map = new HashMap<>(roleIds.length);
            map.put("userId", id);
            map.put("roleId", roleId);
            list.add(map);
        }
        userDao.saveRole(list);
    }

}