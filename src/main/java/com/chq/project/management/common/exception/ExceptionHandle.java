package com.chq.project.management.common.exception;

import com.chq.project.management.common.entity.Response;
import com.chq.project.management.common.enums.ResponseEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author CHQ
 * @Description
 * @date 2019/5/24
 */
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response handler(Exception e) {
        e.printStackTrace();
        return Response.fail(ResponseEnum.INTERNAL_SERVER_ERROR);
    }
}
