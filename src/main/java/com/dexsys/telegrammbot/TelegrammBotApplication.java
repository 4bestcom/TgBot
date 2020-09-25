package com.dexsys.telegrammbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TelegrammBotApplication {
	private static final Logger log = LoggerFactory.getLogger(TelegrammBotApplication.class);

	public static void main(String[] args) {
		log.debug("first run");
		for (int i = 0; i < 50; i++) {
			i = i +1;
		}
		log.info("first run info");
	//	SpringApplication.run(TelegrammBotApplication.class, args);

	}

}
