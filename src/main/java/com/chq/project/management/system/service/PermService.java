package com.chq.project.management.system.service;

import com.chq.project.management.common.utils.SearchUtil;
import com.chq.project.management.system.dao.PermDao;
import com.chq.project.management.system.model.PermModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 描述：权限管理 服务实现层
 *
 * @author CHQ
 * @date 2019-05-24
 */
@Service
public class PermService {

    @Autowired
    private PermDao permDao;

    /**
     * 查询数据
     *
     * @return
     */
    public List<PermModel> selectListForTable(Map<String, Object> searchMap) {
        List<PermModel> list = permDao.selectList(searchMap);
        return formatPermTree(list, 0);
    }

    /**
     *
     * @param searchMap
     * @return
     */
    public List<PermModel> selectList(Map<String, Object> searchMap) {
        return permDao.selectList(searchMap);
    }

    /**
     * 获取树形权限列表
     *
     * @return
     */
    public List<PermModel> getPermTree() {
        PermModel model = new PermModel();
        model.setStatus("0");
        Map<String, Object> searchMap = SearchUtil.getSearch(model);
        List<PermModel> list = permDao.selectList(searchMap);
        return formatPermTree(list, 0);
    }


    /**
     * 新增数据
     *
     * @param model
     */
    public void insert(PermModel model) {
        model.setStatus("0");
        model.setDeleted(0);
        model.setCreateTime(new Date());
        permDao.insert(model);
    }

    /**
     * 更新数据
     *
     * @param model
     */
    public void update(PermModel model) {
        PermModel perm = this.getById(model.getId());
        perm.setPermName(model.getPermName());
        perm.setPermCode(model.getPermCode());
        perm.setPermIcon(model.getPermIcon());
        perm.setPermUrl(model.getPermUrl());
        permDao.update(perm);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public void delete(Integer id) {
        PermModel perm = this.getById(id);
        perm.setDeleted(1);
        permDao.update(perm);
    }

    /**
     * 根据ID查询数据
     *
     * @param id
     */
    public PermModel getById(Integer id) {
        return permDao.getById(id);
    }

    private List<PermModel> formatPermTree(List<PermModel> list, int pid) {
        List<PermModel> tree = new ArrayList<>();
        for (PermModel perm : list) {
            if (perm.getPid() == pid) {
                perm.setChildren(formatPermTree(list, perm.getId()));
                tree.add(perm);
            }
        }
        return tree;
    }
}