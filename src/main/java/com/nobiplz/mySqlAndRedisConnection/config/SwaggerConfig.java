package com.nobiplz.mySqlAndRedisConnection.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@ComponentScan(basePackages = {"com.nobiplz.mySqlAndRedisConnection.config"})
@Configuration
public class SwaggerConfig extends WebMvcConfigurationSupport {

  @Bean
  public Docket createRestApi(){
    return new Docket(DocumentationType.SWAGGER_2)
      .apiInfo(apiInfo())
      .select()
      .apis(RequestHandlerSelectors.any())
      .paths(PathSelectors.any())
      .build();
  }

  private ApiInfo apiInfo(){
    return new ApiInfoBuilder()
      .title("EXCEL 转换")
      .description("description:项目摘要")
      .termsOfServiceUrl("www.baidu.com")
      .contact(new Contact("1nobiPlz","https://juejin.im/user/5e871cf6e51d4546c0382740/posts","543148817@qq.com"))
      .version("1.19")
      .build();
  }

  /**
   * 发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。 需要重新指定静态资源
   *
   * @param registry
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**").addResourceLocations(
      "classpath:/static/");
    registry.addResourceHandler("swagger-ui.html").addResourceLocations(
      "classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**").addResourceLocations(
      "classpath:/META-INF/resources/webjars/");
    super.addResourceHandlers(registry);
  }

}
