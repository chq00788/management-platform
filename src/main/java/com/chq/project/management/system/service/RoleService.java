package com.chq.project.management.system.service;

import com.chq.project.management.system.dao.RoleDao;
import com.chq.project.management.system.model.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 描述：角色管理 服务实现层
 *
 * @author CHQ
 * @date 2019-05-24
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 查询数据
     *
     * @return
     */
    public List<RoleModel> selectList(Map<String, Object> searchMap) {
        return roleDao.selectList(searchMap);
    }


    /**
     * 新增数据
     *
     * @param model
     */
    public void insert(RoleModel model) {
        model.setStatus("0");
        model.setDeleted(0);
        model.setCreateTime(new Date());
        roleDao.insert(model);
    }

    /**
     * 更新数据
     *
     * @param model
     */
    public void update(RoleModel model) {
        roleDao.update(model);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public void delete(Integer id) {
        roleDao.delete(id);
    }

    /**
     * 根据ID查询数据
     *
     * @param id
     */
    public RoleModel getById(Integer id) {
        return roleDao.getById(id);
    }

    /**
     * 保存角色权限信息
     *
     * @param id
     * @param permIds
     */
    @Transactional(rollbackFor = Exception.class)
    public void savePerm(Integer id, Integer[] permIds) {
        //删除原来的权限信息
        roleDao.deletePerm(id);
        //保存新的权限信息
        List<Map<String, Integer>> list = new ArrayList<>();
        for (Integer permId : permIds) {
            Map<String, Integer> map = new HashMap<>(permIds.length);
            map.put("roleId", id);
            map.put("permId", permId);
            list.add(map);
        }
        roleDao.savePerm(list);
    }

    /**
     * 根据用户查询角色信息
     *
     * @param userId
     * @return
     */
    public List<RoleModel> selectListByUserId(Integer userId) {
        return roleDao.selectListByUserId(userId);
    }
}