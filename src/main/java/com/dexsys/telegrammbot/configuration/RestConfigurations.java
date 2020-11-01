package com.dexsys.telegrammbot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfigurations {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
