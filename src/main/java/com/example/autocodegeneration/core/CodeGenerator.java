package com.example.autocodegeneration.core;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.Data;
import lombok.experimental.Accessors;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @auther ice.deng
 * @create 2020-02-19 10:51 下午
 */
@Data
@Accessors(chain = true)
public class CodeGenerator {
    /**
     * 项目模块名称
     */
    private String projectModuleName = "";
    /**
     * 用户名
     */
    private String userName = "root";
    /**
     * 密码
     */
    private String password;
    /**
     * 驱动名称
     */
    private String driverName = "com.mysql.cj.jdbc.Driver";
    /**
     * 驱动URL
     */
    private String driverUrl;

    /**
     * 生成的类路径
     */
    private String projectPackagePath;

    /**
     * 项目主包路径
     */
    private String parentPackage = "com.jd";

    /**
     * 包名称
     */
    private String packageController = "controller";

    // ############################ 自定义配置部分 start ############################
    /**
     * 模块名称
     */
    private String moduleName;
    /**
     * 作者
     */
    private String author;
    /**
     * 生成的表名称 需要包含的表名，允许正则表达式（与exclude二选一配置）
     */
    private String tableName;
    /**
     * 主键数据库列名称
     */
    private String pkIdColumnName = "id";
    /**
     * 代码生成策略 true：All/false:SIMPLE
     * 0. SIMPLE 生成最基本的代码
     * 1. NORMAL 生成普通代码
     * 2. ALL 生成所有的代码
     */
    private GeneratorStrategy generatorStrategy = GeneratorStrategy.ALL;

    /**
     * 生成策略
     */
    public enum GeneratorStrategy {
        SIMPLE, NORMAL, ALL
    }

    /**
     * 分页列表查询是否排序 true：有排序参数/false：无
     */
    private boolean pageListOrder = false;
    /**
     * 是否生成validation校验，true：生成/false：不生成
     */
    private boolean paramValidation = true;

    /**
     * 是否生成实体类
     */
    private boolean generatorEntity;
    /**
     * 是否生成控制器
     */
    private boolean generatorController;
    /**
     * 是否生成service接口
     */
    private boolean generatorService;
    /**
     * 是否生成service实现类
     */
    private boolean generatorServiceImpl;
    /**
     * 是否生成Mapper
     */
    private boolean generatorMapper;
    /**
     * 是否生成Mapper XML
     */
    private boolean generatorMapperXml;
    /**
     * 是否生成查询参数
     */
    private boolean generatorQueryParam;
    /**
     * 是否生成查询VO
     */
    private boolean generatorQueryVo;
    /**
     * 是否生成Shiro RequiresPermissions 注解
     */
    private boolean requiresPermissions;
    // ############################ 自定义配置部分 end ############################

    /**
     * 实体父类
     */
    private String superEntity;
    /**
     * 控制器父类
     */
    private String superController;
    /**
     * service父接口
     */
    private String superService;
    /**
     * service实现父类
     */
    private String superServiceImpl;
    /**
     * 项目路径
     */
    private String projectPath;
    /**
     * 查询参数父类
     */
    private String superQueryParam;
    /**
     * 实体父类实体列表
     */
    private String[] superEntityCommonColumns = new String[]{};

    // 公共类包路径
    /**
     * 公共id参数路径
     */
    private String commonIdParam;
    /**
     * 公共结果集路径
     */
    private String commonApiResult;
    /**
     * 公共排序枚举
     */
    private String commonOrderEnum;
    /**
     * 公共排序查询参数
     */
    private String commonOrderQueryParam;
    /**
     * 公共分页对象
     */
    private String commonPaging;

    /**
     * 是否文件覆盖
     */
    private boolean fileOverride;
    /**
     * 是否在xml中添加二级缓存配置
     */
    private boolean xmlEnableCache;
    /**
     * 启用swagger注解
     */
    private boolean swagger2;

    /**
     * 初始化变量
     */
    public void init() {

        // 多模块项目，需加上模块名称
        if (StrUtil.isNotEmpty(projectModuleName)) {
            projectModuleName = "/" + projectModuleName;
        } else {
            projectModuleName = "";
        }
    }

    /**
     * 生成代码
     */
    public void generator() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
        // 多模块项目，需加上模块名称

        gc.setOutputDir(projectPath + projectModuleName + "/src/main/java");

