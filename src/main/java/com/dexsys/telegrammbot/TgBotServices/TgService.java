package com.dexsys.telegrammbot.TgBotServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;

@Service
public class TgService implements IStartTelegramBot {
    private TelegramLongPollingBot handler;

    @Autowired
    public void setHandler(TelegramLongPollingBot handler) {
        this.handler = handler;
    }

    @PostConstruct
    @Override
    public void startBot() {

        TelegramBotsApi tgApi = new TelegramBotsApi();
        try {
            tgApi.registerBot(handler);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

}
