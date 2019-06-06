package com.chq.project.management.common.utils;

import com.alibaba.fastjson.JSON;
import com.chq.project.management.system.model.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CHQ
 * @Description JwtToken帮助类
 * @date 2019/5/9
 */

public class JwtTokenUtil {

    /**
     * header名称
     */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * token前缀
     */
    public static final String TOKEN_PREFIX = "Bearer";

    /**
     * 秘钥
     */
    public static final String SECRET = "myjwt";

    /**
     * 过期时间
     */
    public static final Long EXPIRATION = 60L;

    /**
     * 选择记住后过期时间
     */
    public static final Long REMEMBER_EXPIRATION = 604800L;

    /**
     * 生成token
     *
     * @param user
     * @return
     */
    public static String createToken(LoginUser user) {
        Long time = user.getRemember() ? REMEMBER_EXPIRATION : EXPIRATION;
        Map<String, Object> map = new HashMap<>(1);
        map.put("user", user);
        return Jwts.builder()
                .setClaims(map)
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + time * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public static String getUserName(String token) {
        return generateToken(token).getSubject();
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static Claims generateToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取userDTO
     *
     * @param token
     * @return
     */
    public static LoginUser getUserDTO(String token) {
        Claims claims = generateToken(token);
        Map<String, String> map = claims.get("user", Map.class);
        LoginUser userDTO = JSON.parseObject(JSON.toJSONString(map), LoginUser.class);
        return userDTO;
    }
}