        gc.setAuthor(author);               // 开发人员
        gc.setOpen(false);                  // 是否打开输出目录
        gc.setEnableCache(xmlEnableCache);  // 是否在xml中添加二级缓存配置
        gc.setSwagger2(swagger2);           // 启用swagger注解
        gc.setActiveRecord(true);           // 开启 ActiveRecord 模式
        gc.setBaseResultMap(true);          // 开启 BaseResultMap
        gc.setBaseColumnList(false);        // 开启 baseColumnList
        gc.setIdType(IdType.AUTO);          // 主键类型:ID_WORKER
        gc.setFileOverride(fileOverride);   // 是否覆盖已有文件
        gc.setDateType(DateType.ONLY_DATE); // 设置日期类型为Date
        //gc.setServiceName("%sService");     // service 命名方式，注意 %s 会自动填充表实体属性！
        //gc.setEntityName("%sEntity");       // 实体命名方式 默认值：null 例如：%sEntity 生成 UserEntity
        //gc.setMapperName("");               // mapper 命名方式
        //gc.setXmlName("");                  // Mapper xml 命名方式
        //gc.setServiceImplName("");          // service impl 命名方式
        //gc.setControllerName("");           // controller 命名方式
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(driverUrl);
        // dsc.setSchemaName("public");
        dsc.setDriverName(driverName);
        dsc.setUsername(userName);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //父包模块名
        pc.setModuleName(moduleName);
        // 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setParent(parentPackage);
        pc.setController(packageController);
        // Entity包名
        pc.setEntity("model");
        // Service包名
        //pc.setService("");
        // Service Impl包名
        //pc.setServiceImpl("");
        // Mapper包名
        //pc.setMapper("");
        // Mapper XML包名
        //pc.setXml("");
        // Controller包名
        //pc.setController("");
        // 路径配置信息
        //pc.setPathInfo();
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

//                String camelTableName = underlineToCamel(tableName);
//                String pascalTableName = underlineToPascal(tableName);
//                String colonTableName = underlineToColon(tableName);
//
                Map<String, Object> map = new HashMap<>();
//                map.put("customField", "Hello " + this.getConfig().getGlobalConfig().getAuthor());
//                // 查询参数包路径
//                String queryParamPackage = parentPackage + StringPool.DOT + pc.getModuleName() + ".param";
//                map.put("queryParamPackage", queryParamPackage);
//                // 查询参数类路径
//                map.put("queryParamPath", queryParamPackage + StringPool.DOT + pascalTableName + "QueryParam");
//                // 查询参数共公包路径
//                map.put("queryParamCommonPath", superQueryParam);
//                // 查询参数共公包路径
//                map.put("idParamPath", commonIdParam);
//                // 响应结果包路径
//                String queryVoPackage = parentPackage + StringPool.DOT + pc.getModuleName() + ".vo";
//                map.put("queryVoPackage", queryVoPackage);
//                // 响应结果类路径
//                map.put("queryVoPath", queryVoPackage + StringPool.DOT + pascalTableName + "QueryVo");
//                // 实体对象名称
//                map.put("entityObjectName", camelTableName);
//                // service对象名称
//                map.put("serviceObjectName", camelTableName + "Service");
//                // mapper对象名称
//                map.put("mapperObjectName", camelTableName + "Mapper");
//                // 主键ID列名
//                map.put("pkIdColumnName", pkIdColumnName);
//                // 主键ID驼峰名称
//                map.put("pkIdCamelName", underlineToCamel(pkIdColumnName));
//                // 导入分页类
//                map.put("paging", commonPaging);
//                // 导入排序枚举
//                map.put("orderEnum", commonOrderEnum);
//                // ApiResult
//                map.put("apiResult", commonApiResult);
//                // 分页列表查询是否排序
//                map.put("pageListOrder", pageListOrder);
//                // 导入排序查询参数类
//                map.put("orderQueryParamPath", commonOrderQueryParam);
//                // 代码生成策略
//                map.put("generatorStrategy", generatorStrategy);
//                // 代码Validation校验
//                map.put("paramValidation", paramValidation);
//                // 冒号连接的表名称
//                map.put("colonTableName", colonTableName);
//                // 是否生成Shiro RequiresPermissions注解
//                map.put("requiresPermissions", requiresPermissions);
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();

        // 生成mapper xml
        if (generatorMapperXml) {
            focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return projectPath
                            + projectModuleName
                            + "/src/main/resources/mapper/"
                            + pc.getModuleName()
                            + "/" + tableInfo.getEntityName()
                            + "Mapper" + StringPool.DOT_XML;
                }
            });
        }

        // 自定义queryParam模板
        if (generatorQueryParam) {
            focList.add(new FileOutConfig("/templates/queryParam.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return projectPath + projectModuleName + "/src/main/java/" + projectPackagePath + "/" + pc.getModuleName() + "/param/" + tableInfo.getEntityName() + "QueryParam" + StringPool.DOT_JAVA;
                }
            });
        }

        // 自定义queryVo模板
        if (generatorQueryVo) {
            focList.add(new FileOutConfig("/templates/queryVo.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return projectPath + projectModuleName + "/src/main/java/" + projectPackagePath + "/" + pc.getModuleName() + "/vo/" + tableInfo.getEntityName() + "QueryVo" + StringPool.DOT_JAVA;
                }
            });
        }

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 模版生成配置，设置为空，表示不生成
        TemplateConfig templateConfig = new TemplateConfig();
        // xml使用自定义输出
