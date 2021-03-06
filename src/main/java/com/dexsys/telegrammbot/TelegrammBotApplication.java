package com.dexsys.telegrammbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class TelegrammBotApplication{

	public static void main(String[] args) {
		ApiContextInitializer.init();
		ConfigurableApplicationContext cx = SpringApplication.run(TelegrammBotApplication.class, args);
	}
}
