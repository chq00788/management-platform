package com.chq.project.management.system.controller;

import com.chq.project.management.common.entity.PageResponse;
import com.chq.project.management.common.entity.Response;
import com.chq.project.management.common.enums.ResponseEnum;
import com.chq.project.management.common.utils.JwtTokenUtil;
import com.chq.project.management.common.utils.SearchUtil;
import com.chq.project.management.system.model.UserModel;
import com.chq.project.management.system.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 描述：用户管理控制层
 *
 * @author CHQ
 * @date 2019-05-24
 */
@Api(tags = {"用户管理操作接口"}, description = "用户管理操作接口")
@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "查询分页信息", notes = "查询分页信息", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页条数", required = true, paramType = "query", dataType = "int")
    })
    @RequestMapping(value = "/getListByPage")
    public PageResponse<List<UserModel>> getListByPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "limit", defaultValue = "10") int limit,
                                                       @RequestBody UserModel model) {

        PageHelper.startPage(page, limit);
        List<UserModel> list = userService.selectList(SearchUtil.getSearch(model));
        PageInfo<UserModel> pageInfo = new PageInfo<>(list);
        return PageResponse.ok(pageInfo);
    }

    @ApiOperation(value = "查询信息列表", notes = "查询信息列表", httpMethod = "GET")
    @RequestMapping(value = "/getList")
    public Response<List<UserModel>> getList(UserModel model) {
        List<UserModel> list = userService.selectList(SearchUtil.getSearch(model));
        return Response.ok(list);
    }

    @ApiOperation(value = "保存信息", notes = "保存信息", httpMethod = "POST")
    @RequestMapping(value = "/save")
    public Response<String> save(@RequestBody UserModel model) {
        if (null != userService.getByUsername(model.getUsername())) {
            return Response.fail("账号已存在");
        }
        userService.insert(model);
        return Response.ok("保存成功");
    }

    @ApiOperation(value = "更新信息", notes = "更新信息", httpMethod = "POST")
    @RequestMapping(value = "/update")
    public Response<String> update(@RequestBody UserModel model) {
        userService.update(model);
        return Response.ok("更新成功");
    }

    @ApiOperation(value = "更新用户状态信息", notes = "更新用户状态信息", httpMethod = "POST")
    @RequestMapping(value = "/updateStatus")
    public Response<String> updateStatus(@RequestBody UserModel model) {
        userService.updateStatus(model);
        return Response.ok("更新成功");
    }

    @ApiOperation(value = "删除信息", notes = "删除信息", httpMethod = "GET")
    @RequestMapping(value = "/delete")
    public Response<String> delete(@RequestParam(value = "id") Integer id) {
        userService.delete(id);
        return Response.ok("删除成功");
    }

    @ApiOperation(value = "根据ID查询信息", notes = "根据ID查询信息", httpMethod = "GET")
    @RequestMapping(value = "/getById")
    public Response<UserModel> getById(@RequestParam(value = "id") Integer id) {
        UserModel model = userService.getById(id);
        return Response.ok(model);
    }

    @ApiOperation(value = "保存用户角色信息", notes = "保存用户角色信息", httpMethod = "POST")
    @RequestMapping(value = "/saveRole")
    public Response<String> saveRole(@RequestBody UserModel model) {
        if (null == model.getId() || null == model.getRoleIds()) {
            return Response.fail(ResponseEnum.BAD_REQUEST);
        }
        userService.saveRole(model.getId(), model.getRoleIds());
        return Response.ok("保存成功");
    }

    @ApiOperation(value = "查询用户角色信息", notes = "查询用户角色信息", httpMethod = "GET")
    @RequestMapping(value = "/getRole")
    public Response<List<Integer>> getRole(@RequestParam Integer id) {
        if (null == id) {
            return Response.fail(ResponseEnum.BAD_REQUEST);
        }
        List<Integer> roles = userService.getUserRole(id);
        return Response.ok(roles);
    }

    @ApiOperation(value = "根据token查询信息", notes = "根据token查询信息", httpMethod = "GET")
    @RequestMapping(value = "/getByToken")
    public Response<UserModel> getByToken(@RequestParam(value = "token") String token) {
        token = token.replace(JwtTokenUtil.TOKEN_PREFIX, "");
        String userName = JwtTokenUtil.getUserName(token);
        UserModel model = userService.getInfoByUsername(userName);
        model.setPassword("");
        model.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Response.ok(model);
    }
}