package com.chq.project.management.system.controller;

import com.chq.project.management.common.entity.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHQ
 * @Description
 * @date 2019/5/29
 */
@RestController
public class LoginController {

    @ApiOperation(value = "登出操作", notes = "登出操作", httpMethod = "POST")
    @RequestMapping(value = "/logout")
    public Response<String> logout() {

        return Response.ok("操作成功");
    }
}
