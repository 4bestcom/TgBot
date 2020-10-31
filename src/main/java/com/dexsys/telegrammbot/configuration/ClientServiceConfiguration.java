package com.dexsys.telegrammbot.configuration;

import com.dexsys.telegrammbot.Controller.ClientService;
import com.dexsys.telegrammbot.Controller.ClientServiceDefault;
import com.dexsys.telegrammbot.Controller.IClientServiceAction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientServiceConfiguration {


    @Bean
//    @ConditionalOnProperty(prefix = "example", name = "isClientService")
    public IClientServiceAction getClientService() {
        return new ClientService();
    }

    @Bean
//    @ConditionalOnProperty(prefix = "example", matchIfMissing = true, name = "isClientService", havingValue = "false")
    public IClientServiceAction getClientServiceDefault() {
        return new ClientServiceDefault();
    }
}
