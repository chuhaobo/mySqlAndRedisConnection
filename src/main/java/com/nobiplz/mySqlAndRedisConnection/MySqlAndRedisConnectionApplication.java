package com.nobiplz.mySqlAndRedisConnection;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableCaching
@SpringBootApplication
@ComponentScan(basePackages = {
  "com.nobiplz.mySqlAndRedisConnection.config",
  "com.nobiplz.mySqlAndRedisConnection.enterInUser.controller",
"com.nobiplz.mySqlAndRedisConnection.enterInUser.service"})
/*@ComponentScan(basePackages ={ "com.nobiplz.mySqlAndRedisConnection"})*/
public class MySqlAndRedisConnectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySqlAndRedisConnectionApplication.class, args);
    }

}
