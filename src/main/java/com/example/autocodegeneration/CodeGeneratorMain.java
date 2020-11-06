package com.example.autocodegeneration;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.example.autocodegeneration.core.CodeGenerator;


import java.util.Scanner;

/**
 * CodeGenerator
 *
 * @auther ice.deng
 * @create 2020-02-19 11:45 下午
 */
public class CodeGeneratorMain {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StrUtil.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {


        String dirName = scanner("目录名称");
        String moduleName = "";
        String[] tables = scanner("表名，多个英文逗号分割").split(",");

        CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.setProjectPath("C:\\jd\\code\\wxdks");
//        codeGenerator.setProjectPath("C:\\codegenerate");
        // 数据库
        codeGenerator
        		.setDriverName("com.mysql.cj.jdbc.Driver")
        		.setDriverUrl("jdbc:mysql://192.168.1.250:3306/wxdks?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8")
                .setUserName("root")
                .setPassword("123456");

        // 包信息
        codeGenerator.
                setProjectPackagePath("com/c3/" + dirName)
                .setParentPackage("com.c3." + dirName);

        // 组件作者等配置
        codeGenerator
                .setModuleName(moduleName)
                .setAuthor("elis")
                .setPkIdColumnName("id");

        codeGenerator.
                setGeneratorQueryParam(false)
                .setGeneratorQueryVo(false)
                .setGeneratorController(false);


        // 生成业务相关代码
        codeGenerator
        		.setGeneratorController(true)
                .setGeneratorEntity(true)
                .setGeneratorService(true)
                .setGeneratorServiceImpl(true)
                .setGeneratorMapper(true)
                .setGeneratorMapperXml(true);

        // 是否生成Shiro RequiresPermissions注解
        codeGenerator.setRequiresPermissions(false);

        // 是否覆盖已有文件
        codeGenerator.setFileOverride(false);

        // swagger2
        codeGenerator.setSwagger2(false);

        // 初始化公共变量
        codeGenerator.init();

        // 循环生成
        for (String table : tables) {
            // 设置需要生成的表名称
            codeGenerator.setTableName(table);
            // 生成代码
            codeGenerator.generator();
        }
    }
}
