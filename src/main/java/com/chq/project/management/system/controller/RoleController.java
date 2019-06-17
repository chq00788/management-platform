package com.chq.project.management.system.controller;

import com.chq.project.management.common.entity.PageResponse;
import com.chq.project.management.common.entity.Response;
import com.chq.project.management.common.enums.ResponseEnum;
import com.chq.project.management.common.utils.SearchUtil;
import com.chq.project.management.system.model.RoleModel;
import com.chq.project.management.system.service.RoleService;
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
 * 描述：角色管理控制层
 *
 * @author CHQ
 * @date 2019-05-24
 */
@Api(tags = {"角色管理操作接口"}, description = "角色管理操作接口")
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @ApiOperation(value = "查询分页信息", notes = "查询分页信息", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页条数", required = true, paramType = "query", dataType = "int")
    })
    @RequestMapping(value = "/getListByPage")
    public PageResponse<List<RoleModel>> getListByPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "limit", defaultValue = "10") int limit,
                                                       @RequestBody RoleModel model) {

        PageHelper.startPage(page, limit);
        List<RoleModel> list = roleService.selectList(SearchUtil.getSearch(model));
        PageInfo<RoleModel> pageInfo = new PageInfo<>(list);
        return PageResponse.ok(pageInfo);
    }

    @ApiOperation(value = "查询信息列表", notes = "查询信息列表", httpMethod = "GET")
    @RequestMapping(value = "/getList")
    public Response<List<RoleModel>> getList(RoleModel model) {
        List<RoleModel> list = roleService.selectList(SearchUtil.getSearch(model));
        return Response.ok(list);
    }

    @ApiOperation(value = "保存信息", notes = "保存信息", httpMethod = "POST")
    @RequestMapping(value = "/save")
    public Response<String> save(@RequestBody RoleModel model) {
        roleService.insert(model);
        return Response.ok("保存成功");
    }

    @ApiOperation(value = "更新信息", notes = "更新信息", httpMethod = "POST")
    @RequestMapping(value = "/update")
    public Response<String> update(@RequestBody RoleModel model) {
        roleService.update(model);
        return Response.ok("更新成功");
    }

    @ApiOperation(value = "更新角色状态信息", notes = "更新角色状态信息", httpMethod = "POST")
    @RequestMapping(value = "/updateStatus")
    public Response<String> updateStatus(@RequestBody RoleModel model) {
        roleService.updateStatus(model);
        return Response.ok("更新成功");
    }

    @ApiOperation(value = "删除信息", notes = "删除信息", httpMethod = "GET")
    @RequestMapping(value = "/delete")
    public Response<String> delete(@RequestParam(value = "id") Integer id) {
        roleService.delete(id);
        return Response.ok("删除成功");
    }

    @ApiOperation(value = "根据ID查询信息", notes = "根据ID查询信息", httpMethod = "GET")
    @RequestMapping(value = "/getById")
    public Response<RoleModel> getById(@RequestParam(value = "id") Integer id) {
        RoleModel model = roleService.getById(id);
        return Response.ok(model);
    }

    @ApiOperation(value = "保存角色权限信息", notes = "保存角色权限信息", httpMethod = "GET")
    @RequestMapping(value = "/savePerm")
    public Response<String> savePerm(@RequestBody RoleModel model) {
        if (null == model.getId() || null == model.getPermIds()) {
            return Response.fail(ResponseEnum.BAD_REQUEST);
        }
        roleService.savePerm(model.getId(), model.getPermIds());
        return Response.ok("保存成功");
    }

    @ApiOperation(value = "根据ID查询权限信息", notes = "根据ID查询权限信息", httpMethod = "GET")
    @RequestMapping(value = "/getPerm")
    public Response<List<Integer>> getPerm(@RequestParam(value = "id") Integer id) {
        List<Integer> perms = roleService.getPermsByRoleId(id);
        return Response.ok(perms);
    }

    @ApiOperation(value = "根据用户查询角色信息", notes = "根据用户查询角色信息", httpMethod = "GET")
    @RequestMapping(value = "/getListByUserId")
    public Response<List<RoleModel>> getListByUserId(@RequestParam Integer id) {
        if (null == id) {
            return Response.fail(ResponseEnum.BAD_REQUEST);
        }
        List<RoleModel> list = roleService.selectListByUserId(id);
        return Response.ok(list);
    }
}