package com.chq.project.management.common.filter;


import com.chq.project.management.common.entity.Response;
import com.chq.project.management.common.utils.JwtTokenUtil;
import com.chq.project.management.common.utils.RedisUtils;
import com.chq.project.management.common.utils.ResponseUtil;
import com.chq.project.management.system.dao.RoleDao;
import com.chq.project.management.system.model.LoginUser;
import com.chq.project.management.system.model.RoleModel;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CHQ
 * @Description
 * @date 2019/5/9
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private RoleDao roleDao;

    private RedisUtils redisUtils;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, RoleDao roleDao, RedisUtils redisUtils) {
        super(authenticationManager);
        this.roleDao = roleDao;
        this.redisUtils = redisUtils;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(JwtTokenUtil.TOKEN_HEADER);
        String username = request.getHeader("username");
        //当token为空或格式错误时 直接放行
        if (header == null || !header.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(header);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (Exception e) {
            if (e instanceof ExpiredJwtException) {
                LoginUser user = new LoginUser();
                user.setUsername(username);
                List<RoleModel> roles = roleDao.selectRoleByUsername(username);
                List<String> roleCodes = new ArrayList<>();
                for (RoleModel role : roles) {
                    roleCodes.add(role.getRoleCode().toUpperCase());
                }
                user.setRoles(roleCodes);
                String token = JwtTokenUtil.createToken(user);
                user.setToken(JwtTokenUtil.TOKEN_PREFIX + token);
                Response<LoginUser> result = new Response<>();
                result.setResult(user);
                result.setCode(1001);
                result.setMsg("登录过期,请重新登录");
                ResponseUtil.write(response, result);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 这里从token中获取用户信息并新建一个token
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String header) {

        String token = header.replace(JwtTokenUtil.TOKEN_PREFIX, "");
        String principal = JwtTokenUtil.getUserName(token);
        if (principal != null) {
            LoginUser userDTO = JwtTokenUtil.getUserDTO(token);
            return new UsernamePasswordAuthenticationToken(principal, null, userDTO.getAuthorities());
        }
        return null;
    }

}
