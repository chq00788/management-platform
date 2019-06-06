package com.chq.project.management.common.exception;

import com.chq.project.management.common.entity.Response;
import com.chq.project.management.common.enums.ResponseEnum;
import com.chq.project.management.common.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CHQ
 * @Description
 * @date 2019/5/24
 */
@ControllerAdvice
public class ExceptionHandle {

    /**
     * 404
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    public Response noHandlerFoundHandler(Exception e) {
        e.printStackTrace();
        return Response.fail(ResponseEnum.NOT_FOUND);
    }

    /**
     * token 过期异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = ExpiredJwtException.class)
    @ResponseBody
    public Response expiredJwtHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        String header = request.getHeader(JwtTokenUtil.TOKEN_HEADER);
        String token = header.replace(JwtTokenUtil.TOKEN_PREFIX, "");
        String username = response.getHeader("username");
        System.out.println(username);
        e.printStackTrace();
        return Response.fail(ResponseEnum.JWT_TOKEN_EXPIRED);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response handler(Exception e) {
        e.printStackTrace();
        return Response.fail(ResponseEnum.INTERNAL_SERVER_ERROR);
    }
}
