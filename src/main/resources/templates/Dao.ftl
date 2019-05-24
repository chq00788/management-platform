package ${package_name}.dao;
import ${package_name}.model.${table_name}Model;
import java.util.Map;
import java.util.List;
/**
* 描述：${table_annotation} Dao接口
* @author ${author}
* @date ${date}
*/
public interface ${table_name}Dao {


    /**
    * 查询数据信息
    *
    * @param searchMap
    * @return
    */
    List<${table_name}Model> selectList(Map<String, Object> searchMap);

    /**
    * 新增
    *
    * @param model
    * @return
    */
    Integer insert(${table_name}Model model);

    /**
    * 更新
    *
    * @param model
    * @return
    */
    Integer update(${table_name}Model model);

    /**
    * 删除
    *
    * @param id
    * @return
    */
    Integer delete(Integer id);

    /**
    * 根据ID查询
    *
    * @param id
    * @return
    */
    ${table_name}Model getById(Integer id);

}