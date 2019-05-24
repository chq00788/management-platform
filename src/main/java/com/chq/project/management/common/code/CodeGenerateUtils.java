package com.chq.project.management.common.code;

import freemarker.template.Template;
import org.springframework.util.StringUtils;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * 代码生成器
 *
 * @author CHQ
 * @create 2017-09-27 10:38
 **/
public class CodeGenerateUtils {

    /**
     * 表名（需要手动修改）
     */
    private final String tableName = "t_system_perm";
    /**
     * 生成的文件名（需要手动修改）
     */
    private final String fileName = "Perm";

    /**
     * 包名（需要手动修改）
     */
    private final String packageName = "com.chq.project.management.system";
    /**
     * 表注解（需要手动修改）
     */
    private final String tableAnnotation = "权限管理";

    /**
     * 数据库连接信息
     */
    private final String URL = "jdbc:mysql://localhost:3306/management-platform?serverTimezone=UTC&characterEncoding=utf8&useSSL=true";
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * 生成java文件路径,手动改成自己的地址（需要手动修改）
     */
    private final String diskPath = "E:\\Projects\\project\\management-platform\\src\\main\\java\\com\\chq\\project\\management\\system";
    /**
     * 生成mapper.xml文件路径,手动改成自己的地址（需要手动修改）
     */
    private final String mapperDiskPath = "E:\\Projects\\project\\management-platform\\src\\main\\resources";

    /**
     * 生成文件前缀名
     */
    private final String changeTableName = replaceUnderLineAndUpperCase(fileName, true);


    /**
     * 主方法
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
        codeGenerateUtils.generate();
    }


    /**
     * 获取数据库连接
     *
     * @return
     * @throws Exception
     */
    public Connection getConnection() throws Exception {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }


    /**
     * 生成代码
     *
     * @throws Exception
     */
    public void generate() throws Exception {
        try {
            Map<String, Object> dataMap = new HashMap<>();
            //获取数据库表列信息
            getTableColumnsForMySQL(dataMap);
            //生成Mapper文件
            generateMapperFile(dataMap);
            //生成Dao文件
            generateDaoFile(dataMap);
            //生成Service文件
            generateServiceFile(dataMap);
            //生成Controller层文件
            generateControllerFile(dataMap);
            //生成Modal文件
            generateModelFile(dataMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

        }
    }

    /**
     * 生成实体类
     *
     * @param dataMap
     * @throws Exception
     */
    private void generateModelFile(Map<String, Object> dataMap) throws Exception {

        final String suffix = "Model.java";
        final String typePath = "\\model\\";
        final String path = diskPath + typePath;
        final String name = changeTableName + suffix;
        final String templateName = "Model.ftl";
        //生成文件
        File mapperFile = createFile(path, name);
        List<ColumnClass> columnClassList = new ArrayList<>();

        String[] columnNames = (String[]) dataMap.get("columnNames");
        String[] propertyNames = (String[]) dataMap.get("propertyNames");
        String[] columnTypeNames = (String[]) dataMap.get("columnTypeNames");
        String[] columnComments = (String[]) dataMap.get("columnComments");
        for (int i = 0; i < columnNames.length; i++) {
            ColumnClass columnClass = new ColumnClass();
            //获取字段名称
            columnClass.setColumnName(columnNames[i]);
            //获取字段类型
            columnClass.setColumnType(columnTypeNames[i]);
            //转换字段名称，如 sys_name 变成 sysName
            columnClass.setChangeColumnName(propertyNames[i]);
            //字段在数据库的注释
            columnClass.setColumnComment(columnComments[i]);
            columnClassList.add(columnClass);
        }
        dataMap.put("model_column", columnClassList);
        generateFileByTemplate(templateName, mapperFile, dataMap);

    }


    /**
     * 生成Controller文件
     *
     * @param dataMap
     * @throws Exception
     */
    private void generateControllerFile(Map<String, Object> dataMap) throws Exception {
        final String suffix = "Controller.java";
        final String typePath = "\\controller\\";
        final String path = diskPath + typePath;
        final String name = changeTableName + suffix;
        final String templateName = "Controller.ftl";
        //生成文件
        File mapperFile = createFile(path, name);
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }


    /**
     * 生成Service文件
     *
     * @param dataMap
     * @throws Exception
     */
    private void generateServiceFile(Map<String, Object> dataMap) throws Exception {
        final String suffix = "Service.java";
        final String typePath = "\\service\\";
        final String path = diskPath + typePath;
        final String name = changeTableName + suffix;
        final String templateName = "Service.ftl";
        //生成文件
        File mapperFile = createFile(path, name);
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    /**
     * 生成Dao文件
     *
     * @param dataMap
     * @throws Exception
     */
    private void generateDaoFile(Map<String, Object> dataMap) throws Exception {
        final String suffix = "Dao.java";
        final String typePath = "\\dao\\";
        final String path = diskPath + typePath;
        final String name = changeTableName + suffix;
        final String templateName = "Dao.ftl";
        //生成文件
        File mapperFile = createFile(path, name);
        generateFileByTemplate(templateName, mapperFile, dataMap);

    }

    /**
     * 生成Mapper文件
     *
     * @param dataMap
     * @throws Exception
     */
    private void generateMapperFile(Map<String, Object> dataMap) throws Exception {
        final String suffix = "Mapper.xml";
        final String typePath = "\\mapper\\";
        final String path = mapperDiskPath + typePath;
        final String name = changeTableName + suffix;
        final String templateName = "Mapper.ftl";
        //生成文件
        File mapperFile = createFile(path, name);
        generateFileByTemplate(templateName, mapperFile, dataMap);

    }

    /**
     * 自定义创建文件（加判空判断）
     *
     * @param filePath
     * @param fileName
     * @return
     * @throws Exception
     */
    private File createFile(String filePath, String fileName) throws Exception {
        //目录
        File path = new File(filePath);
        //文件
        File file = new File(path, fileName);
        // 判断文件夹是否存在
        if (!path.exists()) {
            // 创建目录
            path.mkdir();
        }
        //判断文件是否存在
        if (file.exists()) {
            // 创建文件
            file.createNewFile();
        }
        return file;
    }

    /**
     * 根据模板生成文件
     *
     * @param templateName
     * @param file
     * @param dataMap
     * @throws Exception
     */
    private void generateFileByTemplate(final String templateName, File file, Map<String, Object> dataMap) throws Exception {
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        dataMap.put("table_name_small", tableName);
        dataMap.put("table_name", changeTableName);
        dataMap.put("author", System.getProperties().getProperty("user.name"));
        dataMap.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        dataMap.put("package_name", packageName);
        dataMap.put("table_annotation", tableAnnotation);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap, out);

    }

    /**
     * UpperCamelCase 风格/lowerCamelCase 风格
     *
     * @param str
     * @param upper
     * @return
     */
    public String replaceUnderLineAndUpperCase(String str, boolean upper) {
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        int count = sb.indexOf("_");
        while (count != 0) {
            int num = sb.indexOf("_", count);
            count = num + 1;
            if (num != -1) {
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count, count + 1, ia + "");
            }
        }
        String result = sb.toString().replaceAll("_", "");
        if (upper) {
            return StringUtils.capitalize(result);
        }

        return result;
    }

