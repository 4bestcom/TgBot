package com.dexsys.telegrammbot.services.tgbotservices;

import com.dexsys.telegrammbot.services.clientservice.IClientServiceAction;
import com.dexsys.telegrammbot.services.userservices.IUserAction;
import com.dexsys.telegrammbot.domain.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TgCommand implements ITelegramApi {
    private static final Logger log = LoggerFactory.getLogger(TgCommand.class);
    private TelegramLongPollingBot telegramLongPollingBot;
    private IClientServiceAction iClientServiceAction;

    @Autowired
    public void setiClientServiceAction(IClientServiceAction iClientServiceAction) {
        this.iClientServiceAction = iClientServiceAction;
    }

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
                "/addBirthday - добавление даты рождения пользователя\n" +
                "/infoAboutMe - инофрмация обо мне");
        try {
            telegramLongPollingBot.execute(message);
            log.info("sending message done");
        } catch (TelegramApiException e) {
            log.error("sending message error");
            e.printStackTrace();
        }
    }

    @Override
    public void sendMgEnterPhone(SendMessage message, long chatId, String inputTextMg, IUserAction userAction) {
        String phone = inputTextMg.replaceAll("[^\\+\\d]", "");
        log.info("user enter phone: " + phone);
        log.info("phone have access: " + iClientServiceAction.readUserPhoneFromServer(phone));
        if (phone.matches("\\+\\d{11}") && iClientServiceAction.readUserPhoneFromServer(phone)) {
            userAction.readUserFromBase(chatId).setPhone(phone);
            userAction.readUserFromBase(chatId).setUserStatus(UserStatus.USER_START);
            message.setChatId(chatId);
            message.setText("Номер телефона удачно добавлен");
            try {
                telegramLongPollingBot.execute(message);
                log.info("sending message done");
            } catch (TelegramApiException e) {
                log.error("sending message error");
                e.printStackTrace();
            }
            return;
        }
        message.setChatId(chatId);
        message.setText("Введите ваш номер телефона в формате +79121234567");
        try {
            telegramLongPollingBot.execute(message);
            log.info("sending message done");
        } catch (TelegramApiException e) {
            log.error("sending message error");
            e.printStackTrace();
        }
    }

    @Override
    public void sendMgFromInfoMe(long chatId, SendMessage message, IUserAction iUserAction) {
        message.setChatId(chatId);
        message.setText("UserName = " + iUserAction.readUserFromBase(chatId).getUserName() + "\n" +
                "BirthDate = " + iUserAction.readUserFromBase(chatId).getBirthDate() + "\n" +
                "phone = " + iUserAction.readUserFromBase(chatId).getPhone());
        try {
            telegramLongPollingBot.execute(message);
            log.info("sending message done");
        } catch (TelegramApiException e) {
            log.error("sending message error");
            e.printStackTrace();
        }
    }

    @Override
    public void sendMgFromMatchesRegexBirthday(long chatId, String inputTextMg, IUserAction iUserAction) {
        SendMessage message = new SendMessage();
        log.info("User enter Birthday");
        iUserAction.readUserFromBase(chatId).setBirthDate(inputTextMg);
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
    public void sendMgFromCommandAddBirthday(long chatId, IUserAction iUserAction) {
        SendMessage message = new SendMessage();
        log.info("user press button /addBirthday");
        if (iUserAction.readUserFromBase(chatId).getBirthDate() != null) {
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
