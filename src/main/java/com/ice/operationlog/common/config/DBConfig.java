package com.ice.operationlog.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ice, You're very best
 * @date 2020/2/6 10:12
 * @desc 数据库配置
 */
@MapperScan(basePackages = "com.ice.operationlog.mapper")
@Configuration
public class DBConfig {

}
