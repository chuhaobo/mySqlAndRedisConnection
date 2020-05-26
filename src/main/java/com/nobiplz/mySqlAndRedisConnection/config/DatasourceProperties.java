package com.nobiplz.mySqlAndRedisConnection.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class DatasourceProperties {
  @Configuration
  @Profile("dev")
  @PropertySource("classpath:datasource-dev.properties")
  static class Dev {
  }

  @Configuration
  @Profile("pre")
  @PropertySource("classpath:datasource-pre.properties")
  static class Pre {
  }

  @Configuration
  @Profile("pro")
  @PropertySource("classpath:datasource-pro.properties")
  static class Product {
  }
}
