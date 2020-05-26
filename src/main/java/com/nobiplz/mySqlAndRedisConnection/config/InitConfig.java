package com.nobiplz.mySqlAndRedisConnection.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

// @MapperScan所扫描的要换成自己需要的mapper
@MapperScan("com.mybatis.repository.mapper*")
@Configuration
public class InitConfig {

}
