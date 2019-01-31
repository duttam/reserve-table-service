package com.reservation.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import io.swagger.models.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.reservation"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
              //  .securitySchemes(apiSecuritySchema());
    }
    
    private ApiInfo getApiInfo() {
        
        
        return new ApiInfoBuilder()
                .title("Table reservation app")
                .description("Api Definition for reserve table application")
                .version("1.0")
                .licenseUrl("http://localhost:8080")
                .contact("P borah")
                .build();
    }
    
//    @Bean
//    public SecurityConfiguration securityInfo() {
//        return new SecurityConfiguration(null, null, null, null, "", ApiKeyVehicle.HEADER, "Authorization", "");
//    }
//
//    private List<SecurityScheme> apiSecuritySchema() {
//        //return new ApiKey("Authorization", "Authorization", "header");
//        List<SecurityScheme> schemeList = new ArrayList<>();
//        schemeList.add(new ApiKey(HttpHeaders.AUTHORIZATION, "Authorization", "header"));
//        return schemeList;
//    }
}