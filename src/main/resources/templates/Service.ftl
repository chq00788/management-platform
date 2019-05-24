package ${package_name}.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${package_name}.dao.${table_name}Dao;
import ${package_name}.model.${table_name}Model;
import java.util.Map;
import java.util.List;

/**
* 描述：${table_annotation} 服务实现层
* @author ${author}
* @date ${date}
*/
@Service
public class ${table_name}Service{

    @Autowired
    private ${table_name}Dao ${table_name?uncap_first}Dao;

    /**
    * 查询数据
    *
    * @return
    */
    public List<${table_name}Model> selectList(Map<String, Object> searchMap) {
        return ${table_name?uncap_first}Dao.selectList(searchMap);
    }


    /**
    * 新增数据
    *
    * @param model
    */
    public void insert(${table_name}Model model) {
        ${table_name?uncap_first}Dao.insert(model);
    }

    /**
    * 更新数据
    *
    * @param model
    */
    public void update(${table_name}Model model) {
        ${table_name?uncap_first}Dao.update(model);
    }

    /**
    * 删除数据
    *
    * @param id
    */
    public void delete(Integer id) {
        ${table_name?uncap_first}Dao.delete(id);
    }

    /**
    * 根据ID查询数据
    *
    * @param id
    */
    public ${table_name}Model getById(Integer id) {
        return ${table_name?uncap_first}Dao.getById(id);
    }
}