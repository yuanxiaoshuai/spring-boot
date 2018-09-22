package com.springboot.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
* @ClassName: Swagger 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author alex 
* @date 2017年12月7日 下午6:25:02 
 */
@Configuration
@EnableSwagger2
public class Swagger {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("com.springboot.jpa"))
				/*.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))*/
				.paths(PathSelectors.any())
				.build();
	}

	 private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("Spring Boot中使用Swagger2构建RESTful APIs")
	                .description("知悦接口api")
	                .termsOfServiceUrl("www.baidu.com")
	                .version("1.0")
	                .build();
	    }
}
