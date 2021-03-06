package com.chq.project.management.common.config;


import com.chq.project.management.common.filter.JwtAuthenticationFilter;
import com.chq.project.management.common.filter.JwtLoginFilter;
import com.chq.project.management.common.handle.AuthEntryPoint;
import com.chq.project.management.common.handle.RestAuthenticationAccessDeniedHandler;
import com.chq.project.management.common.utils.RedisUtils;
import com.chq.project.management.system.dao.RoleDao;
import com.chq.project.management.system.service.UserDetailsServiceImpl;
import com.chq.project.management.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author CHQ
 * @Description
 * @date 2019/5/9
 */

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RoleDao roleDao;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .antMatchers("/logout").permitAll()
                .anyRequest().authenticated()
                .anyRequest()
                .access("@rbacAuthorityService.hasPermission(request,authentication)")
                .and()
                .addFilter(new JwtLoginFilter(authenticationManager(),userService))
                .addFilter(new JwtAuthenticationFilter(authenticationManager(),roleDao,redisUtils))
                .exceptionHandling().accessDeniedHandler(new RestAuthenticationAccessDeniedHandler())
                .authenticationEntryPoint(new AuthEntryPoint());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
