package ${package_name}.model;

import io.swagger.annotations.ApiModelProperty;

<#if model_column?exists>
    <#list model_column as model>
        <#if model.columnType = 'DATETIME' || model.columnType = 'TIMESTAMP'>
        import com.fasterxml.jackson.annotation.JsonFormat;
        import java.util.Date;
        </#if>
    </#list>
</#if>

/**
* 描述：${table_annotation}实体类
* @author ${author}
* @date ${date}
*/
public class ${table_name}Model {

<#if model_column?exists>
    <#list model_column as model>

    @ApiModelProperty(value = "${model.columnComment!}")
    <#if model.columnType = 'BIGINT'>
    private Long ${model.changeColumnName};
    </#if>
    <#if model.columnType = 'INT'>
    private Integer ${model.changeColumnName};
    </#if>
    <#if model.columnType = 'VARCHAR'>
    private String ${model.changeColumnName};
    </#if>
    <#if model.columnType = 'DATETIME' || model.columnType = 'TIMESTAMP'>
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:dd")
    private Date ${model.changeColumnName};
    </#if>
    <#if  model.columnType = 'DATE'>
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date ${model.changeColumnName};
    </#if>
    <#if model.columnType = 'FLOAT'>
    private Float ${model.changeColumnName};
    </#if>
    <#if model.columnType = 'DOUBLE'>
    private Double ${model.changeColumnName};
    </#if>
    <#if model.columnType = 'DECIMAL'>
    private BigDecimal ${model.changeColumnName};
    </#if>
    <#if model.columnType = 'CHAR'>
    private char ${model.changeColumnName};
    </#if>
    </#list>
</#if>

    /**
    * 排序字段默认为id
    */
    private String sortCode = "id";

    /**
    * 排序规则默认为降序排列(DESC/ASC)
    */
    private String sortRole = "DESC";

<#if model_column?exists>
    <#list model_column as model>

    <#if model.columnType = 'BIGINT'>
    public Long get${model.changeColumnName?cap_first}() {
    return ${model.changeColumnName};
    }

    public void set${model.changeColumnName?cap_first}(Long ${model.changeColumnName}) {
    this.${model.changeColumnName} = ${model.changeColumnName};
    }
    </#if>
    <#if model.columnType = 'INT'>
    public Integer get${model.changeColumnName?cap_first}() {
    return ${model.changeColumnName};
    }

    public void set${model.changeColumnName?cap_first}(Integer ${model.changeColumnName}) {
    this.${model.changeColumnName} = ${model.changeColumnName};
    }
    </#if>
    <#if model.columnType = 'VARCHAR'>
    public String get${model.changeColumnName?cap_first}() {
    return ${model.changeColumnName};
    }

    public void set${model.changeColumnName?cap_first}(String ${model.changeColumnName}) {
    this.${model.changeColumnName} = ${model.changeColumnName};
    }
    </#if>
    <#if model.columnType = 'DATETIME' || model.columnType = 'TIMESTAMP'>
    public Date get${model.changeColumnName?cap_first}() {
    return ${model.changeColumnName};
    }

    public void set${model.changeColumnName?cap_first}(Date ${model.changeColumnName}) {
    this.${model.changeColumnName} = ${model.changeColumnName};
    }
    </#if>
    <#if model.columnType = 'FLOAT'>
    public Float get${model.changeColumnName?cap_first}() {
    return ${model.changeColumnName};
    }

    public void set${model.changeColumnName?cap_first}(Float ${model.changeColumnName}) {
    this.${model.changeColumnName} = ${model.changeColumnName};
    }
    </#if>
    <#if model.columnType = 'DOUBLE'>
    public Double get${model.changeColumnName?cap_first}() {
    return ${model.changeColumnName};
    }

    public void set${model.changeColumnName?cap_first}(Double ${model.changeColumnName}) {
    this.${model.changeColumnName} = ${model.changeColumnName};
    }
    </#if>
    <#if model.columnType = 'CHAR'>
    public char get${model.changeColumnName?cap_first}() {
    return ${model.changeColumnName};
    }

    public void set${model.changeColumnName?cap_first}(char ${model.changeColumnName}) {
    this.${model.changeColumnName} = ${model.changeColumnName};
    }
    </#if>
    </#list>
</#if>
    public String getSortRole() {
    return sortRole;
    }

    public void setSortRole(String sortRole) {
    this.sortRole = sortRole;
    }

    public String getSortCode() {
    return sortCode;
    }

    public void setSortCode(String sortCode) {
    this.sortCode = sortCode;
    }
}