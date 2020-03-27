package com.whaa.cloudalibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * created by @author wangzelong 2020/3/25 20:51
 */
@Configuration
@MapperScan(basePackages = {"com.whaa.cloudalibaba.mapper"})
public class MybatisConfig {
}
