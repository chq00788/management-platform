package com.chq.project.management.system.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chq.project.management.system.dao.RoleDao;
import com.chq.project.management.system.dao.UserDao;
import com.chq.project.management.system.model.LoginForm;
import com.chq.project.management.system.model.LoginUser;
import com.chq.project.management.system.model.RoleModel;
import com.chq.project.management.system.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CHQ
 * @Description
 * @date 2019/5/9
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        LoginForm loginForm = JSONObject.toJavaObject((JSON) JSON.parse(s),LoginForm.class);
        UserModel userModel = userDao.getByUsername(loginForm.getUsername());
        if (userModel == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        LoginUser user = new LoginUser();
        user.setUsername(userModel.getUsername());
        user.setPassword(userModel.getPassword());
        List<RoleModel> roles = roleDao.selectRoleByUsername(userModel.getUsername());
        List<String> roleCodes = new ArrayList<>();
        for (RoleModel role : roles) {
            roleCodes.add(role.getRoleCode().toUpperCase());
        }
        user.setRoles(roleCodes);
        return user;
    }
}
