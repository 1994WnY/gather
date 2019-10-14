package com.botech.demoalgorithm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
    @Value("${botech.swagger.basePackage}")
    private String basePackage;
    @Value("${botech.swagger.contextPath}")
    private String contextPath;
    @Value("${botech.swagger.title}")
    private String title;
    @Value("${botech.swagger.description}")
    private String description;
    @Value("${botech.swagger.version}")
    private String version;

    public SwaggerConfiguration() {
    }

    @Bean
    public Docket creeateRestApi() {
        return (new Docket(DocumentationType.SWAGGER_2)).pathMapping(this.contextPath).select()
            .apis(RequestHandlerSelectors.basePackage(this.basePackage)).paths(PathSelectors.any())
            .build().apiInfo((new ApiInfoBuilder()).title(this.title).description(this.description)
                .version(this.version).contact(new Contact("botech", "botech.com", ""))
                .license("license").licenseUrl("botech.com").build());
    }
}