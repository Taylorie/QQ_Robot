package com.robot.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author Roys
 * @create 2022-02-12 18:01
 * @Version：1.0
 * @Since:jdk1.8
 * @Description:
 */
public class Generator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/qq_robot?serverTimezone=UTC", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("yu") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E:\\project\\QQ_Robot\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.robot") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "E:\\project\\QQ_Robot\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("group_order");
//                    builder.addInclude("commodity", "customer", "customer_shopping_cart", "shopping_cart");
//                    builder.addInclude("order", "order_user");
//                    builder.addInclude("user_menu");
                    // 设置需要生成的表名
                    // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine())
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
