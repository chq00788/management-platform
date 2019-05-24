package ${package_name}.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.chq.project.management.common.entity.PageResponse;
import com.chq.project.management.common.entity.Response;
import com.chq.project.management.common.utils.SearchUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ${package_name}.model.${table_name}Model;
import ${package_name}.service.${table_name}Service;

import java.util.List;


/**
* 描述：${table_annotation}控制层
* @author ${author}
* @date ${date}
*/
@Api(tags = {"${table_annotation}操作接口"}, description = "${table_annotation}操作接口")
@RestController
@RequestMapping("/${table_name?uncap_first}")
public class ${table_name}Controller {

    @Autowired
    private ${table_name}Service ${table_name?uncap_first}Service;


    @ApiOperation(value = "查询分页信息", notes = "查询分页信息",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页条数", required = true, paramType = "query", dataType = "int")
    })
    @RequestMapping(value = "/getListByPage")
    public PageResponse<List<${table_name}Model>> getListByPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                                             @RequestParam(value = "limit", defaultValue = "10") int limit,
                                                             ${table_name}Model model) {

            PageHelper.startPage(page, limit);
            List<${table_name}Model> list = ${table_name?uncap_first}Service.selectList(SearchUtil.getSearch(model));
            PageInfo<${table_name}Model> pageInfo = new PageInfo<>(list);
            return PageResponse.ok(pageInfo);
    }

    @ApiOperation(value = "查询信息列表", notes = "查询信息列表",httpMethod = "GET")
    @RequestMapping(value = "/getList")
    public Response<List<${table_name}Model>> getList(${table_name}Model model) {
            List<${table_name}Model> list = ${table_name?uncap_first}Service.selectList(SearchUtil.getSearch(model));
            return Response.ok(list);
    }

    @ApiOperation(value = "保存信息", notes = "保存信息",httpMethod = "POST")
    @RequestMapping(value = "/save")
    public Response<String> save(${table_name}Model model) {
            ${table_name?uncap_first}Service.insert(model);
            return Response.ok("保存成功");
    }

    @ApiOperation(value = "更新信息", notes = "更新信息",httpMethod = "POST")
    @RequestMapping(value = "/update")
    public Response<String> update(${table_name}Model model) {
            ${table_name?uncap_first}Service.update(model);
            return Response.ok("更新成功");
    }

    @ApiOperation(value = "删除信息", notes = "删除信息",httpMethod = "GET")
    @RequestMapping(value = "/delete")
    public Response<String> delete(@RequestParam(value = "id") Integer id) {
            ${table_name?uncap_first}Service.delete(id);
            return Response.ok("删除成功");
    }

    @ApiOperation(value = "根据ID查询信息", notes = "根据ID查询信息",httpMethod = "GET")
    @RequestMapping(value = "/getById")
    public Response<${table_name}Model> getById(@RequestParam(value = "id") Integer id) {
            ${table_name}Model model = ${table_name?uncap_first}Service.getById(id);
            return Response.ok(model);
    }
}