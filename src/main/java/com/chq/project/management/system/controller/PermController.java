package com.chq.project.management.system.controller;

import com.chq.project.management.common.entity.PageResponse;
import com.chq.project.management.common.entity.Response;
import com.chq.project.management.common.utils.SearchUtil;
import com.chq.project.management.system.model.PermModel;
import com.chq.project.management.system.service.PermService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 描述：权限管理控制层
 *
 * @author CHQ
 * @date 2019-05-24
 */
@Api(tags = {"权限管理操作接口"}, description = "权限管理操作接口")
@RestController
@RequestMapping("/system/perm")
public class PermController {

    @Autowired
    private PermService permService;


    @ApiOperation(value = "查询分页信息", notes = "查询分页信息", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页条数", required = true, paramType = "query", dataType = "int")
    })
    @RequestMapping(value = "/getListByPage")
    public PageResponse<List<PermModel>> getListByPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "limit", defaultValue = "10") int limit,
                                                       PermModel model) {

        PageHelper.startPage(page, limit);
        List<PermModel> list = permService.selectList(SearchUtil.getSearch(model));
        PageInfo<PermModel> pageInfo = new PageInfo<>(list);
        return PageResponse.ok(pageInfo);
    }

    @ApiOperation(value = "查询信息列表", notes = "查询信息列表", httpMethod = "GET")
    @RequestMapping(value = "/getList")
    public Response<List<PermModel>> getList(PermModel model) {
        List<PermModel> list = permService.selectList(SearchUtil.getSearch(model));
        return Response.ok(list);
    }

    @ApiOperation(value = "保存信息", notes = "保存信息", httpMethod = "POST")
    @RequestMapping(value = "/save")
    public Response<String> save(PermModel model) {
        permService.insert(model);
        return Response.ok("保存成功");
    }

    @ApiOperation(value = "更新信息", notes = "更新信息", httpMethod = "POST")
    @RequestMapping(value = "/update")
    public Response<String> update(PermModel model) {
        permService.update(model);
        return Response.ok("更新成功");
    }

    @ApiOperation(value = "删除信息", notes = "删除信息", httpMethod = "GET")
    @RequestMapping(value = "/delete")
    public Response<String> delete(@RequestParam(value = "id") Integer id) {
        permService.delete(id);
        return Response.ok("删除成功");
    }

    @ApiOperation(value = "根据ID查询信息", notes = "根据ID查询信息", httpMethod = "GET")
    @RequestMapping(value = "/getById")
    public Response<PermModel> getById(@RequestParam(value = "id") Integer id) {
        PermModel model = permService.getById(id);
        return Response.ok(model);
    }
}