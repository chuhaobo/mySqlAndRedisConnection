package com.nobiplz.mySqlAndRedisConnection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
/*@ComponentScan(basePackages ={ "com.nobiplz.mySqlAndRedisConnection"})*/
public class MySqlAndRedisConnectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySqlAndRedisConnectionApplication.class, args);
    }

}
