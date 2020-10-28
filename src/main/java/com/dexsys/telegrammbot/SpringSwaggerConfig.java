package com.dexsys.telegrammbot;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SpringSwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dexsys.telegrammbot"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(metaData());
    }



    public ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("RestAPI Telegram bot")
                .description("This RestAPI telegram birthday bot of Roman")
                .contact(new Contact("Roman Bogatyrev", "https://t.me/bestcom4", "manhunt10@yandex.ru"))
                .version("0.9.1")
                .build();
    }
}