//        templateConfig.setXml("/templates/mapper.xml");
        templateConfig.setXml(null);
        // 是否生成entity
        if (generatorEntity) {
            templateConfig.setEntity("/templates/entity.java");
        }
        // 是否生成controller
        if (generatorController) {
            templateConfig.setController("/templates/controller.java");
        }
        // 是否生成service
        if (generatorService) {
            templateConfig.setService("/templates/service.java");
        }
        // 是否生成serviceImpl
        if (generatorServiceImpl) {
            templateConfig.setServiceImpl("/templates/serviceImpl.java");
        }
        // 是否生成mapper
        if (generatorMapper) {
            templateConfig.setMapper("/templates/mapper.java");
        }
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 是否大写命名
        strategy.setCapitalMode(false);
        // 是否跳过视图
        strategy.setSkipView(true);
        // 驼峰=underline_to_camel；不变=no_change
        // 数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 表前缀
        /**
         * 注意，根据实际情况，进行设置
         * 当表名称的前缀和模块名称一样时，会去掉表的前缀
         * 比如模块名称为user,表明为user_info,则生成的实体名称是Info.java,一定要注意
         */
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.setTablePrefix("");
        // 字段前缀
//		strategy.setFieldPrefix("");
        // 自定义继承的Entity类全称，带包名
        if (StrUtil.isNotEmpty(superEntity)) {
            strategy.setSuperEntityClass(superEntity);
        }
        // 自定义基础的Entity类，公共字段
        strategy.setSuperEntityColumns(superEntityCommonColumns);
        // 自定义继承的Mapper类全称，带包名
//		strategy.setSuperMapperClass("");
        // 自定义继承的Service类全称，带包名
        if (StrUtil.isNotEmpty(superService)) {
            strategy.setSuperServiceClass(superService);
        }
        // 自定义继承的ServiceImpl类全称，带包名
        if (StrUtil.isNotEmpty(superServiceImpl)) {
            strategy.setSuperServiceImplClass(superServiceImpl);
        }
        // 自定义继承的Controller类全称，带包名
        strategy.setSuperControllerClass(superController);
        // 需要包含的表名，允许正则表达式（与exclude二选一配置）
        strategy.setInclude(tableName);
        // 需要排除的表名，允许正则表达式
        // strategy.setExclude("");
        // 【实体】是否生成字段常量（默认 false）
        strategy.setEntityColumnConstant(false);
        // 【实体】是否为构建者模型（默认 false）
        strategy.setEntityBuilderModel(false);
        // 【实体】是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        // Boolean类型字段是否移除is前缀（默认 false）
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        // 生成 @RestController 控制器
        strategy.setRestControllerStyle(false);
        // 驼峰转字符
        strategy.setControllerMappingHyphenStyle(false);
        //是否生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(false);
        // 乐观锁属性名称
        strategy.setVersionFieldName("version");
        // 逻辑删除属性名称
        strategy.setLogicDeleteFieldName("cut_off");
        // 表填充字段
        // strategy.setTableFillList();
        mpg.setStrategy(strategy);

        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        // mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        mpg.execute();
    }

    /**
     * 下划线专程驼峰命名
     * sys_user --> sysUser
     *
     * @param underline
     * @return
     */
    public static String underlineToCamel(String underline) {
        if (StringUtils.isNotBlank(underline)) {
            return NamingStrategy.underlineToCamel(underline);
        }
        return null;
    }

    /**
     * 下划线转换成帕斯卡命名
     * sys_user --> SysUser
     *
     * @param underline
     * @return
     */
    public static String underlineToPascal(String underline) {
        if (StringUtils.isNotBlank(underline)) {
            return NamingStrategy.capitalFirst(NamingStrategy.underlineToCamel(underline));
        }
        return null;
    }

    /**
     * 下划线转换成冒号连接命名
     * sys_user --> sys:user
     *
     * @param underline
     * @return
     */
    public static String underlineToColon(String underline) {
        if (StringUtils.isNotBlank(underline)) {
            String string = underline.toLowerCase();
            return string.replaceAll("_", ":");
        }
        return null;
    }
}
