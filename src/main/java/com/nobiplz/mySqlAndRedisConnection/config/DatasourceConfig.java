package com.nobiplz.mySqlAndRedisConnection.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {
  @Bean
  public DataSource datasource() {
    return DataSourceBuilder.create()
      .driverClassName("com.mysql.cj.jdbc.Driver")
      .url("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC")
      .username("root")
      .password("orcl")
      .build();
  }
}
