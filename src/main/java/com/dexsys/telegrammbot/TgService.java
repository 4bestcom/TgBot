package com.dexsys.telegrammbot;

import com.dexsys.telegrammbot.Controllers.RootHandler;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;

@Service
public class TgService implements IStartTelegramBot {

    @PostConstruct
    @Override
    public void startBot() {
        ApiContextInitializer.init();
        TelegramBotsApi tgApi = new TelegramBotsApi();
        try {
            tgApi.registerBot(new RootHandler());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