    /**
     * MySQL专用
     *
     * @param dataMap
     */
    public void getTableColumnsForMySQL(Map<String, Object> dataMap) {
        try {
            Connection conn = getConnection();
            String sql = "select * from " + tableName;
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();
            // 获得所有列的数目及实际列数
            int columnCount = data.getColumnCount();
            String[] columnNames = new String[columnCount];
            String[] propertyNames = new String[columnCount];
            String[] columnTypeNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                // 获得指定列的列名
                String columnName = data.getColumnName(i);
                columnNames[i - 1] = columnName;
                propertyNames[i - 1] = replaceUnderLineAndUpperCase(columnName, false);
                columnTypeNames[i - 1] = data.getColumnTypeName(i);
                // 获得指定列的列值
                int columnType = data.getColumnType(i);
                // 获得指定列的数据类型名
                String columnTypeName = data.getColumnTypeName(i);
                // 所在的Catalog名字
                String catalogName = data.getCatalogName(i);
                // 对应数据类型的类
                String columnClassName = data.getColumnClassName(i);
                // 在数据库中类型的最大字符个数
                int columnDisplaySize = data.getColumnDisplaySize(i);
                // 默认的列的标题
                String columnLabel = data.getColumnLabel(i);
                // 获得列的模式
                String schemaName = data.getSchemaName(i);
                // 某列类型的精确度(类型的长度)
                int precision = data.getPrecision(i);
                // 小数点后的位数
                int scale = data.getScale(i);
                // 获取某列对应的表名
                String tableName = data.getTableName(i);
                // 是否自动递增
                boolean isAutoInctement = data.isAutoIncrement(i);
                // 在数据库中是否为货币型
                boolean isCurrency = data.isCurrency(i);
                // 是否为空
                int isNullable = data.isNullable(i);
                // 是否为只读
                boolean isReadOnly = data.isReadOnly(i);
                // 能否出现在where中
                boolean isSearchable = data.isSearchable(i);
            }
            dataMap.put("columnNames", columnNames);
            dataMap.put("propertyNames", propertyNames);
            dataMap.put("columnTypeNames", columnTypeNames);
            dataMap.put("columnComments", getComments());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库字段注释
     *
     * @return
     */
    public String[] getComments() throws Exception {
        Connection conn = getConnection();
        String sql = "show full columns from " + tableName;
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData data = rs.getMetaData();
        int columnCount = data.getColumnCount();

        String[] comments = new String[100];
        int i = 0;
        while (rs.next()) {
            comments[i] = rs.getString("Comment");
            i++;
        }
        return comments;
    }

}
