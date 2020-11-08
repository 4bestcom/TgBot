package com.dexsys.telegrammbot.configuration;

import com.dexsys.telegrammbot.services.clientservice.ClientService;
import com.dexsys.telegrammbot.services.clientservice.ClientServiceDefault;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientServiceConfiguration {

    @Bean
    @ConditionalOnProperty(name = "useMock", havingValue = "true")
    public ClientService getClientService() {
        return new ClientService();
    }

    @Bean
    @ConditionalOnMissingBean(ClientService.class)
    public ClientServiceDefault getClientServiceDefault() {
        return new ClientServiceDefault();
    }
}
