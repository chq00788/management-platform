package com.chq.project.management.common.filter;

import com.alibaba.fastjson.JSON;
import com.chq.project.management.common.entity.Response;
import com.chq.project.management.common.enums.ResponseEnum;
import com.chq.project.management.common.utils.JwtTokenUtil;
import com.chq.project.management.common.utils.ResponseUtil;
import com.chq.project.management.system.model.LoginForm;
import com.chq.project.management.system.model.LoginUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author CHQ
 * @Description 处理登录请求
 * @date 2019/5/9
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 请求登录
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginForm loginForm = new ObjectMapper().readValue(request.getInputStream(), LoginForm.class);
            checkLoginForm(loginForm, response);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(JSON.toJSONString(loginForm), loginForm.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            ResponseUtil.write(response, Response.fail(ResponseEnum.INTERNAL_SERVER_ERROR));
        }
        return null;
    }

    /**
     * 登录成功后
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        LoginUser user = (LoginUser) authResult.getPrincipal();

        //此处可以更新登最近一次录时间
        String token = JwtTokenUtil.createToken(user);
        //将token放置请求头返回
        response.addHeader(JwtTokenUtil.TOKEN_HEADER, JwtTokenUtil.TOKEN_PREFIX + token);
        user.setPassword("");
        ResponseUtil.write(response, Response.ok(user));
    }

    /**
     * 登录失败
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.write(response, Response.fail(failed.getMessage()));
    }

    /**
     * 校验参数
     *
     * @param loginForm
     */
    private void checkLoginForm(LoginForm loginForm, HttpServletResponse response) {
        if (StringUtils.isBlank(loginForm.getUsername())) {
            ResponseUtil.write(response, Response.fail("用户名不能为空"));
            return;
        }
        if (StringUtils.isBlank(loginForm.getPassword())) {
            ResponseUtil.write(response, Response.fail("密码不能为空"));
            return;
        }
    }


}
