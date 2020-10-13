package com.dexsys.telegrammbot.TgBotServices;

import com.dexsys.telegrammbot.Services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TgCommand implements ITelegramApi{
    private static final Logger log = LoggerFactory.getLogger(TgCommand.class);
    private TelegramLongPollingBot telegramLongPollingBot;
    @Autowired
    public void setTelegramLongPollingBot(TelegramLongPollingBot telegramLongPollingBot) {
        this.telegramLongPollingBot = telegramLongPollingBot;
    }

    @Override
    public void sendMgErrorEnterBirthday(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Введен неверный формат даты");
        try {
            telegramLongPollingBot.execute(message);
            log.info("sending message done");
        } catch (TelegramApiException e) {
            log.error("sending message error");
            e.printStackTrace();
        }
    }

    @Override
    public void sendMgFromHelp(long chatId, SendMessage message) {
        message.setChatId(chatId);
        message.setText("/help - помощь\n" +
                "/start - запуск бота\n" +
                "/addBirthday - добавление даты рождения пользователя");
        try {
            telegramLongPollingBot.execute(message);
            log.info("sending message done");
        } catch (TelegramApiException e) {
            log.error("sending message error");
            e.printStackTrace();
        }
    }

    @Override
    public void sendMgFromMatchesRegexBirthday(long chatId, String inputTextMg, UserServices userServices) {
        SendMessage message = new SendMessage();
        log.info("User enter Birthday");
        userServices.readUserFromBase(chatId).setBirthDate(inputTextMg);
        log.info("system find this user in DB, added before him Birthday");
        message.setChatId(chatId);
        message.setText("Спасибо");
        try {
            telegramLongPollingBot.execute(message);
            log.info("sending message done");
        } catch (TelegramApiException e) {
            log.error("sending message error");
            e.printStackTrace();
        }
    }

    @Override
    public void sendMgFromCommandAddBirthday(long chatId, UserServices userServices) {
        SendMessage message = new SendMessage();
        log.info("user press button /addBirthday");
        if (userServices.readUserFromBase(chatId).getBirthDate() != null) {
            return;
        }
        message.setChatId(chatId);
        message.setText("Введите дату рождения в формате 19.05.1982");
        try {
            telegramLongPollingBot.execute(message);
        } catch (TelegramApiException e) {
            log.error("sending message error");
            e.printStackTrace();
        }
    }

    @Override
    public void sendMgFromDefaultRunning(long chatId, String inputTextMg, SendMessage message) {
        message.setChatId(chatId);
        message.setText(inputTextMg);
        log.info("user writing message");
        try {
            telegramLongPollingBot.execute(message);
            log.info("sending message done");
        } catch (TelegramApiException e) {
            log.error("sending message error");
            e.printStackTrace();
        }
    }
}
