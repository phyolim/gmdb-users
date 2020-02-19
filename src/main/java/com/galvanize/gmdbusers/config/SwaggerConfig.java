package com.galvanize.gmdbusers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@ComponentScan
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket movieApiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("users-api-1")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/v1/users/.*"))
                .build()
                .apiInfo(new ApiInfoBuilder().version("1").title("Users API").build());
    }
}
