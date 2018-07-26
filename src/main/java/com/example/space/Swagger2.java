package com.example.space;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author liyu
 * @date 18-7-26
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("spring boot tutorial RESTful APIs")
                        .description("springboot API")
                        .contact("aspirin")
                        .version("1.0")
                        .build()
                ).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.space.api.web"))
                .paths(PathSelectors.any())
                .build();
    }
}
