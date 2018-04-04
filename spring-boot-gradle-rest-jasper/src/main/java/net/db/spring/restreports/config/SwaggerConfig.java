package net.db.spring.restreports.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static final String TITLE = "SPRING BOOT - REST - JASPER";
	private static final String DESCRIPTION = "This API is for application servis xyz...";
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.db.spring.restreports"))
                .paths(regex("/report.*"))
                .build()
                .apiInfo(metaData());
    }
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
        		TITLE,
        		DESCRIPTION,
                null,
                null,
                ApiInfo.DEFAULT_CONTACT,
                null,
                null);
        return apiInfo;
    }
}
